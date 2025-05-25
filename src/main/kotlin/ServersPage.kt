import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class ServersPage(
    private val driver: WebDriver
) {
    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val serversIcon = By.xpath("//a[@href='/ru/services/dedicated-server/']")
    private val fromInput =
        By.xpath("//input[@data-selenium='page-dedicated-server-servers-ready-filters-price-range-from']")
    private val toInput =
        By.xpath("//input[@data-selenium='page-dedicated-server-servers-ready-filters-price-range-to']")
    private val sortedResultsArray =
        By.xpath("//div[contains(@class, 'tw-dedic-servers-item')and not(contains(@style, 'display: none'))]")
    private val pricePath = By.xpath("//div[contains(@class, 'price')]//div[contains(@class, 'title')]")

    private val priceList = mutableListOf<String>()

    private fun clickServersIcon() {
        wait.until { driver.findElement(serversIcon) }.click()
    }

    private fun enterFromPrice(from: Int) {
        val slider = driver.findElement(fromInput)
        (driver as JavascriptExecutor).executeScript(
            "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'))",
            slider,
            from
        )
    }

    private fun enterToPrice(to: Int) {
        val slider = driver.findElement(toInput)
        (driver as JavascriptExecutor).executeScript(
            "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'))",
            slider,
            to
        )
    }

    private fun getSortedResults() {
        val elements = wait.until { driver.findElements(sortedResultsArray) }
        println("Elements size: ${elements.size}")
        for (element in elements) {
            val priceElement = element.findElement(pricePath)
            priceList.add(priceElement.text)
        }
    }

    fun sortByPrice(from: Int, to: Int) {
        clickServersIcon()
        enterFromPrice(from)
        enterToPrice(to)
        getSortedResults()
    }

    fun isSortedByPrice(from: Int, to: Int): Boolean {
        val sortedPriceList = priceList.map { it.replace("₽ в месяц", "").replace(" ", "").toInt() }.sorted()
        return sortedPriceList.map { it in (from + 1)..<to }.all { it }
    }


}