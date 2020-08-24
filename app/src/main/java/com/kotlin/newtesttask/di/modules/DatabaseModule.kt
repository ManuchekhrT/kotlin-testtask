package com.kotlin.newtesttask.di.modules

import android.app.Application
import androidx.room.Room
import com.kotlin.newtesttask.data.db.MainDb
import com.kotlin.newtesttask.data.db.UserDao
import com.kotlin.newtesttask.extensions.CONST_DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(mApplication: Application) {

    private val mainDb: MainDb = Room.databaseBuilder(
        mApplication,
        MainDb::class.java,
        CONST_DB_NAME
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()


    @Singleton
    @Provides
    internal fun providesRoomDatabase(): MainDb {
        return mainDb
    }

    @Singleton
    @Provides
    internal fun providesUserDao(mainDb: MainDb): UserDao {
        return mainDb.userDao()
    }

}