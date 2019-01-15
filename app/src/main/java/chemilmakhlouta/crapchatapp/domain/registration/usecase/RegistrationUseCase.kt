package chemilmakhlouta.crapchatapp.domain.registration.usecase

import android.net.Uri
import chemilmakhlouta.crapchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.crapchatapp.data.registration.RegistrationRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class RegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    fun register(email: String, password: String): Completable =
            registrationRepository.register(email, password)

    fun uploadImage(selectedImageUri: Uri): Completable =
            registrationRepository.uploadImage(selectedImageUri)

    fun saveUser(username: String, selectedImageUri: String): Completable =
            registrationRepository.saveUser(username, selectedImageUri)
}