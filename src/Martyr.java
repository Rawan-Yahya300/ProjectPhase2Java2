

public class Martyr extends Person  implements Cloneable {
private String DateOfMartyrdom;
private String CauseOfDeath;
private String PlaceOfDeath;

 public Martyr() {
	 
 }

public Martyr(String ID, String Name, int Age, String Gender, String Address, String ContactInfo,String DateOfMartyrdom,String CauseOfDeath,String PlaceOfDeath) {
	super(ID, Name, Age, Gender, Address, ContactInfo);
	    this.DateOfMartyrdom=DateOfMartyrdom;
	    this.CauseOfDeath=CauseOfDeath;
	    this.PlaceOfDeath=PlaceOfDeath;
	    
}

public String getDateOfMartyrdom() {
	
	return DateOfMartyrdom;
}

public void setDateOfMartyrdom(String DateOfMartyrdom) {
	this.DateOfMartyrdom = DateOfMartyrdom;
}

public String getCauseOfDeath() {
	return CauseOfDeath;
}

public void setCauseOfDeath(String CauseOfDeath) {
	this.CauseOfDeath = CauseOfDeath;
}

public String getPlaceOfDeath() {
	return PlaceOfDeath;
}

public void setPlaceOfDeath(String PlaceOfDeath) {
	this.PlaceOfDeath = PlaceOfDeath;
}

@Override
public String toString() {
	  return " ID:"+getID()+"  Name:"+getName()+"  Age:"+getAge()+"  Gender:"+getGender()+"  Address:"+getAddress()+"  ContactInfo:"+getContactInfo()+"  DateOfMartyrdom:"+DateOfMartyrdom+"  CauseOfDeath:"+CauseOfDeath+"  PlaceOfDeath:"+PlaceOfDeath;
}
  
@Override
public Object clone() {
	try {
		return super.clone();
	}catch(CloneNotSupportedException ex) {
		System.out.println(ex.getMessage());
	}
	return null;
}
}
