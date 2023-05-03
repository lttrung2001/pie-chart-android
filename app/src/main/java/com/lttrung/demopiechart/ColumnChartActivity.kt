package com.lttrung.demopiechart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.axes.Linear
import com.anychart.enums.Orientation
import com.anychart.enums.ScaleStackMode
import com.anychart.enums.ScaleTypes
import com.lttrung.demopiechart.databinding.ActivityColumnChartBinding


class ColumnChartActivity : AppCompatActivity() {
    private val binding: ActivityColumnChartBinding by lazy {
        ActivityColumnChartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cartesian: Cartesian = AnyChart.cartesian()

        cartesian.animation(true)

        cartesian.title("Combination of Stacked Column and Line Chart (Dual Y-Axis)")

        cartesian.yScale().stackMode(ScaleStackMode.VALUE)

        val scalesLinear = Linear.instantiate()
//        scalesLinear.minimum(0.0)
//        scalesLinear.maximum(100.0)
//        scalesLinear.ticks("{ interval: 20 }")

        val extraYAxis: Linear = cartesian.yAxis(1.0)
        extraYAxis.orientation(Orientation.RIGHT)
//            .scale(scalesLinear)
        extraYAxis.labels()
            .padding(0.0, 0.0, 0.0, 5.0)
            .format("{%Value}%")

        val data = arrayListOf<CustomDataEntry>()
        data.add(CustomDataEntry("P1", 96.5, 2040, 1200, 1600))
        data.add(CustomDataEntry("P2", 77.1, 1794, 1124, 1724))
        data.add(CustomDataEntry("P3", 73.2, 2026, 1006, 1806))
        data.add(CustomDataEntry("P4", 61.1, 2341, 921, 1621))
        data.add(CustomDataEntry("P5", 70.0, 1800, 1500, 1700))
        data.add(CustomDataEntry("P6", 60.7, 1507, 1007, 1907))
        data.add(CustomDataEntry("P7", 62.1, 2701, 921, 1821))
        data.add(CustomDataEntry("P8", 75.1, 1671, 971, 1671))
        data.add(CustomDataEntry("P9", 80.0, 1980, 1080, 1880))
        data.add(CustomDataEntry("P10", 54.1, 1041, 1041, 1641))
        data.add(CustomDataEntry("P11", 51.3, 813, 1113, 1913))
        data.add(CustomDataEntry("P12", 59.1, 691, 1091, 1691))

        val set = com.anychart.data.Set.instantiate()
        set.data(data.toList())
        val lineData = set.mapAs("{ x: 'x', value: 'value' }")
        val column1Data = set.mapAs("{ x: 'x', value: 'value2' }")
        val column2Data = set.mapAs("{ x: 'x', value: 'value3' }")
        val column3Data = set.mapAs("{ x: 'x', value: 'value4' }")

        cartesian.column(column1Data)
        cartesian.crosshair(true)

        val line = cartesian.line(lineData)
        line.yScale(ScaleTypes.LINEAR)

        cartesian.column(column2Data);
        cartesian.column(column3Data);

        binding.columnChart.setChart(cartesian)
    }

    private class CustomDataEntry(
        x: String?,
        value: Number,
        value2: Number,
        value3: Number,
        value4: Number
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
            setValue("value4", value4)
        }
    }
}