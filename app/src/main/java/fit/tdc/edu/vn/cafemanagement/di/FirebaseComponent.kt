package fit.tdc.edu.vn.cafemanagement.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FirebaseComponent {

    @Provides
    @Singleton
    fun provideFireBaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireBaseFireStore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()
}