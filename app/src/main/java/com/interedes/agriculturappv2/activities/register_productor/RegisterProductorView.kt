package com.interedes.agriculturappv2.activities.register_productor

interface RegisterProductorView {

    fun loadCoordenadas()
    fun loadInfo()
    fun loadMeses()
    fun loadDialogoRegistroExitoso()
    fun limpiarCambios()
    fun clickEdtMesSiembra()
    fun clickEdtMesCosecha()
    fun navigateToParentActivity()
    fun registerProductor()
    fun disableInputs()
    fun enableInputs()

}