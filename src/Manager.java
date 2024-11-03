import java.util.ArrayList;

public class Manager {
	private ArrayList<Family> families = new ArrayList<>();

	public boolean addFamily(Family family) {   //to add new family to the families array
		
		for(int i=0; i<families.size();i++) {
			if(families.get(i).getFamilyName().equals(family.getFamilyName())) { //test if the family already exist using family name ,and if it exist do not add it
				    return false;
			}
		}
		families.add(family); // and if it not exist , add it
		
		return true;

	}

	public boolean updateFamily(String familyName, Family updatedFamily) {  //to update a family in the families array with new family
		for (int i = 0; i < families.size(); i++) {     //to search for family if it exist using the family name 
			if (familyName.equals(families.get(i).getFamilyName())) {  //if the family exist update it with new family
				families.set(i,updatedFamily);
				return true;
			}
		}
		return false;    //if the family does not exist return false
	}

	public boolean deleteFamily(String familyName) {  //to delete family from the families array
		for (int i = 0; i < families.size(); i++) {   //to search for family if it exist using the family name
			if (families.get(i).getFamilyName().equals(familyName)) {  //if the family exist delete it from the families array
				families.remove(i);
				return true;
			}
		}
		return false;   //if the family does not exist return false
	}

	public Family searchByName(String familyName) { //to search for family using its name
		for (int i = 0; i < families.size(); i++) {   //to search for family if it exist using the family name
			if (familyName.equals(families.get(i).getFamilyName())) {   //if the family exist return it to the method
				return families.get(i);
			}
		}
		return null;
	}

	public Person searchPersonByID(String personID) {   // search for person using his ID
		for (int i = 0; i < families.size(); i++) {    // We search family by family, then we search among family members  
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				if (families.get(i).getMembers().get(j).getID().equals(personID)) { // if the member exist return it
					return families.get(i).getMembers().get(j);
				}
			}
		}
		return null;
	}

	public int calculateTotalMartyrs() {   // to calculate total martyrs in all families
		ArrayList<Person> allPeople = new ArrayList<>();  //enter all people in all family to array
		for (int i = 0; i < families.size(); i++) {
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				allPeople.add(families.get(i).getMembers().get(j));
			}


		}
		for (int i = 0; i < allPeople.size(); i++) {  // remove duplicate from the array , if the person repeats(dad and son in the same time)

			for (int j = i + 1; j < allPeople.size(); j++) {
				if (allPeople.get(j).getID().equals(allPeople.get(i).getID())) {

					allPeople.remove(j);
				}
			}
		}

		int TotalMartyrs = 0;
		for (int i = 0; i < allPeople.size(); i++) {   // pass on all people in the array
			if (allPeople.get(i) instanceof Martyr) { // if the person is martyr , increment the counter of martyr by 1
				TotalMartyrs++;
			}
		}
		return TotalMartyrs;  //return the total number of martyr
	}

	public int calculateTotalOrphans() {   // to calculate total orphans in all families
		int TotalOrphans = 0;

		for (int i = 0; i < families.size(); i++) {  // pass on all family
			int numOfParent=families.get(i).getParents().size();  //number of parents to this family
			int numOfMartyrOfParents=0; // number of martyr parents in this family
			for(int j=0; j<families.get(i).getParents().size();j++) {  // to find the number of  martyr parents in this family
				if(families.get(i).getParents().get(j) instanceof Martyr) {
					numOfMartyrOfParents++;
				}
			}
			 if(numOfParent==numOfMartyrOfParents) { //if the number of parents to this family equals to the number of  martyr parents in this family....
				 for(int j=0;j<families.get(i).getMembers().size();j++) { // add all live people to number of orphans 
					 if(families.get(i).getMembers().get(j) instanceof LivePerson) {
					  TotalOrphans++;
				  }
				 }
			 }
			 
		}
		return TotalOrphans; //return number of orphans
	}

	public int calculateTotalLivePersons() { // to calculate total live persons 
		ArrayList<Person> allPeople = new ArrayList<>();  //enter all people in all family to array
		for (int i = 0; i < families.size(); i++) {
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				allPeople.add(families.get(i).getMembers().get(j));
			}

		}
		for (int i = 0; i < allPeople.size(); i++) {   // remove duplicate from the array , if the person repeats(dad and son in the same time)

			for (int j = i + 1; j < allPeople.size(); j++) {
				if (allPeople.get(j).getID().equals(allPeople.get(i).getID())) {

					allPeople.remove(j);
				}
			}
		}

		int TotalLivePersons = 0;
		for (int i = 0; i < allPeople.size(); i++) {
			if (allPeople.get(i) instanceof LivePerson) {  // if the person is live person , increment the counter of live person by 1
				TotalLivePersons++;
			}
		}
		return TotalLivePersons; //return the total live person
	}

	public ArrayList<Integer> calculateFamilyStatistics(String familyName){ //to calculate statistics for specified family , take the family name 
	 Integer  numberOfMartyrs =0;
	 Integer  numberOfOrphans =0;
	 Integer  numberOfLivePersons =0;

	 for(int i=0; i<families.size();i++) {  //looking for the family using its name
		 if(familyName.equals(families.get(i).getFamilyName())) {  //if we found the family , check its members
			         for(int j=0; j<families.get(i).getMembers().size();j++) { //
			        	  if(families.get(i).getMembers().get(j) instanceof Martyr) { //if the member is martyr , increment the counter of martyr
			        		  numberOfMartyrs++;
			        		 
			        	  }
			        	  else if(families.get(i).getMembers().get(j) instanceof LivePerson) { //if the member is live person, increment the counter of live person
			        		  numberOfLivePersons++;
			        		 
			        	  }
			         }
	 

			         int numOfParent=families.get(i).getParents().size(); // number of parents
						int numOfMartyrOfParents=0; //number of the martyr parents in this family
						for(int j=0; j<families.get(i).getParents().size();j++) { //check the members of this family
							if(families.get(i).getParents().get(j) instanceof Martyr) { //if this parent is martyr , increment the counter of martyr person in this family 
								numOfMartyrOfParents++;
							}
						}
						 if(numOfParent==numOfMartyrOfParents) { //if the number of parents equals the number of martyr parent,increment the counter of orphans by the number of live person in this family
							 for(int j=0;j<families.get(i).getMembers().size();j++) {
								 if(families.get(i).getMembers().get(j) instanceof LivePerson) {
									 numberOfOrphans++;
							  }
							 }
						 }
		 }
		 
	 }
	 
	 ArrayList <Integer> FamilyStatistics = new ArrayList<>(); //create an array list to takes the statistics with this signature 1-number Of Martyrs 2-number Of Orphans 3-number Of Live Persons
	 FamilyStatistics.add(numberOfMartyrs);
	 FamilyStatistics.add(numberOfOrphans);
	 FamilyStatistics.add(numberOfLivePersons);
	 return FamilyStatistics; //return the array list that carries statistics
	 
 }
	public ArrayList<Integer> calculateGlobalStatistics(){ // to calculate global statistics
		 Integer  numberOfMartyrs = calculateTotalMartyrs() ; //invoke method that calculate number of total martyrs
		 Integer  numberOfOrphans =calculateTotalOrphans();  //invoke method that calculate number of total orphans
		 Integer  numberOfLivePersons =calculateTotalLivePersons(); //invoke method that calculate number of total Live Persons
		 ArrayList<Integer> GlobalStatistics = new ArrayList<>(); //create an array list that takes the statistics with this signature 1-number Of Martyrs 2-number Of Orphans 3-number Of Live Persons
		 GlobalStatistics.add(numberOfMartyrs);
		 GlobalStatistics.add(numberOfOrphans);
		 GlobalStatistics.add(numberOfLivePersons);
		 return GlobalStatistics; //return the array list that carries statistics
		 
	}
	public String toString() { // displays all families

		String s = "";
		 for(int i=0;i<families.size();i++) { 
			 s+="family "+(i+1)+" : "+families.get(i).toString()+" \n"; //in the line,display the family and its members
		 }
		 return s;
		
	}

	public ArrayList<Family> getFamilies() { //getter for families
		return families;
	}

	

	
	

}
