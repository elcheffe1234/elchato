package elchatocompany.elchato;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void addUser(View v){
        EditText findUser = (EditText) findViewById(R.id.usernamefinder);
        String user = findUser.getText().toString();
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);

        //if user exists
        if(!sp.getString(user, "").isEmpty()){
           //TODO: add user to listitem in chatview
            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Person not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View v){
        Toast.makeText(this, "Adding person canceled", Toast.LENGTH_SHORT).show();
        finish();
    }
}
