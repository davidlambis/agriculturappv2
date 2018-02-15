package com.interedes.agriculturappv2.activities.productor.modulo_contable_productor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.interedes.agriculturappv2.R
import com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.adapters.AdapterViewPagerContabilidad
import java.util.*
import kotlinx.android.synthetic.main.activity_modulo_contable_productor.view.*

class ModuloContableProductorActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: AdapterViewPagerContabilidad
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo_contable_productor)
        viewPager = findViewById(R.id.viewPager)
        val items = arrayOf("Ingresos", "Gastos", "Balance")
        val arrayContabilidad = ArrayList<String>()
        arrayContabilidad.addAll(Arrays.asList(*items))
        pagerAdapter = AdapterViewPagerContabilidad(supportFragmentManager, arrayContabilidad)
        viewPager.adapter = pagerAdapter
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)
        tabLayout.setTabMode(TabLayout.MODE_FIXED)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.currentItem = pagerAdapter.count / 2
    }


}
