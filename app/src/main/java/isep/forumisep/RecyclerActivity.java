package isep.forumisep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linfengwang on 21/12/2017.
 */

public class RecyclerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Entreprise> entrepriseList = new ArrayList<>();
    private RecyclerActivity recyclerActivity;
    private EntrepriseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_recycler);


    }
}
