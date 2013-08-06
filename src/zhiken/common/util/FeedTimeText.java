package zhiken.common.util;

public class FeedTimeText {

	public static String getText(long timeMillis) {

		DateTime now = DateTime.now();
		
		long timetiff = now.toTimeMillis() - timeMillis;

		if (timetiff < 60 * 1000) {
			return "�ո�";
		}
		if (timetiff < 30 * 60 * 1000) {
			return timetiff / (60 * 1000) + "����ǰ";
		}
		if (timetiff == 30 * 60 * 1000) {
			return "���Сʱǰ";
		}
		if (timetiff == 60 * 60 * 1000) {
			return timetiff / (60 * 1000) + "����ǰ";
		}

		DateTime pub = DateTime.from(timeMillis);

		if (pub.getDay() == now.getDay()) {
			return "����" + pub.toString("HH:mm");
		}

		if (pub.getDay() == now.getDay() - 1) {
			return "����" + pub.toString("HH:mm");
		}
		
		return pub.toString("yyyy-MM-dd HH:mm");
	}
}