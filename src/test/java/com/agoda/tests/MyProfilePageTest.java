package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import com.page.objects.MyProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyProfilePageTest extends BasePage {

    private LogInPage logInPage;
    private MyProfilePage myProfilePage;
    private MyBookingsPage myBookingsPage;

    private String VALID_EMAIL;
    private String VALID_PASSWORD;
    private String EDIT_COUNTRY;
    private String OLD_COUNTRY;
    private String EMAIL_CHANGED;

    @BeforeClass
    public void beforeClass() {
        VALID_EMAIL = testData.getProperty("validEmail");
        VALID_PASSWORD = testData.getProperty("validPassword");
        EDIT_COUNTRY = testData.getProperty("editCountry");
        OLD_COUNTRY = testData.getProperty("oldCountry");
        EMAIL_CHANGED = testData.getProperty("emailChanged");

        logInPage = webPageFactory.loadLogInPage();
        myBookingsPage = logInPage.logInPassed(VALID_EMAIL, VALID_PASSWORD);
        myBookingsPage.isLoaded();
        myProfilePage = webPageFactory.getMyProfilePage();
    }

    @BeforeMethod
    public void beforeMethod() {
        myProfilePage.load();
    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "verify Default Page Displayed")
    public void VerifyDefaultPageDisplayed() {
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(0), "Basic Information");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(1), "Subscriptions");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(2), "Favorite destinations");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(3), "Mailing Address");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(4), "My Social Networks");
    }

    @Test(description = "verify Update Information Profile Incorrect")
    public void verifyUpdateInformationProfileIncorrect() {
        myProfilePage.clickMyProfile()
                     .clickLinkEditInformation()
                     .chooseCountryOfPassport("Please Select")
                     .clickLinkSaveInformation();

        Assert.assertTrue(myProfilePage.shouldSeeTextNotificationError("Basic Information is incorrect."));
    }

    @Test(description = "verify Update Information Successfully")
    public void verifyUpdateInformationSuccessfully() {
        myProfilePage.clickMyProfile()
                     .clickLinkEditInformation()
                     .editEmail(EMAIL_CHANGED)
                     .chooseCountryOfPassport(EDIT_COUNTRY)
//                     .selectTheCheckbox()
                     .clickLinkSaveInformation();

        //verify information has been changed
        Assert.assertEquals(myProfilePage.shouldSeeTextCountry(), EDIT_COUNTRY);
        Assert.assertEquals(myProfilePage.shouldSeeTextBasicInformationUpdateSuccessfully(), "Your Basic Information has been changed successfully.");
        Assert.assertEquals(myProfilePage.shouldSeeTextEmailEdit(), EMAIL_CHANGED);
//        Assert.assertTrue(myProfilePage.shouldSeeTextCheckboxSelected("Business travelers, Couples, Solo travelers"));

//      //Clean up data
        myProfilePage.clickMyProfile()
                     .clickLinkEditInformation()
                     .editEmail(VALID_EMAIL)
                     .chooseCountryOfPassport(OLD_COUNTRY)
//                     .deSelectTheCheckbox()
                     .clickLinkSaveInformation();
    }
}
