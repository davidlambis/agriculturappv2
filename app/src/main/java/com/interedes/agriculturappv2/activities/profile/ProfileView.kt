package com.interedes.agriculturappv2.activities.profile

interface ProfileView {

    fun isCompradorOProductor()
    fun navigateToMiCuenta()
    fun navigateToMercado()
    fun navigateToUnidadProductiva()
    fun navigateToNotificaciones()
    fun limpiarCambios()
    fun logout()
}