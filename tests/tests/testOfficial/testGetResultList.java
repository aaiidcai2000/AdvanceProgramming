package tests.testOfficial;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the getResultList method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testGetResultList {

	Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test
	public void testGetResultList1() {
		Map<Double,Integer> list = official.getResultList();
		assertEquals(0,list.size());
	}
	
	@Test
	public void testGetResultList2() {
		official.readResult(100);
		
		Map<Double,Integer> list = official.getResultList();
		assertEquals(1,list.size());
	}
	
	@Test
	public void testGetResultList3() {
		official.readResult(100.0);
		
		Map<Double,Integer> list = official.getResultList();
		int resultIndex=list.get(100.0);
		assertEquals( 0, resultIndex );
	}
	
	@Test
	public void testGetResultList4() {
		official.readResult(100.0);
		official.readResult(105.0);
		
		Map<Double,Integer> list = official.getResultList();
		int resultIndex=list.get(100.0);
		assertEquals( 0, resultIndex );
	}
	
	@Test
	public void testGetResultList5() {
		official.readResult(100.0);
		official.readResult(105.0);
		
		Map<Double,Integer> list = official.getResultList();
		int resultIndex=list.get(105.0);
		assertEquals( 1, resultIndex );
	}
	
	@Test
	public void testGetResultList6() {
		official.readResult(100);
		official.readResult(105);
		
		Map<Double,Integer> list = official.getResultList();
		assertEquals(2,list.size());
	}

}
