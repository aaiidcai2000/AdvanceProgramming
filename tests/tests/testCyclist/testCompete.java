package tests.testCyclist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Cyclist;

/**
 * The class tests the compete method of Cyclist class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testCompete {

	Cyclist cyclist;
	
	@Before
	public void setUp() throws Exception {
		cyclist = new Cyclist("sw01","cyclist" ,"Adam", 18,"NSD");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompete1() {
		cyclist.compete("Running");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCompete2() {
		cyclist.compete("Swimming");
	}
	
	@Test
	public void testCompete3() {
		double result = cyclist.compete("Cycling");
		assertTrue(result>=500);
		assertTrue(result<=800);
	}
	
	@Test
	public void testCompete4() {
		cyclist.compete("Cycling");
		double result = cyclist.compete("Cycling");
		assertTrue(result>=500);
		assertTrue(result<=800);
	}
	
	@Test
	public void testCompete5() {
		double min=10000000;
		for(int i=0;i<20;++i){
			double time = cyclist.compete("Cycling");
			if(time<min){
				min=time;
			}
		}
		assertTrue(min>=500);
	}
	
	@Test
	public void testCompete6() {
		double max=0;
		for(int i=0;i<20;++i){
			double time = cyclist.compete("Cycling");
			if(time>max){
				max=time;
			}
		}
		assertTrue(max<=800);
	}

}
