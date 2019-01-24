package com.ttl.roomdatabase.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ttl.roomdatabase.R
import com.ttl.roomdatabase.RecyclerItemClick
import com.ttl.roomdatabase.model.User

class UserAdapter(var userList: MutableList<User>, var context: Context, var listener: RecyclerItemClick) :
        RecyclerView.Adapter<UserAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StatusViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false)
        return StatusViewHolder(v)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.cnic.setText(userList.get(position).cnic)
        holder.name.setText(userList.get(position).name)
        holder.address.setText(userList.get(position).address)
        holder.contact.setText(userList.get(position).contact_no)
        holder.delete_user.setOnClickListener {
            listener.deleteItemClicked(userList.get(position).id)
        }
        holder.edit_user.setOnClickListener {
            listener.editItemClicked(userList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val cnic: TextView = itemView.findViewById(R.id.cnic)
        val contact: TextView = itemView.findViewById(R.id.contact)
        var parent: ConstraintLayout = itemView.findViewById(R.id.parent)
        var delete_user: ImageView = itemView.findViewById(R.id.delete_user)
        var edit_user: ImageView = itemView.findViewById(R.id.edit_user)
    }
}
