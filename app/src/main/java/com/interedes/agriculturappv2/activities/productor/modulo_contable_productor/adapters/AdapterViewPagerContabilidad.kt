package com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.fragments.BalanceFragment
import com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.fragments.GastosFragment
import com.interedes.agriculturappv2.activities.productor.modulo_contable_productor.fragments.IngresosFragment

class AdapterViewPagerContabilidad(fragmentManager: FragmentManager, private val array: ArrayList<String>) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return IngresosFragment()
            }
            1 -> {
                return GastosFragment()
            }
            2 -> {
                return BalanceFragment()
            }
        }
        return IngresosFragment()
    }


    override fun getCount(): Int {
        return array.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return array[position % array.size]
    }

}