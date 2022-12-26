package com.example.myapplication.ui.student.dialog

import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable

class DialogAddViewModel(private val mainRepository: MainRepository) {

    fun insertOrUpdateStudent(student: EntityStudent) :Completable {
        return mainRepository.insertOrUpdateStudent(student)
    }
}