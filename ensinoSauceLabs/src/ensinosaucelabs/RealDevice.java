/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensinosaucelabs;

import static ensinosaucelabs.AutomacaoEmulador.CAMINHO_APP;
import static ensinosaucelabs.AutomacaoEmulador.CONTEXT;
import static ensinosaucelabs.AutomacaoEmulador.driver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author lucas.sousa
 */
public class RealDevice {

    public static String URL = "https://us1.appium.testobject.com/wd/hub";

    public void testeReal() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capacidade = DesiredCapabilities.android();
        capacidade.setCapability("platformName", "Android");
        capacidade.setCapability("deviceName", "LG Nexus 5X");
        capacidade.setCapability("testobject_api_key", "340BC9F1498C4BA58FE839C3605D9945");
        capacidade.setCapability("phoneOnly", "false");
        capacidade.setCapability("tabletOnly", "false");
        capacidade.setCapability("privateDevicesOnly", "false");
        capacidade.setCapability("platformVersion", "7");
        driver = new AndroidDriver<AndroidElement>(new URL(URL), capacidade);
        driver.context(CONTEXT);
        //thread sleep adicionado para dar tempo dos elementos do app carregarem:
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        /*encontrar elemento por xpath é uma das formas de se encontrar 
        os elementos a serem interagidos na automação      */
        driver.findElement(By.xpath("//*[@id=\"menuPainelMobile\"]/span")).click();
        //driver.findElement(By.id("menuPainelMobile")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("cpfCnpj")).sendKeys("12345678910");
    }
}
