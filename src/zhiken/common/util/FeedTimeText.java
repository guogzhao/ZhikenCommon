package zhiken.common.util;

public class FeedTimeText {

	public static String getText(long timeMillis) {

		DateTime now = DateTime.now();

		long timetiff = now.toTimeMillis() - timeMillis;

		if (timetiff < 60 * 1000) {
			return "刚刚";
		}

		if (timetiff < 60 * 60 * 1000) {
			return timetiff / (60 * 1000) + "分钟前";
		}

		DateTime pub = DateTime.from(timeMillis);

		if (pub.getDay() == now.getDay()) {
			return "今天" + pub.toString("HH:mm");
		}

		if (pub.getDay() == now.getDay() - 1) {
			return "昨天" + pub.toString("HH:mm");
		}

		if (pub.getYear() == now.getYear()) {
			return pub.toString("MM月dd日 HH:mm");
		}

		return pub.toString("yyyy-MM-dd HH:mm");
	}
}

// if (pub.getMonth() == now.getMonth() - 1) {
// return pub.toString("dd HH:mm");
// }
// if (timetiff < 30 * 60 * 1000) {
// return timetiff / (60 * 1000) + "分钟前";
// }
// if (timetiff == 30 * 60 * 1000) {
// return "半个小时前";
// }