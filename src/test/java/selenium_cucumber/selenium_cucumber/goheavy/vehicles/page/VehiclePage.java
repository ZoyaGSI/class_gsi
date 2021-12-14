package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

@SuppressWarnings("unused")
public class VehiclePage extends PageObject {
    private String vehiclesLiXpath;
    private String addVehicleButtonXpath;
    private String VINImageUploadItemXpath;
    private String vehicleYearMakepath;
    private String vehicleCapacitySubSectionXpath;
    private String vehicleInsuranceImageXpath = "//label[@class='ant-form-item-required' and "
            + "@title='Current Insurance Certificate Picture']/ancestor::div[@class='ant-row ant-form-item']/descendant::input"
            + "[@type='file']";

    public VehiclePage() {
        super();
        setVehiclesLiXpath("//span[text()='Vehicles']/ancestor::li[contains(@class, 'ant-menu-item')]");
        setAddVehicleButtonXpath("//span[text()='Add Vehicle']/ancestor::button[@type='button' and "
                + "@class='ant-btn ant-btn-primary']");
        setVINImageUploadItemXpath("//label[@class='ant-form-item-required' and @title='VIN Image']/"
                + "ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']");
        setVehicleYearMakepath("//input[@placeholder='Enter Vehicle year']/ancestor::div[@class='ant-picker-input']");
        setVehicleCapacitySubSectionXpath("//span[@class='ant-divider-inner-text' and text()='Vehicle Capacity']");
        this.urlpath = "/vehicleinsurance";
    }


    private String getVehicleCapacitySubSectionXpath() {
        return vehicleCapacitySubSectionXpath;
    }

    private void setVehicleCapacitySubSectionXpath(String vehicleSubSectionXpath) {
        this.vehicleCapacitySubSectionXpath = vehicleSubSectionXpath;
    }

    private String getVehicleYearMakepath() {
        return vehicleYearMakepath;
    }

    private void setVehicleYearMakepath(String vehicleYearMakepath) {
        this.vehicleYearMakepath = vehicleYearMakepath;
    }


    private String getVINImageUploadItemXpath() {
        return VINImageUploadItemXpath;
    }

    private void setVINImageUploadItemXpath(String vINImageUploadItemXpath) {
        VINImageUploadItemXpath = vINImageUploadItemXpath;
    }

    private String getAddVehicleButtonXpath() {
        return addVehicleButtonXpath;
    }

    private void setAddVehicleButtonXpath(String addVehicleButtonXpath) {
        this.addVehicleButtonXpath = addVehicleButtonXpath;
    }

    private String getVehiclesLiXpath() {
        return vehiclesLiXpath;
    }

    private void setVehiclesLiXpath(String vehiclesLiXpath) {
        this.vehiclesLiXpath = vehiclesLiXpath;
    }


    public boolean goToVehiclesAndInsuranceListView() {
        try {
            waitForSpinningElementDissapear();
            Setup.getWait().thread(1500);
            WebElement element = getWebElement(By.xpath(getVehiclesLiXpath()));
            clickOn(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickOnAddVehicleButton() {
        try {
            waitForSpinningElementDissapear();
            Setup.getWait().thread(3000);
            WebElement element = getWebElement(By.xpath(getAddVehicleButtonXpath()));
            clickOn(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean checkStep(WebElement element, String step, String stepName) {
        waitForSpinningElementDissapear();
        String numberXpath = "//span[@class='ant-steps-icon' and text()='" + step + "']";
        String titleXpath = "//div[@class='ant-steps-item-title' and text()='" + stepName + "']";

        try {
            Assert.assertNotNull("Error on Step Number Retrieve", element.findElement(
                    By.xpath(numberXpath)));
            Assert.assertNotNull("Error on Step Title Retrieve", element.findElement(
                    By.xpath(titleXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean hoverElement(By by, WebElement element) {
        try {
            Setup.getWait().thread(500);
            if (element != null)
                Setup.getActions().moveToElement(element).build().perform();
            else
                Setup.getActions().moveToElement(getWebElement(by)).build().perform();
            Setup.getWait().thread(500);
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public void sendDataToInput(WebElement element, String data, Keys key, String form) {
        try {
            if (element.getAttribute("value").length() > 0)
                clear_element_text(element);
        } catch (Exception e) {
        }
        scrollToWebElement(element, form);
        if (data != null)
            Setup.getActions().sendKeys(element, data).build().perform();
        else
            Setup.getActions().sendKeys(element, key).build().perform();
    }

    public WebElement systemCreatesVehicleOnStatus(String status) {
        try {
            waitForSpinningElementDissapear();
            Setup.getActions().sendKeys(getWebElement(By.xpath(""
                            + "//input[@placeholder='Search...' and @type='text' and @class='ant-input']")),
                    (String) Setup.getValueStore("vehicleMake")).build().perform();

            Setup.getWait().thread(250);

            Setup.getActions().click(getWebElement(By.xpath(""
                    + "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']"))).build().perform();

            Setup.getWait().thread(250);

            String status_xpath = "//span[@class='ant-tag ant-tag-blue' and text()='" + status + "']";
            return getWebElement(By.xpath(status_xpath));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    public WebElement systemsRegistersCreationDate() {
        waitForSpinningElementDissapear();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date_compare = dtf.format(LocalDateTime.now());
            String date_xpath = "//td[@class='ant-table-cell' and contains(text(), '" + date_compare + "')]";
            return getWebElement(By.xpath(date_xpath));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }


    public boolean systemDisplaysAssignView() {
        Setup.getWait().thread(3000);
        waitForSpinningElementDissapear();
        try {
            String xpath = "//div[text()='Assign Vehicle' and @class='ant-modal-title']";
            WebElement alert = getWebElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public boolean userAssignsTheVehicle() {
        Setup.getWait().thread(3000);
        waitForSpinningElementDissapear();
        try {
            Setup.getActions().moveToElement(getWebElement(By.xpath("//input[@placeholder='Please, select a option']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//input[@placeholder='Please, select a option']"))).
                    build().perform();
            Setup.getWait().thread(1000);
            Setup.getActions().moveToElement(getWebElement(By.xpath("//div[@class='ant-select-item-option-content']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//div[@class='ant-select-item-option-content']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//button[@class='ant-btn ant-btn-primary' and @type='button']/"
                    + "descendant::span[text()='Assign']"))).build().perform();
            Setup.getWait().thread(1000);
            System.exit(0);
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public void clicks_button_done() {
        clickOn(getWebElement(By.xpath("//button[@type='submit']")));

        waitForSpinningElementDissapear();

    }

    public void systemDisplaysMessage(String message) {
        try {
            waitForSpinningElementDissapear();
            String xpath = "//div[@class='ant-notification ant-notification-topRight']";
            WebElement alert = getWebElement(By.xpath(xpath));
            Assert.assertEquals(alert.getText(), message);
        } catch (Exception e) {
            Assert.fail(e.getMessage());

        }
    }


    public boolean hoverOverImageComponent() {
        waitForSpinningElementDissapear();
        try {
            setImage(getWebElement(By.xpath(getVINImageUploadItemXpath())), null);
            Setup.getWait().thread(500);
            Setup.getActions().moveToElement(getWebElement(By.xpath("//div[contains(@class, 'kxeirt')]/descendant::img")))
                    .build().perform();
            Setup.getWait().thread(500);

            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-eye' and @cursor='pointer']")
                    , null));
            clickOn(getWebElement(By.xpath("//span[@role='img' and @class='anticon anticon-eye' and @cursor='pointer']")));
            clickOn(getWebElement(By.xpath("//span[@class='anticon anticon-close ant-modal-close-icon' and @role='img']")));
            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-check' and @cursor='pointer']")
                    , null));
            clickOn(getWebElement(By.xpath("//span[@role='img' and @class='anticon anticon-check' and @cursor='pointer']")));
            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-close' and @cursor='pointer']")
                    , null));

            return true;
        } catch (Exception e) {
            Assert.fail("Expected Image Over element not found");
            return false;
        }
    }
}
