package org.automatics.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestOne {
	
	@Test
	public void scenario() throws InterruptedException{
		System.out.println("parsing elements list from w3c...");
		
		WebDriver w = new FirefoxDriver();
		
		w.get("http://www.w3.org/TR/html-markup/elements.html");
		List<WebElement> parentList = w.findElements(By.cssSelector("span.element"));
		
		Map<String, List<WebElement>> elMap = new HashMap<String, List<WebElement>>();
		List<String> elementNames = new ArrayList<String>();
		
		for(WebElement x:parentList){
			String elString = x.getText().trim().toLowerCase();
			elementNames.add(elString);
		}
		elementNames=new ArrayList<String>(new HashSet<String>(elementNames));
//		System.out.println("after dupe-removal = " + elementNames.size());
		List<WebElement> list = new ArrayList<WebElement>(); 
		
		w.get("http://www.google.com/");
		System.out.println("printing text for each");
		//now build elementwise list and add to map
		for(String tag:elementNames){
			list = w.findElements(By.cssSelector(tag));
			System.out.println("found " + list.size() + " for tag: " + tag+" text follows next line.");
			for(WebElement x:list){
				System.out.print("||"+x.getText());
			}
			System.out.println();
			elMap.put(tag, list);
		}
		System.out.println("done");
		
		w.close();
	}
}
