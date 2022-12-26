package com.example.myapplication.model.dataclass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_student")
data class EntityStudent (
    val name :String ,
    val family :String ,
    val course :String ,
    val score :String ,
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null) :Parcelable