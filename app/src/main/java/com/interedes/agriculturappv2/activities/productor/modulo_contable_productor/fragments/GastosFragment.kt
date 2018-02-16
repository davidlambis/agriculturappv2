package com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.interedes.agriculturappv2.R
import kotlinx.android.synthetic.main.fragment_gastos.*
import java.util.*


class GastosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gastos, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInfo()
    }

    private fun loadInfo() {
        val itemsDescripcion = arrayOf("Análisis de Suelo", "Material Vegetal", "Materiales", "Herramientas", "Agroinsumos", "Transporte")
        val descripcionList = ArrayList<String>()
        descripcionList.addAll(Arrays.asList(*itemsDescripcion))
        spinnerDescripcion?.setAdapter(null)
        val DescripcionArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, descripcionList)
        spinnerDescripcion?.setAdapter(DescripcionArrayAdapter)

        val itemsProducto = arrayOf("Aguacate", "Plátano", "Fríjol")
        val ProductoIngresoList = ArrayList<String>()
        ProductoIngresoList.addAll(Arrays.asList(*itemsProducto))
        spinnerSeleccionarProducto?.setAdapter(null)
        val ProductoIngresoArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ProductoIngresoList)
        spinnerSeleccionarProducto?.setAdapter(ProductoIngresoArrayAdapter)


    }
}