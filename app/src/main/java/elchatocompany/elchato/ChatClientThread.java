package elchatocompany.elchato;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientThread extends Thread{
    String msgLog = "";
    String name;
    String dstAddress;
    int dstPort;
    ChatActivity ca;

    String msgToSend = "";
    boolean goOut = false;

    ChatClientThread(String name, String address, int port, ChatActivity chatActivity) {
        this.name = name;
        dstAddress = address;
        dstPort = port;
        ca = chatActivity;
    }

    @Override
    public void run() {
      while (!goOut) {

                    ca.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ChatActivity.chatMsg.setText(msgLog);
                        }
                    });
                }

                if(!msgToSend.equals("")){
                    ChatActivity.setChatMsg(msgToSend);
                    msgToSend = "";
                }
            }


    public void sendMsg(String msg){
        msgToSend = msg;
    }

    public void disconnect(){
        goOut = true;
    }
}

