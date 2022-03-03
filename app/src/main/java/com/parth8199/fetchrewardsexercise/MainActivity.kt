package com.parth8199.fetchrewardsexercise

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val frList = GetListFetchRewards()
    private lateinit var rvView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvView = findViewById(R.id.rvList)
        val rvAdapter = RvViewAdapter(this, frList)
        rvView.adapter = rvAdapter
        rvView.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val fetchRewardsService: FetchRewardsService =
            retrofit.create(FetchRewardsService::class.java)

        fetchRewardsService.getItemById().enqueue(object : Callback<GetListFetchRewards> {
            override fun onResponse(
                call: Call<GetListFetchRewards>,
                response: Response<GetListFetchRewards>
            ) {
                Log.i(TAG, response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Unsuccessful Network Call!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                val body = response.body()!!
                for (item in body) {
                    if (item.name.isNullOrEmpty()) {
                        continue
                    }
                    frList.add(item)
                }
                frList.sortWith(compareBy<GetListFetchRewardsItem> { it.listId }.thenBy { it.id })
                rvAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<GetListFetchRewards>, t: Throwable) {
                Log.i(TAG, t.message ?: "Null Message")
            }

        })
    }
}