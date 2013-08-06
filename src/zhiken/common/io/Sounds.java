package zhiken.common.io;

import zhiken.common.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {
	private SoundPool mSoundPool;// ����һ��SoundPool

	private int mSoundID;// ����һ��������load������������suondID

	public Sounds(Context context) {
		mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);// ��һ������Ϊͬʱ���������������������ڶ����������ͣ�����Ϊ��������

//		mSoundID = mSoundPool.load(context, R.raw.angry_birds_cross_sound, 1); // ����������زķŵ�res/raw���2��������Ϊ��Դ�ļ�����3��Ϊ���ֵ����ȼ�

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
