package com.project.biposadmin.presentation.order_list

import com.project.biposadmin.model.Order

interface OrderContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOrderList(orderList: List<Order>?)
        fun showOrderStatus(msg: String)
    }

    interface Presenter {
        fun getOrderListByCustomer(customerName: String)
        fun postOrderStatus(order: Order)
    }
}