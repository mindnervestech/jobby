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
	public String skills;
    public String archived ="N"; 
    public String duetoPmo;
     
	public static Finder<Long, AppliedJobs> find = new Finder<Long, AppliedJobs>(
			Long.class, AppliedJobs.class);

	public static List<AppliedJobs> getAllJobs() {
		return find.all();

	}

	public static AppliedJobs getUserAppliedJob(String email, String reqNo) {
		return find.where().eq("username", email).eq("jobno", reqNo).findUnique();
	}
	
	public static List<AppliedJobs> getUserAppliedJobDetails(String email,Integer  pageNumber,Integer rowperpage) {
		String jobStatus = "Applied".trim();
		return find.where().eq("username", email).eq("jobStatus", jobStatus).findList();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypeAsc(int pageNo,String jobType) {
		return find.where().eq("positiontype", jobType).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
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
	public static int getAllJobsCountByEmailAndJobStatus(int pageNo,String email,String jobType) {
		return find.where().eq("username", email).eq("jobStatus", "Applied".trim()).eq("positiontype", jobType).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static List<AppliedJobs>getUserAppliedJobByStatus(String email){
		String status =  "Draft";
		return find.where().eq("username", email).eq("jobStatus", status).findList();
	} 
	
	
	@JsonIgnore
	public static List<AppliedJobs> getAllAppliedJobs(Integer  pageNumber,Integer rowperpage,String jobStatus) {
		String archived = "N";
		return find.where().eq("jobStatus", jobStatus).eq("archived",archived ).setFirstRow(pageNumber * 10).setMaxRows(rowperpage).findList();
	
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllArchivedJobs(Integer  pageNumber,Integer rowperpage,String jobStatus) {
		String archived = "Y";
		return find.where().eq("jobStatus", jobStatus).eq("archived",archived ).setFirstRow(pageNumber * 10).setMaxRows(rowperpage).findList();
	
	}
	
	
	
	@JsonIgnore
	public static int getAllAppliedJobsCount(int pageNo,String jobStatus) {
		String  archived = "N";
		return find.where().eq("jobStatus", jobStatus).eq("archived", archived).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllArchivedJobsCount(int pageNo,String jobStatus) {
		String  archived = "Y";
		return find.where().eq("jobStatus", jobStatus).eq("archived", archived).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	
	@JsonIgnore
	public static int getAllAppliedJobsCountByEmail(int pageNo,String emailId) {
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", emailId).setFirstRow(pageNo * 10).setMaxRows(AppliedJobs.find.findRowCount())
				.findList().size();
	}
	
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByPositionAsc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username",email ).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("positionname").findList();

	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByPositionDesc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username",email ).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("positionname").findList();

	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByPositionJobTypeAsc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().asc("positionname").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByPositionJobTypeDesc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().desc("positionname").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	/*@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByPositionJobTypeAsc(int currentPage,
			int rowsPerPage,String jobType) {
		String	jobStatus = "active";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).order().asc("location").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}*/
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByLocationAsc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("location").findList();

	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByLocationDesc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("location").findList();

	}
	
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByLocationJobTypeAsc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().asc("location").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByLocationJobTypeDesc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().desc("location").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}

	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByClearanceAsc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearancereq").findList();

	}

	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByClearanceDesc(int currentPage, int rowsPerPage,String email){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearancereq").findList();

	}
	
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByClearanceJobTypeAsc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().asc("clearancereq").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByClearanceJobTypeDesc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().desc("clearancereq").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByExperianceAsc(int currentPage, int rowsPerPage,String email   ){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performancelevel").findList();

	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByExperianceDesc(int currentPage, int rowsPerPage,String email   ){
		String jobStatus = "Applied";
		return find.where().eq("jobStatus", jobStatus).eq("username", email).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performancelevel").findList();

	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByExperienceJobTypeAsc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<AppliedJobs> getAllJobsForUserByExperienceJobTypeDesc(int currentPage,
			int rowsPerPage,String jobType,String email) {
		String	jobStatus = "Applied";
		return find.where().eq("positiontype", jobType).eq("jobStatus", jobStatus).eq("username", email).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static AppliedJobs getUserAppliedJobByReqNumber(String id){
		//int ids = Integer.parseInt(id);
		return find.where().eq("jobno",id).findUnique();
	}
	
}
