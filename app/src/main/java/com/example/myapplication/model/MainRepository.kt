package com.example.myapplication.model

import androidx.lifecycle.LiveData
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import rx.schedulers.Schedulers

class MainRepository (private val myDataBase: MyDataBase) {

    fun getAllStudent():LiveData<List<EntityStudent>> {
        return myDataBase.daoStudent.getAllStudent()
    }
    suspend fun insertOrUpdateStudent(student: EntityStudent) {
        return myDataBase.daoStudent.insertOrUpdateStudent(student)
    }
    suspend fun deleteStudent(student: EntityStudent){
        return myDataBase.daoStudent.deleteStudent(student)
    }

}