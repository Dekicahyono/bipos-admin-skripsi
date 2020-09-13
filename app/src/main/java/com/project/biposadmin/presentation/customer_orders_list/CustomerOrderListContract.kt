package com.project.biposadmin.presentation.customer_orders_list

interface CustomerOrderListContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showUserList(userList: List<String>?)
    }

    interface Presenter {
        fun getUserList()
    }
}