/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensinosaucelabs;

import static ensinosaucelabs.AutomacaoEmulador.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author lucas.sousa
 */
public class AutomacaoEmulador {
    //Contexto de webview do aplicativo, caso seja necessário:
    public static String CONTEXT = "WEBVIEW_com.accenture.portalcliente";
    //Forma de comunicação com o S.O a ser usado
    public static AndroidDriver<AndroidElement> driver;
    //ou //public static IOSDriver<IOSElement> driver_ios;
    //username no saucelabs
    public static final String USERNAME = "l.mauricio.de.sousa";
    //token de acesso, disponivel dentro das suas configurações no saucelabs
    public static final String ACCESS_KEY = "token de acesso";
    //url de conexão com o SauceLabs
    public static String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    //Caminho até o aplicativo na nuvem usando repositório de armazenamento no saucelabs
    public static String CAMINHO_APP = "sauce-storage:santander_cliente.apk";
    
   public void testeLocal() throws InterruptedException, MalformedURLException{
       
        DesiredCapabilities capacidade = DesiredCapabilities.android();
        // Configura o tipo de plataforma em que sera automatizado: // MobilePlatform.IOS
        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //define o nome do device
        capacidade.setCapability("deviceName", "Android Emulator");
        //define a versão do S.O
        capacidade.setCapability("platformVersion", "6.0");
        //necessário deixar em branco esse valor
        capacidade.setCapability("browserName", "");
        //define a orientação em modo portrait
        capacidade.setCapability("deviceOrientation", "portrait");
        //Passa a localização do App a ser instalado:
        capacidade.setCapability("app", CAMINHO_APP);
        driver = new AndroidDriver<AndroidElement>(new URL(URL), capacidade);
        // ou //driver_ios = new IOSDriver<IOSElement>(new URL(URL), capacidade);
        //define o contexto do driver// Nativo ou Webview por exemplo
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
