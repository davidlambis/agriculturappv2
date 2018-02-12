package com.interedes.agriculturappv2.services.internet_connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.interedes.agriculturappv2.AgriculturApp

class ConnectivityReceiver : BroadcastReceiver() {

    companion object {
        var connectivityReceiverListener: connectivityReceiverListener? = null
        val isConnected: Boolean
            get() {
                val cm = AgriculturApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
            }
    }

    interface connectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    override fun onReceive(context: Context, p1: Intent?) {
        val isConnected = checkConnection(context)
        if (connectivityReceiverListener != null)
            connectivityReceiverListener!!.onNetworkConnectionChanged(isConnected)
    }

    private fun checkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting)


    }



}