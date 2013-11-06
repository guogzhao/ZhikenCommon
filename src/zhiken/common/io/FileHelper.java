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
		return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();// תΪСд;
	}

	public static String getFileMD5Name(File file) throws IOException {
		String fileMd5 = MD5.get(file);
		String fileExt = FileHelper.getFileExtName(file.getName());
		return fileMd5 + fileExt;
	}

	// public static String sSdCardRootDir;

	// ��Uri uri =
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
	 * ���SDCard�Ƿ����
	 * 
	 * @return
	 */
	public static boolean exitsSDCard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * ��ȡSDCard��Ŀ¼
	 * 
	 * @return
	 */
	public static final String getSDCardDirectory() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * httpUrlתΪ��Ч���ļ���
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
	// // ���㻺���ļ���
	// // String filename = MD5.get(url) + url.substring(url.lastIndexOf("."));
	// String filename = MD5.get(url);
	//
	// // �õ������ϻ�����ļ�����
	// File file = new File(saveDir, filename);
	//
	// // ���ļ����ڣ�ֱ�ӷ�����Uri
	// if (file.exists()) {
	// return Uri.fromFile(file);
	// }
	// // file.createNewFile();//����Ҫ
	//
	// File fileTemp = new File(saveDir, filename + ".tmp");
	// // ���ر��浽��ʱ�ļ�
	// if (HttpHelper.downloadFile(url, fileTemp)) {
	// // ��������ʱ�ļ���Ŀ���ļ�
	// fileTemp.renameTo(file);
	// // �����ļ���Ӧ��uri
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
	// // ���ļ����ڣ�ֱ�ӷ�����Uri
	// if (file.exists()) {
	// return Uri.fromFile(file);
	// }
	//
	// // ������ʱ�ļ�
	// File fileTemp = new File(file.getPath() + ".tmp");
	//
	// // ���ر��浽��ʱ�ļ�
	// if (HttpHelper.downloadFile(url, fileTemp)) {
	//
	// // ��������ʱ�ļ���Ŀ���ļ�
	// fileTemp.renameTo(file);
	//
	// // �����ļ���Ӧ��uri
	// return Uri.fromFile(file);
	// }
	// return null;
	// }

	// public static void existsOrCreateFile(String dirpath, String filename) {
	//
	// // ����ļ����������򴴽���
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
	// // ���Ŀ¼���������򴴽���
	// File file = new File(dirpath);
	// if (!(file.exists() && file.isDirectory())) {
	// file.mkdirs();
	// }
	// }
	// String url, File file
	public static boolean saveFile2SDCard(byte[] byteArray, File dstfile) throws Exception {
		// ��ʱ�ļ�
		File tmpfile = new File(dstfile.getParent(), dstfile.getName() + ".tmp");

		try {
			// ���浽��ʱ�ļ�
			StreamHelper.copyStream(byteArray, new FileOutputStream(tmpfile));
			tmpfile.renameTo(dstfile);// ������ ��ʱ�ļ� �� Ŀ���ļ�

		} catch (Exception ex) {
			try {
				dstfile.delete();// ��������������ɾ��Ŀ���ļ�
			} catch (Exception e) {
			}
			try {
				tmpfile.delete();// ��������������ɾ����ʱ�ļ�
			} catch (Exception e) {
			}
			throw ex;
		}
		return false;
	}
	// public static boolean saveFile2SDCard(FileInputStream in, String
	// savefilepath) throws Exception {
	// // Ŀ���ļ�
	// File dstfile = new File(savefilepath);
	// // ��ʱ�ļ�
	// File tmpfile = new File(savefilepath + ".tmp");
	//
	// try {
	// // ���浽��ʱ�ļ�
	// StreamHelper.copyStream(in, new FileOutputStream(savefilepath));
	// tmpfile.renameTo(dstfile);// ������ ��ʱ�ļ� �� Ŀ���ļ�
	//
	// } catch (Exception ex) {
	// try {
	// dstfile.delete();// ��������������ɾ��Ŀ���ļ�
	// } catch (Exception e) {
	// }
	// try {
	// tmpfile.delete();// ��������������ɾ����ʱ�ļ�
	// } catch (Exception e) {
	// }
	// throw ex;
	// }
	// return false;
	// }
}

// Uri.parse("file:///sdcard/download/everything.mp3");
// /**
// * �ļ�·��ת����Uri
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