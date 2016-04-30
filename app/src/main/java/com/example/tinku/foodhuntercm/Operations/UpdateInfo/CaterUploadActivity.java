package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

/* Import appropriate libraries */
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;
import android.widget.CheckBox;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by sandeep on 4/12/2016.
 */

/* Cater upload activity screen for the application */
public class CaterUploadActivity extends AppCompatActivity {

    /* Variables for handling cater upload activity */
    ImageView iv;
    Button btnCaterUpdate;
    Button btnload;
    CheckBox american, thai, indian, chinese;
    boolean imageset = false;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://www.nativebites.comxa.com/";

    /* Async task for uploading image to server */
    private class UploadImage extends AsyncTask<Void, Void, Void> {

        /* Varibles used for uploading image information */
        Bitmap image;
        String name;
        String uname;

        /* Constructor */
        public UploadImage(Bitmap image, String name, String uname){
            this.name = name;
            this.image = image;
            this.uname = uname;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            /*
             * Post execution of the image upload, start an intent for menu cater activity by
             * passing username information
             */
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            final String username = intent.getStringExtra("username");
            Intent intent2 = new Intent(CaterUploadActivity.this, Menu_CaterActivity.class);
            intent.putExtra("username", username);

            /* Switch to MenuCater Activity here */
            CaterUploadActivity.this.startActivity(intent2);
        }

        @Override
        protected Void doInBackground(Void... params) {
            /* In the background, upload the image */
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            ArrayList<NameValuePair> dataTosend = new ArrayList<>();

            /* Send image data, name and user name information as HTTP request to server */
            dataTosend.add(new BasicNameValuePair("image", encodedImage));
            dataTosend.add(new BasicNameValuePair("name", name));
            dataTosend.add(new BasicNameValuePair("uname", uname));

            HttpParams httpRequestParams = getHttpParamms();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"image.php");
            try{
                post.setEntity(new UrlEncodedFormEntity(dataTosend));
                client.execute(post);
            }
            catch(Exception e){
            }
            return null;
        }

    }

    /* Get HTTP params for image upload request */
    private HttpParams getHttpParamms(){
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
        return httpRequestParams;
    }

    protected void onCreate(Bundle savedInstanceState) {
        /* Create layout for cater upload screen */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        /* Get the ids of the resources */
        iv = (ImageView) findViewById(R.id.ivFood);
        final EditText foodtype,location, foodName, price, contactNumber;
        foodtype = (EditText) findViewById(R.id.etFoodType);
        location = (EditText) findViewById(R.id.etLocation);
        foodName = (EditText) findViewById(R.id.etFood);
        price = (EditText)findViewById(R.id.etPrice);
        contactNumber = (EditText)findViewById(R.id.etContactNumber);
        american = (CheckBox)findViewById(R.id.american);
        indian = (CheckBox)findViewById(R.id.indian);
        chinese = (CheckBox)findViewById(R.id.chinese);
        thai = (CheckBox)findViewById(R.id.thai);
        btnCaterUpdate = (Button)findViewById(R.id.updatebutton);
        /* Get the username from the calling activity */
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");


        /* Listen to cater update button */
        btnCaterUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /* Create string for food type here */
                String foodtype = "/0";
                final String caterLocation = location.getText().toString();
                final String caterFood = foodName.getText().toString();
                final float foodPrice = Float.parseFloat(price.getText().toString());
                final int caterContact = Integer.parseInt(contactNumber.getText().toString());
                final String caterName=username;

                if(american.isChecked())
                    foodtype=foodtype+"/1";
                if(indian.isChecked())
                    foodtype=foodtype+"/2";
                if (thai.isChecked())
                    foodtype=foodtype+"/3";
                if (chinese.isChecked())
                    foodtype=foodtype+"/4";

                Intent intent = getIntent();
                final String username = intent.getStringExtra("username");

                /* If the image is selected from the gallery, upload the image to server here */
                if(imageset) {
                    Bitmap image = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                    new UploadImage(image, foodName.getText().toString(), username).execute();
                }

                /*
                 * Build entity is an abstraction created from application
                 * perspective which hides the details about implementation
                 * of requests. This way user has to just has to call the
                 * APIs provided to them with proper parameters.
                 */
                BuildEntity usr = new BuildEntity();
                usr.updateCaterInfo(username, foodtype, caterLocation, caterFood, foodPrice, caterContact, imageset, getApplicationContext());
            }
        });

        /* Listener for starting Gallery intent */
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              /* If the user clicks on food resource then start the activity */
              if(v.getId() == R.id.ivFood){
                  Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                  startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
              }
            }
        });
    }

    /* On Gallery activity result, check the result code and set the image view in the screen */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            iv.setImageURI(selectedImage);
            imageset = true;
        }
    }
}


