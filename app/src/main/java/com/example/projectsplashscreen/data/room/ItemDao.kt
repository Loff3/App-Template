package com.example.projectsplashscreen.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAll(): List<ItemEntity>

    @Query("SELECT * FROM item WHERE iid IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): List<ItemEntity>

    @Query("SELECT * FROM item WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): ItemEntity?

    @Insert
    fun insertAll(vararg items: ItemEntity)

    @Delete
    fun delete(item: ItemEntity)}