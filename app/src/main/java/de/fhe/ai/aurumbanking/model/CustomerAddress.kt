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
    private var customerAddressId: Long? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Customer Id")
    private var customerId: Long? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Street Name")
    private var streetname: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "modified")
    private var housenumber: Long? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "modified")
    private var additionalHousenumberValue: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "City")
    private var city: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "ZipCode")
    private var zipCode: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Country")
    private var country: Long? = null
        get() = field
        set(value) {
            field = value
        }
}