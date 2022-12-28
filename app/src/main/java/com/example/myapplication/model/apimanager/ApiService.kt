package com.example.myapplication.model.apimanager

import androidx.lifecycle.LiveData
import com.example.myapplication.model.dataclass.Products
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
     suspend fun getAllProducts() :Products

}