package main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class handles all the file input and output requirements.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class FileTool {
	
	/**
	 * Read the file content from fileName 
	 * 
	 * @param fileName
	 * 		  Name of file to read from
	 * @return ArrayList<Participant>
	 */
	public static ArrayList<Participant> readFrom(String fileName){
		
		ArrayList<Participant> res=new ArrayList<Participant>();
		HashSet<String> uniID= new HashSet<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String next = input.readLine();
			
			//each athlete	
			while(next!=null){
				
				Pattern p = Pattern.compile("^[0-9A-Za-z]+, [0-9A-Za-z]+, [0-9A-Za-z]+, [0-9A-Za-z]+, [a-zA-Z]+ ?$");
				 Matcher m = p.matcher(next);
				 
				 //valid format
				 if (m.find()){
					 String[] datas = next.split(", ");
					 String ID = datas[0];
					 String type = datas[1];
					 String name= datas[2];
					 Integer age= Integer.parseInt(datas[3]);
					 String state=datas[4];
					 
					 //no repeat ID
					 if(uniID.add(ID)){
						 if( type.equals("super") ) {
						 	res.add(new SuperAthlete(ID, type, name, age, state));
						 }  else if (type.equals("swimmer")) {
							res.add(new Swimmer(ID, type, name, age, state));
						 } else if (type.equals("cyclist")) {
							res.add(new Cyclist(ID, type, name, age, state));
						 } else if (type.equals("sprinter")) {
							res.add(new Sprinter(ID, type, name, age, state));
						 } else if (type.equals("officer")) {
							res.add(new Official(ID, type, name, age, state));
						 } else {
							System.err.println("not match any accepted types");
						 }			
					 }else{
						 System.out.println("repeat participant");
					 }
					 
					 
				 }else{
				     System.out.println("illigeal format");
				 }
				 
				next = input.readLine();
			}
			input.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(uniID.size()+" participants in total.");
		return res;
		
	} 
	
	/**
	 * Write data to fileName ,could choose to 
	 * append to the file or overwrite the file
	 * 
	 * @param fileName
	 * @param data
	 * @param append 
	 *        append to the file or not
	 * @return true or false
	 * 		   indicate writing successfully or not
	 * @throws IOException
	 */
	public static boolean write(String fileName, String data, boolean append) throws IOException {
		
        File file = new File(fileName);
        try{
            BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append)));
            bufWriter.write(data);
            bufWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(fileName + " writing error");
            return false;
        }
        return true;
    }
	

}
