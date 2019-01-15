package chemilmakhlouta.crapchatapp.presentation.registration.presenter

import android.net.Uri
import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.domain.registration.usecase.RegistrationUseCase
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class RegisterPresenter @Inject constructor(private val registrationUseCase: RegistrationUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private var registerObservable: Completable? = null
    private var registerSubscription = Disposables.disposed()

    private var saveImageObservable: Completable? = null
    private var saveImageSubscription = Disposables.disposed()

    private var saveUserObservable: Completable? = null
    private var saveUserSubscription = Disposables.disposed()

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var selectedImageUri: Uri
    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    // region Private Functions
    private fun register() {
        if (registerObservable == null) {
            registerObservable = registrationUseCase.register(email, password)
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        registerObservable = null
                    }

            subscribeToRegister()
        }
    }

    private fun uploadImage() {
        if (saveImageObservable == null) {
            saveImageObservable = registrationUseCase.uploadImage(selectedImageUri)
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        saveImageObservable = null
                    }

            subscribeToUploadImage()
        }
    }

    private fun saveUser() {
        if (saveUserObservable == null) {
            saveUserObservable = registrationUseCase.saveUser(username, selectedImageUri.toString())
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        saveUserObservable = null
                    }

            subscribeToSaveUser()
        }
    }

    private fun subscribeToRegister() {
        registerObservable?.let {
            registerSubscription = it.subscribe(this::onRegisterSuccess, this::onRegisterFailure)
        }
    }

    private fun subscribeToUploadImage() {
        saveImageObservable?.let {
            saveImageSubscription = it.subscribe(this::onImageUploadSuccess, this::onRegisterFailure)
        }
    }

    private fun subscribeToSaveUser() {
        saveUserObservable?.let {
            saveUserSubscription = it.subscribe(this::onSaveUserSuccess, this::onRegisterFailure)
        }
    }

    private fun onRegisterSuccess() {
        uploadImage()
    }

    private fun onImageUploadSuccess() {
        router.navigateToLogin()
    }

    private fun onSaveUserSuccess() {
        saveUser()
    }

    private fun onRegisterFailure(throwable: Throwable) {
        display.hideLoading()
        display.showError()
    }

    fun onRegisterClicked(email: String, username: String, password: String) {
        this.email = email
        this.username = username
        this.password = password

        register()
    }

    fun onAlreadyRegisteredClicked() {
        router.navigateToLogin()
    }

    fun onProfileImageClicked() {
        router.navigateToImageSelection()
    }

    fun onPhotoSelected(selectedImageUri: Uri) {
        this.selectedImageUri = selectedImageUri

        display.showSelectedImage(selectedImageUri)
    }
    // endregion

    interface Display {
        fun showError()

        fun showLoading()
        fun hideLoading()

        fun showSelectedImage(selectedPhotoUri: Uri)
    }

    interface Router {
        fun navigateToLogin()
        fun navigateToImageSelection()
    }
}