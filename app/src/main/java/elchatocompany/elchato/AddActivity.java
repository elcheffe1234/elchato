package elchatocompany.elchato;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

            savePersonInChatsFile(user, "actual_user_chat");

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

    public void savePersonInChatsFile(String addedPerson, String filename){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, this.MODE_PRIVATE);
            fos.write(addedPerson.getBytes());
            fos.close();
        } catch (IOException e){
            Toast.makeText(this, "Unable to add new chat!", Toast.LENGTH_SHORT).show();
        }
    }
}
