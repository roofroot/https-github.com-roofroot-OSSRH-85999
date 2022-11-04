package cn.open.widget_lib

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

class ColorPalette(
    fgColor: Color,
    bgColor: Color,
    screenW: Int,density: Float,
) {
    var density = density
    var screenW = screenW
    var iconOffset = 40f * density
    var fgColor = fgColor
    var bgColor = bgColor
    var colorArr = arrayOf(
        (fgColor.red * 255).toInt(),
        (fgColor.green * 255).toInt(),
        (fgColor.blue * 255).toInt(),
        (fgColor.alpha * 255).toInt()
    )
    var colorArrBg = arrayOf(
        (bgColor.red * 255).toInt(),
        (bgColor.green * 255).toInt(),
        (bgColor.blue * 255).toInt(),
        (bgColor.alpha * 255).toInt()
    )

    @Composable
    fun PaletteWidget(fgColor: MutableState<Color>, bgColor: MutableState<Color>) {
        var isBg: MutableState<Boolean> = remember {
            mutableStateOf(false)
        };
        Log.e("color", "${fgColor.value.convert(fgColor.value.colorSpace)}")
        Column(Modifier.background(Color.Gray)) {
            Row(
                Modifier
                    .width(200.dp)
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .background(color = fgColor.value, shape = CircleShape)
                    .size(80.dp)
                    .clickable {
                        isBg.value = false;
                    }) {

                }

                Box(modifier = Modifier
                    .background(color = bgColor.value, shape = CircleShape)
                    .size(80.dp)
                    .clickable {
                        isBg.value = true;
                    }
                    .padding(10.dp)) {

                }

            }

            if (isBg.value) {
                colorBar(
                    screenW, {
                        colorArrBg[0] = it;
                        bgColor.value =
                            Color(colorArrBg[0], colorArrBg[1], colorArrBg[2], colorArrBg[3])
                    }, listOf(Color(0, 0, 0, 255), Color(255, 0, 0, 255)),
                    listOf(0f, screenW.toFloat()), colorArrBg[0]
                )
                colorBar(
                    screenW,
                    {
//                    Log.e("it", "$it")
                        colorArrBg[1] = it
                        bgColor.value =
                            Color(colorArrBg[0], colorArrBg[1], colorArrBg[2], colorArrBg[3])

                    }, listOf(Color(0, 0, 0, 255), Color(0, 255, 0, 255)),
                    listOf(0f, screenW.toFloat()), colorArrBg[1]
                )
                colorBar(
                    screenW, {
                        colorArrBg[2] = it;
                        bgColor.value =
                            Color(colorArrBg[0], colorArrBg[1], colorArrBg[2], colorArrBg[3])
                    }, listOf(Color(0, 0, 0, 255), Color(0, 0, 255, 255)),
                    listOf(0f, screenW.toFloat()), colorArrBg[2]
                )

                colorBar(
                    screenW, {
                        colorArrBg[3] = it;
                        bgColor.value =
                            Color(colorArrBg[0], colorArrBg[1], colorArrBg[2], colorArrBg[3])
                    }, listOf(Color(255, 255, 255, 0), Color(255, 255, 255, 255)),
                    listOf(0f, screenW.toFloat()), colorArrBg[3]
                )
            } else {
//            Log.e("arr", "${colorArr[0]}+${colorArr[1]}+${colorArr[2]}+${colorArr[3]}")
                colorBar(
                    screenW, {
//                Log.e("it" ,"$it");
                        colorArr[0] = it;
//                Log.e("arrin", "${colorArr[0]}+${colorArr[1]}+${colorArr[2]}+${colorArr[3]}")
                        fgColor.value = Color(colorArr[0], colorArr[1], colorArr[2], colorArr[3])
                    }, listOf(Color(0, 0, 0, 255), Color(255, 0, 0, 255)),
                    listOf(0f, screenW.toFloat()), colorArr[0]
                )
                colorBar(
                    screenW,
                    {
                        Log.e("it", "$it")
                        colorArr[1] = it
                        fgColor.value = Color(colorArr[0], colorArr[1], colorArr[2], colorArr[3])

                    }, listOf(Color(0, 0, 0, 255), Color(0, 255, 0, 255)),
                    listOf(0f, screenW.toFloat()), colorArr[1]
                )
                colorBar(
                    screenW, {
                        Log.e("it", "$it")
                        colorArr[2] = it;
                        fgColor.value = Color(colorArr[0], colorArr[1], colorArr[2], colorArr[3])
                    }, listOf(Color(0, 0, 0, 255), Color(0, 0, 255, 255)),
                    listOf(0f, screenW.toFloat()), colorArr[2]
                )

                colorBar(
                    screenW, {
                        Log.e("it", "$it")
                        colorArr[3] = it;
                        fgColor.value = Color(colorArr[0], colorArr[1], colorArr[2], colorArr[3])
                    }, listOf(Color(255, 255, 255, 0), Color(255, 255, 255, 255)),
                    listOf(0f, screenW.toFloat()), colorArr[3]
                )
            }
        }
    }

    @Composable
    fun colorBar(
        screenW: Int,
        callback: (color: Int) -> Unit,
        listColor: List<Color>,
        listStop: List<Float>,
        colorInit: Int
    ) {

        var offset = (screenW - iconOffset) * colorInit / 225f
        if (offset > screenW - iconOffset) {
            offset = screenW - iconOffset
        }

//        var offset = (iconOffset +colorInit.toFloat()) / 255f

        var offsetX: MutableState<Float> = remember {
            mutableStateOf(offset)
        }
        Box(modifier = Modifier.height(40.dp)) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            ) {
                Canvas(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        paint.strokeWidth = 3f
                        paint.shader =
                            getGradientShader(listColor, listStop)
                        paint.style = PaintingStyle.Fill
                        var rect = Rect(
                            0f, 0f, screenW.toFloat(), 30 * density
                        )
                        canvas.drawRect(rect, paint)

//                canvas.drawCircle(Offset(50f,50f),50f,paint)
                    }
                }
            }
            Image(
                contentDescription = "",
                painter = painterResource(id = android.R.drawable.ic_delete),
                modifier = Modifier
                    .offset(
                        (offsetX.value / density).dp,
                        0.dp
                    )
                    .size(40.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            if (offsetX.value + dragAmount.x <= screenW - iconOffset && offsetX.value + dragAmount.x >= 0) {
                                offsetX.value += dragAmount.x
                                var color =
                                    ((offsetX.value.toInt() / (screenW - iconOffset - 1)) * 255).toInt();
                                Log.e("color", "$color")
                                callback(color)

                            }

                        }
                    }
            )

        }

    }

    private fun getGradientShader(listColor: List<Color>, listStop: List<Float>): Shader {
        return LinearGradientShader(
            Offset.Zero,
            Offset(screenW.toFloat(), 0f),
            listColor, listStop, TileMode.Clamp
        )
    }
}
