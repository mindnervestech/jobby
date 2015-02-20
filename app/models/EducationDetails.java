package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import play.db.ebean.Model;

@Entity
public class EducationDetails extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public int id;

	@Column(name = "institute_name")
	public String instituteName;

	@Column(name = "degree")
	public String degree;

	@Column(name = "startdate")
	public String fromDate;

	@Column(name = "enddate")
	public String toDate;

	@ManyToOne
	public UserDetails user_details;

	public EducationDetails() {
	}

	public static Finder<Long, EducationDetails> find = new Finder<Long, EducationDetails>(
			Long.class, EducationDetails.class);

	@JsonIgnore
	public static EducationDetails getEducationDetailsByName(String degree) {
		return find.where().eq("degree", degree).findUnique();
	}

	@JsonIgnore
	public static List<EducationDetails> getEducationDetailsByUserEmail(
			String emailid) {
		return find.where().eq("user_details.email", emailid).findList();
	}

	@JsonIgnore
	public void update(EducationDetails ud) {
		Ebean.update(ud);
	}
	@JsonIgnore
	public static List <EducationDetails> checkUserProfileFilldOrNotByEmail(
			String emailid) {
		return find.where().eq("user_details.email", emailid).findList();
	}
	

}
