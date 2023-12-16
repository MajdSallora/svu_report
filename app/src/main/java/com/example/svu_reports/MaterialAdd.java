package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.MaterialModel;
import com.example.svu_reports.database.model.TypeModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MaterialAdd extends ThemeManager {

   com.google.android.material.textfield.TextInputEditText name,des;
   com.google.android.material.button.MaterialButton save;
   Spinner typeList;
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.material_add);
      DatabaseHelper databaseHelperType = new DatabaseHelper(MaterialAdd.this);
      getSupportActionBar().setTitle("إضافة مادة");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      List<TypeModel> typeModelList = databaseHelperType.getTypeList();

      name = findViewById(R.id.typeName);
      des = findViewById(R.id.typeDes);
      save = findViewById(R.id.typeSave);
      typeList = findViewById(R.id.typeDrop);

      ArrayList<String> items = new ArrayList<String>();


      for (TypeModel element : typeModelList)
      {
         items.add(element.getName());
      }
      ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_text_spinner, items);
      typeList.setAdapter(adapter);





      save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            try{

                  if(typeModelList.isEmpty()){
                     Toast.makeText(MaterialAdd.this,"لايوجد لديك مواد يرجى إضافة مادة",Toast.LENGTH_SHORT).show();

                  }else{
                     final String typeName = name.getText().toString();
                     final String typeDes = des.getText().toString();
                     final String typeMaterial = typeList.getSelectedItem().toString();
                     if(!typeDes.isEmpty() && !typeName.isEmpty()){

                        DatabaseHelper databaseHelperClass = new DatabaseHelper(MaterialAdd.this);
                     int id = 0;
                     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        id = typeModelList.stream().filter(x -> Objects.equals(x.getName(), typeMaterial)).findFirst().get().getId();
                     }
                     MaterialModel data = new MaterialModel(0,typeName.toString(),id,typeDes.toString());
                     databaseHelperClass.addMaterial(data);
                     Toast.makeText(MaterialAdd.this,"تمت العملية بنجاح",Toast.LENGTH_SHORT).show();
                     }else{
                        Toast.makeText(MaterialAdd.this,"يرجى تعبئة البيانات بشكل كامل",Toast.LENGTH_SHORT).show();
                     }
                  }


            }catch (Exception e){
               Toast.makeText(MaterialAdd.this,"حدث خطأ ما", Toast.LENGTH_SHORT).show();
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
