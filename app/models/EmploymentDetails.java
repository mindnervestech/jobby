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
public class EmploymentDetails extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public int id;

	public String companyName;
	public String position;
	public String startdate;
	public String enddate;
	public String expdesc;

	@ManyToOne
	public UserDetails user_details;

	public EmploymentDetails() {
	}

	public static Finder<Long, EmploymentDetails> find = new Finder<Long, EmploymentDetails>(
			Long.class, EmploymentDetails.class);

	@JsonIgnore
	public static EmploymentDetails getEmploymentDetailsById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static List<EmploymentDetails> getEmploymentDetailsByUserEmail(
			String emailid) {
		return find.where().eq("user_details.email", emailid).findList();
	}

	@JsonIgnore
	public void update(EmploymentDetails ud) {
		Ebean.update(ud);
	}
}
