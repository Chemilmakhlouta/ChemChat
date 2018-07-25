package chemilmakhlouta.seekapp.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import chemilmakhlouta.seekapp.application.injection.component.ActivityComponent
import chemilmakhlouta.seekapp.application.injection.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val presenter: Presenter

    // region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(JobsApplication.applicationComponent)
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
