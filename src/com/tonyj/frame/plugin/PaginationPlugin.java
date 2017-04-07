package com.tonyj.frame.plugin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.tonyj.frame.auth.IRowLevelLimited;
import com.tonyj.frame.dialect.Dialect;
import com.tonyj.frame.dialect.Dialect.Type;
import com.tonyj.frame.dialect.MySqlDialect;
import com.tonyj.frame.dialect.OracleDialect;
import com.tonyj.frame.orm.BaseEntity;
import com.tonyj.frame.util.ReflectHelper;

/**
 * 
 * <b>本类是 实现物理分页查询的拦截器。
 * </b><br><br>
 * <em>
 * <b>功能：</b>作为mybatis的插件，实现物理分页查询。
 * 
 * </em>
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationPlugin implements Interceptor {
    
    private final static Log log = LogFactory.getLog(PaginationPlugin.class);
    
    //数据库方言
    private static String dialect = "ORACLE";
    
    protected IRowLevelLimited rowLevelLimited = null;
    
    //mapper.xml中需要拦截的SQL语句的ID(正则匹配)
    private static String pageSqlId = ".*selectPage.*";
    
    /**
     * <pre>
     * <b>实现原理：</b>拦截SQL语句的执行， 当要执行的SQL的ID为分页查询SQL的ID时，拼装记录
     *            条数统计的SQL语句并执行，将总记录数量参数Page类的实例中；调用数据库
     *            方言Dialect，拼装分页查询SQL语句并替换原始SQL；分页查询SQL的ID默认
     *            为“.*selectPage.*”，也可在配置文件mybatis-config.xml中配置. 如：
     *               <blockquote>
     *               &lt;plugin interceptor="com.jsmass.frame.ssm.plugin.PaginationPlugin" &gt;
     *        	         <b>&lt;property name="pageSqlId" value=".*selectPage.*"/ &gt;</b>  
     *	        	     ......
     *              &lt;/plugin &gt;
     * 				</blockquote>
     * </pre>
     */
    @Override
	@SuppressWarnings("unchecked")
    public Object intercept(Invocation ivk) throws Throwable {
        if(ivk.getTarget() instanceof RoutingStatementHandler)
        {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            // 如果sql语句类型不是select查询语句，则直接继续进行，不再做数据权限限制和分页限制
            if(!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())){
                return ivk.proceed();
            }
            
            BoundSql boundSql = delegate.getBoundSql();
            //行级数据访问权限限定
            String sqlId = mappedStatement.getId();
            String namespace = StringUtils.substringBeforeLast(sqlId, ".");    
            String id = StringUtils.substringAfterLast(sqlId, "."); 
            
            // 有行级数据访问权限限定接口的实现和且支持数据行级访问权限限制,则进行限制sql绑定.
            if(null != rowLevelLimited && rowLevelLimited.rowLevelSupported() && rowLevelLimited.isLimited(namespace, id)){
                String limitedSql = rowLevelLimited.limitedBySql(namespace, id);
                if(StringUtils.isNotEmpty(limitedSql)){
                    String sql  = boundLimitedSql(boundSql.getSql(), limitedSql);
                    ReflectHelper.setValueByFieldName(boundSql, "sql", sql); //将分页sql语句反射回BoundSql. 
                }
            }//行级数据访问权限限定
            
            
            Object parameterObject = boundSql.getParameterObject();
            //拦截需要分页的SQL 
            if(mappedStatement.getId().matches(pageSqlId))
            {
                Page page = null;
                
                //分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
                if(parameterObject == null)
                {
                    throw new NullPointerException("parameterObject尚未实例化！");
                }
                else if(parameterObject instanceof Page)
                {//参数就是Page实体 
                    page = (Page) parameterObject;
                }
                else if(parameterObject instanceof BaseEntity)
                {
                    page = ((BaseEntity) parameterObject).getPagination();
                    if(page == null)
                    {
                        log.warn("分页查询未设置参数pagination的值,将采用RowBundl的默认值！");
                        page = new Page(RowBounds.NO_ROW_OFFSET / RowBounds.NO_ROW_LIMIT, RowBounds.NO_ROW_LIMIT);
                    }
                }
                else if(parameterObject instanceof Map)
                {
                    Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
                    Object p = paramMap.get("page");
                    if(null != p || p instanceof Page)
                    {
                        page = (Page) p;
                    }
                    else
                    {
                        int offset = (null != paramMap.get(Page.OFFSET_NAME)) ? -1
                                : (Integer) paramMap.get(Page.OFFSET_NAME);
                        int limit = (null != paramMap.get(Page.LIMIT_NAME)) ? -1
                                : (Integer) paramMap.get(Page.LIMIT_NAME);
                        if(offset == -1 || limit == -1)
                        {
                            log.warn("分页查询未设置参数offset和limit的值,将采用RowBundl的默认值！");
                            offset = RowBounds.NO_ROW_OFFSET;
                            limit = RowBounds.NO_ROW_LIMIT;
                        }
                        page = new Page(offset / limit, limit);
                    }
                }
                page = totalCount(ivk, mappedStatement, boundSql, page);
                Type databaseType = null;
                try
                {
                    databaseType = Dialect.Type.valueOf(dialect);
                }
                catch(Exception e)
                {
                    log.warn("未配置数据库类型，将默认采用ORACLE数据库!");
                }
                if(null == databaseType)
                {
                    log.warn("未配置数据库类型，将默认采用ORACLE数据库!");
                }
                Dialect dia = null;
                switch(databaseType){
                    case MYSQL:
                        dia = new MySqlDialect();
                        break;
                    case ORACLE:
                        dia = new OracleDialect();
                        break;
                    default:
                        dia = new OracleDialect();
                }
                if(null != dia){
                    
                    String sql = boundSql.getSql();
                    String pageSql = dia.getLimitString(sql, page.getOffset(), page.getpagesize(),page.getOrderClause());
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.   
                }
            }
            
        }
        return ivk.proceed();
    }
    
    private String boundLimitedSql(String originalSql, String limitedSql){
        if(StringUtils.isEmpty(originalSql) || StringUtils.isEmpty(limitedSql)){
            log.info("在进行数据行级访问权限限制时，参数异常：限制SQL为空");
            return originalSql;
        }
        
        StringBuffer sqlBuf = new StringBuffer("SELECT * FROM (");
        sqlBuf.append(originalSql);
        sqlBuf.append(") temp_  INNER JOIN(select id as ID_ from(");
        sqlBuf.append(limitedSql);
        sqlBuf.append("))pr_ ON (temp_.ID = pr_.ID_)");
        return sqlBuf.toString();
    }
    
    
    /**
     * 
     * <b> 拼装统计查询语句并执行，然后将统计结果放入分页对象page中。
     * </b>
     * <br>
     * @param ivk - 调用器
     * @param mappedStatement - MappedStatement
     * @param boundSql
     * @param page - 分页对象
     * @return
     * @throws SQLException
     * @throws IllegalAccessException 
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     *
     */
    private Page totalCount(Invocation ivk, MappedStatement mappedStatement, BoundSql boundSql, Page page)
            throws SQLException, SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Object parameterObject = boundSql.getParameterObject();
        Connection connection = (Connection) ivk.getArgs()[0];
        String sql = boundSql.getSql();
        String countSql = "select count(0)  as tmp_count from (" + sql + ")rs_"; //记录统计   
      
        PreparedStatement countStmt = connection.prepareStatement(countSql);
        
        setParameters(countStmt, mappedStatement, boundSql, parameterObject);
        
        //log.debug("查询统计总结果数量SQL：boundSql="+boundSql.getSql());
        
        ResultSet rs = countStmt.executeQuery();
        int count = 0;
        if(rs.next())
        {
            count = rs.getInt(1);
        }
        rs.close();
        countStmt.close();
        page.setTotalResult(count);
        return page;
    }
    
    /**
     * 
     * <b> 设置SQL查询的条件参数值
     * </b>
     * <br>
     * @param ps - PreparedStatement
     * @param mappedStatement - MappedStatement
     * @param boundSql -  BoundSql
     * @param parameterObject - 参数对象
     * @throws SQLException
     *
     */
    @SuppressWarnings("unchecked")
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        
        //log.debug("查询统计总结果数量时设置参数SQL：boundSql="+boundSql.getSql());
        if(parameterMappings != null)
        {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = (parameterObject == null) ? null : configuration.newMetaObject(parameterObject);
            for(int i = 0; i < parameterMappings.size(); i++)
            {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if(parameterMapping.getMode() != ParameterMode.OUT)
                {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if(parameterObject == null)
                    {
                        value = null;
                    }
                    else if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass()))
                    {
                        value = parameterObject;
                    }
                    else if(boundSql.hasAdditionalParameter(propertyName))
                    {
                        value = boundSql.getAdditionalParameter(propertyName);
                    }
                    else if(propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName()))
                    {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if(value != null)
                        {
                            value = configuration.newMetaObject(value).getValue(
                                                                                propertyName.substring(prop.getName()
                                                                                                           .length()));
                        }
                    }
                    else
                    {
                        value = (metaObject == null) ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if(typeHandler == null)
                    {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
                                + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }
    
    @Override
	public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }
    
    /**
     * 设置全局属性。
     */
    @Override
	public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if(StringUtils.isEmpty(dialect))
        {
            try
            {
                throw new PropertyException("dialect property is not found!");
            }
            catch(PropertyException e)
            {
                log.error("Exception occur when reading dialect config:", e);
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if(StringUtils.isEmpty(pageSqlId))
        {
            try
            {
                throw new PropertyException("pageSqlId property is not found!");
            }
            catch(PropertyException e)
            {
                log.error("Exception occur when reading pageSqlId config:", e);
            }
        }
        String limitedClassName = p.getProperty("rowLevelLimited");
        if(StringUtils.isNotEmpty(limitedClassName)){
            log.info("rowLevelLimited ==="+limitedClassName);
            try
            {
                rowLevelLimited = (IRowLevelLimited) Class.forName(limitedClassName).newInstance();
            }
            catch(InstantiationException e)
            {
                log.error("创建数据行级访问权限限制实例时异常，",e);
            }
            catch(IllegalAccessException e)
            {
                log.error("创建数据行级访问权限限制实例时异常，",e);
            }
            catch(ClassNotFoundException e)
            {
                log.error("创建数据行级访问权限限制实例时异常，",e);
            }
        }
        
    }
}
