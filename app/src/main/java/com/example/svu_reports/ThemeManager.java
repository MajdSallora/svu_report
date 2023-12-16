package com.example.svu_reports;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeManager extends AppCompatActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
      String dynTheme =  sharedPreferences.getString("theme","");
      boolean isSoundEnabled = sharedPreferences.getBoolean("isSoundEnabled", true);

      if(dynTheme.equals("defaultTheme")){
         setTheme(R.style.DefaultTheme);
      }else if(dynTheme.equals("green")){
         setTheme(R.style.GreenTheme);
      }else if(dynTheme.equals("yellow")){
         setTheme(R.style.YellowTheme);
      }else if(dynTheme.equals("blue")){
         setTheme(R.style.BlueTheme);
      }else if(dynTheme.equals("purple")){
         setTheme(R.style.PurpleTheme);
      }
      AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
      if(isSoundEnabled){
         audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
      }else{
         audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
      }
      super.onCreate(savedInstanceState);
   }
}
