package selenium01;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import net.bytebuddy.description.modifier.SynchronizationState;

public class GoogleFacebookRegister {
		WebDriver  driver;
		int segundos=3;
		int x =1;
		File src = new File("C:\\Users\\mmarquin\\Desktop\\ExportExcel.xlsx");
		FileInputStream fis = new FileInputStream(src);
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		
		public static void main (String[] args) throws Exception {
			GoogleFacebookRegister ejecutar = new GoogleFacebookRegister();
		}
		
		
		public GoogleFacebookRegister() throws Exception {
			
			while(x<2) 
			{
				System.setProperty("webdriver.gecko.driver", "C:\\\\Selenium\\Webdriver\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.get("http://www.google.com");
				driver.manage().window().maximize();
				PrintScreen();
				
				GoogleSearch();
				RegisterFacebook();
			
				x++;
			}
			
		}
		
		private static Object xpath(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		@Test(expectedExceptions = { IOException.class })
		public void RegisterFacebook() {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement Register;
			Register = driver.findElement(By.linkText("Regístrate en Facebook"));
			PrintScreen();
			Register.click();
			

			String data0 = formatter.formatCellValue(sheet1.getRow(x).getCell(0)); /*firstname*/
			String data1 = formatter.formatCellValue(sheet1.getRow(x).getCell(1)); /*lastname*/
			String data2 = formatter.formatCellValue(sheet1.getRow(x).getCell(2)); /*e-mail*/
			String data3 = formatter.formatCellValue(sheet1.getRow(x).getCell(3)); /*type*/
			String data5 = formatter.formatCellValue(sheet1.getRow(x).getCell(5)); /*day*/
			String data6 = formatter.formatCellValue(sheet1.getRow(x).getCell(6)); /*month*/
			int idata6=  Integer.parseInt(data6);
			String data7 = formatter.formatCellValue(sheet1.getRow(x).getCell(7)); /*year*/
			
			WebElement searchBox = driver.findElement(By.xpath(".//*[@name='firstname']"));
			searchBox.sendKeys(data0);
			PrintScreen();
				
			searchBox = driver.findElement(By.xpath(".//*[@name='lastname']"));
			searchBox.sendKeys(data1);
			PrintScreen();
				
			searchBox = driver.findElement(By.xpath(".//*[@name='reg_email__']"));
			searchBox.sendKeys(data2);
			PrintScreen();
				
			searchBox = driver.findElement(By.xpath(".//*[@name='reg_email_confirmation__']"));
			searchBox.sendKeys(data2);
			PrintScreen();
				
			searchBox = driver.findElement(By.xpath(".//*[@name='reg_passwd__']"));
			searchBox.sendKeys(Password(data0, data5));
			PrintScreen();
				
			Select date = new Select(driver.findElement(By.xpath(".//*[@id='day']")));
			date.selectByVisibleText(data5);
			PrintScreen();
			
			date = new Select(driver.findElement(By.xpath(".//*[@id='month']")));
			date.selectByVisibleText(fecha(idata6));
			PrintScreen();
			
			date = new Select(driver.findElement(By.xpath(".//*[@id='year']")));
			date.selectByVisibleText(data7);
			PrintScreen();			
			
			if (data3.equals("H")) {
				driver.findElement(By.xpath("//input[@id=//label[normalize-space(.)='Hombre']/@for]")).click();
				PrintScreen();
			}else {
				driver.findElement(By.xpath("//input[@id=//label[normalize-space(.)='Mujer']/@for]")).click();
				PrintScreen();
			}
			searchBox.sendKeys();
			searchBox.submit();	

		}
		
		@Test(expectedExceptions = { IOException.class })
		public void GoogleSearch() {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@title='Buscar']")).sendKeys("facebook");
			PrintScreen();
			
			driver.findElement(By.xpath(".//*[@name='btnK']")).click();
			PrintScreen();

			
			driver.findElement(By.linkText("Imágenes")).click();
			PrintScreen();
			
			driver.findElement(By.linkText("Todo")).click();
			PrintScreen();
			
			driver.findElement(By.xpath("//a[@href='https://es-la.facebook.com/login/']")).click();
			PrintScreen();
		}
		
//		@Test
//		public void ObtenerValor(WebElement TxtBoxContent) 
//		{
//			System.out.println("Printing " +
//					TxtBoxContent.getAttribute("innerHTML"));
//			
//			String busq = "facebook";
//			int x = busq.indexOf(TxtBoxContent.getAttribute("innerHTML"));
//			
//			if (x == -1) {
//			    System.out.println("trueee");
//			}
//			else
//				System.out.println("falseeeee");
//		}
		
		@Test
		public void esperarSegundos(){
		       
		      synchronized(driver){
		         try {
		            driver.wait(segundos * 1000);
		         } catch (InterruptedException e) {
		            e.printStackTrace();
		         }
		      }
		   }
		
		public String fecha(int mes) {
			String name="";
			switch(mes) {
				case 1 : name="ene"; break;
				case 2 : name="feb"; break;
				case 3 : name="mar"; break;
				case 4 : name="abr"; break;
				case 5 : name="may"; break;
				case 6 : name="jun"; break;
				case 7 : name="jul"; break;
				case 8 : name="ago"; break;
				case 9 : name="sep"; break;
				case 10 : name="oct"; break;
				case 11 : name="nov"; break;
				default: name="dic";
			}
			return name;
			
		}

		public static String Password(String data0, String data5) {
			String message= data0+"123"+data5;
				
			String textoEncriptadoMD5 = DigestUtils.md5Hex(message);
			return textoEncriptadoMD5;
		}
		
		@Test
		public void PrintScreen(){
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String ruta = "C:\\tmp\\screenshot" + timeStamp + ".png";
			
			try {
				FileUtils.copyFile(scrFile, new File(ruta));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			esperarSegundos();
		}
}


//Select sel1= new Select(driver.findElement(By.xpath(".//*[@id='hdtb-msb']")));
//sel1.selectByVisibleText("Imágenes");
//WebElement searchBox;
//searchBox = driver.findElement(By.id("searchInput"));
//searchBox.sendKeys("Software");
//searchBox.submit();
//WebElement PanelSearch = driver.findElement(By.linkText("Iniciar sesión"));
//PanelSearch.click();
