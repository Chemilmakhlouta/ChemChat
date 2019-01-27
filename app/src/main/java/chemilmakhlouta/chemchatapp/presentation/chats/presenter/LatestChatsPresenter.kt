package chemilmakhlouta.chemchatapp.presentation.chats.presenter

import chemilmakhlouta.chemchatapp.application.Presenter
import chemilmakhlouta.chemchatapp.application.callbackinterfaces.FirebaseCallBack
import chemilmakhlouta.chemchatapp.application.callbackinterfaces.ModelCallBack
import chemilmakhlouta.chemchatapp.data.chats.model.ChatResponse
import chemilmakhlouta.chemchatapp.data.chats.model.LatestChatsManager
import chemilmakhlouta.chemchatapp.data.registration.Model.User
import chemilmakhlouta.chemchatapp.domain.chats.usecase.GetSpecificUserUseCase
import chemilmakhlouta.chemchatapp.domain.chats.usecase.SetUserProfileImageUseCase
import com.google.firebase.database.DataSnapshot
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class LatestChatsPresenter @Inject constructor(private val getSpecificUserUseCase: GetSpecificUserUseCase,
                                               private val setUserProfileImageUseCase: SetUserProfileImageUseCase) : Presenter, FirebaseCallBack, ModelCallBack {

    private lateinit var display: Display
    private lateinit var router: Router

    private var getUserObservable: Single<User>? = null
    private var getUserSubscription = Disposables.disposed()

    private val messagesMap = HashMap<String, ChatResponse>()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() {
        setChatsListener()
        getCurrentUser()
    }

//    override fun onResume() = getChats()
//    override fun onPause() = getChatsListSubscription.dispose()

    override fun onStop() = removeChatListener()
    // endregion

    // region UI Interactions
    fun onChatClicked(id: String?) = id?.let {
        router.navigateToChat(id)
    } ?: display.showError()
    // endregion

    private fun setChatsListener() = LatestChatsManager.getInstance(this)!!.addMessageListeners()

    private fun removeChatListener() {
        LatestChatsManager.getInstance(this)!!.removeListener()
        LatestChatsManager.getInstance(this)!!.destroy()
    }

    private fun getCurrentUser() {
        if (getUserObservable == null) {
            getUserObservable = getSpecificUserUseCase.getUser()
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        display.hideLoading()
                        getUserObservable = null
                    }

            subscribeToGetUser()
        }
    }

    private fun subscribeToGetUser() {
        getUserObservable?.let {
            getUserSubscription = it.subscribe(this::onGetUserSuccess, this::onGetUserFailure)
        }
    }

    private fun onGetUserSuccess(user: User) = setUserProfileImageUseCase.setUserProfileImage(user)

    private fun onGetUserFailure(throwable: Throwable) = display.showError()

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

    fun onNewMessageClicked() {
        router.navigateToSelectUser()
    }

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun showMessages(chats: ArrayList<ChatResponse>)
        fun showError()
    }

    interface Router {
        fun navigateToChat(id: String)
        fun navigateToSelectUser()
    }
}