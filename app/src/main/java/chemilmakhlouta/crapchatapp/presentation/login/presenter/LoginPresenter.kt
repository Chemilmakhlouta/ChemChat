package chemilmakhlouta.crapchatapp.presentation.login.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class LoginPresenter @Inject constructor() : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }
    // endregion

    interface Display {
        fun showError()
    }

    interface Router {
    }
}