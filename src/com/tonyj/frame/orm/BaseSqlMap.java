package com.tonyj.frame.orm;

import java.util.Map;

import com.tonyj.frame.plugin.Page;

/**
 *@author TonyJ
 *@time 2015-1-31 下午03:45:52
 *@email tanglongjia@126.com
 */
public interface BaseSqlMap<T extends BaseEntity> extends SqlMap {

	/**
	 * 保存
	 */
	public static final String SQL_INSERT=".insert";
	
	/**
	 * 删除
	 */
	public static final String SQL_DELETE=".delete";
	
	/**
	 * 更新
	 */
	public static final String SQL_UPDATE=".update";
	
	/**
	 * 根据Id查询
	 */
	public static final String SQL_SELECT_SINGLE = ".selectSingle";
	
	/** 分页查询SQL的ID：selectPage */
	public static final String SQL_SELECT_PAGE = ".selectPage";
	
	/**
	 * 查询单个
	 * @param param
	 * @return
	 */
	T selectSingle(Object param);
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	T insert(T entity);
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	boolean update(T entity);
	
	/**
	 * 删除一个
	 * @param param
	 * @return
	 */
	boolean delete(Object param);
	
	/**
	 * <b> 分页查询
     * </b>
     * <dd> <pre><b>适用条件:</b>
     *          SQL文件中必须存在id为“selectPage”的sql语句，且SQL的命名空间为
     *       实体类全路径名。例如:table表对应值对象类为User，则要使用本方法，须：
     *         ...
     *         &lt;mapper namespace="cn.jsmass.demo.entity.User"&gt;
     *                 &lt;select id="selectPage" ...&gt;
     *         ...
     *         &lt;/mapper&gt;
     * </pre>
	 * @param entity - 查询参数实体
	 * @param pageNo - 当前页码
	 * @param pageSize  - 每页大小
	 * @param orderClause - 排序子句[orderColumn,direction,orderColumn,direction...]
	 * @return
	 */
	
	Page selectPage(T entity, int pageNo, int pageSize,String ... orderClause);
	/**
     * <b> 分页查询
     * </b>
     * <dd> <pre><b>适用条件:</b>
     *          SQL文件中必须存在id为“selectPage”的sql语句，且SQL的命名空间为
     *       实体类全路径名。例如:table表对应值对象类为User，则要使用本方法，须：
     *         ...
     *         &lt;mapper namespace="cn.jsmass.demo.entity.User"&gt;
     *                 &lt;select id="selectPage" ...&gt;
     *         ...
     *         &lt;/mapper&gt;
     * </pre>
	 * @param param - 查询参数Map
	 * @param pageNo - 当前页码
	 * @param pageSize  - 每页大小
     * @param orderClause - 排序子句[orderColumn,direction,orderColumn,direction...]
	 * @return
	 */
	/**
     * <b> 分页查询
     * </b>
     * <dd> <pre><b>适用条件:</b>
     *          SQL文件中必须存在id为“selectPage”的sql语句，且SQL的命名空间为
     *       实体类全路径名。例如:table表对应值对象类为User，则要使用本方法，须：
     *         ...
     *         &lt;mapper namespace="cn.jsmass.demo.entity.User"&gt;
     *                 &lt;select id="selectPage" ...&gt;
     *         ...
     *         &lt;/mapper&gt;
     * </pre>
     * @param entity - 查询参数实体
     * @param page - 分页设置实体
     * @param sqlIdAndNamespace - sqlId和命名空间（可选）
     * @return
     */
    
    Page selectPage(T entity, Page page, String... sqlIdAndNamespace);
    /**
     * <b> 分页查询
     * </b>
     * <dd> <pre><b>适用条件:</b>
     *          SQL文件中必须存在id为“selectPage”的sql语句，且SQL的命名空间为
     *       实体类全路径名。例如:table表对应值对象类为User，则要使用本方法，须：
     *         ...
     *         &lt;mapper namespace="cn.jsmass.demo.entity.User"&gt;
     *                 &lt;select id="selectPage" ...&gt;
     *         ...
     *         &lt;/mapper&gt;
     * </pre>
     * @param param - 查询参数Map
     * @param pageNo - 当前页码
     * @param pageSize  - 每页大小
     * @param orderClause - 排序子句[orderColumn,direction,orderColumn,direction...]
     * @return
     */
	Page selectPage(Map<String, Object> param, int pageNo, int pageSize, String ... orderClause);
	/**
     * <b> 分页查询
     * </b>
     * <dd> <pre><b>适用条件:</b>
     *          SQL文件中必须存在id为“selectPage”的sql语句，且SQL的命名空间为
     *       实体类全路径名。例如:table表对应值对象类为User，则要使用本方法，须：
     *         ...
     *         &lt;mapper namespace="cn.jsmass.demo.entity.User"&gt;
     *                 &lt;select id="selectPage" ...&gt;
     *         ...
     *         &lt;/mapper&gt;
     * </pre>
     * @param param - 查询参数Map
     * @param page - 分页设置对象
     * @param sqlIdAndNamespace - sqlId和命名空间（可选）
     */
    Page selectPage(Map<String, Object> param, Page page, String... sqlIdAndNamespace);
	
}
