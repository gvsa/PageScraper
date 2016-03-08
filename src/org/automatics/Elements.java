package org.automatics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Elements {

	public static List<String> getElementNames(WebDriver w) {
//		WebDriver w = new FirefoxDriver();

		w.get("http://www.w3.org/TR/html-markup/elements.html");
		List<WebElement> parentList = w.findElements(By.cssSelector("span.element"));

		List<String> elementNames = new ArrayList<String>();

		for (WebElement x : parentList) {
			String elString = x.getText().trim().toLowerCase();
			elementNames.add(elString);
		}
		
		//cast elementnames into hashset to remove dupes, retrieve values as arraylist and return
		elementNames = new ArrayList<String>(new HashSet<String>(elementNames));
		// System.out.println("after dupe-removal = " + elementNames.size());
//		w.close();
		
		return elementNames;

	}
}