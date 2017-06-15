package tests.testOfficial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the declareTheFirst method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testDeclareTheFirst {
	Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test
	public void testDeclareTheFirst1() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheFirst());
	}

	@Test
	public void testDeclareTheFirst2() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(120.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheFirst());
	}
	
	@Test
	public void testDeclareTheFirst3() {
		official.readResult(110.0);
		official.readResult(105.0);
		official.readResult(100.0);
		official.summarizeResults();
		
		assertEquals(2,official.declareTheFirst());
	}
	
	@Test
	public void testDeclareTheFirst4() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(90.0);
		official.summarizeResults();
		
		assertEquals(3,official.declareTheFirst());
	}
	
	@Test
	public void testDeclareTheFirst5() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(50.0);
		official.readResult(125.0);
		official.readResult(130.0);
		official.readResult(135.0);
		official.readResult(144.0);
		official.readResult(150.0);
		official.summarizeResults();
		
		assertEquals(3,official.declareTheFirst());
	}
	
	@Test
	public void testDeclareTheFirst6() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(50.0);
		official.readResult(125.0);
		official.readResult(130.0);
		official.readResult(135.0);
		official.readResult(144.0);
		official.readResult(150.0);
		official.readResult(10.0);
		official.summarizeResults();
		
		assertEquals(9,official.declareTheFirst());
	}
}
