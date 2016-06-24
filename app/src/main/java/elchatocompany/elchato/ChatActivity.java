package elchatocompany.elchato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    static final int SocketServerPORT = 8080;
    private static ChatActivity ca = new ChatActivity();

    static TextView chatMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatMsg = (TextView) findViewById(R.id.chatMsg);

        //set data for serverconnection
        //ChatClientThread ca = new ChatClientThread(UserModel.getInstance().getUsername(), UserModel.getInstance().getServerip(), UserModel.getSocketServerPORT(),this);
        //UserModel.getInstance().setChatClientThread(ca);
        // ca.start();
    }

    /**
     * Link to onlickmethod for sendbutton on chatview
     * @param v
     */
    public void sendmessage(View v){
        EditText message = (EditText) findViewById(R.id.message);
        //set new textview for chatmessages
        String messageLog = getChatMsg().getText().toString();
        String addMessage = messageLog + "\n User - " + UserModel.getInstance().getUsername() + " : \n" + message.getText().toString();
        chatMsg.setText(addMessage);
       // UserModel.getInstance().getChatClientThread().sendMsg(message.getText().toString() + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //disconnect form server
            //UserModel.getInstance().getChatClientThread().disconnect();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Getter for Chatmessage
     * @return chatMsg
     */
    public static TextView getChatMsg() {
        return chatMsg;
    }

    /**
     * setter text for Chatmessage
     * @param chatMsg
     */
    public static void setChatMsg(String chatMsg) {
        ChatActivity.chatMsg.setText(chatMsg);
    }
}
