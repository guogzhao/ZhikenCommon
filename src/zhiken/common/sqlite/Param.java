package zhiken.common.sqlite;

public class Param {

	public Param(String key, Object val) {
		setKey(key);
		setVal(val);
	}

	public Param(String key, String cdt, Object val) {
		setKey(key);
		setCdt(cdt);
		setVal(val);
	}

	public Param(String and, String key, String cdt, Object val) {
		setAnd(and);
		setKey(key);
		setCdt(cdt);
		setVal(val);
	}

	private String and = "AND";

	public String getAnd() {
		return " " + and.trim() + " ";
	}

	public void setAnd(String and) {
		this.and = and;
	}

	private String key;

	private String cdt = "=";

	private String val;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCdt() {
		return cdt;
	}

	public void setCdt(String cdt) {
		this.cdt = cdt;
	}

	public String getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val.toString();
	}
}
