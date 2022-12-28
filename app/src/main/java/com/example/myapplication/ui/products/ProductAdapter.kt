package com.example.myapplication.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.model.dataclass.EntityProducts
import com.example.myapplication.model.dataclass.Products

class ProductAdapter(
    private val data : ArrayList<EntityProducts> ,
    private val productEvent: ProductEvent
) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var _binding : ItemProductBinding

    inner class ProductViewHolder (itemView:View) :RecyclerView.ViewHolder(itemView) {
        fun bindProduct(product : EntityProducts) {
            _binding.txtTitle.text = product.title
            _binding.txtDescription.text = product.description
            _binding.txtPrice.text = "$" + product.price.toString()
            _binding.ratingBarProducts.rating = product.rating.toFloat()
            Glide.with(_binding.root)
                .load(product.thumbnail)
                .into(_binding.imgProduct)
            _binding.root.setOnClickListener {
                productEvent.click()
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = ItemProductBinding.inflate(inflater , parent , false)
        return ProductViewHolder(_binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(data[position])
    }

    override fun getItemCount(): Int =data.size

    fun refreshData(newData : List<EntityProducts>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    interface ProductEvent{
        fun click()
    }
}