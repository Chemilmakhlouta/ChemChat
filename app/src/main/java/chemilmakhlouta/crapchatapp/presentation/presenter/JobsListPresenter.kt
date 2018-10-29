package chemilmakhlouta.crapchatapp.presentation.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.domain.model.JobObject
import chemilmakhlouta.crapchatapp.domain.usecase.GetJobsUseCase
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class JobsListPresenter @Inject constructor(private val getJobsUseCase: GetJobsUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private lateinit var keywords: String
    private lateinit var location: String

    private var getJobsListObservable: Single<List<JobObject>>? = null
    private var getJobsListSubscription = Disposables.disposed()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() = getJobs()

    override fun onResume() = getJobs()

    override fun onPause() = getJobsListSubscription.dispose()

    override fun onStop() {
        getJobsListObservable = null
    }
    // endregion

    // region UI Interactions
    fun onSwipeToRefresh() = getJobs()

    fun onJobClicked(id: Int) = router.navigateToJob(id)
    // endregion

    // region Private Functions
    private fun getJobs() {
        if (getJobsListObservable == null) {
            getJobsListObservable = getJobsUseCase.getJobs(keywords, location)
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        display.hideLoading()
                        getJobsListObservable = null
                    }

            subscribeToGetJobs()
        }
    }

    private fun subscribeToGetJobs() {
        getJobsListObservable?.let {
            getJobsListSubscription = it.subscribe(this::onJobsListSuccess, this::onJobsListFailure)
        }
    }

    private fun onJobsListSuccess(jobs: List<JobObject>) {
        display.setUpJobsList(mutableListOf<JobObject>().apply {
            addAll(jobs)
        })
    }

    private fun onJobsListFailure(throwable: Throwable) {
        display.showError()
    }
    // endregion

    fun onIntentReceived(keywords: String, location: String) {
        this.keywords = keywords
        this.location = location
    }

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun setUpJobsList(jobs: MutableList<JobObject>)
        fun showError()
    }

    interface Router {
        fun navigateToJob(id: Int)
    }
}