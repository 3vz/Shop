package com.keratin.model;

import java.util.List;
/**
 * paging components.
 */
public class PageModel<T> {
	private int totalRecords;
	private List<T> list;
	private int pageNo;
	private int pageSize;
	/**
	 * get first page.
	 * @return first page.
	 */
	public int getTopPageNo() {
		return 1;
	}
	/**
	 * get previous page.
	 * @return previous page.
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo -1;
	}
	/**
	 * get next page.
	 * @return next page.
	 */
	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		}
		return pageNo + 1;
	}
	/**
	 * get last page.
	 * @return last page.
	 */
	public int getBottomPageNo() {
		return getTotalPages() == 0 ? 1 : getTotalPages();
	}
	/**
	 * get total page count.
	 * @return page count.
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
