package zhiken.common.alipay;

import android.util.Log;

public class AlipayOrderInfo {
	static String tag = AlipayOrderInfo.class.getName();

	private String subject;
	private String body;
	private int total_fee;
	private String out_trade_no;

	private String _partner;
	private String _seller;

	public AlipayOrderInfo(String partner, String seller) {
		super();
		this._partner = partner;
		this._seller = seller;
	}

	// public AlipayOrderInfo(String subject, String body, int price) {
	// super();
	// init(subject, body, price);
	// }

	// private void init(String subject, String body, int price) {
	// this.subject = subject;
	// this.body = body;
	// this.total_fee = price;
	// // this.out_trade_no = AlipayHelper.getOutTradeNo();
	// }

	/**
	 * 消费名称
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 消费名称
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 订单描述
	 * 
	 * @return
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 订单描述
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 应付金额
	 * 
	 * @param body
	 */
	public int getTotalfee() {
		return total_fee;
	}

	/**
	 * 应付金额
	 * 
	 * @param price
	 */
	public void setTotalfee(int price) {
		this.total_fee = price;
	}

	/**
	 * 商户订单号
	 * 
	 * @return
	 */
	public String getOutTradeNo() {
		return out_trade_no;
	}

	/**
	 * 商户订单号
	 * 
	 * @param out_trade_no
	 */
	public void setOutTradeNo(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/**
	 * get the selected order info for pay. 获取商品订单信息
	 * 
	 * @return
	 */
	public String toString() {
		if (!valid()) {
			Log.e(tag, "object field is null!");
		}
		String strOrderInfo = "partner=" + "\"" + _partner + "\"";
		strOrderInfo += "&";
		strOrderInfo += "seller=" + "\"" + _seller + "\"";
		strOrderInfo += "&";
		strOrderInfo += "out_trade_no=" + "\"" + out_trade_no + "\"";
		strOrderInfo += "&";
		strOrderInfo += "subject=" + "\"" + subject + "\"";
		strOrderInfo += "&";
		strOrderInfo += "body=" + "\"" + body + "\"";
		strOrderInfo += "&";
		strOrderInfo += "total_fee=" + "\"" + total_fee + "\"";
		strOrderInfo += "&";
		strOrderInfo += "notify_url=" + "\"" + "http://notify.java.jpxx.org/index.jsp" + "\"";
		return strOrderInfo;
	}

	private boolean valid() {
		if (out_trade_no == null)
			return false;
		if (subject == null)
			return false;
		if (body == null)
			return false;
		if (total_fee == 0)
			return false;
		return true;
	}
	// private String partner = PartnerConfig.PARTNER;
	// private String seller = PartnerConfig.SELLER;
}
