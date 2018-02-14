package com.interedes.agriculturappv2.activities.login

import android.animation.Animator
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import com.interedes.agriculturappv2.AgriculturApp
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.comprador.mercado_comprador.MercadoCompradorActivity
import com.interedes.agriculturappv2.activities.home.HomeActivity
import com.interedes.agriculturappv2.activities.home.HomeView
import com.interedes.agriculturappv2.activities.profile.ProfileActivity
import com.interedes.agriculturappv2.services.internet_connection.ConnectivityReceiver
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity(), LoginView, ConnectivityReceiver.connectivityReceiverListener, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadAnimation()

        baseContext.registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        AgriculturApp.instance.setConnectivityListener(this)
        fabLogin?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun ingresar() {
        edtCedula.setError(null)
        edtContrasena.setError(null)
        var cancel = false
        var focusView: View? = null
        if (TextUtils.isEmpty(edtCedula?.text.toString())) {
            edtCedula.setError(getString(R.string.error_field_required))
            focusView = edtCedula
            cancel = true
        } else if (TextUtils.isEmpty(edtContrasena?.text.toString())) {
            edtContrasena.error = getString(R.string.error_field_required)
            focusView = edtContrasena
            cancel = true
        } else if (edtCedula?.text.toString().equals("1357") && edtContrasena?.text.toString().equals("1357")) {
            val i = Intent(this, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "productor")
            startActivity(i)
        } else if (edtCedula?.text.toString().equals("2468") && edtContrasena?.text.toString().equals("2468")) {
            val i = Intent(this, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "comprador")
            startActivity(i)
        } else {
            Snackbar.make(container, getString(R.string.snackbar_datos_incorrectos), Snackbar.LENGTH_SHORT).show()
        }

        if (cancel) run {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        }
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

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

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fabLogin -> {
                ingresar()
            }
        }
    }
    //endregion

    //region Métodos Generales
    private fun returnToParentActivity() {
        // Obtener intent de la actividad padre
        val upIntent = NavUtils.getParentActivityIntent(this)
        upIntent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        // Comprobar si DetailActivity no se creó desde CourseActivity
        if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot) {

            // Construir de nuevo la tarea para ligar ambas actividades
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(upIntent)
                        .startActivities()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Terminar con el método correspondiente para Android 5.x
            this.finishAfterTransition()
        }

        //Para versiones anterios a 5.x
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Terminar con el método correspondiente para Android 5.x
            onBackPressed()
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

    //region Métodos Generales
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        return super.onKeyDown(keyCode, event)
    }

    //endregion

}
