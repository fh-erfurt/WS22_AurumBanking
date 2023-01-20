package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.*
import de.fhe.ai.aurumbanking.core.Converters
import java.math.BigDecimal
import java.util.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = TransactionList::class,
        parentColumns = ["transactionListId"],
        childColumns = ["transactionListId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(Converters::class)
data class OrderInput(
    //
    // @ColumnInfo(name = "Tranaktion Date")
    // var tranaktionDate: Date? = null,

    @ColumnInfo(name = "Shipper Name")
    var shipperName: String? = null,


    @ColumnInfo(name = "Source Bankname")
    var sourceBankname: String? = null,


    @ColumnInfo(name = "IBAN")
    var iBAN: String? = null,


    @ColumnInfo(name = "BIC")
    var bic: String? = null,


    @ColumnInfo(name = "Money Value")
    var moneyValue: BigDecimal? = null,


    @ColumnInfo(name = "Purpose Of Use")
    var purposeOfUse: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Order Input Id")
    var orderInputId: Long? = null



    @ColumnInfo(name = "transactionListId")
    var transactionListId: Long? = null

}
