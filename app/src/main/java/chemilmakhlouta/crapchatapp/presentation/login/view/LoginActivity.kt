package chemilmakhlouta.crapchatapp.presentation.login.view

import android.os.Bundle
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.presentation.login.presenter.LoginPresenter
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */
class LoginActivity : BaseActivity(), LoginPresenter.Display, LoginPresenter.Router {

    @Inject
    override lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }
}