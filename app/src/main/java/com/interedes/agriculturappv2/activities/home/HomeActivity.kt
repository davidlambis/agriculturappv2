package com.interedes.agriculturappv2.activities.home

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.interedes.agriculturappv2.AgriculturApp
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.login.LoginActivity
import com.interedes.agriculturappv2.activities.register_user.RegisterUserActivity
import com.interedes.agriculturappv2.services.internet_connection.ConnectivityReceiver
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), HomeView, View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        linearLayoutIngresar?.setOnClickListener(this)
        linearLayoutRegistrar?.setOnClickListener(this)
        linearLayoutContactanos?.setOnClickListener(this)


    }

    //region Métodos Interfaz
    override fun ingresar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewIngresar?.setColorFilter(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun registrarse() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewRegistrarse?.setColorFilter(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, RegisterUserActivity::class.java))
    }

    override fun contactarnos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewContactanos?.setColorFilter(getColor(R.color.colorPrimary))
        }
        Snackbar.make(container, getString(R.string.title_contactanos), Snackbar.LENGTH_SHORT).show()
    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewIngresar?.setColorFilter(getColor(R.color.white))
            imageViewRegistrarse?.setColorFilter(getColor(R.color.white))
            imageViewContactanos?.setColorFilter(getColor(R.color.white))

        }
    }
    //endregion

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.linearLayoutIngresar -> {
                ingresar()
            }
            R.id.linearLayoutRegistrar -> {
                registrarse()
            }
            R.id.linearLayoutContactanos -> {
                contactarnos()
            }
        }
    }

    //endregion

    //region Ciclo de Vida Actividad
    override fun onResume() {
        super.onResume()
        limpiarCambios()
    }
    //endregion


}
