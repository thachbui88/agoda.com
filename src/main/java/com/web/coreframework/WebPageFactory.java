package com.web.coreframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;

@Component
public class WebPageFactory {

    @Autowired
    private WebDriver driver;
    @Autowired
    private LogInPage logInPage;
    @Autowired
    private MyBookingsPage myBookingsPage;

    public void checkWeAreOnTheRightPage(String titlePage) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (!wait.until(ExpectedConditions.titleContains(titlePage)))
            throw new IllegalStateException(String.format("This is not the %s page.", titlePage));
    }

    public LogInPage loadLogInPage() {
        PageFactory.initElements(driver, logInPage);
        logInPage.load();
        return logInPage;
    }

    public LogInPage getLogInPage() {
        PageFactory.initElements(driver, logInPage);
        return logInPage;
    }

    public MyBookingsPage getMyBookingsPage() {
        PageFactory.initElements(driver, myBookingsPage);
        return myBookingsPage;
    }

    public WebDriver getDriver() {
        return driver;
    }

}