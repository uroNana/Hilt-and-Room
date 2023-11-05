package com.example.networkcalls

import com.example.networkcalls.network.Data
import com.example.networkcalls.repository.entity.RepoEntity
import com.example.networkcalls.repository.entity.toDomain
import com.example.networkcalls.repository.entity.toEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test


class RepoEntityTest {

    @Test
    fun `toDomain should return the correct Data object`() {
        // Given a RepoEntity
        val repoEntity = RepoEntity(id = 1, joke = "Funny joke")

        // When we call toDomain() on the RepoEntity
        val data = repoEntity.toDomain()

        // Then it should return the correct Data object
        assertEquals(1, data.id)
        assertEquals("Funny joke", data.joke)
    }

    @Test
    fun `toEntity should return the correct RepoEntity object`() {
        // Given a Data object
        val data = Data(id = 2, joke = "Another funny joke")

        // When we call toEntity() on the Data object
        val repoEntity = data.toEntity()

        // Then it should return the correct RepoEntity object
        assertEquals(2, repoEntity.id)
        assertEquals("Another funny joke", repoEntity.joke)
    }
}



