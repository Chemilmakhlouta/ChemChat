package chemilmakhlouta.chemchatapp.domain.registration.usecase

import android.net.Uri
import chemilmakhlouta.chemchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.chemchatapp.data.registration.RegistrationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class RegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    fun register(email: String, password: String): Completable =
            registrationRepository.register(email, password)

    fun uploadImage(selectedImageUri: Uri): Single<String> =
            registrationRepository.uploadImage(selectedImageUri)

    fun saveUser(username: String, selectedImageUri: String): Completable =
            registrationRepository.saveUser(username, selectedImageUri)
}