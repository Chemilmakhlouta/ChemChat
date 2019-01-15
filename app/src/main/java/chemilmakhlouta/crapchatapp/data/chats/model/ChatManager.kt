package chemilmakhlouta.crapchatapp.data.chats.model

import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * Created by Rosinante24 on 11/01/18.
 */
class ChatManager(toUserId: String, private var firebaseCallBack: FirebaseCallBack?) : ChildEventListener {
    private val databaseReference: DatabaseReference

    companion object {
        @Volatile
        private var latestChatsManager: ChatManager? = null

        @Synchronized
        fun getInstance(toUserId: String, firebaseCallBack: FirebaseCallBack): ChatManager? {
            if (latestChatsManager == null) {
                synchronized(LatestChatsManager::class.java) {
                    latestChatsManager = ChatManager(toUserId, firebaseCallBack)
                }
            }
            return latestChatsManager
        }
    }

    init {
        val fromId = FirebaseAuth.getInstance().uid

        databaseReference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toUserId")
    }

    fun addMessageListeners() = databaseReference.addChildEventListener(this)

    fun removeListener() = databaseReference.removeEventListener(this)

    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
        firebaseCallBack!!.onNewMessage(dataSnapshot)
    }

    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
        firebaseCallBack!!.onNewMessage(dataSnapshot)

    }

    override fun onChildRemoved(dataSnapshot: DataSnapshot) {

    }

    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onCancelled(databaseError: DatabaseError) {

    }

    fun destroy() {
        latestChatsManager = null
        firebaseCallBack = null
    }
}
