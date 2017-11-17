package com.violence.ywq.democat;

import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

/*    private SoundPool soundPool;
    private HashMap<Integer,Integer> map;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        //创建一个播放器
        soundPool=new SoundPool(3, AudioManager.STREAM_SYSTEM,1);
        //创建一个集合保存音频
        map=new HashMap<>();
        map.put(1,soundPool.load(this,R.raw.p_knockout3,1));*/
        View view = findViewById(R.id.breath_anim);
        AnimationDrawable breath_anim = new AnimationDrawable();
        breath_anim.start();
    }
}
