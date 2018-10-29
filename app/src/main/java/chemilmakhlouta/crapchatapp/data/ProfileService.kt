package chemilmakhlouta.crapchatapp.data

import android.net.Uri
import android.util.Log
import chemilmakhlouta.crapchatapp.domain.ProfileRepository
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Completable
import io.reactivex.CompletableOnSubscribe
import java.util.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 29/10/18.
 */

class ProfileService @Inject constructor(firebaseStorage: FirebaseStorage) : ProfileRepository {

    private val firebase = firebaseStorage

    override fun uploadPhoto(selectedPhoto: Uri): Completable {
        val filename = UUID.randomUUID().toString()
        val ref = firebase.getReference("/images/$filename")

        return Completable.create { completable ->
            ref.putFile(selectedPhoto).addOnSuccessListener { completable.onComplete() }
        }
    }
}