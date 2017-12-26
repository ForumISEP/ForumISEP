package isep.forumisep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by linfengwang on 25/12/2017.
 */

public class LoginStudent extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        final EditText stuName = (EditText)findViewById(R.id.stuName);
        final EditText stuPassword = (EditText)findViewById(R.id.stuPassword);


        Button btnStuLogin = (Button)findViewById(R.id.btnStuLogin);

        btnStuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stuNameString = stuName.getText().toString();
                String stuPasswordString = stuPassword.getText().toString();

                if (stuNameString.trim().length() > 0 && stuPasswordString.trim().length() > 0) {

                    if (stuNameString.equals("test") && stuPasswordString.equals("test")) {

                        Intent registerIntent = new Intent(LoginStudent.this, RecyclerActivity.class);
                        LoginStudent.this.startActivity(registerIntent);

                    } else {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginStudent.this);
                        builder.setMessage("Login failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                }
            }
       });
}}