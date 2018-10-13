package com.heroes.lesh.kidneys.Adapters;

import android.content.Context;
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

import com.heroes.lesh.kidneys.Models.Recipe;
import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_food_rules_recipes extends RecyclerView.Adapter<Adapter_food_rules_recipes.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> recipesFilter;
    private int layout;
    private OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;

    //constructor
    public Adapter_food_rules_recipes(ArrayList<Recipe> recipes, int layout, OnItemClickListener listener) {
        this.recipes = recipes;
        this.layout = layout;
        this.listener = listener;
        this.recipesFilter = new ArrayList<Recipe>();
        this.recipesFilter.addAll(recipes);
        this.mFilter = new CustomFilter(Adapter_food_rules_recipes.this);
    }

    //estrctura viewholder
    @NonNull
    @Override
    public Adapter_food_rules_recipes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_food_rules_recipes.ViewHolder vh = new Adapter_food_rules_recipes.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_food_rules_recipes.ViewHolder holder, int position) {
        holder.bind(recipesFilter.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return recipesFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //clase viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.floatingFoodRulesRecipes)
        FloatingActionButton floatingFoodRulesRecipes;
        @BindView(R.id.imageViewFoodRulesRecipes)
        ImageView imageViewFoodRulesRecipes;
        @BindView(R.id.textViewTitleFoodRulesRecipes)
        TextView textViewTitleFoodRulesRecipes;
        @BindView(R.id.card_view_food_rules_recipes)
        CardView card_view_food_rules_recipes;
        @BindDrawable(R.drawable.loading)
        Drawable loadingImage;
        @BindDrawable(R.drawable.error)
        Drawable errorImage;
        @BindDrawable(R.drawable.ic_healthy)
        Drawable ic_healthy;
        @BindDrawable(R.drawable.ic_medium)
        Drawable ic_medium;
        @BindDrawable(R.drawable.ic_harmful)
        Drawable ic_harmful;
        @BindColor(R.color.colorVerde)
        int colorVerde;
        @BindColor(R.color.colorMarron)
        int colorMarron;
        @BindColor(R.color.colorRojo)
        int colorRojo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Recipe recipe, final OnItemClickListener listener) {
            //usamos la libreria de picasso para el tratamiento de imagenes
            Picasso.get().load(recipe.getImage()).fit().placeholder(loadingImage).error(errorImage).into(imageViewFoodRulesRecipes);
            textViewTitleFoodRulesRecipes.setText(recipe.getTitle());
            switch (recipe.getHealthRangeName()) {
                case "Healthy":
                    floatingFoodRulesRecipes.setImageDrawable(ic_healthy);
                    card_view_food_rules_recipes.setCardBackgroundColor(colorVerde);
                    break;
                case "Medium":
                    floatingFoodRulesRecipes.setImageDrawable(ic_medium);
                    card_view_food_rules_recipes.setCardBackgroundColor(colorMarron);
                    break;
                case "Harmful":
                    floatingFoodRulesRecipes.setImageDrawable(ic_harmful);
                    card_view_food_rules_recipes.setCardBackgroundColor(colorRojo);
                    break;
            }
            floatingFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFloatingClick(recipe);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(recipe, getAdapterPosition());
                }
            });
        }
    }

    //creamos una clase de interfaz para el onitemclick
    public interface OnItemClickListener {
        void onItemClick(Recipe recipe, int position);

        void onFloatingClick(Recipe recipe);
    }

    //Clase para filtrar las views
    public class CustomFilter extends Filter {
        private Adapter_food_rules_recipes adapter_food_rules_recipes;

        public CustomFilter(Adapter_food_rules_recipes adapter_food_rules_recipes) {
            super();
            this.adapter_food_rules_recipes = adapter_food_rules_recipes;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            recipesFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                recipesFilter.addAll(recipes);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Recipe recipe : recipes) {
                    if (recipe.getTitle().toLowerCase().contains(filterPattern) || recipe.getHealthRangeName().toLowerCase().equals(filterPattern)) {
                        recipesFilter.add(recipe);
                    }
                }
            }
            results.values = recipesFilter;
            results.count = recipesFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_food_rules_recipes.notifyDataSetChanged();
        }
    }
}
