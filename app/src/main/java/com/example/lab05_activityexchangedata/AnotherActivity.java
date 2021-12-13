package com.example.lab05_activityexchangedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class AnotherActivity extends AppCompatActivity {

   EditText Aet;
   Boolean fl1,fl2;
   Switch switch_1;
   Switch switch_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Aet = findViewById(R.id.Aet);
        switch_1 = findViewById(R.id.sw_1);
        switch_2 = findViewById(R.id.sw_2);

        Intent x = getIntent();//Получаем передаваемый в эту Activity Intent
        String q = x.getStringExtra("mystring");//Вытаскиваем данные из посылки по имени
        Aet.setText(q);
        fl1 = x.getBooleanExtra("f1", false);
        fl2 = x.getBooleanExtra("f2", false);
        switch_1.setChecked(fl1);
        switch_2.setChecked(fl2);
    }

    public void OnButtonOK_Click(View v)
    {
        Intent i = new Intent(); //this - экземпляр данного класса MainActivity
        String s = Aet.getText().toString();
        i.putExtra("qwe", s); //qwe - название передаваемой строки для дальнейшего извлекания, s - сами данные
        fl1 = switch_1.isChecked();
        fl2 = switch_2.isChecked();
        i.putExtra("flag1", fl1);
        i.putExtra("flag2",fl2);
        setResult(RESULT_OK, i);//Вернуть в ответ новый intent с данными
        finish();
    }

    public void OnCancel_Click(View v)
    {
        setResult(RESULT_CANCELED);
        finish(); //Закрываем вторую Activity
    }



}