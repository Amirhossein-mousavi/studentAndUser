package com.example.myapplication.ui.student.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.DialogAddBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityStudent
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddStudentDialog : DialogFragment() {

    private lateinit var _binding : DialogAddBinding
    private lateinit var viewModel :DialogAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddBinding.inflate(layoutInflater , container , false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = DialogAddViewModel(MainRepository(MyDataBase.getDatabase(requireContext())))
        _binding.btnCancel.setOnClickListener {
            dismiss()
        }
        _binding.btnConfirm.setOnClickListener {
             val name = _binding.edtName.text.toString()
             val family = _binding.edtFamily.text.toString()
             val course = _binding.edtCourse.text.toString()
             val score = _binding.edtScore.text.toString()
            if (
                name.isNotEmpty() &&
                family.isNotEmpty() &&
                course.isNotEmpty() &&
                score.isNotEmpty()
            ) {
                val student = EntityStudent(name , family, course, score)
                viewModel.insertOrUpdateStudent(student)
                    .subscribeOn(Schedulers.io())
                    .subscribe(object :CompletableObserver {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onComplete() {
                            dismiss()
                        }

                        override fun onError(e: Throwable) {
                            Log.v("Test" , e.message!!)
                        }
                    })
            }
        }



    }
}