package chemilmakhlouta.chemchatapp.data.registration.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class User(val uid: String, val username: String, val profileImageUrl: String): Serializable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor() : this("", "", "")

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}