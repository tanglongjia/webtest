package com.tonyj.frame.auth;


/**
 * <b>本类是 定义数据行级访问权限实现方法的接口。
 * </b><br><br>
 * <em>
 * <b>功能：</b>定义数据行级访问权限实现方法。
 * </em>
 * @author   TonyJ
 * @version  ${version} 2013-12-7 ${desc}
 * @since    SSM version 1.0
 *
 */
public interface IRowLevelLimited {
    /**
     * 
     * <b>获取系统对数据行级访问权限的支持与否
     * </b>
     * <br>
     * @return true:支持，false-不支持
     *
     */
    public abstract boolean rowLevelSupported();
    /**
     * 
     * <b>判断某个sql是否被限制数据的行级访问
     * </b>
     * <br>
     * @param namespace - sql的namespace
     * @param sqlId - sql的ID
     * @return true:被限制，false-不被限制
     *
     */
    public abstract boolean isLimited(String namespace, String sqlId);
    /**
     * 
     * <b>获取限制数据行级访问的SQL文。
     * </b>
     * <br>
     * @param namespace - sql的namespace
     * @param sqlId - sql的ID
     * @return SQL文
     *
     */
    public abstract String limitedBySql(String namespace, String sqlId);
}
