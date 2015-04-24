package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
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
	public String level;
	public String rate;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRate() {
		return rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

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
	
	@JsonIgnore
	public static UserPosition getRecoredByPositionNameAndLevel(String pos,String level) {
		return find.where().eq("position", pos).eq("level", level).findUnique();
	}
	
	@JsonIgnore
	public static UserPosition getRecoredByPositionNameAndLevelAsc(String pos,String level) {
		return find.where().eq("position", pos).eq("level", level).order().asc("level").findUnique();
	}

	public void save(UserPosition userPosition) {
		Ebean.save(userPosition);
	}
	
	
	public void update(UserPosition userPosition) {
		Ebean.update(userPosition);
	}

	@JsonIgnore
	public static List<SqlRow> getAllDistinctPositionForAdmin() {
		
		SqlQuery q = Ebean.createSqlQuery("select distinct position from user_position");
		List<SqlRow> rows = q.findList();
		
		return rows;
	  	/*return find.select("position").setDistinct(true).where().findList();*/
	}
	
	@JsonIgnore
	public static List<UserPosition> getAllPositionForAdmin(String position) {
	  	return find.where().eq("position", position).order().asc("level").findList();
	}
	
	@JsonIgnore
	public static List<UserPosition> getPositionListByPosName(String position) {
	  	return find.where().eq("position", position).order().asc("level").findList();
	}
	
	 
	
	
}
