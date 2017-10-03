package sectest.test;
import java.sql.SQLException;

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
import sectest.test.util.StoredAttackSchoolinfoUtility;
public class ManageAssignment {
WebTester tester;
	
	@Before
	public void prepare() {
		
		
		 tester = new WebTester();
		 tester.setBaseUrl("http://localhost/schoolmate");
			

	
		tester.beginAt("index.php");
		tester.setTextField("username", "Professor");
		tester.setTextField("password", "professor");
		tester.submit();
		tester.assertMatch("sammy Nana's Classes");
		tester.clickLinkWithText("Sec test");
		tester.assertMatch("Class Settings");
}
	@Test
	public void page(){
		tester.assertMatch("Class Settings");
		tester.setWorkingForm("teacher");
		tester.setHiddenField("page", "2'> <a href=www.unitn.it>malicious link</a> <br  '");
		tester.clickLinkWithExactText("Assignments");
		tester.assertMatch("Manage Assignments");
		tester.assertLinkNotPresentWithText("malicious link");
	}
	@Test
	public void onpage(){
		tester.assertMatch("Class Settings");
		tester.clickLinkWithExactText("Assignments");
		tester.assertMatch("Manage Assignments");
		tester.setHiddenField("onpage","1'><a href=www.unitn.it>malicious link</a> <br ");
		tester.setWorkingForm("assignments");
		 addSubmitButton("//form[@name='assignments']");
        tester.submit();     
        tester.assertMatch("Manage Assignments");
        tester.assertLinkNotPresentWithText("malicious link");
      
	
	}
	
	

	@Test
	public void page2(){
		tester.assertMatch("Class Settings");
		tester.setWorkingForm("teacher");
        tester.setHiddenField("page2","2'><a href=www.unitn.it>malicious link</a> <br ");
        addSubmitButton("//form[@name='teacher']");
        tester.submit();     
        tester.assertMatch("Manage Assignments");
        tester.assertLinkNotPresentWithText("malicious link");
	}
	
	
	
	
	@Test
	public void selectclass(){
		tester.assertMatch("Class Settings");
		tester.setWorkingForm("teacher");
		tester.setHiddenField("selectclass", "1' -- '> <a href=www.unitn.it>malicious link</a> <br  '");
		tester.clickLinkWithExactText("Assignments");
		tester.assertMatch("Manage Assignments");
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

		
		
