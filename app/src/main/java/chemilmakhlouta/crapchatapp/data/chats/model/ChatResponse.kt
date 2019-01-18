package chemilmakhlouta.crapchatapp.data.chats.model

import com.google.firebase.database.DataSnapshot
import java.util.HashMap

class ChatResponse(dataSnapshot: DataSnapshot) {
    var msgKey: String? = null
    var timeStamp: Long = 0
    var message: String? = null
    var fromId: String? = null
    var toId: String? = null
    var profileImage: String? = null

    init {
        val `object` = dataSnapshot.value as HashMap<*, *>?
        this.msgKey = dataSnapshot.key
        this.message = `object`!!["text"].toString()
        this.fromId = `object`["fromId"].toString()
        this.toId = `object`["toId"].toString()
        this.timeStamp = java.lang.Long.parseLong(`object`["timestamp"].toString())
        this.profileImage = `object`["profileImage"]?.toString() ?: ""
    }
}