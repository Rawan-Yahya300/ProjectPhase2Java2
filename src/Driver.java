import java.io.*;
import java.util.*;
public class Driver {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("families.txt");        //create a file
		Manager manager = new Manager();        //create a manager
		System.out.println("Do you want to use a file or console? ");    //ask user if he wants file or console
		int ConsoleOrFile = 0;
		//if the user enter number other than 1 or 2 , repeat ask for 1 or 2(1 to use console , 2 to use file)
		ConsoleOrFile = repeatAskForStatus(ConsoleOrFile,"\nif you want a console enter 1 , if you want a file enter 2");
		if (ConsoleOrFile == 2) {     //if user enter 2 , apload data from the file
			
			if (!file.exists()) {       //if the file does not exist stop the system
				System.out.println("the file does not exist ");
				System.exit(2);
			}
			ArrayList<String> arr = new ArrayList<>();    //create an arrayList to fill data from file to inside it
			Scanner sfile = new Scanner(file);  //create scanner to read data from the file
			while (sfile.hasNextLine()) {    //read data from file and enter it to the array
				String line = sfile.nextLine();
				arr.add(line);

			}
			/*note:the array of line of live person its length is 7 (5 for constructor(without gender because gender determined from role) , 2 for role and family name )
			 * the array of line of martyr its length is 10 (8 for constructor , 2 for role and family name 
			 * the last index contains the family name , and the before last index contains the role
			 * */
			 
			for (int i = 0; i < arr.size(); i++) {    
				String[] m = arr.get(i).split(":");  //split the line and store it in array
				if (m.length == 7) {   //if the length of array of line is 7 create an family with name exist on last index , and add the family to manager
					Family fam = new Family(m[m.length - 1]);
					manager.addFamily(fam);
				} else if (m.length == 10) { //if the length of array of line is 10 create an family with name exist on last index
					Family fam = new Family(m[m.length - 1]);
					manager.addFamily(fam);
				}

			} //note: the family will only be created once , because in method add family on manager it is not acceptable to create two families with same name

			for (int i = 0; i < arr.size(); i++) {
				try {
					String[] m = arr.get(i).split(":");
					if (m.length == 7) { //if the length of array of line is 7 create a Live Person to add him to his family
						if(m[5].equals("mom")) { //if the role is mom
							boolean isParentMom = false;     //search if there is an mom , don`t add more mom
							for(int j=0; j<manager.searchByName(m[6]).getParents().size();j++) {
								if(manager.searchByName(m[6]).getParents().get(j).getGender().equals("Female")) {
									isParentMom=true;
								}
							}
							if(isParentMom==false) { //if there is no mom , add his with gender  Female and role mom
							LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Female",m[3],m[4]);
							manager.searchByName(m[6]).addMember(LP, m[5]);
							}else {
								System.out.println("Can not add more mom");
							}
						}
						else if(m[5].equals("dad")) {  //if the role is dad 
							boolean isParentDad = false;  //search if there is dad , if there don`t add more
							for(int j=0; j<manager.searchByName(m[6]).getParents().size();j++) {
								if(manager.searchByName(m[6]).getParents().get(j).getGender().equals("Male")) {
									isParentDad=true;
								}
							}
							if(isParentDad==false) { //if there is no  dad , add him with gender Male
							LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Male",m[3],m[4]);
							manager.searchByName(m[6]).addMember(LP, m[5]);
							}else {
								System.out.println("Can not add more dad");
							}
						}
						else if(m[5].equals("son")) { //if the role is son add him to family with gender Male
							LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Male",m[3],m[4]);
							manager.searchByName(m[6]).addMember(LP, m[5]);
						}
						else if(m[5].equals("daughter")) { //if role is daughter add his to family with gender Female
							LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Female",m[3],m[4]);
							manager.searchByName(m[6]).addMember(LP, m[5]);
						}
						
						
					} else if (m.length == 10) { //if the length of array of line is 10 create a martyr to add him to his family
						if(m[8].equals("mom")) {  //if the role is mom
							boolean isParentMom = false;  //check if there is a mom don`t add more
							for(int j=0; j<manager.searchByName(m[9]).getParents().size();j++) {
								if(manager.searchByName(m[9]).getParents().get(j).getGender().equals("Female")) {
									isParentMom=true;
								}
							}
							if(isParentMom==false) { //if there is no mom , add his with gender Female
							Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Female", m[3], m[4], m[5], m[6], m[7]);
							manager.searchByName(m[9]).addMember(mar, m[8]);
							}else{
								System.out.println("Can not add more mom");
							}
						}
						else if(m[8].equals("dad")) { //if the role is dad
							boolean isParentDad = false;  //check if there is a dad don`t add more
							for(int j=0; j<manager.searchByName(m[9]).getParents().size();j++) {
								if(manager.searchByName(m[9]).getParents().get(j).getGender().equals("Male")) {
									isParentDad=true;
								}
							}
							if(isParentDad==false) { //if there is no dad add him with gender Male
							Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Male", m[3], m[4], m[5], m[6], m[7]);
							manager.searchByName(m[9]).addMember(mar, m[8]);
							}else {
								System.out.println("Can not add more dad");
							}
						}
						else if(m[8].equals("son")) { //if the role is son add him with gender Male
							Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Male", m[3], m[4], m[5], m[6], m[7]);
							manager.searchByName(m[9]).addMember(mar, m[8]);
						}
						else if(m[8].equals("daughter")) {  //if the role is daughter add him with gender Female
							Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Female", m[3], m[4], m[5], m[6], m[7]);
							manager.searchByName(m[9]).addMember(mar, m[8]);
						}
						
					}

				} catch (CanNotAddMemberException e) {  //catch exception for add member without parents
					System.out.println(e.getMessage());
				} catch (CanNotAddParentException e) { //catch exception for add more 2 parents
					System.out.println(e.getMessage());
				} catch (NumberFormatException e) { 
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println(e.toString());
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}catch(NullPointerException e) {
					System.out.println(e.getMessage());
				}
			}

			
		}

		printMenu();   //print the menu
		int process = 0;
		process = repeatAskForInt(process, "Enter a process"); //ask user to enter a process and if he enter a false type repeat ask until enters true type
		while (process != 23) {

			switch (process) {
			case 1: { // to add new family
				// ask the user to enter the family name
				String familyName = null;
				familyName = repeatAskForName(familyName, "enter the family name: "); //if the user enter family name contains numbers or special character , repeat ask to enter true name
				Family family = new Family(familyName); // create the family
				if (manager.addFamily(family)) { // if the adding of family is true , ask about mom and dad
					System.out.println("The process is done");

					System.out.println("Does Mom Martyr or Live Person?");// ask if the mom martyr or live person

					int statusMum = 0; // Enter mom information
					//if mom martyr enter 1 , if live person enter 2 , if user enter another number repeat ask him to enter 1 or 2
					statusMum = repeatAskForStatus(statusMum,"if she is Martyr enter number 1 , if she is Live Person enter number 2");

					String IDMom = null;
					//repeat ask user to enter ID contains just numbers
					IDMom = repeatAskForId(IDMom, "Enter ID: ");
                     String NameMom = null;
					NameMom = repeatAskForName(NameMom, "Enter Name: "); //repeat ask user to enter name just contains letters
                    int AgeMom = 0;
					AgeMom = repeatAskForPositiveInt(AgeMom, "Enter Age: ");//repeat ask user to enter integer and positive age
					String GenderMom = "Female";
					System.out.println("Enter Address: ");
					String AddressMom = input.next();
					System.out.println("Enter Contact Information: ");
					String contactInfoMom = input.next();

					switch (statusMum) {
					case 1: { // if the mom is martyr ask for mom information
						System.out.println("Enter Date of Martyrdom: ");
						String DateOfMartyrdom = input.next();

						String CauseOfDeath = null;
						CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: ");//repeat ask for string contains just letters
						System.out.println("Enter Place of Death: ");
						String PlaceOfDeath = input.next();
						Martyr martyr = new Martyr(IDMom, NameMom, AgeMom, GenderMom, AddressMom, contactInfoMom,DateOfMartyrdom, CauseOfDeath, PlaceOfDeath);
						try {
							family.addParent(martyr); // add the mom to the family as martyr

						} catch (CanNotAddParentException e) { //catch exception for adding more two parents
							System.out.println(e.getMessage());
						}
						break;
					}
					case 2: // if the mom is live person
					{
						LivePerson livePerson = new LivePerson(IDMom, NameMom, AgeMom, GenderMom, AddressMom,
								contactInfoMom);
						try {
							family.addParent(livePerson); // add the mom to the family as live person

						} catch (CanNotAddParentException e) { //catch exception for adding more two parents
							System.out.println(e.getMessage());
						}
						break;
					}

					}
					System.out.println("Does Dad Martyr or Live Person?");// ask about the dad
					int statusDad = 0; // Ask about his information
					//repeat ask for 1 or 2 to determine if dad martyr or live person
					statusDad = repeatAskForStatus(statusDad,"if he is Martyr enter number 1 , if she is Live Person enter number 2");
                     String IDDad = null;
					IDDad = repeatAskForId(IDDad, "Enter ID: ");//repeat ask for Id just contains numbers
                    String NameDad = null;
					NameDad = repeatAskForName(NameDad, "Enter Name: "); //repeat ask for name just contains letters
                    int AgeDad = 0;
					AgeDad = repeatAskForPositiveInt(AgeDad, "Enter Age: "); //repeat ask for integer and positive age
					String GenderDad = "Male";
					System.out.println("Enter Address: ");
					String AddressDad = input.next();
					System.out.println("Enter Contact Information: ");
					String contactInfoDad = input.next();

					switch (statusDad) {
					case 1: { // if the dad is martyr ask about more information
						System.out.println("Enter Date of Martyrdom: ");
						String DateOfMartyrdom = input.next();

						String CauseOfDeath = null;
						CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: "); //repeat ask for string just contains letters
						System.out.println("Enter Place of Death: ");
						String PlaceOfDeath = input.next();
						Martyr martyr = new Martyr(IDDad, NameDad, AgeDad, GenderDad, AddressDad, contactInfoDad,
								DateOfMartyrdom, CauseOfDeath, PlaceOfDeath);
						try {
							family.addParent(martyr); // add the dad as martyr

						} catch (CanNotAddParentException e) { //catch exception for adding more two parents
							System.out.println(e.getMessage());
						}
						break;
					}
					case 2: // if the dad is live person
					{
						LivePerson livePerson = new LivePerson(IDDad, NameDad, AgeDad, GenderDad, AddressDad,
								contactInfoDad);
						try {
							family.addParent(livePerson); // add the dad to the family as a live person

						} catch (CanNotAddParentException e) { //catch exception for adding more two parents
							System.out.println(e.getMessage());
						}
						break;
					}

					}

				} else {
					System.out.println("the process is fail");
				}
				break;
			}
			case 2: { // to add member
				System.out.println("The member is Martyr or Live Person?");// ask the user if the member is martyr or live person
				int status = 0;
				//repeat ask for 1 or 2 to determine the member is martyr or live Person
				status = repeatAskForStatus(status,"if he/she is Martyr enter number 1 ,if he/she is Live Person enter number 2");
				System.out.println("All families: "); //display all family
				System.out.println(manager.toString());
				//ask about family number that the user want to add member to it and repeat ask for integer and positive number just
				int familyNum = 0;
				familyNum = repeatAskForPositiveInt(familyNum,"Enter the family number you want to add member to it: ");
				if (familyNum - 1 >= manager.getFamilies().size()) { // if the family does not exist,stop
					System.out.println("fail,the family does not exist");
					break;
				}
				System.out.println("The member is son or daughter"); // Ask the user if the member is son or daughter
				int sOd = 0; // son or daughter and repeat ask for 1 or 2 to determine if member is son or daughter
				sOd = repeatAskForStatus(sOd, "if son enter 1,if daugther enter number 2:");
				// Ask for information
				String ID = null;
				ID = repeatAskForId(ID, "Enter ID: "); //repeat ask for ID just contains number
                String Name = null;
				Name = repeatAskForName(Name, "Enter Name: "); //repeat ask for name just contains letters

				int Age = 0;
				Age = repeatAskForPositiveInt(Age, "Enter Age: "); //repeat ask for integer and positive age

				String Gender = null;
				if (sOd == 1) {         //determine the gender according to member if is son or daughter
					Gender = "Male";
				} else if (sOd == 2) {
					Gender = "Female";
				}

				System.out.println("Enter Address: ");
				String Address = input.next();
				System.out.println("Enter Contact Information: ");
				String contactInfo = input.next();

				switch (status) {
				case 1: { // if the member is martyr ask about more information
					System.out.println("Enter Date of Martyrdom: ");
					String DateOfMartyrdom = input.next();

					String CauseOfDeath = null;
					CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: "); //repeat ask for string just contains letters
					System.out.println("Enter Place of Death: ");
					String PlaceOfDeath = input.next();
					Martyr martyr = new Martyr(ID, Name, Age, Gender, Address, contactInfo, DateOfMartyrdom,CauseOfDeath, PlaceOfDeath);
					if (sOd == 1) { // if the member is son , add him to the family as martyr and son
						try {
							if (manager.getFamilies().get(familyNum - 1).addMember(martyr, "son")) {
								System.out.println("The process is done ");
							} else {
								System.out.println("The process is fail");
							}
						} catch (CanNotAddMemberException e) { //catch exception for adding member without parents
							System.out.println(e.getMessage());
						}
					} else if (sOd == 2) { // if the member is daughter , add him to the family as martyr and daughter
						try {
							if (manager.getFamilies().get(familyNum - 1).addMember(martyr, "daughter")) {
								System.out.println("The process is done ");
							} else {
								System.out.println("The process is fail");
							}
						} catch (CanNotAddMemberException e) { //catch exception for adding member without parents
							System.out.println(e.getMessage());
						}
					}
					break;
				}
				case 2: {
					LivePerson livePerson = new LivePerson(ID, Name, Age, Gender, Address, contactInfo);
					if (sOd == 1) {// if the member is son , add him to the family as live person and son
						try {
							if (manager.getFamilies().get(familyNum - 1).addMember(livePerson, "son")) {
								System.out.println("The process is done ");
							} else {
								System.out.println("The process is fail");
							}
						} catch (CanNotAddMemberException e) { //catch exception for adding member without parents
							System.out.println(e.getMessage());
						}
					} else if (sOd == 2) { // if the member is daughter , add him to the family as live person and daughter
						try {
							if (manager.getFamilies().get(familyNum - 1).addMember(livePerson, "daughter")) {
								System.out.println("The process is done ");
							} else {
								System.out.println("The process is fail");
							}
						} catch (CanNotAddMemberException e) { //catch exception for adding member without parents
							System.out.println(e.getMessage());
						}
					}
					break;
				}

				}
				break;
			}
			case 3: { // add parent
				System.out.println("The parent is Martyr or Live Person?");// ask the user if the parent is martyr or live person
				int status = 0;
				//repeat ask for 1 or 2 to determine the member is martyr or live person
				status = repeatAskForStatus(status,"if he/she is Martyr enter number 1 ,if he/she is Live Person enter number 2");
				System.out.println("All families: ");   //display all families
				System.out.println(manager.toString());
				//ask about family number that the user want to add member to it
				int familyNum = 0;
				familyNum = repeatAskForPositiveInt(familyNum,"Enter the family number you want to add member to it: ");//repeat ask for integer and positive
				if (familyNum - 1 >= manager.getFamilies().size()) { // if the family does not exist,stop
					System.out.println("fail,the family does not exist");
					break;
				}
				System.out.println("The parent is Mom or  Dad?");// ask the user if the member is martyr or live person
				int mOd = 0;
				//repeat ask for 1 or 2 to determine the member is mom or dad
				mOd = repeatAskForStatus(mOd, "if she is Mom enter number 1 ,if he is Dad enter number 2");
				// Ask for information
				if (mOd == 1) { //if the user choice to add mom , we check if there is an mom can not add another mom
					boolean isMom = false;
					//this for check if the parent exist mom
					for (int i = 0; i < manager.getFamilies().get(familyNum - 1).getParents().size(); i++) {
						if (manager.getFamilies().get(familyNum - 1).getParents().get(i).getGender().equals("Female")) {
							isMom = true;
						}
					}
					if (isMom == true) { //if there is an mom not add another and stop the case
						System.out.println("There is mom , can not add more");
						break;
					}
				}
				if (mOd == 2) {  //if the user choice to add dad , check if there is a dad not add more dad
					boolean isDad = false;
					//this for is to check if parent exist dad 
					for (int i = 0; i < manager.getFamilies().get(familyNum - 1).getParents().size(); i++) {
						if (manager.getFamilies().get(familyNum - 1).getParents().get(i).getGender().equals("Male")) {
							isDad = true;
						}
					}
					if (isDad == true) { //if there is a dad , not add another and stop case
						System.out.println("There is dad , can not add more");
						break;
					}
				}
				String ID = null; 
				ID = repeatAskForId(ID, "Enter ID: "); //repeat ask for ID contains numbers just
                String Name = null;
				Name = repeatAskForName(Name, "Enter Name: "); //repeat ask for name contains just letters
                int Age = 0;
				Age = repeatAskForPositiveInt(Age, "Enter Age: "); //repeat ask for integer and positive age
                String Gender = null;
				if (mOd == 1) {         //determine the gender according to the parent if is mom or dad
					Gender = "Female";
				}
				if (mOd == 2) {
					Gender = "Male";
				}

				System.out.println("Enter Address: ");
				String Address = input.next();
				System.out.println("Enter Contact Information: ");
				String contactInfo = input.next();

				switch (status) {
				case 1: { // if the member is martyr ask about more information
					System.out.println("Enter Date of Martyrdom: ");
					String DateOfMartyrdom = input.next();
                    String CauseOfDeath = null;
					CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: "); //repeat ask for string just contains letters
					System.out.println("Enter Place of Death: ");
					String PlaceOfDeath = input.next();
					Martyr martyr = new Martyr(ID, Name, Age, Gender, Address, contactInfo, DateOfMartyrdom,CauseOfDeath, PlaceOfDeath);
					
					try {   //add the parents
						manager.getFamilies().get(familyNum - 1).addParent(martyr);

					} catch (CanNotAddParentException e) { //catch exception for adding more 2 parents
						System.out.println(e.getMessage());
					}

					break;
				}
				case 2: { //add the parents
					LivePerson livePerson = new LivePerson(ID, Name, Age, Gender, Address, contactInfo);
					try {
                      manager.getFamilies().get(familyNum - 1).addParent(livePerson);
                      } catch (CanNotAddParentException e) { //catch exception for adding more 2 parents
						System.out.println(e.getMessage());
					  }
                   break;
				}
                }
				break;
			}
			case 4: { // to remove member
				System.out.println("All families: "); // display all families
				System.out.println(manager.toString());
				// Ask user to enter the number of family to remove the member from it
				int familyNum = 0;
				//repeat ask for positive integer number
				familyNum = repeatAskForPositiveInt(familyNum,"Enter the family number you want to remove member from it: ");
				boolean is = false;
				if (familyNum - 1 < manager.getFamilies().size()) { // if the family is exist
					System.out.println(manager.getFamilies().get(familyNum - 1).toStringWithId()); // display the family members with their ID
					 boolean isExistinParent = false; //this boolean to check if the member was deleted is parent , to add another parent
					boolean isMom = true; //this boolean to check if member is parent , is mom or dad
					// Ask the user to enter the ID for member to remove it
					String IDtoRemove = null;  //repeat ask for ID just contains numbers
					IDtoRemove = repeatAskForId(IDtoRemove, "Enter the ID for the member you want to delete: ");
					//this for to check if the member the user remove it is parent and determine is mom or dad
					for (int i = 0; i < manager.getFamilies().get(familyNum - 1).getParents().size(); i++) {
						//check if the member is parent
						if (IDtoRemove.equals(manager.getFamilies().get(familyNum - 1).getParents().get(i).getID())) { 
							isExistinParent = true;
							if (manager.getFamilies().get(familyNum - 1).getParents().get(i).getGender()
									.equals("Male")) { //check if is mom
								isMom = false;
							}
						}
					}
					// looking for about the member and if the the member exist remove it
					for (int i = 0; i < manager.getFamilies().get(familyNum - 1).getMembers().size(); i++) { 
						if (IDtoRemove.equals(manager.getFamilies().get(familyNum - 1).getMembers().get(i).getID())) {
							is = true;
							manager.getFamilies().get(familyNum - 1).removeMember(manager.getFamilies().get(familyNum - 1).getMembers().get(i));
							System.out.println("done");
							System.out.println(manager.getFamilies().get(familyNum - 1).toString());
						}
					}
					//if the member was deleted is parent , you must to enter another parent
					if (isExistinParent == true) {
						System.out.println("The member was deleted is parents ,Enter anthor parent");
						int status = 0;
						//ask about new parent if is martyr or live person and repeat ask for 1 or 2 to determine
						status = repeatAskForStatus(status,"if he/she is Martyr enter number 1 ,if he/she is Live Person enter number 2");
						String ID = null;
						ID = repeatAskForId(ID, "Enter ID: "); //repeat ask for ID just contains numbers
                        String Name = null;
						Name = repeatAskForName(Name, "Enter Name: "); //repeat ask for name just contains letters
                        int Age = 0;
						Age = repeatAskForPositiveInt(Age, "Enter Age: ");//repeat ask for integer and positive age
                        String Gender = null;
						if (isMom == true) {  //determine the gender according to parent was deleted(if is mom we must enter mom and the same for dad)
							Gender = "Female";
						}
						if (isMom == false) {
							Gender = "Male";
						}

						System.out.println("Enter Address: ");
						String Address = input.next();
						System.out.println("Enter Contact Information: ");
						String contactInfo = input.next();

						switch (status) {
						case 1: { // if the member is martyr ask about more information
							System.out.println("Enter Date of Martyrdom: ");
							String DateOfMartyrdom = input.next();

							String CauseOfDeath = null;
							CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: ");//repeat ask for string just contains letters
							System.out.println("Enter Place of Death: ");
							String PlaceOfDeath = input.next();
							Martyr martyr = new Martyr(ID, Name, Age, Gender, Address, contactInfo, DateOfMartyrdom,CauseOfDeath, PlaceOfDeath);
                            try {
								manager.getFamilies().get(familyNum - 1).addParent(martyr); //add the parent to the family
							} catch (CanNotAddParentException e) { //catch an exception for adding more two parents
								System.out.println(e.getMessage());
							}
                          break;
						}
						case 2: {
							LivePerson livePerson = new LivePerson(ID, Name, Age, Gender, Address, contactInfo);
							try {
								manager.getFamilies().get(familyNum - 1).addParent(livePerson); //add parent
							} catch (CanNotAddParentException e) {
								System.out.println(e.getMessage()); //catch an exception for adding more two parents
							}
                            break;
						}
                        }
                   }
				
					if (is == false) {
						System.out.println("The member does not exist");
					}
				} else {
					System.out.println("The family does not exist");
				}

				break;
			}
			case 5: { // to remove family
				System.out.println("All families: "); // display all families
				System.out.println(manager.toString());
				// Ask the user to enter the number of family to remove it
				String familyName = null;
				familyName = repeatAskForName(familyName, "Enetr the family name you want  to delete: ");//repeat ask for name just contains letters
				if (manager.deleteFamily(familyName)) { // if the family exist remove it
					System.out.println("The process is done");
					System.out.println(manager.toString()); 
				} else {
					System.out.println("Fail,the family does not exist ");
				}
				break;
			}
			case 6: { // to search by family use its name
				// ask user to enter the family name
				String familyName = null;
				familyName = repeatAskForName(familyName, "Enter the family name: ");//repeat ask for name just contains letters
				if (manager.searchByName(familyName) != null) // if the family exist display it
					System.out.println(manager.searchByName(familyName));
				else
					System.out.println("The family does not exist");
				break;
			}
			case 7: { // to search for member using ID
				// ask the user to enter ID for person to remove him
				String ID = null;
				ID = repeatAskForId(ID, "Enter the ID for member: ");//repeat ask for id just contains numbers
				if (manager.searchPersonByID(ID) != null) // if the member exist display him
					System.out.println(manager.searchPersonByID(ID));
				else
					System.out.println("The member does not exist");

				break;
			}
			case 8: {
				// ask the user to enter the name family to update it
				String familyName = null;
				familyName = repeatAskForName(familyName, "Enter the family name you want to update it: "); //repeat ask for name just contains letters
				boolean isExist = false; //to check if family exist
				for (int i = 0; i < manager.getFamilies().size(); i++) { // search if the family exist
					if (manager.getFamilies().get(i).getFamilyName().equals(familyName)) {
						isExist = true;
					}
				}
				if (isExist == false) { // if the family does not exist , stop
					System.out.println("fails,the family does not exist");
					break;
				}
				// Ask about the name of new family and its dad and mom
				String familyNameNew = null;
				familyNameNew = repeatAskForName(familyNameNew, "Enter the name of new family: ");//repeat ask for name just contains letters
				Family family = new Family(familyNameNew);
				System.out.println("Does Mom Martyr or Live Person?");// ask if the mom martyr or live person
				int statusMum = 0; // Enter mom information
				//repeat ask for 1 or 2 to determine if mom martyr or Live
				statusMum = repeatAskForStatus(statusMum,"if she is Martyr enter number 1 , if she is Live Person enter number 2");
                String IDMom = null;
				IDMom = repeatAskForId(IDMom, "Enter ID: ");//repeat ask for ID just contains numbers
				String NameMom = null;
				NameMom = repeatAskForName(NameMom, "Enter Name: ");//repeat ask for name just contains letters
				int AgeMom = 0;
				AgeMom = repeatAskForPositiveInt(AgeMom, "Enter Age: ");//repeat ask for integer and positive age
				String GenderMom = "Female";
				System.out.println("Enter Address: ");
				String AddressMom = input.next();
				System.out.println("Enter Contact Information: ");
				String contactInfoMom = input.next();

				switch (statusMum) {
				case 1: { // if the mom is martyr ask for more information
					System.out.println("Enter Date of Martyrdom: ");
					String DateOfMartyrdom = input.next();

					String CauseOfDeath = null;
					CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: ");//repeat ask for string just contains letters
					System.out.println("Enter Place of Death: ");
					String PlaceOfDeath = input.next();
					Martyr martyr = new Martyr(IDMom, NameMom, AgeMom, GenderMom, AddressMom, contactInfoMom,DateOfMartyrdom, CauseOfDeath, PlaceOfDeath);
					try {
						family.addParent(martyr); // add the mom to the family as martyr

					} catch (CanNotAddParentException e) { //catch an exception for adding more two parents
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2: // if the mom is live person
				{
					LivePerson livePerson = new LivePerson(IDMom, NameMom, AgeMom, GenderMom, AddressMom,contactInfoMom);
					try {
						family.addParent(livePerson); // add the mom to the family as live person

					} catch (CanNotAddParentException e) {//catch an exception for adding more two parents
						System.out.println(e.getMessage());
					}
					break;
				}

				}
				System.out.println("Does Dad Martyr or Live Person?");// ask about the dad
				int statusDad = 0;   // Ask about his information
				//repeat ask for 1 or 2 to determine if mom martyr or Live
				statusDad = repeatAskForStatus(statusDad,"if he is Martyr enter number 1 , if she is Live Person enter number 2");
				String IDDad = null;
				IDDad = repeatAskForId(IDDad, "Enter ID: ");//repeat ask for ID just contains numbers
				String NameDad = null;
				NameDad = repeatAskForName(NameDad, "Enter Name: "); //repeat ask for name just contains letters
				int AgeDad = 0;
				AgeDad = repeatAskForPositiveInt(AgeDad, "Enter Age: "); //repeat ask for integer and positive age
				String GenderDad = "Male";
				System.out.println("Enter Address: ");
				String AddressDad = input.next();
				System.out.println("Enter Contact Information: ");
				String contactInfoDad = input.next();
				switch (statusDad) {
				case 1: { // if the dad is martyr ask about more information
					System.out.println("Enter Date of Martyrdom: ");
					String DateOfMartyrdom = input.next();

					String CauseOfDeath = null;
					CauseOfDeath = repeatAskForName(CauseOfDeath, "Enter Cause of Death: ");//repeat ask for string just contains letters
					System.out.println("Enter Place of Death: ");
					String PlaceOfDeath = input.next();
					Martyr martyr = new Martyr(IDDad, NameDad, AgeDad, GenderDad, AddressDad, contactInfoDad,DateOfMartyrdom, CauseOfDeath, PlaceOfDeath);
					try {
						family.addParent(martyr); // add the dad as martyr

					} catch (CanNotAddParentException e) { //catch an exception for adding more two parents
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2: // if the dad is live person
				{
					LivePerson livePerson = new LivePerson(IDDad, NameDad, AgeDad, GenderDad, AddressDad,contactInfoDad);
					try {
						family.addParent(livePerson); // add the dad to the family as a live person

					} catch (CanNotAddParentException e) { //catch an exception for adding more two parents
						System.out.println(e.getMessage());
					}
					break;
				}
				}
				if (manager.updateFamily(familyName, family)) {
					System.out.println("The process is done");
				} else {
					System.out.println("The process is fail , family Does not Exist");
				}
				break;
			}
			case 9: { // to calculate total martyr
				System.out.println("Total Martyr= " + manager.calculateTotalMartyrs()); // invoke the method
				break;
			}
			case 10: { // to calculate the total orphans
				System.out.println("Total Orphans= " + manager.calculateTotalOrphans()); // invoke method
				break;
			}
			case 11: { // calculate total live person
				System.out.println("Total Live Person= " + manager.calculateTotalLivePersons()); // invoke method
				break;
			}
			case 12: { // to calculate family statistics
				// enter the family name
				String familyName = null;
				familyName = repeatAskForName(familyName, "Enter the family name to clculate its Statistics: ");
				boolean isExist = false;
				for (int i = 0; i < manager.getFamilies().size(); i++) { // if the family exists display its statistics
					if (manager.getFamilies().get(i).getFamilyName().equals(familyName)) {
						isExist = true;
						System.out.println(manager.calculateFamilyStatistics(familyName) + " :");
						System.out.println("The number of Martyr in this family= "
								+ manager.calculateFamilyStatistics(familyName).get(0));
						System.out.println("The number of Orphans in this family= "
								+ manager.calculateFamilyStatistics(familyName).get(1));
						System.out.println("The number of Live Person in this family= "
								+ manager.calculateFamilyStatistics(familyName).get(2));

					}
				}
				if (isExist == false) {
					System.out.println("The family does not exist");
				}
				break;

			}
			case 13: { // calculate global statistics
				System.out.println("Glopal Statistics: " + manager.calculateGlobalStatistics()); // invoke method
				System.out.println("Total Martyr=" + manager.calculateGlobalStatistics().get(0));
				System.out.println("Total Orphans=" + manager.calculateGlobalStatistics().get(1));
				System.out.println("Total Live Person=" + manager.calculateGlobalStatistics().get(2));
				break;
			}
			case 14: { // to test if the two family are equal in number of martyr
				System.out.println("All families: "); // display all family

				System.out.println(manager.toString());
				// ask user to enter the two families numbers
				int numOfFamily1 = 0;
				numOfFamily1 = repeatAskForPositiveInt(numOfFamily1, "Enter the number of family1: ");//repeat ask for positive integer
				int numOfFamily2 = 0;
				numOfFamily2 = repeatAskForPositiveInt(numOfFamily2, "Enter the number of family2: ");//repeat ask for positive integer
				if (numOfFamily1 - 1 < manager.getFamilies().size()
						&& numOfFamily2 - 1 < manager.getFamilies().size()) { // if the two families are exist invoke the method
					if (manager.getFamilies().get(numOfFamily1 - 1)
							.equals(manager.getFamilies().get(numOfFamily2 - 1))) {
						System.out.println("The two families are equal in number of martyrs");
					} else {
						System.out.println("The two families are not equal in number of martyrs");
					}
				} else {
					System.out.println("fails,families do not exist");
				}

				break;
			}
			case 15: {// to display all family
				System.out.println("All families: ");

				System.out.println(manager.toString());

				break;
			}
			case 16: { // to sort by martyrs
				if (manager.getFamilies().size() == 0) { //if the manager does not contain families
					System.out.println("There are no families to sort ");
				} else {
					ArrayList<Family> sortedFamilies = manager.getFamilies().get(0).sortByMartyrs(manager.getFamilies());//create a sorted array
					for (int i = 0; i < sortedFamilies.size(); i++)
						System.out.println(sortedFamilies.get(i).toString());  //display sorted families
				}
				break;
			}
			case 17: { // to sort by orphans
				if (manager.getFamilies().size() == 0) {  //if the manager does not contain families
					System.out.println("There are no families to sort ");
				} else {
					ArrayList<Family> sortedFamilies = manager.getFamilies().get(0).sortByOrphans(manager.getFamilies());//create a sorted array
					for (int i = 0; i < sortedFamilies.size(); i++)
						System.out.println(sortedFamilies.get(i).toString());   //display sorted families
				}
				break;
			}
			case 18:{ //to copy a Martyr
				String ID = null; //ask for ID for martyr to copy it
				ID = repeatAskForId(ID, "Enter the ID for Martyr you Want to copy : "); //repeat ask for id just contains numbers
				if(manager.searchPersonByID(ID) instanceof Martyr) { //search for member using id and if is martyr copy him
					Martyr copyMartyr = (Martyr)((Martyr)manager.searchPersonByID(ID)).clone();
					
					System.out.println("The copy martyr: "+copyMartyr.toString());
					
				}
				else {
					System.out.println("Sorry,this ID does not exist or not for Martyr");
				}
				break;
			}
			case 19:{ //to copy a Live Person
				String ID = null;      //ask for ID for Live Person to copy it
				ID = repeatAskForId(ID, "Enter the ID for Live Person you Want to copy : ");   //repeat ask for id just contains numbers
				if(manager.searchPersonByID(ID) instanceof LivePerson) {   //search for member using id and if is Live Person copy him
					LivePerson copyLivePerson = (LivePerson)((LivePerson)manager.searchPersonByID(ID)).clone();
					System.out.println("The copy Live Person: "+copyLivePerson.toString());
				}
				else {
					System.out.println("Sorry,this ID does not exist or not for Live Person");
				}
				break;
			}
			case 20:{ //to copy a Family
				String familyName = null;    //ask for family name to copy it
				familyName = repeatAskForName(familyName, "Enter the family name: "); //repeat ask for name just contains letters
				if(manager.searchByName(familyName) != null) { //search for family by name and if it exist copy it
					Family copyFamily = (Family)manager.searchByName(familyName).clone();
					System.out.println(copyFamily.toString());
				}
				else {
					System.out.println("The family does not exist");
				}
				
				break;
			}
			case 21:{ //Print to the file all families with their members in sorted order by martyrs
				   if(manager.getFamilies().size() == 0) { //if the manager does not contains any family stop the case
					System.out.println("There are no families to sort");
					break;
				}
				PrintWriter pw = new PrintWriter("SortedFamilyFile");
				ArrayList<Family> sortedFamilies = manager.getFamilies().get(0).sortByMartyrs(manager.getFamilies()); //sorted families by number martyrs
				    for(int i=0;i<sortedFamilies.size();i++) {
				    	pw.println("Family"+(i+1)+": number of martyrs("+sortedFamilies.get(i).calculateNumOfMartyrs()+")");//print family number and number of its martyrs to file
				    	pw.println(".....................");
				    	pw.println("Family name: "+sortedFamilies.get(i).getFamilyName()); //print family name in the file
				    	pw.print("Parents: ");
				    	StringBuilder parents=new StringBuilder("");  //create a string builder to sort parents names 
				    	for(int j=0;j<sortedFamilies.get(i).getParents().size();j++) {
				    		parents.append(sortedFamilies.get(i).getParents().get(j).getName()+","); //add parent name to string builder parents
				    	}
				    	if(parents.length()>0) {
				    	parents.deleteCharAt(parents.length()-1); //delete last character from string builder (to delete last ',' )
				    	}
				    	pw.print(parents); //print parents names to in file
				    	pw.println();
				    	pw.print("Members: ");
				    	StringBuilder members = new StringBuilder(""); //create a string builder to sort members names
				    	for(int x=0;x<sortedFamilies.get(i).getMembers().size();x++) {    //check if member is parent , do not print him in members
				    		  boolean isParent=false;
				    		  for(int j=0;j<sortedFamilies.get(i).getParents().size();j++) {
				    			  if(sortedFamilies.get(i).getParents().get(j).getID().equals(sortedFamilies.get(i).getMembers().get(x).getID())) {
				    				  isParent=true;
				    			  }
				    		  }
				    		  if(isParent==false) { //if is not a parent add him to members string builder
				    			  members.append(sortedFamilies.get(i).getMembers().get(x).getName()+",");
				    		  }
				    	}
				    	if(members.length()>0) {
				    	members.deleteCharAt(members.length()-1);//delete last character from string builder (to delete last ',' )
				    	}
				    	pw.print(members); //print members names to in file
				    	pw.println();
				    	pw.println();
				    	
				    }
				    pw.close();
				break;
			}
			case 22:{  //note!!!!!!: this case is same for the previous case , so the comment in previous case  is describe this case too
				//Print to the file all families with their members in sorted order by Live person
				   if(manager.getFamilies().size() == 0) {
					System.out.println("There are no families to sort");
					break;
				}
				PrintWriter pw = new PrintWriter("SortedFamilyFile");
				ArrayList<Family> sortedFamilies = manager.getFamilies().get(0).sortByOrphans(manager.getFamilies());
				    for(int i=0;i<sortedFamilies.size();i++) {
				    	pw.println("Family"+(i+1)+": number of orphans("+sortedFamilies.get(i).calculateNumOfOrphans()+")");
				    	pw.println(".....................");
				    	pw.println("Family name: "+sortedFamilies.get(i).getFamilyName());
				    	pw.print("Parents: ");
				    	StringBuilder parents = new StringBuilder("");
				    	for(int j=0;j<sortedFamilies.get(i).getParents().size();j++) {
				    		parents.append(sortedFamilies.get(i).getParents().get(j).getName()+",");
				    	}
				    	if(parents.length()>0) {
				    	parents.deleteCharAt(parents.length()-1);
				    	}
				    	pw.print(parents);
				    	pw.println();
				    	pw.print("Members: ");
				    	StringBuilder members = new StringBuilder("");
				    	for(int x=0;x<sortedFamilies.get(i).getMembers().size();x++) {
				    		  boolean isParent=false;
				    		  for(int j=0;j<sortedFamilies.get(i).getParents().size();j++) {
				    			  if(sortedFamilies.get(i).getParents().get(j).getID().equals(sortedFamilies.get(i).getMembers().get(x).getID())) {
				    				  isParent=true;
				    			  }
				    		  }
				    		  if(isParent==false) {
				    			  members.append(sortedFamilies.get(i).getMembers().get(x).getName()+",");
				    			 }
				    	}
				    	if(members.length()>0) {
				    	members.deleteCharAt(members.length()-1);
				    	}
				    	pw.print(members);
				    	pw.println();
				    	pw.println();
				    	
				    }
				    pw.close();
				break;
			}
			default:
				System.out.println("The process does not exist , Enter anothe process");

			}

			printMenu(); // print menu
			process = repeatAskForInt(process, "Enter a process"); 
		}
		if (process == 23) { // if the process is 15 stop , and if the user choice the file , print the data to the file
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < manager.getFamilies().size(); i++) {
				for (int j = 0; j < manager.getFamilies().get(i).getParents().size(); j++) {  //to print parents
					String role = null;
					if (manager.getFamilies().get(i).getParents().get(j).getGender().equals("Male")) { //if parent`s gender is Male , set the rule is dad
						role = "dad";
					} else if (manager.getFamilies().get(i).getParents().get(j).getGender().equals("Female")) { //if parent`s gender is Female , set the rule is mom
						role = "mom";
					}
					if (manager.getFamilies().get(i).getParents().get(j) instanceof Martyr) { //if the parent is martyr print him as a martyr
						Martyr m = (Martyr) manager.getFamilies().get(i).getParents().get(j);
						pw.println(m.getID() + ":" + m.getName() + ":" + m.getAge() + ":"
								+ m.getAddress() + ":" + m.getContactInfo() + ":" + m.getDateOfMartyrdom() + ":"
								+ m.getCauseOfDeath() + ":" + m.getPlaceOfDeath() + ":" + role + ":"
								+ manager.getFamilies().get(i).getFamilyName());
					} else if (manager.getFamilies().get(i).getParents().get(j) instanceof LivePerson) { //if the parent is Live person print him as a Live person
						LivePerson L = (LivePerson) manager.getFamilies().get(i).getParents().get(j);
						pw.println(L.getID() + ":" + L.getName() + ":" + L.getAge() + ":"
								+ L.getAddress() + ":" + L.getContactInfo() + ":" + role + ":"
								+ manager.getFamilies().get(i).getFamilyName());
					}
				}
				for (int x = 0; x < manager.getFamilies().get(i).getMembers().size(); x++) {
					if (manager.getFamilies().get(i).getParents().contains(manager.getFamilies().get(i).getMembers().get(x))) { //if the member is parent don`t print him again
						continue;
					}
					String role = null;
					if (manager.getFamilies().get(i).getMembers().get(x).getGender().equals("Female")) {//if member`s gender is Male , set the rule is son
						role = "daughter";
					} else if (manager.getFamilies().get(i).getMembers().get(x).getGender().equals("Male")) {//if member`s gender is Female , set the rule is daughter
						role = "son";
					}
					if (manager.getFamilies().get(i).getMembers().get(x) instanceof Martyr) {//if the member is martyr print him as a martyr
						Martyr m = (Martyr) manager.getFamilies().get(i).getMembers().get(x);
						pw.println(m.getID() + ":" + m.getName() + ":" + m.getAge() + ":"
								+ m.getAddress() + ":" + m.getContactInfo() + ":" + m.getDateOfMartyrdom() + ":"
								+ m.getCauseOfDeath() + ":" + m.getPlaceOfDeath() + ":" + role + ":"
								+ manager.getFamilies().get(i).getFamilyName());

					} else if (manager.getFamilies().get(i).getMembers().get(x) instanceof LivePerson) {//if the member is Live person print him as a Live person
						LivePerson L = (LivePerson) manager.getFamilies().get(i).getMembers().get(x);
						pw.println(L.getID() + ":" + L.getName() + ":" + L.getAge() + ":"
								+ L.getAddress() + ":" + L.getContactInfo() + ":" + role + ":"
								+ manager.getFamilies().get(i).getFamilyName());

					}
				}

			}
			pw.close();
			System.out.println("Good Buy");
		}
	}

	public static void printMenu() { // method to display the menu
		System.out.println(
				"1-add family\n2-add member\n3-add Parent\n4-remove member\n5-remove family\n6-search for family by name\n"
				 + "7-search for member\n8-update family\n9-calculate total martyr\n10-calculate total orphans\n11-calculate total live person"
				 + "\n12-calculate family statistics\n13-calculate global statistics\n14-Check whether two families are equal in number of martyrs"
				 + "\n15-Displays all family\n16-sort by martyrs\n17-sort by orphans\n18-Copy an object of type Martyr\n"
				 + "19-Copy an object of type Live Person\n20-Copy an object of type Family\n"
				 + "21-Print to the file all families with their members in sorted order by martyrs\n"
				 + "22-Print to the file all families with their members in sorted order by Live Person\n23-exit");
	}
/**
 * 
 * @param x
 * @param message
 * @return
 * this method to repeat ask the user to enter an integer if he enters another type
 */
	public static int repeatAskForInt(int x, String message) { 
		boolean isValid = false;

		while (isValid == false) {
			try {
				System.out.println(message);
				x = input.nextInt();
				isValid = true;

			} catch (InputMismatchException ex) {
				System.out.println("wrong input , Try again");
				input.next();
			}
		}
		return x;
	}
/**
 * 
 * @param x
 * @param message
 * @return
 * this method is to repeat ask the user to enter 1 or 2 if he enters another numbers
 */
	public static int repeatAskForStatus(int x, String message) {

		x = repeatAskForInt(x, message);
		while (x != 1 && x != 2) {
			System.out.println("Wrong status , Try again");
			x = repeatAskForInt(x, message);
		}

		return x;
	}
     /**
      * 
      * @param x
      * @param message
      * @return
      * this method to repeat ask for integer and positive if the user enter another
      */
	public static int repeatAskForPositiveInt(int x, String message) {

		x = repeatAskForInt(x, message);
		while (x <= 0) {
			System.out.println("Wrong Age , Try again");
			x = repeatAskForInt(x, message);
		}

		return x;
	}
/**
 * 
 * @param Id
 * @return
 * this method check if the id entered is contains just numbers or no
 */
	public static boolean isValidForId(String Id) {
		boolean isValid = true;
		char[] arr = Id.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (!Character.isDigit(arr[i])) {
				isValid = false;
			}
		}
		return isValid;

	}
/**
 * 
 * @param Id
 * @param message
 * @return
 * this method is to repeat ask for ID if the user enter an Id contains letters or special character
 */
	public static String repeatAskForId(String Id, String message) {
		System.out.println(message);
		Id = input.next();
		if (isValidForId(Id) == true) {
			return Id;
		}

		while (isValidForId(Id) == false) {
			System.out.println("Wrong input , Try again: ");
			Id = input.next();

		}
		return Id;
	}
/**
 * 
 * @param Name
 * @return
 * this method is to check if the name contains just letters or no
 */
	public static boolean isValidForName(String Name) {
		boolean isValid = true;
		char[] arr = Name.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (!(Character.isAlphabetic(arr[i]))) {
				isValid = false;
			}
		}
		return isValid;

	}
/**
 * 
 * @param Name
 * @param message
 * @return
 * this method is to repeat ask for name just contains letters
 */
	public static String repeatAskForName(String Name, String message) {
		System.out.println(message);
		Name = input.next();
		if (isValidForName(Name) == true) {
			return Name;
		}

		while (isValidForName(Name) == false) {
			System.out.println("Wrong input , Try again: ");
			Name = input.next();

		}
		return Name;
	}

}
