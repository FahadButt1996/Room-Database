package com.ttl.roomdatabase

import com.ttl.roomdatabase.model.User

interface RecyclerItemClick{

    fun deleteItemClicked(itemId : Int)

    fun editItemClicked(user: User)
}