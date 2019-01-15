package chemilmakhlouta.crapchatapp.data.login

import io.reactivex.Completable

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */
interface LoginRepository {
    fun login(email: String, password: String): Completable
}