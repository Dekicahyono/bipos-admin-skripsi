package com.project.biposadmin.presentation.order_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.biposadmin.R
import com.project.biposadmin.model.Order
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter(private val onItemClicked: (order: Order) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val productList = mutableListOf<Order>()

    fun addItems(users: List<Order>) {
        productList.clear()
        productList.addAll(users)
        notifyDataSetChanged()
    }

    fun refreshAdapter() {
        notifyItemRangeChanged(0, productList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(orderViewHolder: OrderViewHolder, position: Int) {
        val order = productList[position]
        orderViewHolder.bind(order)
        orderViewHolder.itemView.setOnClickListener { onItemClicked(order) }
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(order: Order) {
            Glide.with(itemView)
                .load(order.image)
                .into(itemView.img_product)
            itemView.tv_productName.text = order.productName
            itemView.tv_productPrice.text = "Rp. ${order.totalPrice}"
        }

    }

}