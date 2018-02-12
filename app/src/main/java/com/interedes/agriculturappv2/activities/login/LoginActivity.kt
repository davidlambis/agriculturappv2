package com.interedes.agriculturappv2.activities.login

import android.animation.Animator
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.animation.AnimationUtils
import com.interedes.agriculturappv2.AgriculturApp
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.services.internet_connection.ConnectivityReceiver
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity(), LoginView, ConnectivityReceiver.connectivityReceiverListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadAnimation()

        baseContext.registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        AgriculturApp.instance.setConnectivityListener(this)
    }


    //region Métodos Interfaz
    override fun loadAnimation() {
        fabLogin?.scaleX = 0f
        fabLogin?.scaleY = 0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val interpolador = AnimationUtils.loadInterpolator(baseContext,
                    android.R.interpolator.fast_out_slow_in)
            fabLogin!!.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(1000)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {
                            fabLogin!!.animate()
                                    .scaleY(1f)
                                    .scaleX(1f)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start()

                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
        }


    }
    //endregion

    //region Validar Conexión a Internet
    //Revisar manualmente
    private fun checkConnection(): Boolean {
        return ConnectivityReceiver.isConnected
        //showSnack(isConnected);
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            Snackbar.make(container, getString(R.string.internet_connected), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(container, getString(R.string.not_internet_connected), Snackbar.LENGTH_SHORT).show()
        }
    }
    //endregion

    //region Métodos Ciclo de Vida Actividad
    override fun onResume() {
        super.onResume()
        AgriculturApp.instance.setConnectivityListener(this)
    }
    //endregion

}
