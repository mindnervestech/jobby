package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
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
    public Date  lastlogin;
    public String userLoggedInstatus;
    
    public String altemail;
    public String residentcity;
    public String altphnumber;
    public String residentState;
    public String zipcode;
    public String desiredsalary;
    public String willingtorelocate ;
    public String jobsearchstatus;
    public String currentjobtitle;
    public String phnumber;
    public String emailalert;
    
    
    public String getResidentcity() {
		return residentcity;
	}

	public void setResidentcity(String residentcity) {
		this.residentcity = residentcity;
	}

	public String getAltemail() {
		return altemail;
	}

	public void setAltemail(String altemail) {
		this.altemail = altemail;
	}

	
    public String getPhnumber() {
		return phnumber;
	}

	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}

	public String getAltphnumber() {
		return altphnumber;
	}

	public void setAltphnumber(String altphnumber) {
		this.altphnumber = altphnumber;
	}

	public String getResidentState() {
		return residentState;
	}

	public void setResidentState(String residentState) {
		this.residentState = residentState;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDesiredsalary() {
		return desiredsalary;
	}

	public void setDesiredsalary(String desiredsalary) {
		this.desiredsalary = desiredsalary;
	}

	public String getWillingtorelocate() {
		return willingtorelocate;
	}

	public void setWillingtorelocate(String willingtorelocate) {
		this.willingtorelocate = willingtorelocate;
	}

	public String getJobsearchstatus() {
		return jobsearchstatus;
	}

	public void setJobsearchstatus(String jobsearchstatus) {
		this.jobsearchstatus = jobsearchstatus;
	}

	public String getCurrentjobtitle() {
		return currentjobtitle;
	}

	public void setCurrentjobtitle(String currentjobtitle) {
		this.currentjobtitle = currentjobtitle;
	}

	
    //public String phnumber;
  
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
	public static int getAllUsersCount(int pageNumber) {
		// TODO Auto-generated method stub
		return find.setFirstRow(pageNumber * 10).setMaxRows(UserDetails.find.findRowCount())
				.findList().size();
	}
	
	@JsonIgnore
	public static int getActiveUserCount(int  pageNumber) {
		// TODO Auto-generated method stub
		String userLoggedInstatus = "loggedIn";
		return find.where().eq("userLoggedInstatus", userLoggedInstatus).
				setFirstRow(pageNumber * 10).setMaxRows(UserDetails.find.findRowCount())
				.findList().size();
	}
	
	
	public static List<UserDetails> getallUserEmail(){
		return find.where().eq("emailalert", "Yes").findList();
	}  
}
