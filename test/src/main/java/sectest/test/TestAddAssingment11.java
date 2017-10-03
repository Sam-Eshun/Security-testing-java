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



public class TestAddAssingment11 {
	WebTester tester;
	
	@Before
	public void prepare() {
		
		
	 tester = new WebTester();
	 tester.setBaseUrl("http://localhost/schoolmate");
	 tester.beginAt("/index.php");
		
		tester.setTextField("username", "Professor");
		tester.setTextField("password", "professor");
		tester.submit();	
	
	}
	@Test
	public void mytest(){
       
		
		tester.assertMatch("sammy Nana's Classes");
		tester.clickLinkWithText("Sec test");
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		tester.setTextField("page2", "4 '><a href=\"unitn.it\">malicious link</a><br '");
		//tester.clickButtonWithText("Add");
		addSubmitButton("//form[@name='assignments']");
		tester.submit();
		tester.assertLinkNotPresentWithText("malicious link");
		
	}
		
		
	@Test
	public void page(){
      
		
		tester.assertMatch("sammy Nana's Classes");
		tester.clickLinkWithText("Sec test");
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
        tester.setHiddenField("page","2'> <a href=www.unitn.it>malicious link</a> <br  '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
	}

	@Test
	public void selectclass(){
       
		
		tester.assertMatch("sammy Nana's Classes");
		tester.clickLinkWithText("Sec test");
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		tester.setHiddenField("selectclass", "1'><a href=www.unitn.it>malicious link</a><br  '");
		tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Assignment");
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
