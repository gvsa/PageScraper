package org.automatics.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.automatics.PageScraper;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GatherTest {

	@Test
	public void scenario() {
		WebDriver driver = new FirefoxDriver();
		Map<String, List<WebElement>> lmap = new TreeMap<String,List<WebElement>>(PageScraper.getElementsMap("http://www.redbus.in", driver));
		ArrayList<String> tags = new ArrayList<String>(lmap.keySet());
		File f;
		FileOutputStream fout, indif;
		try {
			f = new File("D:\\GatherTest\\tags.html");
			fout = new FileOutputStream(f);
			fout.write("".getBytes());
			// gather all tags in a single file - called tags.html
			// for each tag, group the occurrences and dump all data into the
			// respective tag_<tagname>.html
			String target = "<html><body>";
			fout.write(target.getBytes());
			target = "";
			for (String tagname : tags) {
				int tagcount=lmap.get(tagname).size();
				target = "\n<br><a href='tag_" + tagname + ".html' target='elframe'>" + tagname + "("
						+  tagcount+ ")</a>";
				fout.write(target.getBytes());
				indif = new FileOutputStream(new File("D:\\GatherTest\\tag_" + tagname + ".html"));
				target = "";
				int ctr = 1;
				if (tagcount == 0) {
					target = "0 elements found for tag " + tagname;
				} else {
					for (WebElement curel : lmap.get(tagname)) {
						target += ctr + "\n";
						target += "\n<br>tag=" + curel.getTagName() + "<br>name=" + curel.getAttribute("name")
								+ "<br>id=" + curel.getAttribute("id") + "<br>class(es)=" + curel.getAttribute("class")
								+ "\n<br>Text=" + curel.getText() + "<br><br>\n\n";
						ctr++;
					}
				}
				indif.write(target.getBytes());
				indif.close();
			}
			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error in file init - tags.html! Aborting...");
			return;
		} catch (IOException e) {
			System.out.println("Error in file write to tags.html! Aborting...");
			return;
		} catch (StaleElementReferenceException e) {
			System.out.println("something went wrong while reading tags! Aborting...");
			return;
		}
		driver.close();
	}

}
