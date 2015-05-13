package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Year extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public int year;
	
	public static Finder<Long, Year> find = new Finder<Long, Year>(
			Long.class, Year.class);
	
	
	public static  List<Year> getAllYear(){
		
		return find.all();
		
	}
}
