package i.am.rauan.satanbek.trymvp.gatjet.repositories

import android.app.Application

class MyRepo (
    private val app: Application
) {
    fun getClassName(): String = app.packageCodePath

    fun test(): String {

//        application.contex
        return "test"
    }
}