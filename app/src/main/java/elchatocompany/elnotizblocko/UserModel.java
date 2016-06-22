package elchatocompany.elnotizblocko;

import java.util.Vector;

/**
 * Created by Daniel on 21.06.2016.
 */
public class UserModel {

    private static UserModel userModel = new UserModel();

    private String username;
    private Vector<String> noteFiles;
    private Vector<String> notes;

    public String getEditFolderName() {
        return editFolderName;
    }

    public void setEditFolderName(String editFolderName) {
        this.editFolderName = editFolderName;
    }

    private String editFolderName;

    public UserModel(){

    }

    public Vector<String> getContacts() {
        return noteFiles;
    }

    public void setContacts(Vector<String> noteFiles) {
        this.noteFiles = noteFiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Vector<String> getChats() {
        return notes;
    }

    public void setChats(Vector<String> notes) {
        this.notes = notes;
    }

    public void getData(){

    }

    public static UserModel getInstance( ) {
        return userModel;
    }
}
