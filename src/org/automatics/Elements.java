package org.automatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Elements {

	public static List<String> getElementNames() {
		WebDriver w = new FirefoxDriver();

		w.get("http://www.w3.org/TR/html-markup/elements.html");
		List<WebElement> parentList = w.findElements(By
				.cssSelector("span.element"));

		Map<String, List<WebElement>> elMap = new HashMap<String, List<WebElement>>();
		List<String> elementNames = new ArrayList<String>();

		for (WebElement x : parentList) {
			String elString = x.getText().trim().toLowerCase();
			elementNames.add(elString);
		}
		elementNames = new ArrayList<String>(new HashSet(elementNames));
		// System.out.println("after dupe-removal = " + elementNames.size());
		w.close();
		
		return elementNames;

	}
}