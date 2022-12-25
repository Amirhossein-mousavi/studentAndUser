package com.example.myapplication.ui.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStudentBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityStudent
import com.example.myapplication.viewmodel.StudentViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class StudentFragment : Fragment() {
    private lateinit var _binding : FragmentStudentBinding
    private lateinit var viewModel : StudentViewModel
    private lateinit var myAdapter: StudentAdapter
    private  var disposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentBinding.inflate(inflater , container , false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = StudentViewModel(MainRepository(MyDataBase.getDatabase(requireContext())))
        viewModel.getAllStudent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<EntityStudent>>{
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: List<EntityStudent>) {
                    myAdapter = StudentAdapter(ArrayList(t))
                    _binding.recyclerStudent.adapter = myAdapter
                }

                override fun onError(e: Throwable) {
                    Log.v("test" , e.message?: "null")
                }

            })
    }

}