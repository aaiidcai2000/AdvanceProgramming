package tests.testOfficial;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import main.Official;

/**
 * The class tests the readResult method of Official class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testReadResult {
	Official official;
	
	@Before
	public void setUp(){
		official=new Official("of01","Official" , "Sherry", 35,"QLD");
	}

	@Test
	public void testReadResult1() {
		official.readResult(100.0);
		
		assertEquals(1,official.getResultList().size());
	}
	
	@Test
	public void testReadResult2() {
		official.readResult(100.0);
		official.readResult(105.0);
		
		assertEquals(2,official.getResultList().size());
		
	}
	
	@Test
	public void testReadResult3() {
		official.readResult(100.0);
		official.readResult(105.0);
		
		Iterator<?> iter = official.getResultList().keySet().iterator();
		assertEquals(100.0,iter.next());
	}
	
	@Test
	public void testReadResult4() {
		
		official.readResult(100.0);
		official.readResult(105.0);
		
		Iterator<?> iter = official.getResultList().keySet().iterator();
		iter.next();
		assertEquals(105.0,iter.next());
	}
	
	@Test
	public void testReadResult5() {
		official.readResult(100.0);
		official.readResult(105.0);
		int index = official.getResultList().get(100.0);
		assertEquals(0,index );
		
	}
	
	@Test
	public void testReadResult6() {
		official.readResult(100.0);
		official.readResult(105.0);
		int index = official.getResultList().get(105.0);
		assertEquals(1,index );
	}

}
