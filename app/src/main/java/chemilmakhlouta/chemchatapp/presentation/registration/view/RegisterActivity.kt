package chemilmakhlouta.chemchatapp.presentation.registration.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import chemilmakhlouta.chemchatapp.R
import chemilmakhlouta.chemchatapp.application.BaseActivity
import chemilmakhlouta.chemchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.chemchatapp.presentation.login.view.LoginActivity
import chemilmakhlouta.chemchatapp.presentation.registration.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject
import android.view.View

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */
class RegisterActivity : BaseActivity(), RegisterPresenter.Display, RegisterPresenter.Router {

    @Inject
    override lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener { presenter.onRegisterClicked(registerEmail.text.toString(), registerUsername.text.toString(), registerPassword.text.toString()) }
        alreadyRegisteredButton.setOnClickListener { presenter.onAlreadyRegisteredClicked() }
        selectPhotoButton.setOnClickListener { presenter.onProfileImageClicked() }
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            presenter.onPhotoSelected(data.data)
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun navigateToLogin() = startActivity(Intent(LoginActivity.makeIntent(this)))

    override fun navigateToImageSelection() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun showSelectedImage(selectedPhotoUri: Uri) {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
        profileImage.setImageBitmap(bitmap)

        selectPhotoButton.alpha = 0f
    }
}