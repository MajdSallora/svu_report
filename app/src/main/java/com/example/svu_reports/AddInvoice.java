package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.AgentModel;
import com.example.svu_reports.database.model.InvoiceDetailsModel;
import com.example.svu_reports.database.model.InvoiceModel;
import com.example.svu_reports.database.model.MaterialModel;
import com.example.svu_reports.database.model.TypeModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddInvoice extends ThemeManager {

   com.google.android.material.textfield.TextInputEditText numberInvoice,priceInvoice,des;
   TextView totalInvoice;
   com.google.android.material.button.MaterialButton save;
   Spinner agentListDrop,materialListDrop;
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      boolean isSale = getIntent().getBooleanExtra("isSale",false);
      getSupportActionBar().setTitle("إضافة فاتورة");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      setContentView(R.layout.invoice_add);
      DatabaseHelper databaseHelperType = new DatabaseHelper(AddInvoice.this);
      DatabaseHelper databaseHelperAgent= new DatabaseHelper(AddInvoice.this);

      List<MaterialModel> materialModelList = databaseHelperType.getMaterialList();
      List<AgentModel> agentModelList = databaseHelperType.getAgentList();

      priceInvoice = findViewById(R.id.priceInvoice);
      numberInvoice = findViewById(R.id.numberInvoice);
      des = findViewById(R.id.desInvoice);
      save = findViewById(R.id.saveInvoice);
      agentListDrop = findViewById(R.id.agentListDrop);
      materialListDrop = findViewById(R.id.typeListDrop);

      ArrayList<String> type = new ArrayList<String>();
      ArrayList<String> agent = new ArrayList<String>();


      for (MaterialModel element : materialModelList)
      {
         type.add(element.getName());
      }
      for (AgentModel element : agentModelList)
      {
         agent.add(element.getName());
      }
      ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, R.layout.custom_text_spinner, type);
      ArrayAdapter<String> agentAdapter = new ArrayAdapter<>(this, R.layout.custom_text_spinner, agent);
      agentListDrop.setAdapter(agentAdapter);
      materialListDrop.setAdapter(typeAdapter);

      save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            try{
               final String price = priceInvoice.getText().toString();
               final String number = numberInvoice.getText().toString();
               final String desc = des.getText().toString();

                  if(materialModelList.isEmpty()){
                     Toast.makeText(AddInvoice.this,"لايوجد لديك منتجات يرجى إضافة منتجات",Toast.LENGTH_SHORT).show();
                  }else if(agentModelList.isEmpty()){
                     Toast.makeText(AddInvoice.this,"لايوجد لديك عملاء يرجى إضافة عملاء",Toast.LENGTH_SHORT).show();
                  }else{
                     if(!price.isEmpty() && !number.isEmpty() && !desc.isEmpty()){

                        int materialId = 0;
                     int agentId = 0;
                     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        materialId = materialModelList.stream().filter(x -> Objects.equals(x.getName(), materialListDrop.getSelectedItem().toString())).findFirst().get().getId();
                        agentId = agentModelList.stream().filter(x -> Objects.equals(x.getName(), agentListDrop.getSelectedItem().toString())).findFirst().get().getId();
                     }
                     DatabaseHelper databaseHelperClass = new DatabaseHelper(AddInvoice.this);
                     int id = 0;
                     InvoiceModel data = new InvoiceModel(agentId,isSale,desc);
                     databaseHelperClass.addInvoice(data);
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        id = databaseHelperClass.getInvoiceList().stream().filter(x -> Objects.equals(x.getDescription(), data.getDescription()) && Objects.equals(x.getAgent_id(), data.getAgent_id()) && x.isBuy() == data.isBuy()).findFirst().get().getId();
                     }

                     InvoiceDetailsModel invoiceDetailsModel = new InvoiceDetailsModel(id,materialId,Integer.parseInt(number),Integer.parseInt(price),Integer.parseInt(number) * Integer.parseInt(price));



                     databaseHelperClass.addInvoiceDetails(invoiceDetailsModel);
                     Toast.makeText(AddInvoice.this,"تمت العملية بنجاح",Toast.LENGTH_SHORT).show();
                     }else{
                        Toast.makeText(AddInvoice.this,"يرجى تعبئة البيانات بشكل كامل",Toast.LENGTH_SHORT).show();
                     }
                  }


            }catch (Exception e){
               Log.i("INSTERT ERROR",e.toString());
               e.printStackTrace();
               Toast.makeText(AddInvoice.this,"حدث خطأ ما", Toast.LENGTH_SHORT).show();
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
