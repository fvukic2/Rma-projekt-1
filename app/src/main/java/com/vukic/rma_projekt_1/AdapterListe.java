package com.vukic.rma_projekt_1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {
    private List<results> results;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public AdapterListe(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setResults(List<results> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public Red onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.red_liste,parent,false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Red holder, int position) {
        results results = this.results.get(position);
        System.out.println("---------------------------------------------------------------------------->"+results.getOriginal_title());
        holder.original_title.setText(results.getOriginal_title());
        holder.poster_path.setImageURI(Uri.parse(results.getBackdrop_path()));
        holder.release_date.setText("RELEASE_DATE: " +results.getRelease_date());
        System.out.println("???????????????????????????????????????????" + results.getOriginal_title());
        System.out.println("?????????????????????????????????????????????????" + results.getOriginal_title());
    }

    @Override
    public int getItemCount() {

        if (results ==null){
            return 0;
        }
        else
        {
            return results.size();
        }
        //return films == null ? 0 : films.size();
    }

    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView original_title;
        private ImageView poster_path;
        private TextView release_date;

        public Red(@NonNull View itemView) {
            super(itemView);
            original_title = itemView.findViewById(R.id.original_title);
            poster_path = itemView.findViewById(R.id.poster_path);
            release_date = itemView.findViewById(R.id.textViewrelease_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickInterface==null){
                return;
            }
            itemClickInterface.onItemClick(results.get(getAdapterPosition()));
        }
    }

    public interface ItemClickInterface{
        void onItemClick(results results);
    }

    public void setItemClickInterface(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }
}
