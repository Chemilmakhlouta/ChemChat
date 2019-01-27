package chemilmakhlouta.chemchatapp.data.chats.model

class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val timestamp: Long, val profileImage: String) {
    constructor() : this("", "", "", "", -1, "")
}