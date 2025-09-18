package com.mj.appmvi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null
)