package com.witlife.funtalk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.hyphenate.chat.EMClient;
import com.witlife.funtalk.R;
import com.witlife.funtalk.bean.Model;
import com.witlife.funtalk.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends Activity {

    public static final int TO_LOGIN = 3000;
    public static final int TO_MAIN = 3001;

    @BindView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    private int messageCode = TO_LOGIN;
    private long startTime;
    private long endTime;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (isFinishing()) {
                return;
            }
            Intent mIntent;

            switch (msg.what) {
                case TO_LOGIN:
                    mIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                    break;

                case TO_MAIN:
                    mIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        checkUserInList();
        setAnimation();
    }

    private void checkUserInList() {

        startTime = System.currentTimeMillis();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                boolean isLoginedBefore = EMClient.getInstance().isLoggedInBefore();
                endTime = System.currentTimeMillis();
                long remainTime = 3000 - (endTime - startTime);

                if (remainTime < 0) {
                    remainTime = 0;
                }
                if (isLoginedBefore) {
                    // get current user info
                    UserBean user = Model.getInstance().getUserAccountDao().getAccountById(EMClient.getInstance().getCurrentUser());

                    if(user == null) {
                        messageCode = TO_LOGIN;
                    } else {
                        messageCode = TO_MAIN;
                        // save to model after login
                        Model.getInstance().loginSuccess(user);
                    }
                } else {
                    messageCode = TO_LOGIN;
                }
                handler.sendEmptyMessageDelayed(messageCode, remainTime);
            }
        });
    }

    private void setAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(3000);
        animation.setInterpolator(new AccelerateInterpolator());
        rlWelcome.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
