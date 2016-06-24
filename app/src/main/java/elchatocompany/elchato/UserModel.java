package elchatocompany.elchato;

import java.util.Vector;

/**
 * Created by Daniel on 21.06.2016.
 */
public class UserModel {

    /**
     * MODELCLASS FOR DATAS
     */
    static final int SocketServerPORT = 8080;
    private String serverip;
    private static UserModel userModel = new UserModel();
    private ChatClientThread chatClientThread;
    private String username;
    private Vector<String> contacts;
    private Vector<String> chats;

    public UserModel(){}
    public Vector<String> getContacts() {
        return contacts;
    }
    public void setContacts(Vector<String> contacts) {
        this.contacts = contacts;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Vector<String> getChats() {
        return chats;
    }
    public void setChats(Vector<String> chats) {
        this.chats = chats;
    }
    public void getData(){}
    public static UserModel getInstance( ) { return userModel;}
    public String getServerip() { return serverip;}
    public void setServerip(String serverip) { this.serverip = serverip;}
    public static int getSocketServerPORT() {
        return SocketServerPORT;
    }
    public void setChatClientThread(ChatClientThread chatClientThread) {this.chatClientThread = chatClientThread;}
    public ChatClientThread getChatClientThread(){return chatClientThread;}

}
