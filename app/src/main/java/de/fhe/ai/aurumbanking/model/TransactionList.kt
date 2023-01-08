package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Deposit::class,
        parentColumns = ["depositId"],
        childColumns = ["depositId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TransactionList(

    @ColumnInfo(name = "Transction Deduction Flag")
    var TransctionDeductionFlag: Boolean? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionListId", index = true)
    var transactionListId: Long? = 0

    @ColumnInfo(name = "depositId")
    var depositId: Long? = 0
}