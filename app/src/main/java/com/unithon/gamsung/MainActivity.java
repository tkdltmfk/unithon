package com.unithon.gamsung;

import android.Manifest;
import android.annotation.TargetApi;
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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.naver.speech.clientapi.SpeechConfig;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int mu[] = {
            R.raw.coolguy, R.raw.forest, R.raw.funny,
            R.raw.peace, R.raw.zzanggu
    };
    public static final int img[] = {
            R.drawable.airplain, R.drawable.dawn, R.drawable.drive, R.drawable.end_of_day, R.drawable.sunset
    };
    static final String clientId = "YH3WfmuhjjcbpxevA5JZ";
    static final SpeechConfig SPEECH_CONFIG = SpeechConfig.OPENAPI_KR;
    Context context;
    MediaPlayer music;
    Button btn_mute, btn_voice;
    TextView tv_musicName, tv_text;
    ImageView imgview;
    int pos;
    String[] results;
    int analzeresults;
    int set;
    boolean running = false;
    VoiceRecognizer voiceRecognizer;
    Handler michandler;
    VoiceRecoder recoder;
    FloatingActionButton fab;
    SqliteHelper sqliteHelper;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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
        btn_mute = (Button) findViewById(R.id.mute);
        imgview = (ImageView) findViewById(R.id.imageView);
        tv_musicName = (TextView) findViewById(R.id.musicName);
        tv_text = (TextView) findViewById(R.id.txt_result);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        preferences = getSharedPreferences("contents",0);
        editor = preferences.edit();
        sqliteHelper = new SqliteHelper(this,"db.db",null,1);
        Cursor cursor = sqliteHelper.getResult();

        if(preferences.getInt("count",0) != 0) {
            while (cursor.moveToNext()) {
                int i = cursor.getInt(0);
                String con = cursor.getString(1);
                int setting = cursor.getInt(2);
                if (i == cursor.getCount()) {
                    if (running == true) music.stop();
                    running = true;
                    music = MediaPlayer.create(context, mu[setting]);
                    music.setLooping(true);
                    music.start();
                    tv_musicName.setText(mu[setting]);
                    tv_text.setText(con);
                    imgview.setImageResource(img[setting]);
                }
            }
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
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
                        tv_text.setText("Error Code: " + msg.obj.toString());
                        break;
                    case VoiceRecognizer.onResult: //성공시 그림과 음악 바꿈
                        results = (String[]) msg.obj;
                        tv_text.setText(results[0]);
                        analzeresults = AnalyzeEmotion.analyzeemotion(results[0]);
                        if (analzeresults > 0) set = 1;
                        else if (analzeresults == 0) set = 2;
                        else if (analzeresults < 0) set = 3;

                        if(preferences.getInt("count",0) == 0){
                            editor.putInt("count",1);
                            editor.commit();
                        }else{
                            editor.putInt("count",preferences.getInt("count",0) + 1);
                            editor.commit();
                        }
                        sqliteHelper.insert(preferences.getInt("count",0),results[0],set);

                        if(running == true) music.stop();
                        running = true;
                        music = MediaPlayer.create(context, mu[set]);
                        music.setLooping(true);
                        music.start();
                        tv_musicName.setText(mu[set]);
                        imgview.setImageResource(img[set]);

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

    public void clickMute(View v) {
        if (music.isPlaying()) {
            pos = music.getCurrentPosition();
            music.pause();
            btn_mute.setText("start");

            try {
                music.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            music.seekTo(0);
        } else {
            music.seekTo(pos);
            music.start();
            btn_mute.setText("pause");
        }
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
        music.stop();

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
