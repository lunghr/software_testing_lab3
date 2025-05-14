import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

object BrowserFactory {
    fun getDriver(browser: String): WebDriver {
        return when (browser.lowercase()) {
            "firefox" -> {
                WebDriverManager.firefoxdriver().setup()
                FirefoxDriver()
            }
            "chrome" -> {
                WebDriverManager.chromedriver().setup()
                ChromeDriver()
            }
            else -> throw IllegalArgumentException("Unsupported browser: $browser")
        }
    }
}
