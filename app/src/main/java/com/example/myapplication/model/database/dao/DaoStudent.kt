package com.example.myapplication.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface DaoStudent {

    // this function is for show all student
    @Query("Select * from tb_student")
    fun getAllStudent() : LiveData<List<EntityStudent>>

    // this function is for insert or update a student
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateStudent(student: EntityStudent )

    // this function is for delete a student
    @Delete
    suspend fun deleteStudent(student: EntityStudent)
}