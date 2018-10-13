package com.heroes.lesh.kidneys.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.R;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_follow_follows extends RecyclerView.Adapter<Adapter_follow_follows.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<Follow> follows;
    private ArrayList<Follow> followsFilter;
    private int layout;
    private Adapter_follow_follows.OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;

    public Adapter_follow_follows(ArrayList<Follow> follows, int layout, Adapter_follow_follows.OnItemClickListener listener) {
        this.follows = follows;
        this.layout = layout;
        this.listener = listener;
        this.followsFilter = new ArrayList<Follow>();
        this.followsFilter.addAll(follows);
        this.mFilter = new CustomFilter(Adapter_follow_follows.this);
    }

    @NonNull
    @Override
    public Adapter_follow_follows.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_follow_follows.ViewHolder vh = new Adapter_follow_follows.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_follow_follows.ViewHolder holder, int position) {
        holder.bind(followsFilter.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return followsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //clase viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewDateFollowFollows)
        TextView textViewDateFollowFollows;
        @BindView(R.id.floatingDeleteFollowFollows)
        FloatingActionButton floatingDeleteFollowFollows;
        @BindView(R.id.card_view_follow_follows)
        CardView card_view_follow_follows;
        @BindColor(R.color.colorVerde)
        int colorVerde;
        @BindColor(R.color.colorMarron)
        int colorMarron;
        @BindColor(R.color.colorRojo)
        int colorRojo;
        private int idFollow;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Follow follow, final Adapter_follow_follows.OnItemClickListener listener) {
            //usamos la libreria de picasso para el tratamiento de imagenes
            idFollow = follow.getIdFollow();
            textViewDateFollowFollows.setText(follow.getDate());
            switch (follow.getHealthRangeName()) {
                case "Healthy":
                    card_view_follow_follows.setCardBackgroundColor(colorVerde);
                    break;
                case "Medium":
                    card_view_follow_follows.setCardBackgroundColor(colorMarron);
                    break;
                case "Harmful":
                    card_view_follow_follows.setCardBackgroundColor(colorRojo);
                    break;
            }
            floatingDeleteFollowFollows.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFloatingClick(idFollow);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(follow, getAdapterPosition());
                }
            });
        }
    }

    //creamos una clase de interfaz para el onitemclick
    public interface OnItemClickListener {
        void onItemClick(Follow follow, int position);

        void onFloatingClick(int idFollow);
    }

    //Clase para filtrar las views
    public class CustomFilter extends Filter {
        private Adapter_follow_follows adapter_follow_follows;

        public CustomFilter(Adapter_follow_follows adapter_follow_follows) {
            super();
            this.adapter_follow_follows = adapter_follow_follows;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            followsFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                followsFilter.addAll(follows);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Follow follow : follows) {
                    if (follow.getDate().toLowerCase().contains(filterPattern)) {
                        followsFilter.add(follow);
                    }
                }
            }
            results.values = followsFilter;
            results.count = followsFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_follow_follows.notifyDataSetChanged();
        }
    }
}
