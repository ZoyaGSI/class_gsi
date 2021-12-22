package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import selenium_cucumber.selenium_cucumber.general.InputType;
import selenium_cucumber.selenium_cucumber.general.Setup;

import java.security.Key;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DrivingRequirementsPage extends TabsPage {

    private String vehicleInsuranceImageXpath = "//label[@title='Current Insurance Certificate Picture']/ancestor::" +
            "div[@class='ant-row ant-form-item']/descendant::input[@type='file']";
    private String stepThreeFormScroll = "//*[@id='step-three-form']/ancestor::div["
            + "@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";

    public DrivingRequirementsPage() {
        super();


    }

    private String getVehicleInsuranceImageXpath() {
        return vehicleInsuranceImageXpath;
    }

    private String getStepThreeFormScroll() {
        return stepThreeFormScroll;
    }


    public void insertValidData() {
        String formId = "step-three-form";

        setImage(getWebElement(By.xpath(getVehicleInsuranceImageXpath())), null);

        clickOn(getWebElement(By.id("verificationDelivery")));
        clickOn(getWebElement(By.id("verificationLicenseTime")));

        sendDataToInput(getWebElement(By.id("insurancePolicyNo")),
                getFaker().number().digits(12), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.id("insuranceCertificateCompany")),
                getFaker().name().firstName(), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);


//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.YEAR, -2);
//        Date past_date = calendar.getTime();
//        DateFormat short_date = DateFormat.getDateInstance(DateFormat.SHORT);

        //From Date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date_compare = dtf.format(LocalDateTime.now());
        String date_compared = dtf.format(LocalDateTime.now().plusYears(1));

        sendDataToInputImproved("Insurance Effective Date", date_compare.toString(), null,
                InputType.input, true, formId, 40);
        Setup.getWait().thread(1000);
        sendDataToInputImproved("Insurance Effective Date", null, Keys.RETURN,
                InputType.input, true, formId, 40);
        Setup.getWait().thread(1000);

//        To Date
        try {
            sendDataToInputImproved("Insurance Expiration Date", date_compared.toString(), null,
                    InputType.input, true, formId, 40);
            sendDataToInputImproved("Insurance Expiration Date", null, Keys.RETURN,
                    InputType.input, true, formId, 40);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.id("insuranceRenewal")),
                getFaker().name().firstName(), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.id("licensePlateNo")),
                getFaker().number().digits(6), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        managePlateState();

        scrollToWebElement(null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        setImage(getWebElement(By.xpath("//label[@title='License Plate Photo']/ancestor::div[contains(@class, "
                + "'ant-form-item')]/descendant::input[@type='file']")), null);

        Setup.getWait().thread(500);

        setImage(getWebElement(By.xpath("//label[@title='Vehicle Registration Sticker']/ancestor::div[contains(@class, "
                + "'ant-form-item')]/descendant::input[@type='file']")), null);

        Setup.getWait().thread(500);

        clickOn(getWebElement(By.xpath("//button[@type='submit']/descendant::span[text()='Done']")));
        waitForSpinningElementDissapear();
        Setup.getWait().thread(1500);
    }

    private void manageDate(boolean back, int random_num) {
        String xpath = "//button[@class='ant-picker-header-prev-btn']";
        Setup.getWait().thread(500);
        if (!back) {
            //TODO: Get this improved
            xpath = "/html/body/div[3]/div/div/div/div/div[1]/div[1]/button[3]";
            Setup.getActions().moveToElement(getWebElement(By.xpath(xpath))).build().perform();
            Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
            xpath = "/html/body/div[3]/div/div/div/div/div[1]/div[2]/table/tbody/tr[5]/td[6]/div";
            Setup.getActions().moveToElement(getWebElement(By.xpath(xpath))).build().perform();
            Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
            return;
        } else {
            try {
                for (int i = 0; i < random_num; i++) {
                    Setup.getActions().click(getWebElement(By.xpath(xpath)))
                            .build().perform();
                }
                String xpath_day = "//div[@class='ant-picker-cell-inner' and text()='" + random_num + "']";
                Setup.getActions().click(getWebElement(By.xpath(xpath_day))).build().perform();
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
    }

    private void managePlateState() {
        String xpath = "//input[@id='licensePlateStateIssuedId']/ancestor::div[@class='ant-form-item-control-input']";

        sendDataToInput(getWebElement(By.xpath(xpath)),
                null, Keys.SPACE, getStepThreeFormScroll());

        List<WebElement> states = getWebElements(By.xpath("//div[@class='ant-select-item ant-select-item-option']"));

        int val = states.size();

        try {
            int number = (int) (Math.random() * val + 1);
            if (number == 0)
                number = 2;

            clickOn(states.get(number - 1));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean systemOpensAddVehicleView() {
        return true;
    }
}
