package com.example.myapplication.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemStudentBinding
import com.example.myapplication.model.dataclass.EntityStudent

class StudentAdapter(
   private val data : ArrayList<EntityStudent> ,
   private val studentEvent: StudentEvent
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

            _binding.root.setOnClickListener {
                studentEvent.clicked(student , adapterPosition)
            }
            _binding.root.setOnLongClickListener {
                studentEvent.longClicked(student , adapterPosition)
                true
            }
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

    fun refreshData(newData : ArrayList<EntityStudent>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    fun deleteStudent(student: EntityStudent , position: Int) {
        data.remove(student)
        notifyItemRemoved(position)
    }

    interface StudentEvent{
        fun clicked(student: EntityStudent , position: Int)
        fun longClicked(student: EntityStudent , position: Int)
    }
}