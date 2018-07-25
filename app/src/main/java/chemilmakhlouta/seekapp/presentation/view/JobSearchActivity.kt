package chemilmakhlouta.seekapp.presentation.view

import android.os.Bundle
import chemilmakhlouta.seekapp.R
import chemilmakhlouta.seekapp.application.BaseActivity
import chemilmakhlouta.seekapp.application.injection.component.ActivityComponent
import chemilmakhlouta.seekapp.presentation.presenter.JobSearchPresenter
import kotlinx.android.synthetic.main.activity_job_search.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class JobSearchActivity : BaseActivity(), JobSearchPresenter.Display, JobSearchPresenter.Router {

    @Inject
    override lateinit var presenter: JobSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_search)

        seekButton.setOnClickListener {
            presenter.onSeekClicked(jobTitle.text.toString(), jobLocation.text.toString())
        }
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun navigateToJobList(keywords: String, location: String) = startActivity(JobsListActivity.makeIntent(this, keywords, location))
}