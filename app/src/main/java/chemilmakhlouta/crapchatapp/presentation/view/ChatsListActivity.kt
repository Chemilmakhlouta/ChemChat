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
import chemilmakhlouta.crapchatapp.data.model.ChatResponse
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
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showMessages(chats: ArrayList<ChatResponse>) {
        val adapter = (chatsList.adapter as ChatListAdapter)
        adapter.setchatsList(chats)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToChat(id: Int) =
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.seek.com.au/job/" + id)))

    override fun onchatsItemClicked(id: Int) {
        presenter.onChatClicked(id)
    }
}