package chemilmakhlouta.chemchatapp.application.callbackinterfaces

import com.google.firebase.database.DataSnapshot

interface FirebaseCallBack {
    fun onNewMessage(dataSnapshot: DataSnapshot)
}