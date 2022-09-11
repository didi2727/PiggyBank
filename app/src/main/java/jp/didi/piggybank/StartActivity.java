package jp.didi.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startGame(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        int targetAmount = sharedPreferences.getInt("TARGET_AMOUNT", 0);

        if (targetAmount <= 0){
            startActivity(new Intent(getApplicationContext(), AimSetActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}