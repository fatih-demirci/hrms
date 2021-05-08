import java.util.List;

import com.eticaret.business.concretes.CustomerManager;
import com.eticaret.core.concretes.SingExternalAdapter;
import com.eticaret.core.concretes.SingGoogleServiceAdapter;
import com.eticaret.dataAccess.concretes.DatabaseCustomer;
import com.eticaret.entities.concretes.Customer;

public class Main {

	public static void main(String[] args) {
		DatabaseCustomer databaseBir = new DatabaseCustomer();

		Customer customer = new Customer("Ýsim", "Soyisim", "asd@asd.com", "111222333");
		CustomerManager  customerManager = new CustomerManager(databaseBir);
		customerManager.register(customer);
		
		List<Customer> customers = databaseBir.getCustomers();
		
		if(!customers.isEmpty()) {	
			System.out.println("Üyeler:");
			for (Customer c : customers) {
				System.out.println(c.getEmail());
			}				
		
		}else {
			System.out.println("Hiç üye yok");
		}
		
		customerManager.signIn(databaseBir, "asd@asd.com", "111222333");
		customerManager.signIn(new SingGoogleServiceAdapter(), "ytghfg@asd.com", "11122233");
		customerManager.signIn(new SingGoogleServiceAdapter(), "asd@gmail.com", "11122233");
		
	}

}
