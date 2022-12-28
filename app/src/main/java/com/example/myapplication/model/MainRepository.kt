package com.example.myapplication.model

import androidx.lifecycle.LiveData
import com.example.myapplication.model.apimanager.ApiService
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.EntityStudent
import com.example.myapplication.model.dataclass.Products

class MainRepository (
    private val myDataBase: MyDataBase,
    private val apiService: ApiService
    ) {

    fun getAllStudent():LiveData<List<EntityStudent>> {
        return myDataBase.daoStudent.getAllStudent()
    }
    suspend fun insertOrUpdateStudent(student: EntityStudent) {
        return myDataBase.daoStudent.insertOrUpdateStudent(student)
    }
    suspend fun deleteStudent(student: EntityStudent){
        return myDataBase.daoStudent.deleteStudent(student)
    }

     suspend fun getAllProducts () : Products{
        return apiService.getAllProducts()
    }
    suspend fun insertAllProducts(data : List<EntityProducts>) {
       return myDataBase.daoProduct.insertAllProducts(data)
    }
    fun showAllProducts() :LiveData<List<EntityProducts>> {
       return myDataBase.daoProduct.showAllProduct()
    }

}