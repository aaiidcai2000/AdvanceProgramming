
public class Participant {
	private String ID;
	private String name;
	private String state;
	private int age;
	
	public Participant(String _ID,String _name,String _state,int _age){
		ID=_ID;
		name=_name;
		state=_state;
		age=_age;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
