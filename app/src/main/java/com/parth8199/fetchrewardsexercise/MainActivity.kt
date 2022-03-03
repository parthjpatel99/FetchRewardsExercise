package com.parth8199.fetchrewardsexercise

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview)

        val moshi = Moshi.Builder().add(FetchRewardsArrayListMoshiAdapter()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
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
                    Log.i(TAG, "This is here $body")
                }


            }

            override fun onFailure(call: Call<GetListFetchRewards>, t: Throwable) {
                Log.i(TAG, t.message ?: "Null Message")
            }

        })
    }
}