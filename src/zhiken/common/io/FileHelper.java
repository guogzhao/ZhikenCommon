package zhiken.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import zhiken.common.encrypt.MD5;
import zhiken.common.net.HttpHelper;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Files;

/**
 * 2013-03-10
 * 
 * @author guogzhao
 * 
 */
public class FileHelper {

	public static String getFileExtName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();// 转为小写;
	}

	public static String getFileMD5Name(File file) throws IOException {
		String fileMd5 = MD5.get(file);
		String fileExt = FileHelper.getFileExtName(file.getName());
		return fileMd5 + fileExt;
	}

	// public static String sSdCardRootDir;

	// 　Uri uri =
	// if ("/mnt/extsd/AutoNavi") {
	// } else if (
	// "/mnt/sdcard2/AutoNavi") {
	// } else if (
	// "/sdcard-ext/AutoNavi") {
	// } else if ("/mnt/E/AutoNavi") {
	// } else {
	// "/mnt/sdcard/AutoNavi"
	// }

	/**
	 * 检测SDCard是否挂载
	 * 
	 * @return
	 */
	public static boolean exitsSDCard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SDCard根目录
	 * 
	 * @return
	 */
	public static final String getSDCardDirectory() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * httpUrl转为有效的文件名
	 * 
	 * @param url
	 * @return
	 */
	public static String url2filename(String url) {
		return url.replace(":", "_").replace("/", "_");// .replace(".",
														// "");
	}

	public static String getFilePath(String dirpath, String filename) {

		if (dirpath.endsWith("/")) {
			return dirpath + filename;
		} else {
			return dirpath + "/" + filename;
		}
	}

	public static boolean fileExists(String dirpath, String filename) {
		return fileExists(getFilePath(dirpath, filename));
	}

	public static boolean fileExists(String filepath) {
		return new File(filepath).exists();
	}

	public static boolean fileCreate(String dirpath, String filename) {
		return fileCreate(getFilePath(dirpath, filename));
	}

	public static boolean fileCreate(String filepath) {
		try {
			return new File(filepath).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean dirsExists(String dirpath) {
		return new File(dirpath).exists();
	}

	public static boolean dirsCreate(String dirpath) {
		return new File(dirpath).mkdirs();
	}

	// public static Uri getFileUri2(String url, File saveDir) throws Exception
	// {
	// // 计算缓存文件名
	// // String filename = MD5.get(url) + url.substring(url.lastIndexOf("."));
	// String filename = MD5.get(url);
	//
	// // 得到磁盘上缓存的文件对象
	// File file = new File(saveDir, filename);
	//
	// // 若文件存在，直接返回其Uri
	// if (file.exists()) {
	// return Uri.fromFile(file);
	// }
	// // file.createNewFile();//不需要
	//
	// File fileTemp = new File(saveDir, filename + ".tmp");
	// // 下载保存到临时文件
	// if (HttpHelper.downloadFile(url, fileTemp)) {
	// // 重命名临时文件到目标文件
	// fileTemp.renameTo(file);
	// // 返回文件对应的uri
	// return Uri.fromFile(file);
	// }
	// return null;
	// }

	public static String getUrl2MD5FileName(String url) {
		return MD5.get(url);
	}

	public static String getUrl2MD5FileName(String url, String ext) {
		return MD5.get(url) + "." + ext;
	}

	// public static Uri getFileUri(String url, File file) throws Exception {
	//
	// // 若文件存在，直接返回其Uri
	// if (file.exists()) {
	// return Uri.fromFile(file);
	// }
	//
	// // 生成临时文件
	// File fileTemp = new File(file.getPath() + ".tmp");
	//
	// // 下载保存到临时文件
	// if (HttpHelper.downloadFile(url, fileTemp)) {
	//
	// // 重命名临时文件到目标文件
	// fileTemp.renameTo(file);
	//
	// // 返回文件对应的uri
	// return Uri.fromFile(file);
	// }
	// return null;
	// }

	// public static void existsOrCreateFile(String dirpath, String filename) {
	//
	// // 检测文件（不存在则创建）
	// File file = new File(getFilePath(dirpath, filename));
	// if (file.exists()) {
	// file.delete();
	// }
	// try {
	// file.createNewFile();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static void existsOrCreateDirectory(String dirpath) {
	// // 检测目录（不存在则创建）
	// File file = new File(dirpath);
	// if (!(file.exists() && file.isDirectory())) {
	// file.mkdirs();
	// }
	// }
	// String url, File file
	public static boolean saveFile2SDCard(byte[] byteArray, File dstfile) throws Exception {
		// 临时文件
		File tmpfile = new File(dstfile.getParent(), dstfile.getName() + ".tmp");

		try {
			// 保存到临时文件
			StreamHelper.copyStream(byteArray, new FileOutputStream(tmpfile));
			tmpfile.renameTo(dstfile);// 重命名 临时文件 到 目标文件

		} catch (Exception ex) {
			try {
				dstfile.delete();// 若重命名出错，则删除目标文件
			} catch (Exception e) {
			}
			try {
				tmpfile.delete();// 若重命名出错，则删除临时文件
			} catch (Exception e) {
			}
			throw ex;
		}
		return false;
	}
	// public static boolean saveFile2SDCard(FileInputStream in, String
	// savefilepath) throws Exception {
	// // 目标文件
	// File dstfile = new File(savefilepath);
	// // 临时文件
	// File tmpfile = new File(savefilepath + ".tmp");
	//
	// try {
	// // 保存到临时文件
	// StreamHelper.copyStream(in, new FileOutputStream(savefilepath));
	// tmpfile.renameTo(dstfile);// 重命名 临时文件 到 目标文件
	//
	// } catch (Exception ex) {
	// try {
	// dstfile.delete();// 若重命名出错，则删除目标文件
	// } catch (Exception e) {
	// }
	// try {
	// tmpfile.delete();// 若重命名出错，则删除临时文件
	// } catch (Exception e) {
	// }
	// throw ex;
	// }
	// return false;
	// }
}

// Uri.parse("file:///sdcard/download/everything.mp3");
// /**
// * 文件路径转本地Uri
// *
// * @param filepath
// * @return
// */
// Uri.parse();
// public static Uri filepath2uri(String filepath) {
// if (filepath.startsWith("/")) {
// return Uri.parse("file://" + filepath);
// } else {
//
// return Uri.parse("file:///" + filepath);
// }
// }