package com.ttl.roomdatabase.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ttl.roomdatabase.model.User

/**
 * Created by fahad.waqar on 10-Apr-18.
 */
@Dao
interface DAO {
    @Query("Select * from User order by id desc")
    fun allUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query ("Delete from User where id = :userId")
    fun deleteUser(userId: Int)

    @Query("Select id, name from User where name =:name ")
    fun getSelectedUser(name: String): List<User>

}
