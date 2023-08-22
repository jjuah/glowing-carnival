import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class logout {

    private static WebDriver driver;

    @Before
    public void before() throws InterruptedException {

        // 옵션 생성
        ChromeOptions ops = new ChromeOptions();
        // 창 숨기는 옵션 추가
        ops.addArguments("--remote-allow-origins=*");

        // driver 설정
        System.setProperty("webdriver.chrome.driver", "/Users/mk-am14-029/Documents/selenium/chromedriver");
        driver = new ChromeDriver(ops);

        Dimension dimension = new Dimension(1800, 1080);
        driver.manage().window().setSize(dimension);

        // xpath 찾는데 구성 값 찾는데 소요되는 시간 (최대 10초 동안 대기)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // stg 접속
        driver.get("https://www.stg.kurly.com");

        // 로그인 클릭
        WebElement header_login_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/a[2]")));
        header_login_click.click();

        // id 입력
        WebElement id_input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/form/div[1]/div[1]/div/input")));
        id_input.sendKeys("mstest150");

        // pw 입력
        WebElement pw_input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/form/div[1]/div[2]/div/input")));
        pw_input.sendKeys("qwert12345");

        // 로그인 btn 클릭
        WebElement login_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/form/div[3]/button[1]")));
        login_click.click();

        // 상단 GNB 마우스 오버
        WebElement mouse_hover = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[1]/a")));
        Actions actions = new Actions(driver);
        actions.moveToElement(mouse_hover).perform();

        // 로그아웃
        WebElement gnb_logout_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[1]/div/div[13]")));
        gnb_logout_click.click();

        System.out.println("로그아웃 성공");


    }

    @Test
    public void afterMethod() throws InterruptedException {
        // driver 종료
        driver.quit();
    }

}








