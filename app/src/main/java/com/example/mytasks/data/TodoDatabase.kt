package com.example.mytasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TodoEntity::class],
    version = 1,
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val dao: TodoDao
}

object TodoDatabaseProvider {

    @Volatile
    private var INSTANCE: TodoDatabase? = null

    fun provide(context: Context): TodoDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "MyTasks-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}