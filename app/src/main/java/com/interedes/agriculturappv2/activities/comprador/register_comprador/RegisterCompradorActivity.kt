package com.interedes.agriculturappv2.activities.comprador.register_comprador

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.R.id.ivBackButtonRegisterComprador
import com.interedes.agriculturappv2.activities.profile.ProfileActivity
import kotlinx.android.synthetic.main.content_register_comprador.*
import kotlinx.android.synthetic.main.dialogo_registro_comprador.view.*
import java.util.*

class RegisterCompradorActivity : AppCompatActivity(), RegisterCompradorView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_comprador)
        loadInfo()
        ivBackButtonRegisterComprador?.setOnClickListener(this)
        btnRegistrarComprador?.setOnClickListener(this)
    }

    //region Métodos Interfaz
    override fun navigateToParentActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ivBackButtonRegisterComprador?.setColorFilter(getColor(R.color.colorPrimary))
        }
        returnToParentActivity()
    }

    override fun loadDialogoRegistroExitoso() {
        val dialogo = View.inflate(this, R.layout.dialogo_registro_comprador, null)
        dialogo?.textViewBuscarProductos?.setText(getString(R.string.title_buscar_productos))

        dialogo?.imageViewBuscarProductos?.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dialogo.imageViewBuscarProductos?.setColorFilter(getColor(R.color.colorPrimary))
            }
            val i = Intent(applicationContext, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "comprador")
            startActivity(i)
        })


        dialogo?.imageViewMiCuenta?.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dialogo.imageViewMiCuenta?.setColorFilter(getColor(R.color.colorPrimary))
            }
            val i = Intent(applicationContext, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "comprador")
            startActivity(i)
        })


        val builder = AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        builder.setView(dialogo)
        //builder.setCancelable(false);
        builder.setOnKeyListener { dialog, keyCode, event ->
            // Prevent dialog close on back press button
            keyCode == KeyEvent.KEYCODE_BACK
        }
        builder.show()
    }

    override fun loadInfo() {
        //Spinner Interes Productos
        val itemsInteresProductos = arrayOf("Aguacate", "Cacao", "Fríjol")
        val interesProductosList = ArrayList<String>()
        interesProductosList.addAll(Arrays.asList(*itemsInteresProductos))
        spinnerInteresProductos?.setAdapter(null)
        val interesProductosArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, interesProductosList)
        spinnerInteresProductos?.setAdapter(interesProductosArrayAdapter)

        //Spinner Método de Pago
        val itemsMetodoPago = arrayOf("Transferencia Bancaria", "Efectivo", "Otros")
        val metodoPagoList = ArrayList<String>()
        metodoPagoList.addAll(Arrays.asList(*itemsMetodoPago))
        spinnerMetodoPago?.setAdapter(null)
        val metodoPagoArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, metodoPagoList)
        spinnerMetodoPago?.setAdapter(metodoPagoArrayAdapter)

        spinnerMetodoPago?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            if (metodoPagoList[position].equals("Transferencia Bancaria")) {
                //Carga Spinner Banco
                spinnerBanco.visibility = View.VISIBLE
                spinnerBanco.setText("")
                val itemsBanco = arrayOf("Bancolombia", "BBVA", "Banco Agrario")
                val bancoList = ArrayList<String>()
                bancoList.addAll(Arrays.asList(*itemsBanco))
                spinnerBanco?.setAdapter(null)
                val bancoArrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, bancoList)
                spinnerBanco?.setAdapter(bancoArrayAdapter)
                /*spinnerBanco?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                    textInputNumeroCuenta?.visibility = View.VISIBLE
                    edtNumeroCuenta?.setText("")
                }*/
            } else if (metodoPagoList[position].equals("Otros")) {
                spinnerBanco?.visibility = View.VISIBLE
                spinnerBanco?.setText("")
                val itemsBanco = arrayOf("Efecty", "Su Chance", "Baloto")
                val bancoList = ArrayList<String>()
                bancoList.addAll(Arrays.asList(*itemsBanco))
                spinnerBanco?.setAdapter(null)
                val bancoArrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, bancoList)
                spinnerBanco?.setAdapter(bancoArrayAdapter)
                spinnerBanco?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> }
            } else {
                spinnerBanco?.visibility = View.GONE
                //textInputNumeroCuenta?.visibility = View.GONE
            }
        }
    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ivBackButtonRegisterComprador?.setColorFilter(getColor(R.color.white))
        }
    }

    override fun registerComprador() {
        loadDialogoRegistroExitoso()
    }
    //endregion

    //region Método On Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBackButtonRegisterComprador -> {
                navigateToParentActivity()
            }
            R.id.btnRegistrarComprador -> {
                registerComprador()
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

    //region Métodos Ciclo de Vida Actividad
    override fun onResume() {
        super.onResume()
        limpiarCambios()
    }
    //endregion
}
