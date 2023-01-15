package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = TransactionList::class,
        parentColumns = ["transactionListId"],
        childColumns = ["transactionListId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class OrderOutput(
    //
    // @ColumnInfo(name = "Tranaktion Date")
    // var tranaktionDate: Date? = null,


    @ColumnInfo(name = "Beneficiary")
    var beneficiary: String? = null,


    @ColumnInfo(name = "IBAN")
    var iBAN: String? = null,



    @ColumnInfo(name = "BIC")
    var bIC: String? = null,



    @ColumnInfo(name = "dstinationBankname")
    var destinationBankname: String? = null,



    @ColumnInfo(name = "Money Value")
    var moneyValue: Float? = null,



    @ColumnInfo(name = "Purpose Of Use")
    var purposeOfUse: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Order Output Id")
    var orderOutputId: Long? = null


    @ColumnInfo(name = "transactionListId")
    var transactionListId: Long? = null

}