package com.xzit.zhihu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

//菜单栏
public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private ListView lv_item;
    private TextView tv_download, tv_main, tv_backuo, tv_login;
    private LinearLayout ll_menu;

    // private static String[] ITEMS = { "日常心理学", "用户推荐日报", "电影日报", "不许无聊",
    // "设计日报", "大公司日报", "财经日报", "互联网安全", "开始游戏", "音乐日报", "动漫日报", "体育日报" };
   private List<String> ls;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
