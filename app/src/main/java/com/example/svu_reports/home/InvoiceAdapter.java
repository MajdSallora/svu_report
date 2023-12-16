package com.example.svu_reports.home;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svu_reports.R;
import com.example.svu_reports.database.DatabaseHelper;
import com.example.svu_reports.database.model.InvoiceDetailsModel;
import com.example.svu_reports.database.model.InvoiceModel;
import com.example.svu_reports.database.model.TypeModel;
import java.util.List;
import java.util.Objects;

public class InvoiceAdapter extends BaseAdapter {
    Context context;
    List<InvoiceModel> data;
    LayoutInflater inflter;

    public InvoiceAdapter(Context applicationContext, List<InvoiceModel> data) {
        this.context = applicationContext;
        this.data = data;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.invoice_list, null); // inflate the layout

        TextView date = (TextView) view.findViewById(R.id.invoiceDate); // ge
        TextView des = (TextView) view.findViewById(R.id.invoiceDes);
        TextView agentName = (TextView) view.findViewById(R.id.agentInvoiceName);
        TextView invoicePrice = (TextView) view.findViewById(R.id.invoicePrice);
        TextView invoiceTotal = (TextView) view.findViewById(R.id.invoiceTotal);
        TextView invoiceNumber = (TextView) view.findViewById(R.id.invoiceNumber);



        date.setText(( this.data.get(i)).getDate());
        des.setText(( this.data.get(i)).getDescription());
//        isBuy.setText((this.data.get(i)).isBuy() ? "مبيع" :"مشترى");
        int agentId = this.data.get(i).getAgent_id();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        String agent = "";
        InvoiceDetailsModel invoiceDetails = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
////            Toast.makeText(context,String.valueOf(databaseHelper.getInvoiceDetailsList().stream().findFirst().get().getInvoice_id()), Toast.LENGTH_SHORT).show();
//            Toast.makeText(context,String.valueOf(data.get(i).getId()), Toast.LENGTH_SHORT).show();
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            invoiceDetails = databaseHelper.getInvoiceDetailsList().stream().filter(x -> Objects.equals(x.getInvoice_id(), data.get(i).getId())).findFirst().get();
            agent = databaseHelper.getAgentList().stream().filter(x -> Objects.equals(x.getId(), agentId)).findFirst().get().getName();
        }
        if (data.get(i).isBuy()) {
            agentName.setText("اسم البائع: " + agent);
        } else {
            agentName.setText("اسم المشتري: " + agent);
        }
        invoicePrice.setText("السعر: "+ invoiceDetails.getPrice());
        invoiceTotal.setText("الاجمالي: "+ invoiceDetails.getTotal());
        invoiceNumber.setText("الكمية: "+ invoiceDetails.getNumber());


        return view;
    }
}
