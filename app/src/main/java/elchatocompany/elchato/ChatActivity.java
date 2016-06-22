package elchatocompany.elchato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    static final int SocketServerPORT = 8080;
    private static ChatActivity ca = new ChatActivity();

    public static TextView getChatMsg() {
        return chatMsg;
    }

    public static void setChatMsg(TextView chatMsg) {
        ChatActivity.chatMsg = chatMsg;
    }

    static TextView chatMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatMsg = (TextView) findViewById(R.id.chatMsg);
        UserModel.getInstance().getChatClientThread().run();

    }

    public void sendmessage(View v){
        EditText message = (EditText) findViewById(R.id.message);
        if (message.getText().toString().equals("")) {
            return;
        }
        if(UserModel.getInstance().getChatClientThread()==null){
            return;
        }
        UserModel.getInstance().getChatClientThread().sendMsg(message.getText().toString() + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            UserModel.getInstance().getChatClientThread().disconnect();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
