package chemilmakhlouta.seekapp.presentation.presenter

import chemilmakhlouta.seekapp.application.Presenter

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class JobSearchPresenter : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }
    // endregion

    // region UI Interactions
    fun onSeekClicked(keywords: String, location: String) = router.navigateToJobList(keywords, location)
    // endregion

    interface Display {
    }

    interface Router {
        fun navigateToJobList(keywords: String, location: String)
    }
}