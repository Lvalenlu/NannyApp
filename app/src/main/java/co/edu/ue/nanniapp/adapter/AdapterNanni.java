package co.edu.ue.nanniapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.ue.nanniapp.NanniDetails;
import co.edu.ue.nanniapp.NanniProfile;
import co.edu.ue.nanniapp.R;
import co.edu.ue.nanniapp.remote.Nanni;

public class AdapterNanni extends RecyclerView.Adapter<AdapterNanni.vh> implements View.OnClickListener{
    List<Nanni> nanniList;
    private View.OnClickListener listener;
    public AdapterNanni(List<Nanni> pokemonList){this.nanniList = pokemonList;}
    @NonNull
    @Override
    public AdapterNanni.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parents_nanni_view,null,false);
        view.setOnClickListener(this);
        return new vh(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterNanni.vh holder, int position) {
        holder.txtName.setText(nanniList.get(position).getName());
        holder.txtLocation.setText(nanniList.get(position).getLocation());
        holder.txtSalary.setText(nanniList.get(position).getSalary());
    }
    @Override
    public int getItemCount() {
        return nanniList.size();
    }
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.listener = onClickListener;
    }
    public class vh extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtLocation;
        TextView txtSalary;
        public vh(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvNameNanni);
            txtLocation = itemView.findViewById(R.id.tvLocationNanni);
            txtSalary = itemView.findViewById(R.id.tvSalaryNanni);
        }
    }
}
