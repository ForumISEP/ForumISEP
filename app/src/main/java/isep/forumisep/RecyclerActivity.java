package isep.forumisep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linfengwang on 21/12/2017.
 */

public class RecyclerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Entreprise> entrepriseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EntrepriseAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(RecyclerActivity.this, LoginStudent.class);
                RecyclerActivity.this.startActivity(registerIntent);
            }
        });


        //fill in the item;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        myAdapter = new EntrepriseAdapter(entrepriseList);

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener(){
           @Override
            public void onClick(View view,int position){
               Entreprise entreprise = entrepriseList.get(position);
               Toast.makeText(getApplicationContext(), entreprise.getName() + "is chosen ", Toast.LENGTH_LONG).show();
               Intent thirdActivity = new Intent(RecyclerActivity.this, MainActivity.class);
               startActivity(thirdActivity);
           }

           @Override
            public void onLongClick(View view,int position){
           }
        }));
        recyclerView.setAdapter(myAdapter);
        prepareEntrepriseData();
    }

    private void prepareEntrepriseData(){
        Entreprise entreprise0 = new Entreprise ("FaceBook","software","2014");
        entrepriseList.add(entreprise0);

        Entreprise entreprise1 = new Entreprise ("Apple","software","2000");
        entrepriseList.add(entreprise1);

        Entreprise entreprise2 = new Entreprise ("IBM","software","2000");
        entrepriseList.add(entreprise2);

        Entreprise entreprise3 = new Entreprise ("Tweeter","software engineer","2000");
        entrepriseList.add(entreprise3);

        myAdapter.notifyDataSetChanged();
    }

    public interface ClickListener{
        void onClick(View view,int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private RecyclerActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerActivity.ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
               @Override
                public boolean onSingleTapUp(MotionEvent e){
                   return true;
               }

               @Override
                public void onLongPress(MotionEvent e){
                   View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                   if(child != null && clickListener != null){
                       clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                   }
               }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv,MotionEvent e){
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e){}

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){}

    }

}
