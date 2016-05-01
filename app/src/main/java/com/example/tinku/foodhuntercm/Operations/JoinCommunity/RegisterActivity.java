package com.example.tinku.foodhuntercm.Operations.JoinCommunity;

/* Import appropriate libraries */
import com.example.tinku.foodhuntercm.Exceptions.AppException;
import com.example.tinku.foodhuntercm.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;


/* Register Activity screen for the application */
public class RegisterActivity extends AppCompatActivity {

    /* Variables for handling Register activity */
    ImageView iv;
    Button regbutton;
    EditText etName,etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create layout of Register screen */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        /* Get the ids of the resources */
        try {
            iv = (ImageView) findViewById(R.id.imageview);
            if(iv == null){
                throw  new AppException(1, "Missing image data");
            }
            iv.setImageResource(R.drawable.logo);
        }
        catch(AppException e){
            e.genericexceptionfix();
        }

        etName=(EditText)findViewById(R.id.reg_fullname);
        etEmail=(EditText)findViewById(R.id.reg_email);
        etPassword=(EditText)findViewById(R.id.reg_password);
        regbutton=(Button) findViewById(R.id.registerbutton);

        /* Listen to click on register button */
        regbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                /*
                 * On click of register button, start register user process
                 * by having username, email,password and user type from
                 * the screen
                 */
                final String username = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                try {
                    Spinner mySpinner=(Spinner) findViewById(R.id.spinner2);
                    if(mySpinner == null){
                        throw  new AppException(1, "Missing spinner information");
                    }
                    TextView textView = (TextView)mySpinner.getSelectedView();
                    final String spinnerType = textView.getText().toString();
                     /*
                      * Build entity is an abstraction created from application
                      * perspective which hides the details about implementation
                      * of requests. This way user has to just has to call the
                      * APIs provided to them with proper parameters.
                      */
                    BuildEntity usr = new BuildEntity();
                    usr.registerUser(username, password, email,spinnerType,getApplicationContext());
                }
                catch(AppException e){
                    e.genericexceptionfix();
                }


            }
        });
    }
}
