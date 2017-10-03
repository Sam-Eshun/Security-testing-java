package sectest.test;
import sectest.test.util.StoredAttackSchoolinfoUtility;
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


public class ManageSchoolInfo {
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
		tester.clickLinkWithExactText("School");
		tester.assertMatch("Manage School Information");
		tester.assertLinkNotPresentWithText("malicious link");
	}
	@Test
	public void page2(){
		tester.assertMatch("Manage Classes");
		tester.setWorkingForm("admin");
        tester.setHiddenField("page2","1'><a href=www.unitn.it>malicious link</a> <br ");
        addSubmitButton("//form[@name='admin']");
        tester.submit();     
        tester.assertMatch("Manage School Information");
        tester.assertLinkNotPresentWithText("malicious link");
	}
	
	@Test
	public void phone() throws SQLException{
		tester.assertMatch("Manage Classes");
		//set phone in dub as a malicious link
		StoredAttackSchoolinfoUtility sacu = new StoredAttackSchoolinfoUtility();
		sacu.createSchoolPhone("''><a>link1</a>");
		tester.clickLinkWithExactText("School");
        tester.assertMatch("Manage School Information");
        tester.assertLinkNotPresentWithText("link1");
	}
	public class address{
	    String previousValue;
	    @Before
	    public void prepare() {
	      
	    }
	     
	    @Test
	    public void test_page() {
	       
	        tester.assertMatch("Manage Classes");
	        tester.clickLinkWithText("School");
	         
	        tester.assertMatch("Manage School Information");
	 
	         
	        tester.setWorkingForm("info");
	        previousValue = tester.getElementAttributeByXPath("html//input[@name='schooladdress']", "value");
	         
	        //tester.setTextField("message", previousValue+" <a href=http://unitn.it>malicious link</a>");
	        tester.setTextField("schooladdress", previousValue+"\\\\\\'> <a href=#>link</a><\\\\\\'");
	        tester.clickButtonWithText(" Update ");
	 
	        tester.assertMatch("Manage School Information");
	        tester.assertLinkNotPresentWithText("link");
	
	    } 
	

	}
	@Test
	public void sitetext() {
		tester.assertMatch("Manage Classes");
		//set phone in dub as a malicious link
		StoredAttackSchoolinfoUtility sacu = new StoredAttackSchoolinfoUtility();
		sacu.createSchoolText("</textarea><a href=ww.unitn.it>malicious link2</a>");
		tester.clickLinkWithExactText("School");
        tester.assertMatch("Manage School Information");
        tester.assertLinkNotPresentWithText("malicious link2");
	
	}
	@Test
	public void sitemessage() {
		tester.assertMatch("Manage Classes");
		//set phone in db as a malicious link
		StoredAttackSchoolinfoUtility sacu = new StoredAttackSchoolinfoUtility();
		sacu.createSchoolMessage("</textarea><a href=ww.unitn.it>malicious link1</a>");
		tester.clickLinkWithExactText("School");
        tester.assertMatch("Manage School Information");
        tester.assertLinkNotPresentWithText("malicious link1");
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

	


