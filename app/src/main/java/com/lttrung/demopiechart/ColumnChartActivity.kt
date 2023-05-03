package com.lttrung.demopiechart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.enums.Orientation
import com.anychart.enums.ScaleStackMode
import com.anychart.scales.Linear
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
        scalesLinear.minimum(0.0)
        scalesLinear.maximum(100.0)
        scalesLinear.ticks("{ interval: 20 }")

        val extraYAxis = cartesian.yAxis(1.0)
        extraYAxis.orientation(Orientation.RIGHT)
        extraYAxis.labels()
            .padding(0.0, 0.0, 0.0, 5.0)
            .format("{%Value}%")

        val data: MutableList<DataEntry> = ArrayList()
        data.add(CustomDataEntry("P1", 96.5, 2040))
        data.add(CustomDataEntry("P2", 77.1, 1794))
        data.add(CustomDataEntry("P3", 73.2, 2026))
        data.add(CustomDataEntry("P4", 61.1, 234))
        data.add(CustomDataEntry("P5", 70.0, 1800))
        data.add(CustomDataEntry("P6", 15.7, 1507))
        data.add(CustomDataEntry("P7", 62.1, 270))
        data.add(CustomDataEntry("P8", 75.1, 167))
        data.add(CustomDataEntry("P9", 80.0, 1980))
        data.add(CustomDataEntry("P10", 54.1, 1041))
        data.add(CustomDataEntry("P11", 51.3, 813))
        data.add(CustomDataEntry("P12", 59.1, 691))

        val set = com.anychart.data.Set.instantiate()
        set.data(data)
        val lineData: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val column1Data: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")

        cartesian.column(column1Data)
        cartesian.crosshair(true)

        val line: Line = cartesian.line(lineData)
        line.yScale(scalesLinear)

        binding.columnChart.setChart(cartesian)
    }

    private class CustomDataEntry(
        x: String?,
        value: Number?,
        value2: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
        }
    }
}