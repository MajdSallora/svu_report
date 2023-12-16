package com.example.svu_reports;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.AgentModel;
import com.example.svu_reports.home.CustomAdapter;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends ThemeManager {

    GridView homeGrid;
    com.google.android.material.textfield.TextInputEditText username,password;
    com.google.android.material.button.MaterialButton login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        if(!isLoggedIn){
            setContentView(R.layout.login);
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            login = findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        final String user = username.getText().toString().toLowerCase();
                        final String pass = password.getText().toString().toLowerCase();
                        if(!user.isEmpty() && !pass.isEmpty()){
                            if(user.equals("admin") && pass.equals("1234")){
                                myEdit.putBoolean("isLoggedIn",true);
                                myEdit.commit();
                                recreate();
                            }else{
                                Toast.makeText(MainActivity.this,"اسم المستخدم او كلمة المرور خاطئة",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"يرجى تعبئة البيانات بشكل كامل",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"حدث خطأ ما",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            setContentView(R.layout.activity_main);
            Resources res = getResources();
            getSupportActionBar().hide();
            String[] homeTitle = {res.getString(R.string.products),res.getString(R.string.type),res.getString(R.string.customer), res.getString(R.string.sales), res.getString(R.string.most_sales), res.getString(R.string.Purchases), res.getString(R.string.logout), res.getString(R.string.settings)};

            homeGrid = findViewById(R.id.homeGrid);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), homeTitle);

            homeGrid.setAdapter(customAdapter);
            homeGrid.setSoundEffectsEnabled(true);
            homeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Resources res = getResources();

                    String data = (String) parent.getItemAtPosition(position);
                    switch (data){
                        case "المبيعات" :
                            Intent intent = new Intent(parent.getContext(), Invoice.class);
                            intent.putExtra("isSale",false);
                            startActivity(intent);
                            break;
                        case "المشتريات" :
                            Intent intent2 = new Intent(parent.getContext(), Invoice.class);
                            intent2.putExtra("isSale",true);
                            startActivity(intent2);
                            break;
                        case "الانواع" :
                            startActivity(new Intent(parent.getContext(), Type.class));
                            break;
                        case "العملاء المفضلة" :
                            Intent agentIntent = new Intent(parent.getContext(), Agent.class);
                            agentIntent.putExtra("mostSale",true);
                            startActivity(agentIntent);
                            break;
                        case "تسجيل الخروج" :
                            myEdit.putBoolean("isLoggedIn",false);
                            myEdit.commit();
                            recreate();
                            break;
                        case "الزبائن" :
                            Intent agentIn = new Intent(parent.getContext(), Agent.class);
                            agentIn.putExtra("mostSale",false);
                            startActivity(agentIn);
                            break;
                        case "المنتجات" :
                            startActivity(new Intent(parent.getContext(), Material.class));
                            break;
                        case "الأعدادات" :
                            startActivity(new Intent(parent.getContext(),Setting.class));
                            break;
                    }

                }
            });
        }

    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}