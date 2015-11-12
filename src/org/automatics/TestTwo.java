package org.automatics;

import org.junit.Test;

public class TestTwo {
	
	@Test
	public void scenario() throws Exception{
		CreatePageClass.getPageClassFileFromUrl("http://www.yahoo.com","Yahoo");
	}
}
