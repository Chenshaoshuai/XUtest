package com.example.asus.xutest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_circle)
    Button btn_circle;
    @BindView(R.id.btn_fillet)
    Button btn_fillet;
    @BindView(R.id.btn_ratio)
    Button btn_ratio;
    @BindView(R.id.btn_gif)
    Button btn_gif;
    @BindView(R.id.btn_annotation)
    Button btn_annotation;
    @BindView(R.id.btn_reflection)
    Button btn_reflection;
    @BindView(R.id.image_circle)
    SimpleDraweeView image_circle;
    @BindView(R.id.image_fillet)
    SimpleDraweeView image_fillet;
    @BindView(R.id.image_ratio)
    SimpleDraweeView image_ratio;
    @BindView(R.id.image_gif)
    SimpleDraweeView image_gif;
    private Uri uri;
    private Uri gif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        uri = Uri.parse("http://www.zhaoapi.cn/images/quarter/ad1.png");
        gif = Uri.parse("http://www.zhaoapi.cn/images/girl.gif");

    }
    @OnClick({R.id.btn_gif,R.id.btn_ratio,R.id.btn_fillet,R.id.btn_circle})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_circle:
                 image_circle.setImageURI(uri);
                image_circle.setVisibility(View.VISIBLE);
                image_fillet.setVisibility(View.GONE);
                image_ratio.setVisibility(View.GONE);
                image_gif.setVisibility(View.GONE);
                break;
            case R.id.btn_fillet:
                image_fillet.setImageURI(uri);
                image_circle.setVisibility(View.GONE);
                image_fillet.setVisibility(View.VISIBLE);
                image_ratio.setVisibility(View.GONE);
                image_gif.setVisibility(View.GONE);
                break;
            case R.id.btn_ratio:
                image_ratio.setImageURI(uri);
                image_circle.setVisibility(View.GONE);
                image_fillet.setVisibility(View.GONE);
                image_ratio.setVisibility(View.VISIBLE);
                image_gif.setVisibility(View.GONE);
                break;
            case R.id.btn_gif:
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(gif)
                        .setAutoPlayAnimations(true)
                        .build();
                image_gif.setController(controller);
                image_circle.setVisibility(View.GONE);
                image_fillet.setVisibility(View.GONE);
                image_ratio.setVisibility(View.GONE);
                image_gif.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_annotation:
                DraweeViewAnnotation draweeViewAnnotation=new DraweeViewAnnotation();
                Class<DraweeViewAnnotation> c=DraweeViewAnnotation.class;
                try {
                    Method method=c.getMethod("execute",new Class[]{});
                    if(method.isAnnotationPresent(MyAnnotation.class)){
                        MyAnnotation myAnnotation=method.getAnnotation(MyAnnotation.class);
                        try {
                            method.invoke(draweeViewAnnotation,new Object[]{});
                            String mName = myAnnotation.name();
                            Toast.makeText(MainActivity.this, mName,Toast.LENGTH_SHORT).show();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.btn_reflection:

                break;
                default:
                    break;
        }
    }
}
