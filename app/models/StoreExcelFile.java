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
	public String duetoPmo;
	public String updateDate;
	public String duetoGovt;
	public String jobStatus = "active";
	
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
		return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.orderBy("workLocation").findList();
	}
	
	public static List<StoreExcelFile> getAllJobsByLocationDsc(int currentPage,
			int rowsPerPage) {
		return find.setFirstRow(currentPage * 10).setMaxRows(rowsPerPage)
				.orderBy("workLocation").findList();
	}

	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeAsc(
			int currentPage, int rowsPerPage, String jobType) {

		return find.where().eq("positionType", jobType).order().asc("workLocation").findList();

	
	}

	
	public static List<StoreExcelFile> getAllJobsByLocationAndJobTypeDsc(
			int currentPage, int rowsPerPage, String jobType) {

		return find.where().eq("positionType", jobType).order().desc("workLocation").findList();

	
	}
	
	@JsonIgnore
	public static long getAllJobsCount(int rowsPerPage) {
		long totalPages = 0, size = 0;
		size = find.findList().size();
		totalPages = size / rowsPerPage;

		if (size % rowsPerPage > 0) {
			totalPages++;
		}
		
		return totalPages;
	}

	public void update(StoreExcelFile storeExcelFile) {
		Ebean.update(storeExcelFile);
	}

	public static List<StoreExcelFile> getALlUserMatchedJobAsc(int rowsPerPage,
			int currentPage, ArrayList<String> al) {

		return find.where().in("labourCategory", al).order().desc("workLocation").findList();
	}
	
	
	public static List<StoreExcelFile> getALlUserMatchedJobDsc(int rowsPerPage,
			int currentPage, ArrayList<String> al) {

		return find.where().in("labourCategory", al).order().asc("workLocation").findList();
	}
	
	public static List<StoreExcelFile>getAllJobsForAdmin(int rowsPerPage,
			int currentPage){
		
		return find.all();
	}

	public static List<StoreExcelFile> getALlUserJobByPosition(int currentPage,
			int rowsPerPage, String position) {
		return find.where().eq("labourCategory", position).findList();

	}
	
	
	public static List<StoreExcelFile> getAllJobsForAdminDsc(int rowsPerPage,
			int currentPage) {

		return find.where().order().desc("workLocation").findList();
	}
	
	public static List<StoreExcelFile> getAllJobsForAdminAsc(int rowsPerPage,
			int currentPage) {

		return find.where().order().asc("workLocation").findList();
	}

	
	public static List<StoreExcelFile> getAllUserJobs() {

		return find.all();
	}
	
}
