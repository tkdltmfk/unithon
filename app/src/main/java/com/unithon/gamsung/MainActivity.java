package com.unithon.gamsung;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.naver.speech.clientapi.SpeechConfig;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    static final String clientId = "YH3WfmuhjjcbpxevA5JZ";
    static final SpeechConfig SPEECH_CONFIG = SpeechConfig.OPENAPI_KR;
    Context context;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    String[] results;
    int analzeresults;
    int set;
    boolean running = false;
    VoiceRecognizer voiceRecognizer;
    Handler michandler;
    VoiceRecoder recoder;
    ProgressDialog dialog;
    FloatingActionButton fab;
    SqliteHelper sqliteHelper;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    int mu[]= {
            R.raw.coolguy, R.raw.peace, R.raw.zzanggu,R.raw.funny,R.raw.forest,R.raw.hpp1,R.raw.hpp2,R.raw.hpp3,R.raw.hpp4,R.raw.hpp5
            ,R.raw.soso,R.raw.soso1,R.raw.soso2,R.raw.soso3,R.raw.soso4,
            R.raw.sad1,R.raw.sad2,R.raw.sad3,R.raw.sad4,R.raw.sad5,R.raw.sad6,R.raw.sad7,R.raw.sad8,R.raw.sad9,R.raw.sad10
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        preferences = getSharedPreferences("contents", 0);
        editor = preferences.edit();
        sqliteHelper = new SqliteHelper(this, "db.db", null, 1);
        final Cursor cursor = sqliteHelper.getResult();

        if (preferences.getInt("count", 0) != 0) {
            while (cursor.moveToNext()) {
                int i = cursor.getInt(0);
                String con = cursor.getString(1);
                int setting = cursor.getInt(2);
                if (i == cursor.getCount() - 2 && cursor.getCount() - 2 > 3) {
                    StaticData.setting3 = setting;
                    StaticData.contents3 = con;
                }
                if (i == cursor.getCount() - 1 && cursor.getCount() - 2 > 2) {
                    StaticData.setting2 = setting;
                    StaticData.contents2 = con;
                }
                if (i == cursor.getCount()) {
                    StaticData.setting1 = setting;
                    StaticData.contents1 = con;
                    break;
                }
            }
        }
        if(StaticData.contents1 != null){
            StaticData.music = MediaPlayer.create(context, mu[StaticData.setting1]);
            StaticData.music.setLooping(true);
            StaticData.music.start();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (running == true) StaticData.music.stop();
                        running = true;
                        StaticData.music = MediaPlayer.create(context, mu[StaticData.setting1]);
                        StaticData.music.setLooping(true);
                        StaticData.music.start();
                        break;
                    case 1:
                        if (running == true) StaticData.music.stop();
                        running = true;
                        StaticData.music = MediaPlayer.create(context, mu[StaticData.setting2]);
                        StaticData.music.setLooping(true);
                        StaticData.music.start();
                        break;
                    case 2:
                        if (running == true) StaticData.music.stop();
                        running = true;
                        StaticData.music = MediaPlayer.create(context, mu[StaticData.setting3]);
                        StaticData.music.setLooping(true);
                        StaticData.music.start();
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //toolbar.setNavigationIcon(R.drawable.drawer_nav);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //View header = navigationView.inflateHeaderView(R.layout.nav_header_nevigation);


        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    voiceRecognizer.recognize();
                    Toast.makeText(context,"편안한 마음으로 말해 주세요",Toast.LENGTH_SHORT);
                }
                return false;
            }
        });


        michandler = new Handler() { //음성인식
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case VoiceRecognizer.onError:
                        if (recoder != null) {
                            recoder.close();
                        }
                        StaticData.err = msg.obj.toString();
                        break;
                    case VoiceRecognizer.onResult: //성공시 그림과 음악 바꿈
                        results = (String[]) msg.obj;
                        StaticData.contents3 = StaticData.contents2;
                        StaticData.contents2 = StaticData.contents1;
                        StaticData.contents1 = results[0];

                        analzeresults = AnalyzeEmotion.analyzeemotion(results[0]);
                        if (analzeresults > 3) set = (int)(Math.random()*3);
                        else if (analzeresults > 0) set = (int)(Math.random()*8+4);
                        else if (analzeresults == 0) set = (int)(Math.random()*12+9);
                        else if (analzeresults < 0) set = (int)(Math.random()*15+13);
                        else if (analzeresults < -3) set = (int)(Math.random()*18+16);

                        if (preferences.getInt("count", 0) == 0) {
                            editor.putInt("count", 1);
                            editor.commit();
                        } else {
                            editor.putInt("count", preferences.getInt("count", 0) + 1);
                            editor.commit();
                        }
                        sqliteHelper.insert(preferences.getInt("count", 0), results[0], set);
                        StaticData.setting3 = StaticData.setting2;
                        StaticData.setting2 = StaticData.setting1;
                        StaticData.setting1 = set;

                        viewPager.removeAllViews();
                        pagerAdapter = new PagerAdapter(context);
                        viewPager.setAdapter(pagerAdapter);
                        viewPager.setCurrentItem(0);


                        break;
                    case VoiceRecognizer.onReady:
                        recoder = new VoiceRecoder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/team3");
                        recoder.open("voice");
                        break;
                    case VoiceRecognizer.onRecord:
                        recoder.write((short[]) msg.obj);
                        break;
                    case VoiceRecognizer.onPartitialResult:

                        break;
                    case VoiceRecognizer.onInactive:
                        if (recoder != null) {
                            recoder.close();
                        }
                        break;
                }
            }
        };
        voiceRecognizer = new VoiceRecognizer(this, michandler, clientId, SPEECH_CONFIG);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        voiceRecognizer.getSpeechRecognizer().initialize();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        voiceRecognizer.getSpeechRecognizer().stopImmediately();
        voiceRecognizer.getSpeechRecognizer().release();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.unithon.gamsung/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        StaticData.music.stop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.unithon.gamsung/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
