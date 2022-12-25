package com.example.myapplication.viewmodel

import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable
import io.reactivex.Single

class StudentViewModel(private val mainRepository: MainRepository) {

    fun getAllStudent() :Single<List<EntityStudent>> {
        return mainRepository.getAllStudent()
    }
    fun deleteStudent(student: EntityStudent) :Completable {
        return mainRepository.deleteStudent(student)
    }

}