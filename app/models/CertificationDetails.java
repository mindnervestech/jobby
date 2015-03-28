package models;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

@Entity
@Table(name = "certification_details")
public class CertificationDetails extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	public String certName;
	public String certYear;
	
	@ManyToOne
	public UserDetails user_details;

	public CertificationDetails() {
	}

	public static Finder<Long, CertificationDetails> find = new Finder<Long, CertificationDetails>(
			Long.class, CertificationDetails.class);

	@JsonIgnore
	public static List<CertificationDetails> getCertificateDetailsByUserEmail(
			String emailid) {
		return find.where().eq("user_details.email", emailid).findList();
	}

	@JsonIgnore
	public static CertificationDetails getCertificateById(int id) {
		return find.where().eq("id", id).findUnique();
	}

	@JsonIgnore
	public static CertificationDetails getCetificateByName(String name) {

		return find.where().eq("certName", name).findUnique();
	}
}
