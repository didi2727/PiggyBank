package jp.didi.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   int totalAmountCount = 0;
   int targetAmountAppear = 0;

   //public TextView targetAmountLabel = findViewById(R.id.targetAmountLabel);
   //public TextView totalAmountLabel = findViewById(R.id.totalAmountLabel);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView targetAmountLabel = findViewById(R.id.targetAmountLabel);
        TextView totalAmountLabel = findViewById(R.id.totalAmountLabel);

        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        int targetAmount = sharedPreferences.getInt("TARGET_AMOUNT", 0);
        int totalAmount = sharedPreferences.getInt("TOTAL_AMOUNT", 0);

        totalAmountCount += totalAmount;
        targetAmountAppear += targetAmount;

        targetAmountLabel.setText(getString(R.string.appear_amount, targetAmountAppear));
        totalAmountLabel.setText(getString(R.string.appear_amount, totalAmountCount));
    }

    public void increaseButton(View view){
        //Intent intent = new Intent(this, MainActivity.class);

        EditText edit = (EditText)findViewById(R.id.increaseAmount);
        Editable getIncreaseAmount = edit.getText();

        if (getIncreaseAmount.toString().length() == 0) {

        } else {
            int increaseAmount = Integer.parseInt(getIncreaseAmount.toString());
            amountCheck(increaseAmount);
        }
    }

    public void amountCheck(int increase) {
        totalAmountCount += increase;
        if (amountStatus(totalAmountCount)) {
            //結果画面へ
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("TOTAL_AMOUNT", totalAmountCount);
            startActivity(intent);
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("TOTAL_AMOUNT", totalAmountCount);
            editor.apply();

            TextView totalAmountLabel = findViewById(R.id.totalAmountLabel);

            totalAmountLabel.setText(getString(R.string.appear_amount, totalAmountCount));
            //edittextを空にする処理をここに追加する
            EditText increaseAmount = findViewById(R.id.increaseAmount);
            increaseAmount.getEditableText().clear();
        }
    }

    public boolean amountStatus(int total){
        return (total >= targetAmountAppear) ? true : false;
    }

    /** 戻るボタン押させない */
    @Override
    public void onBackPressed() {
    }
}