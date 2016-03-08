package org.automatics;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatePageClass {

	static Map<String, List<WebElement>> elMap = null;
	static List<String> tags = null;
	static List<WebElement> blanks = null;

	public static File getPageClassFileFromUrl(String url,String ClassNameString) throws Exception {
		WebDriver driver = new FirefoxDriver();

		if(elMap==null){
			elMap = PageScraper.getElementsMap(url, driver);
		}
		tags = new ArrayList<String>(elMap.keySet());
		blanks = new ArrayList<WebElement>();

		File f = new File(ClassNameString + ".java");
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(("\npublic class "+ClassNameString+" {").getBytes());
		
		String locatorCode = "";

		// iterate though each input type
		for (String x : tags) {
			// now for each input type, iterate through each element
			for (WebElement el : elMap.get(x)) {
				// now for each element, check attributes (name,id,class) and use
				// the first non-blank
				// else add element to blanks
				locatorCode="";
				boolean flag=false;

				locatorCode = getLocCode(el, "name");
				if (locatorCode.equals("")) {
					locatorCode = getLocCode(el, "id");
				} else if(!flag) {
					//build name based locator
					locatorCode = ("By " + x.toUpperCase() + "_" + locatorCode.toUpperCase() + " = By.cssselector(\"" + x + "[name='" + locatorCode +"']\");");
					flag=true;
				}
				if (!flag && locatorCode.equals("")) {
					locatorCode = getLocCode(el, "class");
				} else if(!flag) {
					//build id based locator
					locatorCode = ("By " + x.toUpperCase() + "_" + locatorCode.toUpperCase() + " = By.cssselector(\"" + x + "#" + locatorCode +"\");");
					flag=true;
				}
				if (locatorCode.equals("")) {
					blanks.add(el);
				}else if(!flag){
					//build class based locator
					locatorCode = ("By " + x.toUpperCase() + "_" + locatorCode.toUpperCase() + " = By.cssselector(\"" + x + "." + locatorCode +"\");");
				}
//				elMap.get(x).remove(el);
//				System.out.println(locatorCode);
				fout.write(("\n" + locatorCode).getBytes());
			}
		}
		fout.write("\n}".getBytes());
		fout.close();
		driver.close();
		return f;
	}

	private static String getLocCode(WebElement el, String attrib){
		String result="";
		String value = el.getAttribute(attrib);
		if(value!=null && !value.equals("")){
//			value.replaceFirst(" ", "\0");
			result = value.trim().toLowerCase(); 
//			System.out.println("result = " + value.toLowerCase());
		}
		return result;
	}
}
