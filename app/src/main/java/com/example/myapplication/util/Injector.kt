package com.example.myapplication.util

import android.content.Context
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.apimanager.ApiServiceSingleton
import com.example.myapplication.model.database.MyDataBase

object Injector {
    fun getMainRepository (context: Context) :MainRepository {
        return MainRepository(
            MyDataBase.getDatabase(context) ,
            ApiServiceSingleton.apiService
        )
    }
}