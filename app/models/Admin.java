package models;


import java.util.List;

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
	
	public static List<Admin> getAllAdmin(){
		
		return find.all();
		
	}
	
	public static Admin getAdminByEmail(String email){
		
		return find.where().eq("username", email).findUnique();
	}
	
	
	@JsonIgnore
	public static List<Admin>getAllAdmin(int pageNumber, int rowperpage){
		return find.
		setFirstRow(pageNumber * 10).setMaxRows(rowperpage)
		.findList();
	}
	
	@JsonIgnore
	public static int getAllAdminCount(int pageNo) {
		return find.setFirstRow(pageNo * 10).setMaxRows(Admin.find.findRowCount())
				.findList().size();
	}
}
