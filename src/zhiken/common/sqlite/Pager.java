package zhiken.common.sqlite;

public class Pager {
	public Pager() {
		super();
	}

	public Pager(int page_size, int page_index) {
		super();
		this.page_size = page_size;
		this.page_index = page_index;
	}

	public Pager(int item_total, int page_size, int page_index) {
		super();
		this.item_total = item_total;
		this.page_size = page_size;
		this.page_index = page_index;
	}

	// {"item_count":1,"page_count":1,"page_index":0,"page_size":1,"list_size":1}
	/**
	 * 查询返回的总记录数
	 */
	private int item_total;
	/**
	 * 当前分页每页的大小
	 */
	private int page_size;
	/**
	 * 当前页索引
	 */
	private int page_index;

	/**
	 * 返回查询返回的总记录数
	 */
	public int getItemTotal() {
		return item_total;
	}

	/**
	 * 返回查询返回的总页数
	 */
	public int getPageCount() {
		return item_total / page_size + (item_total % page_size > 0 ? 1 : 0);
	}

	/**
	 * 返回当前分页每页的大小
	 */
	public int getPageSize() {
		return page_size;
	}

	/**
	 * 设置当前分页每页的大小
	 */
	public void setPageSize(int page_size) {
		this.page_size = page_size;
	}

	/**
	 * 返回当前分页的页码（从1开始）
	 * 
	 * @return
	 */
	public int getPageNumber() {
		return page_index + 1;
	}

	/**
	 * 设置当前分页的页码（从1开始）
	 * 
	 */
	public void setPageNumber(int pageNumber) {
		this.page_index = pageNumber - 1;
	}

	/**
	 * 返回当前分页的索引（从0开始）
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return page_index;
	}

	/**
	 * 设置当前分页的索引（从0开始）
	 * 
	 */
	public void setPageIndex(int page_index) {
		this.page_index = page_index;
	}

	/**
	 * 返回当前页的记录数
	 */
	public int getItemCount() {
		return Math.min(page_size, item_total - page_size * page_index);
	}

	/**
	 * 返回Android Sqlite limit 条件字符串
	 * 
	 * @return
	 */
	public String getAndroidSqliteLimit() {
		return page_size * page_index + "," + page_size;
	}
}
