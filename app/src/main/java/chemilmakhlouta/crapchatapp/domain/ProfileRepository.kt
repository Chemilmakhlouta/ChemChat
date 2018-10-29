package chemilmakhlouta.crapchatapp.domain

import android.net.Uri
import chemilmakhlouta.crapchatapp.domain.model.JobObject
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
interface ProfileRepository {
    fun uploadPhoto(selectedPhoto: Uri): Completable
}