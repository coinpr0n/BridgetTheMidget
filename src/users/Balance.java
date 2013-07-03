
package users;

//import java.util.List;

public class Balance{
   	private String delayed;
   	private String pending;
   	private String sent;

 	public String getDelayed(){
		return this.delayed;
	}
	public void setDelayed(String delayed){
		this.delayed = delayed;
	}
 	public String getPending(){
		return this.pending;
	}
	public void setPending(String pending){
		this.pending = pending;
	}
 	public String getSent(){
		return this.sent;
	}
	public void setSent(String sent){
		this.sent = sent;
	}
}
