package com.page.objects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends GeneralPage {

    @FindBy(id = "SearchInput")
    private WebElement searchInput;

    @FindBy(id = "NightCount")
    private WebElement nightCountDropdown;

    @FindBy(css = "input.submit[value=Search]")
    private WebElement searchButton;

    @FindBy(id = "search-datepicker-check-in")
    private WebElement checkInDatePicker;

    @FindBy(id = "search-datepicker-check-out")
    private WebElement checkOutDatePicker;

    @FindBy(linkText = "Agoda price guarantee")
    private WebElement agodaPriceGuaranteeLink;

    @FindBy(id = "CheckInDay")
    private WebElement checkInDay;

    @FindBy(id = "CheckInMonthYear")
    private WebElement checkInMonthYear;

    @FindBy(id = "NightCount")
    private WebElement chooseNightCount;

    public void load() {
        loadPage("");
    }

    public void isLoad() {
        webPageFactory.checkWeAreOnTheRightPage("Agoda.com: Smarter Hotel Booking");
    }

    public SearchPage enterSearchQuery(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public boolean shouldDisplayAutoCompleteResult() {
        return webElementFinder.findElementByLocatorID("ui-id-1").isDisplayed();
    }

    public String shouldSeeTextSearchInput() {
        return searchInput.getText();
    }

    public String shouldSeeTextSelectedNightCountDropdown() {
        String css = "select#NightCount option[selected='selected']";
        // or: String xpath = "//select[@id='NightCount']/option[@selected='selected']";
        return webElementFinder.findElementByLocatorCSS(css).getText();
    }

    public String shouldSeeTextSelectedGuestsDropdown() {
        String css = "select#SelectedGuestOption option[value='2']";
        // or: String xpath = "//select[@id='SelectedGuestOption']/option[2]";
        return webElementFinder.findElementByLocatorCSS(css).getText();
    }

    public boolean shouldDisplaySearchButton() {
        return searchButton.isDisplayed();
    }

    public boolean shouldDisplayCheckInDatePicker() {
        return checkInDatePicker.isDisplayed();
    }

    public boolean shouldDisplayCheckOutDatePicker() {
        return checkOutDatePicker.isDisplayed();
    }

    public String shouldSeeTextAlert() {
        Alert alert = webPageFactory.getDriver().switchTo().alert();
        String msg = alert.getText();
        alert.accept();
        return msg;
    }

    public SearchResultsPage searchWithQuery(String text) {
        enterSearchQuery(text);
        clickSearchButtonShowResultsPage();
        return webPageFactory.loadSearchResultsPage();
    }

    public SearchPage clickAgodaPriceGuaranteeLink() {
        agodaPriceGuaranteeLink.click();
        return this;
    }

    public boolean shouldDisplayAgodaPriceGuaranteePopup() {
        WebElement element = webElementFinder.findElementByLocatorID("myModal");
        return element.isDisplayed();
    }

    public boolean shouldDisplayAgodaPriceGuaranteePopupLabel(String text) {
        WebElement element = webElementFinder.findElementByLocatorID("myModalLabel");
        return element.getText().equals(text);
    }

    public boolean shouldDisplayAgodaPriceGuaranteePopupHeading(String text) {
        String xpath = "//div[contains(@class,'modal-body')]//h3";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public SearchResultsPage clickSearchButtonShowResultsPage() {
        searchButton.click();
        return webPageFactory.loadSearchResultsPage();
    }

    public SearchPage clickSearchButtonShowValidation() {
        searchButton.click();
        return this;
    }

    public SearchResultsPage searchNonPrintingKey() {
        searchButton.click();
        return webPageFactory.loadSearchResultsPage();
    }

    public SearchPage chooseDay(String day){
        checkInDay.sendKeys(day);
        return this;
    }

    public SearchPage chooseMonthYear(String monthyear){
        checkInMonthYear.sendKeys(monthyear);
        return this;
    }

    public SearchPage chooseDataPicker(){
        checkInDatePicker.click();
        return this;
    }

    public SearchPage chooseNight(String night){
        chooseNightCount.sendKeys(night);
        return this;
    }

    public SearchPage chooseDatePickerCheckOut(){
        checkOutDatePicker.click();
        return this;
    }

//    public boolean selectFirstItemAutocompleteDisplay() {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOf(firstItemInAutoComplete));
//        firstItemInAutoComplete.click();
//        return true;
//    }
//
//    public String getBottomTextLink() {
//        return bottomLink.getText();
//    }
//
//    public void zoomIn(int time) {
//        for (int i = 0; i < time; i++) {
//            page.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
//        }
//    }
//
//    public void zoomOut(int time) {
//        for (int i = 0; i < time; i++) {
//            page.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//        }
//    }
//
//    public void zoomReset() {
//        page.sendKeys(Keys.chord(Keys.CONTROL, "0"));
//    }
//
//    public String getSizeWindow() {
//        return String.valueOf(driver.manage().window().getSize());
//    }
//
//    public void resizeWindowToSmaller() {
//        Dimension smallerDimension = new Dimension(600, 350);
//        driver.manage().window().setSize(smallerDimension);
//    }
//
//    public void resizeWindowToLarger() {
//        Dimension largerDimension = new Dimension(1500, 800);
//        driver.manage().window().setSize(largerDimension);
//    }
//
//    public void resizeWindowReset() {
//        Dimension originalSize = new Dimension(1382, 744);
//        driver.manage().window().setSize(originalSize);
//    }
//
//    public SearchPage inputKey() {
//        searchInput.sendKeys(Keys.chord(Keys.CONTROL, "c"));
//        return webPageFactory.loadSearchPage();
//    }

}