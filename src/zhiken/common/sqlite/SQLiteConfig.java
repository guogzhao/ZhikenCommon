package zhiken.common.sqlite;

import java.util.List;

/**
 * 
 * @create 2013-09-06 14:56
 * @author guogzhao
 * 
 *         数据库配置类
 */
public class SQLiteConfig {
	// public SQLiteConfig(int version, String name, List<String> tables) {
	//
	// }

	private int version;
	private String name;
	private List<String> tables;

	public int getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public List<String> getTables() {
		return tables;
	}
}

// protected static final int VERSION = 1;
// protected static final String DB_NAME = "xy.db";

// protected static final List<String> TABLES;
// // SQL
// static {
// TABLES = new ArrayList<String>();
// // // _user
// // {
// // String sql = "";
// // sql += "CREATE TABLE [_user] (";
// // sql += "[id] INTEGER NOT NULL  PRIMARY KEY,";
// // sql += "[name] TEXT,";
// // sql += "[nick] TEXT NOT NULL, ";
// // sql += "[mail] TEXT NOT NULL,";
// // sql += "[pnum] TEXT,";
// // // sql += "[spwd] TEXT NOT NULL,";
// // sql += "[session] TEXT NOT NULL,";
// // sql += "[verified] INTEGER NOT NULL,";// isStar
// // sql += "[gender] INTEGER NOT NULL,";// isMale
// // sql += "[birthday] INTEGER NOT NULL,";
// // sql += "[unreadmsg] INTEGER NOT NULL,";
// // sql += "[goodsCount] INTEGER NOT NULL,";
// // sql += "[followingCount] INTEGER NOT NULL,";
// // sql += "[followersCount] INTEGER NOT NULL,";
// // sql += "[headImage] TEXT,";
// // sql += "[backImage] TEXT,";
// // sql += "[_address] TEXT,";
// // sql += "[_contact] TEXT,";
// // sql += "[_telphone] TEXT,";
// // sql += "[_leadtime] TEXT,";
// // sql += "[sysUpdate] INTEGER);";// ggz+++//用以判断最后一个登陆的用户
// // TABLES.add(sql);
// // }
// // // _welcome
// // {
// // String sql = "";
// // sql += "CREATE TABLE [_welcome] (";
// // sql += "[id] INTEGER NOT NULL PRIMARY KEY,";
// // sql += "[startTime] INTEGER NOT NULL, ";
// // sql += "[logoImage] TEXT,";
// // sql += "[backImage] TEXT NOT NULL,";
// // sql += "[textLabel] TEXT);";
// // TABLES.add(sql);
// // }
// //
// // // _message
// // TABLES.add($jsonTableScript("_message", "messageTypeId",
// // "readFlag"));
// //

// /**
// * 数据库版本
// *
// * @return
// */
// protected abstract int $version();
//
// /**
// * 数据库名
// *
// * @return
// */
// protected abstract String $name();
//
// /**
// * 数据库表数组
// *
// * @return
// */
// protected abstract List<String> $tables();

// /**
// * 返回 JSON 表创建脚本
// *
// * @param table
// * @param clumns
// * @return
// */
// @SuppressWarnings("unused")
// protected static String $jsonTableScript(String table, String... clumns) {
// StringBuilder str = new StringBuilder();
// {
// str.append("CREATE TABLE [");
// str.append(table);
// str.append("]([id] INTEGER NOT NULL PRIMARY KEY");
// if (clumns != null) {
// for (int i = 0; i < clumns.length; i++) {
// str.append(",[");
// str.append(clumns[i]);
// str.append("] TEXT");// NOT NULL
// }
// }
// str.append(",[json] INTEGER NOT NULL);");
// }
// return str.toString();
// }
// // // _order
// // TABLES.add($jsonTableScript("_order", "orderStatusId"));
// //
// // // _actor
// // TABLES.add($jsonTableScript("_actor", "isInterest"));
// //
// // // _wish
// // TABLES.add($jsonTableScript("_wish", "actorId"));
// //
// // // _goods
// // TABLES.add($jsonTableScript("_goods", "channelId", "goodsTypeId",
// // "actorId"));
// //
// // // _comment
// // TABLES.add($jsonTableScript("_comment", "goodsId"));
// }