package chemilmakhlouta.crapchatapp.presentation.chats.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.crapchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.crapchatapp.data.chats.model.ChatManager
import chemilmakhlouta.crapchatapp.data.chats.model.ChatResponse
import chemilmakhlouta.crapchatapp.domain.chats.usecase.SendChatUseCase
import com.google.firebase.database.DataSnapshot
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class ChatListPresenter @Inject constructor(private val sendChatUseCase: SendChatUseCase) : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private lateinit var toUserId: String

    private var chatsList: ArrayList<ChatResponse> = ArrayList<ChatResponse>()

    private var sendChatObservable: Completable? = null
    private var sendChatSubscription = Disposables.disposed()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() {
        setChatListener()
    }

//    override fun onResume() = getChats()
//    override fun onPause() = getChatsListSubscription.dispose()

    override fun onStop() {
        removeChatListener()
    }
    // endregion

    // region Private functions
    override fun onNewMessage(dataSnapshot: DataSnapshot) {
        chatsList.add(ChatResponse(dataSnapshot))
        onModelUpdated(chatsList)
    }

    override fun onModelUpdated(messages: ArrayList<ChatResponse>) {
        if (messages.isNotEmpty()) {
            display.showMessages(messages)
        }
    }

    private fun setChatListener() =
            ChatManager.getInstance(toUserId, this)!!.addMessageListeners()


    private fun sendChat(message: String) {
        if (sendChatObservable == null) {
            sendChatObservable = sendChatUseCase.sendChat(message, toUserId)
                    .doAfterTerminate {
                        sendChatObservable = null
                    }

            subscribeToSendChat()
        }
    }

    private fun subscribeToSendChat() {
        sendChatObservable?.let {
            sendChatSubscription = it.subscribe(this::onSendChatSuccess, this::onSendChatFailure)
        }
    }

    private fun onSendChatSuccess() {
        display.clearTextAndScroll()
    }

    private fun onSendChatFailure(throwable: Throwable) {
        display.showError(throwable.message!!)
    }
    // endregion

    // region public functions
    fun removeChatListener() {
        ChatManager.getInstance(toUserId, this)!!.removeListener()
        ChatManager.getInstance(toUserId, this)!!.destroy()
    }

    fun onIntentReceived(toUserId: String) {
        this.toUserId = toUserId
    }

    fun onSendChatClicked(message: String) {
        if (message.isEmpty()) {
            display.showError("Enter some text!")
        } else {
            sendChat(message)
        }
    }
    // endRegion

    interface Display {
        fun showMessages(chats: ArrayList<ChatResponse>)
        fun showError(message: String)

        fun clearTextAndScroll()
    }

    interface Router {
    }
}