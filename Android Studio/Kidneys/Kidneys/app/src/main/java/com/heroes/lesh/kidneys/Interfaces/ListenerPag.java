package com.heroes.lesh.kidneys.Interfaces;

import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.Recipe;

public interface ListenerPag {
    //metodo para cambiar fragment dentro de fragment
    void openFragment(int num);

    //Metodo para cerrar activity de view pager y abrir otro activity
    void closeViewPagerAndOpenActivity();

    //Metodo para enviar receta
    void sendRecipe(Recipe recipe);

    //Metodo para enviar seguimiento
    void sendFollow(Follow follow);

    //Metodo para enviar el alimento
    void sendFood(Food food);

    //Metodo para enviar la pregunta
    void sendQuestion(QuestionApi questionApi);

    //Metodo para enviar la guia
    void sendGuide(GuideApi guideApi);

    //Metodo para enviar el followDay
    void sendFollowDay(FollowsDayApi followsDayApi);

    //Metodo para reenviar el jsonFoods al main
    void tryAgainJsonFoods(String jsonFoods);

    //Metodo para reenviar el jsonRecipesal main
    void tryAgainJsonRecipes(String jsonRecipes);

    //Metodo para reenviar el jsonFollos
    void tryAgainJsonFollows(String jsonFollows);

    //Metodo para reenvar el jsonQuestions
    void tryAgainJsonQuestions(String jsonQuestions);

    //Metodo para reenviar el jsonLocations
    void tryAgainJsonLocations(String jsonLocations);

    //Metodo para reenviar el jsonGuides
    void tryAgainJsonGuides(String jsonGuides);

    //Metodo para reenviar el jsonUser
    void tryAgainJsonUser(String jsonUser);

    //Metodo para reeenviar el jsonFollowsDay
    void tryAgainJsonFollowsDay(String jsonFollowsDay);

    //Metodo para refrescar el color
    void refreshColor(int color);

    //Metodo para quitar fragment
    void closeFragment();
}
