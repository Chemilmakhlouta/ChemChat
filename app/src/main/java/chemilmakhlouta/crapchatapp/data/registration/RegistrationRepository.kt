package chemilmakhlouta.crapchatapp.data.registration

import android.net.Uri
import io.reactivex.Completable

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */
interface RegistrationRepository {
    fun register(email: String, password: String): Completable
    fun uploadImage(selectedPhotoUri: Uri): Completable
    fun saveUser(username: String, profileImageUrl: String): Completable
}