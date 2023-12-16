package com.example.svu_reports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.AgentModel;
import com.example.svu_reports.database.model.InvoiceModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Agent extends ThemeManager {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        boolean isMostSale = intent.getBooleanExtra("mostSale",false);
        ExtendedFloatingActionButton fabAddAgent;
        ListView agentList;
        DatabaseHelper databaseHelperClass = new DatabaseHelper(Agent.this);

        List<AgentModel> list = new ArrayList<AgentModel>();
//      list = databaseHelperClass.getTypeList();
        setContentView(R.layout.agent_screen);
        fabAddAgent = findViewById(R.id.fabAddAgent);
        getSupportActionBar().setTitle(isMostSale ? "العميل المفضل" :"العملاء");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        agentList = findViewById(R.id.agentList);
        if(isMostSale){
            fabAddAgent.hide();
            int mostAgentBuy = databaseHelperClass.getMostAgentBuy();
            if(mostAgentBuy == -1){
                setContentView(R.layout.emptystate);
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    list = databaseHelperClass.getAgentList().stream().filter(x -> Objects.equals(x.getId(), mostAgentBuy)).collect(Collectors.toList());
                }
            }
        }else{
            list = databaseHelperClass.getAgentList();
        }
        AgentAdapter customAdapter = new AgentAdapter(getApplicationContext(), list);
        agentList.setAdapter(customAdapter);
        fabAddAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AgentAdd.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
