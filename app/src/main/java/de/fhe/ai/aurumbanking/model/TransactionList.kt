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
class TransactionList {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TransactionList Id")
    private var transactionListId: Long? = 0
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "Deposit Id")
    private var depositId: Long? = 0
        get() = field
        set(value) {
            field = value
        }

    @NonNull
    @ColumnInfo(name = "User Password")
    private var TransctionDeductionFlag: Boolean? = null
        get() = field
        set(value) {
            field = value
        }

}