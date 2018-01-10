package isep.forumisep;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by linfengwang on 21/12/2017.
 */

public class EntrepriseAdapter extends RecyclerView.Adapter<EntrepriseAdapter.MyViewHolder> {

    private List<Entreprise> entrepriseList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,date, etat;

        public MyViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            etat = (TextView) view.findViewById(R.id.etat);
        }
    }

    public EntrepriseAdapter(List <Entreprise> entrepriseList){
        this.entrepriseList = entrepriseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup entreprise, int viewType){
        View itemView = LayoutInflater.from(entreprise.getContext())
                .inflate(R.layout.entreprise_list,entreprise,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        Entreprise entreprise = entrepriseList.get(position);
        holder.name.setText(entreprise.getName());
        holder.date.setText(entreprise.getDate());
        holder.etat.setText(entreprise.getEtat());
    }

    @Override
    public int getItemCount() {

        return entrepriseList.size();
    }
}


































