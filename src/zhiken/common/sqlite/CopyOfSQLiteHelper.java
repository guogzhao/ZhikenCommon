//package zhiken.common.sqlite;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class CopyOfSQLiteHelper extends SQLiteOpenHelper {
//	private static CopyOfSQLiteHelper sSQLiteHelper;
//	private static SQLiteConfig sSQLiteConfig;
//
//	public static synchronized CopyOfSQLiteHelper getInstance(Context ctx, SQLiteConfig cfg) {
//		if (sSQLiteHelper == null) {
//			sSQLiteHelper = new CopyOfSQLiteHelper(ctx);
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
//	private CopyOfSQLiteHelper(Context context) {
//		super(context, sSQLiteConfig.$name(), null, sSQLiteConfig.$version());// CursorFactory
//																				// factory
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		for (int i = 0; i < sSQLiteConfig.$tables().size(); i++) {
//			db.execSQL(sSQLiteConfig.$tables().get(i));
//		}
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE IF EXISTS " + sSQLiteConfig.$name());// ggz//?
//		onCreate(db);
//	}
//}
