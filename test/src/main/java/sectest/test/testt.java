package sectest.test;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;

public class testt {
	private WebTester tester;
	private String previousValue;
	
	@Before
	public void prepare() {
	 tester = new WebTester();
	 tester.setBaseUrl("http://localhost/schoolmate");
		
	
	}

	@Test
	public void test(){
		tester.beginAt("/index.php");
		
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
		tester.assertTitleEquals("SchoolMate - School Name");
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		
		previousValue = tester.getElementByXPath("html//textarea[@name='sitetext']").getTextContent();
		
		tester.setTextField("sitetext", "original message <a href=http://unitn.it>malicious link</a>");
		tester.clickButtonWithText(" Update ");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		tester.assertLinkNotPresentWithText("malicious link");
		
		
		
		
	}
	@After
	public void cleanUp(){
		if (previousValue!=null){
			tester.beginAt("/index.php");
			
			tester.setTextField("username", "test");
			tester.setTextField("password", "test");
			tester.submit();
			tester.assertTitleEquals("SchoolMate - School Name");
			tester.clickLinkWithText("School");
			tester.assertMatch("Manage School Information");
			previousValue = tester.getElementByXPath("html//textarea[@name='sitetext']").getTextContent();
			tester.setTextField("sitetext", previousValue);
			tester.clickButtonWithText(" Update ");
			
			
		}
 
	}
	
}
