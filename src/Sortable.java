import java.util.ArrayList;

public interface Sortable {

	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families);

	public   abstract ArrayList<Family> sortByOrphans(ArrayList<Family> families);

}
