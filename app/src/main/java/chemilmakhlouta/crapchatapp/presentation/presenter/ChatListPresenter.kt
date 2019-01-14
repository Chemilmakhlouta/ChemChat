package chemilmakhlouta.crapchatapp.presentation.presenter

import chemilmakhlouta.crapchatapp.application.Presenter
import chemilmakhlouta.crapchatapp.domain.model.ChatObject
import chemilmakhlouta.crapchatapp.domain.usecase.GetJobsUseCase
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class ChatListPresenter @Inject constructor(private val getJobsUseCase: GetJobsUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private lateinit var keywords: String
    private lateinit var location: String

    private var getChatsListObservable: Single<List<ChatObject>>? = null
    private var getChatsListSubscription = Disposables.disposed()

    // region lifecycle
    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    override fun onStart() = getChats()

    override fun onResume() = getChats()

    override fun onPause() = getChatsListSubscription.dispose()

    override fun onStop() {
        getChatsListObservable = null
    }
    // endregion

    // region UI Interactions
    fun onSwipeToRefresh() = getChats()

    fun onChatClicked(id: Int) = router.navigateToChat(id)
    // endregion

    // region Private Functions
    private fun getChats() {
        if (getChatsListObservable == null) {
            getChatsListObservable = getJobsUseCase.getJobs(keywords, location)
                    .doOnSubscribe { display.showLoading() }
                    .doAfterTerminate {
                        display.hideLoading()
                        getChatsListObservable = null
                    }

            subscribeToGetChats()
        }
    }

    private fun subscribeToGetChats() {
        getChatsListObservable?.let {
            getChatsListSubscription = it.subscribe(this::onChatsListSuccess, this::onChatsListFailure)
        }
    }

    private fun onChatsListSuccess(chats: List<ChatObject>) {
        display.setUpChatsList(mutableListOf<ChatObject>().apply {
            addAll(chats)
        })
    }

    private fun onChatsListFailure(throwable: Throwable) {
        display.showError()
    }
    // endregion

    fun onIntentReceived(keywords: String, location: String) {
        this.keywords = keywords
        this.location = location
    }

    interface Display {
        fun showLoading()
        fun hideLoading()
        fun setUpChatsList(chats: MutableList<ChatObject>)
        fun showError()
    }

    interface Router {
        fun navigateToChat(id: Int)
    }
}