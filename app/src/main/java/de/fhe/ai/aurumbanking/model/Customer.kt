package de.fhe.ai.aurumbanking.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Customer () {
    @ColumnInfo(name = "Customer Id")
    @PrimaryKey(autoGenerate = true)
    private var customerId : Long?=null
        get() = field

    @NonNull
    @ColumnInfo(name = "Lastname")
    private var lastname: String ?=null
        get() = field
        set(value) {
            field = value
        }
    @NonNull
    @ColumnInfo(name = "Midname")
    private var midname: String ?=null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Firstname")
    private var firstname: String ?=null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Customer Email")
    private var email: String ?=null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Customer Phonenumber")
    private var phonenumber: String ?=null
        get() = field
        set(value) {
            field = value
        }

}