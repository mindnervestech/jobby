package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Month extends  Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String monthName;
	
	
	
	public static Finder<Long, Month> find = new Finder<Long, Month>(
			Long.class, Month.class);
	
	public static  List<Month> getAllMonth(){
		
		return find.all();
		
	}
	
	
}
