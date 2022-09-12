package jp.didi.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AimSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aim_set);

        TextView setTargetAmount = findViewById(R.id.setTargetAmount);

    }

    /** 登録ボタン押下 */
    public void settingTargetAmount(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        //int targetAmount = sharedPreferences.getInt("SET_AMOUNT", 0);

        Intent intent = new Intent(this, MainActivity.class);

        EditText edit = (EditText)findViewById(R.id.setTargetAmount);
        Editable getTargetAmount = edit.getText();
        int targetAmount = Integer.parseInt(getTargetAmount.toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TARGET_AMOUNT", targetAmount);
        editor.apply();

        startActivity(intent);
    }

    /** 戻るボタン押させない */
    @Override
    public void onBackPressed() {
    }
}