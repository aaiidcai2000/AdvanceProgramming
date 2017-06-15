package tests.testSwimmer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Swimmer;

/**
 * The class tests the compete method of Swimmer class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testCompete {

	Swimmer swimmer;
	
	@Before
	public void setUp() throws Exception {
		swimmer = new Swimmer("sw01","Swimmer" ,"Adam", 18,"NSD");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompete1() {
		swimmer.compete("Running");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCompete2() {
		swimmer.compete("Cycling");
	}
	
	@Test
	public void testCompete3() {
		double result = swimmer.compete("Swimming");
		assertTrue(result>=100);
		assertTrue(result<=200);
	}
	
	@Test
	public void testCompete4() {
		swimmer.compete("Swimming");
		double result = swimmer.compete("Swimming");
		assertTrue(result>=100);
		assertTrue(result<=200);
	}
	
	@Test
	public void testCompete5() {
		double min=10000000;
		for(int i=0;i<20;++i){
			double time = swimmer.compete("Swimming");
			if(time<min){
				min=time;
			}
		}
		assertTrue(min>=100);
	}
	
	@Test
	public void testCompete6() {
		double max=0;
		for(int i=0;i<20;++i){
			double time = swimmer.compete("Swimming");
			if(time>max){
				max=time;
			}
		}
		assertTrue(max<=200);
	}
	

}
