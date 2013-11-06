//package zhiken.common.sqlite;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class __SQLiteHelper extends SQLiteOpenHelper {
//	private static __SQLiteHelper sSQLiteHelper;
//	private static SQLiteConfig sSQLiteConfig;
//
//	public static synchronized __SQLiteHelper getInstance(Context ctx, SQLiteConfig cfg) {
//		if (sSQLiteHelper == null) {
//			sSQLiteHelper = new __SQLiteHelper(ctx);
//		}
//		if (sSQLiteConfig == null) {
//			sSQLiteConfig = cfg;
//			// try {
//			// sSQLiteConfig = (SQLiteConfig)
//			// Class.forName(cls.getName()).newInstance();
//			// } catch (InstantiationException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// } catch (IllegalAccessException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// } catch (ClassNotFoundException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// }
//		}
//		return sSQLiteHelper;
//	}
//
//	private __SQLiteHelper(Context context) {
//		super(context, sSQLiteConfig.getName(), null, sSQLiteConfig.getVersion());// CursorFactory
//		// factory
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		for (int i = 0; i < sSQLiteConfig.getTables().size(); i++) {
//			db.execSQL(sSQLiteConfig.getTables().get(i));
//		}
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE IF EXISTS " + sSQLiteConfig.getName());// ggz//?
//		onCreate(db);
//	}
//}
