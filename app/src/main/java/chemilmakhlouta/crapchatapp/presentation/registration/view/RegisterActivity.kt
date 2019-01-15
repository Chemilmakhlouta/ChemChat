package chemilmakhlouta.crapchatapp.presentation.registration.view

import android.os.Bundle
import android.widget.Toast
import chemilmakhlouta.crapchatapp.R
import chemilmakhlouta.crapchatapp.application.BaseActivity
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.presentation.registration.presenter.RegisterPresenter
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 15/01/19.
 */
class RegisterActivity : BaseActivity(), RegisterPresenter.Display, RegisterPresenter.Router {

    @Inject
    override lateinit var presenter: RegisterPresenter

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