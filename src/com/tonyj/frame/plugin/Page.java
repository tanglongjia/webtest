package com.tonyj.frame.plugin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 
 * <b>本类是定义分页查询需要的属性和方法的类。
 * </b><br><br>
 * <em>
 * <b>功能：</b>定义分页查询需要的属性和方法。
 * 
 *
 */
public class Page {
    
    protected static final String OFFSET_NAME = "offset";
    
    protected static final String LIMIT_NAME = "limit";
    
    /** 默认每页记录数 */
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    private int pagesize; //每页显示记录数   
    
    private int page; //当前页码
    
    private long totalResult; //总记录数   
    
    private int from;
    
    private long to;
    
    /* 查询结果 */
    private List<?> resultList = Collections.emptyList();
    
    private Map<String,String> dictMap = new HashMap<String,String>();
    
    public Page() {
        this.pagesize = DEFAULT_PAGE_SIZE;
        this.page = 1;
    };
    
    public Page(int page, int pagesize) {
        setpagesize(pagesize);
        setPage(page);
    }
    
    public Page(int page, int pagesize, long totalResult) {
        setTotalResult(totalResult);
        setpagesize(pagesize);
        setPage(page);
    }
    /**
     * 
     * <b>获取分页偏移量，即起始记录
     * </b>
     * @return - 获取分页偏移量
     *
     */
    public long getOffset() {
        if(this.getPage() <= 1)
        {
            return 0;
        }
        return (getPage() - 1) * getpagesize();
    }
    
    public List<?> getResultList() {
        return resultList;
    }
    
    public void setResultList(List<?> resultList) {
        this.resultList = resultList;
    }
    /**
     * 
     * <b>获取总记录数
     * </b>
     * @return 总记录数
     *
     */
    public long getTotalResult() {
        return totalResult;
    }
    
    /**
     * 
     * <b>设置总记录数
     * </b>
     * <dd> 注意事项： totalResult<=0时,总记录数设置为0,页码设置为0；
     *             否则，当页码page的值大于 通过totalResult和
     *             pagesize计算得出的页码总数时，页码被设置为计算值。
     * @param totalResult - 总记录数
     *
     */
    public void setTotalResult(long totalResult) {
        if(totalResult <= 0)
        {
            this.totalResult = 0;
            this.page = 0;
        }
        else
        {
            this.totalResult = totalResult;
            if(this.page > getTotalPage())
            {
                page = (int) getTotalPage();
            }
        }
        
    }
    
    public int getFrom() {
        from = (getPage() - 1) * getpagesize() + 1;
        if(from <= 0){
            from = 0;
        }
        return from;
    }
    
    public long getTo() {
        to = getPage() * getpagesize();
        if(to > getTotalResult()){
            to = getTotalResult();
        }
        if(to <= 0){
            to = 0;
        }
        return to;
    }
    /**
     * 
     * <b> 获取总页码数量。
     * </b>
     * @return 总页码数量
     *
     */
    public long getTotalPage() {
        if(totalResult <= 0)
        {
            return 0;
        }
        long totalPage = totalResult / pagesize;
        if(totalResult % pagesize > 0)
        {
            totalPage++;
        }
        return totalPage;
    }
    
    public void setPage(int page) {
        if(page <= 0)
        {
            page = 0;
        }
        else if(this.totalResult > 0)
        {
            if(page > getTotalPage())
            {
                page = (int) getTotalPage();
            }
        }
        this.page = page;
    }
    
    public int getPage() {
        return page;
    }
    
    public int getpagesize() {
        return pagesize;
    }
    
    public void setpagesize(int pagesize) {
        if(pagesize <= 0)
        {
            this.pagesize = DEFAULT_PAGE_SIZE;
        }
        else
        {
            this.pagesize = pagesize;
        }
    }
    
    public boolean hasPre() {
        return getPage() > 1;
    }
    
    public boolean hasNext() {
        return getPage() < getTotalPage();
    }
    @Override
	public String toString(){
        return new StringBuilder().append("【page=")
        .append(this.getPage()).append("】【pagesize=")
        .append(this.getpagesize()).append("】【resultList.size=")
        .append(this.getResultList().size()).append("】【totalPage=")
        .append(this.getTotalPage())
        .append("】【totoalResult=")
        .append(this.getTotalResult())
        .append("】").toString();
    }
    
    /*-************************* 排序 ********************************/
    private List<SortOrder> orderClause = null;
    
    public void setOrderClause(String param){
        orderClause = SortParserFactory.getSortParser().parseToClause(param);
    }
    
    /**
     * 
     * <b>添加排序条件
     * </b>
     * @param sort - 
     *
     */
    public void addOrderClause(SortOrder sort){
        if(null != sort){
            return;
        }
        
        if(null == orderClause){
            orderClause = new ArrayList<SortOrder>();
        }
        orderClause.add(sort);
    }
    
    /**
     * 
     * <b>添加排序字段和排序顺序
     * </b>
     * @param sortColumn - 排序字段
     * @param order - 排序顺序
     *
     */
    public void addOrderClause(String sortColumn, String order){
        if(StringUtils.isEmpty(sortColumn)){
            return;
        }
        
        if(null == orderClause){
            orderClause = new ArrayList<SortOrder>();
        }
        orderClause.add(new SortOrder(sortColumn,order));
    }
    
    /**
     * 
     * <b> 拼接排序子句
     * </b>
     * @return 排序子句，形如：id desc, name asc
     *
     */
    public String getOrderClause(){
        if(CollectionUtils.isEmpty(orderClause)){
            return "";
        }
        StringBuffer clause = new StringBuffer();
        int i = 0;
        for(SortOrder cluase : orderClause){
            if(i > 0){
                clause.append(",");
            }
            
            clause.append(cluase.toCaluse());
            i++;
        }
        
        return clause.toString();
    }

	public Map<String,String> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<String,String> dictMap) {
		this.dictMap = dictMap;
	}
}
