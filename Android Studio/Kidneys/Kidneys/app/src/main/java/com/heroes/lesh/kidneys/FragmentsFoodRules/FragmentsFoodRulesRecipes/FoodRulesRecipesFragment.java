package com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesRecipes;


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


import com.heroes.lesh.kidneys.Adapters.Adapter_food_rules_recipes;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.RecipesApi;
import com.heroes.lesh.kidneys.Models.Recipe;
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
public class FoodRulesRecipesFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private KidneysService kidneysService;
    private HealthRangesApi healthRangesApi;
    private Recipe recipe;
    private RecipesApi recipesApi;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.muyrecomendado)
    String muyrecomendado;
    @BindString(R.string.pocorecomendado)
    String pocorecomendado;
    @BindString(R.string.norecomendado)
    String norecomendado;
    @BindString(R.string.todos)
    String todos;
    private ArrayList<Recipe> recipes;
    @BindView(R.id.recyclerviewFoodRulesRecipes)
    RecyclerView mRecyclerView;
    @BindView(R.id.floatingLinesFoodRulesRecipes)
    FloatingActionButton floatingLinesFoodRulesRecipes;
    @BindView(R.id.floatingHealthyFoodRulesRecipes)
    FloatingActionButton floatingHealthyFoodRulesRecipes;
    @BindView(R.id.floatingMediumFoodRulesRecipes)
    FloatingActionButton floatingMediumFoodRulesRecipes;
    @BindView(R.id.floatingHarmfulFoodRulesRecipes)
    FloatingActionButton floatingHarmfulFoodRulesRecipes;
    @BindView(R.id.floatingAllFoodRulesRecipes)
    FloatingActionButton floatingAllFoodRulesRecipes;
    @BindView(R.id.editTextFilterFoodRulesRecipes)
    EditText editTextFilterFoodRulesRecipes;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
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
    @BindDrawable(R.mipmap.ic_food_rules)
    Drawable ic_food_rules;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_medium)
    Drawable ic_medium;
    @BindDrawable(R.drawable.loading)
    Drawable loadingImage;
    @BindDrawable(R.drawable.ic_line)
    Drawable ic_line;
    @BindDrawable(R.drawable.ic_twolines)
    Drawable ic_twolines;
    @BindDrawable(R.drawable.error)
    Drawable errorImage;
    @BindString(R.string.aceptar)
    String aceptar;
    @BindString(R.string.distribucion)
    String distribucion;
    @BindColor(R.color.colorVerde)
    int colorVerde;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    private Adapter_food_rules_recipes mAdapter;
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
    private String jsonRecipes;
    private int i;
    private int j;
    private LayoutInflater layoutInflaterAlertRecipes;
    private View viewAlertRecipes;
    private TextView textViewAlertRecomendFoodRulesRecipes;
    private ImageView imageViewAlertFoodRulesRecipes;
    private TextView textViewAlertPorcionFoodRulesRecipes;
    private TextView textViewAlertSodioFoodRulesRecipes;
    private TextView textViewAlertPotasioFoodRulesRecipes;
    private TextView textViewAlertFosforoFoodRulesRecipes;
    private TextView textViewAlertNameFoodRulesRecipes;
    LinearLayout layoutDataFoodRulesRecipes;

    public FoodRulesRecipesFragment() {
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
        view = inflater.inflate(R.layout.fragment_food_rules_recipes, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        numTargets();
        //cargar las tablas de recipes
        cargarTablaRecipes();
        changeIcon(numTargets);
        mLayoutManager = new GridLayoutManager(getActivity(), numTargets);
        //mLayoutManager=new LinearLayoutManager(getActivity());
//Cmbiando el adapatador para el recycler
        mAdapter = new Adapter_food_rules_recipes(recipes, R.layout.recycler_food_rules_recipes, new Adapter_food_rules_recipes.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe, int position) {
                //Toast.makeText(getActivity(), recipe.getTitle() + " - " + position, Toast.LENGTH_SHORT).show();
                sendRecipe(recipe);
            }

            @Override
            public void onFloatingClick(Recipe recipe) {
                showAlertForViewRecipe(recipe);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        floatingLinesFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
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
        floatingLinesFoodRulesRecipes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(distribucion, ic_food_rules);
                toast.show();
                return false;
            }
        });
        floatingHealthyFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Healthy");
            }
        });
        floatingHealthyFoodRulesRecipes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(muyrecomendado, ic_healthy);
                toast.show();
                return false;
            }
        });
        floatingMediumFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Medium");
            }
        });
        floatingMediumFoodRulesRecipes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(pocorecomendado, ic_medium);
                toast.show();
                return false;
            }
        });
        floatingHarmfulFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("Harmful");
            }
        });
        floatingHarmfulFoodRulesRecipes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(norecomendado, ic_harmful);
                toast.show();
                return false;
            }
        });
        editTextFilterFoodRulesRecipes.addTextChangedListener(new TextWatcher() {
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
        floatingAllFoodRulesRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter("");
            }
        });
        floatingAllFoodRulesRecipes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(todos, ic_food_rules);
                toast.show();
                return false;
            }
        });
        editTextFilterFoodRulesRecipes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        recipes = new ArrayList<Recipe>();
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //metodo para cargar las recetas
    private void cargarTablaRecipes() {
        bundle = getArguments();
        if (bundle != null) {
            jsonRecipes = bundle.getString("Recipes");
            recipes = gson.fromJson(jsonRecipes, new TypeToken<ArrayList<Recipe>>() {
            }.getType());
        } else {
            changeToast(reintentardatos, ic_food_rules);
            toast.show();
            tryAgainCargarTablasRecipes();
        }
    }

    //Metodo para inicializar el toadt
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
        numTargets = preferences.getInt("NumTargetsRecipes", 0);
        if (numTargets == 0) {
            numTargets = 1;
        }
    }

    //Metodo para cambiar el numero de tarjetas
    private void changePreferences(int numTargets) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NumTargetsRecipes", numTargets);
        editor.apply();
    }
    //Metodo para cambiar el icono
    private void changeIcon(int numTargets){
        if(numTargets==1){
            floatingLinesFoodRulesRecipes.setImageDrawable(ic_twolines);
        }else{
            floatingLinesFoodRulesRecipes.setImageDrawable(ic_line);
        }

    }

    //Metodo para enviar el recipe
    private void sendRecipe(Recipe recipe) {
        callback.sendRecipe(recipe);
    }

    //Metodo para reintentar la carga de las tablas
    private void tryAgainCargarTablasRecipes() {
        selectHealthRanges();
    }

    //Metodo para cargar las healthrage de la api
    private void selectHealthRanges() {
        kidneysService.selectHealthRangesApi().enqueue(new retrofit2.Callback<HealthRangesApi>() {
            @Override
            public void onResponse(Call<HealthRangesApi> call, Response<HealthRangesApi> response) {
                healthRangesApi = response.body();
                selectRecipes();
            }

            @Override
            public void onFailure(Call<HealthRangesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Metodo para cargar las recetas de la api y meterlas en la receta de la app
    private void selectRecipes() {
        kidneysService.selectRecipesApi().enqueue(new retrofit2.Callback<RecipesApi>() {
            @Override
            public void onResponse(Call<RecipesApi> call, Response<RecipesApi> response) {
                recipesApi = response.body();
                if (recipesApi != null) {
                    for (i = 0; i < recipesApi.getRecipeApis().size(); i++) {
                        recipe = new Recipe();
                        recipe.setIdRecipe(recipesApi.getRecipeApis().get(i).getIdRecipe());
                        recipe.setTitle(recipesApi.getRecipeApis().get(i).getTitle().trim());
                        recipe.setDescription(recipesApi.getRecipeApis().get(i).getDescription().trim());
                        recipe.setIngredients(recipesApi.getRecipeApis().get(i).getIngredients().trim());
                        recipe.setPrepare(recipesApi.getRecipeApis().get(i).getPrepare().trim());
                        recipe.setImage(recipesApi.getRecipeApis().get(i).getImage());
                        if (healthRangesApi != null) {
                            for (j = 0; j < healthRangesApi.getHealthRangeApis().size(); j++) {
                                if (healthRangesApi.getHealthRangeApis().get(j).getIdHealthRange() == recipesApi.getRecipeApis().get(i).getIdHealthRange()) {
                                    recipe.setHealthRangeName(healthRangesApi.getHealthRangeApis().get(j).getName().trim());
                                    break;
                                }
                            }
                        }
                        recipe.setPortion(recipesApi.getRecipeApis().get(i).getPortion().trim());
                        recipe.setSodium(recipesApi.getRecipeApis().get(i).getSodium().trim());
                        recipe.setPotassium(recipesApi.getRecipeApis().get(i).getPotassium().trim());
                        recipe.setPhosphor(recipesApi.getRecipeApis().get(i).getPhosphor().trim());
                        recipes.add(recipe);
                    }
                }
                jsonRecipes = gson.toJson(recipes);
                //changeView(numTargets);
                mAdapter.getFilter().filter("");
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                sendJsonRecipes(jsonRecipes);
            }

            @Override
            public void onFailure(Call<RecipesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    //Alerta para mostrar una recipe
    private void showAlertForViewRecipe(Recipe recipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflaterAlertRecipes = getActivity().getLayoutInflater();
        viewAlertRecipes = layoutInflaterAlertRecipes.inflate(R.layout.custom_alert_food_rules_recipes, null);
        textViewAlertRecomendFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertRecomendFoodRulesRecipes);
        imageViewAlertFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.imageViewAlertFoodRulesRecipes);
        textViewAlertPorcionFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertPorcionFoodRulesRecipes);
        textViewAlertSodioFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertSodioFoodRulesRecipess);
        textViewAlertPotasioFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertPotasioFoodRulesRecipes);
        textViewAlertFosforoFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertFosforoFoodRulesRecipes);
        layoutDataFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.layoutDataFoodRulesRecipes);
        textViewAlertNameFoodRulesRecipes = viewAlertRecipes.findViewById(R.id.textViewAlertNameFoodRulesRecipes);
        Picasso.get().load(recipe.getImage()).fit().placeholder(loadingImage).error(errorImage).into(imageViewAlertFoodRulesRecipes);
        builder.setView(viewAlertRecipes);
        textViewAlertNameFoodRulesRecipes.setText(recipe.getTitle());
        switch (recipe.getHealthRangeName()) {
            case "Healthy":
                textViewAlertRecomendFoodRulesRecipes.setText(muyrecomendado);
                textViewAlertRecomendFoodRulesRecipes.setBackgroundColor(colorVerde);
                layoutDataFoodRulesRecipes.setBackgroundColor(colorVerde);
                break;
            case "Medium":
                textViewAlertRecomendFoodRulesRecipes.setText(pocorecomendado);
                textViewAlertRecomendFoodRulesRecipes.setBackgroundColor(colorMarron);
                layoutDataFoodRulesRecipes.setBackgroundColor(colorMarron);
                break;
            case "Harmful":
                textViewAlertRecomendFoodRulesRecipes.setText(norecomendado);
                textViewAlertRecomendFoodRulesRecipes.setBackgroundColor(colorRojo);
                layoutDataFoodRulesRecipes.setBackgroundColor(colorRojo);
                break;
        }
        textViewAlertPorcionFoodRulesRecipes.setText(unaporcion + " " + recipe.getPortion() + " " + contiene + ":");
        textViewAlertSodioFoodRulesRecipes.setText(sodio + "\n" + recipe.getSodium());
        textViewAlertPotasioFoodRulesRecipes.setText(potasio + "\n" + recipe.getPotassium());
        textViewAlertFosforoFoodRulesRecipes.setText(fosforo + "\n" + recipe.getPhosphor());
        builder.setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Metodo para enviar el json a la actividad
    private void sendJsonRecipes(String jsonRecipes) {
        callback.tryAgainJsonRecipes(jsonRecipes);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
