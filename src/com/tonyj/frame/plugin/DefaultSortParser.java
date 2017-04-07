package com.tonyj.frame.plugin;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * <b>本类是 排序子句解析器。
 * </b><br><br>
 * @author   TonyJ
 * @version  ${version} 2013-8-22 ${desc}
 * @since    SSM version 1.0
 *
 */
public class DefaultSortParser implements ISortParser {
    public static final String SORT_ORDER_SEPARATOR = "^";
    public static final String SORT_SORT_SEPARATOR = "|";
    
    @Override
    public List<SortOrder> parseToClause(String param) {
        if(StringUtils.isEmpty(param)){
            return null;
        }
        
        param = StringUtils.replace(param, " ", SORT_ORDER_SEPARATOR);
        param = StringUtils.replace(param, ",", SORT_SORT_SEPARATOR);
        
        String[] clauses = StringUtils.split(param, SORT_SORT_SEPARATOR);
        List<SortOrder> orderList = new ArrayList<SortOrder>(clauses.length);
        for(String clause : clauses){
            if(StringUtils.isNotEmpty(clause)){
                String[] so = StringUtils.split(clause,SORT_ORDER_SEPARATOR);
                SortOrder order = null;
                if(so.length == 1 || StringUtils.isEmpty(so[1])){
                    order = new SortOrder(so[0], DEFAULT_ORDER_DESC);
                }else if(!StringUtils.equalsIgnoreCase(so[1], ORDER_DESC) 
                            && !StringUtils.equalsIgnoreCase(so[1], ORDER_ASC)){
                    order = new SortOrder(so[0], DEFAULT_ORDER_DESC);
                }else{
                    order = new SortOrder(so[0], so[1]);
                }
                orderList.add(order);
            }
        }
        
        return orderList;
    }
}
