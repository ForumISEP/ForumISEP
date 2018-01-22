package isep.forumisep.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import isep.forumisep.R;

public class HomeActivity extends AppCompatActivity {

    Button btnStudient,btnEntreprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnStudient = (Button) findViewById(R.id.btnStudent);
        btnEntreprise = (Button) findViewById(R.id.btnEntreprise);

        btnStudient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent studentIntent = new Intent(getApplicationContext(),Echiquier_VuePersonnalisee.class);
                startActivity(studentIntent);
            }
        });

        btnEntreprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent entrepriseIntent = new Intent(getApplicationContext(),LoginEntreprise.class);
                startActivity(entrepriseIntent);
            }
        });
    }
}
