package chemilmakhlouta.crapchatapp.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.domain.model.ChatObject
import chemilmakhlouta.crapchatapp.presentation.adapter.ChatListAdapter
import chemilmakhlouta.crapchatapp.presentation.presenter.ChatListPresenter
import kotlinx.android.synthetic.main.activity_chat_list.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class ChatsListActivity : BaseActivity(), ChatListPresenter.Display, ChatListPresenter.Router, ChatListAdapter.OnchatsListItemClickListener {

    companion object {
        fun makeIntent(context: Context, keywords: String, location: String): Intent =
                Intent(context, ChatsListActivity::class.java)
    }

    @Inject
    override lateinit var presenter: ChatListPresenter

    private lateinit var chatAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        chatsList.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatListAdapter(this)
        chatAdapter.setchatsListItemClickListener(this)
        chatsList.adapter = chatAdapter

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

    override fun setUpChatsList(chats: MutableList<ChatObject>) {
        val adapter = (chatsList.adapter as ChatListAdapter)
        adapter.setchatsList(chats)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun navigateToChat(id: Int) =
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.seek.com.au/job/" + id)))

    override fun onchatsItemClicked(id: Int) {
        presenter.onChatClicked(id)
    }
}