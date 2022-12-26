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
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStudentBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityStudent
import com.example.myapplication.viewmodel.StudentViewModel



class StudentFragment : Fragment() {
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
        viewModel = StudentViewModel(MainRepository(MyDataBase.getDatabase(requireContext())))
        viewModel.getAllStudent().observe(this.viewLifecycleOwner , object :Observer<List<EntityStudent>> {
            override fun onChanged(t: List<EntityStudent>?) {
                myAdapter = StudentAdapter(ArrayList(t))
                _binding.recyclerStudent.adapter = myAdapter
                _binding.recyclerStudent.layoutManager = LinearLayoutManager(context)
                Log.v("test" ,  "ads")
            }
        })


        _binding.btnAddStudent.setOnClickListener {
            findNavController().navigate(R.id.action_studentFragment_to_studentAddDialog)
        }
    }

}