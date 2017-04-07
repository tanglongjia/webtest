package com.tonyj.frame.plugin;


/**
 * <b>本类是排序子句解析器工程类。
 * </b><br><br>
 * @author   TonyJ
 * @version  ${version} 2013-8-27 ${desc}
 * @since    SSM version 1.0
 *
 */
public class SortParserFactory {
    private SortParserFactory(){
    }
    
    private static ISortParser sortParser;
    
    public void setSortParser(ISortParser parser){
        sortParser = parser;
    }
    
    public static ISortParser getSortParser(){
        if(null == sortParser){
            sortParser = new DefaultSortParser(); 
        }
        return sortParser;
    }
}
