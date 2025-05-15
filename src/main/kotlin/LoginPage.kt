import io.github.cdimascio.dotenv.Dotenv
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class LoginPage(
    private val driver: WebDriver
) {
    private val env = Dotenv.load()
    private val login = env["LOGIN"]
    private val password = env["PASSWORD"]
    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val loginIcon = By.xpath("//a[@data-selenium='header-btn-login']")
    private val usernameInput = By.xpath("//input[@placeholder='Логин']")
    private val passwordInput = By.xpath("//input[@placeholder='Пароль']")
    private val loginButton =
        By.xpath("//p[contains(text(), 'Войти в панель управления')]")
    private val errorNotification = By.xpath("//p[@class='c-hTHUpS']")

    private val accountName = By.xpath("//p[@data-analytics-id='login_vh']")




    private fun redirect(driver: WebDriver): WebDriver {
        val originalWindow = driver.windowHandle
        wait.until { driver.windowHandles.size > 1 }

        val newWindow = driver.windowHandles.first { it != originalWindow }
        driver.switchTo().window(newWindow)

        return driver
    }



    private fun clickLoginIcon() {
        wait.until { driver.findElement(loginIcon) }.click()
    }

    private fun enterUsername(username: String) {
        val usernameField = wait.until { driver.findElement(usernameInput) }
        usernameField.clear()
        usernameField.sendKeys(username)
    }

    private fun enterPassword(password: String) {
        println("CURRENT URL PASS: ${driver.currentUrl}")
        driver.findElement(passwordInput).clear()
        wait.until { driver.findElement(passwordInput) }.sendKeys(password)
    }

    private fun clickLoginButton() {
        wait.until { driver.findElement(loginButton) }.click()
    }

    fun submitEmptyFields() {
        clickLoginIcon()
        redirect(driver)
        clickLoginButton()
    }

    fun submitInvalidCredentials() {
        clickLoginIcon()
        redirect(driver)
        enterUsername("invalid_username")
        enterPassword("invalid_password")
        clickLoginButton()
    }

    fun submitValidCredentials() {
        clickLoginIcon()
        redirect(driver)
        enterUsername(login)
        enterPassword(password)
        clickLoginButton()
    }

    fun isErrorNotificationDisplayed(): Boolean {
        return try {
            wait.until { driver.findElement(errorNotification) }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isFinalPageDisplayed(): Boolean {
        return try {
            wait.until { driver.findElement(accountName) }
            true
        } catch (e: Exception) {
            false
        }
    }
}