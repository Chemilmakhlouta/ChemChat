package chemilmakhlouta.crapchatapp.data.chats

import android.util.Log
import chemilmakhlouta.crapchatapp.data.chats.model.ChatMessage
import chemilmakhlouta.crapchatapp.data.chats.model.mapUsers
import chemilmakhlouta.crapchatapp.data.registration.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.create
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */

class UserService @Inject constructor() : UserRepository {
    override fun sendChat(message: String, toUserId: String): Completable {
        val fromId = FirebaseAuth.getInstance().uid

        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toUserId").push()
        val toReference = FirebaseDatabase.getInstance().getReference("/user-messages/$toUserId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, message, fromId!!, toUserId, System.currentTimeMillis() / 1000)

        return Completable.create { subscriber ->
            reference.setValue(chatMessage)
                    .addOnSuccessListener {
                        toReference.setValue(chatMessage)

                        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toUserId")
                        latestMessageRef.setValue(chatMessage)

                        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toUserId/$fromId")
                        latestMessageToRef.setValue(chatMessage)

                        subscriber.onComplete()
                    }
        }
    }


    override fun getUsers(): Single<ArrayList<User>> {
        return create { subscriber ->
            val ref = FirebaseDatabase.getInstance().getReference("/users")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    subscriber.onSuccess(mapUsers(p0))
                }

                override fun onCancelled(p0: DatabaseError) {
                    subscriber.onError(p0.toException())
                }
            }
            )
        }
    }
}