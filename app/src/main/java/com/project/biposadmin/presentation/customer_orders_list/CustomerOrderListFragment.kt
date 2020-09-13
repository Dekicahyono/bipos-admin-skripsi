package com.project.biposadmin.presentation.customer_orders_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.biposadmin.R
import com.project.biposadmin.presentation.order_list.OrderListActivity
import kotlinx.android.synthetic.main.fragment_customer_order_list.*

class CustomerOrderListFragment : Fragment(), CustomerOrderListContract.View {

    private lateinit var presenter: CustomerOrderListPresenter

    private val customerAdapter = CustomerAdapter {
        val intent = Intent(requireContext(), OrderListActivity::class.java)
        intent.putExtra("name", it)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = CustomerOrderListPresenter(this)
        presenter.getUserList()

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showUserList(userList: List<String>?) {
        if (userList != null) {
            Log.d("TAG", "showUserList size: ${userList.size}")
            customerAdapter.addItems(userList)
            rv_orders.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = customerAdapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_order_list, container, false)
    }


}