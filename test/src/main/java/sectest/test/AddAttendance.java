package sectest.test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;


public class AddAttendance {
WebTester tester;
	
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
		tester.assertMatch("Manage Classes");
		tester.clickLinkWithExactText("Attendance");
	}
		@Test
		public void page(){
			tester.assertMatch("Attendance");
	        tester.setWorkingForm("registration");
	        tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a> <br  '");
	        tester.clickButtonWithText("Add");
	        tester.assertMatch("Add New Attendance Record");
	        tester.assertLinkNotPresentWithText("malicious link");
		}
		@Test
		public void page2(){
			tester.assertMatch("Attendance");
	        tester.setWorkingForm("registration");
	        tester.setHiddenField("page2","31'><a href=www.unitn.it>malicious link</a> <br ");
	        tester.setHiddenField("addattend","1'><a href=www.unitn.it>malicious link</a> <br ");
	        addSubmitButton("//form[@name='registration']");
	        tester.submit();     
	        tester.assertMatch("Add New Attendance Record");
	        tester.assertLinkNotPresentWithText("malicious link");
		
	     	}
		@Test
		public void semester(){
			tester.assertMatch("Attendance");
	        tester.setWorkingForm("registration");
	        tester.getElementByXPath("//form[@name='registration']/select[@name='semester']/option[@value=1]").setAttribute("value", "1'><a href=www.unitn.it>malicious link</a><br '");
			tester.clickButtonWithText("Add");
			tester.assertMatch("Add New Attendance Record");
	        tester.assertLinkNotPresentWithText("malicious link");
		}
		@Test
		public void student(){
			tester.assertMatch("Attendance");
	        tester.setWorkingForm("registration");
	        tester.getElementByXPath("//form[@name='registration']/select[@name='student']/option[@value=1]").setAttribute("value", "1'><a href=www.unitn.it>malicious link</a><br '");
			tester.clickButtonWithText("Add");
			tester.assertMatch("Add New Attendance Record");
	        tester.assertLinkNotPresentWithText("malicious link");
		}


private void addSubmitButton(String fromXpath){	
 	IElement element = tester.getElementByXPath(fromXpath);
 		DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
 		InputElementFactory factory = InputElementFactory.instance;
 		AttributesImpl attributes = new AttributesImpl();
 		attributes.addAttribute("","","type","","submit");
 		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
 		form.appendChild(submit);
}
		@After
		public void restore(){
		               
		}

}
