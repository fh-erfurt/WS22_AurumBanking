package de.fhe.ai.aurumbanking.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * Entity class for customer address database table, database table customer address will be generated and mapped by room framework.
 *
 * @property streetname
 * @property streetAddressNumber
 * @property city
 * @property zipCode
 * @property country
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = CASCADE
    )]
)
data class CustomerAddress(

    @ColumnInfo(name = "Street Name", index = true)
    var streetname: String? = null,


    @ColumnInfo(name = "Housenumber")
    var streetAddressNumber: String? = null,


    @ColumnInfo(name = "City")
    var city: String? = null,


    @ColumnInfo(name = "ZipCode")
    var zipCode: String? = null,


    @ColumnInfo(name = "Country")
    var country: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Customer Address Id")
    var customerAddressId: Long? = null


    @ColumnInfo(name = "customerId", index = true)
    var customerId: Long? = null
}