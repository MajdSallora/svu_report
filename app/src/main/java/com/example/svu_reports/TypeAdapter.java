package com.example.svu_reports;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.svu_reports.database.model.TypeModel;
import java.util.List;

public class TypeAdapter extends BaseAdapter {
   Context context;
   List<TypeModel> data;
   LayoutInflater inflter;

   public TypeAdapter(Context applicationContext, List<TypeModel> data) {
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
      view = inflter.inflate(R.layout.type_list, null); // inflate the layout

         TextView name = (TextView) view.findViewById(R.id.typeName); // ge
         TextView des = (TextView) view.findViewById(R.id.typeDes); //

         name.setText(((TypeModel) this.data.get(i)).getName());
         des.setText(((TypeModel) this.data.get(i)).getDescription());

      return view;
   }
}
