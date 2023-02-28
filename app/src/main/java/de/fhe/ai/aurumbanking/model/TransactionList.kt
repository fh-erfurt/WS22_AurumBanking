package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.*
import de.fhe.ai.aurumbanking.core.Converters
import java.math.BigDecimal
import java.util.*

/**
 * Entity class for TransactionList database table, database table will be generated and mapped by room framework.
 *
 * @property outputFlag
 * @property transactionDate
 * @property beneficiary
 * @property iban
 * @property bic
 * @property bankname
 * @property moneyValue
 * @property purposeOfUse
 */
@TypeConverters(Converters::class)
@Entity(
    foreignKeys = [ForeignKey(
        entity = Deposit::class,
        parentColumns = ["depositId"],
        childColumns = ["depositId"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = TransactionList::class,
            parentColumns = ["transactionListId"],
            childColumns = ["transactionListId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class TransactionList(

    @ColumnInfo(name = "Output Flag")
    var outputFlag: Boolean? = null,

    @ColumnInfo(name = "Tranaction Date")
    var transactionDate: String? = null,


    @ColumnInfo(name = "Beneficiary")
    var beneficiary: String? = null,


    @ColumnInfo(name = "IBAN")
    var iban: String? = null,


    @ColumnInfo(name = "BIC")
    var bic: String? = null,


    @ColumnInfo(name = "Bankname")
    var bankname: String? = null,


    @ColumnInfo(name = "Money Value")
    var moneyValue: BigDecimal? = null,


    @ColumnInfo(name = "Purpose Of Use")
    var purposeOfUse: String? = null


) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionListId", index = true)
    var transactionListId: Long? = null

    @ColumnInfo(name = "depositId")
    var depositId: Long? = 0

    @ColumnInfo(name = "customerId")
    var customerId: Long? = null
}