package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentViewModel(private val mainRepository: MainRepository) {

    fun getAllStudent() :LiveData<List<EntityStudent>> {
        return mainRepository.getAllStudent()
    }
    fun deleteStudent(student: EntityStudent) {
        GlobalScope.launch {
         mainRepository.deleteStudent(student)
        }
    }

}