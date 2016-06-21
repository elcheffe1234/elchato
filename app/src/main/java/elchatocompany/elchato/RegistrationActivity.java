package elchatocompany.elchato;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sp = PreferenceManager
                .getDefaultSharedPreferences(this);
    }

    public void create(View v){
        EditText email = (EditText) findViewById(R.id.email);
        EditText user = (EditText) findViewById(R.id.user);
        EditText password = (EditText) findViewById(R.id.password);

        if(sp.getString(user.getText().toString(), "").isEmpty()) {
            save(user.getText().toString(), password.getText().toString());
            save(user.getText().toString() + "_email", email.getText().toString());
            finish();
        }
        else{
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
        }
    }

    public void save(String key, String value) {


        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();

    }

}
