package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Degree  extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String degreeName;
    
	public static Finder<Long , Degree> find  = new Finder<Long, Degree>(Long.class, Degree.class);
	
	public static  List<Degree> getAllDegress(){
		return  find.all();	
	}
	
	
}