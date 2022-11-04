package cn.open.widget.application

import android.app.Application
import android.content.res.Resources
import cn.open.widget.ui.theme.SCREEN_DENSITY
import cn.open.widget.ui.theme.SCREEN_H
import cn.open.widget.ui.theme.SCREEN_W

/**
 * yuxiu
 * 2022/10/4
 **/
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SCREEN_W = Resources.getSystem().getDisplayMetrics().widthPixels;
        SCREEN_H = Resources.getSystem().getDisplayMetrics().heightPixels;
        SCREEN_DENSITY = Resources.getSystem().displayMetrics.density
    }
}