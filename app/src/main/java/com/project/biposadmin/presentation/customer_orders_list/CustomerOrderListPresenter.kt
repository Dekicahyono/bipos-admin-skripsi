package com.project.biposadmin.presentation.customer_orders_list

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CustomerOrderListPresenter(private val view: CustomerOrderListContract.View) : CustomerOrderListContract.Presenter {

    private var reference = FirebaseDatabase.getInstance().getReference("orders")
    private var userList = mutableListOf<String>()

    override fun getUserList() {
        view.showLoading()
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.hideLoading()
                for (userSnapShot in dataSnapshot.children) {
                    val order = userSnapShot.key.toString()
                    userList.add(order)
                }

                view.showUserList(userList)
            }

        })
    }
}