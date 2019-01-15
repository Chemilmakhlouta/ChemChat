package chemilmakhlouta.crapchatapp.data.model

import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.HashMap

/**
 * Created by Rosinante24 on 11/01/18.
 */

class FirebaseManager(roomName: String, private var firebaseCallBack: FirebaseCallBack?) : ChildEventListener {
    private val databaseReference: DatabaseReference

    init {
        databaseReference = FirebaseDatabase.getInstance().reference.child(roomName)
    }

    fun addMessageListeners() {
        databaseReference.addChildEventListener(this)
    }

    fun removeListener() {
        databaseReference.removeEventListener(this)
    }

    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
        firebaseCallBack!!.onNewMessage(dataSnapshot)
    }

    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onChildRemoved(dataSnapshot: DataSnapshot) {

    }

    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onCancelled(databaseError: DatabaseError) {

    }

    fun sendMessageToFirebase(message: String){
        val map = HashMap<String, Any>()
        map["text"] = message
        map["time"] = System.currentTimeMillis()
        map["senderId"] = FirebaseAuth.getInstance().currentUser!!.uid
        val keyToPush = databaseReference.push().key
        databaseReference.child(keyToPush!!).setValue(map)
    }

    fun destroy() {
        firebaseManager = null
        firebaseCallBack = null
    }

    companion object {
        @Volatile
        private var firebaseManager: FirebaseManager? = null

        @Synchronized
        fun getInstance(roomName: String, firebaseCallBack: FirebaseCallBack): FirebaseManager? {
            if (firebaseManager == null) {
                synchronized(FirebaseManager::class.java) {
                    firebaseManager = FirebaseManager(roomName, firebaseCallBack)
                }
            }
            return firebaseManager
        }
    }
}
