package com.example.tinku.foodhuntercm.Operations.JoinCommunity;

/* Import appropriate libraries */
import com.example.tinku.foodhuntercm.R;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;


/* Login Activity is default start screen for this application */
public class LoginActivity extends AppCompatActivity {

    /* Variables for handling login activity */
    ImageView iv;
    Button btlogin;
    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create layout of Login screen */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get the ids of the resources */
        iv=(ImageView)findViewById(R.id.imageview);
        iv.setImageResource(R.drawable.logo);
        btlogin= (Button)findViewById(R.id.btnLogin);
        etUsername=(EditText)findViewById(R.id.username);
        etPassword=(EditText)findViewById(R.id.pwd);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        /* The user can choose to register if he is a first time user of application */
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* On click of register, start an Intent to switch to register user screen */
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });


        /* Listen to click event on Login button */
        btlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                /*
                 * On click of login button, start login user process
                 * by having username and password from the screen
                 */
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                /*
                 * Build entity is an abstraction created from application
                 * perspective which hides the details about implementation
                 * of requests. This way user has to just has to call the
                 * APIs provided to them with proper parameters.
                 */
                BuildEntity entity = new BuildEntity();
                entity.loginUser(username, password, getApplicationContext());
            };
        });
    }
}