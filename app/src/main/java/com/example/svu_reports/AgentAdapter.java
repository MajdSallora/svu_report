package com.example.svu_reports;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.svu_reports.database.model.AgentModel;
import com.example.svu_reports.database.model.MaterialModel;
import com.example.svu_reports.database.model.TypeModel;
import java.util.ArrayList;
import java.util.List;

public class AgentAdapter<T> extends BaseAdapter {
    Context context;
    List<T> title;
    LayoutInflater inflter;

    public AgentAdapter(Context applicationContext, List<T> title) {
        this.context = applicationContext;
        this.title = title;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return title.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.agent_list, null); // inflate the layout

        if(this.title.get(i).getClass().getName().equals(TypeModel.class.getName())){
            TextView name = (TextView) view.findViewById(R.id.agentName); // ge
            TextView des = (TextView) view.findViewById(R.id.agentDes); //

            name.setText(((TypeModel) this.title.get(i)).getName());
            des.setText(((TypeModel) this.title.get(i)).getDescription());

        }else if(this.title.get(i).getClass().getName().equals(MaterialModel.class.getName())){
            TextView name = (TextView) view.findViewById(R.id.agentName); // ge
            TextView des = (TextView) view.findViewById(R.id.agentDes); //

            name.setText(((MaterialModel) this.title.get(i)).getName());
            des.setText(((MaterialModel) this.title.get(i)).getDescription());

        }else if(this.title.get(i).getClass().getName().equals(AgentModel.class.getName())){
            TextView name = (TextView) view.findViewById(R.id.agentName); // ge
            TextView des = (TextView) view.findViewById(R.id.agentDes); //

            name.setText(((AgentModel) this.title.get(i)).getName());
            des.setText(((AgentModel) this.title.get(i)).getDescription());

        }

        return view;
    }
}
