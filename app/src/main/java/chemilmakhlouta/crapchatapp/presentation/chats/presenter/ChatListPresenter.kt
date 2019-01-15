package chemilmakhlouta.crapchatapp.presentation.chats.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.crapchatapp.data.chats.model.ChatManager
import chemilmakhlouta.crapchatapp.data.chats.model.ChatResponse
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class ChatListPresenter @Inject constructor() : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private lateinit var toUserId: String

    private var chatsList: ArrayList<ChatResponse> = ArrayList<ChatResponse>()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() {
        setChatListener()
    }
//
//    override fun onResume() = getChats()
//
//    override fun onPause() = getChatsListSubscription.dispose()
//
//    override fun onStop() {
//        getChatsListObservable = null
//    }
    // endregion

    override fun onNewMessage(dataSnapshot: DataSnapshot) {
        chatsList.add(ChatResponse(dataSnapshot))
        onModelUpdated(chatsList)
    }

    override fun onModelUpdated(messages: ArrayList<ChatResponse>) {
        if (messages.isNotEmpty()) {
            display.showMessages(messages)
        }
    }

    private fun setChatListener() {
        ChatManager.getInstance(toUserId, this)!!.addMessageListeners()
    }

    // public functions
    fun onIntentReceived(toUserId: String) {
        this.toUserId = toUserId
    }
    // endRegion

    interface Display {
        fun showMessages(chats: ArrayList<ChatResponse>)
        fun showError()
    }

    interface Router {
    }
}