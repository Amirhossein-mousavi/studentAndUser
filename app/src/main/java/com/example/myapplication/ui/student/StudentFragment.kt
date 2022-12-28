package com.example.myapplication.ui.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStudentBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.apimanager.ApiServiceSingleton
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityStudent
import com.example.myapplication.util.Injector
import com.example.myapplication.viewmodel.StudentViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class StudentFragment : Fragment() , StudentAdapter.StudentEvent {
    private lateinit var _binding : FragmentStudentBinding
    private lateinit var viewModel : StudentViewModel
    private lateinit var myAdapter: StudentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentBinding.inflate(inflater , container , false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter()
        viewModel = StudentViewModel(Injector.getMainRepository(requireContext()))
        viewModel.getAllStudent().observe(this.viewLifecycleOwner , object :Observer<List<EntityStudent>> {
            override fun onChanged(t: List<EntityStudent>?) {
                myAdapter.refreshData(ArrayList(t))
            }
        })


        _binding.btnAddStudent.setOnClickListener {
            findNavController().navigate(R.id.action_studentFragment_to_studentAddDialog)
        }
    }

    override fun clicked(student: EntityStudent, position: Int) {

        findNavController().navigate(StudentFragmentDirections.actionStudentFragmentToStudentAddDialog(student))
    }

    override fun longClicked(student: EntityStudent, position: Int) {
        val dialog = SweetAlertDialog(context , SweetAlertDialog.WARNING_TYPE)
        dialog.cancelText = "No"
        dialog.confirmText = "Yes"
        dialog.contentText = "Are you sure about delete this student"
        dialog.titleText = "Delete ${student.name}"
        dialog.setConfirmClickListener {
            viewModel.deleteStudent(student)
            dialog.dismiss()
            myAdapter.deleteStudent(student , position)

        }
        dialog.setCancelClickListener {
            dialog.dismissWithAnimation()
        }
        dialog.show()
    }

    fun createAdapter() {
        val data = arrayListOf<EntityStudent>()
        myAdapter = StudentAdapter(data , this)
        _binding.recyclerStudent.adapter = myAdapter
        _binding.recyclerStudent.layoutManager = LinearLayoutManager(context)
        _binding.recyclerStudent.recycledViewPool.setMaxRecycledViews(0,0)
    }

}