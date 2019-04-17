package id.trydev.sabuba.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    public static final String PREFS_NAME = "app_pref";

    private static final String TOKEN = "token";
    private static final String ROLE = "role";

    private final SharedPreferences prefs;

    public AppPreferences(Context context){
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return prefs.getString(TOKEN, null);
    }

    public void setRole(String role){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ROLE, role);
        editor.apply();
    }

    public String getRole(){
        return prefs.getString(ROLE, null);
    }


}