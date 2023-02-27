package de.fhe.ai.aurumbanking.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.room.TypeConverter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

/**
 * object class that convert input data into predefined data types
 */
object Converters {

    /**
     * Converts String into Date
     * @param String
     * @return Date
     */
    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun fromStringToDate(dateString : String) : Date? {
        return SimpleDateFormat("dd.MM.yyyy").parse(dateString);
    }

    /**
     * Converts Date into String
     *
     * @param date
     * @return String
     */
    @TypeConverter
    fun fromDate(date : Date) : String {
        return date.toString()
    }

    /**
     * Converts BigDecimal into String
     *
     * @param value
     * @return String
     */
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    /**
     * Converts String into BigDecimal
     *
     * @param value
     * @return BigDecimal
     */
    @TypeConverter
    fun stringToBigDecimal(value: String): BigDecimal {
        Log.i("Sring to Decimal", "Value" + BigDecimal(value))
        return BigDecimal(value)
    }


}