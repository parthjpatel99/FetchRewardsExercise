package com.parth8199.fetchrewardsexercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewards
import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewardsItem


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //Attach ViewModel to our Activity
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val frList = ItemList()
    private lateinit var rvView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling RecyclerView, RecyclerViewAdapter and ItemList i.e. to be passed to RV
        rvView = findViewById(R.id.rvList)
        val rvAdapter = RvViewAdapter(this, frList)
        rvView.adapter = rvAdapter
        rvView.layoutManager = LinearLayoutManager(this)

        //Observe changes in ViewModel
        viewModel.refreshItem()
        viewModel.itemByIdLiveData.observe(this) { itemList ->
            if (itemList == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful Network Call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            //Attach Data to RV and notify RV
            frList.addAll(itemList)
            rvAdapter.notifyDataSetChanged()
        }
    }
}