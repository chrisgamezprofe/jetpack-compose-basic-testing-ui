package com.example.pruebas


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAutomatorTest {
    private lateinit var device: UiDevice
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    val pkg =appContext.packageName

    @Before
    fun startMainActivity() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Abre la app usando el paquete y el nombre de la actividad principal
        // Reemplaza "com.example.myapp" con el nombre real de tu paquete

    }

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testThatOutputIsExpectedOutput() {
        device.pressHome()
        device.executeShellCommand("am start -n ${pkg}/.MainActivity")

// Espera a que la app cargue (ajusta el tiempo si es necesario)
        device.waitForIdle(2000)

        // Enter the name into the inputTextField
        // Encuentra el campo de nombre de usuario por el testTag y le asigna texto
        composeTestRule.onNodeWithTag("UsernameField")
            .performTextInput("christiansena")
        Thread.sleep(2000)

        // Encuentra el campo de contraseña por el testTag y le asigna texto
        composeTestRule.onNodeWithTag("PasswordField")
            .performTextInput("mala")

        Thread.sleep(2000)
        // Encuentra el botón de login por su texto y haz clic
       /* composeTestRule.onNodeWithText("Login")
            .performClick()*/
        device.pressBack()
        Thread.sleep(2000)

        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()
        // Verifica que el mensaje de éxito de login se muestra
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithTag("outputTextField")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        composeTestRule.onNodeWithTag("PasswordField")
            .performTextClearance()
        Thread.sleep(2000)

        composeTestRule.onNodeWithTag("PasswordField")
            .performClick()
        Thread.sleep(1000)
        // Encuentra el campo de contraseña por el testTag y le asigna texto
        composeTestRule.onNodeWithTag("PasswordField")
            .performTextInput("wslyion2024")
        Thread.sleep(2000)
        device.pressBack()

        Thread.sleep(3000)
        // Encuentra el botón de login por su texto y haz clic
        /* composeTestRule.onNodeWithText("Login")
             .performClick()*/

        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()


        // Verifica que el mensaje de éxito de login se muestra
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithTag("outputTextField")
                .fetchSemanticsNodes().isNotEmpty()
        }

        Thread.sleep(4000)
        composeTestRule.onNodeWithTag("outputTextField")
            .assertTextEquals("Welcome, Christian sena!")

    }
}