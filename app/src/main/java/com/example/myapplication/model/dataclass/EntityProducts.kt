package com.example.myapplication.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_product")
data class EntityProducts (
    val description: String,
    @PrimaryKey
    val id: Int,
    val price: Int,
    val rating: Double,
    val thumbnail: String,
    val title: String
        )