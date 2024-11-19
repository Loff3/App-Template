package com.example.projectsplashscreen.data.room
import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        // Return the existing instance if it exists
        return INSTANCE ?: synchronized(this) {
            // Create a new instance if it doesn't exist
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "item_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
