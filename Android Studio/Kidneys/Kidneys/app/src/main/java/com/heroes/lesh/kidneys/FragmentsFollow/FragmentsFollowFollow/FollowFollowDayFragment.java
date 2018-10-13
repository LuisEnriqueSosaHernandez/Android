package com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Adapters.Adapter_follow_follows;
import com.heroes.lesh.kidneys.Adapters.Adapter_follow_follows_day;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowDeleteApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayDeleteApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDaysApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowFollowDayFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private FollowsDayCreateApi followsDayCreateApi;
    private FollowsDayDeleteApi followsDayDeleteApi;
    private FollowsDaysApi followsDaysApisArray;
    private FollowsDayApi followsDayApi;
    private KidneysService kidneysService;
    private ArrayList<FollowsDayApi> followsDayApis;
    @BindView(R.id.recyclerviewFollowFollowsDay)
    RecyclerView mRecyclerView;
    @BindView(R.id.floatingLinesFollowFollowsDay)
    FloatingActionButton floatingLinesFollowFollowsDay;
    @BindView(R.id.floatingAddFollowFollowsDay)
    FloatingActionButton floatingAddFollowFollowsDay;
    @BindView(R.id.floatingAllFollowFollowsDay)
    FloatingActionButton floatingAllFollowFollowsDay;
    @BindView(R.id.editTextFilterFollowFollowsDay)
    EditText editTextFilterFollowFollowsDay;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.eliminar)
    String eliminar;
    @BindString(R.string.confirmaralert)
    String confirmaralert;
    @BindString(R.string.anadir)
    String anadir;
    @BindString(R.string.vacio)
    String vacio;
    @BindString(R.string.correovacio)
    String correovacio;
    @BindString(R.string.eliminado)
    String eliminado;
    @BindString(R.string.noeliminado)
    String noeliminado;
    @BindString(R.string.insertado)
    String insertado;
    @BindString(R.string.noinsertado)
    String noinsertado;
    @BindString(R.string.cancelar)
    String cancelar;
    @BindString(R.string.cancelado)
    String cancelado;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.errorreliminar)
    String errorreliminar;
    @BindString(R.string.erroragregar)
    String erroragregar;
    @BindString(R.string.todos)
    String todos;
    @BindString(R.string.fechanovalida)
    String fechanovalida;
    @BindString(R.string.bien)
    String bien;
    @BindString(R.string.normal)
    String normal;
    @BindString(R.string.mal)
    String mal;
    @BindString(R.string.distribucion)
    String distribucion;
    @BindString(R.string.nuevorecambio)
    String nuevorecambio;
    @BindString(R.string.mensajenuevorecambio)
    String mensajenuevorecambio;
    @BindString(R.string.horanovalida)
    String horanovalida;
    @BindString(R.string.drenadoinvalido)
    String drenadoinvalido;
    @BindString(R.string.ufinvalido)
    String ufinvalido;
    @BindString(R.string.ingeridoinvalido)
    String ingeridoinvalido;
    @BindString(R.string.eliminando)
    String eliminando;
    @BindString(R.string.insertando)
    String insertando;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_medium)
    Drawable ic_medium;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_seguimiento)
    Drawable ic_seguimiento;
    @BindDrawable(R.drawable.ic_line)
    Drawable ic_line;
    @BindDrawable(R.drawable.ic_twolines)
    Drawable ic_twolines;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    @BindColor(R.color.colorNegro)
    int colorNegro;
    private Adapter_follow_follows_day mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Unbinder unbinder;
    private View view;
    private int numTargets;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private LayoutInflater layoutInflaterAlertFollowDay;
    private View viewAlertFollowDay;
    private View viewToast;
    private Bundle bundle;
    private Gson gson;
    private String jsonFollowsDay;
    private String email;
    private String healthRangeName;
    private boolean addordelete;
    private int idFollow;
    private ArrayList<String> typesofsolution;
    private Spinner spinnerFollowDayTypeOfSolution;
    private EditText editTextStartFollowDay;
    private EditText editTextEndFollowDay;
    private EditText editTextDrainageFollowDay;
    private EditText editTextUfFollowDay;
    private EditText editTextIngestedLiquid;
    private Date date;
    private DateFormat hourFormat;
    private String startHourInString;
    private String endHourInString;
    private String hourInStringNow;
    private Date startHour;
    private Date hourNow;
    private Date endHour;
    private double typeOfSolution;
    private String start;
    private String end;
    private double drainage;
    private double uf;
    private double ingestedLiquid;
    private ProgressDialog progressDialog;

    public FollowFollowDayFragment() {
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
        addordelete=false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_follow_follow_day, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        //Cargar tablas de follows
        cargarTablasFollowsDay();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        //mLayoutManager=new LinearLayoutManager(getActivity());
//Cmbiando el adapatador para el recycler
        mAdapter = new Adapter_follow_follows_day(followsDayApis, R.layout.recycler_follow_follows_day,healthRangeName, new Adapter_follow_follows_day.OnItemClickListener() {
            @Override
            public void onItemClick(FollowsDayApi followsDayApi, int position) {
                sendFollowDay(followsDayApi);
            }

            @Override
            public void onFloatingClick(int idFollowDay) {
                showAlertForDeletingFollowDay(eliminar, confirmaralert, idFollowDay);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesFollowFollowsDay.setOnClickListener(new View.OnClickListener() {
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
        floatingLinesFollowFollowsDay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_seguimiento);
                toast.show();
                return false;
            }
        });
        floatingAddFollowFollowsDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showAlertForCreatingFollowDay(nuevorecambio, mensajenuevorecambio);
            }
        });
        editTextFilterFollowFollowsDay.addTextChangedListener(new TextWatcher() {
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
        floatingAllFollowFollowsDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllFollowFollowsDay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_seguimiento);
                toast.show();
                return false;
            }
        });
        editTextFilterFollowFollowsDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    private boolean validateDrainage(String drainage){
        if(!TextUtils.isEmpty(drainage)&&!drainage.startsWith(".")){
            return (Double.parseDouble(drainage) < 5000 && Double.parseDouble(drainage) > 0);
        }else{
            return false;
        }
    }
    private boolean validateUf(String uf){
        if(!TextUtils.isEmpty(uf)&&!uf.startsWith(".")){
            return (Double.parseDouble(uf) < 1000 && Double.parseDouble(uf) >= 0);
        }else{
            return false;
        }
    }
    private boolean validateIngestedLiquid(String ingestedLiquid){
        if(!TextUtils.isEmpty(ingestedLiquid)&&!ingestedLiquid.startsWith(".")){
            return (Double.parseDouble(ingestedLiquid) < 5000 && Double.parseDouble(ingestedLiquid) > 0);
        }else{
            return false;
        }
    }


    //Metodo parfa inicializar componentes
    private void inicializar() {
        gson = new Gson();
        typesofsolution=typeofsolution();
        followsDayApis = new ArrayList<FollowsDayApi>();
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        date=new Date();
        hourFormat = new SimpleDateFormat("HH:mm:ss");
        hourInStringNow=hourFormat.format(date);
        try {
            hourNow=hourFormat.parse(hourInStringNow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startHour=hourNow;
        progressDialog = new ProgressDialog(getActivity());
    }

    //Metodo para cargar las tablas de follows
    private void cargarTablasFollowsDay() {
        bundle = getArguments();
        if (bundle != null) {
            jsonFollowsDay = bundle.getString("FollowsDays");
            idFollow=bundle.getInt("IdFollow");
            email = bundle.getString("Email");
            healthRangeName=bundle.getString("HealthRangeName");
            if (jsonFollowsDay != null&&!addordelete) {
                followsDayApis = gson.fromJson(jsonFollowsDay, new TypeToken<ArrayList<FollowsDayApi>>() {
                }.getType());
                filterDay();
            } else {
                changeToast(reintentardatos, ic_seguimiento);
                toast.show();
                tryAgainCargarTablasFollowsDay(cargacorrecta);
            }
        }
    }
    private void filterDay(){
        for (int i=0;i<followsDayApis.size();i++){
            if(followsDayApis.get(i).getIdFollow()!=idFollow){
                followsDayApis.remove(i);
                i--;
            }
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

    private void changeProgressDialog(String message) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    //Metodo para cambiar el toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    private void showTimePickerDialogStart(){
        TimePickerFragment newFragment=TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                date=new Date(0,0,0,hourOfDay,minute,0);
                startHourInString=hourFormat.format(date);
                try {
                    startHour=hourFormat.parse(startHourInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                editTextStartFollowDay.setText(startHourInString);
                editTextEndFollowDay.setText(startHourInString);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(),"TimePicker");
    }
    private void showTimePickerDialogEnd(){
        TimePickerFragment newFragment=TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                date=new Date(0,0,0,hourOfDay,minute,0);
                endHourInString=hourFormat.format(date);
                try {
                    endHour=hourFormat.parse(endHourInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(endHour.compareTo(startHour)<0){
                    changeToast(horanovalida,ic_harmful);
                    toast.show();
                }else{
                    editTextEndFollowDay.setText(endHourInString);
                }
                editTextStartFollowDay.setText(startHourInString);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(),"TimePicker");
    }

    //Metodo para cambiar la distribucion del layout
    private void changeView(int numTargets) {
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesFollowFollowsDay.setImageDrawable(ic_twolines);
        }else{
            floatingLinesFollowFollowsDay.setImageDrawable(ic_line);
        }

    }

    //Metodo para recuperar el numero de tarjetas
    private void numTargets() {
        numTargets = preferences.getInt("NumTargetsFollowsDay", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsFollowsDay", numTargets);
        editor.apply();
    }

    private ArrayList<String> typeofsolution() {
        typesofsolution = new ArrayList<String>() {{
            add("1.5");
            add("2.5");
            add("4.5");
        }};
        return typesofsolution;
    }

    //Alerta para crear un nuevo follow
    private void showAlertForCreatingFollowDay(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);

        layoutInflaterAlertFollowDay = getActivity().getLayoutInflater();
        viewAlertFollowDay = layoutInflaterAlertFollowDay.inflate(R.layout.custom_alert_follow_day, null);
        spinnerFollowDayTypeOfSolution = viewAlertFollowDay.findViewById(R.id.spinnerFollowDayTypeOfSolution);
        spinnerFollowDayTypeOfSolution.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, typesofsolution));
        spinnerFollowDayTypeOfSolution.getSelectedItem().toString();
        editTextStartFollowDay=viewAlertFollowDay.findViewById(R.id.editTextStartFollowDay);
        editTextEndFollowDay=viewAlertFollowDay.findViewById(R.id.editTextEndFollowDay);
        editTextDrainageFollowDay=viewAlertFollowDay.findViewById(R.id.editTextDrainageFollowDay);
        editTextUfFollowDay=viewAlertFollowDay.findViewById(R.id.editTextUfFollowDay);
        editTextIngestedLiquid=viewAlertFollowDay.findViewById(R.id.editTextIngestedLiquid);
        //Evitar que la vista abra el teclado
        editTextStartFollowDay.setInputType(InputType.TYPE_NULL);
        editTextEndFollowDay.setInputType(InputType.TYPE_NULL);
        if(!TextUtils.isEmpty(hourInStringNow)) {
            editTextStartFollowDay.setText(hourInStringNow);
            editTextEndFollowDay.setText(hourInStringNow);
        }
        editTextStartFollowDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogStart();
            }
        });
        editTextStartFollowDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextEndFollowDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogEnd();
            }
        });
        editTextEndFollowDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextDrainageFollowDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateDrainage(editTextDrainageFollowDay.getText().toString())&&!TextUtils.isEmpty(editTextDrainageFollowDay.getText().toString())){
                        changeToast(drenadoinvalido,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextDrainageFollowDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateDrainage(s.toString())){
                    editTextDrainageFollowDay.setTextColor(colorNegro);
                    editTextDrainageFollowDay.setHintTextColor(colorNegro);
                }else{
                    editTextDrainageFollowDay.setTextColor(colorRojo);
                    editTextDrainageFollowDay.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextUfFollowDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateUf(editTextUfFollowDay.getText().toString())&&!TextUtils.isEmpty(editTextUfFollowDay.getText().toString())){
                        changeToast(ufinvalido,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextUfFollowDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateUf(s.toString())){
                    editTextUfFollowDay.setTextColor(colorNegro);
                    editTextUfFollowDay.setHintTextColor(colorNegro);
                }else{
                    editTextUfFollowDay.setTextColor(colorRojo);
                    editTextUfFollowDay.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextIngestedLiquid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateIngestedLiquid(editTextIngestedLiquid.getText().toString())&&!TextUtils.isEmpty(editTextIngestedLiquid.getText().toString())){
                        changeToast(ingeridoinvalido,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextIngestedLiquid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateIngestedLiquid(s.toString())){
                    editTextIngestedLiquid.setTextColor(colorNegro);
                    editTextIngestedLiquid.setHintTextColor(colorNegro);
                }else{
                    editTextIngestedLiquid.setTextColor(colorRojo);
                    editTextIngestedLiquid.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        builder.setView(viewAlertFollowDay);
        builder.setPositiveButton(anadir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(email)) {
                  if(validateDrainage(editTextDrainageFollowDay.getText().toString())){
                      if(validateUf(editTextUfFollowDay.getText().toString())){
                          if(validateIngestedLiquid(editTextIngestedLiquid.getText().toString())){
                              typeOfSolution=Double.parseDouble(spinnerFollowDayTypeOfSolution.getSelectedItem().toString());
                              start=editTextStartFollowDay.getText().toString().trim();
                              end=editTextEndFollowDay.getText().toString().trim();
                              drainage=Double.parseDouble(editTextDrainageFollowDay.getText().toString().trim());
                              uf=Double.parseDouble(editTextUfFollowDay.getText().toString().trim());
                              ingestedLiquid=Double.parseDouble(editTextIngestedLiquid.getText().toString().trim());
                              changeProgressDialog(insertando);
                              progressDialog.show();
                              addFollowDay(typeOfSolution,start,end,drainage,uf,ingestedLiquid,idFollow,email);
                          }else{
                              changeToast(ingeridoinvalido,ic_harmful);
                              toast.show();
                          }
                      }else{
                          changeToast(ufinvalido,ic_harmful);
                          toast.show();
                      }
                  }else{
                      changeToast(drenadoinvalido,ic_harmful);
                      toast.show();
                  }
                } else {
                    changeToast(vacio, ic_harmful);
                    toast.show();
                }
            }
        });
        builder.setNegativeButton(cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeToast(cancelado, ic_harmful);
                toast.show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //Alerta de comfirmacion delete follow
    private void showAlertForDeletingFollowDay(String title, String message, final int idFollowDay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);
        builder.setPositiveButton(eliminar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(email)) {
                    changeProgressDialog(eliminando);
                    progressDialog.show();
                  deleteFollowDay(idFollowDay);
                } else {
                    changeToast(correovacio, ic_harmful);
                    toast.show();
                }

            }
        });
        builder.setNegativeButton(cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeToast(cancelado, ic_harmful);
                toast.show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Metodo para eliminar un follow
    private void deleteFollowDay(int idfollowday) {
        kidneysService.deleteFollowsDayApi(idfollowday).enqueue(new retrofit2.Callback<FollowsDayDeleteApi>() {
            @Override
            public void onResponse(Call<FollowsDayDeleteApi> call, Response<FollowsDayDeleteApi> response) {
                progressDialog.dismiss();
                followsDayDeleteApi = response.body();
                if (TextUtils.equals(followsDayDeleteApi.getStatus(), "true")) {
                    //changeToast("Eliminado",ic_healthy);
                    //toast.show();
                    followsDayApis.clear();
                    //Arreglar bug de pinter null y evitar carga excesiva
                        tryAgainCargarTablasFollowsDay(eliminado);
                    //Recargar los nuevos datos
                    addordelete=true;
                } else if (TextUtils.equals(followsDayDeleteApi.getStatus(), "false")) {
                    changeToast(noeliminado, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<FollowsDayDeleteApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(errorreliminar, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para agregar un nuevo followday
   private void addFollowDay(double typeOfSolution,String start,String end,double drainage,double uf,double ingestedLiquid,
                          int idFollow,String email) {
        kidneysService.createFollowsDayApi(typeOfSolution, start,end,drainage,uf,ingestedLiquid,idFollow,email).
                enqueue(new retrofit2.Callback<FollowsDayCreateApi>() {
            @Override
            public void onResponse(Call<FollowsDayCreateApi> call, Response<FollowsDayCreateApi> response) {
                progressDialog.dismiss();
                followsDayCreateApi = response.body();
                if (TextUtils.equals(followsDayCreateApi.getStatus(), "true")) {
                     //changeToast("Insertado",ic_healthy);
                    //toast.show();
                    followsDayApis.clear();
                    //Arreglar bug de pinter null y evitar carga excesiva
                    tryAgainCargarTablasFollowsDay(insertado);
                    //Recargar los nuevos datos
                    addordelete=true;
                } else if (TextUtils.equals(followsDayCreateApi.getStatus(), "false")) {
                    changeToast(noinsertado, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<FollowsDayCreateApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(erroragregar, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para enviar el followDay
    private void sendFollowDay(FollowsDayApi followsDayApi) {
        callback.sendFollowDay(followsDayApi);
    }

    //Metodo para reintentar cargar las tablas
    private void tryAgainCargarTablasFollowsDay(String message) {
        selectFollowsDay(message);
    }
    private void selectFollowsDay(final String message){
        kidneysService.selectFollowsDayApi(email).enqueue(new Callback<FollowsDaysApi>() {
            @Override
            public void onResponse(Call<FollowsDaysApi> call, Response<FollowsDaysApi> response) {
                followsDaysApisArray=response.body();
                if(followsDaysApisArray!=null){
                    for (int i=0;i<followsDaysApisArray.getFollowsDayApis().size();i++){
                        followsDayApi=new FollowsDayApi();
                        followsDayApi.setIdFollowDay(followsDaysApisArray.getFollowsDayApis().get(i).getIdFollowDay());
                        followsDayApi.setTypeOfSolution(followsDaysApisArray.getFollowsDayApis().get(i).getTypeOfSolution());
                        followsDayApi.setStart(followsDaysApisArray.getFollowsDayApis().get(i).getStart());
                        followsDayApi.setEnd(followsDaysApisArray.getFollowsDayApis().get(i).getEnd());
                        followsDayApi.setDrainage(followsDaysApisArray.getFollowsDayApis().get(i).getDrainage());
                        followsDayApi.setUf(followsDaysApisArray.getFollowsDayApis().get(i).getUf());
                        followsDayApi.setIngestedLiquid(followsDaysApisArray.getFollowsDayApis().get(i).getIngestedLiquid());
                        followsDayApi.setIdFollow(followsDaysApisArray.getFollowsDayApis().get(i).getIdFollow());
                        followsDayApi.setEmail(followsDaysApisArray.getFollowsDayApis().get(i).getEmail());
                        followsDayApis.add(followsDayApi);
                    }
                }
                jsonFollowsDay = gson.toJson(followsDayApis);
                sendJsonFollowsDay(jsonFollowsDay);
                filterDay();
                mAdapter.getFilter().filter("");
                changeToast(message, ic_healthy);
                toast.show();
            }

            @Override
            public void onFailure(Call<FollowsDaysApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }
    private void sendJsonFollowsDay(String jsonFollowsDay) {
        callback.tryAgainJsonFollowsDay(jsonFollowsDay);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    public static class TimePickerFragment extends DialogFragment{
        private TimePickerDialog.OnTimeSetListener listener;
        public static TimePickerFragment newInstance(TimePickerDialog.OnTimeSetListener listener){
            TimePickerFragment fragment=new TimePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }
        public void setListener(TimePickerDialog.OnTimeSetListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();

            //Variables para obtener la hora hora
            final int hour = c.get(Calendar.HOUR_OF_DAY);
            final int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),listener,hour,minute,true);
        }
    }

}



