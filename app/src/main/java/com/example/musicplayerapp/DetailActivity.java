package com.example.musicplayerapp;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayerapp.Adapter.ListNhacAdapter;
import com.example.musicplayerapp.Model.BaiHat;


public class DetailActivity extends AppCompatActivity {
    private static final int ACTION_PAUSE =1 ;
    private static final int ACTION_RESUME =2 ;
    private static final int ACTION_CLEAR =3;
    private static final int ACTION_START =4 ;
    TextView txvTenBaiHat,tvTimerun,tvtimeMax;
    ImageView imgAnh,imgPlay,imgNext,imgPrevious;
    MediaPlayer mediaPlayer;
    BaiHat baiHat;
    SeekBar seekBar;
    boolean isPlaying=false;
    int gocQuay=0;
    int thoiGianNhacDung=0;
    int timeMax=0;
    int indexBaiNhac;
    boolean daCoNhac;
    ListNhacAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        anhXa();
        getObjectBaiNhac();
        setOnlick();

        new CountDownTimer(30000,50){

            @Override
            public void onTick(long millisUntilFinished) {
                upDateSeekBar();
            }

            @Override
            public void onFinish() {
                start();
            }
        }.start();

    }
    private void anhXa() {
        imgAnh = findViewById(R.id.imgAvatarNhac);
        txvTenBaiHat = findViewById(R.id.txtTenBaiHat);
        imgPlay=findViewById(R.id.imgPlay);
        seekBar=findViewById(R.id.seekbarNhac);
        tvTimerun=findViewById(R.id.txtTimeNhacRun);
        tvtimeMax=findViewById(R.id.txtTimeNhacMax);
        imgNext=findViewById(R.id.imgNext);
        imgPrevious=findViewById(R.id.imgPrevious);

    }
    private void upDateSeekBar() {
        if(isPlaying==false){
            return;
        }
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        gocQuay++;
        if(gocQuay==360){
            gocQuay=0;
        }


        tvTimerun.setText(convertMinute(mediaPlayer.getCurrentPosition()));
        tvtimeMax.setText(convertMinute(timeMax));
        imgAnh.setRotation(gocQuay);
    }

    public String convertMinute(long miliseconds){
        long minutes = (miliseconds / 1000) / 60;
        long seconds = (miliseconds / 1000) % 60;
        return checknum10(minutes)+":"+checknum10(seconds);
    }
     private String checknum10(long i){
         if(i<10){
             return "0"+i;
         }else {
             return ""+i;
         }
    }

    private void setOnlick() {
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                               if(isPlaying==false){
//                    startMusic(baiHat.getLinkMp3());
//                }else {
//                    pauseMusic();
//                }
                if(daCoNhac==false){
                    startMusic(baiHat.getLinkMp3());
                }else {
                    if(isPlaying==true){
                       pauseMusic();
                }else {
                       resumeMusic();
                }
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(isPlaying==false){
                    thoiGianNhacDung=seekBar.getProgress();
                    mediaPlayer.seekTo(thoiGianNhacDung);

                } else {
                    thoiGianNhacDung=seekBar.getProgress();
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(thoiGianNhacDung);
                    mediaPlayer.start();
                }


            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenBaiHat(+1);
            }
        });
        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenBaiHat(-1);
            }
        });
    }



    private void getObjectBaiNhac() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        baiHat = (BaiHat) bundle.get("baihat");
        txvTenBaiHat.setText(baiHat.getTenBaiHat());
        imgAnh.setImageResource(baiHat.getAvtBaiHat());
        indexBaiNhac=baiHat.getIndex();
    }
    private void startMusic(int fileMp3){
        if(mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this,fileMp3);
        }
        else {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=MediaPlayer.create(this,fileMp3);
        }
        mediaPlayer.start();
        imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        daCoNhac=true;
        isPlaying=true;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(0);
        }
        timeMax=mediaPlayer.getDuration();
        seekBar.setMax(timeMax);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finishMusic();
            }
        });


    }
    private void pauseMusic(){
        isPlaying=false;
        mediaPlayer.pause();
        imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        thoiGianNhacDung=mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(thoiGianNhacDung);
    }
    private void resumeMusic(){
        isPlaying=true;
        imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        mediaPlayer.seekTo(thoiGianNhacDung);
        mediaPlayer.start();
        thoiGianNhacDung=mediaPlayer.getCurrentPosition();
    }
    private void finishMusic(){
        isPlaying=false;
        daCoNhac=false;
        thoiGianNhacDung=0;
        gocQuay=0;
        imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);

    }
    private void chuyenBaiHat(int i) {
        indexBaiNhac=indexBaiNhac+i;
        if(indexBaiNhac==ListNhacActivity.listBaiHat.size()){
            indexBaiNhac=0;
        }
        if(indexBaiNhac==-1){
            indexBaiNhac=ListNhacActivity.listBaiHat.size()-1;
        }
        baiHat=ListNhacActivity.listBaiHat.get(indexBaiNhac);
        imgAnh.setImageResource(baiHat.getAvtBaiHat());
        txvTenBaiHat.setText(baiHat.getTenBaiHat());
        startMusic(baiHat.getLinkMp3());
    }


}
