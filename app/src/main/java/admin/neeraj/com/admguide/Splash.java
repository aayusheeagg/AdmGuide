package admin.neeraj.com.admguide;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	MediaPlayer sound;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		sound= MediaPlayer.create(Splash.this, R.raw.welcome);
		sound.start();
		Thread timer=new Thread(){
			public void run(){
				try {
					sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent open=new Intent(getApplicationContext(),MainActivity.class);
					startActivity(open);
                    finish();
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {		
		super.onPause();
		sound.release();
	}

}
