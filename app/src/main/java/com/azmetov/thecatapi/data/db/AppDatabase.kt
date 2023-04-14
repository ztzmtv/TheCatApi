package com.azmetov.thecatapi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azmetov.thecatapi.data.db.favorites.FavoriteDbModel
import com.azmetov.thecatapi.data.db.favorites.FavoritesDao
import com.azmetov.thecatapi.data.db.images.ImageDbModel
import com.azmetov.thecatapi.data.db.images.ImagesDao


@Database(
    entities = [
        ImageDbModel::class,
        FavoriteDbModel::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun imagesDao(): ImagesDao
    abstract fun favoritesDao(): FavoritesDao
}