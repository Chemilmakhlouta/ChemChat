package chemilmakhlouta.chemchatapp.application.callbackinterfaces

import chemilmakhlouta.chemchatapp.data.chats.model.ChatResponse
import java.util.ArrayList

interface ModelCallBack {
    fun onModelUpdated(messages: ArrayList<ChatResponse>)
}