package com.ttl.roomdatabase.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

/**
 * Created by fahad.waqar on 10-Apr-18.
 */
@Entity
data class User(
        @NonNull
        @PrimaryKey(autoGenerate = true)
        var id: Int ,
        var name: String? = "",
        var contact_no: String? = "",
        var cnic: String? = "",
        var address: String? = ""
)