package isep.forumisep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by linfengwang on 26/12/2017.
 */

public class GalleryActivity extends AppCompatActivity {
    ImageView im;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_perm_identity_black_24dp);



        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(GalleryActivity.this, MainActivity.class);
                GalleryActivity.this.startActivity(registerIntent);
            }
        });
    }

    public void biggerView (View v)
    {
        im=(ImageView)findViewById(R.id.selected);

        switch (v.getId())
        {
            case R.id.image1: im.setImageResource(R.drawable.ic_face_black_48dp);
                break;
            case R.id.image2: im.setImageResource(R.drawable.ic_face_black_48dp);
                break;
        }
    }
}
