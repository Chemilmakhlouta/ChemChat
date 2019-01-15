package chemilmakhlouta.crapchatapp.presentation.chats.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.crapchatapp.data.chats.model.ChatResponse
import chemilmakhlouta.crapchatapp.data.chats.model.LatestChatsManager
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class LatestChatsPresenter @Inject constructor() : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private val messagesMap = HashMap<String, ChatResponse>()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() {
        setChatsListener()
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

    // region UI Interactions
    fun onChatClicked(id: Int) = router.navigateToChat(id)
    // endregion

    private fun setChatsListener() {
        LatestChatsManager.getInstance("",this)!!.addMessageListeners()
    }

    override fun onNewMessage(dataSnapshot: DataSnapshot) {
        val chatMessage = ChatResponse(dataSnapshot)
        messagesMap[dataSnapshot.key!!] = chatMessage
        onModelUpdated(ArrayList(messagesMap.values))
    }

    override fun onModelUpdated(messages: ArrayList<ChatResponse>) {
        if (messages.isNotEmpty()) {
            display.showMessages(messages)
        }
    }

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun showMessages(chats: ArrayList<ChatResponse>)
        fun showError()
    }

    interface Router {
        fun navigateToChat(id: Int)
    }
}