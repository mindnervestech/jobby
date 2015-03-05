package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class SendEmailAlert extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5288153235054323469L;
       
      	
	    @Id
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
		public int  id; 
	    public String userEmail;
		public String position;
		public String jobNumber; 
		
		public String getUserEmail() {
			return userEmail;
		}
		
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		
		
		public static Finder<Long, SendEmailAlert> find = new Finder<Long, SendEmailAlert>(
				Long.class, SendEmailAlert.class);

        
		public  static List <SendEmailAlert> getAllPositionMatchedJobCount(String email){
			return find.where().eq("userEmail", email).findList();
			
		} 
		
		public static  List <SendEmailAlert> getAllRecords(){
			
			return find.all();
		}

}
