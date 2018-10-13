package com.heroes.lesh.kidneys.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.heroes.lesh.kidneys.Activities.LoginActivity;
import com.heroes.lesh.kidneys.Activities.MainActivity;
import com.heroes.lesh.kidneys.Activities.WelcomeActivity;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.Location;
import com.heroes.lesh.kidneys.Models.ModelsApi.CategoriesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDaysApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FoodsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuidesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.LocationsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.RecipesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserApi;
import com.heroes.lesh.kidneys.Models.Recipe;
import com.heroes.lesh.kidneys.R;
import com.facebook.AccessToken;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    //variables globales
    private Intent intent;
    private SharedPreferences preferences;
    private CategoriesApi categoriesApi;
    private HealthRangesApi healthRangesApi;
    private FoodsApi foodsApi;
    private RecipesApi recipesApi;
    private FollowsApi followApis;
    private FollowsDaysApi followsDaysApisArray;
    private QuestionsApi questionsApiArray;
    private LocationsApi locationsApi;
    private GuidesApi guidesApiArray;
    private KidneysService kidneysService;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    private Gson gson;
    // private String jsonCategoriesApi;
    private String jsonFoods;
    private String jsonRecipes;
    private String jsonFollows;
    private String jsonFollowsDay;
    private String jsonQuestions;
    private String jsonLocations;
    private String jsonGuides;
    private String jsonUser;
    //private String jsonIngredientsApi;
    private Food food;
    private Recipe recipe;
    private Follow follow;
    private FollowsDayApi followsDayApi;
    private QuestionApi questionApi;
    private Location location;
    private GuideApi guideApi;
    private UserApi userApi;
    private ArrayList<Food> foods;
    private ArrayList<Recipe> recipes;
    private ArrayList<Follow> follows;
    private ArrayList<FollowsDayApi> followsDayApis;
    private ArrayList<QuestionApi> questionApis;
    private ArrayList<Location> locations;
    private ArrayList<GuideApi> guideApis;
    private String category;
    private int i;
    private int j;
    private int k;
    private String email;
    private int watchWelcome;
    private String cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Poner la actividad en pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        inicializar();
        createPreferences();
        if (AccessToken.getCurrentAccessToken() == null && GoogleSignIn.getLastSignedInAccount(this)==null &&(TextUtils.equals(email, "") || TextUtils.equals(email, "no email"))) {
            intent = new Intent(this, LoginActivity.class);
            //Agregar banderas
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            //Iniciar la actividad aun si falla temporal
            startActivity(intent);
            //Quitar animacion de transicion
            overridePendingTransition(0, 0);
        } else {
            if (watchWelcome()) {
                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, WelcomeActivity.class);
            }
            //Intento cargar los datos solo si la sesion esta iniciada validando con el preferences y token facebook
            if (TextUtils.equals(cargar, "yes")) {
                chargeData();
            } else {
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }

        }
    }

    //Metodo para verificar si vio el welcome
    private boolean watchWelcome() {
        if (watchWelcome == 0 || watchWelcome == 1) {
            return false;
        } else {
            return true;
        }
    }

    //Metodo para cargar los datos
    private void chargeData() {
        if (!TextUtils.equals(email, "no email")) {
            selectUser();
        } else {
            intent = new Intent(this, LoginActivity.class);
            //Agregar banderas
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            //Iniciar la actividad aun si falla temporal
            startActivity(intent);
            //Quitar animacion de transicion
            overridePendingTransition(0, 0);
        }
    }

//Metodo para cargar el usuario
    private void selectUser(){
        kidneysService.selectUserApi(email).enqueue(new retrofit2.Callback<UserApi>() {
            @Override
            public void onResponse(Call<UserApi> call, Response<UserApi> response) {
                userApi=response.body();
                jsonUser = gson.toJson(userApi);
                intent.putExtra("User", jsonUser);
                selectCategories();
            }

            @Override
            public void onFailure(Call<UserApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }
    //Metodo para cargar las categorias de la api
    private void selectCategories() {
        kidneysService.selectCategoriesApi().enqueue(new retrofit2.Callback<CategoriesApi>() {
            @Override
            public void onResponse(Call<CategoriesApi> call, Response<CategoriesApi> response) {
                categoriesApi = response.body();
                //Toast.makeText(getApplicationContext(),categoriesApi.getCategoriesApi().get(0).getName(),Toast.LENGTH_SHORT).show();
                selectHealthRanges();
                //Enviar datos a otra acctivity , pero lo cambiare y creare las listas ya completas de la app , por la api regresa all separado
                //Como es una clase, la clase tiene el metodo para regresar el array , por eso es necesario poner categoriesApi.getCategoriesApi() en ves de categories solo
                // jsonCategoriesApi = gson.toJson(categoriesApi.getCategoriesApi());
                //intent.putExtra("CategoriesApi", jsonCategoriesApi);
            }

            @Override
            public void onFailure(Call<CategoriesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
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
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
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
                intent.putExtra("Foods", jsonFoods);
                selectRecipes();
            }

            @Override
            public void onFailure(Call<FoodsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }

    //Metodo para cargar las recetas de la api y meterlas en la receta de la app
    private void selectRecipes() {
        kidneysService.selectRecipesApi().enqueue(new retrofit2.Callback<RecipesApi>() {
            @Override
            public void onResponse(Call<RecipesApi> call, Response<RecipesApi> response) {
                recipesApi = response.body();
                //jsonRecipesApi = gson.toJson(recipesApi.getRecipeApis());
                //intent.putExtra("RecipesApi", jsonRecipesApi);
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
                intent.putExtra("Recipes", jsonRecipes);
                selectFollows();
            }

            @Override
            public void onFailure(Call<RecipesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }

    //Metodo para cargar los follows
    private void selectFollows() {
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
                            for (j = 0; j < healthRangesApi.getHealthRangeApis().size(); j++) {
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
                intent.putExtra("Follows", jsonFollows);
                selectFollowsDay();
            }

            @Override
            public void onFailure(Call<FollowsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }
private void selectFollowsDay(){
        kidneysService.selectFollowsDayApi(email).enqueue(new Callback<FollowsDaysApi>() {
            @Override
            public void onResponse(Call<FollowsDaysApi> call, Response<FollowsDaysApi> response) {
                followsDaysApisArray=response.body();
                if(followsDaysApisArray!=null){
                    for (i=0;i<followsDaysApisArray.getFollowsDayApis().size();i++){
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
                intent.putExtra("FollowsDays", jsonFollowsDay);
                selectQuestions();
            }

            @Override
            public void onFailure(Call<FollowsDaysApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
}
    //Metodo para seleccionar las preguntas
    private void selectQuestions() {
        kidneysService.selectQuestionsApi().enqueue(new retrofit2.Callback<QuestionsApi>() {
            @Override
            public void onResponse(Call<QuestionsApi> call, Response<QuestionsApi> response) {
                questionsApiArray = response.body();
                if (questionsApiArray != null) {
                    for (i = 0; i < questionsApiArray.getQuestionApis().size(); i++) {
                        questionApi = new QuestionApi();
                        questionApi.setIdQuestion(questionsApiArray.getQuestionApis().get(i).getIdQuestion());
                        questionApi.setQuestion(questionsApiArray.getQuestionApis().get(i).getQuestion());
                        questionApi.setAnswer(questionsApiArray.getQuestionApis().get(i).getAnswer());
                        questionApis.add(questionApi);
                    }
                }
                jsonQuestions = gson.toJson(questionApis);
                intent.putExtra("Questions", jsonQuestions);
                selectLocations();
            }

            @Override
            public void onFailure(Call<QuestionsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }

    //Metodo para seleccionar las locaciones
    private void selectLocations() {
        kidneysService.selectLocationsApi().enqueue(new retrofit2.Callback<LocationsApi>() {
            @Override
            public void onResponse(Call<LocationsApi> call, Response<LocationsApi> response) {
                locationsApi = response.body();
                if (locationsApi != null) {
                    for (i = 0; i < locationsApi.getLocationApis().size(); i++) {
                        location = new Location();
                        location.setIdLocation(locationsApi.getLocationApis().get(i).getIdLocation());
                        location.setName(locationsApi.getLocationApis().get(i).getName());
                        location.setLongitude(locationsApi.getLocationApis().get(i).getLongitude());
                        location.setLatitude(locationsApi.getLocationApis().get(i).getLatitude());
                        if (categoriesApi != null) {
                            for (j = 0; j < categoriesApi.getCategoriesApi().size(); j++) {
                                if (categoriesApi.getCategoriesApi().get(j).getIdCategory() == locationsApi.getLocationApis().get(i).getIdcategory()) {
                                    category = categoriesApi.getCategoriesApi().get(j).getName().trim();
                                    location.setCategory(category.trim());
                                    break;
                                }
                            }
                        }
                        locations.add(location);
                    }
                }
                jsonLocations = gson.toJson(locations);
                intent.putExtra("Locations", jsonLocations);
                selectGuides();
            }

            @Override
            public void onFailure(Call<LocationsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }

    //Metodo para seleccionar las guias
    private void selectGuides() {
        kidneysService.selectGuidesApi().enqueue(new retrofit2.Callback<GuidesApi>() {
            @Override
            public void onResponse(Call<GuidesApi> call, Response<GuidesApi> response) {
                guidesApiArray = response.body();
                if (guidesApiArray != null) {
                    for (i = 0; i < guidesApiArray.getGuideApis().size(); i++) {
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
                intent.putExtra("Guides", jsonGuides);
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }

            @Override
            public void onFailure(Call<GuidesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                //Agregar banderas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //Iniciar la actividad aun si falla temporal
                startActivity(intent);
                //Quitar animacion de transicion
                overridePendingTransition(0, 0);
            }
        });
    }



    //Metodo para inicializar componentes
    private void inicializar() {
        kidneysService = Api.getApi().create(KidneysService.class);
        inicializarToast();
        foods = new ArrayList<Food>();
        recipes = new ArrayList<Recipe>();
        follows = new ArrayList<Follow>();
        questionApis = new ArrayList<QuestionApi>();
        locations = new ArrayList<Location>();
        guideApis = new ArrayList<GuideApi>();
       followsDayApis=new ArrayList<FollowsDayApi>();
        gson = new Gson();
        category = "";
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        watchWelcome = preferences.getInt("WatchWelcome", 0);
        cargar = preferences.getString("Cargar", "");
    }

    //Metodo para crear el shared preferences
    private void createPreferences() {
        if (ifNoExistPreferences()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("WatchWelcome", 1);
            editor.putInt("NumTargetsGuides", 1);
            editor.putInt("NumTargetsRecipes", 1);
            editor.putInt("NumTargetsFoods", 1);
            editor.putInt("NumTargetsFollows", 1);
            editor.putInt("NumTargetsQuestions", 1);
            editor.putInt("NumTargetsTreatments", 1);
            editor.putInt("NumTargetsFollowsDay",1);
            editor.putInt("ColorApp", 1);
            editor.putString("Cargar", "no");
            editor.putString("Email", "no email");
            editor.putString("RememberEmail","");
            editor.putString("RememberPassword","");
            editor.putString("PhoneEmergency","");
            editor.apply();
        }
        email = preferences.getString("Email", "");
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar el renderizado del toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para comprobar si existe  el shared preferences
    private boolean ifNoExistPreferences() {
        if (
                preferences.getInt("WatchWelcome", 0) == 0 &&
                        preferences.getInt("NumTargetsGuides", 0) == 0 &&
                        preferences.getInt("NumTargetsRecipes", 0) == 0 &&
                        preferences.getInt("NumTargetsFoods", 0) == 0 &&
                        preferences.getInt("NumTargetsFollows", 0) == 0 &&
                        preferences.getInt("NumTargetsQuestions", 0) == 0 &&
                        preferences.getInt("NumTargetsTreatments", 0) == 0 &&
                        preferences.getInt("NumTargetsFollowsDay", 0) == 0 &&
                        preferences.getInt("ColorApp", 0) == 0 &&
                        preferences.getString("Cargar", "") == "" &&
                        preferences.getString("RememberEmail", "") == "" &&
                        preferences.getString("RememberPassword", "") == "" &&
                        preferences.getString("PhoneEmergency", "") == ""&&
                        preferences.getString("Email", "") == "") {
            return true;
        } else {
            return false;
        }

    }
}
