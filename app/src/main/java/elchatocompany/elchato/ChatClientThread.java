package elchatocompany.elchato;

import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientThread extends ChatActivity implements Runnable{
    String msgLog = "";
    String name;
    String dstAddress;
    int dstPort;

    String msgToSend = "";
    boolean goOut = false;

    ChatClientThread(String name, String address, int port) {
        this.name = name;
        dstAddress = address;
        dstPort = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            dataOutputStream = new DataOutputStream(
                    socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF(name);
            dataOutputStream.flush();

            while (!goOut) {
                if (dataInputStream.available() > 0) {
                    msgLog += dataInputStream.readUTF();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ChatActivity.chatMsg.setText(msgLog);
                        }
                    });
                }

                if(!msgToSend.equals("")){
                    dataOutputStream.writeUTF(msgToSend);
                    dataOutputStream.flush();
                    msgToSend = "";
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            final String eString = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            final String eString = e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public void sendMsg(String msg){
        msgToSend = msg;
    }

    public void disconnect(){
        goOut = true;
    }
}

