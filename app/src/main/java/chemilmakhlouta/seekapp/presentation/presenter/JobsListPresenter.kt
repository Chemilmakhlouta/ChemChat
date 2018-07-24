package chemilmakhlouta.seekapp.presentation.presenter

import chemilmakhlouta.seekapp.application.Presenter
import chemilmakhlouta.seekapp.domain.model.JobObject
import chemilmakhlouta.seekapp.domain.usecase.GetJobsUseCase
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class JobsListPresenter @Inject constructor(private val getJobsUseCase: GetJobsUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

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

    fun onJobClicked(url: String) = router.navigateToJob(url)
    // endregion

    // region Private Functions
    private fun getJobs() {
        if (getJobsListObservable == null) {
            getJobsListObservable = getJobsUseCase.getJobs()
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
            if (getJobsListSubscription.isDisposed) {
                getJobsListSubscription = it.subscribe(this::onJobsListSuccess, this::onJobsListFailure)
            }
        }
    }

    private fun onJobsListSuccess(jobs: List<JobObject>) =
            display.setUpJobsList(mutableListOf<JobObject>().apply {
                addAll(jobs)
            })

    private fun onJobsListFailure(throwable: Throwable) {
        display.showError()
    }
    // endregion

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun setUpJobsList(jobs: MutableList<JobObject>)
        fun showError()
    }

    interface Router {
        fun navigateToJob(url: String)
    }
}