package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium_cucumber.selenium_cucumber.general.Setup;

import java.util.concurrent.ThreadLocalRandom;

public class VehiculeInfoPage extends TabsPage {


    private String VINImageUploadItemXpath;
    private String VINInputXpath;
    private String vehicleMakeXpath;
    private String vehicleYearMakepath;
    private String vehicleCapacitySubSectionXpath;
    private String formScroll = "//*[@id='step-one-form']/ancestor::div["
            + "@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
    private String vehiclePayloadXpath;

    public VehiculeInfoPage() {
        super();
        setVINImageUploadItemXpath("//label[@class='ant-form-item-required' and @title='VIN Image']/"
                + "ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']");
        setVINInputXpath("//label[@title='Vehicle ID No. (VIN)' and text()='Vehicle ID No. (VIN)']/"
                + "ancestor::div[@class='ant-row ant-form-item']/"
                + "descendant::input[@placeholder='XXXXXXXXXXXXXXXXX' and @maxlength='17']");
        setVehicleMakeXpath("//input[@placeholder='Enter Vehicle make' and @id='make']");
        setVehicleYearMakepath("//input[@placeholder='Enter Vehicle year']/ancestor::div[@class='ant-picker-input']");
        setVehicleCapacitySubSectionXpath("//span[@class='ant-divider-inner-text' and text()='Vehicle Capacity']");
        setVehiclePayloadXpath("//input[@id='payload']");
    }

    private String getVehiclePayloadXpath() {
        return vehiclePayloadXpath;
    }

    private void setVehiclePayloadXpath(String vehiclePayloadXpath) {
        this.vehiclePayloadXpath = vehiclePayloadXpath;
    }

    //
    private String getFormScroll() {
        return formScroll;
    }

    private void setFormScroll(String formScroll) {
        this.formScroll = formScroll;
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

    private String getVehicleMakeXpath() {
        return vehicleMakeXpath;
    }

    private void setVehicleMakeXpath(String vehicleMakeXpath) {
        this.vehicleMakeXpath = vehicleMakeXpath;
    }


    private String getVINInputXpath() {
        return VINInputXpath;
    }


    private void setVINInputXpath(String vINInputXpath) {
        VINInputXpath = vINInputXpath;
    }

    private String getVINImageUploadItemXpath() {
        return VINImageUploadItemXpath;
    }

    private void setVINImageUploadItemXpath(String vINImageUploadItemXpath) {
        VINImageUploadItemXpath = vINImageUploadItemXpath;
    }







    public void insertValidData() {

        sendDataToInput(getWebElement(By.xpath(getVINInputXpath())), getFaker().number().digits(17), null, getFormScroll());

        String vehicleMake = getFaker().superhero().name();
        Setup.setKeyValueStore("vehicleMake", vehicleMake);

        sendDataToInput(getWebElement(By.xpath(getVehicleMakeXpath())), (String) Setup.getValueStore("vehicleMake"), null,
                getFormScroll());

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
        sendDataToInput(getWebElement(By.xpath(getVehiclePayloadXpath())), String.valueOf(randomNum), null, getFormScroll());
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
}
