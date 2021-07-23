package com.jpmc.android_challenge.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.jpmc.android_challenge.db.dao.AlbumDao
import com.jpmc.android_challenge.db.entity.AlbumEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {

    private lateinit var db: AppDatabase

    private lateinit var dao: AlbumDao

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule() //needed to test code with LiveData


    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries().build()
        dao = db.albumDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

     @Test
    fun insertAlbum_withEntity_returnTrue() = runBlocking {
            dao.insertAll(listOf(AlbumEntity(1, 12, "My album")))
            val albums = dao.getAll()
            assertThat(albums.contains(AlbumEntity(1, 12, "My album"))).isTrue()
        }

}