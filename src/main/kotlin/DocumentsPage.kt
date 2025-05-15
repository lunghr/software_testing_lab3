import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class DocumentsPage(
    private val driver: WebDriver
) {
    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val documentsIcon = By.xpath("//a[@data-selenium='header-btn-community']")
    private val signInButton = By.xpath("//button[contains(text(), 'Войти в систему')]")
    private val errorMessage = By.xpath("//div[contains(text(), 'Все поля обязательны для заполнения')]")


    private fun clickDocumentsIcon() {
        wait.until { driver.findElement(documentsIcon) }.click()
    }

    private fun clickSignInButton() {
        wait.until { driver.findElement(signInButton) }.click()
    }

    fun openDocumentsPage() {
        clickDocumentsIcon()
        clickSignInButton()
    }

    fun isErrorMessageDisplayed(): Boolean {
        return try {
            wait.until { driver.findElement(errorMessage) }
            true
        } catch (e: Exception) {
            false
        }
    }
}