import java.sql.SQLException;
import views.MainView;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Test {
	public static void main(String[] args) throws SQLException {
		
		MainView.getInstance().displayMenu();
	}

}
