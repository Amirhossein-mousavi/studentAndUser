package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.Products
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel
    (private val mainRepository: MainRepository){



            fun getAllProducts() :ArrayList<Products.Product> {
                var data = listOf<Products.Product>()
                GlobalScope.launch {
                    data = mainRepository.getAllProducts()
                }
                return ArrayList(data) }


}