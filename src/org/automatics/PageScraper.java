package org.automatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageScraper {
	
	public static Map<String, List<WebElement>> getElementsMap(String URL, WebDriver w){
		Map<String, List<WebElement>> elMap = new HashMap<String, List<WebElement>>();
		
		List<String> elementNames = Elements.getElementNames();
//		System.out.println("after dupe-removal = " + elementNames.size());
		List<WebElement> list = new ArrayList<WebElement>(); 
		
		w.get(URL);
		System.out.println("printing text for each");
		//now build elementwise list and add to map
		for(String tag:elementNames){
			list = w.findElements(By.cssSelector(tag));
//			System.out.println("found " + list.size() + " for tag: " + tag);
//			for(WebElement x:list){
//				System.out.println(x.getText());
//			}
			elMap.put(tag, list);
		}
		System.out.println("done scraping");
		
		return elMap;
	}

}
