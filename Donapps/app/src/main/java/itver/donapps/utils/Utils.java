package itver.donapps.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;
import itver.donapps.models.User;

public class Utils {

    //TEST-ARRAYS
    private static final String[] genders ={"Elige tu genero", "Hombre", "Mujer"};
    private static final String[] categoria ={"Elige tu categoría", "Donador", "Receptor"};
    private static final String[] bloodGroups ={"Elige tu tipo de sangre", "A+", "A-", "B+","B-","AB+","AB-","O+","O-"};
    private static User currentUser;


    public static void createCurrentUser(User user){
        currentUser=user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static String[] getGenders(){ return genders;}

    public static String[] getBloodGroups(){return bloodGroups;}

    public static String[] getCategoria(){return categoria;}

    public static boolean checkEmail(String email) {

        return  (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


}
