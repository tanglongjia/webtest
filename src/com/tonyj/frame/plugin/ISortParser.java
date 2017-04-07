package com.tonyj.frame.plugin;


import java.util.List;


/**
 * <b>本类是定义用户界面传过来排序字符串的解析器的接口。
 * </b><br><br>
 * @author   TonyJ
 * @version  ${version} 2013-8-22 ${desc}
 * @since    SSM version 1.0
 *
 */
public interface ISortParser {
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";
    public static final String DEFAULT_ORDER_DESC = ORDER_DESC;
    
    List<SortOrder> parseToClause(String param);
}
