package com.example.cointoss;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private ImageView coin;
    private Button flipbtn;
    private Button headsbtn;
    private Button tailsbtn;

    private TextView scoretxt;

    int headTails = 0;
    int score =0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coin = (ImageView) findViewById(R.id.imageView);
        headsbtn = (Button) findViewById(R.id.heads_btn);
        tailsbtn = (Button) findViewById(R.id.tails_btn);
        flipbtn = (Button) findViewById(R.id.flip_btn);
        scoretxt = (TextView) findViewById(R.id.score_txt);

        scoretxt.setText("0");


        flipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCoin();
            }
        });

        headsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headTails = 1;
            }
        });

        tailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headTails = 2;
            }
        });
    }

    private void flipCoin() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean ranNum = RANDOM.nextFloat()>0.5f;
                if(ranNum){
                    coin.setImageResource(R.drawable.pound_heads);
                    if(headTails == 1){
                        score++;
                    }
                }
                if(ranNum == false){
                    coin.setImageResource(R.drawable.pound_tails);
                    if(headTails == 2){
                        score++;
                    }
                }

                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);
                scoretxt.setText(String.valueOf(score));
                headTails = 0;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coin.startAnimation(fadeOut);
    }


}

