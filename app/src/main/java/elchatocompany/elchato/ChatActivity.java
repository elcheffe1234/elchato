package elchatocompany.elchato;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChatActivity extends AppCompatActivity {

    static final int SocketServerPORT = 8080;
    private static ChatActivity ca = new ChatActivity();

    static TextView chatMsg;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private com.google.android.gms.common.api.GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatMsg = (TextView) findViewById(R.id.chatMsg);
        setChatMsg(readFromChatLog());
        //set data for serverconnection
        //ChatClientThread ca = new ChatClientThread(UserModel.getInstance().getUsername(), UserModel.getInstance().getServerip(), UserModel.getSocketServerPORT(),this);
        //UserModel.getInstance().setChatClientThread(ca);
        // ca.start();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new com.google.android.gms.common.api.GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Link to onlickmethod for sendbutton on chatview
     *
     * @param v
     */
    public void sendmessage(View v) {
        EditText message = (EditText) findViewById(R.id.message);
        //set new textview for chatmessages
        String messageLog = getChatMsg().getText().toString();
        String addMessage = messageLog + "\n User - " + UserModel.getInstance().getUsername() + " : " + message.getText().toString();
        chatMsg.setText(addMessage);
        message.setText("");
        // UserModel.getInstance().getChatClientThread().sendMsg(message.getText().toString() + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //disconnect form server
            //UserModel.getInstance().getChatClientThread().disconnect();

            //save in chatlog
            saveInChatLog(getChatMsg().getText().toString(), UserModel.getInstance().getServerip());
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Getter for Chatmessage
     *
     * @return chatMsg
     */
    public static TextView getChatMsg() {
        return chatMsg;
    }

    /**
     * setter text for Chatmessage
     *
     * @param chatMsg
     */
    public static void setChatMsg(String chatMsg) {
        ChatActivity.chatMsg.setText(chatMsg);
    }

    /**
     * read chathistory from file
     *
     * @return all chats
     */
    public String readFromChatLog() {
        String chathistory = "";
        try {
            InputStream inputStream = openFileInput(UserModel.getInstance().getServerip());

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                chathistory = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return chathistory;
    }

    /**
     * save content into file
     *
     * @param chatLog
     * @param filename
     */
    public void saveInChatLog(String chatLog, String filename) {
        FileOutputStream fos = null;
        try {
            //before saving read whole file - avoid overwriting existing filecontent
            String writestring = readFromChatLog();
            writestring += chatLog;
            //write into file
            fos = openFileOutput(filename, this.MODE_PRIVATE);
            fos.write(writestring.getBytes());
            fos.close();
        } catch (IOException e) {
            Toast.makeText(this, "Unable to add new chat!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Chat Page", // TODO: Define a title for the content shown.
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
                "Chat Page", // TODO: Define a title for the content shown.
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
