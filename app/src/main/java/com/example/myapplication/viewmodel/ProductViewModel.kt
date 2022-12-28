package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.Products
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel
    (private val mainRepository: MainRepository){

    fun insertAllProducts() {
        GlobalScope.launch {
            val products = arrayListOf<EntityProducts>()
            val data = mainRepository.getAllProducts()
            data.products.forEach {
                val product = EntityProducts(
                    it.description ,
                    it.id,
                    it.price,
                    it.rating,
                    it.thumbnail,
                    it.title
                )
                products.add(product)
            }
            mainRepository.insertAllProducts(products)
        }

    }

    fun showAllProducts() :LiveData<List<EntityProducts>> {
        return mainRepository.showAllProducts()
    }


}