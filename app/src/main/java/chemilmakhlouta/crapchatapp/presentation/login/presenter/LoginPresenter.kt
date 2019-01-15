package chemilmakhlouta.crapchatapp.presentation.login.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.domain.login.usecase.LoginUseCase
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class LoginPresenter @Inject constructor(private val loginUseCase: LoginUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private var loginObservable: Completable? = null
    private var loginSubscription = Disposables.disposed()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }
    // endregion

    // region Private Functions
    private fun login(email: String, password: String) {
        if (loginObservable == null) {
            loginObservable = loginUseCase.login(email, password)
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        display.hideLoading()
                        loginObservable = null
                    }

            subscribeToLogin()
        }
    }

    private fun subscribeToLogin() {
        loginObservable?.let {
            loginSubscription = it.subscribe(this::onLoginSuccess, this::onLoginFailure)
        }
    }

    private fun onLoginSuccess() {
        router.navigateToLatestChats()
    }

    private fun onLoginFailure(throwable: Throwable) = display.showError()
    // endregion

    // region public functions
    fun onLoginButtonClicked(email: String, password: String) = login(email, password)

    fun onBackToRegistrationClicked() = router.backToRegistration()

    // endregion

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun showError()
    }

    interface Router {
        fun backToRegistration()
        fun navigateToLatestChats()
    }
}