import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class VDSPage(
    private val driver: WebDriver
) {

    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val vdsIcon = By.xpath("//a[@href='/ru/services/vds/']")
    private val hzSortIcon = By.xpath("//div[@data-selenium='page-vds-tariff-filters-cpu']")
    private val hzList = By.xpath("//div[@class='chevron text-red flex-initial down']")
    private val fiveHzOption = By.xpath("//div[contains(text(), '5.5 ГГц')]")
    private val cpuOptions = By.xpath("//div[contains(text(), ' x 5.5 ГГц')]")
    private val showMoreButton =
        By.xpath("//div[@data-selenium='page-vds-tariff-show_more']")


    private val cpusHzList = mutableListOf<Double>()


    private fun clickVdsIcon() {
        wait.until { driver.findElement(vdsIcon) }.click()
    }

    private fun clickHzSortIcon() {
        val element = wait.until { driver.findElement(hzSortIcon)}
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView({block: 'center'});", element)
        wait.until(ExpectedConditions.elementToBeClickable(hzSortIcon)).click()
    }

    private fun clickFiveHzOption() {
        val element = wait.until { driver.findElement(fiveHzOption) }
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView({block: 'center'});", element)
        wait.until(ExpectedConditions.elementToBeClickable(fiveHzOption)).click()
    }

    private fun clickShowMoreButton() {
        val element = wait.until { driver.findElement(showMoreButton) }
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView({block: 'center'});", element)
        wait.until(ExpectedConditions.elementToBeClickable(showMoreButton)).click()
    }

    private fun getHzList() {
        val elements = wait.until { driver.findElements(cpuOptions) }
        for (element in elements) {
            val text = element.text
            val hzValue = text.substringAfter("x ").substringBefore(" ГГц").replace(",", ".").toDouble()
            cpusHzList.add(hzValue)
        }
    }

    fun sortByHz() {
        clickVdsIcon()
        clickHzSortIcon()
        clickFiveHzOption()
        clickShowMoreButton()
        getHzList()
    }

    fun isSortedByHz(): Boolean {
        return cpusHzList.size == 7
    }


}