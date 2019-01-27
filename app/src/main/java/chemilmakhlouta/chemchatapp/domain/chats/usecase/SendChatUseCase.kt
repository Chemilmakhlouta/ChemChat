package chemilmakhlouta.chemchatapp.domain.chats.usecase

import chemilmakhlouta.chemchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.chemchatapp.data.chats.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class SendChatUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun sendChat(message: String, toUserId: String): Completable =
            userRepository.sendChat(message, toUserId)
}