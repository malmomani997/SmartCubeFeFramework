package Base.TestHelpers;

import Base.PagesHelper.PetStoreCheckoutPageHelper;
import Base.PagesHelper.PetStoreHomePageHelper;
import Base.PagesHelper.PetStoreRegistrationPageHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private PetStoreHomePageHelper petStoreHomePageHelper;
    private PetStoreRegistrationPageHelper petStoreRegistrationPageHelper;
    private PetStoreCheckoutPageHelper petStoreCheckoutPageHelper;

    private static final String GLOBAL_DATA_PATH = "/src/main/java/Base/Resources/GlobalData.properties";
    private static final String REPORTS_PATH = "/reports/";
    private static final String SCREENSHOTS_PATH = "/screenshots/";

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws IOException {
        setDriver(initializeDriver(browser));
        getDriver().get("https://petstore.octoperf.com/actions/Catalog.action");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getMethod().getMethodName());
        }

        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    private WebDriver initializeDriver(String browser) throws IOException {
        Properties prop = loadProperties();
        String browserName = browser != null ? browser : prop.getProperty("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                setDriver(new ChromeDriver(getChromeOptions()));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                setDriver(new FirefoxDriver(getFirefoxOptions()));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                setDriver(new EdgeDriver(getEdgeOptions()));
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        configureDriver();
        return getDriver();
    }

    private Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + GLOBAL_DATA_PATH);
        prop.load(fis);
        return prop;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Enable for docker
        options.addArguments("--no-sandbox"); // Enable for docker
        options.addArguments("--disable-gpu"); // Enable for docker
        options.setBinary("/usr/bin/google-chrome"); // Enable for docker
        options.addArguments("--remote-allow-origins=*"); // Enable for docker
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"); // Enable for docker
        options.addArguments("--window-size=1920,1080");
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        profile.setPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
        options.setProfile(profile);
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        return new EdgeOptions();
    }

    private void configureDriver() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().manage().window().maximize();

        petStoreHomePageHelper = new PetStoreHomePageHelper(getDriver());
        petStoreRegistrationPageHelper = new PetStoreRegistrationPageHelper(getDriver());
        petStoreCheckoutPageHelper = new PetStoreCheckoutPageHelper(getDriver());

    }

    @BeforeSuite
    public void cleanUp() {
        cleanupExtentReport();
    }

    public void cleanupExtentReport() {
        File reportsDirectory = new File(System.getProperty("user.dir") + REPORTS_PATH);
        if (reportsDirectory.exists() && deleteDirectory(reportsDirectory)) {
            System.out.println("Reports directory and its contents deleted successfully.");
        } else {
            System.err.println("Failed to delete the reports directory or directory does not exist.");
        }
    }

    private boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
        }
        return directory.delete();
    }

    public String captureScreenshot(String testCaseName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotFileName = testCaseName + "_" + timestamp + ".png";
            String screenshotPath = System.getProperty("user.dir") + REPORTS_PATH + SCREENSHOTS_PATH + screenshotFileName;
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot captured: " + destination.getAbsolutePath());
            return screenshotPath;
        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            return null;
        }
    }

    public PetStoreHomePageHelper getPetStoreHomePageHelper() {return petStoreHomePageHelper;}
    public PetStoreRegistrationPageHelper getPetStoreRegistrationPageHelper() {return petStoreRegistrationPageHelper;}
    public PetStoreCheckoutPageHelper getPetStoreCheckoutPageHelper(){return petStoreCheckoutPageHelper;}

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }
}
