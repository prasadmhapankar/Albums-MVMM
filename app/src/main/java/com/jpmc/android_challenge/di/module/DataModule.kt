package com.jpmc.android_challenge.di.module

import android.content.Context
import androidx.room.Room
import com.jpmc.android_challenge.api.ApiService
import com.jpmc.android_challenge.data.AlbumsRepository
import com.jpmc.android_challenge.data.AlbumsRepositoryImpl
import com.jpmc.android_challenge.data.source.AlbumsLocalDataSource
import com.jpmc.android_challenge.data.source.AlbumsRemoteDataSource
import com.jpmc.android_challenge.db.AppDatabase
import com.jpmc.android_challenge.db.dao.AlbumDao
import com.jpmc.android_challenge.mapper.AlbumsMapper
import com.jpmc.android_challenge.mapper.AlbumsMapperImpl
import com.jpmc.android_challenge.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun providesRepository(networkHelper: NetworkHelper, remoteDataSource : AlbumsRemoteDataSource,
                           localDataSource: AlbumsLocalDataSource) : AlbumsRepository = AlbumsRepositoryImpl(networkHelper, remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun providesAlbumsRemoteDataSource(apiService: ApiService) : AlbumsRemoteDataSource = AlbumsRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesAlbumsLocalDataSource(appDatabase: AppDatabase,
                                      mapper: AlbumsMapper) : AlbumsLocalDataSource = AlbumsLocalDataSource(appDatabase, mapper)

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "albums-database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesAlbumsDao(appDatabase: AppDatabase) : AlbumDao = appDatabase.albumDao()

    @Provides
    @Singleton
    fun providesAlbumsMapper() : AlbumsMapper = AlbumsMapperImpl()
}