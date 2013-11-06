package zhiken.common.sqlite;

import java.util.ArrayList;
import java.util.List;

public class SQLiteWhere {
	/**
	 * 查询参数
	 */
	private List<Param> params;

	/**
	 * 排序参数
	 */
	private List<Order> orders;

	/**
	 * 分页参数
	 */
	private Pager pager;

	public SQLiteWhere() {
		super();
	}

	public SQLiteWhere(String clumn, Object value) {
		addParam(new Param(clumn, value));
	}

	public SQLiteWhere(String clumn, String condi, Object value) {
		addParam(new Param(clumn, condi, value));
	}

	public SQLiteWhere(String logic, String clumn, String condi, Object value) {
		addParam(new Param(logic, clumn, condi, value));
	}

	public SQLiteWhere(Param param) {
		super();
		addParam(param);
	}

	public SQLiteWhere(List<Param> params) {
		super();
		setParams(params);
	}

	public SQLiteWhere(Param param, Order order) {
		super();
		addParam(param);
		addOrder(order);
	}

	public SQLiteWhere(List<Param> params, Order order) {
		super();
		setParams(params);
		addOrder(order);
	}

	public SQLiteWhere(Param param, Order order, Pager pager) {
		super();
		addParam(param);
		addOrder(order);
		setPager(pager);
	}

	public SQLiteWhere(List<Param> params, Order order, Pager pager) {
		super();
		setParams(params);
		addOrder(order);
		setPager(pager);
	}

	public void addParam(Param param) {
		if (params == null) {
			params = new ArrayList<Param>();
		}
		params.add(param);
	}

	public void addOrder(Order order) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		orders.add(order);
	}

	public void setPager(int pageSize, int pageIndex) {
		if (pager == null) {
			pager = new Pager();
		}
		pager.setPageSize(pageSize);
		pager.setPageIndex(pageIndex);
	}

	public void setParam(Param param) {
		params = new ArrayList<Param>();
		params.add(param);
	}

	public void setOrder(Order order) {
		orders = new ArrayList<Order>();
		orders.add(order);
	}

	protected void setParams(List<Param> params) {
		this.params = params;
	}

	protected void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	protected void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getWhereClause() {
		if (params != null) {
			StringBuilder builder = new StringBuilder();
			Param param;
			int size = params.size();
			for (int i = 0; i < size; i++) {
				param = params.get(i);
				if (i > 0) {
					builder.append(param.getAnd());
				}
				builder.append("(");
				builder.append(param.getKey());
				builder.append(param.getCdt());
				builder.append("?)");
			}
			return builder.toString();
		}
		return null;
	}

	public String[] getWhereArgs() {
		if (params != null) {
			String[] parameters = new String[params.size()];
			for (int i = 0; i < parameters.length; i++) {
				parameters[i] = params.get(i).getVal();
			}
			return parameters;
		}
		return null;
	}

	// whereClause, String[] whereArgs
	public String getOrderBy() {
		if (orders != null) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < orders.size(); i++) {
				builder.append(orders.get(i));
			}
			return builder.toString();
		}
		return null;
	}

	public String getGroupBy() {
		return null;
	}

	// String groupBy, String having
	public String getHaving() {
		return null;
	}

	public String getLimit() {
		if (this.pager != null) {
			return this.pager.getAndroidSqliteLimit();
		}
		return null;
	}
}

// public SQLiteWhere(List<Param> params, List<Order> orders, Pager pager) {
// super();
// this.params = params;
// this.orders = orders;
// this.pager = pager;
// }

// private void getWhere(List<Param> params) {
// if (params != null) {
// this.parameters = new String[params.size()];
// Param param;
// for (int i = 0; i < this.parameters.length; i++) {
// param = params.get(i);
// if (i > 0) {
// this.expression.append(param.getAnd());
// }
// this.expression.append("(");
// this.expression.append(param.getKey());
// this.expression.append(param.getCdt());
// this.expression.append("?)");
// this.parameters[i] = param.getVal();
// }
// }
// }

// private StringBuilder builder;
// private String[] parameters;