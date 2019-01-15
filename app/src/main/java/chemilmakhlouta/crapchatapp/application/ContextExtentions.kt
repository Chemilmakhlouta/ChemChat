package chemilmakhlouta.crapchatapp.application

import java.text.SimpleDateFormat
import java.util.*

fun convertTime(timeStamp: Long): String {
    val simpleDateFormat: SimpleDateFormat
    simpleDateFormat = SimpleDateFormat("HH:mm")
    val date = Date(timeStamp)
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(date)
}