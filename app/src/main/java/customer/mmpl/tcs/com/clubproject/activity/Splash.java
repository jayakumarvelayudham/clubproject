package customer.mmpl.tcs.com.clubproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import customer.mmpl.tcs.com.clubproject.MainActivity;
import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.sharedpreference.Session;

public class Splash extends AppCompatActivity implements Animation.AnimationListener{

    private ImageView iv;
    private static final String TAG = "Splash";
    private Session sessionManager;
    private Thread timerThread;
    private boolean isLoggedIn;
    Animation animFadeIn;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv = (ImageView) findViewById(R.id.iv);

        sessionManager = new Session(Splash.this);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        // set animation listener
        animFadeIn.setAnimationListener(this);
        // animation for image
        relativeLayout = (RelativeLayout) findViewById(R.id.splash);
        // start the animation
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout.startAnimation(animFadeIn);
        isLoggedIn = sessionManager.isLoggedIn();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerThread.isAlive()) {
            timerThread.interrupt();
        }
        finish();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        timerThread = new Thread() {
            public void run() {
//                try {
////                    sleep(5000);//3000
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isLoggedIn) {
                                Intent intent = new Intent(Splash.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(Splash.this, Login_Activity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
                }
//            }
        };
        timerThread.start();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
