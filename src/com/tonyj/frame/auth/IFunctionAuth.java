package com.tonyj.frame.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * <b>本类是定义操作按钮的访问权限方法的接口。
 * </b><br><br>
 * <em>
 * <b>功能：</b>定义操作按钮的访问权限方法。
 * 
 * </em>
 * @author   TonyJ
 * @version  ${version} 2013-12-7 ${desc}
 * @since    SSM version 1.0
 *
 */
public interface IFunctionAuth<T> {
    public abstract List<T> authFunction(HttpServletRequest request);
}
