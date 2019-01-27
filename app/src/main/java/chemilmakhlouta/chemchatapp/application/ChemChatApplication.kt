package chemilmakhlouta.chemchatapp.application

import android.support.multidex.MultiDexApplication
import chemilmakhlouta.chemchatapp.application.injection.component.ApplicationComponent
import chemilmakhlouta.chemchatapp.application.injection.component.DaggerApplicationComponent
import chemilmakhlouta.chemchatapp.application.injection.module.ApplicationModule
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by Chemil Makhlouta on 25/6/18.
 */
class ChemChatApplication : MultiDexApplication() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)

        Fresco.initialize(this);
    }
}