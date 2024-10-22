package com.example.userbuyify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.userbuyify.databinding.ItemViewCartProductsBinding
import com.example.userbuyify.roomdb.CartProductTable

class AdapterCartProducts : RecyclerView.Adapter<AdapterCartProducts.CartProductsViewHolder>(){   //1.recycleView extend 2.create viewHolder class & 3.Create Three Override function
    class CartProductsViewHolder (val binding: ItemViewCartProductsBinding) : ViewHolder(binding.root) //first line to create 2nd line class viewHolder

    val diffUtil = object : DiffUtil.ItemCallback<CartProductTable>() {
        override fun areItemsTheSame(
            oldItem: CartProductTable,
            newItem: CartProductTable
        ): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(
            oldItem: CartProductTable,
            newItem: CartProductTable
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder {
        return  CartProductsViewHolder(ItemViewCartProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        val product = differ.currentList[position]

        holder.binding.apply {
            Glide.with(holder.itemView).load(product.productImage).into(ivProductImage)
            tvProductTitle.text = product.productTitle
            tvProductQuantity.text = product.productQuantity
            tvProductPrice.text = product.productPrice
            tvProductCount.text = product.productCount.toString()

        }
    }
}