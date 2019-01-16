package chemilmakhlouta.crapchatapp.presentation.chats.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.data.chats.model.ChatResponse
import chemilmakhlouta.crapchatapp.presentation.chats.adapter.ChatListAdapter
import chemilmakhlouta.crapchatapp.presentation.chats.presenter.ChatListPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class ChatsListActivity : BaseActivity(), ChatListPresenter.Display, ChatListPresenter.Router {

    companion object {
        private const val INTENT_EXTRA_TO_USER_ID = "intent_extra_to_user_id"

        fun makeIntent(context: Context, toUserId: String): Intent =
                Intent(context, ChatsListActivity::class.java)
                        .putExtra(INTENT_EXTRA_TO_USER_ID, toUserId)
    }

    @Inject
    override lateinit var presenter: ChatListPresenter

    private lateinit var chatAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatList.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatListAdapter(this)
        chatList.adapter = chatAdapter

        intent?.let {
            presenter.onIntentReceived(intent.getStringExtra(INTENT_EXTRA_TO_USER_ID))
        }

        sendChatButton.setOnClickListener { presenter.onSendChatClicked(chatText.text.toString()) }
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showMessages(chats: ArrayList<ChatResponse>) {
        val adapter = (chatList.adapter as ChatListAdapter)
        adapter.setchatsList(chats)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun clearTextAndScroll() {
        chatText.text.clear()
        chatList.scrollToPosition(chatAdapter.itemCount - 1)
    }
}