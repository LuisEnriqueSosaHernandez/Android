package com.heroes.lesh.kidneys.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.R;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Adapter_help_questions extends RecyclerView.Adapter<Adapter_help_questions.ViewHolder> implements Filterable {
    //Variables globales
    private ArrayList<QuestionApi> questionApis;
    private ArrayList<QuestionApi> questionApisFilter;
    private int layout;
    private Adapter_help_questions.OnItemClickListener listener;
    private Context context;
    private CustomFilter mFilter;
    private SharedPreferences preferences;

    public Adapter_help_questions(ArrayList<QuestionApi> questionApis, int layout, Adapter_help_questions.OnItemClickListener listener) {
        this.questionApis = questionApis;
        this.layout = layout;
        this.listener = listener;
        this.questionApisFilter = new ArrayList<QuestionApi>();
        this.questionApisFilter.addAll(questionApis);
        this.mFilter = new CustomFilter(Adapter_help_questions.this);
    }

    @NonNull
    @Override
    public Adapter_help_questions.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        Adapter_help_questions.ViewHolder vh = new Adapter_help_questions.ViewHolder(v);
        preferences=context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_help_questions.ViewHolder holder, int position) {
        holder.bind(questionApisFilter.get(position),preferences, listener);
    }

    @Override
    public int getItemCount() {
        return questionApisFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    //clase viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewQuestionHelpQuestions)
        TextView textViewQuestionHelpQuestions;
        @BindView(R.id.card_view_help_questions)
        CardView card_view_help_questions;
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

        public void bind(final QuestionApi questionApi,SharedPreferences preferences,final Adapter_help_questions.OnItemClickListener listener) {
            this.preferences=preferences;
            //usamos la libreria de picasso para el tratamiento de imagenes
            textViewQuestionHelpQuestions.setText(questionApi.getQuestion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(questionApi, getAdapterPosition());
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
            card_view_help_questions.setBackgroundColor(color);
        }
    }

    //creamos una clase de interfaz para el onitemclick
    public interface OnItemClickListener {
        void onItemClick(QuestionApi questionApi, int position);
    }

    //Clase para filtrar las views
    public class CustomFilter extends Filter {
        private Adapter_help_questions adapter_help_questions;

        public CustomFilter(Adapter_help_questions adapter_help_questions) {
            super();
            this.adapter_help_questions = adapter_help_questions;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            questionApisFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                questionApisFilter.addAll(questionApis);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final QuestionApi questionApi : questionApis) {
                    if (questionApi.getQuestion().toLowerCase().contains(filterPattern)) {
                        questionApisFilter.add(questionApi);
                    }
                }
            }
            results.values = questionApisFilter;
            results.count = questionApisFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter_help_questions.notifyDataSetChanged();
        }
    }
}

