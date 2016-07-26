package com.example.ivan.secretnotes;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import dbutils.DButil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("POSITION", "MainActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DButil dbutil = new DButil(this);

        if (dbutil.userExists(null)) {
            //user exists, create a login screen
            Log.d("INFO", "User exists.");
        } else {
            //user does not exists, create a register new user screen
            Log.d("INFO", "User does NOT eists");

            final EditText editPwd = new EditText(this);
            editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editPwd.setHint("New password");

            final EditText editRepeatPwd = new EditText(this);
            editRepeatPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editRepeatPwd.setHint("Repeat password");


            Button btnRegister = new Button(this);
            btnRegister.setText("Register");
            btnRegister.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {


                    String pwd = editPwd.getText().toString();
                    String repeatPwd = editRepeatPwd.getText().toString();

                    if (pwd.length() < 4) {
                        Toast.makeText(view.getContext(),
                                "Minimum password length is 4.", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    if (pwd.equals(repeatPwd) == false) {
                        Toast.makeText(view.getContext(),
                                "Repeat password does not match.", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    Toast.makeText(view.getContext(),
                            "Button registered clicked", Toast.LENGTH_SHORT)
                            .show();
                }
            });

            LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            ll.addView(editPwd, lp);
            ll.addView(editRepeatPwd, lp);
            ll.addView(btnRegister, lp);
        }

    }
}
