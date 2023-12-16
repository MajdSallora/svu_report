package com.example.svu_reports;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.InvoiceModel;
import com.example.svu_reports.database.model.MaterialModel;
import com.example.svu_reports.home.InvoiceAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Invoice extends ThemeManager {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        boolean isSale = intent.getBooleanExtra("isSale", false);
        ExtendedFloatingActionButton fabAddInvoice;

        ListView materialList;
        DatabaseHelper databaseHelperClass = new DatabaseHelper(Invoice.this);

        List<InvoiceModel> list = new ArrayList<InvoiceModel>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list = databaseHelperClass.getInvoiceList().stream().filter(x -> x.isBuy() == isSale).collect(Collectors.toList());
        }

        setContentView(R.layout.invoice);
        fabAddInvoice = findViewById(R.id.fabAddInvoice);
        getSupportActionBar().setTitle(isSale ? "المشتريات" : "المبيعات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        materialList = findViewById(R.id.invoiceList);

        InvoiceAdapter customAdapter = new InvoiceAdapter(getApplicationContext(), list);
        materialList.setAdapter(customAdapter);
        fabAddInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddInvoice.class);
                intent.putExtra("isSale", isSale);
                startActivity(intent);
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
