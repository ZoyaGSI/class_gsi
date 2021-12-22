package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import selenium_cucumber.selenium_cucumber.general.Setup;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VehiculeInfoPage extends TabsPage {


    private String VINImageUploadItemXpath;
    private String VINInputID;
    private String VehicleMakeID;
    private String VINInputXpath;
    private String vehicleYearMakepath;
    private String vehicleCapacitySubSectionXpath;
    private String VehicleTypeID;
    private String stepBaseXpath;
    private String addVehicleTitleXpath;
    private String carIconXpath;
    private String formScroll = "//*[@id='step-one-form']/ancestor::div["
            + "@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
    private String vehiclePayloadID;

    public VehiculeInfoPage() {
        super();
        setVINImageUploadItemXpath("//label[@class='ant-form-item-required' and @title='VIN Image']/"
                + "ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']");
        setVINInputID("vin");
        setVehicleMakeID("make");
        setVehicleTypeID("vehicleTypeId");
        setStepBaseXpath("//div[@class='ant-steps-item-container']");
        setCarIconXpath("//span[text()='Add Vehicle']/span[@role='img' and @class='anticon anticon-car']");
        setVehicleYearMakepath("//input[@placeholder='Enter Vehicle year']/ancestor::div[@class='ant-picker-input']");
        setVehicleCapacitySubSectionXpath("//span[@class='ant-divider-inner-text' and text()='Vehicle Capacity']");
        setVehiclePayloadID("payload");
        setVINInputXpath("//label[@title='Vehicle ID No. (VIN)' and text()='Vehicle ID No. (VIN)']/"
                + "ancestor::div[@class='ant-row ant-form-item']/"
                + "descendant::input[@placeholder='XXXXXXXXXXXXXXXXX' and @maxlength='17']");
        setAddVehicleTitleXpath("//span[@role='img'and @aria-label='car' and "
                + "@class='anticon anticon-car']/ancestor::div[contains(@class, "
                + "'ant-col')]/descendant::span[text()='Add Vehicle']");
    }

    private String getVehiclePayloadID() {
        return vehiclePayloadID;
    }

    private void setVehiclePayloadID(String vehiclePayloadID) {
        this.vehiclePayloadID = vehiclePayloadID;
    }

    //
    private String getFormScroll() {
        return this.formScroll;
    }

    private void setFormScroll(String formScroll) {
        this.formScroll = formScroll;
    }


    private String getVehicleCapacitySubSectionXpath() {
        return this.vehicleCapacitySubSectionXpath;
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

    private String getVehicleMakeID() {
        return VehicleMakeID;
    }

    private void setVehicleMakeID(String VehicleMakeID) {
        this.VehicleMakeID = VehicleMakeID;
    }


    private String getVINInputID() {
        return this.VINInputID;
    }


    private void setVINInputID(String VINInputID) {
        this.VINInputID = VINInputID;
    }

    private String getVINImageUploadItemXpath() {
        return this.VINImageUploadItemXpath;
    }

    private void setVINImageUploadItemXpath(String vINImageUploadItemXpath) {
        this.VINImageUploadItemXpath = vINImageUploadItemXpath;
    }

    private String getCarIconXpath() {
        return carIconXpath;
    }

    private void setCarIconXpath(String carIconXpath) {
        this.carIconXpath = carIconXpath;
    }


    private String getVINInputXpath() {
        return this.VINInputXpath;
    }

    private void setVINInputXpath(String vINInputXpath) {
        this.VINInputXpath = vINInputXpath;
    }

    private String getAddVehicleTitleXpath() {
        return this.addVehicleTitleXpath;
    }

    private void setAddVehicleTitleXpath(String addVehicleTitleXpath) {
        this.addVehicleTitleXpath = addVehicleTitleXpath;
    }

    private String getVehicleTypeID() {
        return this.VehicleTypeID;
    }


    private void setVehicleTypeID(String VehicleTypeID) {
        this.VehicleTypeID = VehicleTypeID;
    }

    private String getStepBaseXpath() {
        return this.stepBaseXpath;
    }

    private void setStepBaseXpath(String stepBaseXpath) {
        this.stepBaseXpath = stepBaseXpath;
    }

    public void insertValidData() {

        sendDataToInput(getWebElement(By.id(getVINInputID())), getFaker().number().digits(17), null, getFormScroll());

        String vehicleMake = getFaker().superhero().name();
        Setup.setKeyValueStore("vehicleMake", vehicleMake);

        sendDataToInput(getWebElement(By.id(getVehicleMakeID())), (String) Setup.getValueStore("vehicleMake"), null,
                getFormScroll());
        checkVehicleTypeComponentBehaviour();
        int min_val = 1995;
        int max_val = 2018;

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);

        sendDataToInput(getWebElement(By.xpath(getVehicleYearMakepath())), String.valueOf(randomNum), null, getFormScroll());
        sendDataToInput(getWebElement(By.xpath(getVehicleYearMakepath())), null, Keys.RETURN, getFormScroll());

        fillDimensions();
        min_val = 90;
        max_val = 210;
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.id(getVehiclePayloadID())), String.valueOf(randomNum), null, getFormScroll());
        scrollToWebElement(null, getFormScroll());

        clickOn(getWebElement(By.xpath("//button[@type='submit']/descendant::span[text()='Next']")));
        waitForSpinningElementDissapear();
        Setup.getWait().thread(1500);

    }

    private void fillDimensions() {
        int min_val = 22;
        int max_val = 45;

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='long']")), String.valueOf(randomNum), null, getFormScroll());
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='width']")), String.valueOf(randomNum), null, getFormScroll());
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='height']")), String.valueOf(randomNum), null, getFormScroll());
    }

    public boolean systemOpensAddVehicleView() {
        Setup.getWait().thread(5000);
        waitForSpinningElementDissapear();

        try {
            Assert.assertNotNull("Page Title not found", getPageElementBy(By.xpath(getAddVehicleTitleXpath())));
            Assert.assertNotNull("Page Icon not Found", getPageElementBy(By.xpath(getCarIconXpath())));
            Assert.assertTrue("Step 1 not found", checkStep(getWebElement(By.xpath(getStepBaseXpath())), "1", "Vehicle Info"));
            Assert.assertNotNull("VIN Image Upload not found", getPageElementBy(By.xpath(getVINImageUploadItemXpath())));
            Assert.assertNotNull("VIN Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVINInputXpath())));
            Assert.assertNotNull("Vehicle Type Input not found or do not match Expected Criteria",
                    getPageElementBy(By.id(getVehicleTypeID())));
            checkVehicleTypeComponentBehaviour();
            Assert.assertNotNull("Vehicle Make Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleMakeID())));
            Assert.assertNotNull("Vehicle Year Make Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleYearMakepath())));
            Assert.assertNotNull("Vehicle Sub Section not found",
                    getPageElementBy(By.xpath(getVehicleCapacitySubSectionXpath())));
            //TODO: Work in progress check for every element on the page to be Expected
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void checkVehicleTypeComponentBehaviour() {
        waitForSpinningElementDissapear();
        try {
//            Setup.getWait().thread(150);
            clickOn(getWebElement(By.id(getVehicleTypeID())));
//            Setup.getWait().thread(150);
            Assert.assertNotNull("Vehicle types not found or none to show", getPageElementBy(By.xpath(
                    "//div[@class='rc-virtual-list-holder-inner']")));
            WebElement vehicle_type_list = getPageElementBy(By.xpath("//div[@class='rc-virtual-list-holder-inner']"));
            List<WebElement> element_list = vehicle_type_list.findElements(By.xpath(
                    "//div[@class='ant-select-item ant-select-item-option']"));
            int val = element_list.size();
            if (element_list.size() > 3)
                val = 0;

            int number = (int) (Math.random() * val + 1);
            hoverElement(null, element_list.get(number));
            clickOn(element_list.get(number));
//            Setup.getWait().thread(150);
        } catch (Exception e) {
        }
    }

    public WebElement getPageElementBy(By by) {
        try {
            return getWebElement(By.xpath(getAddVehicleTitleXpath()));
        } catch (Exception e) {
            Assert.fail("Expected Title Page element not found");
            return null;
        }
    }
}
