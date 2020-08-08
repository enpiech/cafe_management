package fit.tdc.edu.vn.cafemanagement.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import fit.tdc.edu.vn.cafemanagement.extensions.SharedPreferencesExtensions
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CommonComponent {
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        SharedPreferencesExtensions.defaultPreference(context)
}