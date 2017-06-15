package tests.testOfficial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the declareTheSecond method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testDeclareTheSecond {
Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test
	public void testDeclareTheSecond1() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(1,official.declareTheSecond());
	}

	@Test
	public void testDeclareTheSecond2() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(120.0);
		official.summarizeResults();
		
		assertEquals(1,official.declareTheSecond());
	}
	
	@Test
	public void testDeclareTheSecond3() {
		official.readResult(110.0);
		official.readResult(105.0);
		official.readResult(100.0);
		official.summarizeResults();
		
		assertEquals(1,official.declareTheSecond());
	}
	
	@Test
	public void testDeclareTheSecond4() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(90.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheSecond());
	}
	
	@Test
	public void testDeclareTheSecond5() {
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
		
		assertEquals(0,official.declareTheSecond());
	}
	
	@Test
	public void testDeclareTheSecond6() {
		
		official.readResult(105.0);
		official.readResult(110.0);
		official.readResult(50.0);
		official.readResult(125.0);
		official.readResult(130.0);
		official.readResult(135.0);
		official.readResult(144.0);
		official.readResult(150.0);
		official.readResult(10.0);
		official.readResult(20.0);
		official.summarizeResults();
		
		assertEquals(9,official.declareTheSecond());
	}

}
