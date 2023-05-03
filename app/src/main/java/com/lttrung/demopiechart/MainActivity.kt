package com.lttrung.demopiechart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.lttrung.demopiechart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val pie = AnyChart.pie()
        pie.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
//                Toast.makeText(
//                    this@MainActivity,
//                    event.data["x"] + ":" + event.data["value"],
//                    Toast.LENGTH_SHORT
//                ).show()
                startActivity(Intent(this@MainActivity, ColumnChartActivity::class.java))
            }
        })
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Sinh viên mới vào", 100))
        data.add(ValueDataEntry("Sinh viên rời đi", 50))
        data.add(ValueDataEntry("Sinh viên duy trì", 300))
        pie.data(data)
        pie.title("Thống kê sinh viên")
        pie.labels().position("outside")
        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Retail channels")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)
        binding.pieChart.setChart(pie)
    }
}