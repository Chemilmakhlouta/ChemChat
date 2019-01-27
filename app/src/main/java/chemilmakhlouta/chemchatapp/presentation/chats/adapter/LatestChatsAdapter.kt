package chemilmakhlouta.chemchatapp.presentation.chats.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.chemchatapp.R
import chemilmakhlouta.chemchatapp.data.chats.model.ChatResponse
import kotlinx.android.synthetic.main.item_latest_chat.view.*
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams


/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class LatestChatsAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chatsList: List<ChatResponse> = emptyList()

    private lateinit var listItemClickListener: OnchatsListItemClickListener

    private val roundingParams = RoundingParams.fromCornersRadius(7f).setRoundAsCircle(true)

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val latestChatItem = chatsList[position]
        with(holder.itemView) {
            latestMessageDetail.text = latestChatItem.message
            setOnClickListener { listItemClickListener.onchatsItemClicked(latestChatItem.fromId) }

            profilePicture.hierarchy = GenericDraweeHierarchyBuilder(resources)
                    .setRoundingParams(roundingParams)
                    .build()
            profilePicture.setImageURI(latestChatItem.profileImage)

        }
    }

    override fun getItemCount(): Int = chatsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return chatListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_latest_chat, parent, false))
    }

    fun setchatsListItemClickListener(listener: OnchatsListItemClickListener) {
        listItemClickListener = listener
    }

    private class chatListItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnchatsListItemClickListener {
        fun onchatsItemClicked(id: String?)
    }

    fun setchatsList(chats: MutableList<ChatResponse>) {
        chatsList = chats
        notifyDataSetChanged()
    }
}