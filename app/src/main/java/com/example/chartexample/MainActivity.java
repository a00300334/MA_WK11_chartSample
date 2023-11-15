package com.example.chartexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = findViewById(R.id.mp_chart);

        // Configure the cart
        lineChart.getDescription().setEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
//        lineChart.setBackgroundColor(Color.DKGRAY);

        //add Legend
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.MAGENTA);

        //Create data set
        LineData lineData = new LineData();
        lineData.addDataSet(createDataset1());
        lineData.addDataSet(createDataset2());

        //Add data set to linechart
        lineChart.setData(lineData);
    }

    private ILineDataSet createDataset1() {
        LineDataSet dataSet = new LineDataSet(null, "Data 1");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setColor(Color.BLUE);
        dataSet.setLineWidth(2f);
        dataSet.setFillColor(Color.RED);
        return dataSet;
    }

    private ILineDataSet createDataset2() {
        LineDataSet dataSet = new LineDataSet(null, "Data 2");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setColor(Color.GREEN);
        dataSet.setLineWidth(2f);
        dataSet.setFillColor(Color.RED);
        return dataSet;
    }

    public void addEntry(View view) {
        LineData data = lineChart.getLineData();

        if(data != null){
            ILineDataSet dataSet1 = data.getDataSetByIndex(0);
            ILineDataSet dataSet2 = data.getDataSetByIndex(1);

            Random random = new Random();
            float counter = dataSet1.getEntryCount();
            float value1 = random.nextInt(150);
            float value2 = random.nextInt(150);

            Entry newEntry1 = new Entry(counter,
                    value1);

            Entry newEntry2 = new Entry(counter,
                    value2);

            dataSet1.addEntry(newEntry1);
            dataSet2.addEntry(newEntry2);

            //Notify chart that data has been changed
            data.notifyDataChanged();
            lineChart.notifyDataSetChanged();

            lineChart.moveViewToX(data.getEntryCount());
        }
    }
}