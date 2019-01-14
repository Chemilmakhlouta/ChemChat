package chemilmakhlouta.crapchatapp.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.domain.model.ChatObject

/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class ChatListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chatsList: List<ChatObject> = emptyList()

    private lateinit var listItemClickListener: OnchatsListItemClickListener

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatsItem = chatsList[position]
        with(holder.itemView) {

            setOnClickListener { listItemClickListener.onchatsItemClicked(chatsItem.id) }
        }
    }

    override fun getItemCount(): Int = chatsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return chatListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
    }

    fun setchatsListItemClickListener(listener: OnchatsListItemClickListener) {
        listItemClickListener = listener
    }

    private class chatListItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnchatsListItemClickListener {
        fun onchatsItemClicked(id: Int)
    }

    fun setchatsList(chats: MutableList<ChatObject>) {
        chatsList = chats
        notifyDataSetChanged()
    }
}