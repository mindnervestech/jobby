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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String email;
	public String password;
	public String fullname;
	public String gender;
	public String dob;

	@ManyToMany
	public List<UserSkill> userSkill;

	@ManyToMany
	public List<UserClearance> userClearance;

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
}
