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

public class AddClass {
WebTester tester;
	
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.setTextField("username", "test");
		tester.setTextField("password", "test");
		tester.submit();
		tester.clickLinkWithExactText("Classes");
}
	
	@Test
	public void page(){
		tester.assertMatch("Manage Classes");
		tester.setWorkingForm("classes");
        tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a> <br  '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("malicious link");
	}

	@Test
	public void page2(){
		tester.assertMatch("Manage Classes");
		tester.setWorkingForm("classes");
        tester.setHiddenField("page2","9'><a href=www.unitn.it>malicious link</a> <br ");
        addSubmitButton("//form[@name='classes']");
        tester.submit();     
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("malicious link");
	}
	@Test
	public void page_FullYear(){
		tester.assertMatch("Manage Classes");
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Class");
		tester.setWorkingForm("addclass");
        tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a> <br  '");
        tester.clickButtonWithText("Full Year");
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("malicious link");
	}
	@Test
	public void page2_FullYear(){
		tester.assertMatch("Manage Classes");
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Class");
		tester.setWorkingForm("addclass");
        tester.setHiddenField("page2","9'><a href=www.unitn.it>malicious link</a> <br ");
        tester.setHiddenField("fullyear","1");
        addSubmitButton("//form[@name='addclass']");
        tester.submit();     
        tester.assertMatch("Add New Class");
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

