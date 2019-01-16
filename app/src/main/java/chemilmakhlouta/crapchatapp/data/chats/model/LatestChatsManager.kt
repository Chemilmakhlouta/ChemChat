package chemilmakhlouta.crapchatapp.data.chats.model

import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by Rosinante24 on 11/01/18.
 */

class LatestChatsManager(private var firebaseCallBack: FirebaseCallBack?) : ChildEventListener {
    private val databaseReference: DatabaseReference

    companion object {
        @Volatile
        private var latestChatsManager: LatestChatsManager? = null

        @Synchronized
        fun getInstance(firebaseCallBack: FirebaseCallBack): LatestChatsManager? {
            if (latestChatsManager == null) {
                synchronized(LatestChatsManager::class.java) {
                    latestChatsManager = LatestChatsManager(firebaseCallBack)
                }
            }
            return latestChatsManager
        }
    }

    init {
        val fromId = FirebaseAuth.getInstance().uid

        databaseReference = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
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
