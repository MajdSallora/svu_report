package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.MaterialModel;

import java.util.ArrayList;
import java.util.List;

public class Material extends ThemeManager {


   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton fabAddMaterial;

      ListView materialList;
      DatabaseHelper databaseHelperClass = new DatabaseHelper(Material.this);

      List<MaterialModel> list = new ArrayList<MaterialModel>();
      list = databaseHelperClass.getMaterialList();

      setContentView(R.layout.material);
      fabAddMaterial = findViewById(R.id.fabAddMaterial);
      getSupportActionBar().setTitle("المواد");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      materialList = findViewById(R.id.materialList);

         AgentAdapter customAdapter = new AgentAdapter(getApplicationContext(), list);
         materialList.setAdapter(customAdapter);
      fabAddMaterial.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            startActivity(new Intent(v.getContext(), MaterialAdd.class));
         }
      });
   }
   @Override
   public boolean onSupportNavigateUp() {
      finish();
      return true;
   }
   @Override
   public void onRestart()
   {
      super.onRestart();
      finish();
      startActivity(getIntent());
   }
}
