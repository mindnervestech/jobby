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
public class UserSkill extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String skillName;

	/*
	 * @ManyToMany public UserDetails user;
	 */
	public static Finder<Long, UserSkill> find = new Finder<Long, UserSkill>(
			Long.class, UserSkill.class);

	@JsonIgnore
	public static List<UserSkill> getAllSkills() {
		return find.all();
	}

	@JsonIgnore
	public static UserSkill getSkillById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static UserSkill getSkillByName(String name) {
		return find.where().eq("skillName", name).findUnique();
	}

	public UserSkill() {

	}
}
