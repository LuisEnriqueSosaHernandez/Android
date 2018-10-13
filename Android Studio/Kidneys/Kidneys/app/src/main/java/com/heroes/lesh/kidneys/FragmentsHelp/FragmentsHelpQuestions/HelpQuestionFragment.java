package com.heroes.lesh.kidneys.FragmentsHelp.FragmentsHelpQuestions;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.heroes.lesh.kidneys.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpQuestionFragment extends Fragment {
    //Variables globales
    private View view;
    private Bundle bundle;
    private Unbinder unbinder;
    @BindView(R.id.textViewQuestionHelpQuestion)
    TextView textViewQuestionHelpQuestion;
    @BindView(R.id.textViewAnswerHelpQuestion)
    TextView textViewAnswerHelpQuestion;
    @BindView(R.id.layoutHelpQuestion)
    FrameLayout layoutHelpQuestion;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private SharedPreferences preferences;

    public HelpQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_help_question, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        changeColors();
        bundle = getArguments();
        if (bundle != null) {
            textViewQuestionHelpQuestion.setText(bundle.getString("Question"));
            textViewAnswerHelpQuestion.append(": " + bundle.getString("Answer"));
        }
        return view;
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //Metodo para recuperar el color requerido
    private void changeColors() {
        switch (preferences.getInt("ColorApp", 1)) {
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
    private void setColorsApp(int color) {
        layoutHelpQuestion.setBackgroundColor(color);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
