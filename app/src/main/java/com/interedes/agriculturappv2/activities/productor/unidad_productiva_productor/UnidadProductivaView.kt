package com.interedes.agriculturappv2.activities.productor.unidad_productiva_productor

interface UnidadProductivaView {
    fun registrarUnidadProductiva()
    fun navigateToParentActivity()
    fun limpiarCambios()
    fun loadCoordenadas()
    fun loadInfo()
    fun loadMeses()
    fun clickEdtMesSiembra()
    fun clickEdtMesCosecha()
}