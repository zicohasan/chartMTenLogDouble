package com.qmul.deni.chartmtenlogdouble.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmul.deni.chartmtenlogdouble.CreateActivity;
import com.qmul.deni.chartmtenlogdouble.JoinActivity;
import com.qmul.deni.chartmtenlogdouble.R;

import com.qmul.deni.chartmtenlogdouble.model.ChartManager;
import com.qmul.deni.chartmtenlogdouble.model.Charts;
import com.qmul.deni.chartmtenlogdouble.model.Workgroups;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deni on 18/07/2018.
 */
public class RecyclerChartManagerAdapter extends RecyclerView.Adapter<RecyclerChartManagerAdapter.ViewHolder> {


    private Context context;
    private List<ChartManager> chartManagers;

    public RecyclerChartManagerAdapter(Context context, List<ChartManager> chartManagers) {
        this.context = context;
        this.chartManagers = chartManagers;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chart_manager_list_view, parent, false);
        RecyclerChartManagerAdapter.ViewHolder holder = new RecyclerChartManagerAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerChartManagerAdapter.ViewHolder holder, int position) { // tinggal menggunakan yang telah di deklarasikan
        // menggunakan
        ChartManager wg = chartManagers.get(position);
        Log.d("@@position ; ", String.valueOf(position));

        holder.NameChartManager.setText("chart "+wg.getIdChartManager()); // untuk men set text. getNim() dari kelas mahasiswa / model
        holder.statusChatManager.setText(wg.getChartManagerStatus());
        holder.idChartManager.setText(wg.getIdChartManager());
        holder.createdTimeChatManaager.setText(wg.getChartManagerCreatedTime());

    }

    @Override
    public int getItemCount() {
        return chartManagers.size();
//        return workgroups.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // memasukkan komponen di tampilan ke variable menggunakan butterknife
        @BindView(R.id.NameChartManager)TextView NameChartManager;
        @BindView(R.id.statusChatManager)TextView statusChatManager;
        @BindView(R.id.idChartManager)TextView idChartManager;
        @BindView(R.id.createdTimeChatManaager)TextView createdTimeChatManaager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            SharedPreferences pref = context.getApplicationContext().getSharedPreferences("SessionPref", 0); // 0 - for private mode
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("role_session", "subscriber");  // Saving string
//
//            editor.commit();

            String role_session = pref.getString("role_session",null);
            Log.d("@@ chartmanager :",pref.getString("role_session",null));

            if(role_session.equals("subscriber")){
                String NameChartManager_var = NameChartManager.getText().toString();
                String statusChatManager_var = statusChatManager.getText().toString();
                String idChartManager_id_var = idChartManager.getText().toString();
                String createdTimeChatManaager_var = createdTimeChatManaager.getText().toString();

                Intent i = new Intent(context, JoinActivity.class);
                i.putExtra("chartName",NameChartManager_var);
                i.putExtra("chartStatus",statusChatManager_var);
                i.putExtra("chartId",idChartManager_id_var);
                i.putExtra("chartTimeCreated",createdTimeChatManaager_var);
                context.startActivity(i);


            }else{
                String NameChartManager_var = NameChartManager.getText().toString();
                String statusChatManager_var = statusChatManager.getText().toString();
                String idChartManager_id_var = idChartManager.getText().toString();
                String createdTimeChatManaager_var = createdTimeChatManaager.getText().toString();

                Intent i = new Intent(context, CreateActivity.class);
                i.putExtra("chartName",NameChartManager_var);
                i.putExtra("chartStatus",statusChatManager_var);
                i.putExtra("chartId",idChartManager_id_var);
                i.putExtra("chartTimeCreated",createdTimeChatManaager_var);
                context.startActivity(i);


            }

        }


    }
}
