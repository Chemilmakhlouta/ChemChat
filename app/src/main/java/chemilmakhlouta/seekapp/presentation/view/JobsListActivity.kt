package chemilmakhlouta.seekapp.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import chemilmakhlouta.seekapp.R
import chemilmakhlouta.seekapp.application.BaseActivity
import chemilmakhlouta.seekapp.application.injection.component.ActivityComponent
import chemilmakhlouta.seekapp.domain.model.JobObject
import chemilmakhlouta.seekapp.presentation.adapter.JobsListAdapter
import chemilmakhlouta.seekapp.presentation.presenter.JobsListPresenter
import kotlinx.android.synthetic.main.activity_job_list.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class JobsListActivity : BaseActivity(), JobsListPresenter.Display, JobsListPresenter.Router, JobsListAdapter.OnJobsListItemClickListener {

    companion object {
        private const val INTENT_EXTRA_JOB_KEYWORDS = "intent_extra_job_keywords"
        private const val INTENT_EXTRA_JOB_LOCATION = "intent_extra_job_location"

        fun makeIntent(context: Context, keywords: String, location: String): Intent =
                Intent(context, JobsListActivity::class.java)
                        .putExtra(INTENT_EXTRA_JOB_KEYWORDS, keywords)
                        .putExtra(INTENT_EXTRA_JOB_LOCATION, location)
    }

    @Inject
    override lateinit var presenter: JobsListPresenter

    private lateinit var jobsAdapter: JobsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobsList.layoutManager = LinearLayoutManager(this)
        jobsAdapter = JobsListAdapter(this)
        jobsAdapter.setJobsListItemClickListener(this)
        jobsList.adapter = jobsAdapter

        intent?.let {
            presenter.onIntentReceived(intent.getStringExtra(INTENT_EXTRA_JOB_KEYWORDS),
                                       intent.getStringExtra(INTENT_EXTRA_JOB_LOCATION))
        }

        swipeContainer.setOnRefreshListener { presenter.onSwipeToRefresh() }
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showLoading() {
        swipeContainer.isRefreshing = true
    }

    override fun hideLoading() {
        swipeContainer.isRefreshing = false
    }

    override fun setUpJobsList(jobs: MutableList<JobObject>) {
        val adapter = (jobsList.adapter as JobsListAdapter)
        adapter.setJobsList(jobs)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun navigateToJob(id: Int) =
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.seek.com.au/job/" + id)))

    override fun onJobsItemClicked(id: Int) {
        presenter.onJobClicked(id)
    }
}