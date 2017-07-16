package com.quincy.pojo;

import java.util.List;

/**
 * 分页javaBean
 * @author quincy
 *另外一种样式默认的显示10页数据
 */
public class Page {
	
	//数据库总的记录数
	private int totalRecord;
	
	//每一页显示的记录数据
	private int pageRecordNumber = 2;
	
	//总页数
	private int totalPageNumber;
	
	//上一页
	private int previousPage;
	
	//下一页
	private int nextPage;
	
	//页签从第几页开始
	private int startPageNumber;
	
	private int endPageNumber;
	
	//当前页数，默认的是从第一页开始
	private int currentPageNumber = 1;
	
	//页签的长度,如果设置为5条数据，有bug需要解决
	private int tabsLength = 10;
	
	private List<User> lists;
	
	public Page(int currentPageNumber,int totalRecord){
		this.currentPageNumber = currentPageNumber;
		this.totalRecord = totalRecord;
		getTotalPageNumber();
		initStartPageNumberAndEndPageNumber();
	}
	
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}


	public List<User> getLists(){
		return lists;
	}
	
	public void setLists(List<User> lists){
		this.lists = lists;
	}
	
	public int getPageRecordNumber() {
		return pageRecordNumber;
	}

	public void setPageRecordNumber(int pageRecordNumber) {
		this.pageRecordNumber = pageRecordNumber;
	}

	/**
	 * 计算开始页数
	 */
	/*
	public int getStartPageNumber(){
		if( ( currentPageNumber - pageDynamicsLength ) <= 0 ){
			startPageNumber = 1;
		}else{
			startPageNumber = currentPageNumber - pageDynamicsLength;
		}
		
		return startPageNumber;
	}
	
	*/
	
	/**
	 * 计算结束页数
	 */
	/*
	public int getEndPageNumber(){
		if( ( currentPageNumber + pageDynamicsLength ) >= totalPageNumber ){
			endPageNumber = 1;
		}else{
			endPageNumber = currentPageNumber + pageDynamicsLength;
		}
		return endPageNumber;
	}
	*/
	/**
	 * 模拟百度分页算法，计算开始和结束页
	 * @return
	 */
	private void initStartPageNumberAndEndPageNumber(){
		if( currentPageNumber <= ( tabsLength / 2 ) + 1){
			startPageNumber = 1;
			endPageNumber = tabsLength;
		}
		if(currentPageNumber > ( tabsLength / 2 ) + 1){
			startPageNumber = currentPageNumber - ( tabsLength / 2 );
			endPageNumber = currentPageNumber + ( tabsLength / 2 ) - 1;
			//endPageNumber = startPageNumber + ( tabsLength - 1 );
			//endPageNumber = startPageNumber + ( tabsLength / 2 ) - 1;
		}
		System.out.println(startPageNumber + " ------- " +endPageNumber + "---" + totalPageNumber);
		verify();
	}
	
	public void verify(){
	    if (endPageNumber >= totalPageNumber) {
	    	//startPageNumber = startPageNumber - ( endPageNumber - totalPageNumber  );
		    endPageNumber = totalPageNumber;
		}
		if (endPageNumber <= tabsLength) {// 当不足totalPageNumber数目时，要全部显示，所以startPageNumber要始终置为1
			  startPageNumber = 1;
			 // endPageNumber = totalPageNumber;
		}
		
		System.out.println(startPageNumber + " ====== " +endPageNumber);
		
	}
	
	
	/**
	 * 计算总页数
	 * 改方法不需要提供set方法，因为总页数是计算出来的，不能人为的进行设置
	 */
	private void getTotalPageNumber(){
		int total = totalRecord / pageRecordNumber;
		System.out.println("total  " + total);
		totalPageNumber =  (totalRecord % pageRecordNumber) == 0 ? (totalRecord / pageRecordNumber) : (totalRecord / pageRecordNumber) + 1;
	}

	//计算上一页
	//当前的页数减去 1 如果小于0，设置为第一页，
	public int getPreviousPage() {
		return currentPageNumber - 1 <= 0 ? 1 : currentPageNumber - 1;
	}
	//计算下一个
	//如果大于总的页数，设置为最后一个的页数
	public int getNextPage() {
		return currentPageNumber + 1 > totalPageNumber ? totalPageNumber : currentPageNumber + 1;
	}
	
	
	
	//必须提供，jsp页面才能访问
	public int getStartPageNumber() {
		return startPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}
	

}
