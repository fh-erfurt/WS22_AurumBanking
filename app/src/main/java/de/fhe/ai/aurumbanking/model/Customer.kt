package de.fhe.ai.aurumbanking.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(

    @ColumnInfo(name = "Lastname")
    var lastname: String? = null,

    @ColumnInfo(name = "Firstname")
    var firstname: String? = null,


    @ColumnInfo(name = "Customer Email")
    var email: String? = null,


    @ColumnInfo(name = "Customer Phonenumber")
    var phonenumber: Int? = null,
) {
    @ColumnInfo(name = "customerId", index = true)
    @PrimaryKey(autoGenerate = true)
    var customerId: Long? = null
}