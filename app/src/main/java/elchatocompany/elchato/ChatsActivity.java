package elchatocompany.elchato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
    }

    public void addChat(View v){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}
