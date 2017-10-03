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


public class AddStudent {
			private WebTester tester;
			
			
		
			

			@Before
			public void setup(){
				
				 tester = new WebTester();
				 tester.setBaseUrl("http://localhost/schoolmate");
					
		
			
				tester.beginAt("index.php");
				tester.setTextField("username", "test");
				tester.setTextField("password", "test");
				tester.submit();
				tester.clickLinkWithExactText("Students");
			}
			
			@Test
			public void page(){
				tester.assertMatch("Manage Students");
				tester.setWorkingForm("students");
		        tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a> <br  '");
		        tester.clickButtonWithText("Add");
		        tester.assertMatch("Add New Student");
		        tester.assertLinkNotPresentWithText("malicious link");
			}

			@Test
			public void page2(){
				tester.assertMatch("Manage Students");
				tester.setWorkingForm("students");
		        tester.setHiddenField("page2","20'><a href=www.unitn.it>malicious link</a> <br ");
		        addSubmitButton("//form[@name='students']");
		        tester.submit();     
		        tester.assertMatch("Add New Student");
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
