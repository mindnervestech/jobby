package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class JobSearchStatus  extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;
	public String statusName;

	public static Finder<Long, JobSearchStatus> find = new Finder<Long, JobSearchStatus>(
			Long.class, JobSearchStatus.class);


	public static List<JobSearchStatus>  getAllJobSearchStatus(){
	
		return find.all();
	
	}

}
