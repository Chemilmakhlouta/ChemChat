package chemilmakhlouta.crapchatapp.data.chats

import chemilmakhlouta.crapchatapp.data.chats.model.ChatMessage
import chemilmakhlouta.crapchatapp.data.chats.model.mapSingleUser
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

class UserService @Inject constructor(private val userDataStore: UserDataStore) : UserRepository {

    override fun sendChat(message: String, toUserId: String): Completable {
        val fromId = FirebaseAuth.getInstance().uid

        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toUserId").push()
        val toReference = FirebaseDatabase.getInstance().getReference("/user-messages/$toUserId/$fromId").push()

        val user: User? = userDataStore.user ?: User()

        val chatMessage = ChatMessage(reference.key!!, message, fromId!!, toUserId, System.currentTimeMillis() / 1000, user!!.profileImageUrl)

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

    override fun getUser(): Single<User> {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        return create { subscribe ->
            ref.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot) {
                    subscribe.onSuccess(mapSingleUser(p0))
                }

                override fun onCancelled(p0: DatabaseError) = subscribe.onError(p0.toException())
            })
        }
    }

    override fun setUserProfileImage(user: User) {
        userDataStore.user = user
    }
}