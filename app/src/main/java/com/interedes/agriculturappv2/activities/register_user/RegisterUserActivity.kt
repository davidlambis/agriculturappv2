package com.interedes.agriculturappv2.activities.register_user

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.view.View
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.R.id.*
import com.interedes.agriculturappv2.activities.comprador.register_comprador.RegisterCompradorActivity
import com.interedes.agriculturappv2.activities.productor.register_productor.RegisterProductorActivity
import kotlinx.android.synthetic.main.content_register_user.*

class RegisterUserActivity : AppCompatActivity(), RegisterUserView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        linearLayoutProductor?.setOnClickListener(this)
        linearLayoutComprador?.setOnClickListener(this)
        linearLayoutAyudaRegistro?.setOnClickListener(this)
        imageViewBackButton?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun navigateToRegistrarProductor() {
        showProgress()
        imageViewProductor?.setColorFilter(resources.getColor(R.color.colorPrimary))
        startActivity(Intent(this, RegisterProductorActivity::class.java))
    }

    override fun navigateToRegistrarComprador() {
        showProgress()
        imageViewComprador?.setColorFilter(resources.getColor(R.color.colorPrimary))
        startActivity(Intent(this, RegisterCompradorActivity::class.java))
    }

    override fun navigateToAyudaRegistro() {

    }

    override fun navigateToParentActivity() {
        imageViewBackButton?.setColorFilter(resources.getColor(R.color.colorPrimary))
        returnToParentActivity()
    }

    override fun limpiarCambios() {
        hideProgress()
        imageViewProductor?.setColorFilter(resources.getColor(R.color.white))
        imageViewComprador?.setColorFilter(resources.getColor(R.color.white))
        imageViewBackButton?.setColorFilter(resources.getColor(R.color.white))

    }

    override fun showProgress() {
        //progressBar?.visibility = View.VISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    //endregion

    //region Método Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.linearLayoutProductor -> {
                navigateToRegistrarProductor()
            }
            R.id.linearLayoutComprador -> {
                navigateToRegistrarComprador()
            }
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

    //region Métodos Ciclo de Vida Actividad
    override fun onResume() {
        super.onResume()
        limpiarCambios()
    }
    //endregion


}
