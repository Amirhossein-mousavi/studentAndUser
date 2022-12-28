package com.example.myapplication.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.database.dao.DaoProduct
import com.example.myapplication.model.database.dao.DaoStudent
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.EntityStudent

@Database(entities = [EntityStudent::class , EntityProducts::class] , version = 1 , exportSchema = false)
abstract class MyDataBase :RoomDatabase(){
    abstract val daoStudent :DaoStudent
    abstract val daoProduct :DaoProduct
    companion object {
        private var myDataBase :MyDataBase? = null
        fun getDatabase (context :Context) :MyDataBase {
            if (myDataBase ==null) {
                myDataBase = Room.databaseBuilder(
                    context.applicationContext ,
                    MyDataBase::class.java,
                    "myDatabase"
                ).build()
            }
            return myDataBase!!
        }
    }
}