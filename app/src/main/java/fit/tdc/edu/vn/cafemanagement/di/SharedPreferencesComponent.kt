package fit.tdc.edu.vn.cafemanagement.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import fit.tdc.edu.vn.cafemanagement.data.datasources.SharedPreferencesDataSource
import fit.tdc.edu.vn.cafemanagement.data.datasources.SharedPreferencesDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class SharedPreferencesComponent {
    @Binds
    @Singleton
    abstract fun bindSharedPreferencesDataSource(
        impl: SharedPreferencesDataSourceImpl
    ): SharedPreferencesDataSource
}