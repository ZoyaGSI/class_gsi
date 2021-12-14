package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import org.openqa.selenium.By;
import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

import java.util.concurrent.ThreadLocalRandom;

public class VehiculeFeaturesPage extends TabsPage {
    private String stepTwoFormScroll;
    private String vehiclePhotoImageXpath;

    public VehiculeFeaturesPage() {
        super();
        setVehiclePhotoImageXpath("//label[@class='ant-form-item-required' and "
                + "@title='Vehicle Photo (from front driver side angle)']/ancestor::div[@class='ant-row ant-form-item']/"
                + "descendant::input[@type='file']");
        setStepTwoFormScroll("//*[@id='step-two-form']/ancestor::div[@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']");
    }

    private String getStepTwoFormScroll() {
        return stepTwoFormScroll;
    }

    private void setStepTwoFormScroll(String stepTwoFormScroll) {
        this.stepTwoFormScroll = stepTwoFormScroll;
    }

    private String getVehiclePhotoImageXpath() {
        return vehiclePhotoImageXpath;
    }

    private void setVehiclePhotoImageXpath(String vehiclePhotoImageXpath) {
        this.vehiclePhotoImageXpath = vehiclePhotoImageXpath;
    }

    public void insertValidData() {
        int min_val = 1995;
        int max_val = 2018;

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='model' and @placeholder='Enter Vehicle model']")),
                getFaker().artist().name(), null, getStepTwoFormScroll());

        sendDataToInput(getWebElement(By.xpath("//input[@id='color' and @placeholder='Enter Vehicle color']")),
                getFaker().color().name().toUpperCase(), null, getStepTwoFormScroll());

        sendDataToInput(getWebElement(By.xpath("//input[@id='trim' and @placeholder='Enter Vehicle trim']")),
                getFaker().name().firstName(), null, getStepTwoFormScroll());

        sendDataToInput(getWebElement(By.xpath("//input[@id='transmission' and @placeholder='Enter Vehicle transmission']")),
                getFaker().name().firstName(), null, getStepTwoFormScroll());

        if (min_val < 145)
            clickOn(getWebElement(By.xpath("//label[text()='Towing Kit Installed']/ancestor::div["
                    + "@class='ant-row ant-form-item']/descendant::button[@type='button' and @class='ant-switch']")));

        if (randomNum % 2 == 0)
            clickOn(getWebElement(By.xpath("//label[text()='Liftgate Installed']/ancestor::div["
                    + "@class='ant-row ant-form-item']/descendant::button[@type='button' and @class='ant-switch']")));

        setImage(getWebElement(By.xpath(getVehiclePhotoImageXpath())), null);

        scrollToWebElement(null, getStepTwoFormScroll());

        clickOn(getWebElement(By.xpath("//button[@type='submit']/descendant::span[text()='Next']")));
        waitForSpinningElementDissapear();
        Setup.getWait().thread(1500);
    }


}
