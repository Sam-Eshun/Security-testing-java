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
public class ManageAnnouncement {
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
		tester.assertMatch("Manage Classes");
		tester.setWorkingForm("admin");
		tester.setHiddenField("page", "1'> <a href=www.unitn.it>malicious link</a> <br  '");
		tester.clickLinkWithExactText("Announcements");
		tester.assertMatch("Manage Announcements");
		tester.assertLinkNotPresentWithText("malicious link");
	}
		@Test
		public void page2(){
			tester.assertMatch("Manage Classes");
			//tester.setWorkingForm("admin");
	        tester.setHiddenField("page2","4'><a href=www.unitn.it>malicious link</a> <br ");
	        addSubmitButton("//form[@name='admin']");
	        tester.submit();     
	        tester.assertMatch("Manage Announcements");
	        tester.assertLinkNotPresentWithText("malicious link");
		}
		
		@Test
		public void onpage(){
			tester.assertMatch("Manage Classes");
			tester.clickLinkWithExactText("Announcements");
			tester.assertMatch("Manage Announcements");
			tester.setHiddenField("onpage","1'><a href=www.unitn.it>malicious link</a> <br ");
			tester.setWorkingForm("announcements");
			  addSubmitButton("//form[@name='announcements']");
	        tester.submit();     
	        tester.assertMatch("Manage Announcements");
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
		