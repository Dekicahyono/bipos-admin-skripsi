package com.project.biposadmin.presentation.order_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.biposadmin.R
import com.project.biposadmin.model.Order
import com.project.biposadmin.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity(), OrderContract.View {

    private lateinit var presenter: OrderPresenter

    private lateinit var customerName: String

    private val orderAdapter = OrderAdapter {
        DialogUtils.showDialogOrderDetail(this, it, presenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        setSupportActionBar(toolbar)

        customerName = intent.getStringExtra("name")!!
        initView()

        presenter = OrderPresenter(this)
        presenter.getOrderListByCustomer(customerName)
    }

    private fun initView() {
        supportActionBar?.title = "Order $customerName"
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showOrderList(orderList: List<Order>?) {
        if (orderList != null) {
            orderAdapter.addItems(orderList)
            rv_orders.apply {
                layoutManager = LinearLayoutManager(this@OrderListActivity, RecyclerView.VERTICAL, false)
                adapter = orderAdapter
            }
        }
    }

    override fun showOrderStatus(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
    }
}