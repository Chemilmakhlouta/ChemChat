package chemilmakhlouta.seekapp

import chemilmakhlouta.seekapp.presentation.presenter.JobSearchPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
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
class JobSearchPresenterTest : SubjectSpek<JobSearchPresenter>(
        {
            var display: JobSearchPresenter.Display = mock()
            var router: JobSearchPresenter.Router = mock()

            subject {
                val presenter = JobSearchPresenter()
                display = mock()
                router = mock()
                presenter.inject(display, router)
                presenter
            }

            given("I am on the jobs search screen") {
                on("clicking the seek button") {
                    it("navigates to the job list screen") {
                        subject.onSeekClicked("","")

                        verify(router).navigateToJobList("","")
                    }
                }
            }
        })