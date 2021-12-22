package selenium_cucumber.selenium_cucumber.goheavy.driver.pages;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import selenium_cucumber.selenium_cucumber.general.InputType;
import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

@SuppressWarnings("unused")
public class DriverPage extends PageObject {
	private String menuDriversLink;
	private String addDriverButton;
	private String AddDriverTitle;

	public DriverPage() {
		super();
		this.urlpath = "/driver";
		setMenuDriversLink("//span[text()='Drivers']/ancestor::span[@class='ant-menu-title-content']");
		setAddDriverButton("//span[text()='Add Driver']/ancestor::button[@class='ant-btn ant-btn-primary']");
		setAddDriverTitle("//span[text()='Add Driver']/ancestor::div[@class='ant-row ant-row-space-between ant-row-middle']");
	}
	
	private String getAddDriverTitle() {
		return AddDriverTitle;
	}

	private void setAddDriverTitle(String addDriverTitle) {
		AddDriverTitle = addDriverTitle;
	}

	private String getAddDriverButton() {
		return addDriverButton;
	}

	private void setAddDriverButton(String addDriverButton) {
		this.addDriverButton = addDriverButton;
	}

	private String getMenuDriversLink() {
		return menuDriversLink;
	}

	private void setMenuDriversLink(String menuDriversLink) {
		this.menuDriversLink = menuDriversLink;
	}

	public boolean goToView() {
		try {
			waitForSpinningElementDissapear();
//			waitAddittionalTime();
			clickOnElement(getWebElement(By.xpath(getMenuDriversLink())), true);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void waitAddittionalTime() {
		Setup.getWait().thread(1000);
	}
	
	public void waitAddittionalShortTime() {
		Setup.getWait().thread(1000);
	}
	
	public void clickOnElement(WebElement element, boolean waitForSpinner) {
		if (waitForSpinner)
			waitForSpinningElementDissapear();
		Setup.getActions().moveToElement(element).build().perform();
		Setup.getActions().click(element).build().perform();
		if (waitForSpinner)
			waitForSpinningElementDissapear();
	}

	public boolean userClickOnAddDriver() {
		try {
			waitForSpinningElementDissapear();
//			waitAddittionalTime();
			clickOnElement(getWebElement(By.xpath(getAddDriverButton())), true);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public WebElement systemOpensAddDriverView() {
		try {
			waitForSpinningElementDissapear();
//			waitAddittionalTime();
			return getWebElement(By.xpath(getAddDriverTitle()));
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void interactAndRandomSelectFromDropDown(String id_dropdown, String id_options) {
		try {
			WebElement element = getWebElement(By.xpath("//input[@id='" + id_dropdown +"' and @role='combobox']"));
			Setup.getActions().moveToElement(element).build().perform();
			Setup.getActions().click(element).build().perform();
			String xpath = "//div[@role='listbox' and @id='" + id_options + "']/ancestor::div[contains(@class,'ant-select-dropdown')]/descendant::div[@class='ant-select-item-option-content']";
			List<WebElement> select_elements = getWebElements(By.xpath(xpath));
			WebElement option_element = select_elements.get(
					getFaker().number().numberBetween(2, 5));
			Setup.getWait().thread(750);
			Setup.getActions().moveToElement(option_element).build().perform();
			Setup.getActions().click(option_element).build().perform();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void clear_element_text(WebElement element) {
		int length = element.getAttribute("value").length();
		for (int i = 0;i <= length;i++) {
			Setup.getActions().sendKeys(element, Keys.BACK_SPACE).perform();
		}	
	}
	
	public void sendDataToInputByWebElement(WebElement element, String data) {
		clear_element_text(element);
		Setup.getActions().moveToElement(element).build().perform();
		Setup.getActions().sendKeys(element, data).build().perform();
	}

	public boolean userInsertsValidDataDone(boolean update) {
		try {
			waitForSpinningElementDissapear();
			waitAddittionalTime();
			String formId = "driver-form";

			if (update) {
				Setup.getActions().moveToElement(getWebElement(By.xpath("//input[@id='status']"))).build().perform();
				waitAddittionalShortTime();
				Setup.getActions().click(getWebElement(By.xpath("//input[@id='status']"))).build().perform();
				waitAddittionalShortTime();
				Setup.getActions().moveToElement(getWebElement(By.xpath("//div[text()='GoHeavy Ready']"))).build().perform();
				waitAddittionalShortTime();
				Setup.getActions().click(getWebElement(By.xpath("//div[text()='GoHeavy Ready']"))).build().perform();
				formScrollImproved(formId, Integer.valueOf(Setup.getTimeouts().get("pageLoad").toString()));
				waitAddittionalShortTime();

			} else {
				String title = "Driver Photo (including shoulders)";
				setImageImproved(title, null);

				String name = getFaker().name().firstName();
				Setup.setKeyValueStore("driverName", name);

				sendDataToInputImproved("First Name", (String) Setup.getValueStore("driverName"), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				sendDataToInputImproved("Last Name", getFaker().name().lastName(), null, InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				int min_val = 22;
				int max_val = 55;

				ThreadLocalRandom tlr = ThreadLocalRandom.current();
				int randomNum = tlr.nextInt(min_val, max_val + 1);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String date_compare = dtf.format(LocalDateTime.now().plusYears(randomNum * -1));

				sendDataToInputImproved("Birth Date", date_compare.toString(), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();
				sendDataToInputImproved("Birth Date", null, Keys.RETURN,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				sendDataToInputImproved("Experience", String.valueOf(getFaker().number().numberBetween(3, 8)), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				sendDataToInputImproved("Mobile", "53" + (String) getFaker().number().digits(8), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				sendDataToInputImproved("Email", getFaker().internet().emailAddress(), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				//tShirtSize
				//tShirtSize_list
				interactAndRandomSelectFromDropDown("tShirtSize", "tShirtSize_list");

				sendDataToInputImproved("Address", getFaker().address().streetName(), null, InputType.textarea, true, formId, 210);
				waitAddittionalShortTime();

				//addressStateId
				//addressStateId_list
				interactAndRandomSelectFromDropDown("addressStateId", "addressStateId_list");

				sendDataToInputImproved("City", getFaker().address().cityName(), null, InputType.input, true, formId, 210);
				waitAddittionalShortTime();

				sendDataToInputImproved("ZIP Code", getFaker().address().zipCode(), null, InputType.input, true, formId, 210);
				waitAddittionalShortTime();

				//Driver's License Photo (Front)
				title = "Driver's License Photo (Front)";
				setImageImproved(title, null);

				//Driver's License Photo (Back)
				title = "Driver's License Photo (Back)";
				setImageImproved(title, null);

				sendDataToInputImproved("Driver's License (DL) Number", "1" + String.valueOf(getFaker().number().digits(6)), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();

				formScrollImproved(formId, Integer.valueOf(Setup.getTimeouts().get("pageLoad").toString()));

				//dlClassType
				//dlClassType_list
				interactAndRandomSelectFromDropDown("dlClassType", "dlClassType_list");

				min_val = 2;
				max_val = 5;

				randomNum = tlr.nextInt(min_val, max_val + 1);

				dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				date_compare = dtf.format(LocalDateTime.now().plusMonths((randomNum * -1)));

				sendDataToInputImproved("DL Issued Date", date_compare.toString(), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();
				sendDataToInputImproved("DL Issued Date", null, Keys.RETURN,
						InputType.input, true, formId, 40);

				date_compare = dtf.format(LocalDateTime.now().plusMonths((randomNum)));

				sendDataToInputImproved("DL Expiration Date", date_compare.toString(), null,
						InputType.input, true, formId, 40);
				waitAddittionalShortTime();
				sendDataToInputImproved("DL Expiration Date", null, Keys.RETURN,
						InputType.input, true, formId, 40);

			}
			submitForm(formId);
			String xpath = "//*[@type='submit']";
			Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();

			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public WebElement systemCreatesDriverOnStatus(String status) {
		try {
			waitForSpinningElementDissapear();
			waitAddittionalTime();
			waitAddittionalShortTime();
			String class_value;
			if (status.equals("Clear"))
			class_value = "ant-tag ant-tag-green";
			else if (status.equals("GoHeavy Ready"))
				class_value = "ant-tag ant-tag-geekblue";
			else
			class_value = "ant-tag ant-tag-blue";
			
			/*if (Boolean.valueOf(Setup.getValueStore("clearDriver").toString())) {
				status = "Clear";
				class_value = "ant-tag ant-tag-green";
			}*/
			
			Setup.getActions().sendKeys(getWebElement(By.xpath("//input[@placeholder='Search...' and @type='text' and @class='ant-input']")),
					(String) Setup.getValueStore("driverName")).build().perform();
			
			Setup.getWait().thread(500);
			
			Setup.getActions().click(getWebElement(By.xpath("//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']"))).build().perform();
			
			Setup.getWait().thread(1000);

			String status_xpath = "//span[@class='" + class_value + "' and text()='" + status + "']";
			WebElement element = getWebElement(By.xpath(status_xpath));

			return element;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean userClicksOnDocumentsButton() {
		try{
			clickOnElement(getWebElement(By.xpath("//span[@class='ant-tag ant-tag-blue' and text()='On-boarding']"
					+ "/ancestor::tr[contains(@class, 'ant-table-row')]/descendant::span[@class='anticon anticon-file-text' "
					+ "and @role='img']")), true);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage()+"Missing Documents button");
			return false;
		}
	}
	
	public Object systemDisplaysDocuments() {
		try {
			waitAddittionalTime();
			waitForSpinningElementDissapear();
			return getWebElement(By.xpath("//span[@class='ant-breadcrumb-link']/ancestor::div"
					+ "[contains(@class, 'ant-breadcrumb')]/descendant::span[text()='Documents']"));
		} catch(Exception e) {
			print(e.getMessage());
			return null;
		}
	}

	public boolean userClicksOnApproveButton() {
		try {
			waitAddittionalTime();
			waitForSpinningElementDissapear();
			
			List<WebElement> elements = getWebElements(By.xpath("//span[not(contains(text(), 'Clear')) "
					+ "and contains(@class, 'ant-tag')]/ancestor::tr[contains(@class, 'ant-table-row')]/"
					+ "descendant::span[@role='img' and @class='anticon anticon-check-circle']"));
			
			for (int i = 0;i < elements.size();i++) {
				clickOnElement(elements.get(i), true);
				manageClearDocument();

			}
			clickOnElement(getWebElement(By.xpath("//button[contains(@class,'ant-btn ant-btn-primary')]")), true);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public boolean userClicksOnVehicleButton() {
			clickOnElement(getWebElement(By.xpath("//span[@class='rifi_link_icon_action']/descendant::span[@role='img' and @class='anticon anticon-car']")), true);
			return true;
	}

	private void manageClearDocument() {
		// TODO Auto-generated method stub
		clickOnElement(getWebElement(By.xpath("//button[@type='submit']")), true);
	}

	public boolean userClicksUpdate() {
		try {
			waitAddittionalTime();
			waitForSpinningElementDissapear();
			clickOnElement(getWebElement(By.xpath("//span[@class='rifi_link_icon_action']/descendant::span[@role='img' and @class='anticon anticon-edit']")), true);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Missing the Edit button");
			return false;
		}
	}

	public boolean systemOpensEdit() {
		// TODO Get this Implemented
		return true;
	}

	public boolean userClicksOnAssignButton() {
		try {
			clickOnElement(getWebElement(By.xpath("//span[@class='ant-tag ant-tag-green' and text()='Clear']"
					+ "/ancestor::tr[contains(@class, 'ant-table-row')]/descendant::span[@class='anticon anticon-edit' "
					+ "and @role='img']")), true);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
