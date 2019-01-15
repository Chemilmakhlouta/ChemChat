package chemilmakhlouta.crapchatapp.application.callbackinterfaces

import chemilmakhlouta.crapchatapp.data.chats.model.ChatResponse
import java.util.ArrayList

interface ModelCallBack {
    fun onModelUpdated(messages: ArrayList<ChatResponse>)
}