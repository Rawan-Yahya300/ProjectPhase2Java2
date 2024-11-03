import java.util.ArrayList;

public class Family implements Sortable,Cloneable {
	private String familyName;
	private ArrayList<Person> members = new ArrayList<>();
	private ArrayList<Person> parents = new ArrayList<>();

	 Family() {

	}

	public Family(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyName() { // getter for family name
		return familyName;
	}
	public void setFamilyName(String familyName) { // setter for family name
		this.familyName=familyName;
	}

	public ArrayList<Person> getMembers() { // getter for members
		return members;
	}

	public ArrayList<Person> getParents() { // getter for parents
		return parents;
	}

	public boolean addMember(Person member, String role) { //add member to family

		for (int i = 0; i < members.size(); i++) {   //search for person using ID and if the the person is exists do not add him
			if (member.getID().equals(members.get(i).getID()))
				return false;
		}
		if (role.equals("son") || role.equals("daughter")) { //if the person does not exist ,check if the member is son or daughter and add to the family
			if(parents.size()<2) { //if there are no two parents throw an exception and don`t add the member
				throw new CanNotAddMemberException("Can not add this member ,There is no parents");
			}
			members.add(member);  //add the member
			return true;
		} else if (role.equals("mom") || role.equals("dad")) {//check if the member is mom or dad and add to the family
              if(parents.size()>=2) { //if there are two parents throw an exception and don`t add more
			   throw new CanNotAddParentException("There are parents , can not add more");
		   }
			members.add(member); //add the member
			boolean isExistInParents = false;   //  and if mom/dad does not exist in the person array , add him/her
			for (int i = 0; i < parents.size(); i++) {
				if (parents.get(i).getID().equals(member.getID())) {
					isExistInParents = true;
				}
			}
			if (isExistInParents == false) {
				parents.add(member);
			}

			return true;

		} else
			return false;

	}

	public boolean removeMember(Person member) {  //to remove member
		for (int i = 0; i < members.size(); i++) { //search for person using ID , if the person exists remove him
			if (member.getID().equals(members.get(i).getID())) {
				members.remove(i);
				for(int j=0; j<parents.size();j++) { //if the person exist in the parent array ,also remove him from it
					if (member.getID().equals(parents.get(j).getID())) {
						parents.remove(j);
					}
				}				
				return true;
			}
		}
		return false;
	}


	public void addParent(Person parent) {  //to add parent
		   if(parents.size()>=2) {  //if there are two parents throw an exception and don`t add more
			   throw new CanNotAddParentException("There are parents , can not add more");
		   }
		   boolean isExist=false;
		   for (int i = 0; i < parents.size(); i++) {   //search for parent using ID and if the the person is exists do not add him
				if (parent.getID().equals(parents.get(i).getID()))
					isExist=true;;
			}
		   
		   if(isExist==false) {   
			   parents.add(parent);
			   System.out.println("The process is done");
			   boolean isExistInMember=false;
				for (int i = 0; i < members.size(); i++) {   //if the parent does not exist on members array , add him to it
					if (parent.getID().equals(members.get(i).getID()))
						isExistInMember=true;
				}
				if(isExistInMember==false) {
					members.add(parent);
		   }
		   }
		   else {
	          System.out.println("can not add , is already exist");
		   }
	}
	public boolean removeParent(Person parent) { //to remove parent
		for (int i = 0; i < parents.size(); i++) {  //search for parent using ID ,if the parent exists remove it
			if (parent.getID().equals(parents.get(i).getID())) {
				parents.remove(i);
				for(int j=0; j<members.size();j++) {  //and if parent exists on members array , remove him from it
					if (parent.getID().equals(members.get(j).getID())) {
						members.remove(j);
					}
				}
				
				return true;
			}
		}
		return false;
	}

	public String toString() { //to put the members of family in string

		String s = "Family name is " + familyName + ", family members:";

		for (int i = 0; i < members.size(); i++) {
			s += " " + members.get(i).getName(); 
		}
		return s;
	}

	public String toStringWithId() { // to put the members of family in string with their ID
		String s = "Family name is " + familyName + ", family members:";

		for (int i = 0; i < members.size(); i++) {
			s += " Name:" + members.get(i).getName() + " ID:" + members.get(i).getID() + " ,";
		}
		return s;
	}

	public boolean equals(Object obj) {// to check if two families have the same number of martyrs
		int martyrOfThisFamily1 = this.calculateNumOfMartyrs();  //calculate number of martyrs for family1
		int martyrOfNewFamily = ((Family)obj).calculateNumOfMartyrs(); //calculate number of martyrs for family2
		return martyrOfThisFamily1 == martyrOfNewFamily; //if they have the same number , return true
	}
	
	
     public int calculateNumOfMartyrs() { //to calculate number of martyr for family
        	    int numOfMartyrs=0;
        	    for(int i=0;i<getMembers().size();i++) {
        	    	if(getMembers().get(i) instanceof Martyr) {
        	    		numOfMartyrs++;
        	    	}
        	    }
        	   return numOfMartyrs;
        	   }
          
      public int calculateNumOfOrphans() {   //to calculate number of martyr for family
        	  int numOfOrphans=0;
        	  int numOfParent=getParents().size(); // number of parents
				int numOfMartyrOfParents=0; //number of the martyr parents in this family
				for(int j=0; j<getParents().size();j++) { //check the members of this family
					if(getParents().get(j) instanceof Martyr) { //if this parent is martyr , increment the counter of martyr person in this family 
						numOfMartyrOfParents++;
					}
				}
				if(numOfParent==numOfMartyrOfParents) { //if the number of parents equals the number of martyr parent,increment the counter of orphans by the number of live person in this family
					 for(int j=0;j<getMembers().size();j++) {
						 if(getMembers().get(j) instanceof LivePerson) {
							 numOfOrphans++;
					  }
					 }
				 }
				return numOfOrphans;
          }
	@Override
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) {    //to sort families by number of martyrs
		ArrayList<Family>  newFamilies =(ArrayList) families.clone();  //enters the families to new array to sort 
		for(int i=0;i<newFamilies.size();i++) {        //copy all families
			newFamilies.set(i, (Family)families.get(i).clone());
		}
		int n=newFamilies.size();        
		 for(int i=0;i<n-1;i++) {      //sort 
			 for(int j=i+1;j<n;j++) {
				 if(newFamilies.get(j).calculateNumOfMartyrs()>newFamilies.get(i).calculateNumOfMartyrs()) {
					 Family temp=newFamilies.get(i);              
					 newFamilies.set(i, newFamilies.get(j));
					 newFamilies.set(j, temp);
				 }
			 }
		 }
		 return newFamilies;
		
	}

	@Override
	public  ArrayList<Family> sortByOrphans(ArrayList<Family> families) {  //to sort families by number of orphans
	
		ArrayList<Family>  newFamilies =(ArrayList) families.clone(); //enters the families to new array to sort 
		for(int i=0;i<newFamilies.size();i++) {  //copy all families
			newFamilies.set(i, (Family)families.get(i).clone());
		}
		int n=newFamilies.size();            //sort
		 for(int i=0;i<n-1;i++) {
			 for(int j=i+1;j<n;j++) {
				 if(newFamilies.get(j).calculateNumOfOrphans()>newFamilies.get(i).calculateNumOfOrphans()) {
					 Family temp=newFamilies.get(i);             
					 newFamilies.set(i, newFamilies.get(j));
					 newFamilies.set(j, temp);
				 }
			 }
		 }
		 return newFamilies;
		
	}
	 protected Object clone() {     //copy the family(deep copy)
		try {
			Family family = (Family)super.clone();     
			family.members=(ArrayList)members.clone();  //copy the array of members
			for(int i=0;i<family.members.size();i++) {       //to cope all members in array
				if(family.members.get(i) instanceof Martyr) {					
					family.members.set(i, (Martyr)(((Martyr)members.get(i)).clone()));                  				
				}
				else if(family.members.get(i) instanceof LivePerson) {
					family.members.set(i, (LivePerson)(((LivePerson)members.get(i)).clone()));  
				}
			}
			family.parents=(ArrayList)parents.clone(); //copy the array of members
			for(int i=0;i<family.parents.size();i++) {        //to cope all members in array
				if(family.parents.get(i) instanceof Martyr) {
					family.parents.set(i, (Martyr)(((Martyr)parents.get(i)).clone()));  
				}
				else if(family.parents.get(i) instanceof LivePerson) {
					family.parents.set(i, (LivePerson)(((LivePerson)parents.get(i)).clone()));  
				}
				
			}
			for(int i=0;i<family.members.size();i++) {   //to set the parent in array parents and in array members have the same address of object
				 for(int j=0;j<family.parents.size();j++) {
					 if(family.parents.get(j).getID().equals(family.members.get(i).getID())) {
							family.parents.set(j, family.members.get(i));
						}
				 }
			}
			return family;
		}catch(CloneNotSupportedException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}  
}
