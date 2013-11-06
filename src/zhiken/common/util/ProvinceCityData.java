package zhiken.common.util;

import zhiken.common.R;

public class ProvinceCityData {
	private static int[] sProvinceCityArrayResId = { R.array.no_province_array, R.array.beijin_province_array, R.array.tianjin_province_array, R.array.heibei_province_array,
			R.array.shanxi1_province_array, R.array.neimenggu_province_array, R.array.liaoning_province_array, R.array.jilin_province_array, R.array.heilongjiang_province_array,
			R.array.shanghai_province_array, R.array.jiangsu_province_array, R.array.zhejiang_province_array, R.array.anhui_province_array, R.array.fujian_province_array,
			R.array.jiangxi_province_array, R.array.shandong_province_array, R.array.henan_province_array, R.array.hubei_province_array, R.array.hunan_province_array,
			R.array.guangdong_province_array, R.array.guangxi_province_array, R.array.hainan_province_array, R.array.chongqing_province_array, R.array.sichuan_province_array,
			R.array.guizhou_province_array, R.array.yunnan_province_array, R.array.xizang_province_array, R.array.shanxi2_province_array, R.array.gansu_province_array,
			R.array.qinghai_province_array, R.array.linxia_province_array, R.array.xinjiang_province_array, R.array.hongkong_province_array, R.array.aomen_province_array,
			R.array.taiwan_province_array };

	public static int getProvinceCityArrayResId(int iProvinceIndex) {
		return sProvinceCityArrayResId[iProvinceIndex];
	}

	public static int getProvinceArrayResId() {
		return R.array.province_array;
	}
}
