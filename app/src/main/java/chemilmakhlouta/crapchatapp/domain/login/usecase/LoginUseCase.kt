package chemilmakhlouta.crapchatapp.domain.login.usecase

import chemilmakhlouta.crapchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.crapchatapp.data.login.LoginRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/19.
 */
@Mockable
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    fun login(email: String, password: String): Completable =
            loginRepository.login(email, password)
}