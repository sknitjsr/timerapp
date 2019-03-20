package com.example.egg;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    SeekBar seekBar;
    CountDownTimer cdt;
    TextView tv;
    Button btn;
    boolean isActive;
    public void update(int secondsleft) {


            int minutes = (int) secondsleft / 60;
            int seconds = secondsleft - minutes * 60;
            String second = Integer.toString(seconds);
            if (seconds <= 9) {
                second = "0" + second;

            }
            tv.setText(Integer.toString(minutes) + " : " + second);



    }
    public void finish(View view)
    {

        if (isActive == false) {
            isActive=true;
            btn.setText("Stop");
            seekBar.setEnabled(false);
    cdt =    new CountDownTimer(seekBar.getProgress() * 1000 + 100, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                update((int)millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                tv.setText("0:00");
                isActive=true;
                btn.setText("Go!");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.airhorn);
                mediaPlayer.start();


            }

        }.start();


        } else {

            tv.setText("0:00");
            seekBar.setProgress(30);
            cdt.cancel();
            seekBar.setEnabled(true);
            btn.setText("GO!");
            isActive = false;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isActive= false;
        seekBar = (SeekBar)findViewById(R.id.seekBar);
         tv = (TextView)findViewById(R.id.textView);
         btn = (Button)findViewById(R.id.button);

         seekBar.setProgress(30);
         seekBar.setMax(100);
         tv.setText("0:30");

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 update(progress);

             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });


        }
    }

