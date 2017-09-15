package com.witlife.funtalk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.witlife.funtalk.R;
import com.witlife.funtalk.bean.Model;
import com.witlife.funtalk.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bruce on 14/09/2017.
 */

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.tvname)
    TextView tvname;
    @BindView(R.id.tvmsg)
    TextView tvmsg;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.view_user)
    RelativeLayout viewUser;
    @BindView(R.id.txt_money)
    TextView txtMoney;
    @BindView(R.id.txt_collect)
    TextView txtCollect;
    @BindView(R.id.txt_album)
    TextView txtAlbum;
    @BindView(R.id.txt_card)
    TextView txtCard;
    @BindView(R.id.txt_smail)
    TextView txtSmail;
    @BindView(R.id.txt_setting)
    TextView txtSetting;

    private UserBean user;

    @Override
    protected void initData() {
        user = Model.getInstance().getUserAccountDao().getAccountById(EMClient.getInstance().getCurrentUser());

        if (user != null) {
            tvname.setText(user.getUserName());
            tvmsg.setText(user.getId());
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

}
