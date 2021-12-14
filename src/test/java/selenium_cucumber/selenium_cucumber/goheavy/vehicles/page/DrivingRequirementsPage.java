package selenium_cucumber.selenium_cucumber.goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import selenium_cucumber.selenium_cucumber.general.Setup;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DrivingRequirementsPage extends TabsPage {

    private String vehicleInsuranceImageXpath = "//label[@class='ant-form-item-required' and "
            + "@title='Current Insurance Certificate Picture']/ancestor::div[@class='ant-row ant-form-item']/descendant::input"
            + "[@type='file']";
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
        setImage(getWebElement(By.xpath(getVehicleInsuranceImageXpath())), null);

        clickOn(getWebElement(By.xpath("//input[@id='verificationDelivery']")));
        clickOn(getWebElement(By.xpath("//input[@id='verificationLicenseTime']")));

        sendDataToInput(getWebElement(By.xpath("//input[@id='insurancePolicyNo' and "
                        + "@placeholder='Enter Insurance Policy Number']")),
                getFaker().number().digits(12), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.xpath("//input[@id='insuranceCertificateCompany' and "
                        + "@placeholder='Enter Company name']")),
                getFaker().name().firstName(), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -2);
        Date past_date = calendar.getTime();
        DateFormat short_date = DateFormat.getDateInstance(DateFormat.SHORT);

        //From Date
        String by = "//input[@id='insuranceEffectiveDate' and "
                + "@placeholder='Select Date']";
        Setup.getActions().moveToElement(getWebElement(By.xpath(by))).build().perform();
        Setup.getActions().click(getWebElement(By.xpath(by))).build().perform();
        Setup.getActions().sendKeys(getWebElement(By.xpath(by)), short_date.format(getFaker().date().between(past_date, date)).toString())
                .build().perform();
        //Date here
        int min_val = 1;
        int max_val = 10;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);

        manageDate(true, randomNum);

        //To Date
        calendar.add(Calendar.YEAR, 2);
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date future_date = calendar.getTime();

        try {
            by = "//input[@id='insuranceExpirationDate' and "
                    + "@placeholder='Select Date']";
            Setup.getActions().moveToElement(getWebElement(By.xpath(by))).build().perform();
            Setup.getActions().click(getWebElement(By.xpath(by))).build().perform();
            Setup.getActions().sendKeys(getWebElement(By.xpath(by)), short_date.format(future_date).toString())
                    .build().perform();
            manageDate(false, randomNum);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.xpath("//input[@id='insuranceRenewal' and "
                        + "@placeholder='Enter Insurance Renewal']")),
                getFaker().name().firstName(), null, getStepThreeFormScroll());

        Setup.getWait().thread(500);

        sendDataToInput(getWebElement(By.xpath("//input[@id='licensePlateNo' and "
                        + "@placeholder='Enter Vehicle License Plate Number']")),
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
}
