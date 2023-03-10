package com.example.astronauts.datalayer.network

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class GetAstronautDetailsMapperTest {

    private val testee = GetAstronautDetailsMapper()

    @Test
    fun testMappingDtoToAstronautDetails(){
        val dto = AstronautDto(1, "name", "US", "image", "bio", "12/12/1999")

       val result = testee.map(dto)

        assertNotNull(result)
        assertEquals(1, result?.id)
        assertEquals("name", result?.name)
        assertEquals("US", result?.nationality)
        assertEquals("image", result?.profileImage)
        assertEquals("bio", result?.bio)
        assertEquals("12/12/1999", result?.dateOfBirth)
    }
}