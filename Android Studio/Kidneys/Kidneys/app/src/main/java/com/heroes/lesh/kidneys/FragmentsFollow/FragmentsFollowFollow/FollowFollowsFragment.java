package com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Adapters.Adapter_follow_follows;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowDeleteApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
public class FollowFollowsFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private FollowCreateApi followCreateApi;
    private FollowDeleteApi followDeleteApi;
    private FollowsApi followApis;
    private Follow follow;
    private HealthRangesApi healthRangesApi;
    private KidneysService kidneysService;
    private ArrayList<Follow> follows;
    @BindView(R.id.recyclerviewFollowFollows)
    RecyclerView mRecyclerView;
   @BindView(R.id.floatingLinesFollowFollows)
   FloatingActionButton floatingLinesFollowFollows;
    @BindView(R.id.floatingAddFollowFollows)
    FloatingActionButton floatingAddFollowFollows;
    @BindView(R.id.floatingAllFollowFollows)
    FloatingActionButton floatingAllFollowFollows;
    @BindView(R.id.editTextFilterFollowFollows)
    EditText editTextFilterFollowFollows;
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
    @BindString(R.string.nuevofollow)
    String nuevofollow;
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
    @BindString(R.string.nuevofollowmessage)
    String nuevofollowmessage;
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
    private Adapter_follow_follows mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Unbinder unbinder;
    private View view;
    private int numTargets;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private LayoutInflater layoutInflaterAlertFollow;
    private View viewAlertFollow;
    private View viewToast;
    private Bundle bundle;
    private Gson gson;
    private String jsonFollows;
    private EditText editTextFollowDate;
    private SimpleDateFormat dateFormat;
    private Date date;
    private String dateInString;
    private String dateInStringNow;
    private String dateUser;
    private String email;
    private Date dateNow;
    private Date dateSelect;
    private int idhealthrange;
    private FloatingActionButton floatingGoodFollow;
    private  FloatingActionButton floatingNormalFollow;
    private FloatingActionButton floatingBadFollow;
    private boolean addordelete;
    private ProgressDialog progressDialog;
    public FollowFollowsFragment() {
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
        view = inflater.inflate(R.layout.fragment_follow_follows, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        //Cargar tablas de foods
        cargarTablaFollows();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        //mLayoutManager=new LinearLayoutManager(getActivity());
//Cmbiando el adapatador para el recycler
        mAdapter = new Adapter_follow_follows(follows, R.layout.recycler_follow_follows, new Adapter_follow_follows.OnItemClickListener() {
            @Override
            public void onItemClick(Follow follow, int position) {
                sendFollow(follow);
            }

            @Override
            public void onFloatingClick(int idFollow) {
                showAlertForDeletingFollow(eliminar, confirmaralert, idFollow);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesFollowFollows.setOnClickListener(new View.OnClickListener() {
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
        floatingLinesFollowFollows.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_seguimiento);
                toast.show();
                return false;
            }
        });
        floatingAddFollowFollows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingFollow(nuevofollow, nuevofollowmessage);
            }
        });
        editTextFilterFollowFollows.addTextChangedListener(new TextWatcher() {
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
        floatingAllFollowFollows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllFollowFollows.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_seguimiento);
                toast.show();
                return false;
            }
        });
        editTextFilterFollowFollows.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        follows = new ArrayList<Follow>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = new Date();
        dateInStringNow = dateFormat.format(date);
        try {
            dateNow=dateFormat.parse(dateInStringNow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(getActivity());
    }

    //Metodo para cargar las tablas de follows
    private void cargarTablaFollows() {
        bundle = getArguments();
        if (bundle != null) {
            jsonFollows = bundle.getString("Follows");
            email = bundle.getString("Email");
            if (jsonFollows != null&&!addordelete) {
                follows = gson.fromJson(jsonFollows, new TypeToken<ArrayList<Follow>>() {
                }.getType());
            } else {
                changeToast(reintentardatos, ic_seguimiento);
                toast.show();
                tryAgainCargarTablasFollows();
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

    //Metodo para cambiar la distribucion del layout
    private void changeView(int numTargets) {
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesFollowFollows.setImageDrawable(ic_twolines);
        }else{
            floatingLinesFollowFollows.setImageDrawable(ic_line);
        }

    }

    //Metodo para recuperar el numero de tarjetas
    private void numTargets() {
        numTargets = preferences.getInt("NumTargetsFollows", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsFollows", numTargets);
        editor.apply();
    }

    //Alerta para crear un nuevo follow
    private void showAlertForCreatingFollow(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);

        layoutInflaterAlertFollow = getActivity().getLayoutInflater();
        viewAlertFollow = layoutInflaterAlertFollow.inflate(R.layout.custom_alert_follow, null);
        editTextFollowDate = viewAlertFollow.findViewById(R.id.editTextFollowDate);
        floatingGoodFollow=viewAlertFollow.findViewById(R.id.floatingGoodFollow);
        floatingNormalFollow=viewAlertFollow.findViewById(R.id.floatingNormalFollow);
        floatingBadFollow=viewAlertFollow.findViewById(R.id.floatingBadFollow);
        //Evitar que la vista abra el teclado
        editTextFollowDate.setInputType(InputType.TYPE_NULL);
        floatingGoodFollow.setAlpha(63);
        floatingBadFollow.setAlpha(63);
        idhealthrange=2;
        if (!TextUtils.isEmpty(dateInStringNow)) {
            editTextFollowDate.setText(dateInStringNow);
        }
        editTextFollowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        floatingGoodFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingGoodFollow.setAlpha(255);
                floatingNormalFollow.setAlpha(63);
                floatingBadFollow.setAlpha(63);
                idhealthrange=1;
            }
        });
        floatingGoodFollow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(bien,ic_healthy);
                toast.show();
                return false;
            }
        });
        floatingNormalFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingGoodFollow.setAlpha(63);
                floatingNormalFollow.setAlpha(255);
                floatingBadFollow.setAlpha(63);
                idhealthrange=2;
            }
        });
        floatingNormalFollow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(normal,ic_medium);
                toast.show();
                return false;
            }
        });
        floatingBadFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingGoodFollow.setAlpha(63);
                floatingNormalFollow.setAlpha(63);
                floatingBadFollow.setAlpha(255);
                idhealthrange=3;
            }
        });
        floatingBadFollow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(mal,ic_harmful);
                toast.show();
                return false;
            }
        });
        builder.setView(viewAlertFollow);
        builder.setPositiveButton(anadir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dateUser = editTextFollowDate.getText().toString();
                if (!TextUtils.isEmpty(dateUser) && !TextUtils.isEmpty(email)) {
                    changeProgressDialog(insertando);
                    progressDialog.show();
                    addFollow(email, dateUser,idhealthrange);
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                //final String selectedDate = year + "-" + (month+1) + "-" + day;
                date=new Date((year-1900),month,day);
                dateInString=dateFormat.format(date);
                try {
                    dateSelect=dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(dateSelect.compareTo(dateNow)>0){
                    changeToast(fechanovalida,ic_harmful);
                    toast.show();
                }else{
                    editTextFollowDate.setText(dateInString);
                }
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    //Alerta de comfirmacion delete follow
    private void showAlertForDeletingFollow(String title, String message, final int idFollow) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);
        builder.setPositiveButton(eliminar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(email)) {
                    changeProgressDialog(eliminando);
                    progressDialog.show();
                    deleteFollow(idFollow);
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
    private void deleteFollow(int idFollow) {
        kidneysService.deleteFollowApi(idFollow).enqueue(new retrofit2.Callback<FollowDeleteApi>() {
            @Override
            public void onResponse(Call<FollowDeleteApi> call, Response<FollowDeleteApi> response) {
                progressDialog.dismiss();
                followDeleteApi = response.body();
                if (TextUtils.equals(followDeleteApi.getStatus(), "true")) {
                    // changeToast("Eliminado",ic_food_rules);
                    //toast.show();
                    follows.clear();
                    //Arreglar bug de pinter null y evitar carga excesiva
                    if(healthRangesApi!=null){
                        selectFollows(eliminado);
                    }else{
                        tryAgainCargarTablasFollows();
                    }
                    //Recargar los nuevos datos
                    addordelete=true;
                } else if (TextUtils.equals(followDeleteApi.getStatus(), "false")) {
                    changeToast(noeliminado, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<FollowDeleteApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(errorreliminar, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para agregar un nuevo follow
    private void addFollow(String email, String dateInString,int idhealthrange) {
        kidneysService.createFollowApi(dateInString, email,idhealthrange).enqueue(new retrofit2.Callback<FollowCreateApi>() {
            @Override
            public void onResponse(Call<FollowCreateApi> call, Response<FollowCreateApi> response) {
                progressDialog.dismiss();
                followCreateApi = response.body();
                if (TextUtils.equals(followCreateApi.getStatus(), "true")) {
                    // changeToast("Insertado",ic_food_rules);
                    //toast.show();
                    follows.clear();
                    //Arreglar bug de pinter null y evitar carga excesiva
                    if(healthRangesApi!=null){
                        selectFollows(insertado);
                    }else{
                     tryAgainCargarTablasFollows();
                    }
                    //Recargar los nuevos datos
                    addordelete=true;
                } else if (TextUtils.equals(followCreateApi.getStatus(), "false")) {
                    changeToast(noinsertado, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<FollowCreateApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(erroragregar, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para enviar el follow
    private void sendFollow(Follow follow) {
        callback.sendFollow(follow);
    }

    //Metodo para reintentar cargar las tablas
    private void tryAgainCargarTablasFollows() {
        selectHealthRanges();
    }
    //Metodo para cargar las healthrage de la api
    private void selectHealthRanges() {
        kidneysService.selectHealthRangesApi().enqueue(new retrofit2.Callback<HealthRangesApi>() {
            @Override
            public void onResponse(Call<HealthRangesApi> call, Response<HealthRangesApi> response) {
                healthRangesApi = response.body();
                selectFollows(cargacorrecta);
            }

            @Override
            public void onFailure(Call<HealthRangesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }
    //Metodo para seleccionar los follows
    private void selectFollows(final String message) {
        kidneysService.selectFollowsApi(email).enqueue(new retrofit2.Callback<FollowsApi>() {
            @Override
            public void onResponse(Call<FollowsApi> call, Response<FollowsApi> response) {
                followApis = response.body();
                if (followApis != null) {
                    for (int i = 0; i < followApis.getFollowApis().size(); i++) {
                        follow = new Follow();
                        follow.setIdFollow(followApis.getFollowApis().get(i).getIdFollow());
                        follow.setDate(followApis.getFollowApis().get(i).getDate().trim());
                        if (healthRangesApi != null) {
                            for (int j = 0; j < healthRangesApi.getHealthRangeApis().size(); j++) {
                                if (healthRangesApi.getHealthRangeApis().get(j).getIdHealthRange() == followApis.getFollowApis().get(i).getIdhealthrange()) {
                                    follow.setHealthRangeName(healthRangesApi.getHealthRangeApis().get(j).getName().trim());
                                    break;
                                }
                            }
                        }
                        follows.add(follow);
                    }
                }
                jsonFollows = gson.toJson(follows);
                //Por alguna extra;a razon sin el for y con el for ya tampoco no se actualiza el adapter
                //followApis = gson.fromJson(jsonFollows, new TypeToken<ArrayList<FollowApi>>() {}.getType());
                // changeView(numTargets);
                mAdapter.getFilter().filter("");
                changeToast(message, ic_healthy);
                toast.show();
                sendJsonFollows(jsonFollows);
            }

            @Override
            public void onFailure(Call<FollowsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    private void sendJsonFollows(String jsonFollows) {
        callback.tryAgainJsonFollows(jsonFollows);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }
}



