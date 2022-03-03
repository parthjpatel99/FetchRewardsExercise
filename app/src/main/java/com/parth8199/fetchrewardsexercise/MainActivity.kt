package com.parth8199.fetchrewardsexercise

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val fetchRewardsService : FetchRewardsService = retrofit.create(FetchRewardsService::class.java)

        fetchRewardsService.getItemById().enqueue(object: Callback<GetListFetchRewards> {
            override fun onResponse(call: Call<GetListFetchRewards>, response: Response<GetListFetchRewards>) {
                Log.i(TAG, response.toString())

                if(!response.isSuccessful){
                    Toast.makeText(this@MainActivity,"Unsuccessful Network Call!",Toast.LENGTH_SHORT).show()
                    return
                }
                val body = response.body()
                if (body is ArrayList<*>){
                    val item1 = body[0]
                    Log.i(TAG, "This is here $body")
                    textView.text = item1.id.toString()
                }


            }

            override fun onFailure(call: Call<GetListFetchRewards>, t: Throwable) {
                Log.i(TAG, t.message ?: "Null Message")
            }

        })
    }
}