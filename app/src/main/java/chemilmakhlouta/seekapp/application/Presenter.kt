package chemilmakhlouta.seekapp.application

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

interface Presenter {
    fun onStart() {}
    fun onStop() {}
    fun onResume() {}
    fun onPause() {}
}