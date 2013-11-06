package zhiken.common.sqlite;

/**
 * @create 2013-09-06 13:42
 * @author guogzhao
 * 
 */
public class Order {
	/**
	 * 用 ACS 表示按正序排序(即：从小到大排序)
	 */
	public static final String ASCENDING = "ASC";

	/**
	 * 用 DESC 表示按倒序排序(即：从大到小排序)
	 */
	public static final String DESCENDING = "DESC";

	private String key;
	private boolean asc;

	public Order(String key, boolean asc) {
		super();
		init(key, asc);
	}

	public static Order createAscending(String key) {
		return new Order(key, true);
	}

	public static Order createDescending(String key) {
		return new Order(key, false);
	}

	private void init(String key, boolean asc) {
		this.key = key;
		this.asc = asc;
	}

	public String toString() {
		return key + (asc ? " ASC" : " DESC");
	}
}
