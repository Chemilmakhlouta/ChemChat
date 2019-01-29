package chemilmakhlouta.chemchatapp.domain.registration.usecase

import chemilmakhlouta.chemchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.chemchatapp.data.registration.RegistrationRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 29/01/19.
 */
@Mockable
class SaveUserUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    fun saveUser(username: String, selectedImageUri: String): Completable =
            registrationRepository.saveUser(username, selectedImageUri)
}