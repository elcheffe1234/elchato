package elchatocompany.elchato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AddActivity extends AppCompatActivity {

    UserModel um;

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
            savePersonInChatsFile(user, UserModel.getInstance().getUsername() + "_chats");

            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ChatsActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, "Person not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View v){
        Toast.makeText(this, "Adding person canceled", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ChatsActivity.class);
        startActivity(i);
        finish();
    }

    public void savePersonInChatsFile(String addedPerson, String filename){
        FileOutputStream fos = null;
        try {
            String writestring = readFromFile(filename);
            if(!writestring.isEmpty()) {
                writestring = writestring + "," + addedPerson;
            }
            else {
                writestring = addedPerson;
            }
            fos = openFileOutput(filename, this.MODE_PRIVATE);
            fos.write(writestring.getBytes());
            fos.close();
        } catch (IOException e){
            Toast.makeText(this, "Unable to add new chat!", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromFile(String filename) {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(this, ChatsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
