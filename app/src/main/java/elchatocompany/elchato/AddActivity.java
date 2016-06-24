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

    /**
     * Add serveraddress to chat
     * @param v
     */
    public void addAdress(View v){
        EditText findAdress = (EditText) findViewById(R.id.usernamefinder);
        String adress = findAdress.getText().toString();
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);

        //if address is empty
        if(!adress.isEmpty()){
            savePersonInChatsFile(adress, UserModel.getInstance().getUsername() + "_chats");
            Toast.makeText(this, "Chatroom added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ChatsActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, "Chatroom can not be empty", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * cancel button pressed
     * @param v
     */
    public void cancel(View v){
        Toast.makeText(this, "Adding chat canceled", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ChatsActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * save content into file
     * @param addedPerson
     * @param filename
     */
    public void savePersonInChatsFile(String addedPerson, String filename){
        FileOutputStream fos = null;
        try {
            //before saving read whole file - avoid overwriting existing filecontent
            String writestring = readFromFile(filename);
            if(!writestring.isEmpty()) {
                writestring = writestring + "," + addedPerson;
            }
            else {
                writestring = addedPerson;
            }
            //write into file
            fos = openFileOutput(filename, this.MODE_PRIVATE);
            fos.write(writestring.getBytes());
            fos.close();
        } catch (IOException e){
            Toast.makeText(this, "Unable to add new chat!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * read content from file
     * @param filename
     * @return filecontent
     */
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

    /**
     * when back button on phone was pressed
     * @param keyCode
     * @param event
     * @return last intent
     */
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
