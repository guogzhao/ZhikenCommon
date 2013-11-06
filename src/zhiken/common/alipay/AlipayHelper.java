package zhiken.common.alipay;


public class AlipayHelper {

	// /**
	// * get the selected order info for pay. 获取商品订单信息
	// *
	// * @param position
	// * 商品在列表中的位置
	// * @return
	// */
	// public static String getOrderInfo(String subject, String body, int price)
	// {
	// String strOrderInfo = "partner=" + "\"" + PartnerConfig.PARTNER + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "seller=" + "\"" + PartnerConfig.SELLER + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "out_trade_no=" + "\"" + getOutTradeNo() + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "subject=" + "\"" + subject + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "body=" + "\"" + body + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "total_fee=" + "\"" + price + "\"";
	// strOrderInfo += "&";
	// strOrderInfo += "notify_url=" + "\""
	// + "http://notify.java.jpxx.org/index.jsp" + "\"";
	// return strOrderInfo;
	// }

	// /**
	// * get the out_trade_no for an order. 获取外部订单号
	// *
	// * @return
	// */
	// public static String getOutTradeNo() {
	// SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
	// Locale.getDefault());
	// Date date = new Date();
	// String strKey = format.format(date);
	//
	// java.util.Random r = new java.util.Random();
	// strKey = strKey + r.nextInt();
	// strKey = strKey.substring(0, 15);
	// return strKey;
	// // 0701181738-1179
	// }

	// /**
	// * 获XY取外部订单号
	// *
	// * @param id
	// * @return
	// */
	// public static String getXyOutTradeNo(long orderId, long orderTimeCreate)
	// {
	// // SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss",
	// // Locale.getDefault());
	// // Date date = new Date();
	// // String strDatetime = format.format(date);
	// DateTime dtOrderTimeCreate = DateTime.from(orderTimeCreate);
	//
	// String strDatetime = dtOrderTimeCreate.toString("yyyyMMddHHmmss") +
	// orderTimeCreate % 1000;
	// String strPadOrder = StringFormat.padLeft("" + orderId, 12, '0');
	//
	// return strDatetime + strPadOrder;
	// // 0701181738-1179
	// }

	//
	//
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param signType
	 *            签名方式
	 * @param content
	 *            待签名订单信息
	 * @return
	 */
	public static String sign(String rsa_private, String signType, String content) {
		return Rsa.sign(content, rsa_private);
	}

	/**
	 * get the sign goodsTypeId we use. 获取签名方式
	 * 
	 * @return
	 */
	public static String getSignType() {
		String getSignType = "sign_type=" + "\"" + "RSA" + "\"";
		return getSignType;
	}

	/**
	 * get the char set we use. 获取字符集
	 * 
	 * @return
	 */
	public static String getCharset() {
		String charset = "charset=" + "\"" + "utf-8" + "\"";
		return charset;
	}

	/**
	 * check some info.the partner,seller etc. 检测配置信息
	 * partnerid商户id，seller收款帐号不能为空
	 * 
	 * @return
	 */
	public static boolean checkInfo(String partner, String seller) {
		if (partner == null || partner.length() <= 0 || seller == null || seller.length() <= 0)
			return false;

		return true;
	}

}
