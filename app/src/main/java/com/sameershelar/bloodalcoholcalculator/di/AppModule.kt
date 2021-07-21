package com.sameershelar.bloodalcoholcalculator.di

import android.app.Application
import androidx.room.Room
import com.sameershelar.bloodalcoholcalculator.data.BACDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope

    @Provides
    @Singleton
    @ApplicationScope
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: BACDatabase.Callback
    ) = Room.databaseBuilder(app, BACDatabase::class.java, "bac_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun providesDrinkDao(db: BACDatabase) = db.drinkDao()
}