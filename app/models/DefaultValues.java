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
public class DefaultValues extends Model {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public int id;

	public int hours = 2080;
	public double fringe = 1.4127;
	public double overhead = 1.1469;
	public double ganda = 1.0480;
	public double gandaWrap = 1.697981;
	public double overheadWrap = 1.620145;
	public double fringeWrap = 1.412677;
	public double profit = 14;

	public DefaultValues() {

	}

	public static Finder<Long, DefaultValues> find = new Finder<Long, DefaultValues>(
			Long.class, DefaultValues.class);

	@JsonIgnore
	public static DefaultValues getDefaultValues() {
		return find.where().findUnique();
	}

}
