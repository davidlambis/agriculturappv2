package com.interedes.agriculturappv2.activities.productor.unidad_productiva_productor

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.view.View
import android.widget.ArrayAdapter
import com.interedes.agriculturappv2.R
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
import kotlinx.android.synthetic.main.content_unidad_productiva_productor.*
import java.text.SimpleDateFormat
import java.util.*

class UnidadProductivaActivity : AppCompatActivity(), UnidadProductivaView, View.OnClickListener {

    private var is_mes_cultivo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidad_productiva)
        loadCoordenadas()
        loadInfo()
        imageViewBackButton?.setOnClickListener(this)
        imageViewLocalizarFinca?.setOnClickListener(this)
        edtMesSiembra?.setOnClickListener(this)
        edtMesCosecha?.setOnClickListener(this)
        btnRegistrarUnidadProductiva?.setOnClickListener(this)
    }

    //region Métodos Interfaz
    override fun loadCoordenadas() {
        edtLocalizacionFinca?.setText("75.921231, -4.23132")
    }


    override fun loadInfo() {
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

    override fun clickEdtMesSiembra() {
        is_mes_cultivo = true
        loadMeses()
    }

    override fun clickEdtMesCosecha() {
        is_mes_cultivo = false
        loadMeses()
    }

    override fun registrarUnidadProductiva() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun navigateToParentActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewBackButton?.setColorFilter(getColor(R.color.colorPrimary))
        }
        returnToParentActivity()
    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewBackButton?.setColorFilter(getColor(R.color.white))
        }
    }
    //endregion

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imageViewBackButton -> {
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
            R.id.btnRegistrarUnidadProductiva -> {
                registrarUnidadProductiva()
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
