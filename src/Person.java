
public abstract class Person  {   
  private String ID;
  private String Name;
  private int Age;
  private String Gender;
  private String Address;
  private String ContactInfo;
    public Person() {
    	 
     }
	public Person(String ID, String Name, int Age, String Gender, String Address, String ContactInfo) {
		this.ID=ID;
		this.Name=Name;
		this.Age=Age;
		this.Gender=Gender;
		this.Address=Address;
		this.ContactInfo=ContactInfo;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name=Name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int Age) {
		this.Age=Age;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String Gender) {
		this.Gender=Gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address=Address;
	}
	public String getContactInfo() {
		return ContactInfo;
	}
	public void setContactInfo(String ContactInfo) {
		this.ContactInfo = ContactInfo;
	}
	@Override
     public abstract String toString() ;
	
	
  }
