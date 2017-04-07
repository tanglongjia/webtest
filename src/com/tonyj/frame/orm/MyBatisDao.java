package com.tonyj.frame.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.ReflectHelper;


/**
 *@author TonyJ
 *@time 2015-1-31 04:43:08
 *@email tanglongjia@126.com
 */
public class MyBatisDao<T extends BaseEntity>  implements BaseSqlMap<T> {

	private static final Logger logger = Logger.getLogger(MyBatisDao.class);
    
	protected Class<T> entityClass;
	
	@Autowired
	protected SqlSessionTemplate sqlSession;
	
	@SuppressWarnings("unchecked")
	public MyBatisDao(){
		this.entityClass = ReflectHelper.getSuperClassGenericType(getClass(), 0);
	}
	
	public SqlSession getSqlSession(){
		return sqlSession;
	}
	
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public T selectSingle(Object param) {
		if(null == param){
            logger.error("非法参数：param为空！");
            throw new IllegalArgumentException("非法参数：param为空！");
        }
        T result = null;
        try{
            result = (T) getSqlSession().selectOne(entityClass.getName()+SQL_SELECT_SINGLE, param);
        }catch(Throwable e){
            logger.error(e);
        }
        return result;
	}

	@Override
	public T insert(T entity) {
		if(null == entity){
            logger.error("参数对象为null!");
            throw new IllegalArgumentException("参数不可为null！");
        }
	    try{
            getSqlSession().insert(entityClass.getName()+SQL_INSERT, entity);
        }catch(Throwable e){
            logger.error(e);
            return null;
        }
		
		return entity;
	}

	@Override
	public boolean update(T entity) {
		if(null == entity){
            throw new IllegalArgumentException("参数不可为null！");
        }
        try{
            getSqlSession().update(entityClass.getName()+SQL_UPDATE, entity);
        }
        catch (Exception e) {
           logger.error("更新数据异常：", e);
           return false;
        }
        return true;
	}

	@Override
	public boolean delete(Object param) {
		try{
	        getSqlSession().delete(entityClass.getName()+SQL_DELETE, param);
	    }catch (Exception e) {
	        logger.error("删除数据异常：",e);
            return false;
        }
	    return true;
	}
	
	@Override
	public final Page selectPage(Map<String, Object> param, int pageNo, int pageSize, String ... orderClause) {
		
	    // 构建查询参数对象
	    Page page = new Page(pageNo, pageSize);
		if(null == param){
		    param = new HashMap<String,Object>();
		}
		
		if(ArrayUtils.isNotEmpty(orderClause)){
		    for(int i = 0; i < orderClause.length ; ){
		        if(i+1 < orderClause.length){
		            page.addOrderClause(orderClause[i], orderClause[i+1]);
		        }else{
		            page.addOrderClause(orderClause[i], null);
		        }
		        i += 2 ;
		    }
		}
		param.put("page", page);
		
		List<T> result = null;
        try{
           result  =  getSqlSession().selectList(entityClass.getName()+SQL_SELECT_PAGE, param) ;
        }catch(Throwable e){
            logger.error(e);
        }
		page.setResultList(result);
		return page;
	}

	
	@Override
	public final Page selectPage(T entity, int pageNo, int pageSize, String ... orderClause) {
	    // 构建查询参数对象
	    Page page = new Page(pageNo, pageSize);
        if(null == entity){
            throw new IllegalArgumentException("参数不可为null！");
        }
        // 设置排序子句
        if(ArrayUtils.isNotEmpty(orderClause)){
            page.setOrderClause(orderClause[0]);
        }
		entity.setPagination(page);
		
		List<T> result = null;
        try{
           result  =  getSqlSession().selectList(entityClass.getName()+SQL_SELECT_PAGE, entity) ;
        }catch(Throwable e){
            logger.error(e);
        }
		page.setResultList(result);
		return page;
	}
	
	@Override
	public Page selectPage(Map<String, Object> param, Page page, String... sqlIdAndNamespace) {
	   
	    String sqlId = SQL_SELECT_PAGE;
	    String namespace = entityClass.getName();
	    if(ArrayUtils.isNotEmpty(sqlIdAndNamespace)){
	        if(StringUtils.isNotEmpty(sqlIdAndNamespace[0])){
	            if(sqlIdAndNamespace[0].startsWith(".")){
	                sqlId = sqlIdAndNamespace[0];
	            }else{
	                sqlId = "." + sqlIdAndNamespace[0];
	            }
	        }
	        if(sqlIdAndNamespace.length > 1 && StringUtils.isNotEmpty(sqlIdAndNamespace[1])){
	            if(sqlIdAndNamespace[1].endsWith(".")){
	                namespace = sqlIdAndNamespace[1].substring(1);
	            }else{
	                namespace = sqlIdAndNamespace[1];
	            }
	        }
	    }
	    
	    // 构建查询参数对象
	    if(null == param){
            param = new HashMap<String,Object>();
        }
	    if(null == page){
            page = new Page();
        }
        param.put("page", page);
        List<T> result = null;
        try{
           result  =  getSqlSession().selectList(StringUtils.join(namespace, sqlId), param);
        }catch(Throwable e){
            logger.error(e);
        }
        page.setResultList(result);
        return page;
    }

	 @Override
	    public Page selectPage(T entity, Page page, String... sqlIdAndNamespace) {
	        String sqlId = SQL_SELECT_PAGE;
	        String namespace = entityClass.getName();
	        if(ArrayUtils.isNotEmpty(sqlIdAndNamespace)){
	            if(StringUtils.isNotEmpty(sqlIdAndNamespace[0])){
	                if(sqlIdAndNamespace[0].startsWith(".")){
	                    sqlId = sqlIdAndNamespace[0];
	                }else{
	                    sqlId = "." + sqlIdAndNamespace[0];
	                }
	            }
	            if(sqlIdAndNamespace.length > 1 && StringUtils.isNotEmpty(sqlIdAndNamespace[1])){
	                if(sqlIdAndNamespace[1].endsWith(".")){
	                    namespace = sqlIdAndNamespace[1].substring(1);
	                }else{
	                    namespace = sqlIdAndNamespace[1];
	                }
	            }
	        }
	        // 构建查询参数对象
	        if(null == entity || null == page){
	            logger.error("构建查询参数对象时异常！");
	            throw new IllegalArgumentException("参数不可为null！");
	        }
	        entity.setPagination(page);
	        List<T> result = null;
	        try{
	           result  =  getSqlSession().selectList(StringUtils.join(namespace,sqlId) , entity) ;
	        }catch(Throwable e){
	            logger.error(e);
	        }
	        page.setResultList(result);
	        return page;
	    }

}
