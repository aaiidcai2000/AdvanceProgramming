package tests.testSprinter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Sprinter;

/**
 * The class tests the compete method of Sprinter class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testCompete {

	Sprinter Sprinter;
	
	@Before
	public void setUp() throws Exception {
		Sprinter = new Sprinter("sw01","Sprinter" ,"Adam", 18,"NSD");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompete1() {
		Sprinter.compete("Cycling");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCompete2() {
		Sprinter.compete("Swimming");
	}
	
	@Test
	public void testCompete3() {
		double result = Sprinter.compete("Running");
		assertTrue(result>=10);
		assertTrue(result<=20);
	}
	
	@Test
	public void testCompete4() {
		Sprinter.compete("Running");
		double result = Sprinter.compete("Running");
		assertTrue(result>=10);
		assertTrue(result<=20);
	}
	
	@Test
	public void testCompete5() {
		double min=10000000;
		for(int i=0;i<20;++i){
			double time = Sprinter.compete("Running");
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
			double time = Sprinter.compete("Running");
			if(time>max){
				max=time;
			}
		}
		assertTrue(max<=20);
	}

}
