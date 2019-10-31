package sk.furmi.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;


import java.util.ArrayList;
import java.util.List;

import sk.furmi.myapplication.models.Projectile;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        List<Projectile> dataReceived = (List<Projectile>) args.getSerializable("ARRAYLIST");

        List<Entry> entries = new ArrayList<>();
        int step = getStep(dataReceived.get(dataReceived.size() - 1).getTimeVal());
        List<String> labels = new ArrayList<>();
        for (Projectile data : dataReceived) {

            // turn your data into Entry objects
            entries.add(new Entry(data.getyPos(), (int)(data.getTimeVal()*step)));
            labels.add("t="+data.getTimeVal());
        }
        System.out.println(entries.toString());


        LineDataSet dataSet = new LineDataSet(entries, "yPos");
        dataSet.setColor(Color.GREEN);
        dataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(labels, dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }

    private int getStep(float timeVal) {
        if (timeVal < 0.001) {
            return 100000;

        }
        if (timeVal < 0.01) {
            return 10000;
        }
        if (timeVal < 0.1) {
            return 1000;
        }
        if (timeVal < 1) {
            return 100;
        }
        if (timeVal < 10) {
            return 10;
        }
        return 1;
    }

}