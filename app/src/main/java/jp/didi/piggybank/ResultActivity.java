package jp.didi.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resurt);

        TextView resultAmountLabel = findViewById(R.id.resultAmount);

        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        int resultAmount = getIntent().getIntExtra("TOTAL_AMOUNT", 0);
        resultAmountLabel.setText(getString(R.string.appear_amount, resultAmount));

        /** 目標金額と現在金額のリセット */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_AMOUNT", 0);
        editor.putInt("TARGET_AMOUNT", 0);
        editor.apply();
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
    }

    /** 戻るボタン押させない */
    @Override
    public void onBackPressed() {
    }
}