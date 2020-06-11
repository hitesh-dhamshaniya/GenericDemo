package com.dhl.genricdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dhl.genricdemo.R
import com.dhl.genricdemo.callbacks.TopicClickListener
import com.dhl.genricdemo.datacatch.DataHolder

class TopicAdapter(val topicClickListener: TopicClickListener) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolderItem =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_topics, parent, false)
        return ViewHolder(viewHolderItem)
    }

    override fun getItemCount() = DataHolder.getTopicList().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = DataHolder.getTopicList()[position]
        holder.tvTitle.text = topic
        holder.itemView.tag = topic
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTopicTitle)

        init {
            itemView.setOnClickListener {
                val topic: String = itemView.tag as String
                topicClickListener.onTopicClick(topic)
            }
        }
    }

}