package zhiken.common.io;

import zhiken.common.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {
	private SoundPool mSoundPool;// 声明一个SoundPool

	private int mSoundID;// 定义一个整型用load（）；来设置suondID

	public Sounds(Context context) {
		mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);// 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量

//		mSoundID = mSoundPool.load(context, R.raw.angry_birds_cross_sound, 1); // 把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

	}

	public void play(float leftVolume, float rightVolume, int priority,
			int loop, float rate) {
		mSoundPool
				.play(mSoundID, leftVolume, rightVolume, priority, loop, rate);
	}

	public void play() {
		mSoundPool.play(mSoundID, 0.5f, 0.5f, 0, 0, 1);		
	}

	// ******************************************************************
	// private SoundManager mSoundManager;
	// mSoundManager = new SoundManager();

	// mSoundManager.initSounds(getBaseContext());
	//
	// mSoundManager.addSound(1, R.raw.finn_whatthejugisthat);
	//
	// mSoundManager.addSound(2, R.raw.finn_wordtoyourmother);
	//
	// mSoundManager.playSound(1);
	//
	// mSoundManager.playSound(2);
}
