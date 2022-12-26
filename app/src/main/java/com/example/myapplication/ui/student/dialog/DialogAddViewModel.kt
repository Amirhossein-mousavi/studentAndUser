package com.example.myapplication.ui.student.dialog

import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DialogAddViewModel(private val mainRepository: MainRepository) {

    fun insertOrUpdateStudent(student: EntityStudent) {
        GlobalScope.launch {
            mainRepository.insertOrUpdateStudent(student)
        }
    }
}