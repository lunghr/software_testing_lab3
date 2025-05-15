import com.example.PaymentPage
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.WebDriver


class Lab3Tests {


    @Nested
    inner class  PaymentPageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillFullPaymenxtForm`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try{
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
            }
            finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillOnlyHostName`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try{
                driver.get("https://timeweb.com")
                val paymentPage = PaymentPage(driver)
                val hostName = "example.com"

                paymentPage.openPaymentPage()
                paymentPage.fillOnlyHostName(hostName)
                assertTrue(paymentPage.isEmptyAmountErrorDisplayed(), "Empty amount error should be displayed")
            }
            finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testFillOnlyAmount`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try{
                driver.get("https://timeweb.com")
                val paymentPage = PaymentPage(driver)
                val amount = "100.00"

                paymentPage.openPaymentPage()
                paymentPage.fillOnlyAmount(amount)
                assertTrue(paymentPage.isEmptyHostNameErrorDisplayed(), "Empty hostname error should be displayed")
            }
            finally {
                driver.quit()
            }
        }
    }

    @Nested
    inner class KnowledgeBasePageTests {
        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testGetAnswerFromFaqSection`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try{
                driver.get("https://timeweb.com")
                val knowledgeBasePage = KnowledgeBasePage(driver)

                knowledgeBasePage.getAnswerFromFaqSection()
                assertTrue(knowledgeBasePage.isCorrectPageDisplayed(), "Correct page should be displayed")
            }
            finally {
                driver.quit()
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["chrome", "firefox"])
        fun `testGetAnswerFromSearch`(browser: String) {
            val driver = BrowserFactory.getDriver(browser)
            try{
                driver.get("https://timeweb.com")
                val knowledgeBasePage = KnowledgeBasePage(driver)
                val searchQuery = "Что такое домен?"

                knowledgeBasePage.getAnswerFromSearch(searchQuery)
                assertTrue(knowledgeBasePage.isCorrectPageDisplayed(), "Correct page should be displayed")
            }
            finally {
                driver.quit()
            }
        }
    }
}