package com.example.svu_reports;
import android.annotation.SuppressLint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.checkbox.MaterialCheckBox;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Setting extends ThemeManager {
    final String tag = ".Setting";
    LinearLayout firstTheme, secondTheme, thirdTheme, fourthTheme, fifthTheme;

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        String dynTheme = sharedPreferences.getString("theme", "");
        boolean isSoundEnabled = sharedPreferences.getBoolean("isSoundEnabled", true);

        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("الإعدادات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.setting);
        firstTheme = findViewById(R.id.defualtTheme);
        secondTheme = findViewById(R.id.greenTheme);
        thirdTheme = findViewById(R.id.yellowTheme);
        fourthTheme = findViewById(R.id.blueTheme);
        fifthTheme = findViewById(R.id.purbleTheme);
        MaterialCheckBox soundCheck = findViewById(R.id.btnDisableSound);
        if (isSoundEnabled) {
            soundCheck.setChecked(true);
        } else {
            soundCheck.setChecked(false);
//            soundCheck.setSoundEffectsEnabled(false);
//            firstTheme.setSoundEffectsEnabled(false);
//            secondTheme.setSoundEffectsEnabled(false);
//            thirdTheme.setSoundEffectsEnabled(false);
//            fourthTheme.setSoundEffectsEnabled(false);
//            fifthTheme.setSoundEffectsEnabled(false);
        }

        if (dynTheme.equals("theme1")) {
            firstTheme.setBackgroundResource(R.drawable.border);
        } else if (dynTheme.equals("theme2")) {
            secondTheme.setBackgroundResource(R.drawable.border);
        } else if (dynTheme.equals("theme3")) {
            thirdTheme.setBackgroundResource(R.drawable.border);
        } else if (dynTheme.equals("theme4")) {
            fourthTheme.setBackgroundResource(R.drawable.border);
        } else if (dynTheme.equals("theme5")) {
            fifthTheme.setBackgroundResource(R.drawable.border);
        }

        soundCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundCheck.isChecked()) {
                    myEdit.putBoolean("isSoundEnabled", true).apply();
                    myEdit.commit();
//                    soundCheck.setSoundEffectsEnabled(true);
//                    firstTheme.setSoundEffectsEnabled(true);
//                    secondTheme.setSoundEffectsEnabled(true);
//                    thirdTheme.setSoundEffectsEnabled(true);
//                    fourthTheme.setSoundEffectsEnabled(true);
//                    fifthTheme.setSoundEffectsEnabled(true);
                } else {
                    myEdit.putBoolean("isSoundEnabled", false).apply();
                    myEdit.commit();
//                    soundCheck.setSoundEffectsEnabled(false);
//                    firstTheme.setSoundEffectsEnabled(false);
//                    secondTheme.setSoundEffectsEnabled(false);
//                    thirdTheme.setSoundEffectsEnabled(false);
//                    fourthTheme.setSoundEffectsEnabled(false);
//                    fifthTheme.setSoundEffectsEnabled(false);

                }
            }
        });
        firstTheme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                myEdit.putString("theme", "defaultTheme");
                myEdit.commit();
                finish();
                startActivity(getIntent());
            }
        });

        secondTheme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myEdit.putString("theme", "green");
                myEdit.commit();
                finish();
                startActivity(getIntent());
            }
        });
        thirdTheme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myEdit.putString("theme", "yellow");
                myEdit.commit();
                finish();
                startActivity(getIntent());
            }
        });
        fourthTheme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myEdit.putString("theme", "blue");
                myEdit.commit();
                finish();
                startActivity(getIntent());
            }
        });

        fifthTheme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myEdit.putString("theme", "purple");
                myEdit.commit();
                finish();
                startActivity(getIntent());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
