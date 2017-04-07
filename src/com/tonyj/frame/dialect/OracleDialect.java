package com.tonyj.frame.dialect;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * <b>本类是定义Oracle数据库方言的接口。
 * </b><br><br>
 * <em>
 * <b>功能：</b>定义数据库方言
 * 
 * </em>
 * @author   TonyJ
 * @version  ${version} 2013-06-15 ${desc}
 * @since    SSM version 1.0
 *
 */
public class OracleDialect implements Dialect {


	@Override
	public String getLimitString(String sql, long offset, int limit, String orderClause) {
		sql = sql.trim();
//		boolean isForUpdate = false;
//		if (sql.toLowerCase().endsWith(" for update")) {
//			sql = sql.substring(0, sql.length() - 11);
//			isForUpdate = true;
//		}
		
		StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM (SELECT rs_.*,ROWNUM rn FROM (");
		
		
		if(null != orderClause && !orderClause.isEmpty()){
		    sqlBuffer.append(" select * from(")
		    .append(sql).append(") order by ")
		    .append(orderClause);
		}else{
		    sqlBuffer.append(sql);
		}
		sqlBuffer.append(" ) rs_ WHERE ROWNUM<=")
			     .append(limit + offset)
			     .append(") WHERE rn >")
				 .append(offset);
		
//		if (isForUpdate) 
//		{
//			sqlBuffer.append(" for update");
//		}
		
		return sqlBuffer.toString();
	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		return null;
	}

	@Override
	public void setLimitParamters(PreparedStatement ps, int parameterSize,
			int offset, int limit) throws SQLException {
		
	}

}
