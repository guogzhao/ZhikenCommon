package zhiken.common.encrypt;

/**
 * Tea�㷨 ÿ�β������Դ���8���ֽ����� KEYΪ16�ֽ�,ӦΪ����4��int������int[]��һ��intΪ4���ֽ� ���ܽ�������ӦΪ8�ı������Ƽ���������Ϊ64��
 * */
public class TEA {
	private final static int[] KEY = new int[] {// ���ܽ������õ�KEY
	0x789f5645, 0xf68bd5a4, 0x81963ffa, 0x458fac58 };

	// ͨ��TEA�㷨������Ϣ
	public byte[] encryptByTea(String info) {
		byte[] temp = info.getBytes();
		int n = 8 - temp.length % 8;// ��temp��λ������8�ı���,��Ҫ����λ��
		byte[] encryptStr = new byte[temp.length + n];
		encryptStr[0] = (byte) n;
		System.arraycopy(temp, 0, encryptStr, n, temp.length);
		byte[] result = new byte[encryptStr.length];
		for (int offset = 0; offset < result.length; offset += 8) {
			byte[] tempEncrpt = encrypt(encryptStr, offset, KEY, 32);
			System.arraycopy(tempEncrpt, 0, result, offset, 8);
		}
		return result;
	}

	// ͨ��TEA�㷨������Ϣ
	public String decryptByTea(byte[] secretInfo) {
		byte[] decryptStr = null;
		byte[] tempDecrypt = new byte[secretInfo.length];
		for (int offset = 0; offset < secretInfo.length; offset += 8) {
			decryptStr = decrypt(secretInfo, offset, KEY, 32);
			System.arraycopy(decryptStr, 0, tempDecrypt, offset, 8);
		}

		int n = tempDecrypt[0];
		return new String(tempDecrypt, n, decryptStr.length - n);

	}

	/**
	 * ����
	 * 
	 * @param content
	 * @param offset
	 * @param key
	 * @param times
	 * @return
	 */
	public byte[] encrypt(byte[] content, int offset, int[] key, int times) {// timesΪ��������
		int[] tempInt = byteToInt(content, offset);
		int y = tempInt[0], z = tempInt[1], sum = 0, i;
		int delta = 0x9e3779b9; // �����㷨��׼����ֵ
		int a = key[0], b = key[1], c = key[2], d = key[3];

		for (i = 0; i < times; i++) {

			sum += delta;
			y += ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			z += ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
		}
		tempInt[0] = y;
		tempInt[1] = z;
		return intToByte(tempInt, 0);
	}

	/**
	 * ����
	 * 
	 * @param encryptContent
	 * @param offset
	 * @param key
	 * @param times
	 * @return
	 */
	public byte[] decrypt(byte[] encryptContent, int offset, int[] key, int times) {
		int[] tempInt = byteToInt(encryptContent, offset);
		int y = tempInt[0], z = tempInt[1], sum = 0, i;
		int delta = 0x9e3779b9; // �����㷨��׼����ֵ
		int a = key[0], b = key[1], c = key[2], d = key[3];
		if (times == 32)
			sum = 0xC6EF3720; /* delta << 5 */
		else if (times == 16)
			sum = 0xE3779B90; /* delta << 4 */
		else
			sum = delta * times;

		for (i = 0; i < times; i++) {
			z -= ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
			y -= ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			sum -= delta;
		}
		tempInt[0] = y;
		tempInt[1] = z;

		return intToByte(tempInt, 0);
	}

	// byte[]������ת��int[]������
	private int[] byteToInt(byte[] content, int offset) {

		int[] result = new int[content.length >> 2];// ����2��n�η� == ����nλ �� content.length / 4 == content.length >> 2
		for (int i = 0, j = offset; j < content.length; i++, j += 4) {
			result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 | transform(content[j + 1]) << 16 | (int) content[j] << 24;
		}
		return result;

	}

	// int[]������ת��byte[]������
	private byte[] intToByte(int[] content, int offset) {
		byte[] result = new byte[content.length << 2];// ����2��n�η� == ����nλ �� content.length * 4 == content.length << 2
		for (int i = 0, j = offset; j < result.length; i++, j += 4) {
			result[j + 3] = (byte) (content[i] & 0xff);
			result[j + 2] = (byte) ((content[i] >> 8) & 0xff);
			result[j + 1] = (byte) ((content[i] >> 16) & 0xff);
			result[j] = (byte) ((content[i] >> 24) & 0xff);
		}
		return result;
	}

	// ��ĳ�ֽ�Ϊ�������轫��ת���޷�������
	private static int transform(byte temp) {
		int tempInt = (int) temp;
		if (tempInt < 0) {
			tempInt += 256;
		}
		return tempInt;
	}

	/**
	 * ���Դ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] KEY = new int[] {// ���ܽ������õ�KEY
		0x789f5645, 0xf68bd5a4, 0x81963ffa, 0x458fac58 };
		TEA tea = new TEA();

		byte[] info = new byte[] {

		1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.print("ԭ���ݣ�");
		for (byte i : info)
			System.out.print(i + " ");
		System.out.println();

		byte[] secretInfo = tea.encrypt(info, 0, KEY, 32);
		System.out.print("���ܺ�����ݣ�");
		for (byte i : secretInfo)
			System.out.print(i + " ");
		System.out.println();

		byte[] decryptInfo = tea.decrypt(secretInfo, 0, KEY, 32);
		System.out.print("���ܺ�����ݣ�");
		for (byte i : decryptInfo)
			System.out.print(i + " ");
	}
}// // ����
// public String encrypt(String plaintext, String charset, int[] key, int times) {// timesΪ��������
// byte[] plaintBytes = plaintext.getBytes(charset);
// byte[] cipherBytes = encrypt(plaintBytes, 0, key, times);
//
// }
//
// // ����
// public String decrypt(String ciphertext, String encoding, int[] key, int times) {
//
// }
