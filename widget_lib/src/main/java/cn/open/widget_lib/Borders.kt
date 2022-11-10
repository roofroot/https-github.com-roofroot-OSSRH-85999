package cn.open.widget_lib

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun trapezoidTextBar(
    text: String, textColor: Color, textSize: TextUnit,strokeColor: Color,width:Float,density:Float
) {
    var listColor = listOf(Color(255, 255, 255, 255), strokeColor)
    var listColor2 = listOf( strokeColor, Color(255, 255, 255, 255))
    var listStop = listOf(0f, width)
    var listStop2 = listOf(0f, 120*density)
    var textOffset= 8.dp;
    var textWidth= remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier.wrapContentHeight()
    ) {
        Canvas(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            var height=textSize.value+textOffset.value
            var rect = Rect(
                0f, 0f, width, height * density
            )
            var strokeRect = Rect(
                0f, 0 * density, textWidth.value, height * density
            )
            drawIntoCanvas { canvas ->
                val paint = Paint()
                paint.shader =
                    getGradientShader(listColor, listStop,width)
                paint.style = PaintingStyle.Fill
                var tilt = 15 * density
                var offset = 5 * density
                val paintStroke = Paint()
                paintStroke.strokeWidth = 1f * density
                paintStroke.style = PaintingStyle.Stroke
                paintStroke.color = strokeColor
                val paintStroke2 = Paint()
                paintStroke2.shader =
                    getGradientShader(listColor2, listStop2, width)
                paintStroke2.strokeWidth = 1f * density
                paintStroke2.style = PaintingStyle.Stroke
                paintStroke2.color = strokeColor

                var path3 = Path();
                path3.moveTo(strokeRect.left + offset, strokeRect.bottom)
                path3.lineTo(strokeRect.left + offset + tilt, strokeRect.top)
                path3.lineTo(strokeRect.right + offset + offset, strokeRect.top)
                path3.lineTo(strokeRect.right - tilt + offset, strokeRect.bottom)
                path3.lineTo(strokeRect.left, strokeRect.bottom)
                canvas.drawPath(path3, paint)
                var path = Path();
                path.moveTo(strokeRect.left, strokeRect.bottom)
                path.lineTo(strokeRect.left + tilt, strokeRect.top + offset)
                path.lineTo(strokeRect.right+offset, strokeRect.top + offset)
//                path.lineTo(rect.right-tilt,strokeRect.bottom)
                canvas.drawPath(path, paintStroke2)
                var path2 = Path();
                path2.moveTo(strokeRect.left + offset, strokeRect.bottom)
                path2.lineTo(strokeRect.left + tilt + offset + offset, strokeRect.top)
                path2.lineTo(strokeRect.right + offset + offset, strokeRect.top)
//                path2.lineTo(strokeRect.right + offset, strokeRect.top+offset)
                canvas.drawPath(path2, paintStroke)
                canvas.drawLine(
                    Offset(rect.left, rect.bottom + 1),
                    Offset(rect.right, rect.bottom + 1),
                    paintStroke2
                )
            }

        }

        Text(
            text = text,
            Modifier
                .padding(start = 20.dp, top = textOffset)
                .onPlaced {
                    textWidth.value=it.size.width.toFloat()+40*density
                },
            color = textColor,
            fontSize = textSize,
            fontStyle = FontStyle.Normal
        );

    }

}
fun getGradientShader(listColor: List<Color>, listStop: List<Float>,width: Float): Shader {
    return LinearGradientShader(
        Offset.Zero,
        Offset(width, 0f),
        listColor, listStop, TileMode.Clamp
    )
}