package models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

@Entity
public class Admin extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String username;
	public String password;
	public String role;

	
	public static Finder<Long, Admin> find = new Finder<Long, Admin>(
			Long.class, Admin.class);

	@JsonIgnore
	public static Admin isAdmin(String username, String password) {

		return find.where().eq("username", username).eq("password", password)
				.findUnique();
	}

	public static Admin checkAdmin(String email){
		return find.where().eq("username", email).findUnique();
	}
}
