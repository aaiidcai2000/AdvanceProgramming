package tests.testSuperAthlete;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.SuperAthlete;

/**
 * The class tests the compete method of SuperAthlete class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testCompete {

SuperAthlete SuperAthlete;
	
	@Before
	public void setUp() throws Exception {
		SuperAthlete = new SuperAthlete("sw01","SuperAthlete" ,"Adam", 18,"NSD");
	}

	@Test
	public void testCompete1() {
		double result = SuperAthlete.compete("Cycling");
		assertTrue(result>=500);
		assertTrue(result<=800);
	}
	
	@Test
	public void testCompete2() {
		double result = SuperAthlete.compete("Swimming");
		assertTrue(result>=100);
		assertTrue(result<=200);
	}
	
	@Test
	public void testCompete3() {
		double result = SuperAthlete.compete("Running");
		assertTrue(result>=10);
		assertTrue(result<=20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCompete4() {
		 SuperAthlete.compete("Climbing");
	}
	
	@Test
	public void testCompete5() {
		double min=10000000;
		for(int i=0;i<20;++i){
			double time = SuperAthlete.compete("Running");
			if(time<min){
				min=time;
			}
		}
		assertTrue(min>=10);
	}
	
	@Test
	public void testCompete6() {
		double max=0;
		for(int i=0;i<20;++i){
			double time = SuperAthlete.compete("Running");
			if(time>max){
				max=time;
			}
		}
		assertTrue(max<=20);
	}

}
