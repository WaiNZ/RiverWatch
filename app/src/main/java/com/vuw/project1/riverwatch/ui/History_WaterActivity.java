package com.vuw.project1.riverwatch.ui;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.vuw.project1.riverwatch.R;
import com.vuw.project1.riverwatch.database.Database;
import com.vuw.project1.riverwatch.objects.Incident_Report;
import com.vuw.project1.riverwatch.objects.Water_Report;

import java.util.ArrayList;

public class History_WaterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterHistory_Water mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        ArrayList<Water_Report> waterTests = new Database(this).getWaterReportList();
        mAdapter = new AdapterHistory_Water(this, waterTests, new AdapterHistory_Water.Callback() {
            @Override
            public void open(Water_Report obj) {
                Intent intent = new Intent(History_WaterActivity.this, History_WaterActivity_Samples.class);
                intent.putExtra("id", obj.id);
                intent.putExtra("latitude", obj.latitude);
                intent.putExtra("longitude", obj.longitude);
//                intent.putExtra("name", obj.name);
//                intent.putExtra("location", obj.location);
//                intent.putExtra("date", obj.date);
//                intent.putExtra("description", obj.description);
//                intent.putExtra("image", obj.image);
//                intent.putExtra("temperature", obj.temperature);
//                intent.putExtra("pH", obj.pH);
//                intent.putExtra("conductivity", obj.conductivity);
//                intent.putExtra("turbidity", obj.turbidity);
                startActivity(intent);
            }
            @Override
            public void delete(final Water_Report obj) {
                new AlertDialog.Builder(History_WaterActivity.this)
                        .setTitle("Delete Report")
                        .setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Database database = new Database(History_WaterActivity.this);
                                database.deleteWaterReportById(obj.id);
                                ArrayList<Water_Report> list = database.getWaterReportList();
                                mAdapter.updateList(list);
                                Toast.makeText(History_WaterActivity.this, obj.name+" deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        GridLayoutManager llm = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
