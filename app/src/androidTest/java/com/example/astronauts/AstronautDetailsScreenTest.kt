package com.example.astronauts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.ui.detail.AstronautDetailCard
import com.example.astronauts.ui.theme.AstronautsTheme
import org.junit.Rule
import org.junit.Test

class AstronautDetailsScreenTest {
    @get:Rule
    var composeTestRule = createComposeRule()

    @Test
    fun testAstronautsDetails() {
        composeTestRule.setContent {
            AstronautsTheme {
                Box{
                    AstronautDetailCard(
                        Modifier.fillMaxSize(),
                        getMockAstronaut("Brad", "Aus")
                    )

                }
            }
        }

        with(composeTestRule) {
            waitUntil(200L) { true }
            onNodeWithText("Brad").assertIsDisplayed()
            onNodeWithText("Aus").assertIsDisplayed()
            onNodeWithText("my bio").assertIsDisplayed()
            onNodeWithText("19/12/1988").assertIsDisplayed()
        }
    }

    private fun getMockAstronaut(name: String, nationality: String) =
        Astronaut(
            id = 1,
            name = name,
            nationality = nationality,
            profileImage = "image",
            bio = "my bio",
            dateOfBirth = "19/12/1988"
        )
}
