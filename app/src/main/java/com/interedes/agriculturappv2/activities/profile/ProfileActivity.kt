package com.interedes.agriculturappv2.activities.profile

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.comprador.account_comprador.MyAccountCompradorActivity
import com.interedes.agriculturappv2.activities.productor.account_productor.MyAccountProductorActivity
import kotlinx.android.synthetic.main.content_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView, View.OnClickListener {


    var isCompradorOProductor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        isCompradorOProductor()
        linearLayoutMiCuenta?.setOnClickListener(this)
        linearLayoutMercado?.setOnClickListener(this)
        linearLayoutNotificaciones?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun isCompradorOProductor() {
        isCompradorOProductor = intent?.extras?.getString("isCompradorOProductor")
        if (isCompradorOProductor == "productor") {
            linearLayoutMercado?.setVisibility(View.GONE)
        } else if (isCompradorOProductor == "comprador") {
            linearLayoutMercado?.setVisibility(View.VISIBLE)
        }
    }

    override fun navigateToMiCuenta() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textViewMiCuenta?.setTextColor(getColor(R.color.colorPrimary))
            imageViewMiCuenta.setColorFilter(getColor(R.color.colorPrimary))
        }
        if (isCompradorOProductor == "productor") {
            startActivity(Intent(this, MyAccountProductorActivity::class.java))
        } else if (isCompradorOProductor == "comprador") {
            startActivity(Intent(this, MyAccountCompradorActivity::class.java))
        }
    }

    override fun navigateToMercado() {
        if (isCompradorOProductor == "comprador") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textViewMercado.setTextColor(getColor(R.color.colorPrimary))
                imageViewMercado.setColorFilter(getColor(R.color.colorPrimary))
            }
            //startActivity(Intent(this, MercadoCompradorActivity::class.java))
        }
    }

    override fun navigateToNotificaciones() {

    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textViewMiCuenta?.setTextColor(getColor(R.color.white))
            imageViewMiCuenta.setColorFilter(getColor(R.color.white))
            textViewMercado.setTextColor(getColor(R.color.white))
            imageViewMercado.setColorFilter(getColor(R.color.white))
        }
    }
    //endregion

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.linearLayoutMiCuenta -> {
                navigateToMiCuenta()
            }
            R.id.linearLayoutMercado -> {
                navigateToMercado()
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

    //region Métodos Generales
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //endregion

}
