package tests.testOfficial;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the summarizeResults method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testSummarizeResults {

	Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test (expected = NoSuchElementException.class)
	public void testSummarizeResults1() {
		official.summarizeResults();
	}
	
	@Test
	public void testSummarizeResults2() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheFirst());
	}
	
	@Test
	public void testSummarizeResults3() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(1,official.declareTheSecond());
	}
	
	@Test
	public void testSummarizeResults4() {
		official.readResult(100.0);
		official.readResult(105.0);
		official.readResult(110.0);
		official.summarizeResults();
		
		assertEquals(2,official.declareTheThird());
	}
	
	@Test
	public void testSummarizeResults5() {
		official.readResult(110.0);
		official.readResult(105.0);
		official.readResult(100.0);
		official.summarizeResults();
		
		assertEquals(0,official.declareTheThird());
	}
	
	@Test
	public void testSummarizeResults6() {
		official.readResult(110.0);
		official.readResult(105.0);
		official.readResult(100.0);
		official.readResult(20.0);
		official.readResult(40.0);
		official.readResult(60.0);
		official.readResult(80.0);
		official.summarizeResults();
		
		assertEquals(5,official.declareTheThird());
	}

}
