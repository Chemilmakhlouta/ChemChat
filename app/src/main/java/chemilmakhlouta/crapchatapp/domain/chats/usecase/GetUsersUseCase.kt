package chemilmakhlouta.crapchatapp.domain.chats.usecase

import chemilmakhlouta.crapchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.crapchatapp.data.chats.UserRepository
import chemilmakhlouta.crapchatapp.data.registration.Model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun getUsers(): Single<ArrayList<User>> =
            userRepository.getUsers()

    fun getUser(): Single<User> = userRepository.getUser()

    fun setUserProfileImage(profileImageUrl: String): Unit = userRepository.setUserProfileImage(profileImageUrl)
}