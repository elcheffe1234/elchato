package elchatocompany.elchato;

import java.util.Vector;

/**
 * Created by Daniel on 21.06.2016.
 */
public class UserModel {
    public static int getSocketServerPORT() {
        return SocketServerPORT;
    }

    static final int SocketServerPORT = 8080;
    private static UserModel userModel = new UserModel();

    private String username;
    private Vector<String> contacts;
    private Vector<String> chats;

    public void setChatClientThread(ChatClientThread chatClientThread) {
        this.chatClientThread = chatClientThread;
    }

    private ChatClientThread chatClientThread;

    public UserModel(){

    }

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

    public void getData(){

    }

    public ChatClientThread getChatClientThread(){
        return chatClientThread;
    }

    public static UserModel getInstance( ) {
        return userModel;
    }

}
