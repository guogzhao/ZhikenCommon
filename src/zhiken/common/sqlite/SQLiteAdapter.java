package zhiken.common.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @create 2013-08-26
 * @update 2013-09-06
 * @author guogzhao
 * 
 *         ANDROID SQLite ����������
 * @param <Item>
 */
public abstract class SQLiteAdapter<Item> {
	protected Context mContext;
	protected SQLiteOpenHelper mHelper;

	public SQLiteAdapter(Context context) {
		mContext = context;
		// mHelper = SQLiteHelper.getInstance(context);
		mHelper = $helper();
	}

	protected abstract SQLiteOpenHelper $helper();

	/**
	 * ���ر���
	 * 
	 * @return
	 */
	protected abstract String $table();

	// protected abstract String $script();

	private SQLiteDatabase mReadableDatabase;
	private SQLiteDatabase mWritableDatabase;

	protected SQLiteDatabase openReadable() {
		return mReadableDatabase = mHelper.getReadableDatabase();
	}

	protected SQLiteDatabase openWritable() {
		return mWritableDatabase = mHelper.getWritableDatabase();
	}

	protected void closeReadable() {
//		if (mReadableDatabase != null) {
//			mReadableDatabase.close();
//			mReadableDatabase = null;
//		}
	}

	protected void closeWritable() {
//		if (mWritableDatabase != null) {
//			mWritableDatabase.close();
//			mWritableDatabase = null;
//		}
	}

	protected String[] toArray(List<String> lstClumns) {
		String[] clumns = new String[lstClumns.size()];
		for (int i = 0; i < clumns.length; i++) {
			clumns[i] = lstClumns.get(i);
		}
		return clumns;
	}

	/**
	 * �����е�����
	 * 
	 * @return
	 */
	protected abstract String[] $clumns();

	/**
	 * �����ֶκ�ֵ��ӳ�����
	 * 
	 * @return
	 */
	protected abstract ContentValues $values(Item item);

	/**
	 * �����ɵ�ǰ�α�����Ķ���
	 * 
	 * @return
	 */
	protected abstract Item $parse(Cursor cursor);

	protected String $nullColumnHack() {
		return null;
	}

	/**
	 * �ж϶����Ƿ����
	 * 
	 * @param sQLiteWhere
	 * @return
	 */
	public boolean exists(SQLiteWhere sQLiteWhere) {
		if (get(sQLiteWhere) != null)
			return true;
		return false;
	}

	/**
	 * �������������ĵ�һ����¼����
	 * 
	 * @param sQLiteWhere
	 * @return
	 */
	public Item get(SQLiteWhere sQLiteWhere) {
		Item item = null;
		{
			sQLiteWhere.setPager(1, 0);
			Cursor cursor = openReadable().query($table(), $clumns(), sQLiteWhere.getWhereClause(), sQLiteWhere.getWhereArgs(), sQLiteWhere.getGroupBy(), sQLiteWhere.getHaving(),
					sQLiteWhere.getOrderBy(), sQLiteWhere.getLimit());
			try {
				if (cursor != null) {
					if (cursor.moveToFirst())
						item = $parse(cursor);
					cursor.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeReadable();
			}

		}
		return item;
	}

	/**
	 * ����ָ���������ֶ�����ĵ�һ����¼����
	 * 
	 * @param sQLiteWhere
	 * @param order
	 * @return
	 */
	public Item max(SQLiteWhere sQLiteWhere, String order) {
		sQLiteWhere.addOrder(Order.createDescending(order));
		return get(sQLiteWhere);
	}

	/**
	 * ����ָ���ֶν���ĵ�һ����¼����
	 * 
	 * @param order
	 * @return
	 */
	public Item max(String order) {
		SQLiteWhere sQLiteWhere = new SQLiteWhere();
		sQLiteWhere.addOrder(Order.createDescending(order));
		return get(sQLiteWhere);
	}

	/**
	 * ����ָ���������ֶ�����ĵ�һ����¼����
	 * 
	 * @param sQLiteWhere
	 * @param order
	 * @return
	 */
	public Item min(SQLiteWhere sQLiteWhere, String order) {
		sQLiteWhere.addOrder(Order.createAscending(order));
		return get(sQLiteWhere);
	}

	/**
	 * ����ָ���ֶ�����ĵ�һ����¼����
	 * 
	 * @param order
	 * @return
	 */
	public Item min(String order) {
		SQLiteWhere sQLiteWhere = new SQLiteWhere();
		sQLiteWhere.addOrder(Order.createAscending(order));
		return get(sQLiteWhere);
	}

	/**
	 * �������������Ķ����б�
	 * 
	 * @param sQLiteWhere
	 * @return
	 */
	public List<Item> select(SQLiteWhere sQLiteWhere) {
		List<Item> lstItem = null;
		{
			Cursor cursor = openReadable().query($table(), $clumns(), sQLiteWhere.getWhereClause(), sQLiteWhere.getWhereArgs(), sQLiteWhere.getGroupBy(), sQLiteWhere.getHaving(),
					sQLiteWhere.getOrderBy(), sQLiteWhere.getLimit());
			try {
				if (cursor != null) {
					lstItem = new ArrayList<Item>();
					if (cursor.moveToFirst()) {
						do {
							try {
								lstItem.add($parse(cursor));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						} while (cursor.moveToNext());
					}
					cursor.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeReadable();
			}
		}
		return lstItem;
	}

	/**
	 * ����һ������
	 * 
	 * @param item
	 * @return
	 */
	public boolean insert(Item item) {
		long flag = 0;
		try {
			String nullColumnHack = null;
			flag = openWritable().insert($table(), nullColumnHack, $values(item));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeWritable();
		}
		if (flag > 0)
			return true;
		return false;
	}

	/**
	 * ����ʱ���£����߲�����ʱ����
	 * 
	 * @param item
	 * @param sQLiteWhere
	 * @return
	 */
	public boolean existsUpdateOrInsert(Item item, SQLiteWhere sQLiteWhere) {
		if (exists(sQLiteWhere)) {
			return update(item, sQLiteWhere);
		} else {
			return insert(item);
		}
	}

	/**
	 * �������������Ķ���
	 * 
	 * @param item
	 * @param sQLiteWhere
	 * @return
	 */
	public boolean update(Item item, SQLiteWhere sQLiteWhere) {
		long flag = 0;
		try {
			flag = openWritable().update($table(), $values(item), sQLiteWhere.getWhereClause(), sQLiteWhere.getWhereArgs());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeWritable();
		}
		if (flag > 0)
			return true;
		return false;
	}

	/**
	 * ɾ�����������Ķ���
	 * 
	 * @param item
	 * @param sQLiteWhere
	 * @return
	 */
	public boolean delete(SQLiteWhere sQLiteWhere) {
		long flag = 0;
		{
			flag = openWritable().delete($table(), sQLiteWhere.getWhereClause(), sQLiteWhere.getWhereArgs());
			closeWritable();
		}
		if (flag > 0)
			return true;
		return false;
	};

	protected boolean $boolean(Cursor cursor, String column) {
		return cursor.getInt(cursor.getColumnIndex(column)) != 0;
	}

	protected int $int(Cursor cursor, String column) {
		return cursor.getInt(cursor.getColumnIndex(column));
	}

	protected long $long(Cursor cursor, String column) {
		return cursor.getLong(cursor.getColumnIndex(column));
	}

	protected String $string(Cursor cursor, String column) {
		return cursor.getString(cursor.getColumnIndex(column));
	}
}

// List<Item> lstItem = null;
// {
// SQLiteWhere where = wheres(params);
// Cursor cursor = openReadable().query($table(), $clumns(),
// where.getExpression(), where.getParameters(), null, null, null,
// null);
// if (cursor != null) {
// lstItem = new ArrayList<Item>();
// if (cursor.moveToFirst()) {
// try {
// lstItem.add($parse(cursor));
// } catch (Exception ex) {
// ex.printStackTrace();
// }
// }
// cursor.close();
// }
// closeReadable();
// }
// return lstItem;

// private SQLiteWhere wheres(Param[] params) {
// SQLiteWhere where = new SQLiteWhere();
// if (params != null) {
// where.setParameters(new String[params.length]);
// for (int i = 0; i < params.length; i++) {
// if (i == 0) {
// where.setExpression(("(" + params[i].getKey() + params[i].getCdt() +
// "?)"));
// } else {
// where.setExpression(where.getExpression() + (" AND(" + params[i].getKey()
// + params[i].getCdt() + "?)"));
// }
// where.getParameters()[i] = params[i].getVal();
// }
// }
// return where;
// }

// private List<Item> select(SQLiteWhere where) {// Param[] params, String
// orderBy,
// // Pager
// // pager
// Item item = null;
// {
// // SQLiteWhere where = wheres(params);
// // String limit = null;
// // if (where.getpager != null) {
// // limit = pager.getAndroidSqliteLimit();
// // }
// Cursor cursor = openReadable().query($table(), $clumns(),
// where.getExpression(), where.getParameters(), null, null,
// where.getOrder(), where.getLimit());
// if (cursor != null) {
// if (cursor.moveToFirst()) {
// item = $parse(cursor);
// }
// cursor.close();
// }
// closeReadable();
// }
// return item;
// }

// public List<Item> select(SQLiteWhere where) {// Param[] params
// return select(where);
// }