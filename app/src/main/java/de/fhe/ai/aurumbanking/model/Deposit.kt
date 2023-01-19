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
data class Deposit(

    @ColumnInfo(name = "Current Depostit Value")
     var currentDepostitValue: Float? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "depositId", index = true)
     var depositId: Long? = null

    @ColumnInfo(name = "customerId")
     var customerId: Long? = null

}