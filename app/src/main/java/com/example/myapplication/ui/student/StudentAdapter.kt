package com.example.myapplication.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemStudentBinding
import com.example.myapplication.model.dataclass.EntityStudent

class StudentAdapter(
   private val data : ArrayList<EntityStudent>
) :RecyclerView.Adapter<StudentAdapter.StudentViewHolder> () {
    private lateinit var _binding : ItemStudentBinding
    inner class StudentViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    {
        fun bindItem (student : EntityStudent) {
            _binding.txtName.text = student.name
            _binding.txtFamily.text = student.family
            _binding.txtCourse.text = student.course
            _binding.txtScore.text = student.score
            val firstChar = student.name[0].uppercase()
            _binding.txtFirsCharName.text = firstChar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = ItemStudentBinding.inflate(inflater , parent , false)
        return StudentViewHolder(_binding.root)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindItem(data[position])
    }
    override fun getItemCount(): Int = data.size
}