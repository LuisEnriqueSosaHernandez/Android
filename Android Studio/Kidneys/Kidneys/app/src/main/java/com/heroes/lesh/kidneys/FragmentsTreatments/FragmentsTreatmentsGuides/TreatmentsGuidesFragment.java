package com.heroes.lesh.kidneys.FragmentsTreatments.FragmentsTreatmentsGuides;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
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
import com.heroes.lesh.kidneys.Adapters.Adapter_treatments_guides;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuidesApi;
import com.heroes.lesh.kidneys.R;

import java.util.ArrayList;

import butterknife.BindColor;
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
public class TreatmentsGuidesFragment extends Fragment {
    //Variables globales
    @BindView(R.id.recyclerviewTreatmentsGuides)
    RecyclerView mRecyclerView;
    @BindView(R.id.floatingLinesTreatmentsGuides)
    FloatingActionButton floatingLinesTreatmentsGuides;
    @BindView(R.id.floatingAllTreatmentsGuides)
    FloatingActionButton floatingAllTreatmentsGuides;
    @BindView(R.id.editTextFilterTreatmentsGuides)
    EditText editTextFilterTreatmentsGuides;
    @BindString(R.string.distribucion)
    String distribucion;
    @BindString(R.string.todos)
    String todos;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.aceptar)
    String aceptar;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_tratamientos)
    Drawable ic_tratamientos;
    @BindDrawable(R.drawable.ic_line)
    Drawable ic_line;
    @BindDrawable(R.drawable.ic_twolines)
    Drawable ic_twolines;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private Adapter_treatments_guides mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Unbinder unbinder;
    private View view;
    private SharedPreferences preferences;
    private int numTargets;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private Bundle bundle;
    private Gson gson;
    private String jsonGuides;
    private ListenerPag callback;
    private GuidesApi guidesApiArray;
    private GuideApi guideApi;
    private KidneysService kidneysService;
    private ArrayList<GuideApi> guideApis;
    private LayoutInflater layoutInflaterAlertGuides;
    private View viewAlertGuides;
    private TextView textViewAlertTreatmentsGuides;
    private TextView textViewAlertIndexTreatmentsGuides;
    private int color;

    public TreatmentsGuidesFragment() {
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_treatments_guides, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        cargarTablasGuides();
        changeColors();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        //mLayoutManager=new LinearLayoutManager(getActivity());
//Cambiando el adapatador
        mAdapter = new Adapter_treatments_guides(guideApis, R.layout.recycler_treatments_guides, new Adapter_treatments_guides.OnItemClickListener() {
            @Override
            public void onItemClick(GuideApi guideApi, int position) {
                // Toast.makeText(getActivity(), guideApi.getName() + " - " + position, Toast.LENGTH_SHORT).show();
                openguide(guideApi);
            }

            @Override
            public void onFloatingClick(GuideApi guideApi) {
                showAlertForViewGuide(guideApi);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesTreatmentsGuides.setOnClickListener(new View.OnClickListener() {
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
        floatingLinesTreatmentsGuides.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_tratamientos);
                toast.show();
                return false;
            }
        });
        floatingAllTreatmentsGuides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllTreatmentsGuides.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_tratamientos);
                toast.show();
                return false;
            }
        });
        editTextFilterTreatmentsGuides.addTextChangedListener(new TextWatcher() {
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
        editTextFilterTreatmentsGuides.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    //Metodo para abrir la guia seleccionada
    private void openguide(GuideApi guideApi) {

        callback.sendGuide(guideApi);
    }

    //Metodo para car las tablas de las guias
    private void cargarTablasGuides() {
        bundle = getArguments();
        if (bundle != null) {
            jsonGuides = bundle.getString("Guides");
            guideApis = gson.fromJson(jsonGuides, new TypeToken<ArrayList<GuideApi>>() {
            }.getType());
        } else {
            changeToast(reintentardatos, ic_tratamientos);
            toast.show();
            tryAgainCargarTablasGuides();
        }
    }

    //Metodo para reintentar la carga de las tablas
    private void tryAgainCargarTablasGuides() {
        selectGuides();
    }

    //Metodo para cargar las guias
    private void selectGuides() {
        kidneysService.selectGuidesApi().enqueue(new retrofit2.Callback<GuidesApi>() {
            @Override
            public void onResponse(Call<GuidesApi> call, Response<GuidesApi> response) {
                guidesApiArray = response.body();
                if (guidesApiArray != null) {
                    for (int i = 0; i < guidesApiArray.getGuideApis().size(); i++) {
                        guideApi = new GuideApi();
                        guideApi.setIdGuide(guidesApiArray.getGuideApis().get(i).getIdGuide());
                        guideApi.setName(guidesApiArray.getGuideApis().get(i).getName());
                        guideApi.setDescription(guidesApiArray.getGuideApis().get(i).getDescription());
                        guideApi.setNumPages(guidesApiArray.getGuideApis().get(i).getNumPages());
                        guideApi.setImage(guidesApiArray.getGuideApis().get(i).getImage());
                        guideApi.setPdf(guidesApiArray.getGuideApis().get(i).getPdf());
                        guideApi.setAuthor(guidesApiArray.getGuideApis().get(i).getAuthor());
                        guideApi.setYearPublication(guidesApiArray.getGuideApis().get(i).getYearPublication());
                        guideApi.setReference(guidesApiArray.getGuideApis().get(i).getReference());
                        guideApi.setContent(guidesApiArray.getGuideApis().get(i).getContent());
                        guideApis.add(guideApi);
                    }
                }
                jsonGuides = gson.toJson(guideApis);
                mAdapter.getFilter().filter("");
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                sendJsonGuides(jsonGuides);
            }

            @Override
            public void onFailure(Call<GuidesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Alerta para mostrar una food
    private void showAlertForViewGuide(GuideApi guideApi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflaterAlertGuides = getActivity().getLayoutInflater();
        viewAlertGuides = layoutInflaterAlertGuides.inflate(R.layout.custom_alert_treatments_guides, null);
        textViewAlertTreatmentsGuides = viewAlertGuides.findViewById(R.id.textViewAlertTreatmentsGuides);
        textViewAlertIndexTreatmentsGuides = viewAlertGuides.findViewById(R.id.textViewAlertIndexTreatmentsGuides);
        builder.setView(viewAlertGuides);
            textViewAlertTreatmentsGuides.setBackgroundColor(color);
        textViewAlertIndexTreatmentsGuides.setText(Html.fromHtml(guideApi.getContent()));

        builder.setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    //Metodo para enviar el json al main
    private void sendJsonGuides(String jsonGuides) {
        callback.tryAgainJsonGuides(jsonGuides);
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        gson = new Gson();
        guideApis = new ArrayList<GuideApi>();
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        color=colorAzul;
    }

    //Metodo para recuperar el color requerido
    private void changeColors() {
        switch (preferences.getInt("ColorApp", 1)) {
            case 1:
                color = colorAzul;
                break;
            case 2:
                color = colorMarron;
                break;
            case 3:
                color = colorRosado;
                break;
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

    //Metodo para modificar el toast
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
        numTargets = preferences.getInt("NumTargetsTreatments", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsTreatments", numTargets);
        editor.apply();
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesTreatmentsGuides.setImageDrawable(ic_twolines);
        }else{
            floatingLinesTreatmentsGuides.setImageDrawable(ic_line);
        }

    }


    //Metodo para destruir el bind
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}