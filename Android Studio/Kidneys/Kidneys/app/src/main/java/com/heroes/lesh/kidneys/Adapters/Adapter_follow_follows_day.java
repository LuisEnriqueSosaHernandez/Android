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

import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.R;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_follow_follows_day extends RecyclerView.Adapter<Adapter_follow_follows_day.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<FollowsDayApi> followsDayApis;
    private ArrayList<FollowsDayApi> followsDayApisFilter;
    private int layout;
    private Adapter_follow_follows_day.OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;
    private String healthRangeName;

    public Adapter_follow_follows_day(ArrayList<FollowsDayApi> followsDayApis, int layout,String healthRangeName, Adapter_follow_follows_day.OnItemClickListener listener) {
        this.followsDayApis = followsDayApis;
        this.layout = layout;
        this.listener = listener;
        this.followsDayApisFilter = new ArrayList<FollowsDayApi>();
        this.followsDayApisFilter.addAll(followsDayApis);
        this.mFilter = new CustomFilter(Adapter_follow_follows_day.this);
        this.healthRangeName=healthRangeName;
    }

    @NonNull
    @Override
    public Adapter_follow_follows_day.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_follow_follows_day.ViewHolder vh = new Adapter_follow_follows_day.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_follow_follows_day.ViewHolder holder, int position) {
        holder.bind(followsDayApisFilter.get(position),healthRangeName, listener);
    }

    @Override
    public int getItemCount() {
        return followsDayApisFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //clase viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewHourFollowFollowsDay)
        TextView textViewHourFollowFollowsDay;
        @BindView(R.id.floatingDeleteFollowFollowsDay)
        FloatingActionButton floatingDeleteFollowFollowsDay;
        @BindView(R.id.card_view_follow_follows_day)
        CardView card_view_follow_follows_day;
        @BindColor(R.color.colorVerde)
        int colorVerde;
        @BindColor(R.color.colorMarron)
        int colorMarron;
        @BindColor(R.color.colorRojo)
        int colorRojo;
        private int idFollowDay;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final FollowsDayApi followsDayApi,String healthRangeName, final Adapter_follow_follows_day.OnItemClickListener listener) {
            //usamos la libreria de picasso para el tratamiento de imagenes
            idFollowDay = followsDayApi.getIdFollowDay();
            textViewHourFollowFollowsDay.setText(followsDayApi.getStart());
            switch (healthRangeName) {
                case "Healthy":
                    card_view_follow_follows_day.setCardBackgroundColor(colorVerde);
                    break;
                case "Medium":
                    card_view_follow_follows_day.setCardBackgroundColor(colorMarron);
                    break;
                case "Harmful":
                    card_view_follow_follows_day.setCardBackgroundColor(colorRojo);
                    break;
            }
            floatingDeleteFollowFollowsDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFloatingClick(idFollowDay);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(followsDayApi, getAdapterPosition());
                }
            });
        }
    }

    //creamos una clase de interfaz para el onitemclick
    public interface OnItemClickListener {
        void onItemClick(FollowsDayApi followsDayApi, int position);

        void onFloatingClick(int idFollow);
    }

    //Clase para filtrar las views
    public class CustomFilter extends Filter {
        private Adapter_follow_follows_day adapter_follow_follows_day;

        public CustomFilter(Adapter_follow_follows_day adapter_follow_follows_day) {
            super();
            this.adapter_follow_follows_day = adapter_follow_follows_day;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            followsDayApisFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                followsDayApisFilter.addAll(followsDayApis);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final FollowsDayApi followsDayApi : followsDayApis) {
                    if (followsDayApi.getStart().toLowerCase().contains(filterPattern)) {
                        followsDayApisFilter.add(followsDayApi);
                    }
                }
            }
            results.values = followsDayApisFilter;
            results.count = followsDayApisFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_follow_follows_day.notifyDataSetChanged();
        }
    }
}
