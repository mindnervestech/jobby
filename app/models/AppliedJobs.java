package models;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	public int id;

	public String manadatorySkill;
	public String desiredSkil;
	public String workDesc;
	public String jobStatus;
	public String username;
	public String jobno;
	public String location;
	public String positionname;
	public String positiontype;
	public String reqType;
	public String performancelevel;
	public String clearancereq;

	public static Finder<Long, AppliedJobs> find = new Finder<Long, AppliedJobs>(
			Long.class, AppliedJobs.class);

	public static List<AppliedJobs> getAllJobs() {
		return find.all();

	}

	public static AppliedJobs getUserAppliedJob(String email, String reqNo) {
		return find.where().eq("username", email).eq("jobno", reqNo).findUnique();
	}
	
	public static AppliedJobs getUserAppliedJobDetails(String email, String reqNo ,Integer  pageNumber,Integer rowperpage) {
		String jobStatus = "Applied".trim();
		return find.where().eq("username", email).eq("jobno", reqNo).eq("jobStatus", jobStatus).findUnique();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypeAsc(int pageNo,String jobType) {
		return find.where().eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static AppliedJobs getUserAppliedJobById(int id){
		//int ids = Integer.parseInt(id);
		return find.where().eq("id",id).findUnique();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByEmail(int pageNo,String email) {
		return find.where().eq("username", email).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByEmailAndJobStatus(int pageNo,String email) {
		return find.where().eq("username", email).eq("jobStatus", "Applied".trim()).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static List<AppliedJobs>getUserAppliedJobByStatus(String email){
		String status =  "Draft";
		return find.where().eq("username", email).eq("jobStatus", status).findList();
	} 
	
	
	@JsonIgnore
	public static List<AppliedJobs> getAllAppliedJobs(Integer  pageNumber,Integer rowperpage,String jobStatus) {
		return find.where().eq("jobStatus", jobStatus).setFirstRow(pageNumber * 10).setMaxRows(rowperpage).findList();
	}
	
	@JsonIgnore
	public static int getAllAppliedJobsCount(int pageNo,String jobStatus) {
		return find.where().eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
}
