package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class UserExperiance extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String experianceLevel;
	
	
	
	public UserExperiance() {

	}

	public static Finder<Long, UserExperiance> find = new Finder<Long, UserExperiance>(
			Long.class, UserExperiance.class);

	@JsonIgnore
	public static List<UserExperiance> getAllExperiance() {
		return find.all();
	}

	@JsonIgnore
	public static UserExperiance getExperianceById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static UserExperiance getExperianceByExperianceName(String experianceLevel) {
		return find.where().eq("experianceLevel", experianceLevel).findUnique();
	}


}
