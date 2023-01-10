package com.example.testapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        //GET THE CURRENT USER FROM THE LIST OF USERS
        val currentUser = userList[position]

        holder.textName.text = currentUser.name


        //when the user clicks on a name from the names list
        //then it goes to the chat screen
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)

            //here we pass the name and uid of the user to the chat activity class
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}