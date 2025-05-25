package com.example

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


class PaymentPage(
    private val driver: WebDriver
) {

    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    private val paymentIcon = By.xpath("//a[@data-selenium='header-btn-payment']")
    private val sbpPaymentMethod = By.xpath("//div[@data-selenium='modal_pay_sbp']")
    private val hostNameInput = By.xpath("//input[@data-selenium='modal-payment-fld-domain']")
    private val amountInput = By.xpath("//input[@data-selenium='modal-payment-fld-amount']")
    private val submitButton = By.xpath("//button[@data-selenium='modal-payment-btn-submit']")
    private val emptyHostnameInputError = By.xpath("//div[@data-selenium='modal-payment-fld-domain-error']")
    private val emptyAmountInputError = By.xpath("//div[@data-selenium='modal-payment-fld-amount-error']")
    private val invalidInputDataNotification =
        By.xpath("//div[contains(@class, 'ui-toast-item') and contains(@class, 'bg-red')]")

    private fun clickPaymentIcon() {
        wait.until { driver.findElement(paymentIcon) }.click()
    }

    private fun clickSbpPaymentMethod() {
        // Wait for the payment method to be clickable

        wait.until(ExpectedConditions.elementToBeClickable(sbpPaymentMethod)).click()
    }

    private fun enterHostName(hostName: String) {
        driver.findElement(hostNameInput).clear()
        wait.until { driver.findElement(hostNameInput) }.sendKeys(hostName)
        wait.
    }

    private fun enterAmount(amount: String) {
        driver.findElement(amountInput).clear()
        wait.until { driver.findElement(amountInput) }.sendKeys(amount)
    }

    private fun clickSubmitButton() {
        wait.until { driver.findElement(submitButton) }.click()
    }

    fun openPaymentPage() {
        clickPaymentIcon()
        clickSbpPaymentMethod()
    }

    fun fillFullPaymentForm(hostName: String, amount: String) {
        enterHostName(hostName)
        enterAmount(amount)
        clickSubmitButton()
    }

    fun fillOnlyHostName(hostName: String) {
        enterHostName(hostName)
        clickSubmitButton()
    }

    fun fillOnlyAmount(amount: String) {
        enterAmount(amount)
        clickSubmitButton()
    }

    fun isEmptyHostNameErrorDisplayed(): Boolean {
        return wait.until { driver.findElement(emptyHostnameInputError) }.isDisplayed
    }

    fun isEmptyAmountErrorDisplayed(): Boolean {
        return wait.until { driver.findElement(emptyAmountInputError) }.isDisplayed
    }

    fun isInvalidInputDataNotificationDisplayed(): Boolean {
        return wait.until { driver.findElement(invalidInputDataNotification) }.isDisplayed
    }
}