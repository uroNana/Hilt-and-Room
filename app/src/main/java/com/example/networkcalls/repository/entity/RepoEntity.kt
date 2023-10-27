package com.example.networkcalls.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.networkcalls.network.Data


@Entity(tableName = "repo")

data class RepoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "joke")
    val joke: String?)
    fun RepoEntity.toDomain(): Data {
        return Data(id = this.id, joke = this.joke)
    }
    fun Data.toEntity(): RepoEntity {
        return RepoEntity(id = this.id, joke = this.joke)
    }
