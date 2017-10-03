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
public class Loginn {
	WebTester tester;
	
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
		
}
	@Test
	public void page(){
        tester.setHiddenField("page","1'><a href=www.unitn.it>malicious link</a><br  '");
        addSubmitButton("//form[@name='admin']");
        tester.submit();//("submit"); 
        tester.assertLinkNotPresentWithText("malicious link");
	}
	

@Test
public void message(){
 tester.assertMatch("Manage Classes");
 tester.clickLinkWithText("School");
  
 tester.assertMatch("Manage School Information");
  
 tester.getElementByXPath("html//textarea[@name='sitemessage']").getTextContent();
  
 tester.setTextField("sitemessage", "original message <a href=http://unitn.it>malicious link</a>");
 tester.clickButtonWithText(" Update ");
  
 tester.clickLinkWithText("Log Out");
  
 tester.assertMatch("Today's Message");
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