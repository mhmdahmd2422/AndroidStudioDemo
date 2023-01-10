package com.example.testapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val item_receive = 1;
    val item_sent = 2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            //inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.received, parent, false)
            return ReceiveViewHolder(view)
        } else {
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        Log.d("hello", currentMessage.message.toString())
        if (holder.javaClass == SentViewHolder::class.java) {
            //do the stuff for the sent view holder
            Log.d("check sent", currentMessage.message.toString())
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        } else if (holder.javaClass == ReceiveViewHolder::class.java) {
            //do the stuff for the recieve view holder
            Log.d("check received", currentMessage.message.toString())
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.setText(currentMessage.message)  //text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return item_sent
        } else {
            return item_receive
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)

    }
}