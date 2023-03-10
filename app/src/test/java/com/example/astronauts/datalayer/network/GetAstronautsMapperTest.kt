package com.example.astronauts.datalayer.network

import junit.framework.TestCase.*
import org.junit.Test

class GetAstronautsMapperTest {

    private val testee = GetAstronautsMapper()

    @Test
    fun testMappingDtoToAstronautDetails() {
        val dto = AstronautsDto(listOf(AstronautDto(1, "name", "US", "image", "bio", "12/12/1999")))

        val result = testee.map(dto)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("name", result[0].name)
        assertEquals("US", result[0].nationality)
        assertEquals("image", result[0].profileImage)
        assertNull(result[0].bio)
        assertNull(result[0].dateOfBirth)
    }
}