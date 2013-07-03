
package users;


//import java.util.List;

public class Users{
   	private String address;
   	private Balance balance;
   	private String username;

 	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address = address;
	}
 	public Balance getBalance(){
		return this.balance;
	}
	public void setBalance(Balance balance){
		this.balance = balance;
	}
 	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
}
