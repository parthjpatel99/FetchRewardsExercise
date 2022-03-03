package com.parth8199.fetchrewardsexercise

import android.os.Bundle
import android.util.Log
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

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val fetchRewardsService : FetchRewardsService = retrofit.create(FetchRewardsService::class.java)

        fetchRewardsService.getItemById().enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i(TAG, response.toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i(TAG, t.message ?: "Null Message")
            }

        })
    }
}