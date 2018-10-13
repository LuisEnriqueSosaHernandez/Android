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

import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_food_rules_foods extends RecyclerView.Adapter<Adapter_food_rules_foods.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<Food> foods;
    private ArrayList<Food> foodsFilter;
    private int layout;
    private Adapter_food_rules_foods.OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;

    public Adapter_food_rules_foods(ArrayList<Food> foods, int layout, Adapter_food_rules_foods.OnItemClickListener listener) {
        this.foods = foods;
        this.layout = layout;
        this.listener = listener;
        this.foodsFilter = new ArrayList<Food>();
        this.foodsFilter.addAll(foods);
        this.mFilter = new CustomFilter(Adapter_food_rules_foods.this);
    }

    @NonNull
    @Override
    public Adapter_food_rules_foods.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_food_rules_foods.ViewHolder vh = new Adapter_food_rules_foods.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_food_rules_foods.ViewHolder holder, int position) {
        holder.bind(foodsFilter.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return foodsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //clase viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.floatingFoodRulesFoods)
        FloatingActionButton floatingFoodRulesFoods;
        @BindView(R.id.imageViewFoodRulesFoods)
        ImageView imageViewFoodRulesFoods;
        @BindView(R.id.textViewNameFoodRulesFoods)
        TextView textViewNameFoodRulesFoods;
        @BindView(R.id.card_view_food_rules_foods)
        CardView card_view_food_rules_foods;
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

        public void bind(final Food food, final Adapter_food_rules_foods.OnItemClickListener listener) {
            //usamos la libreria de picasso para el tratamiento de imagenes
            Picasso.get().load(food.getImage()).fit().placeholder(loadingImage).error(errorImage).into(imageViewFoodRulesFoods);
            textViewNameFoodRulesFoods.setText(food.getName());
            switch (food.getHealthRangeName()) {
                case "Healthy":
                    floatingFoodRulesFoods.setImageDrawable(ic_healthy);
                    card_view_food_rules_foods.setCardBackgroundColor(colorVerde);
                    break;
                case "Medium":
                    floatingFoodRulesFoods.setImageDrawable(ic_medium);
                    card_view_food_rules_foods.setCardBackgroundColor(colorMarron);
                    break;
                case "Harmful":
                    floatingFoodRulesFoods.setImageDrawable(ic_harmful);
                    card_view_food_rules_foods.setCardBackgroundColor(colorRojo);
                    break;
            }
            floatingFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFloatingClick(food);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(food, getAdapterPosition());
                }
            });
        }
    }

    //creamos una clase de interfaz para el onitemclick
    public interface OnItemClickListener {
        void onItemClick(Food food, int position);

        void onFloatingClick(Food food);
    }

    //Clase para filtrar las views
    public class CustomFilter extends Filter {
        private Adapter_food_rules_foods adapter_food_rules_foods;

        public CustomFilter(Adapter_food_rules_foods adapter_food_rules_foods) {
            super();
            this.adapter_food_rules_foods = adapter_food_rules_foods;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            foodsFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                foodsFilter.addAll(foods);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Food food : foods) {
                    if (food.getName().toLowerCase().contains(filterPattern) || food.getHealthRangeName().toLowerCase().equals(filterPattern)) {
                        foodsFilter.add(food);
                    }
                }
            }
            results.values = foodsFilter;
            results.count = foodsFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_food_rules_foods.notifyDataSetChanged();
        }
    }
}
