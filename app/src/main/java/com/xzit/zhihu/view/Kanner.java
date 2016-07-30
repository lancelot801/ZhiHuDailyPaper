package com.xzit.zhihu.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xzit.zhihu.R;
import com.xzit.zhihu.model.Latest;

import java.util.ArrayList;
import java.util.List;

//图片轮播控件
public class Kanner extends FrameLayout implements View.OnClickListener{
    private List<Latest.TopStoriesEntity> topStoriesEntities;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    private List<View> views;
    private Context context;
    private ViewPager vp;
    private boolean isAutoPlay;
    private int currentItem;
    private int delayTime;
    private LinearLayout ll_dot;
    private List<ImageView> iv_dots;
    private Handler handler=new Handler();
    private AdapterView.OnItemClickListener mItemClickListener;

    //构造方法
    public Kanner(Context context, AttributeSet attrs,int defStyle)
    {
        super(context,attrs,defStyle);
        mImageLoader=ImageLoader.getInstance();
        options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        this.context=context;
        this.topStoriesEntities=new ArrayList<>();
        intitView();
    }

    private void intitView() {
        views=new ArrayList<View>();
        iv_dots=new ArrayList<ImageView>();
        delayTime=2000;
    }

    public Kanner(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public Kanner(Context context) {
        super(context,null);
    }

    public void setTopStoriesEntities(List<Latest.TopStoriesEntity> topStoriesEntities) {
        this.topStoriesEntities = topStoriesEntities;
        reset();
    }

    private void reset() {
        views.clear();
        initUI();
    }

    private void initUI() {
        View view= LayoutInflater.from(context).inflate(
                R.layout.kanner_layout,this,true);
        vp=(ViewPager)view.findViewById(R.id.vp);
        ll_dot=(LinearLayout)view.findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();

        int len=topStoriesEntities.size();
        for(int i=0;i<len;i++)
        {
            ImageView iv_dot=new ImageView(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
              LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.leftMargin=5;
            params.rightMargin=5;
            ll_dot.addView(iv_dot,params);
            iv_dots.add(iv_dot);
        }
       // for (int i=0;)
    }

    @Override
    public void onClick(View v) {

    }
}
