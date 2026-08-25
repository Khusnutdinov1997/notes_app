package com.example.notes

import com.example.notes.data.Entity
import com.example.notes.view.entityItemKey
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun entityItemKey_usesStableDatabaseIdWhenPresent() {
        val item = Entity(id = 42, title = "T1", content = "C1")

        val key1 = entityItemKey(item)
        val key2 = entityItemKey(item.copy(title = "Updated", content = "Updated"))

        assertEquals(42, key1)
        assertEquals(42, key2)
    }

    @Test
    fun entityItemKey_usesContentFallbackWhenIdMissing() {
        val item = Entity(id = null, title = "Draft", content = "Body")

        val key = entityItemKey(item)

        assertEquals("draft:Draft:Body", key)
    }
}