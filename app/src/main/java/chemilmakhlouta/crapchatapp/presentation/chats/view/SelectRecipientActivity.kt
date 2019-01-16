package chemilmakhlouta.crapchatapp.presentation.chats.view

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.data.registration.Model.User
import chemilmakhlouta.crapchatapp.presentation.chats.adapter.LatestChatsAdapter
import chemilmakhlouta.crapchatapp.presentation.chats.adapter.RecipientAdapter
import chemilmakhlouta.crapchatapp.presentation.chats.presenter.SelectRecipientPresenter
import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class SelectRecipientActivity : BaseActivity(), SelectRecipientPresenter.Display, SelectRecipientPresenter.Router, RecipientAdapter.onUserItemClickedListener {

    companion object {
        fun makeIntent(context: Context): Intent =
                Intent(context, SelectRecipientActivity::class.java)
    }

    @Inject
    override lateinit var presenter: SelectRecipientPresenter

    private lateinit var usersAdapter: RecipientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        usersListLayout.layoutManager = LinearLayoutManager(this)
        usersAdapter = RecipientAdapter(this)
        usersAdapter.setchatsListItemClickListener(this)
        usersListLayout.adapter = usersAdapter
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun showUsers(usersList: ArrayList<User>) {
        val adapter = (usersListLayout.adapter as RecipientAdapter)
        adapter.setUsersList(usersList)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUserItemClicked(userId: String) {
        startActivity(Intent(ChatsListActivity.makeIntent(this, userId)))
    }
}