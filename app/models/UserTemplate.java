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
public class UserTemplate extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public int id;
	public String email;
	public String template;
	public String templateName;
	
	public UserTemplate(){
		
	}
	
	public static Finder<Long, UserTemplate> find = new Finder<Long, UserTemplate>(
			Long.class, UserTemplate.class);
	
	public static UserTemplate getUserTemplateByName(String templatename,String email){
		
		return find.where().eq("templateName", templatename).eq("email", email).findUnique();	
	}
	
	public static List <UserTemplate> getUserTemplateByName(String email){
		
		return find.where().eq("email", email).findList();
	} 
}
