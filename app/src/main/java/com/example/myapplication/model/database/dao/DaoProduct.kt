package com.example.myapplication.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.Products

@Dao
interface DaoProduct {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts (data : List<EntityProducts>)

    @Query("SELECT * FROM tb_product")
    fun showAllProduct() :LiveData<List<EntityProducts>>
}