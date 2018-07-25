package chemilmakhlouta.seekapp.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chemilmakhlouta.seekapp.R
import chemilmakhlouta.seekapp.presentation.presenter.JobSearchPresenter
import kotlinx.android.synthetic.main.activity_job_search.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class JobSearchActivity : AppCompatActivity(), JobSearchPresenter.Display, JobSearchPresenter.Router {

    @Inject
    lateinit var presenter: JobSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_search)

        seekButton.setOnClickListener {
            presenter.onSeekClicked(jobTitle.text.toString(), jobLocation.text.toString())
        }
    }

    override fun navigateToJobList(keywords: String, location: String) = startActivity(JobsListActivity.makeIntent(this, keywords, location))
}