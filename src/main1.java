import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main1 {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("families.txt");        //create a file
		Manager manager = new Manager();    
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
		/*note:the array of line of live person its length is 8 (6 for constructor , 2 for role and family name 
		 * the array of line of martyr its length is 11 (9 for constructor , 2 for role and family name 
		 * the last index contains the family name , and the before last index contains the role
		 * */
		 
		for (int i = 0; i < arr.size(); i++) {    
			String[] m = arr.get(i).split(":");  //split the line and store it in array
			if (m.length == 7) {   //if the length of array of line is 8 create an family with name exist on last index , and add the family to manager
				Family fam = new Family(m[m.length - 1]);
				manager.addFamily(fam);
			} else if (m.length == 10) { //if the length of array of line is 11 create an family with name exist on last index
				Family fam = new Family(m[m.length - 1]);
				manager.addFamily(fam);
			}

		} //note: the family will only be created once , because in method add family on manager it is not acceptable to create two families with same name

		for (int i = 0; i < arr.size(); i++) {
			try {
				String[] m = arr.get(i).split(":");
				if (m.length == 7) { //if the length of array of line is 8 create a Live Person and add him to his family
					if(m[5].equals("mom")) {
						boolean isParentMom = false;
						for(int j=0; j<manager.searchByName(m[6]).getParents().size();j++) {
							if(manager.searchByName(m[6]).getParents().get(j).getGender().equals("Female")) {
								isParentMom=true;
							}
						}
						if(isParentMom==false) {
						LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Female",m[3],m[4]);
						manager.searchByName(m[6]).addMember(LP, m[5]);
						}else {
							System.out.println("Can not add more mom");
						}
					}
					else if(m[5].equals("dad")) {
						boolean isParentDad = false;
						for(int j=0; j<manager.searchByName(m[6]).getParents().size();j++) {
							if(manager.searchByName(m[6]).getParents().get(j).getGender().equals("Male")) {
								isParentDad=true;
							}
						}
						if(isParentDad==false) {
						LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Male",m[3],m[4]);
						manager.searchByName(m[6]).addMember(LP, m[5]);
						}else {
							System.out.println("Can not add more dad");
						}
					}
					else if(m[5].equals("son")) {
						LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Male",m[3],m[4]);
						manager.searchByName(m[6]).addMember(LP, m[5]);
					}
					else if(m[5].equals("daughter")) {
						LivePerson LP = new LivePerson(m[0], m[1], Integer.parseInt(m[2]),"Female",m[3],m[4]);
						manager.searchByName(m[6]).addMember(LP, m[5]);
					}
					
					//manager.searchByName(m[7]).addMember(LP, m[6]); //use method search by name to find his family and add him to it
				} else if (m.length == 10) { //if the length of array of line is 11 create a martyr and add him to his family
					if(m[8].equals("mom")) {
						boolean isParentMom = false;
						for(int j=0; j<manager.searchByName(m[9]).getParents().size();j++) {
							if(manager.searchByName(m[9]).getParents().get(j).getGender().equals("Female")) {
								isParentMom=true;
							}
						}
						if(isParentMom==false) {
						Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Female", m[3], m[4], m[5], m[6], m[7]);
						manager.searchByName(m[9]).addMember(mar, m[8]);
						}else{
							System.out.println("Can not add more mom");
						}
					}
					else if(m[8].equals("dad")) {
						boolean isParentDad = false;
						for(int j=0; j<manager.searchByName(m[9]).getParents().size();j++) {
							if(manager.searchByName(m[9]).getParents().get(j).getGender().equals("Male")) {
								isParentDad=true;
							}
						}
						if(isParentDad==false) {
						Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Male", m[3], m[4], m[5], m[6], m[7]);
						manager.searchByName(m[9]).addMember(mar, m[8]);
						}else {
							System.out.println("Can not add more dad");
						}
					}
					else if(m[8].equals("son")) {
						Martyr mar = new Martyr(m[0], m[1], Integer.parseInt(m[2]), "Male", m[3], m[4], m[5], m[6], m[7]);
						manager.searchByName(m[9]).addMember(mar, m[8]);
					}
					else if(m[8].equals("daughter")) {
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
			}
		}

		System.out.println(manager.toString());
		ArrayList<Family> k = manager.getFamilies();
		Family n = new Family();
		
		ArrayList<Family> k2= n.sortByMartyrs(k);
		for(int i=0;i<k2.size();i++) {
			System.out.println(k2.get(i).toString());
		}
		System.out.println(manager.toString());
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
		
	}
}
