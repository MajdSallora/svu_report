package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.AgentModel;

public class AgentAdd extends ThemeManager {

   com.google.android.material.textfield.TextInputEditText name,des;
   com.google.android.material.button.MaterialButton save;
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      getSupportActionBar().setTitle("إضافة عميل");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      setContentView(R.layout.type_add);

      name = findViewById(R.id.typeName);
      des = findViewById(R.id.typeDes);
      save = findViewById(R.id.typeSave);

      save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            try{
               final String typeName = name.getText().toString();
               final String typeDes = des.getText().toString();
               if(!typeDes.isEmpty() && !typeName.isEmpty()){
                  DatabaseHelper databaseHelperClass = new DatabaseHelper(AgentAdd.this);
                  AgentModel data = new AgentModel(typeName.toString(),typeDes.toString());
                  databaseHelperClass.addAgent(data);
                  Toast.makeText(AgentAdd.this,"تمت العملية بنجاح",Toast.LENGTH_SHORT).show();
               }else{
                  Toast.makeText(AgentAdd.this,"يرجى تعبئة البيانات بشكل كامل",Toast.LENGTH_SHORT).show();
               }
            }catch (Exception e){
               Toast.makeText(AgentAdd.this,"حدث خطأ ما",Toast.LENGTH_SHORT).show();
            }
         }
      });



   }

   @Override
   public boolean onSupportNavigateUp() {
      finish();
      return true;
   }
}
