//package zhiken.common.ex;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.lang.Thread.UncaughtExceptionHandler;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.TreeSet;
//
//import android.content.Context;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.util.Log;
//
//
///**
// * 
// * @author zg_hu@calinks.com.cn
// * 
// * @version 1.0
// * 
// *          crash log
// * 
// */
//// @SuppressLint("SimpleDateFormat")
//public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
//
//	private static final String TAG = MyUncaughtExceptionHandler.class.getSimpleName();
//
//	/**
//	 * 是否开启日志输出在Debug状态下开 在Release状态下关闭以提示程序性能
//	 **/
//	public static final boolean DEBUG = true;// true|false
//
//	private Thread.UncaughtExceptionHandler mDefaultHandler;
//	/** CrashHandler实例 */
//	private static MyUncaughtExceptionHandler INSTANCE;
//	/** 程序的Context对象 */
//	private Context mContext;
//
//	/** 使用Properties来保存设备的信息和错误堆栈信息 */
//	// private Properties mDeviceCrashInfo = new Properties();
//	// private static final String VERSION_NAME = "versionName";
//	private String ver_name = "unknow";
//	// private static final String VERSION_CODE = "versionCode";
//	private String ver_code = "-1";
//	// private static final String STACK_TRACE = "STACK_TRACE";
//	private String content = "unknow";
//	/** 错误报告文件的扩展名 */
//	private static final String CRASH_REPORTER_EXTENSION = ".log";
//
//	private MyUncaughtExceptionHandler() {
//
//	}
//
//	public static MyUncaughtExceptionHandler getInstance() {
//		if (INSTANCE == null) {
//			INSTANCE = new MyUncaughtExceptionHandler();
//		}
//
//		return INSTANCE;
//	}
//
//	public void init(Context context) {
//		mContext = context;
//		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//		Thread.setDefaultUncaughtExceptionHandler(this);
//	}
//
//	@Override
//	public void uncaughtException(Thread thread, Throwable ex) {
//		// TODO Auto-generated method stub
//		if (!handleException(ex) && mDefaultHandler != null) {
//			// 如果用户没有处理则让系统默认的异常处理器来处理
//			mDefaultHandler.uncaughtException(thread, ex);
//		} else {
//			// Sleep一会后结束程序
//			// try {
//			// Thread.sleep(3000);
//			// } catch (InterruptedException e) {
//			// Log.e(TAG, "Error : ", e);
//			// }
//			// android.os.Process.killProcess(android.os.Process.myPid());
//			// System.exit(10);
//		}
//
//	}
//
//	/**
//	 * 
//	 * @return true:如果处理了该异常信息;否则返回false
//	 */
//	private boolean handleException(Throwable ex) {
//		if (ex == null) {
//			return true;
//		}
//		// final String msg = ex.getLocalizedMessage();
//		// 使用Toast来显示异常信息
//		// new Thread() {
//		// @Override
//		// public void run() {
//		// Looper.prepare();
//		// Toast.makeText(mContext, "程序出错啦" + msg, Toast.LENGTH_LONG)
//		// .show();
//		// Looper.loop();
//		// }
//		//
//		// }.start();
//		// 收集设备信息
//		collectCrashDeviceInfo(mContext);
//		// 保存错误报告文件
//		String crashFileName = saveCrashInfoToFile(ex);
//
//		Log.e(TAG, "crashFileName = " + crashFileName);
//
//		// 发送错误报告到服务器
//		// sendCrashReportsToServer(mContext);
//		return true;
//	}
//
//	/**
//	 * 
//	 * @param ex
//	 * @return
//	 */
//	private String saveCrashInfoToFile(Throwable ex) {
//		Writer info = new StringWriter();
//		PrintWriter printWriter = new PrintWriter(info);
//		ex.printStackTrace(printWriter);
//
//		Throwable cause = ex.getCause();
//		while (cause != null) {
//			cause.printStackTrace(printWriter);
//			cause = cause.getCause();
//		}
//
//		content = info.toString();
//		printWriter.close();
//
//		// long timestamp = System.currentTimeMillis();
//		Calendar calendar = Calendar.getInstance();
//		String tempTime = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss")
//				.format(calendar.getTime());
//
//		checkDir();
//		File logFile = new File(JocParameter.LOG_PATH + tempTime
//				+ CRASH_REPORTER_EXTENSION);
//
//		PrintWriter pw = null;
//		try {
//			if (!logFile.exists()) {
//				logFile.createNewFile();
//			}
//
//			pw = new PrintWriter(new FileWriter(logFile), true);
//
//			pw.println(ver_name);
//			pw.println(ver_code);
//			pw.println(content);
//
//			pw.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return logFile.getAbsolutePath();
//	}
//
//	private void checkDir() {
//		File file = new File(JocParameter.getSDPath() + JocParameter.JOCPATH
//				+ JocParameter.LOG);
//		// System.out.println("dir path = " + file.getAbsolutePath());
//		if (!file.isDirectory()) {
//			file.mkdirs();
//		}
//	}
//
//	/**
//	 * 保存错误信息到文件中
//	 * 
//	 * @param ex
//	 * @return
//	 */
//	// private String saveCrashInfoToFile(Throwable ex) {
//	// Writer info = new StringWriter();
//	// PrintWriter printWriter = new PrintWriter(info);
//	// ex.printStackTrace(printWriter);
//	//
//	// Throwable cause = ex.getCause();
//	// while (cause != null) {
//	// cause.printStackTrace(printWriter);
//	// cause = cause.getCause();
//	// }
//	//
//	// String result = info.toString();
//	// printWriter.close();
//	// mDeviceCrashInfo.put(STACK_TRACE, result);
//	//
//	// try {
//	// long timestamp = System.currentTimeMillis();
//	// String fileName = "crash-" + timestamp + CRASH_REPORTER_EXTENSION;
//	// FileOutputStream trace = mContext.openFileOutput(fileName,
//	// Context.MODE_PRIVATE);
//	// mDeviceCrashInfo.store(trace, "");
//	// trace.flush();
//	// trace.close();
//	// return fileName;
//	// } catch (Exception e) {
//	// Log.e(TAG, "an error occured while writing report file...", e);
//	// }
//	// return null;
//	// }
//
//	/**
//	 * 收集程序崩溃的设备信息
//	 * 
//	 * @param ctx
//	 */
//	private void collectCrashDeviceInfo(Context ctx) {
//		try {
//			PackageManager pm = ctx.getPackageManager();
//			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
//					PackageManager.GET_ACTIVITIES);
//			if (pi != null) {
//				// mDeviceCrashInfo.put(VERSION_NAME,
//				// pi.versionName == null ? "not set" : pi.versionName);
//				ver_name = pi.versionName == null ? "ver_name = unknow"
//						: "ver_name = " + pi.versionName;
//				// mDeviceCrashInfo.put(VERSION_CODE, pi.versionCode);
//				ver_code = pi.versionCode == 0 ? "ver_code = -1"
//						: "ver_code = " + pi.versionCode;
//			}
//		} catch (NameNotFoundException e) {
//			Log.e(TAG, "Error while collect package info", e);
//		}
//		// 使用反射来收集设备信息在Build类中包含各种设备信息,
//		// 例如: 系统版本号设备生产名等帮助调试程序的有用信息
//		// Field[] fields = Build.class.getDeclaredFields();
//		// for (Field field : fields) {
//		// try {
//		// field.setAccessible(true);
//		// mDeviceCrashInfo.put(field.getName(), field.get(null));
//		// if (DEBUG) {
//		// Log.d(TAG, field.getName() + " : " + field.get(null));
//		// }
//		// } catch (Exception e) {
//		// Log.e(TAG, "Error while collect crash info", e);
//		// }
//		//
//		// }
//
//	}
//
//	/**
//	 * 把错误报告发送给服务包含新产生的和以前没发送的.
//	 * 
//	 * @param ctx
//	 */
//	private void sendCrashReportsToServer(Context ctx) {
//		String[] crFiles = getCrashReportFiles(ctx);
//		if (crFiles != null && crFiles.length > 0) {
//			TreeSet<String> sortedFiles = new TreeSet<String>();
//			sortedFiles.addAll(Arrays.asList(crFiles));
//
//			for (String fileName : sortedFiles) {
//				File cr = new File(ctx.getFilesDir(), fileName);
//				postReport(cr);
//				cr.delete();// 删除已发送的报告
//			}
//		}
//	}
//
//	private void postReport(File file) {
//		// TODO 使用HTTP Post 发送错误报告到服务
//	}
//
//	/**
//	 * 获取错误报告文件
//	 * 
//	 * @param ctx
//	 * @return
//	 */
//	private String[] getCrashReportFiles(Context ctx) {
//		File filesDir = ctx.getFilesDir();
//		FilenameFilter filter = new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				return name.endsWith(CRASH_REPORTER_EXTENSION);
//			}
//		};
//		return filesDir.list(filter);
//	}
//}
