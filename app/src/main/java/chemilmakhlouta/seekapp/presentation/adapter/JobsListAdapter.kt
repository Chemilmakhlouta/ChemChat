package chemilmakhlouta.seekapp.presentation.adapter

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.seekapp.R
import chemilmakhlouta.seekapp.domain.model.JobObject
import kotlinx.android.synthetic.main.item_job.view.*

/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class JobsListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobsList: List<JobObject> = emptyList()

    private lateinit var listItemClickListener: OnJobsListItemClickListener
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val jobsItem = jobsList[position]
        with(holder.itemView) {
            jobTitle.text = jobsItem.title
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