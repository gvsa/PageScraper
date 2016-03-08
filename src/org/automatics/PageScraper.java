package org.automatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageScraper {

	/**
	 * This method scrapes a given target page and returns a map in the form
	 * <String (html element tagname), List<WebElement> for all such tags found
	 * on the page
	 * 
	 * @param URL - the target page that needs to be scraped
	 * @param w - the webdriver instance
	 * @return -  Map <String,List<WebElement>>
	 */
	public static Map<String, List<WebElement>> getElementsMap(String URL, WebDriver w) {
		Map<String, List<WebElement>> elMap = new HashMap<String, List<WebElement>>();

		List<String> elementNames = Elements.getElementNames(w);
		// System.out.println("after dupe-removal = " + elementNames.size());
		List<WebElement> list = new ArrayList<WebElement>();

		w.get(URL);
		System.out.println("printing text for each");
		// now build elementwise list and add to map
		for (String tag : elementNames) {
			list = w.findElements(By.cssSelector(tag));
			// System.out.println("found " + list.size() + " for tag: " + tag);
			// for(WebElement x:list){
			// System.out.println(x.getText());
			// }
			elMap.put(tag, list);
		}
		System.out.println("done scraping");

		return elMap;
	}
}
