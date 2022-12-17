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
class OrderOutput {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Order Output Id")
    private var orderOutputId: Long? = null
        get() = field
        set(value) {
            field = value
        }


    @NonNull
    @ColumnInfo(name = "Transaction List Id")
    private var transactionListId: Long? = null
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
    @ColumnInfo(name = "Beneficiary")
    private var beneficiary: String? = null
        get() = field
        set(value) {
            field = value
        }


    @NonNull
    @ColumnInfo(name = "IBAN")
    private var iBAN: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "BIC")
    private var bIC: String? = null
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "dstinationBankname")
    private var destinationBankname: String? = null
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
    private var purposeOfUse: String? = null
        get() = field
        set(value) {
            field = value
        }



}