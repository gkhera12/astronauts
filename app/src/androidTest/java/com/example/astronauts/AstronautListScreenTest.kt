package com.example.astronauts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.ui.astronautlist.AstronautsList
import com.example.astronauts.ui.components.ErrorCard
import com.example.astronauts.ui.theme.AstronautsTheme
import org.junit.Rule
import org.junit.Test

class AstronautListScreenTest {
    @get:Rule
    var composeTestRule = createComposeRule()

    @Test
    fun testAstronautsList() {
        composeTestRule.setContent {
            AstronautsTheme {
                Column {
                    AstronautsList(Modifier.fillMaxSize(),
                        listOf(
                            getMockAstronaut("Brad", "Aus"),
                            getMockAstronaut("Chang", "China"),
                            getMockAstronaut("Human", "Earth"),
                            getMockAstronaut("Zebra", "Kingdom")
                        ), {}, {})

                }
            }
        }

        with(composeTestRule) {
            onNodeWithText("Brad").assertIsDisplayed()
            onNodeWithText("Aus").assertIsDisplayed()
            onNodeWithText("Chang").assertIsDisplayed()
            onNodeWithText("China").assertIsDisplayed()
            onNodeWithText("Human").assertIsDisplayed()
            onNodeWithText("Earth").assertIsDisplayed()
            onNodeWithText("Zebra").assertIsDisplayed()
            onNodeWithText("Kingdom").assertIsDisplayed()
        }
    }

    @Test
    fun testErrorCard() {
        composeTestRule.setContent {
            AstronautsTheme {
                Column {
                    ErrorCard(Modifier.fillMaxSize(), {})

                }
            }
        }

        with(composeTestRule) {
            onNodeWithText("Something went wrong").assertIsDisplayed()
            onNodeWithText("Retry").assertIsDisplayed().assertHasClickAction()
        }
    }

    private fun getMockAstronaut(name: String, nationality: String) =
        Astronaut(
            id = 1,
            name = name,
            nationality = nationality,
            profileImage = "image"
        )
}

