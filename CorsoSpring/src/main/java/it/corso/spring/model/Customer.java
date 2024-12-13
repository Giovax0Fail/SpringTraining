
package it.corso.spring.model;

/*
 * This is our model class and it corresponds to Customer table in database
 */
public class Customer{

	int id;

	String customerName;

	String email;



	public Customer() {

		super();
	}
	public Customer(int id,String customerName,String email) {
		super();
		this.id=id;
		this.customerName=customerName;
		this.email=email;

	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
		this.email=email;
	}

	public String getEmail() {

		return email;
	}
	public void setEmail(String email) {

		this.email = email;
	}

	public int getId() {

		return id;
	}
	public void setId(int id) {

		this.id = id;
	}




}
