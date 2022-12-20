package de.fhe.ai.aurumbanking.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = CASCADE
    )]
)
class CustomerAddress() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Customer Address Id")
    private var customerAddressId: Long? = 0
       

    @NonNull
    @ColumnInfo(name = "Customer Id")
    private var customerId: Long? = 0
        

    @NonNull
    @ColumnInfo(name = "Street Name")
    private var streetname: String? = ""
        

    @NonNull
    @ColumnInfo(name = "modified")
    private var housenumber: Long? = 0
       
    @NonNull
    @ColumnInfo(name = "modified")
    private var additionalHousenumberValue: String? = ""
        

    @NonNull
    @ColumnInfo(name = "City")
    private var city: String? = ""
       

    @NonNull
    @ColumnInfo(name = "ZipCode")
    private var zipCode: String? = ""
        

    @NonNull
    @ColumnInfo(name = "Country")
    private var country: Long? = 0
        get() = field
        set(value) {
            field = value
        }
}