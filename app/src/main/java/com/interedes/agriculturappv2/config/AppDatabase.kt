package com.interedes.agriculturappv2.config

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "db_agriculturapp"
    const val VERSION: Int = 1
}