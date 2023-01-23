package com.example.flows.di

import android.content.Context
import androidx.room.Room
import com.example.p6_flows.data.common.DataConstants
import com.example.p6_flows.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.p6_flows.data.database.local.MovieDB
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDB {
        return Room.databaseBuilder(
            appContext,
            MovieDB::class.java,
            DataConstants.MY_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: MovieDB): MovieDao {
        return appDatabase.movieDao()
    }
}