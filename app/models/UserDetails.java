package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import play.db.ebean.Model;

@Entity
public class UserDetails extends Model {

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String email;
	public String password;
	public String firstname;
	public String middlename;
	public String lastname;
	public String gender;
	public String dob;
	public String userstatus;

	@ManyToMany
	public List<UserSkill> userSkill;

	@ManyToMany
	public List<UserClearance> userClearance;

	@ManyToMany
	public List<UserExperiance> userExperiance;
	
	@ManyToMany
	public List<UserPosition> userPosition;
	
	@JsonIgnore
	@OneToMany
	public List<EducationDetails> educationDetails;

	@JsonIgnore
	@OneToMany
	public List<EmploymentDetails> employmentDetails;

	@JsonIgnore
	@OneToMany
	public List<CertificationDetails> certificationDetails;

	public UserDetails() {

	}

	public static Finder<Long, UserDetails> find = new Finder<Long, UserDetails>(
			Long.class, UserDetails.class);

	@JsonIgnore
	public static UserDetails getUserByEmail(String uname) {
		return find.where().eq("email", uname).findUnique();
	}

	public static void update(UserDetails ud) {
		Ebean.update(ud);
	}

	@JsonIgnore
	public static UserDetails isUser(String uname, String pass) {
		return find.where().eq("email", uname).eq("password", pass)
				.findUnique();
	}

	@JsonIgnore
	public static UserDetails getPassword(String email) {
		return find.where().eq("email", email).findUnique();

	}
	
	@JsonIgnore
	public static List<UserDetails>getAllUsers(int pageNumber, int rowperpage){
		return find.
		setFirstRow(pageNumber * 10).setMaxRows(rowperpage)
		.findList();
	}
	
	@JsonIgnore
	public static int getAllUsersCount(int pageNo) {
		return find.setFirstRow(pageNo * 10).setMaxRows(UserDetails.find.findRowCount())
				.findList().size();
	}
}
