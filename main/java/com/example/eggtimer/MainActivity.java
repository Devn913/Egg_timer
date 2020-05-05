package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;;

public class MainActivity extends AppCompatActivity {
    TextView myTextView;
    SeekBar mySeekBar;
    Boolean timerIsActive = false;
    Button myButton;
    CountDownTimer countDownTimer;
    public void startTimer(View view) {
        if (timerIsActive) {
            myTextView.setText("0:30");
            mySeekBar.setProgress(30);
            mySeekBar.setEnabled(true);
            countDownTimer.cancel();
            myButton.setText("GO");
            timerIsActive = false;
        } else {
            timerIsActive = true;
            mySeekBar.setEnabled(false);
            myButton.setText("STOP");
            int progress = mySeekBar.getProgress();
            countDownTimer = new CountDownTimer(progress * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    myProgress((int) millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mediaPlayer.start();
                    timerIsActive = false;
                    mySeekBar.setEnabled(true);
                    myButton.setText("GO");
                    myTextView.setText("0:30");

                }
            }.start();

        }
    }
    public void myProgress (int progress){
        Integer minSet = progress/60;
        Integer secSet = progress - minSet * 60;
        String showingSec = Integer.toString(secSet) ;
        if(secSet < 10) showingSec = "0" + Integer.toString(secSet);
        myTextView.setText(Integer.toString(minSet)+ ":"+ showingSec);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        myTextView = (TextView) findViewById(R.id.timerTextView);
        myButton = (Button) findViewById(R.id.button);
        mySeekBar.setMax(600);
        myTextView.setText("5" +":"+ "00");
        mySeekBar.setProgress(300);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myProgress(progress);
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
