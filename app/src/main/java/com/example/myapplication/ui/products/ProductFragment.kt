package com.example.myapplication.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductBinding
import com.example.myapplication.model.MainRepository
import com.example.myapplication.model.apimanager.ApiServiceSingleton
import com.example.myapplication.model.database.MyDataBase
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.Products
import com.example.myapplication.util.Injector
import com.example.myapplication.util.InternetChecker
import com.example.myapplication.util.toast
import com.example.myapplication.viewmodel.ProductViewModel


class ProductFragment : Fragment() , ProductAdapter.ProductEvent {
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
        viewModel = ProductViewModel(Injector.getMainRepository(requireContext()))
        if (InternetChecker().checkForInternet(requireContext()))
        {
            viewModel.insertAllProducts()
        } else {
            requireContext().toast("Please check your connection")
        }
        viewModel.showAllProducts().observe(this.viewLifecycleOwner) {
            myAdapter.refreshData(it)
        }


    }

    private fun initialAdapter(){
        val data = arrayListOf<EntityProducts>()
         myAdapter = ProductAdapter(data , this)
        _binding.recyclerProduct.adapter = myAdapter
        _binding.recyclerProduct.layoutManager = LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        _binding.recyclerProduct.recycledViewPool.setMaxRecycledViews(0,0)
    }

    override fun click() {

    }


}