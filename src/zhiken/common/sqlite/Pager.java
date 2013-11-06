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
	 * ��ѯ���ص��ܼ�¼��
	 */
	private int item_total;
	/**
	 * ��ǰ��ҳÿҳ�Ĵ�С
	 */
	private int page_size;
	/**
	 * ��ǰҳ����
	 */
	private int page_index;

	/**
	 * ���ز�ѯ���ص��ܼ�¼��
	 */
	public int getItemTotal() {
		return item_total;
	}

	/**
	 * ���ز�ѯ���ص���ҳ��
	 */
	public int getPageCount() {
		return item_total / page_size + (item_total % page_size > 0 ? 1 : 0);
	}

	/**
	 * ���ص�ǰ��ҳÿҳ�Ĵ�С
	 */
	public int getPageSize() {
		return page_size;
	}

	/**
	 * ���õ�ǰ��ҳÿҳ�Ĵ�С
	 */
	public void setPageSize(int page_size) {
		this.page_size = page_size;
	}

	/**
	 * ���ص�ǰ��ҳ��ҳ�루��1��ʼ��
	 * 
	 * @return
	 */
	public int getPageNumber() {
		return page_index + 1;
	}

	/**
	 * ���õ�ǰ��ҳ��ҳ�루��1��ʼ��
	 * 
	 */
	public void setPageNumber(int pageNumber) {
		this.page_index = pageNumber - 1;
	}

	/**
	 * ���ص�ǰ��ҳ����������0��ʼ��
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return page_index;
	}

	/**
	 * ���õ�ǰ��ҳ����������0��ʼ��
	 * 
	 */
	public void setPageIndex(int page_index) {
		this.page_index = page_index;
	}

	/**
	 * ���ص�ǰҳ�ļ�¼��
	 */
	public int getItemCount() {
		return Math.min(page_size, item_total - page_size * page_index);
	}

	/**
	 * ����Android Sqlite limit �����ַ���
	 * 
	 * @return
	 */
	public String getAndroidSqliteLimit() {
		return page_size * page_index + "," + page_size;
	}
}
