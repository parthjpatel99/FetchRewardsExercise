package com.parth8199.fetchrewardsexercise

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG="RvViewAdapter"
class RvViewAdapter(private val context: Context, private val frList:GetListFetchRewards):RecyclerView.Adapter<RvViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val frItem = frList[position]
        holder.bind(frItem)
    }

    override fun getItemCount() = frList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(frItem: GetListFetchRewardsItem) {
            tvItem.text = frItem.listId.toString().plus("  ").plus(frItem.id.toString()).plus("  ").plus(frItem.name)
        }

        private val tvItem = itemView.findViewById<TextView>(R.id.tvItem)


    }

}