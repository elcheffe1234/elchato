package elchatocompany.elnotizblocko;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import elchatocompany.elchato.R;

public class NoteActivity extends AppCompatActivity {

    EditText area;
    String itemvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        area = (EditText) findViewById(R.id.textfield);
        itemvalue = UserModel.getInstance().getEditFolderName();

        area.setText(readFromFile(itemvalue));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //save note in file

            String wholeText = area.getText().toString();


            saveNoteInNotesFile(wholeText, itemvalue);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void saveNoteInNotesFile(String addedNote, String filename){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, this.MODE_PRIVATE);
            fos.write(addedNote.getBytes());
            fos.close();
        } catch (IOException e){
            Toast.makeText(this, "Unable to add new note!", Toast.LENGTH_SHORT).show();
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

}
