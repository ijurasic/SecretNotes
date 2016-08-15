package ijura.software.secretnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import utils.DButil;


public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    DButil dbutil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("POSITION", "MainActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(ijura.software.secretnotes.R.layout.activity_main);

        dbutil = new DButil(this);


        ll = (LinearLayout) findViewById(ijura.software.secretnotes.R.id.linearLayout);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (dbutil.userExists(null)) {
            //user exists, create a login screen
            //Log.d("INFO", "User exists.");
            createLayoutLogin();
        } else {
            //user does not exists, create a register new user screen
            //Log.d("INFO", "User does NOT eists");
            createLayoutNewUser();
        }

    }


    public void createLayoutNewUser() {

        /*clear current layout*/
        ll.removeAllViews();

        /*adding field for creating a new user*/
        final EditText editPwd = new EditText(this);
        editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPwd.setHint("New password");

        final EditText editRepeatPwd = new EditText(this);
        editRepeatPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editRepeatPwd.setHint("Repeat password");


        Button btnRegister = new Button(this);
        btnRegister.setBackgroundResource(android.R.color.holo_blue_dark);
        btnRegister.setText("Create user");
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
                if (pwd.length() > 10) {
                    Toast.makeText(view.getContext(),
                            "Maximum password length is 10.", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (pwd.equals(repeatPwd) == false) {
                    Toast.makeText(view.getContext(),
                            "Repeat password does not match.", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                /*for now, app supports only one user, so I put USER as a default username*/
                dbutil.createUser("USER", pwd);
                Toast.makeText(view.getContext(),
                        "User created.", Toast.LENGTH_SHORT)
                        .show();
                createLayoutLogin();


            }
        });


        ll.addView(editPwd, lp);
        ll.addView(editRepeatPwd, lp);
        ll.addView(btnRegister, lp);

        /*this lines are necessary to show keyboard on the screen*/
        if (editPwd.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }



    }


    public void createLayoutLogin() {

        /*clear current layout*/
        ll.removeAllViews();

        /*adding field for login*/
        final EditText editPwd = new EditText(this);
        editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPwd.setHint("Password");


        Button btnLogin = new Button(this);
        btnLogin.setBackgroundResource(android.R.color.holo_blue_dark);
        btnLogin.setText("Login");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String pwd = editPwd.getText().toString();

                if (pwd.length() < 4) {
                    Toast.makeText(view.getContext(),
                            "Minimum password length is 4.", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }


                if (dbutil.loginUser("USER", pwd)) {
                    startNotesActivity();
                    editPwd.setText("");
                } else {
                    Toast.makeText(view.getContext(),
                            "Incorrect password.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });



        ll.addView(editPwd, lp);
        ll.addView(btnLogin, lp);


        /*this lines are necessary to show keyboard on the screen*/
        if (editPwd.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


    }

    public void startNotesActivity() {
        startActivity(new Intent(MainActivity.this, NotesActivity.class));
    }
}
