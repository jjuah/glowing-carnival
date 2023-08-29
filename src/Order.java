import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Order {

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

        Dimension dimension = new Dimension(1700, 1080);
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

        // GNB 마이컬리 클릭
        WebElement gnb_mykurly_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[1]/a/span[1]")));
        gnb_mykurly_click.click();

        // 로그인 name 일치 여부 확인
        Thread.sleep(2000);
        Assert.assertEquals("150주아님", driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div/div[1]/div[1]/div/strong")).getText());
        System.out.println("로그인 성공 - name 일치");

        // 장바구니 아이콘 클릭
        WebElement cart_icon_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div[2]/div[3]/div/div[2]/button")));
        cart_icon_click.click();

        // 장바구니 페이지 > 주문하기 btn 클릭
        WebElement order_btn_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div[2]/div/div[3]/button")));
        order_btn_click.click();

        Thread.sleep(2000);

        // JavascriptExecutor를 사용하여 스크롤 조작
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // 아래로 스크롤 내리기
        jsExecutor.executeScript("window.scrollBy(0, 1000);");

        Thread.sleep(2000);

        // 주문서 > 적립금 > 모두사용 btn 클릭
        WebElement point_use_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div/div/div[7]/div[1]/div[6]/div/div[1]/button")));
        point_use_click.click();

        Thread.sleep(2000);

        jsExecutor.executeScript("window.scrollBy(0, 500);");

        // 주문서 > 결제하기 btn 클릭
        WebElement payment_click = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div/div/div[7]/div[1]/div[9]/button")));
        payment_click.click();

    }

    @Test
    public void afterMethod() throws InterruptedException {
        // driver 종료
        //driver.quit();
    }

}








