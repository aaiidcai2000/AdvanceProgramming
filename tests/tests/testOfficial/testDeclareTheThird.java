package tests.testOfficial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the declareTheThird method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testDeclareTheThird {
Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test
	public void testDeclareTheThird1() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(2,official.declareTheThird());
	}

	@Test
	public void testDeclareTheThird2() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(120.0);
		official.summarizeResults();
		
		assertEquals(2,official.declareTheThird());
	}
	
	@Test
	public void testDeclareTheThird3() {
		official.readResult(110.0);
		official.readResult(105.0);
		official.readResult(100.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheThird());
	}
	
	@Test
	public void testDeclareTheThird4() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(90.0);
		official.summarizeResults();
		
		assertEquals(1,official.declareTheThird());
	}
	
	@Test
	public void testDeclareTheThird5() {
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
		
		assertEquals(1,official.declareTheThird());
	}
	
	@Test
	public void testDeclareTheThird6() {
		
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(50.0);
		official.readResult(125.0);
		official.readResult(130.0);
		official.readResult(135.0);
		official.readResult(144.0);
		official.readResult(150.0);
		official.readResult(10.0);
		official.readResult(100.0);
		official.summarizeResults();
		
		assertEquals(9,official.declareTheThird());
	}

}
