package chemilmakhlouta.crapchatapp.presentation.chats.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.data.registration.Model.User
import chemilmakhlouta.crapchatapp.domain.chats.usecase.GetUsersUseCase
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class SelectRecipientPresenter @Inject constructor(private val getUsersUseCase: GetUsersUseCase): Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private var getUsersObservable: Single<ArrayList<User>>? = null
    private var getUsersSubscription = Disposables.disposed()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() {
        getUsers()
    }
    // endregion

    private fun getUsers() {
        if (getUsersObservable == null) {
            getUsersObservable = getUsersUseCase.getUsers()
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        display.hideLoading()
                        getUsersObservable = null
                    }

            subscribeToGetUsers()
        }
    }

    private fun subscribeToGetUsers() {
        getUsersObservable?.let {
            getUsersSubscription = it.subscribe(this::onGetUsersSuccess, this::onGetUsersFailure)
        }
    }

    private fun onGetUsersSuccess(usersList: ArrayList<User>) {
        display.showUsers(usersList)
    }

    private fun onGetUsersFailure(throwable: Throwable) {
        display.showError(throwable.message!!)
    }

    interface Display {
        fun showLoading()
        fun hideLoading()

        fun showUsers(usersList: ArrayList<User>)

        fun showError(message: String)
    }

    interface Router {
    }
}
