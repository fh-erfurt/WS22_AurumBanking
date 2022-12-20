package de.fhe.ai.aurumbanking.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @ColumnInfo(name = "Customer Id")
    @PrimaryKey(autoGenerate = true)
    var customerId: Long? = null,
    @NonNull
    @ColumnInfo(name = "Lastname")
    var lastname: String? = null,
    @NonNull
    @ColumnInfo(name = "Midname")
    var midname: String? = null,
    @NonNull
    @ColumnInfo(name = "Firstname")
    var firstname: String? = null,

    @NonNull
    @ColumnInfo(name = "Customer Email")
    var email: String? = null,

    @NonNull
    @ColumnInfo(name = "Customer Phonenumber")
    var phonenumber: Int? = null,
    )