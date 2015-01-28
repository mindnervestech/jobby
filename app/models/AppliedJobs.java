package models;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class AppliedJobs extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String manadatorySkill;
	public String desiredSkil;
	public String jobStatus;
	public String username;
	public String jobno;
	public String location;
	public String positionname;

	public static Finder<Long, AppliedJobs> find = new Finder<Long, AppliedJobs>(
			Long.class, AppliedJobs.class);

	public static List<AppliedJobs> getAllJobs() {
		return find.all();

	}

	public static AppliedJobs getUserAppliedJob(String email, String reqNo) {
		return find.where().eq("username", email).eq("jobno", reqNo)
				.findUnique();
	}

}
