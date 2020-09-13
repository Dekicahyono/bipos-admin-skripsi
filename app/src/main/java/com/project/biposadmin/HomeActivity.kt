package com.project.biposadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.project.biposadmin.presentation.add_product.BestPriceFragment
import com.project.biposadmin.presentation.add_product.ProductByCategoryFragment
import com.project.biposadmin.presentation.add_product.ProductPopularFragment
import com.project.biposadmin.presentation.add_product.TopBannerFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()

    }

    private fun initView() {
        loadFragment(TopBannerFragment())

        spinner_upload.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.uploads_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_upload.adapter = adapter
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, int: Long) {
        when (pos) {
            0 -> loadFragment(TopBannerFragment())
            1 -> loadFragment(ProductPopularFragment())
            2 -> loadFragment(BestPriceFragment())
            3 -> loadFragment(ProductByCategoryFragment())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}