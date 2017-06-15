package tests.testFileTool;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.FileTool;
import main.Participant;


/**
 * The class tests the readFrom method of FileTool class
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class testReadFrom {
	ArrayList<Participant> res;
	
	@Before
	public void setUp() throws Exception {
		res = new ArrayList<Participant>();
	}

	@Test
	public void testReadFrom1() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile1.txt");
		assertTrue(res.size()==0);
	}
	
	@Test
	public void testReadFrom2() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile2.txt");
		assertTrue(res.size()==0);
	}

	@Test
	public void testReadFrom3() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile3.txt");
		assertTrue(res.size()==0);
	}

	
	@Test
	public void testReadFrom4() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile4.txt");
		assertTrue(res.size()==1);
	}

	@Test
	public void testReadFrom5() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile5.txt");
		assertTrue(res.size()==2);
	}

	@Test
	public void testReadFrom6() {
		res = FileTool.readFrom("src/tests/testFileTool/testFile6.txt");
		assertTrue(res.size()==2);
	}


}
