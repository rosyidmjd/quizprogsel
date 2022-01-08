package com.app.kuis;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;

   public SharedPrefManager(Context context) {
       sharedPreferences = context.getSharedPreferences("com.app.kuis", context.MODE_PRIVATE);
       editor = sharedPreferences.edit();
       setAsDefaultValue();
   }

   private void setAsDefaultValue() {
       setUsername("rosyid");
       setPassword("4280");
   }

   public void saveString(String value) {
       editor.putString("sp_string", value);
       editor.commit();
   }

   private void setUsername(String usernameValue) {
       editor.putString("sp_username", usernameValue).apply();
   }

   private void setPassword(String passwordValue) {
       editor.putString("sp_password", passwordValue).apply();
    }

    public void saveIsLogin(Boolean value){
       editor.putBoolean("sp_islogin", value);
       editor.commit();
    }

    public String getUsername() {
       return sharedPreferences.getString("sp_username", "");
    }

    public String getPassword() {
       return sharedPreferences.getString("sp_password", "");
    }

    public Boolean getIsLogin() {
       return sharedPreferences.getBoolean("sp_islogin", false);
    }
}