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
public class UserPosition extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String position;

	/*
	 * @ManyToMany public UserDetails user;
	 */
	public UserPosition() {

	}

	public static Finder<Long, UserPosition> find = new Finder<Long, UserPosition>(
			Long.class, UserPosition.class);

	@JsonIgnore
	public static List<UserPosition> getAllPosition() {
		return find.where().order().asc("position").findList();
	}

	@JsonIgnore
	public static UserPosition getPositionById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static UserPosition getPositionByPosName(String pos) {
		return find.where().eq("position", pos).findUnique();
	}

	

}
