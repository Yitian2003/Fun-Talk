package com.witlife.funtalk.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.witlife.funtalk.R;
import com.witlife.funtalk.fragment.ContactFragment;
import com.witlife.funtalk.fragment.DiscoveryFragment;
import com.witlife.funtalk.fragment.MainFragment;
import com.witlife.funtalk.fragment.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rb_main)
    RadioButton rbMain;
    @BindView(R.id.rb_contact)
    RadioButton rbContact;
    @BindView(R.id.rb_discover)
    RadioButton rbDiscover;
    @BindView(R.id.rb_me)
    RadioButton rbMe;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private ContactFragment contactFragment;
    private DiscoveryFragment discoveryFragment;
    private ProfileFragment profileFragment;

    @Override
    public void initView() {
        super.initView();

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                FragmentManager manager = MainActivity.this.getSupportFragmentManager();
                transaction = manager.beginTransaction();

                switch (i){
                    case R.id.rb_main:
                        toMain();
                        break;
                    case R.id.rb_contact:
                        toContact();
                        break;
                    case R.id.rb_discover:
                        toDiscover();
                        break;
                    case R.id.rb_me:
                        toProfile();
                        break;
                }
                transaction.commit();
            }
        });

        rgMain.check(R.id.rb_main);
    }

    private void toProfile() {
        if (profileFragment == null){
            profileFragment = new ProfileFragment();
        }
        transaction.replace(R.id.frameLayout,profileFragment);
        transaction.show(profileFragment);
    }

    private void toDiscover() {
        if (discoveryFragment == null){
            discoveryFragment = new DiscoveryFragment();
        }
        transaction.replace(R.id.frameLayout,discoveryFragment);
        transaction.show(discoveryFragment);
    }

    private void toContact() {
        if (contactFragment == null){
            contactFragment = new ContactFragment();
        }
        transaction.replace(R.id.frameLayout,contactFragment);
        transaction.show(contactFragment);
    }

    private void toMain() {
        if (mainFragment == null){
            mainFragment = new MainFragment();
        }
        transaction.replace(R.id.frameLayout,mainFragment);
        transaction.show(mainFragment);
    }

    @Override
    public void initData() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitle() {

    }
}
