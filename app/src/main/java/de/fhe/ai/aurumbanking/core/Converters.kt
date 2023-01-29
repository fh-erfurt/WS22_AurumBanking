package de.fhe.ai.aurumbanking.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.room.TypeConverter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

object Converters {


    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun fromDateToString(dateString : String) : Date? {
        return SimpleDateFormat("dd.MM.yyyy").parse(dateString);
    }


    @TypeConverter
    fun fromDate(date : Date) : String {
        return date.toString()
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun stringToBigDecimal(value: String): BigDecimal {
        Log.i("Sring to Decimal", "Value" + BigDecimal(value))
        return BigDecimal(value)
    }


}