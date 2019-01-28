package chemilmakhlouta.chemchatapp.presentation.chats.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chemilmakhlouta.chemchatapp.R
import chemilmakhlouta.chemchatapp.data.registration.Model.User
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by Chemil Makhlouta on 24/6/18.
 */
class RecipientAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var usersList: List<User> = emptyList()

    private lateinit var listItemClickListener: OnUserItemClickedListener
    private val roundingParams = RoundingParams.fromCornersRadius(7f).setRoundAsCircle(true)

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userItem = usersList[position]
        with(holder.itemView) {
            username.text = userItem.username

            profilePicture.hierarchy = GenericDraweeHierarchyBuilder(resources)
                    .setRoundingParams(roundingParams)
                    .build()
            profilePicture.setImageURI(userItem.profileImageUrl)

            setOnClickListener { listItemClickListener.onUserItemClicked(userItem.uid) }
        }
    }

    override fun getItemCount(): Int = usersList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChatListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    fun setchatsListItemClickListener(listener: OnUserItemClickedListener) {
        listItemClickListener = listener
    }

    private class ChatListItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnUserItemClickedListener {
        fun onUserItemClicked(userId: String)
    }

    fun setUsersList(users: MutableList<User>) {
        usersList = users
        notifyDataSetChanged()
    }
}