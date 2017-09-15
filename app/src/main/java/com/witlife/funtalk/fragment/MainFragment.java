package com.witlife.funtalk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hyphenate.chat.EMConversation;
import com.witlife.funtalk.R;
import com.witlife.funtalk.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bruce on 14/09/2017.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.listview)
    ListView listview;

    private MessageListAdapter adapter;
    private List<EMConversation> conversationList = new ArrayList<>();

    @Override
    protected void initData() {
        adapter = new MessageListAdapter();
        listview.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

}
