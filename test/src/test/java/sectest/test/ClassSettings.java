package sectest.test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.sourceforge.jwebunit.junit.WebTester;
public class ClassSettings {
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
	tester.setWorkingForm("classes");
    tester.setHiddenField("page","2'> <a href=www.unitn.it>malicious link</a> <br  '");
    tester.clickButtonWithText("Update");
    tester.assertMatch("Class Settings");
    tester.assertLinkNotPresentWithText("malicious link");
}
@Test
public void page2(){
	tester.assertMatch("Class Settings");
	tester.setWorkingForm("classes");
    tester.setHiddenField("page2","1'><a href=www.unitn.it>malicious link</a><br ");
    tester.clickButtonWithText("Update");        
    tester.assertMatch("Class Settings");
    tester.assertLinkNotPresentWithText("malicious link");
}
@Test
public void selectclass(){
	tester.assertMatch("Class Settings");
	tester.setWorkingForm("classes");
    tester.setHiddenField("selectclass","1 -- ''><a href=www.unitn.it>malicious link</a> <br");//double quote to escape sql
    tester.clickButtonWithText("Update");        
    tester.assertMatch("Class Settings");
    tester.assertLinkNotPresentWithText("malicious link");
}
@After
public void restore(){
	
}
}
