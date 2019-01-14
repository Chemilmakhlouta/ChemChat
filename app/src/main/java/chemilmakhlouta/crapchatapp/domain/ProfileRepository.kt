package chemilmakhlouta.crapchatapp.domain

import android.net.Uri
import io.reactivex.Completable

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
interface ProfileRepository {
    fun uploadPhoto(selectedPhoto: Uri): Completable
}