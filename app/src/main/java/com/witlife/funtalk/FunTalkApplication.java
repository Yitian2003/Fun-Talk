package com.witlife.funtalk;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.witlife.funtalk.bean.Model;

/**
 * Created by bruce on 12/09/2017.
 */

public class FunTalkApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        EMOptions options = new EMOptions();

        options.setAutoAcceptGroupInvitation(false);
        options.setAcceptInvitationAlways(false);

        EaseUI.getInstance().init(this, options);

        Model.getInstance().init(this);
    }
}
