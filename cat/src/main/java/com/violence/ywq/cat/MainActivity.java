package com.violence.ywq.cat;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;//定义播放器
    private HashMap<Integer, Integer> map;///定义集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到播放的位置
        View view = findViewById(R.id.breath_anim);
        //创建图画对象
        AnimationDrawable breath_anim = (AnimationDrawable) view.getBackground();
        breath_anim.start();//开启
        //创建播放器
        //参数1：播放数目
        //参数2：播放类型
        //参数3：优先级，官方不做解释，默认为1.
        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 1);
        map = new HashMap<>();
        map.put(1, soundPool.load(this, R.raw.p_knockout3, 1));//点击眼睛的音效
        map.put(2, soundPool.load(this, R.raw.p_poke_foot3, 1));//点击嘴巴的音效
        map.put(3, soundPool.load(this, R.raw.pillow5, 1));//点击尾巴的音效
        map.put(4, soundPool.load(this, R.raw.duzi, 1));
    }

    // 点击眼睛触发的方法
    public void Start_look(View view) {
        Start_anim(R.drawable.look_anim, 1);
    }

    //触发嘴巴
    public void Start_talk(View view) {
        Start_anim(R.drawable.talk_anim, 2);
    }

    //触发尾巴
    public void Start_fart(View view) {
        Start_anim(R.drawable.fart_anim, 3);
    }

    public void duzi(View view) {
        Start_anim(R.drawable.fart_anim, 4);
    }

    //创建一个方法管理播放的动画和音效
    //参数1：动画的资源
    //参数2：音效的ID
    public void Start_anim(int it1, int it2) {
        //第一步：找到播放的舞台
        final View other_anim = findViewById(R.id.other_aniam);
        other_anim.setBackgroundResource(it1);//添加背景
        AnimationDrawable anim = (AnimationDrawable) other_anim.getBackground();// //创建图画对象
        anim.setOneShot(true);//播放次数：为一次
        anim.start();
        //播放音效
        //参数1：音效Id
        //参数2、3：用于指定左右声道的音量，取值范围是0.0~1.0
        //参数4：int  priority：优先级，值越大优先级越高，0的优先级最低。
        //参数5：是否需要循环播放。
        //参数6：播放的速率：1正播放（0.5-2.0取值）。
        soundPool.play(map.get(it2), 1, 1, 0, 0, 1);
        int time = 500;//初始化时间为500毫秒
        //遍历获取时间
        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            time = time + anim.getDuration(i);//每一帧的时间加上初始化时间
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //让动画退场
                //设置背景颜色为透明
                other_anim.setBackgroundColor(Color.TRANSPARENT);
            }
        }, time);
    }
}
