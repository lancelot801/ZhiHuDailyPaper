package com.xzit.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.xzit.zhihu.util.Constant;
import com.xzit.zhihu.util.HttpUtils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//打开知乎日报，启动也放大图片的动画效果
public class SplashActivity  extends Activity{
    private ImageView iv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //启动动画布局
        setContentView(R.layout.splash);
        iv_start=(ImageView)findViewById(R.id.iv_start);
        initImage();
    }

    private void initImage() {
        File dir=getFilesDir();
        final File imgFile=new File(dir,"start.jpg");
        if(imgFile.exists())
        {
            iv_start.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        }else
        {
            iv_start.setImageResource(R.mipmap.start);
        }
        final ScaleAnimation scaleAnim=new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                HttpUtils.get(Constant.START, new AsyncHttpResponseHandler() {
                    @Override
                    //导入Header时出现错误，原因是Header依赖于httpcore jar包
                    public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                        try {
                            JSONObject jsonObject=new JSONObject();
                            String url=jsonObject.getString("img");
                            HttpUtils.get(url, new BinaryHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                                    saveImage(imgFile,bytes);
                                    startActivity();
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                                    startActivity();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                            startActivity();
                    }
                });
                iv_start.startAnimation(scaleAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
            iv_start.startAnimation(scaleAnim);
    }

    private void startActivity() {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    private void saveImage(File file, byte[] bytes) {
        try {
            file.delete();
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
