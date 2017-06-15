package main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The class record the basic information of the participants in the Ozlympic game,
 * including their ID,name,nationality and age.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public abstract class Participant {
	
	private SimpleStringProperty ID;
	private SimpleStringProperty type;
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty state;
	
	/**
	 * initialize Participant with ID,name,nationality,age
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public Participant(String _ID, String _type, String _name, Integer _age, String _state){
		ID = new SimpleStringProperty(_ID);
		type = new SimpleStringProperty(_type);
		name = new SimpleStringProperty(_name);
		age = new SimpleIntegerProperty(_age);
		state = new SimpleStringProperty(_state);
	}
	
	public String getID() {
		return ID.get();
	}
	public String getType() {
		return type.get();
	}
	public String getName() {
		return name.get();
	}
	public int getAge() {
		return age.get();
	}
	public String getState() {
		return state.get();
	}
	
}
