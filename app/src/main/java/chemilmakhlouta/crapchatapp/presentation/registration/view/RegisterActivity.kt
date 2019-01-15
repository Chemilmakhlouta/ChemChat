package chemilmakhlouta.crapchatapp.presentation.registration.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.presentation.login.view.LoginActivity
import chemilmakhlouta.crapchatapp.presentation.registration.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

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

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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