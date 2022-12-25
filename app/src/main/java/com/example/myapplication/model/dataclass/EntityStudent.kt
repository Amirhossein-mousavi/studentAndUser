package com.example.myapplication.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_student")
data class EntityStudent (
    val name :String ,
    val family :String ,
    val course :String ,
    val score :String ,
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null)