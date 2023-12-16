package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.TypeModel;
import java.util.ArrayList;
import java.util.List;

public class Type extends ThemeManager {

   com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton fabAddType;
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      ListView typeList;
      List<TypeModel> list = new ArrayList<TypeModel>();
      DatabaseHelper databaseHelperClass = new DatabaseHelper(Type.this);



      list = databaseHelperClass.getTypeList();

      setContentView(R.layout.type_screen);

      fabAddType = findViewById(R.id.fabAddType);

      getSupportActionBar().setTitle("الأنواع");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      typeList = findViewById(R.id.typeList);


      TypeAdapter customAdapter = new TypeAdapter(getApplicationContext(), list);
      typeList.setAdapter(customAdapter);

      fabAddType.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            startActivity(new Intent(v.getContext(),TypeAdd.class));
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
