package com.heroes.lesh.kidneys.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_treatments_guides extends RecyclerView.Adapter<Adapter_treatments_guides.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<GuideApi> guideApis;
    private ArrayList<GuideApi> guideApisFilter;
    private int layout;
    private OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;
    private SharedPreferences preferences;

    //constructor
    public Adapter_treatments_guides(ArrayList<GuideApi> guideApis, int layout, OnItemClickListener listener) {
        this.guideApis = guideApis;
        this.layout = layout;
        this.listener = listener;
        this.guideApisFilter = new ArrayList<GuideApi>();
        this.guideApisFilter.addAll(guideApis);
        this.mFilter = new CustomFilter(Adapter_treatments_guides.this);
    }

    //estructura viewholder
    @NonNull
    @Override
    public Adapter_treatments_guides.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_treatments_guides.ViewHolder vh = new Adapter_treatments_guides.ViewHolder(v);
        preferences=context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_treatments_guides.ViewHolder holder, int position) {
        holder.bind(guideApisFilter.get(position),preferences, listener);
    }

    @Override
    public int getItemCount() {
        return guideApisFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //Clase para filtrar las views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Checar si aqui se puede poner el bind
        @BindView(R.id.floatingTreatmentsGuides)
        FloatingActionButton floatingTreatmentsGuides;
        @BindView(R.id.textViewNameTreatmentsGuides)
        TextView textViewNameTreatmentsGuides;
        @BindView(R.id.imageViewTreatmentsGuides)
        ImageView imageViewTreatmentsGuides;
        @BindView(R.id.card_view_treatments_guides)
        CardView card_view_treatments_guides;
        @BindDrawable(R.drawable.loading)
        Drawable loadingImage;
        @BindDrawable(R.drawable.error)
        Drawable errorImage;
        @BindColor(R.color.colorAzul)
        int colorAzul;
        @BindColor(R.color.colorMarron)
        int colorMarron;
        @BindColor(R.color.colorRosado)
        int colorRosado;
        private SharedPreferences preferences;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final GuideApi guideApi,SharedPreferences preferences, final OnItemClickListener listener) {
            this.preferences=preferences;
            //libreria de picasso para tratamiento de imagenes
            Picasso.get().load(guideApi.getImage()).fit().placeholder(loadingImage).error(errorImage).into(imageViewTreatmentsGuides);
            //imageViewPoster.setImageResource(diet.getPoster());
            textViewNameTreatmentsGuides.setText(guideApi.getName());
            floatingTreatmentsGuides.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFloatingClick(guideApi);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(guideApi, getAdapterPosition());
                }
            });
            changeColors();
        }
        //Metodo para recuperar el color requerido
        private void changeColors(){
            switch (preferences.getInt("ColorApp", 1)){
                case 1:
                    setColorsApp(colorAzul);
                    break;
                case 2:
                    setColorsApp(colorMarron);
                    break;
                case 3:
                    setColorsApp(colorRosado);
                    break;
            }
        }
        //Metodo para cambiar los colores de la app
        private void setColorsApp(int color){
            card_view_treatments_guides.setCardBackgroundColor(color);
        }
    }

    //clase de interfaz para el metodo onitemclick
    public interface OnItemClickListener {
        void onItemClick(GuideApi guideApi, int position);
        void onFloatingClick(GuideApi guideApi);
    }

    public class CustomFilter extends Filter {
        private Adapter_treatments_guides adapter_treatments_guides;

        public CustomFilter(Adapter_treatments_guides adapter_treatments_guides) {
            super();
            this.adapter_treatments_guides = adapter_treatments_guides;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            guideApisFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                guideApisFilter.addAll(guideApis);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final GuideApi guideApi : guideApis) {
                    if (guideApi.getName().toLowerCase().contains(filterPattern)) {
                        guideApisFilter.add(guideApi);
                    }
                }
            }
            results.values = guideApisFilter;
            results.count = guideApisFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_treatments_guides.notifyDataSetChanged();
        }
    }
}
