import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


class KnowledgeBasePage(
    private val driver: WebDriver
) {
//    2. База знаний -> часто задаваемые вопросы -> выбрать вопрос -> ответ
//    3. База знаний -> часто задаваемые вопросы -> поиск -> ввести вопрос -> открыть ответ -> ответ
    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val knowledgeBaseIcon = By.xpath("//a[@data-selenium='header-btn-help']")
    private val faqSection = By.xpath("//div[contains(text(), 'Часто задаваемые вопросы')]")
    private val question = By.xpath("//a[@href='https://timeweb.com/ru/docs/chasto-zadavaemye-voprosy/domeny-faq/#chto-takoe-domen']")
    private val searchInput = By.xpath("//input[@placeholder='Поиск по базе знаний']")
    private val searchAnswerLink = By.xpath("//div[contains(text(), 'Часто задаваемые вопросы')]")

    private val finalLink = "https://timeweb.com/ru/docs/chasto-zadavaemye-voprosy/domeny-faq/#chto-takoe-domen"

    private fun clickKnowledgeBaseIcon() {
        wait.until { driver.findElement(knowledgeBaseIcon) }.click()
    }

    private fun clickFaqSection() {
        wait.until { driver.findElement(faqSection) }.click()
    }

    private fun clickQuestion() {
        val element = wait.until { driver.findElement(question) }
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView({block: 'center'});", element)
        wait.until(ExpectedConditions.elementToBeClickable(question)).click()
    }


    private fun enterSearchQuery(query: String) {
        driver.findElement(searchInput).clear()
        val input = wait.until { driver.findElement(searchInput) }
        input.sendKeys(query)
        input.sendKeys(org.openqa.selenium.Keys.ENTER)
    }

    private fun clickSearchAnswerLink() {
        wait.until { driver.findElement(searchAnswerLink) }.click()
    }

    fun getAnswerFromFaqSection() {
        clickKnowledgeBaseIcon()
        clickFaqSection()
        clickQuestion()
    }

    fun getAnswerFromSearch(query: String) {
        clickKnowledgeBaseIcon()
        enterSearchQuery(query)
        clickSearchAnswerLink()
        clickQuestion()
    }

    fun isCorrectPageDisplayed(): Boolean {
        return driver.currentUrl == finalLink
    }
}