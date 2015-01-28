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
public class UserClearance extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String clearance;

	/*
	 * @ManyToMany public UserDetails user;
	 */

	public UserClearance() {

	}

	public static Finder<Long, UserClearance> find = new Finder<Long, UserClearance>(
			Long.class, UserClearance.class);

	@JsonIgnore
	public static List<UserClearance> getAllClearance() {
		return find.all();
	}

	@JsonIgnore
	public static UserClearance getClearanceById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static UserClearance getClearanceByName(String name) {
		return find.where().eq("clearance", name).findUnique();
	}

}
