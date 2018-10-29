package chemilmakhlouta.crapchatapp.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import chemilmakhlouta.crapchatapp.application.injection.component.ActivityComponent
import chemilmakhlouta.crapchatapp.application.injection.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val presenter: Presenter

    // region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(CrappApplication.applicationComponent)
                .build()

        inject(activityComponent)
    }

    protected abstract fun inject(activityComponent: ActivityComponent)

    @CallSuper
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        presenter.onStart()
    }
    // endRegion
}
