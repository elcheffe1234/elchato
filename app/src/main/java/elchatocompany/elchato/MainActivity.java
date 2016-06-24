package elchatocompany.elchato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private Button login;
    private Button registr;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private UserModel um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * initialize login for chat
     */
    public void init() {

        //Zuweisung textviews
        username = (TextView) findViewById(R.id.user);
        password = (TextView) findViewById(R.id.password);

        //Zuweisung buttons
        login = (Button) findViewById(R.id.login);
        registr = (Button) findViewById(R.id.registr);
    }

    /**
     * open new register intent
     * @param v
     */
    public void register(View v){
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }

    /**
     * check for login
     * @param v
     */
    public void login(View v){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String logintext = "Login failed";

        //user && pass are not empty
        if(!user.isEmpty() && !pass.isEmpty()) {
            SharedPreferences sp = PreferenceManager
                    .getDefaultSharedPreferences(this);
            try {
                //search user in shared preferences
                String sharedPassword = sp.getString(user, "");
                //when user exists (user is the key for the preferences) - check for the password
                if (pass.equals(sharedPassword)) {
                    UserModel.getInstance().setUsername(user);
                    logintext = "Welcome " + user;
                    Intent i = new Intent(this, ChatsActivity.class);
                    startActivity(i);
                    Toast.makeText(this, logintext, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    //user does not exists || password is wrong
                    Toast.makeText(this, logintext, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, logintext, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, logintext, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * when backbutton is pressed
     */
    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://elchatocompany.elchato/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://elchatocompany.elchato/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
