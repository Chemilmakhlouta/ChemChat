package chemilmakhlouta.crapchatapp.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.convertTime
import chemilmakhlouta.crapchatapp.data.model.ChatResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.item_chat.view.*

/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class ChatListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chatsList: List<ChatResponse> = emptyList()

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatItem = chatsList[position]
        with(holder.itemView) {

            if (chatItem.senderId.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                layout_message_left.visibility = View.GONE
                layout_message_right.visibility = View.VISIBLE
                text_message_right.text = chatItem.message
                text_time_messages_right.text = convertTime(chatItem.timeStamp)
            } else {
                layout_message_left.visibility = View.VISIBLE
                layout_message_right.visibility = View.GONE
                text_message_left.text = chatItem.message
                text_time_messages_left.text = convertTime(chatItem.timeStamp)
            }
        }
    }

    override fun getItemCount(): Int = chatsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return chatListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
    }

    private class chatListItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun setchatsList(chats: MutableList<ChatResponse>) {
        chatsList = chats
        notifyDataSetChanged()
    }
}