package zhiken.common.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import zhiken.common.util.Logger;
import zhiken.common.util.MathUtil;

public class SocketClient implements Runnable {
	private static Logger mLogger = new Logger(SocketClient.class);
	public static final String DEFAULT_ENCODING = "utf-8";
	private Host mHost;
	private Socket mSocket;
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private Thread mThread;
	private int mReadCount;
	private byte[] mReadBuffer;
	private int mSendBufferSize = 1024;// K
	private int mRecvBufferSize = 1024;// K

	public SocketClient(Host host) {
		setHost(host);
		mThread = new Thread(this);
	}

	public SocketClient(String name, int port) {
		setHost(name, port);
		mThread = new Thread(this);
		mReadBuffer = new byte[mRecvBufferSize];
	}

	public Host getHost() {
		return mHost;
	}

	public void setHost(Host host) {
		this.mHost = host;
	}

	public void setHost(String name, int port) {
		this.mHost = new Host(name, port);
	}

	public boolean init() {
		try {
			mSocket = new Socket(mHost.getName(), mHost.getPort());
			mOutputStream = mSocket.getOutputStream();
			mInputStream = mSocket.getInputStream();
			mThread.start();// 开始接收数据线程
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mSocket != null)
			return true;
		return false;
	}

	private int mMaxReTryCount = 3;

	public int getMaxReTryCount() {
		return mMaxReTryCount;
	}

	public void setMaxReTryCount(int maxReTryCount) {
		mMaxReTryCount = maxReTryCount;
	}

	private int mCurReTryCount;

	public boolean reinit() {
		mCurReTryCount = 0;
		do {
			mCurReTryCount++;
			if (init())
				return true;
		} while (mCurReTryCount < mMaxReTryCount);
		return false;
	}

	public boolean sendData(byte[] data) {
		try {
			int size = MathUtil.chouti(data.length, mSendBufferSize);
			for (int i = 0; i < size; i++) {
				if (i == size - 1) {
					mOutputStream.write(data, i * mSendBufferSize, data.length
							% mSendBufferSize);
				} else {
					mOutputStream.write(data, i * mSendBufferSize,
							mSendBufferSize);
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// public boolean sendText(String text) {
	//
	// }
	//
	// public boolean sendFile(String path) {
	// }

	@Override
	public void run() {
		while (true) {
			try {
				mReadCount = mInputStream.read(mReadBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (mReadCount <= 0) {
				if (!reinit()) {// 重连
					mRecvCallback.onErrors(RecvCallback.TYPE_ERROR);
				}
				break;
			} else {
				mRecvCallback.onRecved(mReadCount, mReadBuffer);
			}
		}
	}

	public interface RecvCallback {
		public static final int TYPE_ERROR = 0;

		public void onErrors(int type);

		public void onRecved(int read, byte[] data);

		public void onSended();
	}

	private RecvCallback mRecvCallback = new RecvCallback() {

		@Override
		public void onRecved(int read, byte[] data) {
			mLogger.e("onRecved");
		}

		@Override
		public void onErrors(int type) {
			mLogger.e("onErrors");
		}

		@Override
		public void onSended() {
			mLogger.e("onSended");
		}
	};

	public RecvCallback getRecvCallback() {
		return mRecvCallback;
	}

	public void setRecvCallback(RecvCallback recvCallback) {
		mRecvCallback = recvCallback;
	}
}