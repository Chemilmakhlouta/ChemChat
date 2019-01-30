package chemilmakhlouta.chemchatapp.presentation.chats.presenter

import android.net.Uri
import chemilmakhlouta.chemchatapp.application.Presenter
import chemilmakhlouta.chemchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.chemchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.chemchatapp.data.chats.model.ChatManager
import chemilmakhlouta.chemchatapp.data.chats.model.ChatResponse
import chemilmakhlouta.chemchatapp.domain.chats.usecase.SendChatUseCase
import chemilmakhlouta.chemchatapp.domain.registration.usecase.UploadImageUseCase
import com.google.firebase.database.DataSnapshot
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
class ChatListPresenter @Inject constructor(private val sendChatUseCase: SendChatUseCase,
                                            private val uploadImageUseCase: UploadImageUseCase) : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private lateinit var toUserId: String

    private var chatsList: ArrayList<ChatResponse> = ArrayList<ChatResponse>()
    private lateinit var selectedImageUri: Uri

    private var sendChatObservable: Completable? = null
    private var sendChatSubscription = Disposables.disposed()

    private var uploadImageObservable: Single<String>? = null
    private var uploadImageSubscription = Disposables.disposed()

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

    private fun sendImage() {
        if (uploadImageObservable == null) {
            uploadImageObservable = uploadImageUseCase.uploadImage(selectedImageUri)
                    .doAfterTerminate {
                        uploadImageObservable = null
                    }

            subscribeToUploadImage()
        }
    }

    private fun subscribeToSendChat() {
        sendChatObservable?.let {
            sendChatSubscription = it.subscribe(this::onSendChatSuccess, this::onSendChatFailure)
        }
    }

    private fun subscribeToUploadImage() {
        uploadImageObservable?.let {
            uploadImageSubscription = it.subscribe(this::onUploadImageSuccess, this::onUploadImageFailure)
        }
    }

    private fun onSendChatSuccess() {
        display.clearTextAndScroll()
    }

    private fun onSendChatFailure(throwable: Throwable) {
        display.showError(throwable.message!!)
    }

    private fun onUploadImageSuccess(imageUrl: String) {
        display.clearTextAndScroll()
    }

    private fun onUploadImageFailure(throwable: Throwable) {
        display.showError(throwable.message!!)
    }

    private fun removeChatListener() {
        ChatManager.getInstance(toUserId, this)!!.removeListener()
        ChatManager.getInstance(toUserId, this)!!.destroy()
    }
    // endregion

    // region public functions
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

    fun onPhotoSelected(selectedImageUri: Uri) {
        this.selectedImageUri = selectedImageUri

        sendImage()
        display.showSelectedImage(selectedImageUri)
    }

    fun onAddPhotoClicked() = router.navigateToImageSelection()
    // endRegion

    interface Display {
        fun showMessages(chats: ArrayList<ChatResponse>)
        fun showError(message: String)

        fun clearTextAndScroll()
        fun showSelectedImage(selectedImageUri: Uri)
    }

    interface Router {
        fun navigateToImageSelection()
    }
}