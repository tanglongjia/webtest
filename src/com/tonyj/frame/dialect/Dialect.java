package com.tonyj.frame.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 
 * <b>本类是定义数据库方言的接口。
 * </b><br><br>
 * <em>
 * <b>功能：</b>定义数据库方言
 * 
 *
 */
public interface Dialect {

	public static enum Type{
		MYSQL,
		ORACLE
	}
	/**
	 * 
	 *<b> 拼装分页查询的SQL语句。
	 * </b>
	 * <br>
	 * @param sql - 原始SQL语句
	 * @param offset - 偏移量，即分页起始记录的行号
	 * @param limit - 限制，即每页显示记录
	 * @return 根据offset和limit拼装后的SQL语句
	 *
	 */
	public String getLimitString(String sql, long offset, int limit, String orderClause);
	/**
	 * 
	 *<b>拼装偏移查询的SQL语句。
	 * </b>
	 * <br>
	 * @param sql - 原始SQL语句
	 * @param hasOffset - 是否存在偏移量？
	 * @return 如果存在偏移量，则返回偏移之后的sql语句
	 *
	 */
    public String getLimitString(String sql, boolean hasOffset);   
    /**
     * 
     *<b> 设置限定参数
     * </b>
     * <br>
     * @param ps - PreparedStatement
     * @param parameterSize - 参数数量
     * @param offset - 偏移量
     * @param limit - 限制
     * @throws SQLException
     *
     */
    public void setLimitParamters(PreparedStatement ps, int parameterSize, int offset, int limit) throws SQLException;  
}
