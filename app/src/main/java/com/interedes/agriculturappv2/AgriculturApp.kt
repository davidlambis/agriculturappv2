package com.interedes.agriculturappv2

import android.app.Application
import android.os.StrictMode
import com.interedes.agriculturappv2.services.internet_connection.ConnectivityReceiver
import com.raizlabs.android.dbflow.config.FlowManager

class AgriculturApp : Application() {

    companion object {
        @get:Synchronized
        lateinit var instance: AgriculturApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FlowManager.init(this)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }

    fun setConnectivityListener(listener: ConnectivityReceiver.connectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }

}