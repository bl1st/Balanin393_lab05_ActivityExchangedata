package com.example.lab05_activityexchangedata;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Balanin393 Lab 05 Activity Exchange
    EditText et;
    AlertDialog.Builder dlg;
    CheckBox check_1;
    CheckBox check_2;
    Boolean flag1, flag2;
    Intent i;
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Создание экземпляра класса Activity
        et = findViewById(R.id.et);
        check_1=findViewById(R.id.chk_1);
        check_2=findViewById(R.id.chk_2);
    }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      Log.e("TEST", " Method onActivity result initializing");
    if (requestCode == 555) {
        if (data !=null) {
            String a = data.getStringExtra("qwe");
            Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
            et.setText(a);
            flag1 = data.getBooleanExtra("flag1", false);
            flag2 = data.getBooleanExtra("flag2", false);
            check_1.setChecked(flag1);
            check_2.setChecked(flag2);
        }
    }
      super.onActivityResult(requestCode, resultCode, data);
  }

    public void OnButton_click(View v) {
        String s = et.getText().toString();
        i = new Intent(this, AnotherActivity.class); //this - экземпляр данного класса MainActivity
        i.putExtra("mystring",s); //mystring - название передаваемой строки для дальнейшего извлекания, s - сами данные
        flag1 = check_1.isChecked();
        flag2 = check_2.isChecked();
        i.putExtra("f1", flag1);
        i.putExtra("f2",flag2);
        startActivityForResult(i, 555); //передаем второй activity данные и ждем от нее код 555
    }

    public void OnButtonExit_Click(View v) {//Creating alertDialog when trying to exit main activity
        dlg = new AlertDialog.Builder(this);
        LayoutInflater myLayout = LayoutInflater.from(this);
        dlg.setTitle("Exit");
        dlg.setCancelable(true);
        dlg.setMessage("Are you sure you want to exit?");
        dlg.setIcon(R.drawable.picturus);
        dlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                }
        );
        dlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                      finish();// Закрываем Activity
                   }
               }
             );
        dlg.create().show();
    }

    public void OnButtonShowDialog_Click(View v) {//Creating dialog window
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setCancelable(true);
        EditText editText = new EditText(this);
        editText.setMinWidth(100);
        LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setOrientation(LinearLayout.VERTICAL);
        LinearLayout layout1 = new LinearLayout(this);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout1.addView(editText);
        Button b1 = new Button(this);
        Button b2 = new Button(this);
        b1.setText("OK");
        b2.setText("Cancel");
        AlertDialog ad = d.create();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String s = editText.getText().toString();
            et.setText(s);
            ad.cancel();
            return;
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.cancel();
            }
        });
        layout2.addView(b1);
        layout2.addView(b2);
        layoutMain.addView(layout1);
        layoutMain.addView(layout2);
        ad.setView(layoutMain);
        ad.show();
    }

}