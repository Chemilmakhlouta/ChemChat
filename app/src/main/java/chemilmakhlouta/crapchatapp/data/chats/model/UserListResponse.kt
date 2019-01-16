package chemilmakhlouta.crapchatapp.data.chats.model

import chemilmakhlouta.crapchatapp.data.registration.Model.User
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList

fun mapUsers(dataSnapshot: DataSnapshot): ArrayList<User> {
    var userList: ArrayList<User> = ArrayList()
    dataSnapshot.children.forEach {
        val uid = it.child("uid").value.toString()
        val username = it.child("username").value.toString()
        val profileImageUrl = it.child("profileImageUrl").value.toString()

        userList.add(User(uid, username, profileImageUrl))
    }

    return userList
}
