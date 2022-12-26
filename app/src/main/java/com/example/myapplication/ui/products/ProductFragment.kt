package com.example.myapplication.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.apimanager.ApiServiceSingleton
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.Products
import com.example.myapplication.viewmodel.ProductViewModel


class ProductFragment : Fragment() {
    private lateinit var _binding : FragmentProductBinding
    private lateinit var viewModel : ProductViewModel
    private lateinit var myAdapter : ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProductBinding.inflate(inflater , container , false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialAdapter()
        viewModel = ProductViewModel(
            MainRepository(
                MyDataBase.getDatabase(requireContext()) ,
                ApiServiceSingleton.apiService
            )
        )
        myAdapter.refreshData(viewModel.getAllProducts())

    }

    private fun initialAdapter(){
        val data = arrayListOf<Products.Product>()
         myAdapter = ProductAdapter(data)
        _binding.recyclerProduct.adapter = myAdapter
        _binding.recyclerProduct.layoutManager = LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        _binding.recyclerProduct.recycledViewPool.setMaxRecycledViews(0,0)
    }


}