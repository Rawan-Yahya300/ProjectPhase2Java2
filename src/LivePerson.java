
public class LivePerson extends Person implements Cloneable {

	public LivePerson() {
		super();
	
	}

	public LivePerson(String ID, String Name, int Age, String Gender, String Address, String ContactInfo) {
		super(ID, Name, Age, Gender, Address, ContactInfo);
		
	}
	@Override
   public String toString() {
	   return " ID:"+getID()+"  Name:"+getName()+"  Age:"+getAge()+"  Gender:"+getGender()+"  Address:"+getAddress()+"  ContactInfo:"+getContactInfo();
   }
	@Override
	protected Object clone() {
		try {
			return super.clone();
		}catch(CloneNotSupportedException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
