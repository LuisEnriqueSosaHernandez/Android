package com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesFoods;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Adapters.Adapter_food_rules_foods;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.ModelsApi.CategoriesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FoodsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

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
public class FoodRulesFoodsFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private KidneysService kidneysService;
    private CategoriesApi categoriesApi;
    private FoodsApi foodsApi;
    private HealthRangesApi healthRangesApi;
    private ArrayList<Food> foods;
    private Food food;
    @BindView(R.id.recyclerviewFoodRulesFoods)
    RecyclerView mRecyclerView;
   @BindView(R.id.floatingLinesFoodRulesFoods)
   FloatingActionButton floatingLinesFoodRulesFoods;
    @BindView(R.id.floatingHealthyFoodRulesFoods)
    FloatingActionButton floatingHealthyFoodRulesFoods;
    @BindView(R.id.floatingMediumFoodRulesFoods)
    FloatingActionButton floatingMediumFoodRulesFoods;
    @BindView(R.id.floatingHarmfulFoodRulesFoods)
    FloatingActionButton floatingHarmfulFoodRulesFoods;
    @BindView(R.id.floatingAllFoodRulesFoods)
    FloatingActionButton floatingAllFoodRulesFoods;
    @BindView(R.id.editTextFilterFoodRulesFoods)
    EditText editTextFilterFoodRulesFoods;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.muyrecomendado)
    String muyrecomendado;
    @BindString(R.string.pocorecomendado)
    String pocorecomendado;
    @BindString(R.string.norecomendado)
    String norecomendado;
    @BindString(R.string.todos)
    String todos;
    @BindString(R.string.aceptar)
    String aceptar;
    @BindString(R.string.unaporcion)
    String unaporcion;
    @BindString(R.string.contiene)
    String contiene;
    @BindString(R.string.sodio)
    String sodio;
    @BindString(R.string.potasio)
    String potasio;
    @BindString(R.string.fosforo)
    String fosforo;
    @BindString(R.string.distribucion)
    String distribucion;
    @BindDrawable(R.mipmap.ic_food_rules)
    Drawable ic_food_rules;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_medium)
    Drawable ic_medium;
    @BindDrawable(R.drawable.loading)
    Drawable loadingImage;
    @BindDrawable(R.drawable.error)
    Drawable errorImage;
    @BindDrawable(R.drawable.ic_line)
    Drawable ic_line;
    @BindDrawable(R.drawable.ic_twolines)
    Drawable ic_twolines;
    @BindColor(R.color.colorVerde)
    int colorVerde;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    // private RecyclerView.Adapter mAdapter; //asi no , por que no extiende del adaptador en si
    private Adapter_food_rules_foods mAdapter;
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
    private String jsonFoods;
    private int i;
    private int j;
    private int k;
    private String category;
    private LayoutInflater layoutInflaterAlertFoods;
    private View viewAlertFoods;
    private TextView textViewAlertRecomendFoodRulesFoods;
    private ImageView imageViewAlertFoodRulesFoods;
    private TextView textViewAlertPorcionFoodRulesFood;
    private TextView textViewAlertSodioFoodRulesFood;
    private TextView textViewAlertPotasioFoodRulesFood;
    private TextView textViewAlertFosforoFoodRulesFood;
    private LinearLayout layoutDataFoodRulesFoods;
    private TextView textViewAlertNameFoodRulesFoods;

    public FoodRulesFoodsFragment() {
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
        view = inflater.inflate(R.layout.fragment_food_rules_foods, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        //Cargar tablas de foods
        cargarTablaFoods();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        // mLayoutManager=new StaggeredGridLayoutManager(numTargets,StaggeredGridLayoutManager.VERTICAL);
        //mLayoutManager=new LinearLayoutManager(getActivity());
        //Cambiando el adapatador para el recycler
        mAdapter = new Adapter_food_rules_foods(foods, R.layout.recycler_food_rules_foods, new Adapter_food_rules_foods.OnItemClickListener() {
            @Override
            public void onItemClick(Food food, int position) {
                //Toast.makeText(getActivity(), recipe.getTitle() + " - " + position, Toast.LENGTH_SHORT).show();
                sendFood(food);
            }

            @Override
            public void onFloatingClick(Food food) {
                showAlertForViewFood(food);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
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
        floatingLinesFoodRulesFoods.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_food_rules);
                toast.show();
                return false;
            }
        });
        floatingHealthyFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Healthy");
            }
        });
        floatingHealthyFoodRulesFoods.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(muyrecomendado, ic_healthy);
                toast.show();
                return false;
            }
        });
        floatingMediumFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Medium");
            }
        });
        floatingMediumFoodRulesFoods.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(pocorecomendado, ic_medium);
                toast.show();
                return false;
            }
        });
        floatingHarmfulFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Harmful");
            }
        });
        floatingHarmfulFoodRulesFoods.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(norecomendado, ic_harmful);
                toast.show();
                return false;
            }
        });
        floatingAllFoodRulesFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllFoodRulesFoods.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_food_rules);
                toast.show();
                return false;
            }
        });
        editTextFilterFoodRulesFoods.addTextChangedListener(new TextWatcher() {
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
        editTextFilterFoodRulesFoods.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    //Metodo para inicializar los componentes
    private void inicializar() {
        gson = new Gson();
        foods = new ArrayList<Food>();
        kidneysService = Api.getApi().create(KidneysService.class);
        category = "";
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //Metodo para cargar las tablas de alimentos
    private void cargarTablaFoods() {
        bundle = getArguments();
        if (bundle != null) {
            jsonFoods = bundle.getString("Foods");
            foods = gson.fromJson(jsonFoods, new TypeToken<ArrayList<Food>>() {
            }.getType());
        } else {
            changeToast(reintentardatos, ic_food_rules);
            toast.show();
            tryAgainCargarTablasFoods();
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

    //Metodo paracmabiar parametros del toast
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
        numTargets = preferences.getInt("NumTargetsFoods", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsFoods", numTargets);
        editor.apply();
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesFoodRulesFoods.setImageDrawable(ic_twolines);
        }else{
            floatingLinesFoodRulesFoods.setImageDrawable(ic_line);
        }

    }
    //Metodo para enviar el food
    private void sendFood(Food food) {
        callback.sendFood(food);
    }

    //Metodo para reintentar cargar las tablas
    private void tryAgainCargarTablasFoods() {
        selectCategories();
    }

    //Metodo para cargar las categorias de la api
    private void selectCategories() {
        kidneysService.selectCategoriesApi().enqueue(new retrofit2.Callback<CategoriesApi>() {
            @Override
            public void onResponse(Call<CategoriesApi> call, Response<CategoriesApi> response) {
                categoriesApi = response.body();
                selectHealthRanges();
            }

            @Override
            public void onFailure(Call<CategoriesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Metodo para cargar las healthrage de la api
    private void selectHealthRanges() {
        kidneysService.selectHealthRangesApi().enqueue(new retrofit2.Callback<HealthRangesApi>() {
            @Override
            public void onResponse(Call<HealthRangesApi> call, Response<HealthRangesApi> response) {
                healthRangesApi = response.body();
                selectFoods();
            }

            @Override
            public void onFailure(Call<HealthRangesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Metodo para cargar los alimentos de la api y meterlo en alimentos de la app
    private void selectFoods() {
        kidneysService.selectFoodsApi().enqueue(new retrofit2.Callback<FoodsApi>() {
            @Override
            public void onResponse(Call<FoodsApi> call, Response<FoodsApi> response) {
                foodsApi = response.body();
                if (foodsApi != null) {
                    for (i = 0; i < foodsApi.getFoodApis().size(); i++) {
                        //si no inicio uno nuevo me carga solo el ultimo, por eso lo hago aqui
                        food = new Food();
                        food.setIdFood(foodsApi.getFoodApis().get(i).getIdFood());
                        food.setName(foodsApi.getFoodApis().get(i).getName().trim());
                        food.setDescription(foodsApi.getFoodApis().get(i).getDescription().trim());
                        if (categoriesApi != null) {
                            for (j = 0; j < categoriesApi.getCategoriesApi().size(); j++) {
                                if (categoriesApi.getCategoriesApi().get(j).getIdCategory() == foodsApi.getFoodApis().get(i).getIdCategory()) {
                                    category = categoriesApi.getCategoriesApi().get(j).getName().trim();
                                    food.setCategory(category.trim());
                                    break;
                                }
                            }
                        }
                        food.setImage(foodsApi.getFoodApis().get(i).getImage());
                        if (healthRangesApi != null) {
                            for (k = 0; k < healthRangesApi.getHealthRangeApis().size(); k++) {
                                if (healthRangesApi.getHealthRangeApis().get(k).getIdHealthRange() == foodsApi.getFoodApis().get(i).getIdHealthRange()) {
                                    food.setHealthRangeName(healthRangesApi.getHealthRangeApis().get(k).getName().trim());
                                    break;
                                }
                            }
                        }
                        food.setPortion(foodsApi.getFoodApis().get(i).getPortion().trim());
                        food.setSodium(foodsApi.getFoodApis().get(i).getSodium().trim());
                        food.setPotassium(foodsApi.getFoodApis().get(i).getPotassium().trim());
                        food.setPhosphor(foodsApi.getFoodApis().get(i).getPhosphor().trim());
                        foods.add(food);
                    }
                }
                jsonFoods = gson.toJson(foods);
                //changeView(numTargets);
                mAdapter.getFilter().filter("");
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                sendJsonFoods(jsonFoods);
            }

            @Override
            public void onFailure(Call<FoodsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Alerta para mostrar una food
    private void showAlertForViewFood(Food food) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflaterAlertFoods = getActivity().getLayoutInflater();
        viewAlertFoods = layoutInflaterAlertFoods.inflate(R.layout.custom_alert_food_rules_foods, null);
        textViewAlertRecomendFoodRulesFoods = viewAlertFoods.findViewById(R.id.textViewAlertRecomendFoodRulesFoods);
        imageViewAlertFoodRulesFoods = viewAlertFoods.findViewById(R.id.imageViewAlertFoodRulesFoods);
        Picasso.get().load(food.getImage()).fit().placeholder(loadingImage).error(errorImage).into(imageViewAlertFoodRulesFoods);
        textViewAlertPorcionFoodRulesFood=viewAlertFoods.findViewById(R.id.textViewAlertPorcionFoodRulesFood);
        textViewAlertSodioFoodRulesFood=viewAlertFoods.findViewById(R.id.textViewAlertSodioFoodRulesFood);
        textViewAlertPotasioFoodRulesFood=viewAlertFoods.findViewById(R.id.textViewAlertPotasioFoodRulesFood);
        textViewAlertFosforoFoodRulesFood=viewAlertFoods.findViewById(R.id.textViewAlertFosforoFoodRulesFood);
        layoutDataFoodRulesFoods=viewAlertFoods.findViewById(R.id.layoutDataFoodRulesFoods);
        textViewAlertNameFoodRulesFoods=viewAlertFoods.findViewById(R.id.textViewAlertNameFoodRulesFoods);
        builder.setView(viewAlertFoods);
        textViewAlertNameFoodRulesFoods.setText(food.getName());
        switch (food.getHealthRangeName()) {
            case "Healthy":
                textViewAlertRecomendFoodRulesFoods.setText(muyrecomendado);
                textViewAlertRecomendFoodRulesFoods.setBackgroundColor(colorVerde);
                layoutDataFoodRulesFoods.setBackgroundColor(colorVerde);
                break;
            case "Medium":
                textViewAlertRecomendFoodRulesFoods.setText(pocorecomendado);
                textViewAlertRecomendFoodRulesFoods.setBackgroundColor(colorMarron);
                layoutDataFoodRulesFoods.setBackgroundColor(colorMarron);
                break;
            case "Harmful":
                textViewAlertRecomendFoodRulesFoods.setText(norecomendado);
                textViewAlertRecomendFoodRulesFoods.setBackgroundColor(colorRojo);
                layoutDataFoodRulesFoods.setBackgroundColor(colorRojo);
                break;
        }
        textViewAlertPorcionFoodRulesFood.setText(unaporcion + " " + food.getPortion() + " " + contiene + ":");
        textViewAlertSodioFoodRulesFood.setText(sodio + "\n" + food.getSodium());
        textViewAlertPotasioFoodRulesFood.setText(potasio + "\n" + food.getPotassium());
        textViewAlertFosforoFoodRulesFood.setText(fosforo + "\n" + food.getPhosphor());

        builder.setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Metodo para enviar el json del food
    private void sendJsonFoods(String jsonFoods) {
        callback.tryAgainJsonFoods(jsonFoods);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
