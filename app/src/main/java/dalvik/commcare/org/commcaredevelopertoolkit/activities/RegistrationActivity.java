package dalvik.commcare.org.commcaredevelopertoolkit.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dalvik.commcare.org.commcaredevelopertoolkit.R;

/**
 * Activity to register mobile device with HQ for FCM notifications
 */
public class RegistrationActivity extends AppCompatActivity{

    private String registrationToken;

    private EditText username;
    private EditText password;
    private final int REGISTRATION_REQUEST = 5;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        Button submit = (Button) findViewById(R.id.submit);
        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDevice();

            }
        });
    }

    private void registerDevice() {
        getInstanceId();
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.isEmpty()){
            //TODO: Alert for empty field
            return;
        }

        if(pass.isEmpty()){
            //TODO: Alert for empty field
            return;
        }

        //TODO: Submit info to HQ endpoint, check if submission was successful
        if(registrationToken != null){

        }
    }

    private void getInstanceId() {
        //Using explicit intent
        ComponentName cn = new ComponentName("org.commcare.dalvik", "org.commcare.activities.DeviceRegistrationActivity");
        Intent registrationIntent = new Intent();
        registrationIntent.setComponent(cn);
        startActivityForResult(registrationIntent, REGISTRATION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REGISTRATION_REQUEST){
            if(resultCode == RESULT_OK){
                registrationToken = data.getStringExtra("TOKEN");
                System.out.println("TOKEN: " + registrationToken);
                finish();
            }
        }
        if(resultCode == RESULT_CANCELED){
            TextView response = (TextView) findViewById(R.id.response_text);
            response.setText(R.string.failure);
            response.setVisibility(View.VISIBLE);
        }

    }

}
