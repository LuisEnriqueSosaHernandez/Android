package com.heroes.lesh.kidneys.FragmentsHelp.FragmentsHelpQuestions;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heroes.lesh.kidneys.Adapters.Adapter_help_questions;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionsApi;
import com.heroes.lesh.kidneys.R;

import java.util.ArrayList;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpQuestionsFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private QuestionsApi questionsApiArray;
    private QuestionApi questionApi;
    private KidneysService kidneysService;
    private ArrayList<QuestionApi> questionApis;
    @BindView(R.id.recyclerviewHelpQuestions)
    RecyclerView mRecyclerView;
    @BindView(R.id.floatingLinesHelpQuestions)
    FloatingActionButton floatingLinesHelpQuestions;
    @BindView(R.id.floatingAllHelpQuestions)
    FloatingActionButton floatingAllHelpQuestions;
    @BindView(R.id.editTextFilterHelpQuestions)
    EditText editTextFilterHelpQuestions;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.todos)
    String todos;
    @BindString(R.string.distribucion)
    String distribucion;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_help)
    Drawable ic_help;
    @BindDrawable(R.drawable.ic_line)
    Drawable ic_line;
    @BindDrawable(R.drawable.ic_twolines)
    Drawable ic_twolines;
    private Adapter_help_questions mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Unbinder unbinder;
    private View view;
    private int numTargets;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private Bundle bundle;
    private Gson gson;
    private String jsonQuestions;

    public HelpQuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //Inicializacion del callback de la interfaz
            callback = (ListenerPag) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_help_questions, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        //Cargar tablas de foods
        cargarTablaQuestions();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        //mLayoutManager=new LinearLayoutManager(getActivity());
//Cmbiando el adapatador para el recycler
        mAdapter = new Adapter_help_questions(questionApis, R.layout.recycler_help_questions, new Adapter_help_questions.OnItemClickListener() {
            @Override
            public void onItemClick(QuestionApi questionApi, int position) {
                sendQuestion(questionApi);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesHelpQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numTargets==1){
                    numTargets++;
                    changePreferences(numTargets);
                    changeView(numTargets);
                    changeIcon(numTargets);
                }else{
                    numTargets--;
                    changePreferences(numTargets);
                    changeView(numTargets);
                    changeIcon(numTargets);
                }
            }
        });
        floatingLinesHelpQuestions.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_help);
                toast.show();
                return false;
            }
        });
        editTextFilterHelpQuestions.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        floatingAllHelpQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllHelpQuestions.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_help);
                toast.show();
                return false;
            }
        });
        editTextFilterHelpQuestions.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        return view;
    }

    //Metodo parfa inicializar componentes
    private void inicializar() {
        gson = new Gson();
        questionApis = new ArrayList<QuestionApi>();
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //Metodo para cargar las tablas de follows
    private void cargarTablaQuestions() {
        bundle = getArguments();
        if (bundle != null) {
            jsonQuestions = bundle.getString("Questions");
            questionApis = gson.fromJson(jsonQuestions, new TypeToken<ArrayList<QuestionApi>>() {
            }.getType());
        } else {
            changeToast(reintentardatos, ic_help);
            toast.show();
            tryAgainCargarTablasQuestions();
        }

    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar el toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para cambiar la distribucion del layout
    private void changeView(int numTargets) {
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Metodo para recuperar el numero de tarjetas
    private void numTargets() {
        numTargets = preferences.getInt("NumTargetsQuestions", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsQuestions", numTargets);
        editor.apply();
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesHelpQuestions.setImageDrawable(ic_twolines);
        }else{
            floatingLinesHelpQuestions.setImageDrawable(ic_line);
        }

    }


    //Metodo para enviar el follow
    private void sendQuestion(QuestionApi questionApi) {
        callback.sendQuestion(questionApi);
    }

    //Metodo para reintentar cargar las tablas
    private void tryAgainCargarTablasQuestions() {
        selectQuestions();
    }

    //Metodo para seleccionar los follows
    private void selectQuestions() {
        kidneysService.selectQuestionsApi().enqueue(new retrofit2.Callback<QuestionsApi>() {
            @Override
            public void onResponse(Call<QuestionsApi> call, Response<QuestionsApi> response) {
                questionsApiArray = response.body();
                if (questionsApiArray != null) {
                    for (int i = 0; i < questionsApiArray.getQuestionApis().size(); i++) {
                        questionApi = new QuestionApi();
                        questionApi.setIdQuestion(questionsApiArray.getQuestionApis().get(i).getIdQuestion());
                        questionApi.setQuestion(questionsApiArray.getQuestionApis().get(i).getQuestion());
                        questionApi.setAnswer(questionsApiArray.getQuestionApis().get(i).getAnswer());
                        questionApis.add(questionApi);
                    }
                }
                jsonQuestions = gson.toJson(questionApis);
                mAdapter.getFilter().filter("");
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                sendJsonQuestions(jsonQuestions);
            }

            @Override
            public void onFailure(Call<QuestionsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    private void sendJsonQuestions(String jsonQuestions) {
        callback.tryAgainJsonQuestions(jsonQuestions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

