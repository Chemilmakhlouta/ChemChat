package chemilmakhlouta.seekapp.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chemilmakhlouta.seekapp.R

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class JobsListActivity : AppCompatActivity() {

    companion object {
        private const val INTENT_EXTRA_JOB_KEYWORDS = "intent_extra_job_keywords"
        private const val INTENT_EXTRA_JOB_LOCATION = "intent_extra_job_location"

        fun makeIntent(context: Context, keywords: String, location: String): Intent =
                Intent(context, JobsListActivity::class.java)
                        .putExtra(INTENT_EXTRA_JOB_KEYWORDS, keywords)
                        .putExtra(INTENT_EXTRA_JOB_LOCATION, location)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)
    }
}