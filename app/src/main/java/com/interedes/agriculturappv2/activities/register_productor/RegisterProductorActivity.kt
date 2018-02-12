package com.interedes.agriculturappv2.activities.register_productor

import android.content.DialogInterface
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
import android.widget.ListAdapter
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.profile.ProfileActivity
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
import kotlinx.android.synthetic.main.content_register_productor.*
import kotlinx.android.synthetic.main.dialogo_registro_comprador.view.*

import java.text.SimpleDateFormat
import java.util.*

class RegisterProductorActivity : AppCompatActivity(), RegisterProductorView, View.OnClickListener {

    //Variables globales
    private var is_mes_cultivo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_productor)
        loadInfo()
        loadCoordenadas()
        imageViewLocalizarFinca?.setOnClickListener(this)
        ivBackButtonRegisterProductor?.setOnClickListener(this)
        edtMesSiembra?.setOnClickListener(this)
        edtMesCosecha?.setOnClickListener(this)
        btnRegistrarProductor?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun loadCoordenadas() {
        edtLocalizacionFinca?.setText("75.921231, -4.23132")
    }

    override fun loadInfo() {
        //Presenter
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
                spinnerBanco?.visibility = View.VISIBLE
                spinnerBanco?.setText("")
                val itemsBanco = arrayOf("Bancolombia", "BBVA", "Banco Agrario")
                val bancoList = ArrayList<String>()
                bancoList.addAll(Arrays.asList(*itemsBanco))
                spinnerBanco?.setAdapter(null)
                val bancoArrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, bancoList)
                spinnerBanco?.setAdapter(bancoArrayAdapter)
                spinnerBanco?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                    textInputNumeroCuenta?.visibility = View.VISIBLE
                    edtNumeroCuenta?.setText("")
                }
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
                textInputNumeroCuenta?.visibility = View.GONE
            }
        }

        //Spinner Tipo Producto
        val itemsTipoProducto = arrayOf("Aguacate", "Cacao", "Fríjol")
        val tipoProductoList = ArrayList<String>()
        tipoProductoList.addAll(Arrays.asList(*itemsTipoProducto))
        spinnerTipoProducto?.setAdapter(null)
        val tipoProductoArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipoProductoList)
        spinnerTipoProducto?.setAdapter(tipoProductoArrayAdapter)
    }

    override fun loadMeses() {
        val yearMonthPickerDialog = YearMonthPickerDialog(this, YearMonthPickerDialog.OnDateSetListener { year, month ->
            /*Locale locale = getResources().getConfiguration().locale;
                Locale.setDefault(locale);*/
            val spanish = Locale("es", "ES")
            Locale.setDefault(spanish)
            val calendar = Calendar.getInstance(spanish)
            calendar?.set(Calendar.YEAR, year)
            //month = Integer.parseInt(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
            calendar?.set(Calendar.MONTH, month)


            val dateFormat = SimpleDateFormat("MMMM yyyy")

            if (is_mes_cultivo) {
                edtMesSiembra?.setText(dateFormat.format(calendar.time))
            } else {
                edtMesCosecha?.setText(dateFormat.format(calendar.time))
            }
        })

        yearMonthPickerDialog.show()
    }

    override fun loadDialogoRegistroExitoso() {
        val dialogo = View.inflate(this, R.layout.dialogo_registro_comprador, null)
        dialogo?.textViewBuscarProductos?.setText(getString(R.string.title_mis_cultivos))

        dialogo?.imageViewBuscarProductos?.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dialogo.imageViewBuscarProductos?.setColorFilter(getColor(R.color.colorPrimary))
            }
            val i = Intent(applicationContext, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "productor")
            startActivity(i)
        })


        dialogo?.imageViewMiCuenta?.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dialogo?.imageViewMiCuenta?.setColorFilter(getColor(R.color.colorPrimary))
            }
            val i = Intent(applicationContext, ProfileActivity::class.java)
            i.putExtra("isCompradorOProductor", "productor")
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

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ivBackButtonRegisterProductor?.setColorFilter(getColor(R.color.white))
        }
    }

    override fun clickEdtMesSiembra() {
        is_mes_cultivo = true
        loadMeses()
    }

    override fun clickEdtMesCosecha() {
        is_mes_cultivo = false
        loadMeses()
    }

    override fun navigateToParentActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ivBackButtonRegisterProductor?.setColorFilter(getColor(R.color.colorPrimary))
        }
        returnToParentActivity()
    }

    override fun registerProductor() {
        //Presenter
        //TODO Registrar productor
        //TODO Show Progress
        loadDialogoRegistroExitoso()
    }

    override fun disableInputs() {

    }

    override fun enableInputs() {

    }
    //endregion

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBackButtonRegisterProductor -> {
                navigateToParentActivity()
            }
            R.id.imageViewLocalizarFinca -> {
                loadCoordenadas()
            }
            R.id.edtMesSiembra -> {
                clickEdtMesSiembra()
            }
            R.id.edtMesCosecha -> {
                clickEdtMesCosecha()
            }
            R.id.btnRegistrarProductor -> {
                registerProductor()
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

    //region Ciclo de Vida Actividad
    override fun onResume() {
        super.onResume()
        limpiarCambios()
    }
    //endregion


}
