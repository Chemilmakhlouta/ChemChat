package chemilmakhlouta.crapchatapp.presentation.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.crapchatapp.data.model.ChatResponse
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class ChatListPresenter @Inject constructor() : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private var  chatsList: ArrayList<ChatResponse> = ArrayList<ChatResponse>()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

//    override fun onStart() {
//        getChats()
//    }
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

    override fun onNewMessage(dataSnapshot: DataSnapshot) {
        chatsList.add(ChatResponse(dataSnapshot))
        onModelUpdated(chatsList)
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