package chemilmakhlouta.chemchatapp.domain.chats.usecase

import chemilmakhlouta.chemchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.chemchatapp.data.chats.UserRepository
import chemilmakhlouta.chemchatapp.data.registration.Model.User
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class SetUserProfileImageUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun setUserProfileImage(user: User): Unit = userRepository.setUserProfileImage(user)
}