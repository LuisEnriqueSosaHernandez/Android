package lesh.egresatecnm.com.recyclerandcardview.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lesh.egresatecnm.com.recyclerandcardview.Modelos.Movie;
import lesh.egresatecnm.com.recyclerandcardview.R;


/**
 * Created by pc on 03/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener itemClickListener){
    this.movies=movies;
        this.layout=layout;
        this.itemClickListener=itemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     public TextView textViewName;
        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewName=(TextView)itemView.findViewById(R.id.txt);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
        public void bind(final Movie movie,final OnItemClickListener listener){
            textViewName.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie,getAdapterPosition());
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Movie movie, int position);
    }
}
