package com.interedes.agriculturappv2.activities.productor.account_productor

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.R.id.imageViewVentas
import com.interedes.agriculturappv2.R.id.textViewVentas
import com.interedes.agriculturappv2.activities.productor.agroinsumos_productor.AgroInsumosProductorActivity
import com.interedes.agriculturappv2.activities.productor.asistencia_tecnica_productor.AsistenciaTecnicaProductorActivity
import com.interedes.agriculturappv2.activities.productor.mis_clientes_productor.MisClientesProductorActivity
import com.interedes.agriculturappv2.activities.productor.mis_cultivos_productor.MisCultivosProductorActivity
import com.interedes.agriculturappv2.activities.productor.mis_ventas_productor.MisVentasProductorActivity
import com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.ModuloContableProductorActivity
import com.interedes.agriculturappv2.activities.productor.ofertas_productor.MisOfertasProductorActivity
import com.interedes.agriculturappv2.activities.productor.preguntas_productor.MisPreguntasProductorActivity
import kotlinx.android.synthetic.main.content_my_account_productor.*

class MyAccountProductorActivity : AppCompatActivity(), MyAccountProductorView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account_productor)
        imageViewBackButton?.setOnClickListener(this)
        linearLayoutMisCultivos?.setOnClickListener(this)
        linearLayoutPreguntas?.setOnClickListener(this)
        linearLayoutOfertas?.setOnClickListener(this)
        linearLayoutVentasRealizadas?.setOnClickListener(this)
        linearLayoutClientes?.setOnClickListener(this)
        linearLayoutPlagas?.setOnClickListener(this)
        linearLayoutInsumos?.setOnClickListener(this)
        linearLayoutModuloContable?.setOnClickListener(this)
    }


    //region Métodos Interfaz
    override fun navigateToParentActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewBackButton?.setColorFilter(getColor(R.color.colorPrimary))
        }
        returnToParentActivity()
    }

    override fun navigateToAgregarProducto() {

    }

    override fun navigateToMisCultivos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewCultivos.setColorFilter(getColor(R.color.colorPrimary))
            textViewCultivos.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, MisCultivosProductorActivity::class.java))
    }

    override fun navigateToPreguntas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewPreguntas.setColorFilter(getColor(R.color.colorPrimary))
            textViewPreguntas.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, MisPreguntasProductorActivity::class.java))
    }

    override fun navigateToOfertas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewOfertas.setColorFilter(getColor(R.color.colorPrimary))
            textViewOfertas.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, MisOfertasProductorActivity::class.java))
    }

    override fun navigateToVentas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewVentas.setColorFilter(getColor(R.color.colorPrimary))
            textViewVentas.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, MisVentasProductorActivity::class.java))
    }

    override fun navigateToClientes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewClientes.setColorFilter(getColor(R.color.colorPrimary))
            textViewClientes.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, MisClientesProductorActivity::class.java))
    }


    override fun navigateToPlagas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewPlagas.setColorFilter(getColor(R.color.colorPrimary))
            textViewPlagas.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, AsistenciaTecnicaProductorActivity::class.java))
    }


    override fun navigateToEnfermedades() {

    }

    override fun navigateToInsumos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewInsumos.setColorFilter(getColor(R.color.colorPrimary))
            textViewInsumos.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, AgroInsumosProductorActivity::class.java))
    }

    override fun navigateToPreguntasFrecuentes() {

    }

    override fun navigateToBusqueda() {

    }

    override fun navigateToModuloContable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewModuloContable.setColorFilter(getColor(R.color.colorPrimary))
            textViewModuloContable.setTextColor(getColor(R.color.colorPrimary))
        }
        startActivity(Intent(this, ModuloContableProductorActivity::class.java))
    }

    override fun navigateToEditarCuenta() {

    }

    override fun limpiarCambios() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageViewBackButton.setColorFilter(getColor(R.color.white))
            imageViewVentas.setColorFilter(getColor(R.color.white))
            textViewVentas.setTextColor(getColor(R.color.white))
            imageViewCultivos.setColorFilter(getColor(R.color.white))
            textViewCultivos.setTextColor(getColor(R.color.white))
            imageViewPreguntas.setColorFilter(getColor(R.color.white))
            textViewPreguntas.setTextColor(getColor(R.color.white))
            imageViewOfertas.setColorFilter(getColor(R.color.white))
            textViewOfertas.setTextColor(getColor(R.color.white))
            imageViewClientes.setColorFilter(getColor(R.color.white))
            textViewClientes.setTextColor(getColor(R.color.white))
            imageViewPlagas.setColorFilter(getColor(R.color.white))
            textViewPlagas.setTextColor(getColor(R.color.white))
            imageViewInsumos.setColorFilter(getColor(R.color.white))
            textViewInsumos.setTextColor(getColor(R.color.white))
            imageViewModuloContable.setColorFilter(getColor(R.color.white))
            textViewModuloContable.setTextColor(getColor(R.color.white))
        }

    }
    //endregion

    //region Método On Click
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imageViewBackButton -> {
                navigateToParentActivity()
            }
            R.id.linearLayoutMisCultivos -> {
                navigateToMisCultivos()
            }
            R.id.linearLayoutPreguntas -> {
                navigateToPreguntas()
            }
            R.id.linearLayoutOfertas -> {
                navigateToOfertas()
            }
            R.id.linearLayoutVentasRealizadas -> {
                navigateToVentas()
            }
            R.id.linearLayoutClientes -> {
                navigateToClientes()
            }
            R.id.linearLayoutPlagas -> {
                navigateToPlagas()
            }
            R.id.linearLayoutInsumos -> {
                navigateToInsumos()
            }
            R.id.linearLayoutModuloContable -> {
                navigateToModuloContable()
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
