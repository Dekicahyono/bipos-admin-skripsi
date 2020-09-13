package com.project.biposadmin.presentation.customer_orders_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.biposadmin.R
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerAdapter(private val onItemClicked: (user: String) -> Unit) :
    RecyclerView.Adapter<CustomerAdapter.UserViewHolder>() {

    private val productList = mutableListOf<String>()

    fun addItems(users: List<String>) {
        productList.clear()
        productList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_customer, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(userViewHolder: UserViewHolder, position: Int) {
        val user = productList[position]
        userViewHolder.bind(user)
        userViewHolder.itemView.setOnClickListener { onItemClicked(user) }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: String) {
            itemView.tv_customerName.text = user
        }

    }

}