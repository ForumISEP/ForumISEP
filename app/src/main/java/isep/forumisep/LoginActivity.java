package isep.forumisep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by linfengwang on 21/12/2017.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etName = (EditText) findViewById(R.id.etName);

        //set button click listener;
        Button btn =(Button) findViewById(R.id.btnlogin);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String name= etName.getText().toString();
                String passwork= etPassword.getText().toString();

                //when name and password is not empty,go to RecyclerActivity;
                if(name.trim().length() >0 && passwork.trim().length() >0){

                    Intent registerIntent = new Intent(LoginActivity.this, RecyclerActivity.class);
                    LoginActivity.this.startActivity(registerIntent);

                }else{

                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Login failed")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();

                }
            }
        });
    }
}
