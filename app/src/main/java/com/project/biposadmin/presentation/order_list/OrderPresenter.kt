package com.project.biposadmin.presentation.order_list

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.biposadmin.model.Order

class OrderPresenter(private val view: OrderContract.View) : OrderContract.Presenter {

    private var reference = FirebaseDatabase.getInstance().getReference("orders")
    private var orderList: MutableList<Order> = mutableListOf()

    override fun getOrderListByCustomer(customerName: String) {
        view.showLoading()
        reference.child(customerName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.hideLoading()
                for (orderSnapShot in dataSnapshot.children) {
                    val order = orderSnapShot.getValue(Order::class.java)
                    orderList.add(order!!)
                }

                view.showOrderList(orderList)
            }

        })
    }

    override fun postOrderStatus(order: Order) {
        view.showLoading()

        // add to order status
        val postOrderReference =
            FirebaseDatabase.getInstance().getReference("users").child(order.customerName)
                .child("orders_status").child(order.orderNumber)
        postOrderReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.setValue(order)
                view.showOrderStatus("Berhasil di update")
                view.hideLoading()
            }

        })

        // removed from order list
        val removedFromOrderListReference = FirebaseDatabase.getInstance().reference
        val queryRemovedFromOrderList =
            removedFromOrderListReference.child("orders").child(order.customerName).orderByChild(order.orderNumber).equalTo(order.orderNumber)
        queryRemovedFromOrderList.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.removeValue()
                view.hideLoading()
            }

        })

    }



}