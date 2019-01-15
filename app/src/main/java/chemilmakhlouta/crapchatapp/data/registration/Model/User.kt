package chemilmakhlouta.crapchatapp.data.registration.Model

class User(val uid: String, val username: String, val profileImageUrl: String) {
    constructor() : this("", "", "")
}