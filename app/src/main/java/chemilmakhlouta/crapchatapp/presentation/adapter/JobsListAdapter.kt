package chemilmakhlouta.crapchatapp.presentation.adapter

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.domain.model.JobObject
import kotlinx.android.synthetic.main.item_job.view.*

/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class JobsListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobsList: List<JobObject> = emptyList()

    private lateinit var listItemClickListener: OnJobsListItemClickListener

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val jobsItem = jobsList[position]
        with(holder.itemView) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                title.text = Html.fromHtml(jobsItem.title, Html.FROM_HTML_MODE_LEGACY)
                teaser.text = Html.fromHtml(jobsItem.teaser, Html.FROM_HTML_MODE_LEGACY)
            } else {
                title.text = Html.fromHtml(jobsItem.title)
                teaser.text = Html.fromHtml(jobsItem.teaser)
            }

            setOnClickListener { listItemClickListener.onJobsItemClicked(jobsItem.id) }
        }
    }

    override fun getItemCount(): Int = jobsList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return JobListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_job, parent, false))
    }

    fun setJobsListItemClickListener(listener: OnJobsListItemClickListener) {
        listItemClickListener = listener
    }

    private class JobListItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnJobsListItemClickListener {
        fun onJobsItemClicked(id: Int)
    }

    fun setJobsList(jobs: MutableList<JobObject>) {
        jobsList = jobs
        notifyDataSetChanged()
    }
}