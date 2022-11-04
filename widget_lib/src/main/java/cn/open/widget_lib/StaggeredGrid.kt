package cn.open.widget_lib

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun StaggeredGrid(SCREEN_W:Int,SCREEN_H:Int,modifier: Modifier = Modifier, counts: Int, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        //每个元素的x轴位置
        val leftLocations = Array(counts) { 0 }
        val topLocations = Array(counts) { 0 }
        var top = 0;
        var left = 0;
        val placeables = measurables.mapIndexed { index, measurable ->
            //测量每一个元素的宽高
            val placeable = measurable.measure(constraints)

            if (left + placeable.width < SCREEN_W) {
                leftLocations[index] = left
            } else {
                left = 0
                leftLocations[index] = left
                top += placeable.height
            }
            topLocations[index] = top
            left += placeable.width
            placeable
        }
        val width = SCREEN_W
        layout(width, top + 55) {
            placeables.forEachIndexed { index, placeable ->
                //重置每一个元素的宽高
                placeable.placeRelative(x = leftLocations[index], topLocations[index])
            }
        }
    }
}