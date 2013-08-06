package zhiken.common.xxx;

//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;


public class NotifyHelper {
//	public static final int Notify_ID = 0X1681680;
//
//	public static void notify(Context context, String title, String content) {
//		notify(context, MsgsActivity.class, title, content);// 默认通知到消息中�?
//	}
//
//	public static void notify(Context ctx, Class<?> cls, String title,
//			String content) {
//
//		Notification notification = new Notification();
//		{
//			notification.icon = R.drawable.notify;
//			notification.tickerText = content;
//			// 通知时屏幕发�?
//			notification.defaults = Notification.DEFAULT_LIGHTS
//					| Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;// 闪光、声音�?震动
//			// 点击通知时转移内�?
//			Intent intent = new Intent(ctx, cls);
//			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//			// 主要是设置点击�?知时显示内容的类
//			PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0,
//					intent, 0);
//			notification.setLatestEventInfo(ctx, title, content,
//					pendingIntent);
//		}
//
//		getNotificationManager(ctx).notify(Notify_ID, notification);
//	}
//
//	private static NotificationManager getNotificationManager(Context context) {
//		return ((NotificationManager) context
//				.getSystemService(Service.NOTIFICATION_SERVICE));
//	}
//
//	public static void cancel(Context context) {
//		getNotificationManager(context).cancel(Notify_ID);
//	}
}
