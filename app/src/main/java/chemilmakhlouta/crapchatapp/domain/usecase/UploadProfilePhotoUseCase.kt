package chemilmakhlouta.crapchatapp.domain.usecase

import android.net.Uri
import chemilmakhlouta.crapchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.crapchatapp.domain.ProfileRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
@Mockable
class UploadProfilePhotoUseCase @Inject constructor(private val profileRepository: ProfileRepository) {

    fun uploadPhoto(selectedPhoto: Uri): Completable =
            profileRepository.uploadPhoto(selectedPhoto)
}