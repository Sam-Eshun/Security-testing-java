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


public class AddParent {
WebTester tester;
	
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
		tester.clickLinkWithExactText("Parents");

}


@Test
public void page(){
	tester.assertMatch("Manage Parents");
	tester.setWorkingForm("parents");
    tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a> <br  '");
    tester.clickButtonWithText("Add");
    tester.assertMatch("Add New Parent");
    tester.assertLinkNotPresentWithText("malicious link");
}

@Test
public void page2(){
	tester.assertMatch("Manage Parents");
	tester.setWorkingForm("parents");
    tester.setHiddenField("page2","23'><a href=www.unitn.it>malicious link</a> <br ");
    addSubmitButton("//form[@name='parents']");
    tester.submit();     
    tester.assertMatch("Add New Parent");
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
