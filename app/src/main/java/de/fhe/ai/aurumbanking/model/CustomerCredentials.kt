package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class CustomerCredentials () {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Customer Credentials Id")
    private var customerCredentialsId: Long? = 0
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Customer Id")
    private var customerId: Long? = 0
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "User Password")
    private var password: String? = ""
        get() = field
        set(value) {
            field = value
        }

}