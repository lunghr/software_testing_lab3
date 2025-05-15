import com.example.PaymentPage
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
class Lab3Tests {


    @Nested
    @Execution(ExecutionMode.SAME_THREAD)
    inner class PaymentPageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillFullPaymenxtForm`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val paymentPage = PaymentPage(driver)
                val hostName = "example.com"
                val amount = "100.00"

                paymentPage.openPaymentPage()
                paymentPage.fillFullPaymentForm(hostName, amount)
                assertTrue(
                    paymentPage.isInvalidInputDataNotificationDisplayed(),
                    "Invalid input data notification should be displayed"
                )
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillOnlyHostName`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val paymentPage = PaymentPage(driver)
                val hostName = "example.com"

                paymentPage.openPaymentPage()
                paymentPage.fillOnlyHostName(hostName)
                assertTrue(paymentPage.isEmptyAmountErrorDisplayed(), "Empty amount error should be displayed")
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillOnlyAmount`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val paymentPage = PaymentPage(driver)
                val amount = "100.00"

                paymentPage.openPaymentPage()
                paymentPage.fillOnlyAmount(amount)
                assertTrue(paymentPage.isEmptyHostNameErrorDisplayed(), "Empty hostname error should be displayed")
            } finally {
                driver.quit()
            }
        }
    }

    @Nested
    @Execution(ExecutionMode.SAME_THREAD)
    inner class KnowledgeBasePageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testGetAnswerFromFaqSection`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val knowledgeBasePage = KnowledgeBasePage(driver)

                knowledgeBasePage.getAnswerFromFaqSection()
                assertTrue(knowledgeBasePage.isCorrectPageDisplayed(), "Correct page should be displayed")
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testGetAnswerFromSearch`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val knowledgeBasePage = KnowledgeBasePage(driver)
                val searchQuery = "Что такое домен?"

                knowledgeBasePage.getAnswerFromSearch(searchQuery)
                assertTrue(knowledgeBasePage.isCorrectPageDisplayed(), "Correct page should be displayed")
            } finally {
                driver.quit()
            }
        }
    }

    @Nested
    @Execution(ExecutionMode.SAME_THREAD)
    inner class DocumentsPageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testOpenDocumentsPage`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val documentsPage = DocumentsPage(driver)

                documentsPage.openDocumentsPage()
                assertTrue(documentsPage.isErrorMessageDisplayed(), "Error message should be displayed")
            } finally {
                driver.quit()
            }
        }
    }

    @Nested
    @Execution(ExecutionMode.SAME_THREAD)
    inner class LoginPageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testSubmitEmptyFields`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val loginPage = LoginPage(driver)

                loginPage.submitEmptyFields()
                assertTrue(loginPage.isErrorNotificationDisplayed(), "Error notification should be displayed")
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testSubmitInvalidCredentials`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val loginPage = LoginPage(driver)

                loginPage.submitInvalidCredentials()
                assertTrue(loginPage.isErrorNotificationDisplayed(), "Error notification should be displayed")
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testSubmitValidCredentials`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val loginPage = LoginPage(driver)

                loginPage.submitValidCredentials()
                assertTrue(loginPage.isFinalPageDisplayed(), "Final page should be displayed")
            } finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testSubmitEmptyForm`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try {
                driver.get("https://timeweb.com")
                val loginPage = LoginPage(driver)

                loginPage.submitEmptyFields()
                assertTrue(loginPage.isErrorNotificationDisplayed(), "Error notification should be displayed")
            } finally {
                driver.quit()
            }
        }
    }


}