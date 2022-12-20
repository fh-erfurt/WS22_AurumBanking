package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = Deposit::class,
        parentColumns = ["transactionListId"],
        childColumns = ["transactionListId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class OrderInput {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Order Input Id")
    private var orderInputId: Long? = 0
        get() = field
        set(value) {
            field = value
        }


    @NonNull
    @ColumnInfo(name = "Transaction List Id")
    private var transactionListId: Long? = 0
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Tranaktion Date")
    private var tranaktionDate: Date? = null
        get() = field
        set(value) {
            field = value
        }


    @NonNull
    @ColumnInfo(name = "Shipper Name")
    private var shipperName: String? = ""
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Source Bankname")
    private var sourceBankname: String? = ""
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "IBAN")
    private var iBAN: String? = ""
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "BIC")
    private var bIC: String? = ""
        get() = field
        set(value) {
            field = value
        }


    @NonNull
    @ColumnInfo(name = "Money Value")
    private var moneyValue: Float? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Purpose Of Use")
    private var purposeOfUse: String? = ""
        get() = field
        set(value) {
            field = value
        }


}
