package zhiken.common.util;

/**
 * @create 2013-06-09 13:51
 * @author guogzhao
 * 
 */
public class MathUtil {
	/**
	 * ???
	 * 
	 * @param dui
	 * @param hu
	 * @return
	 */
	public static int chouti(int dui, int hu) {
		return dui / hu + (dui % hu == 0 ? 0 : 1);
	}
}
