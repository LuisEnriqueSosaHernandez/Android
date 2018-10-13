package com.heroes.lesh.kidneys.Interfaces.InterfacesApi;

import com.heroes.lesh.kidneys.Models.ModelsApi.CategoriesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowDeleteApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayDeleteApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDaysApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.FoodsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuidesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.HealthRangesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.LocationsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionsApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.RecipesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserUpdatePasswordApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.ValidateUserApi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KidneysService {
    //Para registrar un nuevo usuario
    @FormUrlEncoded
    @POST("index.php/CreateUser")
    Call<UserCreateApi> createUserApi(@Field("email") String email, @Field("password") String password, @Field("name") String name,
                                      @Field("gender") String gender, @Field("image") String image,
                                      @Field("dateofbirth") String dateofbirthday, @Field("weight") double weight, @Field("height") double height,
                                      @Field("datecatheter") String datecatheter, @Field("typeofsolution") double typeofsolution,
                                      @Field("imc") double imc, @Field("typeoftreatment") String typeoftreatment,
                                      @Field("emergencycontact") String emergencycontact);

    //Para recuperar el usuario
    @GET("index.php/User/{email}")
    Call<UserApi> selectUserApi(@Path("email") String email);

    //Para recuperar las healthRanges
    @GET("index.php/HealthRanges")
    Call<HealthRangesApi> selectHealthRangesApi();

    //Para recuperar las categotrias
    @GET("index.php/Categories")
    Call<CategoriesApi> selectCategoriesApi();

    //Para recuperar los alimentos
    @GET("index.php/Foods")
    Call<FoodsApi> selectFoodsApi();

    //Para recuperar las recetas
    @GET("index.php/Recipes")
    Call<RecipesApi> selectRecipesApi();

    //Para recuperar los follows
    @GET("index.php/Follows/{email}")
    Call<FollowsApi> selectFollowsApi(@Path("email") String email);

    //Para crear un nuevo follows
    @FormUrlEncoded
    @POST("index.php/CreateFollow")
    Call<FollowCreateApi> createFollowApi(@Field("date") String date, @Field("email") String email, @Field("idhealthrange") int idhealthrange);

    //Para eliminar un follow
    @DELETE("index.php/DeleteFollow/{idfollow}")
    Call<FollowDeleteApi> deleteFollowApi(@Path("idfollow") int idfollow);

    //Para recuperar los followsDay
    @GET("index.php/FollowsDay/{email}")
    Call<FollowsDaysApi> selectFollowsDayApi(@Path("email") String email);

    //Para eliminar un followday
    @DELETE("index.php/DeleteFollowDay/{idfollowday}")
    Call<FollowsDayDeleteApi> deleteFollowsDayApi(@Path("idfollowday") int idfollowday);

    //Para crear un nuevo follows
    @FormUrlEncoded
    @POST("index.php/CreateFollowDay")
    Call<FollowsDayCreateApi> createFollowsDayApi(@Field("typeofsolution") double typeofsolution, @Field("start") String start,
                                                  @Field("end") String end, @Field("drainage") double drainage, @Field("uf") double uf,
                                                  @Field("ingestedliquid") double ingestedliquid, @Field("idfollow") int idfollow,
                                                  @Field("email") String email);

    //Para validar el usuario
    @FormUrlEncoded
    @POST("index.php/ValidateUser")
    Call<ValidateUserApi> validateUserApi(@Field("email") String email, @Field("password") String password);

    //Metodo para recuperar las questions
    @GET("index.php/Questions")
    Call<QuestionsApi> selectQuestionsApi();

    //Metodo para recuperar las locations
    @GET("index.php/Locations")
    Call<LocationsApi> selectLocationsApi();

    //Metodo para seleccionar las guias
    @GET("index.php/Guides")
    Call<GuidesApi> selectGuidesApi();

    //Para actualizar password
    @FormUrlEncoded
    @PUT("index.php/UpdatePassword/{email}")
    Call<UserUpdatePasswordApi> updateUserPassswordApi(@Path("email") String email, @Field("password") String password,
                                                       @Field("newpassword") String newpassword);

    //Para actualizar profile
    @FormUrlEncoded
    @PUT("index.php/UpdateProfile/{email}")
    Call<UserUpdatePasswordApi> updateUserProfiledApi(@Path("email") String email, @Field("name") String name,
                                                      @Field("gender") String gender, @Field("dateofbirth") String dateofbirth,
                                                      @Field("weight") double weight, @Field("height") double height,
                                                      @Field("datecatheter") String datecatheter,
                                                      @Field("typeofsolution") double typeofsolution,
                                                      @Field("imc") double imc, @Field("typeoftreatment") String typeoftreatment,
                                                      @Field("emergencycontact") String emergencycontact);
}
