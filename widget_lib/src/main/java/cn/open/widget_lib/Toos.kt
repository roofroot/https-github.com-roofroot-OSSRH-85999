package cn.open.widget_lib

import android.graphics.Point

fun fingerAngleWithCenter(fingerPoint: Point, center: Point): Int {
    var pointR =
        Math.sqrt((fingerPoint.x * fingerPoint.x + fingerPoint.y * fingerPoint.y).toDouble())//当前手指与图片中间点半径
    var xdistance = Math.abs(fingerPoint.x - center.x)
    var ydistance = Math.abs(fingerPoint.y - center.y)
    var result = (Math.atan((ydistance / xdistance).toDouble()) * 180 / Math.PI).toInt() //反正弦函数求出角度
    if (fingerPoint.x >= center.x && fingerPoint.y <= center.y) {
        return result
    } else if (fingerPoint.x < center.x && fingerPoint.y <= center.y) {
        return 180 - result

    } else if (fingerPoint.x < center.x && fingerPoint.y > center.y) {
        return 180 + result
    } else if (fingerPoint.x >= center.x && fingerPoint.y >= center.y) {
        return 360 - result
    }
    return result
}

//最大公约数
fun getGCD(a: Int, b: Int): Int {
    val min = Math.min(a, b)
    for (i in min downTo 1) {
        if (a % i == 0 && b % i == 0) {
            return i
        }
    }
    return 1
}