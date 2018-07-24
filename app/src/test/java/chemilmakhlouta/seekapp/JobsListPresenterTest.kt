package chemilmakhlouta.seekapp

import chemilmakhlouta.seekapp.domain.model.JobObject
import chemilmakhlouta.seekapp.domain.usecase.GetJobsUseCase
import chemilmakhlouta.seekapp.presentation.presenter.JobsListPresenter
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

/**
 * Created by Chemil Makhlouta on 25/7/18.
 */

@RunWith(JUnitPlatform::class)
class JobsListPresenterTest : SubjectSpek<JobsListPresenter>(
        {
            var display: JobsListPresenter.Display = mock()
            var router: JobsListPresenter.Router = mock()

            var getJobsUseCase: GetJobsUseCase

            val mockUrl = "google.com"

            val mockJobsSuccess = Single.just(listOf(JobObject("title", 123, "teaser")))
            var mockJobsResponse = mockJobsSuccess

            subject {
                getJobsUseCase = mock()

                val presenter = JobsListPresenter(getJobsUseCase)

                whenever(getJobsUseCase.getJobs()).doReturn(mockJobsResponse)
                display = mock()
                router = mock()
                presenter.inject(display, router)
                presenter
            }

            given("I am on the jobs list screen") {
                it("Shows the loading indicator while the jobs list is fetched") {
                    subject.onStart()

                    verify(display).showLoading()
                }

                it("hides the loading indicator when the result is returned") {
                    subject.onStart()

                    verify(display).hideLoading()
                }

                on("clicking a job") {
                    it("opens the job item's link in a chrome custom tab") {
                        subject.onJobClicked(mockUrl)

                        verify(router).navigateToJob(mockUrl)
                    }
                }

                on("swiping the list down") {
                    it("refreshes the list") {
                        mockJobsResponse = mockJobsSuccess
                        subject.onSwipeToRefresh()

                        verify(display).setUpJobsList(any())
                    }
                }

                on("getting an error when trying to attain jobs") {
                    it("displays an error message") {
                        mockJobsResponse = Single.error(Throwable())
                        subject.onSwipeToRefresh()

                        verify(display).showError()
                    }
                }
            }

        })