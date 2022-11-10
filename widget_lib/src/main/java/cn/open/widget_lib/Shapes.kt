package cn.open.widget_lib

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class PathShape(
    private val path: Path
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path)
    }
}

class SimplePathShape(
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        var rect = Rect(
            0f, 0f, size.width, size.height
        )
        var path = Path();
        path.moveTo(rect.left, rect.bottom)
        path.quadraticBezierTo(
            rect.left + 10 * density.density, rect.top,
            rect.left + 50 * density.density, rect.top
        )
        path.lineTo(rect.right, rect.top)
        path.quadraticBezierTo(
            rect.right - 10 * density.density, rect.bottom,
            rect.right - 50 * density.density, rect.bottom
        )
        path.lineTo(rect.left, rect.bottom)
        return Outline.Generic(path)
    }
}

class CutHornShape(
    private val tilt: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        var rect = Rect(
            0f, 0f, size.width, size.height
        )
        var path = Path()
        path.moveTo(rect.left, rect.bottom - tilt)
        path.lineTo(rect.left + tilt, rect.bottom)
        path.lineTo(rect.right - tilt, rect.bottom)
        path.lineTo(rect.right, rect.bottom - tilt)
        path.lineTo(rect.right, rect.top + tilt)
        path.lineTo(rect.right - tilt, rect.top)
        path.lineTo(rect.left + tilt, rect.top)
        path.lineTo(rect.left, rect.top + tilt)
        path.close()
        return Outline.Generic(path)
    }
}

//波浪线
class SimpleWaveLineShape(
    private val waveStep: Int
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        var rect = Rect(
            0f, 0f, size.width, size.height
        )
        var path = Path();
        var waveW = size.width / waveStep
        path.moveTo(rect.left, rect.bottom - 2)
        var right = 0f;
        while (right < size.width.toInt()) {
            right += waveW
            path.quadraticBezierTo(
                rect.left + right, rect.top,
                rect.left + (right + waveW), rect.bottom - 2
            )
            right += waveW
        }
        return Outline.Generic(path)
    }
}

//底部波浪形的长方形
class SimpleWaveRectShape(
    private val waveStep: Int
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        var rect = Rect(
            0f, 0f, size.width, size.height
        )
//                    canvas.drawRect(rect, paint)
        var path = Path();
        var waveW = size.width / waveStep
        var waveH = 10 * density.density
        var right = 0f;
        path.lineTo(rect.left, rect.bottom - waveH)
        while (right < size.width.toInt()) {
            right += waveW
            path.quadraticBezierTo(
                rect.left + right, rect.bottom,
                rect.left + (right + waveW), rect.bottom - waveH
            )
            right += waveW
        }
        path.lineTo(right, rect.top)
        path.close()
        return Outline.Generic(path)
    }
}

