package com.example.advance.chapter1.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import com.example.advance.R;

/**
 * @author Administrator
 */
public class CardViewActivity extends AppCompatActivity {

    private CardView mCardView;
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        assignViews();
    }

    private void assignViews() {
        mCardView = (CardView) findViewById(R.id.tv_item);
        sb1 = (SeekBar) findViewById(R.id.sb_1);
        sb2 = (SeekBar) findViewById(R.id.sb_2);
        sb3= (SeekBar) findViewById(R.id.sb_3);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //圆的半径
                mCardView.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //阴影的半径
                mCardView.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //设置子控件和父空间的距离
                mCardView.setContentPadding(i,i,i,i);
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
