package com.example.guessthatname;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.guessthatname.R.font.arcade_classic;

public class MainActivity extends AppCompatActivity {
private int score;
private TextView mScoreTV;
private ImageView mAlbumArtIV;

private static final String SCORE_KEY = "currentScore";
private static final String testLink = "https://www.sageaudio.com/blog/wp-content/uploads/2014/04/album-art-300x300.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAlbumArtIV = findViewById(R.id.iv_album_art);
        ImageUtil.displayImageFromLink(mAlbumArtIV, testLink);
        mAlbumArtIV.setVisibility(View.VISIBLE);

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(SCORE_KEY)){
                score = savedInstanceState.getInt(SCORE_KEY);
            } else {
                score = 0;
            }
        }
        score+=2;
        mScoreTV = findViewById(R.id.tv_score);
        mScoreTV.setText(getString(R.string.score_pre)+" "+score);

        Typeface typeface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            typeface = getResources().getFont(arcade_classic);
            mScoreTV.setTypeface(typeface);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(score > 0){
            outState.putInt(SCORE_KEY, score);
        }
    }

    private void updateScore(int popularity){
        score += (100-(popularity/2));
        mScoreTV.setText(getString(R.string.score_pre)+" "+score);
    }
}
