package com.unithon.gamsung;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by kyun on 2016-07-30.
 */
public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    Context mcontext;
    LayoutInflater mInflater;
    ImageButton btn_play, btn_pause;
    TextView tv_musicName, tv_text, tag1, tag2;
    ImageView imgview, scrap;
    SqliteHelper sqliteHelper;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int pos;
    boolean running = false;

    int img[]= {
            R.drawable.airplain, R.drawable.dawn, R.drawable.drive, R.drawable.end_of_day, R.drawable.sunset,R.drawable.happy1, R.drawable.happy2,
            R.drawable.happy3, R.drawable.happy4, R.drawable.happy5, R.drawable.happy6, R.drawable.happy7, R.drawable.enjoy,
            R.drawable.sad1, R.drawable.sad2, R.drawable.sad3, R.drawable.sad4, R.drawable.sad5, R.drawable.sad6
    };

    int mu[]= {
            R.raw.coolguy, R.raw.peace, R.raw.zzanggu,R.raw.funny,R.raw.forest,R.raw.hpp1,R.raw.hpp2,R.raw.hpp3,R.raw.hpp4,R.raw.hpp5
            ,R.raw.soso,R.raw.soso1,R.raw.soso2,R.raw.soso3,R.raw.soso4,
            R.raw.sad1,R.raw.sad2,R.raw.sad3,R.raw.sad4,R.raw.sad5,R.raw.sad6,R.raw.sad7,R.raw.sad8,R.raw.sad9,R.raw.sad10
    };

    public PagerAdapter (Context context) {
        super();
        mcontext = context;
        mInflater = LayoutInflater.from(context);
        preferences = mcontext.getSharedPreferences("contents",0);
        editor = preferences.edit();
        sqliteHelper = new SqliteHelper(mcontext,"db.db",null,1);
    }

    public int getCount() {
        return 3;
    }

    public Object instantiateItem(View pager, int position) {
        View v = null;
        switch (position){
            case 0:
                v = mInflater.inflate(R.layout.pager_inflater,null);
                if(StaticData.contents1 != null) {
                    btn_play = (ImageButton) v.findViewById(R.id.play);
                    btn_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPlay(v);
                        }
                    });
                    btn_pause = (ImageButton) v.findViewById(R.id.pause);
                    btn_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPause(v);
                        }
                    });
                    imgview = (ImageView) v.findViewById(R.id.imageView);
                    imgview.setImageResource(img[StaticData.setting1]);
                    tv_musicName = (TextView) v.findViewById(R.id.musicName);
                    tv_text = (TextView) v.findViewById(R.id.txt_result);
                    tv_text.setText("# " +StaticData.contents1);
                    tv_musicName.setText(mu[StaticData.setting1]);
                    scrap = (ImageView) v.findViewById(R.id.scrap);
                    scrap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mcontext,"하트 주셔서 감사합니다~",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case 1:
                v = mInflater.inflate(R.layout.pager_inflater,null);
                if(StaticData.contents2 != null) {
                    btn_play = (ImageButton) v.findViewById(R.id.play);
                    btn_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPlay(v);
                        }
                    });
                    btn_pause = (ImageButton) v.findViewById(R.id.pause);
                    btn_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPause(v);
                        }
                    });
                    imgview = (ImageView) v.findViewById(R.id.imageView);
                    imgview.setImageResource(img[StaticData.setting2]);
                    tv_musicName = (TextView) v.findViewById(R.id.musicName);
                    tv_text = (TextView) v.findViewById(R.id.txt_result);
                    tv_text.setText("# " + StaticData.contents2);
                    tv_musicName.setText(mu[StaticData.setting2]);
                    scrap = (ImageView) v.findViewById(R.id.scrap);
                    scrap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mcontext,"하트 주셔서 감사합니다~",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case  2:
                v = mInflater.inflate(R.layout.pager_inflater,null);
                if(StaticData.contents3 != null) {
                    btn_play = (ImageButton) v.findViewById(R.id.play);
                    btn_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPlay(v);
                        }
                    });
                    btn_pause = (ImageButton) v.findViewById(R.id.pause);
                    btn_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickPause(v);
                        }
                    });
                    imgview = (ImageView) v.findViewById(R.id.imageView);
                    imgview.setImageResource(img[StaticData.setting3]);
                    tv_musicName = (TextView) v.findViewById(R.id.musicName);
                    tv_text = (TextView) v.findViewById(R.id.txt_result);
                    tv_text.setText("# " +StaticData.contents3);
                    tv_musicName.setText(mu[StaticData.setting3]);
                    scrap = (ImageView) v.findViewById(R.id.scrap);
                    scrap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mcontext,"하트 주셔서 감사합니다~",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
        ((ViewPager)pager).addView(v, null);
        return v;
    }

    public void clickPause(View v) {
        if (StaticData.music.isPlaying()) {
            pos = StaticData.music.getCurrentPosition();
            StaticData.music.pause();
            try {
                StaticData.music.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //btn_pause.setEnabled(false);
            //music.seekTo(0);
        } else {
            //btn_pause.setEnabled(true);
        }
    }

    public void clickPlay(View v) {
        if (StaticData.music.isPlaying() == false) {
            StaticData.music.seekTo(pos);
            StaticData.music.start();
            //btn_pause.setEnabled(false);
        } else {
           //btn_pause.setEnabled(true);
        }

    }



    public void destroyItem(View pager, int position, Object view) {
        ((ViewPager)pager).removeView((View)view);
    }

    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

}
