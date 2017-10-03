package sectest.test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;
public class Login {
WebTester tester;
String previousValue;
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.beginAt("index.php");
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
}
	@Test
	public void sitetext(){
	 tester.assertMatch("Manage Classes");
     tester.clickLinkWithText("School");
      
     tester.assertMatch("Manage School Information");
      
     tester.getElementByXPath("html//textarea[@name='sitetext']").getTextContent();
      
     tester.setTextField("sitetext", "original message <a href=http://unitn.it>malicious link</a>");
     tester.clickButtonWithText(" Update ");
      
     tester.clickLinkWithText("Log Out");
      
     tester.assertMatch("Today's Message");
     tester.assertLinkNotPresentWithText("malicious link");
	}
	
	@After
	public void restore(){
		
	}
}
