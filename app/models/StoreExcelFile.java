package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StoreExcelFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String requestNumber;
	public String requestType;
	public String labourCategory;
	public String performanceLevel;
	public String positionType;
	public String clearanceRequired;
	public String workLocation;
	public String workDescription;
	public String manadatorySkills;
	public String desiredSkill;
	public String certificationRequired;
	public String conusTravel;
	public String oconusTravel;
	public String reghrperYear;
	public String scheduleComments;
	public String nonpubComments;
	public String missionCritical;
	public String nightWork;
	public String localTravalusingpub;
	public String pagerDuty;
	public String pagerdutyComments;
	public String workonHoliday;
	public String workonWeekends;
	public String shiftWork;
	public String warzone;
	public String coop;
	//SCHEDULED CLOSE DATE value stored
	public String scheduledCloseDate; 
	public String updateDate;
	//DATE OF STATUS CHANGE value stored
	public String dateofStatus;
	public String jobStatus = "active";
	public String maxOffer = "0"; 
	
	public StoreExcelFile() {

	}

	public void save(StoreExcelFile storeExcel) {
		Ebean.save(storeExcel);
	}

	public static Finder<Long, StoreExcelFile> find = new Finder<Long, StoreExcelFile>(
			Long.class, StoreExcelFile.class);

	public static StoreExcelFile getregNumber(String reqId) {
		return find.where().eq("requestNumber", reqId).findUnique();
	}

	public static List<StoreExcelFile> getAllJobs(int currentPage,
			int rowsPerPage) {

		return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.findList();
	}

	public static List<StoreExcelFile> getAllJobsByJobType(int currentPage,
			int rowsPerPage, String jobType) {

		return find.where().eq("positionType", jobType.trim())
				.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.orderBy("workLocation").findList();
	}

	public static List<StoreExcelFile> getAllJobsByLocationAsc(int currentPage,
			int rowsPerPage) {
		String jobStatus = "active";
		return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.order().asc("workLocation").findList();
	}
	
	public static List<StoreExcelFile> getAllJobsByLocationDsc(int currentPage,
			int rowsPerPage) {
		
		String jobStatus = "active";
		return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.order().desc("workLocation").findList();
	}

	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeAsc(
			int currentPage, int rowsPerPage, String jobType,ArrayList<String> al) {

		return find.where().eq("positionType", jobType).in("labourCategory", al).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

	
	}

	
	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeDsc(
			int currentPage, int rowsPerPage, String jobType, ArrayList<String> al) {
		
		return find.where().eq("positionType", jobType).in("labourCategory", al).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).orderBy().desc("workLocation").findList();

	
	}
	
	@JsonIgnore
	/*public static long getAllJobsCount(int rowsPerPage) {
		long totalPages = 0, size = 0;
		size = find.findList().size();
		totalPages = size / rowsPerPage;

		if (size % rowsPerPage > 0) {
			totalPages++;
		}
		
		return totalPages;
	}*/

	
	public void update(StoreExcelFile storeExcelFile) {
		Ebean.update(storeExcelFile);
	}

	@JsonIgnore
	public static List<StoreExcelFile> getALlUserMatchedJobAsc(int  currentPage,
			int rowsPerPage, ArrayList<String> al) {
		return find.where().in("labourCategory", al).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	
	@JsonIgnore
	public static List<StoreExcelFile> getALlUserMatchedJobDsc(int currentPage,
			int rowsPerPage, ArrayList<String> al) {
		return find.where().in("labourCategory", al).order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	@JsonIgnore
	public static List<StoreExcelFile>getAllJobsForAdmin(int rowsPerPage,
			int currentPage){
		
		return find.all();
	}

	@JsonIgnore
	public static List<StoreExcelFile> getALlUserJobByPosition(int currentPage,
			int rowsPerPage, String position) {
		return find.where().eq("labourCategory", position).findList();

	}
	
	
	
	public static List<StoreExcelFile> getAllJobsForAdminDsc(int  currentPage,
			int rowsPerPage) {
	//	String  jobType = "Full-Time";
		return find.where().order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();

		//return find.where().order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	}
	
	public static List<StoreExcelFile> getAllJobsForAdminAsc(int  currentPage,
			int rowsPerPage) {

		//return find.where().order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
	//	String  jobType = "Full-Time";
		return find.where().order().asc("workLocation").order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();

	
	}

	
	public static List<StoreExcelFile> getAllUserJobs() {

		return find.all();
	}

	@JsonIgnore
	public static int getAllJobsCount(int pageNo) {
		String jobStatus = "active";
		return find.where().eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllJobsCountAdmin(int pageNo) {
		String jobStatus = "active";
		return find.setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	
	@JsonIgnore
	public static int getAllJobsCountSearchIdAdmin(int pageNo,String saearchId) {
		String jobStatus = "active";
		return find.where().like("requestNumber", "%"+saearchId+"%").setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByPositionMatched(int pageNo,ArrayList<String> al) {
		String jobStatus = "active";
		return find.where().in("labourCategory", al).eq("jobStatus",jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	

	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypePositionAsc(int pageNo,String jobType,ArrayList<String> al) {
		String jobStatus = "active"; 
		return find.where().eq("positionType", jobType).eq("jobStatus",jobStatus).in("labourCategory", al).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypeAsc(int pageNo,String jobType) {
		return find.where().eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypePositionDsc(int pageNo,String jobType,ArrayList<String> al) {
		String jobStatus = "active"; 
		return find.where().eq("positionType", jobType).eq("jobStatus",jobStatus).in("labourCategory", al).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getAllJobsCountByLocationAndJobTypeDsc(int pageNo,String jobType) {
		return find.where().eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeAdminDsc(
			int currentPage, int rowsPerPage, String jobType) {

		return find.where().eq("positionType", jobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

	
	}
	
	@JsonIgnore
	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeAdminAsc(
			int currentPage, int rowsPerPage, String jobType) {

		return find.where().eq("positionType", jobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

	
	}
	
	@JsonIgnore
	public static List<StoreExcelFile> getAllJobsByLocationJobTypeAsc(int currentPage, int rowsPerPage, String jobType){
		String jobStatus = "active";
		return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

	}
	
	
	@JsonIgnore
	public static int getAllJobsCountByLocationJobTypeAsc(int pageNo,String jobType) {
		String jobStatus = "active";
		return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	
	@JsonIgnore
	public static List<StoreExcelFile> getAllJobsByLocationJobTypeDesc(int currentPage, int rowsPerPage, String jobType){
		String jobStatus = "active";
		return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

	}
	
	
	@JsonIgnore
	public static int getAllJobsCountByLocationJobTypeDesc(int pageNo,String jobType) {
		String jobStatus = "active";
		return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	/*//get list of all jobs by location Desc
	@JsonIgnore
	public static List<StoreExcelFile> getAllJobsByLocationDsc(int currentPage, int rowsPerPage){
		return find.where().setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

	}*/
	
	@JsonIgnore
	public static int getAllJobsByLocationCountDesc(int pageNo,String jobType) {
		String jobStatus = "active";
		return find.where().eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
				.findList().size();
	}
	
	
	/*//get list of all jobs by location Asc
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsByLocationAsc(int currentPage, int rowsPerPage){
			return find.where().setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}*/
		
		
		@JsonIgnore
		public static int getAllJobsByLocationCountAsc(int pageNo,String jobType) {
		String	jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
	
		@JsonIgnore
		public static StoreExcelFile getJobById(String reqId) {
			return find.where().eq("id", reqId).findUnique();
		}
		
		public void delete(StoreExcelFile storeExcelFile) {
			Ebean.delete(storeExcelFile);
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserTypeAsc(int currentPage, int rowsPerPage,String jobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserTypeDesc(int currentPage, int rowsPerPage,String jobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionAdminAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionSearchIdAdminAsc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").
					setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionSearchIdAdminDesc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").
					setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("labourCategory").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionAdminDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionTypeAsc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionTypeDesc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("labourCategory").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserLocationAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserLocationTypeAsc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserLocationDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserLocationTypeDesc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserClearanceAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserClearanceTypeAsc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearanceRequired").findList();

		}
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserClearanceDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearanceRequired").findList();

		}
	
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserClearanceTypeDesc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserExpAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performanceLevel").findList();

		}
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserExpTypeAsc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performanceLevel").findList();

		}
	
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserExpDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performanceLevel").findList();

		}
	
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserExpTypeDesc(int currentPage, int rowsPerPage,String JobType){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", JobType).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performanceLevel").findList();

		}
		
		@JsonIgnore
		public static int getAllJobsCountByuserPositionMatched(int pageNo,ArrayList<String> al) {
		String	jobStatus = "active";
			return find.where().in("labourCategory", al).eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		
		@JsonIgnore
		public static int getAllJobsCountByuserExperiancedMatched(int pageNo,ArrayList<String> al) {
		String	jobStatus = "active";
			return find.where().in("labourCategory", al).eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static int getAllJobsCountByuserLocationMatched(int pageNo,ArrayList<String> al) {
		String	jobStatus = "active";
			return find.where().in("labourCategory", al).eq("jobStatus", jobStatus).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserPositionMatchedAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserLocationMatchedAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserClearanceMatchedAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserExperiancedMatchedAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserExperiancedMatchedDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserClearanceMatchedDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().desc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserLocationMatchedDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserPositionMatchedDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al) {
			return find.where().in("labourCategory", al).order().desc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static int getAllJobsCountByuserPositionMatchedJobType(int pageNo,ArrayList<String> al,String jobType) {
		String	jobStatus = "active";
			return find.where().in("labourCategory", al).eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static int getAllJobsCountByuserLocationMatchedJobType(int pageNo,ArrayList<String> al,String jobType) {
		String	jobStatus = "active";
			return find.where().in("labourCategory", al).eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserPositionMatchedJobTypeAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserLocationMatchedJobTypeDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserLocationMatchedJobTypeAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserExperiancedMatchedJobTypeDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserExperiancedMatchedJobTypeAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserClearanceMatchedJobTypeDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserClearanceMatchedJobTypeAsc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAlluserPositionMatchedJobTypeDesc(int currentPage,
				int rowsPerPage, ArrayList<String> al,String jobType) {
			return find.where().in("labourCategory", al).eq("positionType", jobType).order().desc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static int getAllJobsCountjobType(int pageNo,String jobType) {
		String	jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static int getAllJobsCountjobTypeAdmin(int pageNo,String jobType) {
		String	jobStatus = "active";
			return find.where().eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static int getAllJobsCountjobTypeSearchIdAdmin(int pageNo,String jobType,String SearchId) {
		String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+SearchId+"%").setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static int getAllJobsCountLocationjobType(int pageNo,String jobType) {
		String	jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).eq("positionType", jobType).setFirstRow(pageNo * 10).setMaxRows(StoreExcelFile.find.findRowCount())
					.findList().size();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeAdminAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeSearchIdAdminAsc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeSearchIdAdminDesc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().desc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeAdminDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().asc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByPositionJobTypeDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().desc("labourCategory").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}

		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationAdminAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationSearchAdminAsc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().eq("requestNumber", searchId).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationSearchAdminDesc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationAdminDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}
		
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceAdminAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearanceRequired").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceSearchAdminAsc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceSearchAdminDesc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceAdminDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performanceLevel").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceAdminAsc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performanceLevel").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceSearchAdminAsc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("performanceLevel").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceSearchAdminDesc(int currentPage, int rowsPerPage,String searchId){
			String jobStatus = "active";
			return find.where().like("requestNumber", "%"+searchId+"%").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performanceLevel").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceAdminDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performanceLevel").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperianceDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("performanceLevel").findList();

		}
		
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("clearanceRequired").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationDesc(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().desc("workLocation").findList();

		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsCountLocationjobType(int currentPage, int rowsPerPage){
			String jobStatus = "active";
			return find.where().eq("jobStatus", jobStatus).setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).order().asc("workLocation").findList();

		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeAdminAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeSearchAdminAsc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().asc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeSearchAdminDesc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeAdminDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeAdminAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}

		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeSearchAdminAsc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().asc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeSearchAdminDesc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().desc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeAdminDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().desc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeAsc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).eq("requestNumber", searchId).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeAdminAsc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeSearchAdminAsc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().asc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeSearchAdminDesc(int currentPage,
				int rowsPerPage,String jobType,String searchId) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).like("requestNumber", "%"+searchId+"%").order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeAdminDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		/*@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByExperienceJobTypeDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().desc("performanceLevel").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		*/
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByClearanceJobTypeDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().desc("clearanceRequired").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		@JsonIgnore
		public static List<StoreExcelFile> getAllJobsForUserByLocationJobTypeDesc(int currentPage,
				int rowsPerPage,String jobType) {
			String	jobStatus = "active";
			return find.where().eq("positionType", jobType).eq("jobStatus", jobStatus).order().desc("workLocation").setFirstRow(currentPage * 10).setMaxRows(rowsPerPage).findList();
		}
		
		public static List <StoreExcelFile> getAllJobstoSchedular(){
			return find.all(); 
		}
}
