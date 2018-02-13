package com.interedes.agriculturappv2.activities.productor.mis_ventas_productor

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.interedes.agriculturappv2.R
import kotlinx.android.synthetic.main.content_mis_ventas_productor.*
import java.util.*

class MisVentasProductorActivity : AppCompatActivity(), MisVentasProductorView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_ventas_productor)
        loadInfo()
        imageViewBackButton?.setOnClickListener(this)
    }

    //region Métodos Interfaz
    override fun loadInfo() {
        val itemsCalidad = arrayOf("Primera", "Segunda", "Tercera")
        val itemsCalidadList = ArrayList<String>()
        itemsCalidadList.addAll(Arrays.asList(*itemsCalidad))
        spinnerCalidad.setAdapter(null)
        val calidadArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, itemsCalidadList)
        spinnerCalidad?.setAdapter(calidadArrayAdapter)

        val itemsUbicacion = arrayOf("Amazonas", "Cundinamarca", "Huila")
        val itemsUbicacionList = ArrayList<String>()
        itemsUbicacionList.addAll(Arrays.asList(*itemsUbicacion))
        spinnerUbicacion.setAdapter(null)
        val ubicacionArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, itemsUbicacionList)
        spinnerUbicacion?.setAdapter(ubicacionArrayAdapter)
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

    override fun onResume() {
        super.onResume()
        limpiarCambios()
    }
}
