package chemilmakhlouta.chemchatapp.domain.registration.usecase

import android.net.Uri
import chemilmakhlouta.chemchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.chemchatapp.data.registration.RegistrationRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class UploadImageUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    fun uploadImage(selectedImageUri: Uri): Single<String> =
            registrationRepository.uploadImage(selectedImageUri)
}