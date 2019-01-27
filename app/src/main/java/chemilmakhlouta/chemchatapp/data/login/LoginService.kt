package chemilmakhlouta.chemchatapp.data.login

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */

class LoginService @Inject constructor() : LoginRepository {
    override fun login(email: String, password: String): Completable {
        return Completable.create { subscriber ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        subscriber.onComplete()
                    }
                    .addOnFailureListener {
                        subscriber.onError(it)
                    }
        }
    }
}