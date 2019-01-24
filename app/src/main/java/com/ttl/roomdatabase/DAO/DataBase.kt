package com.ttl.roomdatabase.DAO

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.ttl.roomdatabase.model.User

/**
 * Created by fahad.waqar on 10-Apr-18.
 */
@Database(entities = arrayOf(User::class), version = 2)
abstract class DataBase : RoomDatabase() {

    companion object {
        private val DBName = "CFM.db"
        @Volatile
        private var instance: DataBase? = null

        @Synchronized
        fun getInstance(context: Context): DataBase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): DataBase {
            return Room.databaseBuilder(context, DataBase::class.java, DBName).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }

    }
    abstract fun getUserDao() : DAO

}
