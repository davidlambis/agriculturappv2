package com.interedes.agriculturappv2.activities.profile

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.comprador.account_comprador.MyAccountCompradorActivity
import com.interedes.agriculturappv2.activities.comprador.mercado_comprador.MercadoCompradorActivity
import com.interedes.agriculturappv2.activities.login.LoginActivity
import com.interedes.agriculturappv2.activities.productor.account_productor.MyAccountProductorActivity
import com.interedes.agriculturappv2.activities.productor.unidad_productiva_productor.UnidadProductivaActivity
import kotlinx.android.synthetic.main.content_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView, View.OnClickListener {

    var isCompradorOProductor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        isCompradorOProductor()
        linearLayoutMiCuenta?.setOnClickListener(this)
        linearLayoutMercado?.setOnClickListener(this)
        linearLayoutUnidadProductiva?.setOnClickListener(this)
        linearLayoutNotificaciones?.setOnClickListener(this)
        relativeLayoutSalir?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun isCompradorOProductor() {
        isCompradorOProductor = intent?.extras?.getString("isCompradorOProductor")
        if (isCompradorOProductor == "productor") {
            linearLayoutUnidadProductiva?.setVisibility(View.VISIBLE)
            linearLayoutMercado?.setVisibility(View.GONE)
        } else if (isCompradorOProductor == "comprador") {
            linearLayoutUnidadProductiva?.setVisibility(View.GONE)
            linearLayoutMercado?.setVisibility(View.VISIBLE)
        }
    }

    override fun navigateToMiCuenta() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textViewMiCuenta?.setTextColor(getColor(R.color.colorPrimary))
            imageViewMiCuenta?.setColorFilter(getColor(R.color.colorPrimary))
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
                textViewMercado?.setTextColor(getColor(R.color.colorPrimary))
                imageViewMercado?.setColorFilter(getColor(R.color.colorPrimary))
            }
            startActivity(Intent(this, MercadoCompradorActivity::class.java))
        }
    }

    override fun navigateToUnidadProductiva() {
        if (isCompradorOProductor == "productor") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textViewUnidadProductiva?.setTextColor(getColor(R.color.colorPrimary))
                imageViewUnidadProductiva?.setColorFilter(getColor(R.color.colorPrimary))
            }
            startActivityForResult(Intent(this, UnidadProductivaActivity::class.java),200)
        }
    }

    override fun navigateToNotificaciones() {

    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textViewMiCuenta?.setTextColor(getColor(R.color.white))
            imageViewMiCuenta?.setColorFilter(getColor(R.color.white))
            textViewMercado?.setTextColor(getColor(R.color.white))
            imageViewMercado?.setColorFilter(getColor(R.color.white))
            imageViewSalir?.setColorFilter(getColor(R.color.white))
            textViewSalir?.setTextColor(getColor(R.color.white))
        }
    }

    override fun logout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewSalir?.setColorFilter(getColor(R.color.colorPrimary))
            textViewSalir?.setTextColor(getColor(R.color.colorPrimary))
        }
        showExit()
    }
    //endregion

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            Snackbar.make(container,getString(R.string.snackbar_unidad_productiva_registrada), Snackbar.LENGTH_SHORT).show()
        }
    }

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.linearLayoutMiCuenta -> {
                navigateToMiCuenta()
            }
            R.id.linearLayoutMercado -> {
                navigateToMercado()
            }
            R.id.relativeLayoutSalir -> {
                logout()
            }
            R.id.linearLayoutUnidadProductiva -> {
                navigateToUnidadProductiva()
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

    fun showExit(): Dialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación")
        builder.setNegativeButton("Cancelar") { dialog, which -> Log.i("Dialogos", "Confirmacion Cancelada.") }
        builder.setMessage("¿Cerrar Sesión?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            startActivity(Intent(baseContext, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
        builder.setIcon(R.mipmap.ic_launcher)
        return builder.show()
    }

    //endregion

}
