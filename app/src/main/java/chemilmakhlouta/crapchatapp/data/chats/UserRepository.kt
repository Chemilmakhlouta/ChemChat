package chemilmakhlouta.crapchatapp.data.chats

import chemilmakhlouta.crapchatapp.data.registration.Model.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Chemil Makhlouta on 16/01/19.
 */
interface UserRepository {
    fun getUsers(): Single<ArrayList<User>>
    fun sendChat(message: String, toUserId: String): Completable
}