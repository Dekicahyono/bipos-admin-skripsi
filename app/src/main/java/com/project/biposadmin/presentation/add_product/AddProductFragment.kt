package com.project.biposadmin.presentation.add_product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.project.biposadmin.R
import kotlinx.android.synthetic.main.activity_home.*

class AddProductFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {
        spinner_upload.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.uploads_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_upload.adapter = adapter
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, int: Long) {
        when (pos) {
            0 -> loadFragment(TopBannerFragment())
            1 -> loadFragment(ProductPopularFragment())
            2 -> loadFragment(BestPriceFragment())
            3 -> loadFragment(ProductByCategoryFragment())
            4 -> loadFragment(HomeCategoryFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}