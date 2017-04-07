package com.tonyj.frame.plugin;

import org.apache.commons.lang3.StringUtils;


/**
 * <b>本类是排序子句的管理类。
 * </b>
 * @author   TonyJ
 * @version  ${version} 2013-8-22 ${desc}
 * @since    SSM version 1.0
 *
 */
public class SortOrder{
    
    
    public SortOrder(String orderColumn, String direction){
        this.sortColumn = orderColumn;
        this.order = direction;
    }
    String sortColumn;
    String order;
     
    public String toCaluse(){
        return StringUtils.join(sortColumn," ",order);
    }
}
