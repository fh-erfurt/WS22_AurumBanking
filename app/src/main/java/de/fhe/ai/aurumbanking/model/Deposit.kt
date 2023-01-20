package de.fhe.ai.aurumbanking.model

import androidx.annotation.NonNull
import androidx.room.*
import de.fhe.ai.aurumbanking.core.Converters
import java.math.BigDecimal
import java.text.DecimalFormat


@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(Converters::class)
data class Deposit(
    @ColumnInfo(name = "Current Depostit Value")
     var currentDepostitValue: BigDecimal? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "depositId", index = true)
     var depositId: Long? = null

    @ColumnInfo(name = "customerId")
     var customerId: Long? = null

}