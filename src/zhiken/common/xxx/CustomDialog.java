package zhiken.common.xxx;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class CustomDialog {
	public static AlertDialog showAlert(Context context, int titleResId,
			int messageResId, int buttonResId,
			DialogInterface.OnClickListener listener) {
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(titleResId)// ���ñ���
				.setMessage(messageResId)// ��������
				.setPositiveButton(buttonResId, listener)// ����ȷ����ť
				.create();
		dialog.show();
		return dialog;
	}

	public static AlertDialog showAlert(Context context, int titleResId,
			int messageResId, int positiveResId,
			DialogInterface.OnClickListener positiveListener,
			int negativeResId, DialogInterface.OnClickListener negativeListener) {
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(titleResId)// ���ñ���
				.setMessage(messageResId)// ��������
				.setPositiveButton(positiveResId, positiveListener)// ������ť
				.setNegativeButton(negativeResId, negativeListener)// �����Ұ�ť
				.create();
		dialog.show();
		return dialog;
	}

	public static AlertDialog showCustomAlert(Context context, int titleResId,
			View contentView, int buttonResId,
			DialogInterface.OnClickListener listener) {
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(titleResId)// ���ñ���
				.setView(contentView)// ��������
				.setPositiveButton(buttonResId, listener)// ����ȷ����ť
				.create();
		dialog.show();
		return dialog;
	}
	// AlertDialog.Builder builder = new AlertDialog.Builder(context);
	// builder.setTitle(R.string.dialog_nonet_title);
	// builder.setIcon(R.drawable.ic_dialog_alert_holo_light);
	// builder.setMessage(R.string.dialog_nonet_message);
	// Builder builder = new AlertDialog.Builder(this);
	// builder.setTitle(R.string.dialog_nonet_title);
	// builder.setIcon(R.drawable.ic_dialog_alert_holo_light);
	// builder.setMessage(R.string.dialog_nonet_message);
	// builder.setNegativeButton(R.string.dialog_nonet_negative,
	// new DialogInterface.OnClickListener() {
	//
	// public void onClick(DialogInterface dialog, int which) {
	// Intent intent = new Intent(Settings.ACTION_SETTINGS);//
	// Settings.ACTION_WIRELESS_SETTINGS
	// HomeActivity.this.startActivityForResult(intent,
	// ACTIVITY_RESULT_NETWORK);
	//
	// }
	// });
	// builder.setPositiveButton(R.string.dialog_nonet_positive,
	// new DialogInterface.OnClickListener() {
	//
	// public void onClick(DialogInterface dialog, int which) {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	// builder.show();
}
