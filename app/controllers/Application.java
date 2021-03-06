package controllers;

import java.io.File;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import models.Admin;
import models.AppliedJobs;
import models.CertificationDetails;
import models.DefaultValues;
import models.Degree;
import models.EducationDetails;
import models.EmploymentDetails;
import models.JobSearchStatus;
import models.Month;
import models.SendEmailAlert;
import models.States;
import models.StoreExcelFile;
import models.UserClearance;
import models.UserDetails;
import models.UserExperiance;
import models.UserPosition;
import models.UserSkill;
import models.UserTemplate;
import models.Year;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import views.html.index;
import views.html.register;
import views.html.registrationredirect;
import views.html.signin;

import Utility.MailUtility;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.List;;


public class Application extends Controller {
	public static Result index() {
		return ok();
	}

	public static Result login() {
		return ok(signin.render());
	}

	public static Result signup() {
		return ok(register.render());
	}
	
	public static Result registrationRedirect() {
	
		return ok(registrationredirect.render());
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class UserRegisterVM{
		String email;
		String password;
		String firstname;
		String middlename;
		String lastname ;
		String gender ; 
		
	}
	
	
	public static Result createNewUser() {

		Form<UserVM> form = DynamicForm.form(UserVM.class)
				.bindFromRequest();
		UserVM ui = form.get();
				System.out.println(form);
				UserDetails existingUser = UserDetails.getUserByEmail(ui.email);
				if (existingUser == null) {
				UserDetails u = new UserDetails();
				u.setEmail(ui.email);
				u.setDob(ui.dob);
				u.setPassword(ui.password);
				u.setFirstname(ui.firstname);
				u.setMiddlename(ui.middlename);
				u.setLastname(ui.lastname);
				u.setJobsearchstatus(ui.jobsearchstatus);
				u.setResidentcity(ui.residentcity);
				//u.setAltphnumber(ui.altphnumber);
				u.setResidentState(ui.residentState);
				u.setZipcode(ui.zipcode);
				u.setDesiredsalary(ui.desiredsalary);
				u.setWillingtorelocate(ui.willingtorelocate);
				u.setJobsearchstatus(ui.jobsearchstatus);
				u.setCurrentjobtitle(ui.currentjobtitle);
				u.setGender(ui.gender);
				u.setAltemail(ui.altemail);
				u.setUserstatus("active");
				u.setEmailalert("Yes");
				
				//ui.userPosition;
				
				Ebean.save(u);
				
				u.deleteManyToManyAssociations("userExperiance");

				String s = ui.userExperience;

				UserExperiance ue = UserExperiance.getExperianceByExperianceName(s);
				u.userExperiance.add(ue);
				u.saveManyToManyAssociations("userExperiance");
			
				u.deleteManyToManyAssociations("userPosition");
				
				/*ArrayNode brandIds = (ArrayNode) Json.parse(form.get("brandIds"));
				for(int i=0;i<brandIds.size();i++){
				s.brandIds.add(brandIds.get(i).asLong());
				}
				*/
				
				System.out.println(ui.userPosition.size());
				for (int j = 0; j < ui.userPosition.size(); j++) {
				String position = ui.userPosition.get(j);
						UserPosition up = UserPosition.getRecoredByPositionNameAndLevel(position,ui.userExperience);
						if(up != null){
						System.out.println("positon: "+position);
							u.userPosition.add(up);
						} 
				}
				u.saveManyToManyAssociations("userPosition");
				
				u.deleteManyToManyAssociations("userClearance");
				
					String clea = ui.userClearance;
					UserClearance uc = UserClearance.getClearanceByName(clea);
					u.userClearance.add(uc);

				    u.saveManyToManyAssociations("userClearance");
				    u.save();
				    
				    
				    MailUtility mail = new MailUtility();

				    mail.sendRegistrationMail(ui.email,ui.password);
					mail.sendMailToAdminAboutNewUserRegistration(ui.email); 
				    
				    flash("registration_success", " Account is created ! Please  log in");
					return redirect("/registrationredirect");
				    
				} else {
					flash("error", "Email ID Already Exist");
					return redirect("/signup");
				}
				
			
			
		
	}

	
	// called when user login to get there details
	public static Result getUserName() {
		String uname = session().get("email");

		System.out.println("Akash Session getUserName:"+uname);
		Admin ad = Admin.checkAdmin(uname);
		if (ad == null) {
			UserDetails ud = UserDetails.getUserByEmail(uname);

			if (ud != null) {
				return ok(ud.firstname);
			}
		}

		return ok(ad.username);
	}

	// called to check the usertype and also when page is refreshed
	public static Result checkForadmin() {
		String email = session().get("email");
		System.out.println("Akash Session checkForadmin "+session().get("email"));
		//System.out.println("checkForadmin: "+email);
		Admin ad = Admin.checkAdmin(email);
		if (ad == null) {
			return ok("notAdmin");
		} else {
			return ok((Json.stringify(Json.toJson(ad))));
		}

	}

	public static Result SignIn() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		String uname = dynamicForm.get("username");
		String pass = dynamicForm.get("password");
		Admin as = Admin.isAdmin(uname, pass);
		
		if (as != null) {
			session().clear();
			System.out.println("After putting inSession" + session().get("email"));
			session().put("email", as.username);
			System.out.println("Before putting inSession" + session().get("email"));
			session().put("role", as.role);
			return redirect("/dashboard#/viewAllJobsforAdmin");

		} else {

			UserDetails ud = UserDetails.isUser(uname, pass);

			if (ud != null) {
				if ("active".equalsIgnoreCase(ud.userstatus)) {
					session().clear();
					System.out.println("Akash  Before Sign In: "+session().get("email"));
					session().put("email", ud.email);
					
					System.out.println("Akash After putting inSession" + session().get("email"));
					  ud.lastlogin = new Date();
					  ud.userLoggedInstatus = "loggedIn";
					  ud.update();
					// check for the user profile filled or not if filled
					// redirect to vieew jobd else redirect to user profile page
					List<EducationDetails> edu = EducationDetails
							.checkUserProfileFilldOrNotByEmail(uname);
					if (edu.size() == 0) {
						return redirect("/dashboard#/extra-profile");
					} else {
						return redirect("/dashboard#/viewJobs");
					}

				} else {
					flash().put("AccountError",
							"Your account has been deactivated, please contact and admin for assistance ");
					return ok(signin.render());
				}

			} else {
				flash().put("error", "Login Failed");
				return ok(signin.render());
			}

		}
		// return ok();

	}

	// used to send the password to sign in user
	public static Result forgetPassword(String uname) {
		/*
		 * DynamicForm dynamicForm = Form.form().bindFromRequest(); String uname
		 * = dynamicForm.get("email");
		 */
		
		UserDetails ud = UserDetails.getPassword(uname);
		if(ud != null){
			MailUtility mailUtil = new MailUtility();
			mailUtil.sendMailForgetpassword(ud.email,ud.password);
			
		flash().put("email_success", "Password has been sent to your registered email");
		return ok(signin.render());
		}else{
			
			flash().put("email_error", "You are not Registered ,Please register and then  try!  ");
			return ok(signin.render());
		}
	}

	
	
	// upload the excel
	public static Result uploadandStoreExcel() {
		play.mvc.Http.MultipartFormData body = request().body()
				.asMultipartFormData();
		MultipartFormData.FilePart excelpart = body.getFile("file");
		File excelfile = excelpart.getFile();
		String filename = excelpart.getFilename();
		Workbook wb_xssf; //Declare XSSF WorkBook
	    Workbook wb_hssf;//Declare HSSf WorkBook
		
		int newRows = 0;
		int updatedRows = 0;
		Sheet sheet = null;
		String  jobNum = ""; 
		String userPosition = "";
		try {
			
			 FileInputStream file = new FileInputStream(excelfile);
			 String fileExtn = FilenameUtils.getExtension(filename);
			
			 if (fileExtn.equalsIgnoreCase("xlsx")){
			       wb_xssf = new XSSFWorkbook(file);
			       
			       sheet = wb_xssf.getSheetAt(0);
		      }

			 if (fileExtn.equalsIgnoreCase("xls")){
			      POIFSFileSystem fs = new POIFSFileSystem(file);
		    	  wb_hssf = new HSSFWorkbook(fs);
		    	  sheet = wb_hssf.getSheetAt(0);
		      }
			 
			StoreExcelFile storeExcelFile = null;
			Row row;
			String reqNo = null;
			String posName = "";
			String level = "" ;
 			
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				reqNo = null;
				row = rowIterator.next();
				if (!row.getZeroHeight()) {

					StoreExcelFile sd = new StoreExcelFile();
					Cell c = row.getCell(0);

					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.requestNumber = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:
						//System.out.println("nukmberString");
						storeExcelFile = StoreExcelFile.getregNumber(c
								.getStringCellValue());
						jobNum =  c
								.getStringCellValue();
						if (storeExcelFile != null) {
							storeExcelFile.requestNumber = c
									.getStringCellValue();
							reqNo = c.getStringCellValue();

						} else {
							sd.requestNumber = c.getStringCellValue();
							
						}

						break;
					}

					c = row.getCell(1);

					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.requestType = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:
						if (storeExcelFile != null) {
							storeExcelFile.requestType = c.getStringCellValue();
						} else {
							sd.requestType = c.getStringCellValue();
						}

						break;
					}

					c = row.getCell(2);
					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.labourCategory = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:
						
						posName =c.getStringCellValue();
						
						if (storeExcelFile != null) {
							storeExcelFile.labourCategory = c
									.getStringCellValue();
						} else {
							sd.labourCategory = c.getStringCellValue();
							userPosition = sd.labourCategory;
							
						
						}

						break;
					}

					c = row.getCell(3);
					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.performanceLevel = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:

						UserExperiance ue = UserExperiance
								.getExperianceByExperianceName(c
										.getStringCellValue());
						if (ue == null) {
							UserExperiance u = new UserExperiance();
							u.experianceLevel = c.getStringCellValue();
							u.save();
						}

						
						
						if (storeExcelFile != null) {
							storeExcelFile.performanceLevel = c
									.getStringCellValue();
							UserPosition up = UserPosition.getRecoredByPositionNameAndLevelAsc(posName,c.getStringCellValue());
						    DefaultValues df =  DefaultValues.getDefaultValues();
							if(up != null){
								double result   = ((Double.parseDouble(up.rate) * df.hours) / (df.gandaWrap *( 1 + (df.profit/100))));
								double rounded = (double) Math.round(result * 100) / 100;
								
								storeExcelFile.maxOffer = Double.toString(rounded);
							}
						
						} else {
							
							UserPosition up = UserPosition.getRecoredByPositionNameAndLevelAsc(posName,c.getStringCellValue());
						    DefaultValues df =  DefaultValues.getDefaultValues();
							
						    if(up != null){
								double result   = ((Double.parseDouble(up.rate) * df.hours) / (df.gandaWrap *( 1 + (df.profit/100))));
								double rounded = (double) Math.round(result * 100) / 100;
								sd.maxOffer = Double.toString(rounded);
								
							}
							sd.performanceLevel = c.getStringCellValue();
						}

						break;
					}

					c = row.getCell(4);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.positionType = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.positionType = c
										.getStringCellValue();
							} else {
								sd.positionType = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(5);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.clearanceRequired = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:

							// used to add the clearance in UserClearance model
							// when not present
							UserClearance uc = UserClearance
									.getClearanceByName(c.getStringCellValue());
							if (uc == null) {
								UserClearance ucc = new UserClearance();
								ucc.clearance = c.getStringCellValue();
								ucc.save();

							}

							if (storeExcelFile != null) {
								storeExcelFile.clearanceRequired = c
										.getStringCellValue();
							} else {
								sd.clearanceRequired = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(6);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.workLocation = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.workLocation = c
										.getStringCellValue();
							} else {
								sd.workLocation = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(7);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.workDescription = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.workDescription = c
										.getStringCellValue();
							} else {
								sd.workDescription = c.getStringCellValue();
							}
							break;
						}
					}

					c = row.getCell(8);

					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.manadatorySkills = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.manadatorySkills = c
										.getStringCellValue();
							} else {
								sd.manadatorySkills = c.getStringCellValue();
							}
							break;
						}
					}

					c = row.getCell(9);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.desiredSkill = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.desiredSkill = c
										.getStringCellValue();
							} else {
								sd.desiredSkill = c.getStringCellValue();
							}
							break;
						}
					}

					c = row.getCell(10);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.certificationRequired = c.getNumericCellValue()
									+ "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.certificationRequired = c
										.getStringCellValue();
							} else {
								sd.certificationRequired = c
										.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(11);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.conusTravel = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.conusTravel = c
										.getStringCellValue();
							} else {
								sd.conusTravel = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(12);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.oconusTravel = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.oconusTravel = c
										.getStringCellValue();
							} else {
								sd.oconusTravel = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(13);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.reghrperYear = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.reghrperYear = c
										.getStringCellValue();
							} else {
								sd.reghrperYear = c.getStringCellValue();
							}

							break;
						}
					}
					
					
					
					c = row.getCell(14);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							
							sd.extrahoursperyear = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.extrahoursperyear = c
										.getStringCellValue();
							} else {
								sd.extrahoursperyear = c.getStringCellValue();
							}

							break;
						}
					}
					
					//change to 15 from here
					
					c = row.getCell(15);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.scheduleComments = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.scheduleComments = c
										.getStringCellValue();
							} else {
								sd.scheduleComments = c.getStringCellValue();
							}

							break;
						}
					}

					if (c != null) {
						c = row.getCell(16);
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.nonpubComments = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.nonpubComments = c
										.getStringCellValue();
							} else {
								sd.nonpubComments = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(17);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.missionCritical = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.missionCritical = c
										.getStringCellValue();
							} else {
								sd.missionCritical = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(18);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.nightWork = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.nightWork = c
										.getStringCellValue();
							} else {
								sd.nightWork = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(19);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.localTravalusingpub = c.getNumericCellValue()
									+ "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.localTravalusingpub = c
										.getStringCellValue();
							} else {
								sd.localTravalusingpub = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(20);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.pagerDuty = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.pagerDuty = c
										.getStringCellValue();
							} else {
								sd.pagerDuty = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(21);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.pagerdutyComments = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.pagerdutyComments = c
										.getStringCellValue();
							} else {
								sd.pagerdutyComments = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(22);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.workonHoliday = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.workonHoliday = c
										.getStringCellValue();
							} else {
								sd.workonHoliday = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(23);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.workonWeekends = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.workonWeekends = c
										.getStringCellValue();
							} else {
								sd.workonWeekends = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(24);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.shiftWork = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:

							if (storeExcelFile != null) {
								storeExcelFile.shiftWork = c
										.getStringCellValue();
							} else {
								sd.shiftWork = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(25);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.warzone = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.warzone = c.getStringCellValue();
							} else {
								sd.warzone = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(26);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.coop = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.coop = c.getStringCellValue();
							} else {
								sd.coop = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(27);
					if (c != null) {
						switch (c.getCellType()) {

						case Cell.CELL_TYPE_NUMERIC:
							if (storeExcelFile != null) {
								if (DateUtil.isCellDateFormatted(c)) {
									Date date = c.getDateCellValue();
									//System.out.println(date);
									if (date != null) {
										String DATE_FORMAT_NOW = "MM/dd/yyyy";
										SimpleDateFormat sdf = new SimpleDateFormat(
												DATE_FORMAT_NOW);
										storeExcelFile.dateofStatus = sdf
												.format(date);

									}

								}
							} else {
								if (DateUtil.isCellDateFormatted(c)) {
									Date date = c.getDateCellValue();
									if (date != null) {
										//System.out.println(date);
										String DATE_FORMAT_NOW = "MM/dd/yyyy";
										SimpleDateFormat sdf = new SimpleDateFormat(
												DATE_FORMAT_NOW);
										sd.dateofStatus = sdf.format(date);
									}

								}
							}
							
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.dateofStatus = c
										.getStringCellValue();
								/*System.out.println("c.getStringCellValue()"
										+ c.getStringCellValue());*/
							} else {
								sd.dateofStatus = c.getStringCellValue();
								/*System.out.println("c.getStringCellValue()"
										+ c.getStringCellValue());*/
							}

							break;
						}
					
					
					//
					
					}
					c = row.getCell(28);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (storeExcelFile != null) {

								if (DateUtil.isCellDateFormatted(c)) {
									Date date = c.getDateCellValue();
									if (date != null) {
										String DATE_FORMAT_NOW = "MM/dd/yyyy";
										SimpleDateFormat sdf = new SimpleDateFormat(
												DATE_FORMAT_NOW);
										storeExcelFile.updateDate = sdf
												.format(date);
									}

								}
							} else {
								if (DateUtil.isCellDateFormatted(c)) {
									Date date = c.getDateCellValue();
									if (date != null) {
										String DATE_FORMAT_NOW = "MM/dd/yyyy";
										SimpleDateFormat sdf = new SimpleDateFormat(
												DATE_FORMAT_NOW);
										sd.updateDate = sdf.format(date);
									}

								}
							}

							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.updateDate = c
										.getStringCellValue();
							} else {
								sd.updateDate = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(29);
					//
					if (c != null) {
					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						if (storeExcelFile != null) {
							if (DateUtil.isCellDateFormatted(c)) {
								Date date = c.getDateCellValue();
								//System.out.println("date" + date);
								if (date != null) {
									String DATE_FORMAT_NOW = "MM/dd/yyyy";
									SimpleDateFormat sdf = new SimpleDateFormat(
											DATE_FORMAT_NOW);
									storeExcelFile.scheduledCloseDate = sdf
											.format(date);
									
									storeExcelFile.scheduledCloseDateforsort = date;
								}

							}
						} else {
							if (DateUtil.isCellDateFormatted(c)) {
								Date date = c.getDateCellValue();
								if (date != null) {
									String DATE_FORMAT_NOW = "MM/dd/yyyy";
									SimpleDateFormat sdf = new SimpleDateFormat(
											DATE_FORMAT_NOW);
									sd.scheduledCloseDate = sdf.format(date);
									sd.scheduledCloseDateforsort = date;
									
									List <UserDetails> ud = UserDetails.getallUserEmail();
									for(UserDetails u: ud){
										List<UserPosition> position = u.userPosition;
										
										//check if user only reg not saved there profile
										if(position.size() != 0){
											for(UserPosition userPos : position){
												String userPositonName =  userPos.position;
												//user position matched with the job positio name then add for the email alert 
												if(userPositonName.equalsIgnoreCase(userPosition)){
													String DATE_FORMAT_NOW1 = "MM/dd/yyyy";
												    Date date1 = new Date();
												    SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT_NOW1);
												    String stringDate = sdf.format(date1);
												    String scheduledCloseDate = sdf.format(date);
												 
												    	 try{
												    		Date todaysDate = sdf1.parse(stringDate);
												 	        Date jobPMODate = sdf1.parse(scheduledCloseDate);
												 	       if(jobPMODate.before(todaysDate)){
												               //does nothing if date(date) is before the todays date
												            }else{
												            	 //save for email alert  if date is after(date1) the todays date
												            	SendEmailAlert emailAlert = new  SendEmailAlert();
																emailAlert.position = userPositonName;
																emailAlert.userEmail = u.email;
																emailAlert.jobNumber = jobNum;
																emailAlert.save();
												            }
												 	        
												 	    }catch(Exception e){
												 	     //handle exception
												 	    } 
												   
												
												}
												}
												
										}
								
									}

								}

							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						if (storeExcelFile != null) {
							storeExcelFile.scheduledCloseDate = c
									.getStringCellValue();
						} else {
							sd.scheduledCloseDate = c.getStringCellValue();
						}

						break;
					}
				}
					if (storeExcelFile != null) {

						storeExcelFile.update(storeExcelFile);
						if (reqNo != null) {
							updatedRows = updatedRows + 1;
						}

					} else {
						if (sd.requestNumber != null) {
							System.out.println("storeExcelFile"
									+ storeExcelFile);
							newRows = newRows + 1;
							sd.save(sd);
						}

					}

				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			flash().put("error", "Upload Failed");
			String newrowscount = Integer.toString(newRows);
			String updatedRowsCount = Integer.toString(updatedRows);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("newrowscount", newrowscount);
			map.put("updatedRowsCount", updatedRowsCount);
			Application.deactivateTheJobByPMODate();
			return ok(Json.stringify(Json.toJson(map)));
		}

		String newrowscount = Integer.toString(newRows);
		String updatedRowsCount = Integer.toString(updatedRows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newrowscount", newrowscount);
		map.put("updatedRowsCount", updatedRowsCount);

		Application.deactivateTheJobByPMODate();
		
		return ok(Json.stringify(Json.toJson(map)));
	}

	public static class AdminExtra {
		public String name;
		public String value;
	}

	// method takes the input as json and split it add into list to show all the
	// skills to user
	public static List<DesiredSkills> getDesiredSkills(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		List<DesiredSkills> list = new ArrayList<DesiredSkills>();
		try {
			list = mapper.readValue(jsonString, TypeFactory.defaultInstance()
					.constructCollectionType(List.class, DesiredSkills.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public static List<Skills> getSkills(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		List<Skills> list = new ArrayList<Skills>();
		try {
			list = mapper
					.readValue(
							jsonString,
							TypeFactory.defaultInstance()
									.constructCollectionType(List.class,
											Skills.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// method takes the input as json and split it add into list to show all the
	// skills to user
	public static List<MandatorySkills> getMandtorySkills(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		List<MandatorySkills> list = new ArrayList<MandatorySkills>();
		try {
			list = mapper
					.readValue(
							jsonString,
							TypeFactory.defaultInstance()
									.constructCollectionType(List.class,
											MandatorySkills.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// method takes the input as json and split it add into list to show all the
		// skills to user
		public static List<UserSkillsVM> getAllUserSkill(String jsonString) {
			ObjectMapper mapper = new ObjectMapper();
			List<UserSkillsVM> list = new ArrayList<UserSkillsVM>();
			try {
				list = mapper
						.readValue(
								jsonString,
								TypeFactory.defaultInstance()
										.constructCollectionType(List.class,
												UserSkillsVM.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		}

	public static Result dashBoard() {
		return ok(index.render());
	}

	public static class PermissionVM {
		public boolean manage_users;
		public boolean manage_inventory;
		public boolean manage_channels;
		public boolean others;
		public int id;
	}

	public static class ActionVM {

		public List<UiActionVM> action;

	}

	public static class UiActionVM {
		public int id;
		public String display_name;
		public boolean value;
	}

	public static Result logOut() {

		try {
			
			System.out.println("Akash  Before Logout: "+session().get("email"));
			String email = session().get("email");
			Admin ad = Admin.getAdminByEmail(email);

			if (ad == null) {
				UserDetails ud = UserDetails.getUserByEmail(email);
				ud.userLoggedInstatus = "loggedOut";
				ud.update();
				session().clear();
				System.out.println("Session after Log out" + session().get("email"));
			}else{
				session().clear();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			session().clear();
			System.out.println("Akash  After Logout: "+session().get("email"));
		}

		return ok(signin.render());
	}

	// /
	public static class OrderVM {
		public int order_id;
		public String customer_name;
		public String order_date;
		public String marketplace;
		public String marketpalce_ack_and_trans_id;
		public String status;
		public int oAttempts;

	}

	public static class OrderStatusVM {
		public String status_name;
		public int status_count;
		public String Status;
	}

	public static class JobVM {
		public String requestNumber;
		public String requestType;
		public String labourCategory;
		public String performanceLevel;
		public String positionType;
		public String clearanceRequired;
		public String workLocation;
		public String workDescription;
		public ArrayList<String> manadatorySkills;
		public ArrayList<String> desiredSkill;
		public Long id;
		public String certificationRequired;
		public String conusTravel;
		public String oconusTravel;
		public String reghrperYear;
		public String scheduleComments;
		public String nonpubComments;
		public String missionCritical;
		public String nightWork;
		public String localTravalusingpub;
		public String pagerDuty;
		public String pagerdutyComments;
		public String workonHoliday;
		public String workonWeekends;
		public String shiftWork;
		public String warzone;
		public String coop;
		public String scheduledCloseDate;
		public String updateDate;
		public String dateofStatus;
		public String jobStatus;
		public String maxOffer;
		public long dateDiff;
		

	}

	// called when user first view job page loaded
	public static Result getAllJobs(int currentpage, String jobType,
			Boolean sortType,Boolean allJobs,String sortName) {

		List<StoreExcelFile> jobs = new ArrayList<>();
		List<StoreExcelFile> userJobs = null;
		int count = 0;
		//String searchId = "dev";
		/*System.out.println(sortName);
		if(("Position".equalsIgnoreCase(sortName)) && (searchId.equalsIgnoreCase("undefined")) ){
			System.out.println("in if ");*/
		    if("Position".equalsIgnoreCase(sortName) && allJobs == true ){
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByPositionAsc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByPositionDesc(currentpage, 10);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}else{
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeAsc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeDesc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}
		    	
			} else if("Position".equalsIgnoreCase(sortName) && allJobs == false){
				String emailId  = session().get("email");
				UserDetails u = UserDetails.getUserByEmail(emailId);
				ArrayList<String> al = new ArrayList<>();
				List<UserPosition> up = u.userPosition;
				for (UserPosition upd : up) {
					String pos = upd.position;
					al.add(pos);
				}
				
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserPositionMatchedAsc(currentpage, 10,al);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserPositionMatchedDesc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				}else{
					//count = StoreExcelFile.getAllJobsCountByLocationAndJobTypeAsc(currentpage,jobType);
					//jobs = StoreExcelFile.getAllJobsForUserByPositionTypeDesc(currentpage, 10,jobType);
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserPositionMatchedJobTypeAsc(currentpage, 10,al,jobType);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserPositionMatchedJobTypeDesc(currentpage, 10,al,jobType);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				
				}
				
			}
		/*    }
			else if(("Position".equalsIgnoreCase(sortName)) && !(searchId.equalsIgnoreCase("undefined"))){
				System.out.println(" in elkse");
		    if("Position".equalsIgnoreCase(sortName) && allJobs == true ){
		    	
						if("All".equalsIgnoreCase(jobType) ){
							if(sortType == true){
								count = StoreExcelFile.getAllJobsCount(currentpage);
								userJobs = StoreExcelFile.getAllJobsForUserByPositionAscSerchName(currentpage, 10,searchId);
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
								
							}else{
								count = StoreExcelFile.getAllJobsCount(currentpage);
								userJobs = StoreExcelFile.getAllJobsForUserByPositionDescSearchName(currentpage, 10,searchId);
								
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
							
						}else{
							if(sortType == true){
								count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
								userJobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeAscSearchName(currentpage, 10,jobType,searchId);
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
								
							}else{
								count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
								userJobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeDescSortName(currentpage, 10,jobType,searchId);
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
							
						}
				    	
					} else if("Position".equalsIgnoreCase(sortName) && allJobs == false){
						String emailId  = session().get("email");
						UserDetails u = UserDetails.getUserByEmail(emailId);
						ArrayList<String> al = new ArrayList<>();
						List<UserPosition> up = u.userPosition;
						for (UserPosition upd : up) {
							String pos = upd.position;
							al.add(pos);
						}
						
						if("All".equalsIgnoreCase(jobType) ){
							if(sortType == true){
								count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
								userJobs = StoreExcelFile.getAlluserPositionMatchedAscSearchName(currentpage, 10,al,searchId);
								
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
							else{
								count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
								userJobs = StoreExcelFile.getAlluserPositionMatchedDescSearchName(currentpage, 10,al,searchId);
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
						}else{
							//count = StoreExcelFile.getAllJobsCountByLocationAndJobTypeAsc(currentpage,jobType);
							//jobs = StoreExcelFile.getAllJobsForUserByPositionTypeDesc(currentpage, 10,jobType);
							if(sortType == true){
								count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
								userJobs = StoreExcelFile.getAlluserPositionMatchedJobTypeAscSearchName(currentpage, 10,al,jobType,searchId);
								
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
							else{
								count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
								userJobs = StoreExcelFile.getAlluserPositionMatchedJobTypeDescSearchName(currentpage, 10,al,jobType,searchId);
								
								for(StoreExcelFile str:userJobs){
									AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
									if(apj == null){
										jobs.add(str);
									}else{
										count  = count-1;
									}
								}
							}
						
						}
						
					}
		    
			}*/

		    if("Location".equalsIgnoreCase(sortName) && allJobs == true ){
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByLocationAsc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByLocationDesc(currentpage, 10);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}else{
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeAsc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeDesc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}
		    	
			} else if("Location".equalsIgnoreCase(sortName) && allJobs == false){
				String emailId  = session().get("email");
				UserDetails u = UserDetails.getUserByEmail(emailId);
				ArrayList<String> al = new ArrayList<>();
				List<UserPosition> up = u.userPosition;
				for (UserPosition upd : up) {
					String pos = upd.position;
					al.add(pos);
				}
				
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserLocationMatchedAsc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserLocationMatchedDesc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				}else{
					//count = StoreExcelFile.getAllJobsCountByLocationAndJobTypeAsc(currentpage,jobType);
					//jobs = StoreExcelFile.getAllJobsForUserByPositionTypeDesc(currentpage, 10,jobType);
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserLocationMatchedJobTypeAsc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserLocationMatchedJobTypeDesc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				
				}
				
			}
		    
		    
		    if("Clearance".equalsIgnoreCase(sortName) && allJobs == true ){
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByClearanceAsc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByClearanceDesc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}else{
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeAsc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeDesc(currentpage, 10,jobType);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}
		    	
			} else if("Clearance".equalsIgnoreCase(sortName) && allJobs == false){
				String emailId  = session().get("email");
				UserDetails u = UserDetails.getUserByEmail(emailId);
				ArrayList<String> al = new ArrayList<>();
				List<UserPosition> up = u.userPosition;
				for (UserPosition upd : up) {
					String pos = upd.position;
					al.add(pos);
				}
				
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserClearanceMatchedAsc(currentpage, 10,al);
						
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserClearanceMatchedDesc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				}else{
					//count = StoreExcelFile.getAllJobsCountByLocationAndJobTypeAsc(currentpage,jobType);
					//jobs = StoreExcelFile.getAllJobsForUserByPositionTypeDesc(currentpage, 10,jobType);
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserLocationMatchedJobTypeAsc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserClearanceMatchedJobTypeDesc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				
				}
				
			}
		    
		    if("Experiance".equalsIgnoreCase(sortName) && allJobs == true ){
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByExperianceAsc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCount(currentpage);
						userJobs = StoreExcelFile.getAllJobsForUserByExperianceDesc(currentpage, 10);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}else{
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAsc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
						
					}else{
						count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
						userJobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeDesc(currentpage, 10,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					
				}
		    	
			} else if("Experiance".equalsIgnoreCase(sortName) && allJobs == false){
				String emailId  = session().get("email");
				UserDetails u = UserDetails.getUserByEmail(emailId);
				ArrayList<String> al = new ArrayList<>();
				List<UserPosition> up = u.userPosition;
				for (UserPosition upd : up) {
					String pos = upd.position;
					al.add(pos);
				}
				
				if("All".equalsIgnoreCase(jobType) ){
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserExperiancedMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserExperiancedMatchedAsc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatched(currentpage,al);
						userJobs = StoreExcelFile.getAlluserExperiancedMatchedDesc(currentpage, 10,al);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				}else{
					//count = StoreExcelFile.getAllJobsCountByLocationAndJobTypeAsc(currentpage,jobType);
					//jobs = StoreExcelFile.getAllJobsForUserByPositionTypeDesc(currentpage, 10,jobType);
					if(sortType == true){
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserLocationMatchedJobTypeAsc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
					else{
						count = StoreExcelFile.getAllJobsCountByuserPositionMatchedJobType(currentpage,al,jobType);
						userJobs = StoreExcelFile.getAlluserExperiancedMatchedJobTypeDesc(currentpage, 10,al,jobType);
						for(StoreExcelFile str:userJobs){
							AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumber(str.requestNumber,session().get("email"));
							if(apj == null){
								jobs.add(str);
							}else{
								count  = count-1;
							}
						}
					}
				
				}
				
			}
		  

			
		
	

		List<JobVM> jobVMs = new ArrayList<JobVM>();


		String mskills;
		String dSkills;

		for (StoreExcelFile s : jobs) {

			JobVM jobVM = new JobVM();

			jobVM.requestNumber = s.requestNumber;
			jobVM.requestType = s.requestType;
			jobVM.labourCategory = s.labourCategory;
			jobVM.performanceLevel = s.performanceLevel;
			jobVM.positionType = s.positionType;
			jobVM.clearanceRequired = s.clearanceRequired;
			jobVM.workLocation = s.workLocation;
			jobVM.workDescription = s.workDescription;

			jobVM.certificationRequired = s.certificationRequired;
			jobVM.conusTravel = s.conusTravel;
			jobVM.oconusTravel = s.oconusTravel;
			jobVM.reghrperYear = s.reghrperYear;
			jobVM.scheduleComments = s.scheduleComments;
			jobVM.nonpubComments = s.nonpubComments;
			jobVM.missionCritical = s.missionCritical;
			jobVM.nightWork = s.nightWork;
			jobVM.localTravalusingpub = s.localTravalusingpub;
			jobVM.pagerDuty = s.pagerDuty;
			jobVM.pagerdutyComments = s.pagerdutyComments;
			jobVM.workonHoliday = s.workonHoliday;
			jobVM.workonWeekends = s.workonWeekends;
			jobVM.shiftWork = s.shiftWork;
			jobVM.warzone = s.warzone;
			jobVM.coop = s.coop;
			jobVM.scheduledCloseDate = s.scheduledCloseDate;
		
			jobVM.updateDate = s.updateDate;
			jobVM.dateofStatus = s.dateofStatus;

			jobVM.jobStatus = s.jobStatus;
			// check if the desired skill does not contain null value;
			if (s.desiredSkill != null) {
				dSkills = s.desiredSkill;
			} else {
				dSkills = " ";
			}

			// split the string with numbers
			String[] tokendesiredsVal = dSkills.split("(?=(\\d)(\\.)(\\s+))");

			// prints the count of tokens
			ArrayList<String> desSkill = new ArrayList<String>();
			int tokenCount = 0;
			for (String token : tokendesiredsVal) {
				tokenCount++;
				// add the string with number
				if (token.trim().length() != 0) {
					
					if(tokenCount> 10 ){
						desSkill.add("1"+token.substring(0,token.length()-1));
					}else{
						desSkill.add(token);
					}
				}

			}

			// check if the mandatory skill does not contain null value;
			if (s.manadatorySkills != null) {
				mskills = s.manadatorySkills;
			} else {
				mskills = " ";
			}

			// split the string with numbers
			String[] tokensVal = mskills.split("(?=(\\d)(\\.)(\\s+))");
			// prints the count of tokens  
			ArrayList<String> manSkill = new ArrayList<String>();
			int tokenManCount = 0;
			for (String token : tokensVal) {
				// check for the number
				tokenManCount++;
				if (token.trim().length() != 0) {
					
					if(tokenManCount > 10){
						manSkill.add("1" + token.substring(0,token.length()-1));
					}else{
						manSkill.add(token);
					}
				}

			}

			jobVM.manadatorySkills = manSkill;
			
			jobVM.desiredSkill = desSkill;
			jobVM.maxOffer = s.maxOffer;

			jobVMs.add(jobVM);
		}

				if(count < 0){
			       count = 0;
		     }
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("totalPages", 10);
		map.put("jobs", jobVMs);
		
		map.put("jobsCount", count);
		return ok(Json.toJson(map));
	}

	public static Result getAllJobsOnlogin(int currentpage,String jobType,String sortName,Boolean sortType) {
		List<StoreExcelFile> jobs = new ArrayList<>();
		int count = 0;
		 
		if("Position".equalsIgnoreCase(sortName) ){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByPositionAsc(currentpage, 10);
					
				}else{
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByPositionDesc(currentpage, 10);
				}
				
			}else{
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeAsc(currentpage, 10,jobType);
					
					
				}else{
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeDesc(currentpage, 10,jobType);
					
				}
				
			}
	    	
		} 

	    if("Location".equalsIgnoreCase(sortName) ){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByLocationAsc(currentpage, 10);
					
					
				}else{
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByLocationDesc(currentpage, 10);
					
				}
				
			}else{
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeAsc(currentpage, 10,jobType);
					
				}else{
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeDesc(currentpage, 10,jobType);
					
				}
				
			}
	    	
		} 
	    
	    
	    if("Clearance".equalsIgnoreCase(sortName) ){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByClearanceAsc(currentpage, 10);
					
					
				}else{
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByClearanceDesc(currentpage, 10);
					
				}
				
			}else{
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeAsc(currentpage, 10,jobType);
					
				}else{
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeDesc(currentpage, 10,jobType);
					
					
				}
				
			}
	    	
		}
	    
	    if("Experiance".equalsIgnoreCase(sortName)){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByExperianceAsc(currentpage, 10);
					
					
				}else{
					count = StoreExcelFile.getAllJobsCount(currentpage);
					jobs = StoreExcelFile.getAllJobsForUserByExperianceDesc(currentpage, 10);
					
				}
				
			}else{
				if(sortType == true){
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAsc(currentpage, 10,jobType);
					
					
				}else{
					count = StoreExcelFile.getAllJobsCountjobType(currentpage,jobType);
					jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeDesc(currentpage, 10,jobType);
					
				}
				
			}
	    	
		} 
			
		List<JobVM> jobVMs = new ArrayList<JobVM>();
		String mskills;
		String dSkills;

		for (StoreExcelFile s : jobs) {

			JobVM jobVM = new JobVM();
			jobVM.id = s.id;
			jobVM.requestNumber = s.requestNumber;
			jobVM.requestType = s.requestType;
			jobVM.labourCategory = s.labourCategory;
			jobVM.performanceLevel = s.performanceLevel;
			jobVM.positionType = s.positionType;
			jobVM.clearanceRequired = s.clearanceRequired;
			jobVM.workLocation = s.workLocation;
			jobVM.workDescription = s.workDescription;
			jobVM.certificationRequired = s.certificationRequired;
			jobVM.conusTravel = s.conusTravel;
			jobVM.oconusTravel = s.oconusTravel;
			jobVM.reghrperYear = s.reghrperYear;
			jobVM.scheduleComments = s.scheduleComments;
			jobVM.nonpubComments = s.nonpubComments;
			jobVM.missionCritical = s.missionCritical;
			jobVM.nightWork = s.nightWork;
			jobVM.localTravalusingpub = s.localTravalusingpub;
			jobVM.pagerDuty = s.pagerDuty;
			jobVM.pagerdutyComments = s.pagerdutyComments;
			jobVM.workonHoliday = s.workonHoliday;
			jobVM.workonWeekends = s.workonWeekends;
			jobVM.shiftWork = s.shiftWork;
			jobVM.warzone = s.warzone;
			jobVM.coop = s.coop;
			jobVM.scheduledCloseDate = s.scheduledCloseDate;
			jobVM.updateDate = s.updateDate;
			jobVM.dateofStatus = s.dateofStatus;
			jobVM.jobStatus = s.jobStatus;

			// check if the desired skill does not contain null value;
			if (s.desiredSkill != null) {
				dSkills = s.desiredSkill;
			} else {
				dSkills = " ";
			}

			// split the string with numbers
			String[] tokendesiredsVal = dSkills.split("(?=(\\d)(\\.)(\\s+))");

			// prints the count of tokens
			String numd = null;
			ArrayList<String> desSkill = new ArrayList<String>();
			int tokenCount = 0;
			for (String token : tokendesiredsVal) {
				// check for the number
				tokenCount++;
				if (token.trim().length() != 0) {
					//desSkill.add(token);
					if(tokenCount> 10 ){
						desSkill.add("1"+token.substring(0,token.length()-1));
					}else{
						desSkill.add(token);
					}
					
				}
				//System.out.print(token);
			}

			// check if the mandatory skill does not contain null value;
			if (s.manadatorySkills != null) {
				mskills = s.manadatorySkills;
			} else {
				mskills = " ";
			}

			// split the string with numbers
			String[] tokensVal = mskills.split("(?=(\\d)(\\.)(\\s+))");
			// prints the count of tokens
			ArrayList<String> manSkill = new ArrayList<String>();
			int tokenManCount = 0;
			for (String token : tokensVal) {
				tokenManCount++;
				if (token.trim().length() != 0) {
					//manSkill.add(token);
					
					if(tokenManCount > 10 ){
						manSkill.add("1"+token.substring(0,token.length()-1));
					}else{
						manSkill.add(token);
					}
				}

			}

			jobVM.manadatorySkills = manSkill;
			jobVM.desiredSkill = desSkill;

			jobVMs.add(jobVM);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPages", 10);
		map.put("currentPage", currentpage);
		map.put("jobs", jobVMs);
		map.put("jobsCount", count);
		return ok(Json.toJson(map));

	}

	public static Result getAllJobsForAdmin(int currentpage, String jobType,
			Boolean sortType, String sortName,String searchId) {
		searchId = searchId.trim();
	
		List<StoreExcelFile> jobs = new ArrayList<>();
		// List<StoreExcelFile> userJobs = null;
		int count = 0;

		 if("Position".equalsIgnoreCase(sortName)){
				if("All".equalsIgnoreCase(jobType) ){
					
							if("undefined".equalsIgnoreCase(searchId)){
									if(sortType == true){
										count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
										jobs = StoreExcelFile.getAllJobsForUserByPositionAdminAsc(currentpage, 10);
										
									}else{
										count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
										jobs = StoreExcelFile.getAllJobsForUserByPositionAdminDesc(currentpage, 10);
									}
							}else{
								
									if(sortType == true){
										
										count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
										if(count != 0){
											jobs = StoreExcelFile.getAllJobsForUserByPositionSearchIdAdminAsc(currentpage, 10,searchId);
										}else{
											jobs = StoreExcelFile.getAllJobsForUserByPositionNameAdminAsc(currentpage, 10,searchId);
											count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
										}
										
									}else{
										count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
										
										if( count != 0){
											jobs = StoreExcelFile.getAllJobsForUserByPositionSearchIdAdminDesc(currentpage, 10,searchId);
										}else{
											count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
											jobs = StoreExcelFile.getAllJobsForUserByPositionNameAdminDesc(currentpage, 10,searchId);
											
										}
										
									}
							}
					
					
					
					
				}else{
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeAdminAsc(currentpage, 10,jobType);
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeAdminDesc(currentpage, 10,jobType);
						}
						
					}else{
						
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0 ){
								jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeSearchIdAdminAsc(currentpage, 10,jobType,searchId);
							}else{
								jobs = StoreExcelFile.getAllJobsForUserByPositionNameJobTypeAdminAsc(currentpage, 10,jobType,searchId);
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,jobType);
								
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByPositionJobTypeSearchIdAdminDesc(currentpage, 10,jobType,searchId);
							}else{
								jobs = StoreExcelFile.getAllJobsForUserByPositionNameJobTypeSearchIdAdminDesc(currentpage, 10,jobType,searchId);
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,jobType);
							}
							
						}
						
					}
					
				}
		    	
			} 
			

		    if("Location".equalsIgnoreCase(sortName)){
				if("All".equalsIgnoreCase(jobType) ){
				
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByLocationAdminAsc(currentpage, 10);
							
						}else{
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByLocationAdminDesc(currentpage, 10);
						}
					}else{
						
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count  != 0){
								jobs = StoreExcelFile.getAllJobsForUserByLocationSearchAdminDesc(currentpage, 10,searchId);
							}else{
								jobs = StoreExcelFile.getAllJobsForUserByLocationByPositionNameAdminDesc(currentpage, 10,searchId);
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByLocationSearchAdminDesc(currentpage, 10,searchId);
							}else{
								
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByLocationSearchIdPositionNameAdminDesc(currentpage, 10,searchId);
							}
							
						}
					}
					
					
				
				
				}else{
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeAdminAsc(currentpage, 10,jobType);
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeAdminDesc(currentpage, 10,jobType);
						}
					}else{
						
						if(sortType == true){
							
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeSearchAdminAsc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeSearchPositionNameAdminAsc(currentpage, 10,jobType,searchId);
								
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeSearchAdminDesc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);	
								jobs = StoreExcelFile.getAllJobsForUserByLocationJobTypeSearchPositionNameAdminDesc(currentpage, 10,jobType,searchId);
							}
							
						}
					}
					
					
				}
		    	
			} 
		    
		    
		    if("Clearance".equalsIgnoreCase(sortName)){
				if("All".equalsIgnoreCase(jobType) ){
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByClearanceAdminAsc(currentpage, 10);
							
						}else{
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByClearanceAdminDesc(currentpage, 10);
						}
						
					}else{
						if(sortType == true){
							
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByClearanceSearchAdminAsc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);	
								jobs = StoreExcelFile.getAllJobsForUserByClearanceSearchPositionNameAdminAsc(currentpage, 10,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0 ){
								jobs = StoreExcelFile.getAllJobsForUserByClearanceSearchAdminDesc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByClearanceSearchPositionNameAdminDesc(currentpage, 10,searchId);
							}
							
						}
						
						
					}
					
				}else{
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeAdminAsc(currentpage, 10,jobType);
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeAdminDesc(currentpage, 10,jobType);
						}
						
					}else{
						
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeSearchAdminAsc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeSearchPositionNameAdminAsc(currentpage, 10,jobType,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeSearchAdminDesc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByClearanceJobTypeSearchPositionNameAdminDesc(currentpage, 10,jobType,searchId);
							}
							
						}
					}
					
					
				}
		    	
			} 
		    
		    if("Experiance".equalsIgnoreCase(sortName)){
				if("All".equalsIgnoreCase(jobType) ){
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByExperianceAdminAsc(currentpage, 10);
							
						}else{
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByExperianceAdminDesc(currentpage, 10);
						}
					}else{
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminAsc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchPositionNameAdminAsc(currentpage, 10,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminDesc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchPositionNameAdminDesc(currentpage, 10,searchId);
							}
							
						}
						
					}
					
					
				}else{
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAdminAsc(currentpage, 10,jobType);
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAdminDesc(currentpage, 10,jobType);
						}
					}else{
						
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchAdminAsc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchPositionNameAdminAsc(currentpage, 10,jobType,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchAdminDesc(currentpage, 10,jobType,searchId);
	
							 }else{
								 count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
								 jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchPositionNameAdminDesc(currentpage, 10,jobType,searchId);

							 }
						}
					}
					
					
				}
		    	
			} 
		
		    
		    if("closeDate".equalsIgnoreCase(sortName)){
				if("All".equalsIgnoreCase(jobType) ){
					
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByExperianceAdmincloseDateAsc(currentpage, 10);
							
						}else{
							count = StoreExcelFile.getAllJobsCountAdmin(currentpage);
							jobs = StoreExcelFile.getAllJobsForUserByExperianceAdmincloseDateDesc(currentpage, 10);
						}
					}else{
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminClosedateAsc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminClosedatePosAsc(currentpage, 10,searchId);
							}
							
						}
						else{
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchAdminCloseDateDesc(currentpage, 10,jobType,searchId);
	
							 }else{
								 count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,jobType);
								 jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchPositionNameAdminCloseDateDesc(currentpage, 10,jobType,searchId);

							 }
						}
						
						
						
					}
					
					
				}else{
					if("undefined".equalsIgnoreCase(searchId)){
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAdminCloseDateAsc(currentpage, 10,jobType);
							
						}else{
							count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
							jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeAdminDesc(currentpage, 10,jobType);
						}
					}else{
						
						if(sortType == true){
							count = StoreExcelFile.getAllJobsCountjobTypeSearchIdAdmin(currentpage,jobType,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchAdminClosedateAsc(currentpage, 10,jobType,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountjobTypeAdmin(currentpage,jobType);
								jobs = StoreExcelFile.getAllJobsForUserByExperienceJobTypeSearchPositionNameAdminCloseDateAsc(currentpage, 10,jobType,searchId);
							}
							
							
						}else{
							count = StoreExcelFile.getAllJobsCountSearchIdAdmin(currentpage,searchId);
							if(count != 0){
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminCloseDateDesc(currentpage, 10,searchId);
							}else{
								count = StoreExcelFile.getAllJobsCountPositionNameAdmin(currentpage,searchId);
								jobs = StoreExcelFile.getAllJobsForUserByExperianceSearchAdminCloseDatePosnameDesc(currentpage, 10,searchId);
							}
							
						}
					}
					
					
				}
		    	
			}
		    
		
		List<JobVM> jobVMs = new ArrayList<JobVM>();

		String mskills;
		String dSkills;

		for (StoreExcelFile s : jobs) {

			JobVM jobVM = new JobVM();
			jobVM.id = s.id;
			jobVM.requestNumber = s.requestNumber;
			jobVM.requestType = s.requestType;
			jobVM.labourCategory = s.labourCategory;
			jobVM.performanceLevel = s.performanceLevel;
			jobVM.positionType = s.positionType;
			jobVM.clearanceRequired = s.clearanceRequired;
			jobVM.workLocation = s.workLocation;
			jobVM.workDescription = s.workDescription;
			jobVM.certificationRequired = s.certificationRequired;
			jobVM.conusTravel = s.conusTravel;
			jobVM.oconusTravel = s.oconusTravel;
			jobVM.reghrperYear = s.reghrperYear;
			jobVM.scheduleComments = s.scheduleComments;
			jobVM.nonpubComments = s.nonpubComments;
			jobVM.missionCritical = s.missionCritical;
			jobVM.nightWork = s.nightWork;
			jobVM.localTravalusingpub = s.localTravalusingpub;
			jobVM.pagerDuty = s.pagerDuty;
			jobVM.pagerdutyComments = s.pagerdutyComments;
			jobVM.workonHoliday = s.workonHoliday;
			jobVM.workonWeekends = s.workonWeekends;
			jobVM.shiftWork = s.shiftWork;
			jobVM.warzone = s.warzone;
			jobVM.coop = s.coop;
			jobVM.scheduledCloseDate = s.scheduledCloseDate;
			jobVM.updateDate = s.updateDate;
			jobVM.dateofStatus = s.dateofStatus;
			jobVM.jobStatus = s.jobStatus;
			jobVM.maxOffer  =s.maxOffer;
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dateEnd = null;
			try {
				dateEnd = format.parse(s.scheduledCloseDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date  dateStart  = new Date();
			
			long diff = dateEnd.getTime() -  dateStart.getTime() ;
			
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			System.out.print(diffDays + " days, "+s.labourCategory);
			jobVM.dateDiff =  diffDays;
			
			// check if the desired skill does not contain null value;
			if (s.desiredSkill != null) {
				dSkills = s.desiredSkill;
			} else {
				dSkills = " ";
			}

			// split the string with numbers
			String[] tokendesiredsVal = dSkills.split("(?=(\\d)(\\.)(\\s+))");

			// prints the count of tokens
			ArrayList<String> desSkill = new ArrayList<String>();
			int tokenCount = 0;
			for (String token : tokendesiredsVal) {
				tokenCount++;
				// check for the number
				if (token.trim().length() != 0) {
					
					if(tokenCount > 10 ){
						desSkill.add("1"+token.substring(0,token.length()-1));
					}else{
						desSkill.add(token);
					}
				}
			}

			// check if the mandatory skill does not contain null value;
			if (s.manadatorySkills != null) {
				mskills = s.manadatorySkills;
			} else {
				mskills = " ";
			}

			// split the string with numbers
			String[] tokensVal = mskills.split("(?=(\\d)(\\.)(\\s+))");
			// prints the count of tokens
			ArrayList<String> manSkill = new ArrayList<String>();
		   int	tokenManCount = 0;
			for (String token : tokensVal) {
				// check for the number
				tokenManCount++;
				// if number skip dont add to the list
				// add the string with number
				if (token.trim().length() != 0) {
					//manSkill.add(token);
					if(tokenManCount > 10 ){
						manSkill.add("1"+token.substring(0,token.length()-1));
					}else{
						manSkill.add(token);
					}
				}

			}

			jobVM.manadatorySkills = manSkill;
			jobVM.desiredSkill = desSkill;

			jobVMs.add(jobVM);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPages", 10);
		map.put("jobs", jobVMs);
		map.put("jobsCount", count);
		return ok(Json.toJson(map));
	}

	public static class FilterOrderVM {
		public String customer_name;
		public String marketplace;
		public String status;
		public Date from_date;
		public Date to_date;
		public int pageNo;
		public boolean flag;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AddEducationVM {
		public int id;
		public String instituteName;
		public String degree;
		public String major;
		public String toDate;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AddEmpHistoryVM {
		public int id;
		public String companyName;
		public String position;
		public String startdate;
		public String enddate;
		public String expdesc;
		public String endYear;
		public String startYear;
		
		
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AddCertificateVM {
		public int id;
		public String certName;
		public String certYear;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserInfoVM {

		public String firstname;
		public String middlename;
		public String lastname;
		public String dob;
		public String gender;
		// public String userposition;
		public String email;
		public String password;
		public String phnumber;
	    public String altphnumber;
	    public String residentState;
	    public String zipcode;
	    public String desiredsalary;
	    public String willingtorelocate ;
	    public String jobsearchstatus;
	    public String currentjobtitle;
	    public String  altemail;
	    public String residentcity;

	}

	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserVM {

		public String firstname;
		public String middlename;
		public String lastname;
		public String dob;
		public String gender;
		// public String userposition;
		public String email;
		public String password;
		public String phnumber;
	    public String altphnumber;
	    public String residentState;
	    public String zipcode;
	    public String desiredsalary;
	    public String willingtorelocate ;
	    public String jobsearchstatus;
	    public String currentjobtitle;
	    public String  altemail;
	    public String residentcity;
	    public String userClearance;
	    public ArrayList<String> userPosition  =  new ArrayList<>();
	    public String userExperience;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserPositionVM {
		List<String> psname;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserClearanceVM {
		String name;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProfileVM {
		public String uname;
		public String pass;
		public List<AddEducationVM> addEducation;
		public List<AddEmpHistoryVM> addNewEmphistory;
		public List<AddCertificateVM> addCertificate;
		public UserInfoVM userDetails;

	}

	// called when user clicked to view there own profile
	@JsonIgnore
	public static Result getUserProfile() {
		String email = session().get("email");

		UserDetails ud = UserDetails.getUserByEmail(email);
		List<EducationDetails> ed = EducationDetails
				.getEducationDetailsByUserEmail(email);
		List<CertificationDetails> cd = CertificationDetails
				.getCertificateDetailsByUserEmail(email);
		List<EmploymentDetails> eds = EmploymentDetails
				.getEmploymentDetailsByUserEmail(email);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userDetails", ud);
		map.put("educationDetails", ed);
		map.put("certificationDetails", cd);
		map.put("employmentDetails", eds);
		
		return ok(Json.toJson(map));

	}

	// called when user add its own skill(if skill present none is added)
	public static Result saveOtherSkill(String otherSkill) {
		// check if the skill is present or not if present does not add.
		UserSkill us = UserSkill.getSkillByName(otherSkill);
		if (us == null) {
			UserSkill u = new UserSkill();
			u.skillName = otherSkill;
			u.save();
			return ok(Json.toJson(u));
		} else {
			return ok("");
		}

	}

	
	
	// update user profile
		@JsonIgnore
		public static Result updateUserBaseResume() {
			JsonNode json = request().body().asJson();

			List<AddEducationVM> addEducation;
			List<AddEmpHistoryVM> addNewEmphistory;
			List<AddCertificateVM> addCertificate;
			
			
			String email = session().get("email");
			UserDetails u = UserDetails.getUserByEmail(email);


			JsonNode eduJson = json.get("addEducation");
			JsonNode empJson = json.get("addNewEmphistory");
			JsonNode certJson = json.get("addCertificate");
			
			JsonNode userSkills = json.path("skills");
			
			u.deleteManyToManyAssociations("userSkill");

			ArrayNode skills = (ArrayNode) userSkills;
			for (int k = 0; k < skills.size(); k++) {
				String s = skills.get(k).asText();

				UserSkill us = UserSkill.getSkillByName(s);
				u.userSkill.add(us);
			}

			u.saveManyToManyAssociations("userSkill");
			
			ObjectMapper mapper = new ObjectMapper();
			addNewEmphistory = mapper.convertValue(empJson, mapper.getTypeFactory()
					.constructCollectionType(List.class, AddEmpHistoryVM.class));
			
			List<EmploymentDetails> ed = EmploymentDetails
					.getEmploymentDetailsByUserEmail(session().get("email"));
			if (ed != null) {
				for(EmploymentDetails edetails : ed)
					edetails.delete();
	         }
			
			for (int i = 0; i < addNewEmphistory.size(); i++) {
				
				EmploymentDetails eds = new EmploymentDetails();
				eds.companyName = addNewEmphistory.get(i).companyName;
				eds.position = addNewEmphistory.get(i).position;
				eds.startdate = addNewEmphistory.get(i).startdate;
				eds.startYear = addNewEmphistory.get(i).startYear;
				
				if (("".equalsIgnoreCase(addNewEmphistory.get(i).endYear)) || addNewEmphistory.get(i).endYear == null) {
					eds.endYear = "Present";
				} else {
					eds.endYear = addNewEmphistory.get(i).endYear;
				}
				
				if (("".equalsIgnoreCase(addNewEmphistory.get(i).enddate)) || addNewEmphistory.get(i).enddate == null) {
					eds.enddate = "Present";
				} else {
					eds.enddate = addNewEmphistory.get(i).enddate;
				}
				
				eds.user_details = UserDetails.getUserByEmail(email);
				eds.expdesc = addNewEmphistory.get(i).expdesc;
				eds.save();
				u.employmentDetails.add(eds);

			}

			
			List<EducationDetails> eds = EducationDetails
					.getEducationDetailsByUserEmail(session().get("email"));
			if (eds != null) {
				for(EducationDetails edetails : eds){
					edetails.delete();
				}
			}
			ObjectMapper addEducationmapper = new ObjectMapper();

			addEducation = mapper.convertValue(
					eduJson,
					addEducationmapper.getTypeFactory().constructCollectionType(
							List.class, AddEducationVM.class));
			for (int i = 0; i < addEducation.size(); i++) {

				EducationDetails eduDetails = new EducationDetails();
				eduDetails.instituteName = (addEducation.get(i).instituteName);
				eduDetails.degree = (addEducation.get(i).degree);
				eduDetails.major = (addEducation.get(i).major);
				if (("".equalsIgnoreCase(addEducation.get(i).toDate)) || addEducation.get(i).toDate == null) {
					eduDetails.toDate = "Present";
				} else {
					eduDetails.toDate = addEducation.get(i).toDate;
				}
				
				eduDetails.user_details = UserDetails.getUserByEmail(email);
				eduDetails.save();

				u.educationDetails.add(eduDetails);

			}

			List	<CertificationDetails> c = CertificationDetails
					.getCertificateDetailsByUserEmail(session().get("email"));
			if (c != null) {
				for(CertificationDetails cdetails :c){
					cdetails.delete();
				}
				
			}

			ObjectMapper certMapper = new ObjectMapper();
			addCertificate = mapper.convertValue(
					certJson,
					certMapper.getTypeFactory().constructCollectionType(List.class,
							AddCertificateVM.class));

			for (int i = 0; i < addCertificate.size(); i++) {
			
				CertificationDetails ce = new CertificationDetails();
			
				ce.certName = addCertificate.get(i).certName;
				ce.certYear = addCertificate.get(i).certYear;
				ce.user_details = UserDetails.getUserByEmail(email);
				ce.save();

				u.certificationDetails.add(ce);

			}

			u.update();
			return ok();
		}
		
		
	// update user profile
	@JsonIgnore
	public static Result updateUserProfile() {
		JsonNode json = request().body().asJson();

		String email = session().get("email");
		UserDetails u = UserDetails.getUserByEmail(email);


		JsonNode userClearance = json.path("clearance");
		JsonNode userPosition = json.path("position");
		
		JsonNode userExperience = json.path("experience");
		
		u.deleteManyToManyAssociations("userSkill");

		
		
		u.deleteManyToManyAssociations("userExperiance");

			String s = userExperience.asText();

			UserExperiance ue = UserExperiance.getExperianceByExperianceName(s);
			u.userExperiance.add(ue);
			u.saveManyToManyAssociations("userExperiance");
		
		u.deleteManyToManyAssociations("userPosition");
		ArrayNode positions = (ArrayNode) userPosition;
		for (int j = 0; j < positions.size(); j++) {
			String position = positions.get(j).asText();

			UserPosition up = UserPosition.getRecoredByPositionNameAndLevel(position,userExperience.asText());
			
			if(up != null){
			System.out.println("positon: "+position);
				u.userPosition.add(up);
			}

		}

		u.saveManyToManyAssociations("userPosition");
		
		u.deleteManyToManyAssociations("userClearance");
		
			String clea = userClearance.asText();
			UserClearance uc = UserClearance.getClearanceByName(clea);
			u.userClearance.add(uc);

		u.saveManyToManyAssociations("userClearance");
		
		JsonNode userDet = json.path("userInfo");
		ObjectMapper userinfoMapper = new ObjectMapper();

		try {
			UserInfoVM ui = userinfoMapper.readValue(userDet.traverse(),
					UserInfoVM.class);
		
			u.setEmail(ui.email);
			u.setDob(ui.dob);
			u.setFirstname(ui.firstname);
			u.setMiddlename(ui.middlename);
			u.setLastname(ui.lastname);
			u.setPhnumber(ui.phnumber);
			u.setAltphnumber(ui.altphnumber);
			u.setResidentState(ui.residentState);
			u.setZipcode(ui.zipcode);
			u.setDesiredsalary(ui.desiredsalary);
			u.setWillingtorelocate(ui.willingtorelocate);
			u.setJobsearchstatus(ui.jobsearchstatus);
			u.setCurrentjobtitle(ui.currentjobtitle);
			u.setAltemail(ui.altemail);
			u.setResidentcity(ui.residentcity);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		u.update();

		return ok();
	}

	@JsonIgnore
	public static Result updateUserProfileByAdmin() {

		JsonNode json = request().body().asJson();

		List<AddEducationVM> addEducation;
		List<AddEmpHistoryVM> addNewEmphistory;
		List<AddCertificateVM> addCertificate;

		String email = json.path("email").asText();
		UserDetails u = UserDetails.getUserByEmail(email);

		JsonNode eduJson = json.get("addEducation");
		JsonNode empJson = json.get("addNewEmphistory");
		JsonNode certJson = json.get("addCertificate");
		JsonNode userClearance = json.path("clearance");
		JsonNode userPosition = json.path("position");
		JsonNode userSkills = json.path("skills");
		JsonNode userExperience = json.path("experience");

		u.deleteManyToManyAssociations("userSkill");

		ArrayNode skills = (ArrayNode) userSkills;
		for (int k = 0; k < skills.size(); k++) {
			String s = skills.get(k).asText();
			UserSkill us = UserSkill.getSkillByName(s);
			u.userSkill.add(us);
		}
		u.saveManyToManyAssociations("userSkill");

		u.deleteManyToManyAssociations("userExperiance");
		
			String s = userExperience.asText();
			UserExperiance ue = UserExperiance.getExperianceByExperianceName(s);
			u.userExperiance.add(ue);
		

		u.saveManyToManyAssociations("userExperiance");

		u.deleteManyToManyAssociations("userPosition");

		
		
		ArrayNode positions = (ArrayNode) userPosition;
		for (int j = 0; j < positions.size(); j++) {
			String position = positions.get(j).asText();
			UserPosition up = UserPosition.getRecoredByPositionNameAndLevel(position,userExperience.asText());
			
			if(up != null){
				u.userPosition.add(up);
			}
			//u.userPosition.add(up);
		}

		u.saveManyToManyAssociations("userPosition");

		u.deleteManyToManyAssociations("userClearance");
		
			String clea = userClearance.asText();
			UserClearance uc = UserClearance.getClearanceByName(clea);
			u.userClearance.add(uc);

		u.saveManyToManyAssociations("userClearance");

		ObjectMapper mapper = new ObjectMapper();
		addNewEmphistory = mapper.convertValue(empJson, mapper.getTypeFactory()
				.constructCollectionType(List.class, AddEmpHistoryVM.class));
		List <EmploymentDetails> ed = EmploymentDetails
				.getEmploymentDetailsByUserEmail(email);
		if (ed != null) {
			for(EmploymentDetails edetails : ed){
			edetails.delete();
		}
		}
		
		for (int i = 0; i < addNewEmphistory.size(); i++) {

			//System.out.println("addNewEmphistory.size():"+addNewEmphistory.size());
			EmploymentDetails eds = new EmploymentDetails();
			eds.companyName = addNewEmphistory.get(i).companyName;
			eds.position = addNewEmphistory.get(i).position;
			eds.startdate = addNewEmphistory.get(i).startdate;
			//System.out.println("addNewEmphistory.get(i).enddate"+addNewEmphistory.get(i).enddate);
			eds.startYear = addNewEmphistory.get(i).startYear;
			
			if (("".equalsIgnoreCase(addNewEmphistory.get(i).endYear)) || addNewEmphistory.get(i).endYear == null) {
				eds.endYear = "Present";
			} else {
				eds.endYear = addNewEmphistory.get(i).endYear;
			}
			
			if (("".equalsIgnoreCase(addNewEmphistory.get(i).enddate)) || addNewEmphistory.get(i).enddate == null) {
				eds.enddate = "Present";
			} else {
				eds.enddate = addNewEmphistory.get(i).enddate;
			}
			eds.user_details = UserDetails.getUserByEmail(email);
			eds.expdesc = addNewEmphistory.get(i).expdesc;
			eds.save();
			u.employmentDetails.add(eds);

		}

		ObjectMapper addEducationmapper = new ObjectMapper();

		addEducation = mapper.convertValue(
				eduJson,
				addEducationmapper.getTypeFactory().constructCollectionType(
						List.class, AddEducationVM.class));
		List<EducationDetails> eds = EducationDetails
				.getEducationDetailsByUserEmail(email);
		if (eds != null) {
			for(EducationDetails edetails : eds){
				edetails.delete();
			}
		}
		
		for (int i = 0; i < addEducation.size(); i++) {
			/*EducationDetails eds = EducationDetails
					.getEducationDetailsByName(addEducation.get(i).id);
			if (eds != null) {
				eds.delete();
			}
			 */
			EducationDetails edudetails = new EducationDetails();
			edudetails.instituteName = (addEducation.get(i).instituteName);
			edudetails.degree = (addEducation.get(i).degree);
			edudetails.major = (addEducation.get(i).major);
//			ed.toDate = (addEducation.get(i).toDate);
			if (("".equalsIgnoreCase(addEducation.get(i).toDate)) || addEducation.get(i).toDate == null) {
				edudetails.toDate = "Present";
			} else {
				edudetails.toDate = addEducation.get(i).toDate;
			}
			
			edudetails.user_details = UserDetails.getUserByEmail(email);
			edudetails.save();

			u.educationDetails.add(edudetails);

		}

		ObjectMapper certMapper = new ObjectMapper();
		addCertificate = mapper.convertValue(
				certJson,
				certMapper.getTypeFactory().constructCollectionType(List.class,
						AddCertificateVM.class));

		List	<CertificationDetails> c = CertificationDetails
				.getCertificateDetailsByUserEmail(email);
		if (c != null) {
			//System.out.println("in delete");
			for(CertificationDetails cdetails :c){
				cdetails.delete();
			}
			
		}
		
		for (int i = 0; i < addCertificate.size(); i++) {
			/*CertificationDetails c = CertificationDetails
					.getCetificateByName(addCertificate.get(i).id);
			if (c != null) {
				c.delete();
			}*/

			CertificationDetails ce = new CertificationDetails();
			ce.certName = addCertificate.get(i).certName;
			ce.certYear = addCertificate.get(i).certYear;
			ce.user_details = UserDetails.getUserByEmail(email);
			ce.save();

			u.certificationDetails.add(ce);

		}

		JsonNode userDet = json.path("userInfo");
		ObjectMapper userinfoMapper = new ObjectMapper();

		try {
			UserInfoVM ui = userinfoMapper.readValue(userDet.traverse(),
					UserInfoVM.class);
			//System.out.println("ui.firstname --" + ui.firstname);
			u.email = ui.email;
			u.setFirstname(ui.firstname);
			u.setMiddlename(ui.middlename);
			u.setLastname(ui.lastname);
			u.setDob(ui.dob);
			u.setPhnumber(ui.phnumber);
			u.setAltphnumber(ui.altphnumber);
			u.setResidentState(ui.residentState);
			u.setZipcode(ui.zipcode);
			u.setDesiredsalary(ui.desiredsalary);
			u.setWillingtorelocate(ui.willingtorelocate);
			u.setJobsearchstatus(ui.jobsearchstatus);
			u.setCurrentjobtitle(ui.currentjobtitle);
			u.setAltemail(ui.altemail);
			u.setResidentcity(ui.residentcity);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		u.update();

		return ok();
	}

	@JsonIgnore
	public static Result getAllSkills() {
		List<UserSkill> skill = UserSkill.getAllSkills();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("skills", skill);
		return ok((Json.stringify(Json.toJson(map))));
	}

	@JsonIgnore
	public static Result getAllPosition() {
		//List<UserPosition> up = UserPosition.getAllPosition();
		
		List<SqlRow> up = UserPosition.getAllDistinctPositionForAdmin();
		List<PositionVM> positionVM = new ArrayList<PositionVM>();
		
		for(SqlRow u: up){
			PositionVM pvm = new PositionVM();
			pvm.level = u.getString("level");
			pvm.position = u.getString("position");
			pvm.rate = u.getString("rate");
			positionVM.add(pvm);
			
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position", positionVM);
		return ok((Json.stringify(Json.toJson(map))));

	}

	
	public static class PositionVM{
		public int id;
		public String position;
		public String level;
		public String rate;
		
	}
	
	
	public static class PositionDetailsVM{
		public 	List<PositionVM> positionVM; 
	   public String position;
	} 
	
	@JsonIgnore
	public static Result getAllPositionForAdmin() {
		List<SqlRow> up = UserPosition.getAllDistinctPositionForAdmin();
		List<PositionDetailsVM> positionDetailsVM = new ArrayList<PositionDetailsVM>();
		
		
		for(SqlRow u: up){
			List<UserPosition> userPosition = UserPosition.getAllPositionForAdmin(u.getString("position"));
			List<PositionVM> positionVM = new ArrayList<PositionVM>();
			for(UserPosition upp:userPosition){
					PositionVM pvm = new PositionVM();
					pvm.level = upp.level;
					pvm.position = upp.position;
					pvm.rate = upp.rate;
					positionVM.add(pvm);
			}
			
			PositionDetailsVM PosDetailsVM =new PositionDetailsVM();
			PosDetailsVM.positionVM  =    positionVM;
			PosDetailsVM.position = u.getString("position");
			positionDetailsVM.add(PosDetailsVM);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position", positionDetailsVM);
		return ok((Json.stringify(Json.toJson(map))));

	}
	
	
	@JsonIgnore
	public static Result getAllExperiance() {
		List<UserExperiance> ue = UserExperiance.getAllExperiance();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("experiance", ue);
		return ok((Json.stringify(Json.toJson(map))));

	}

	public static class PositionRateVM{
		
		public String level;
		public String rate;
	}
	
	@JsonIgnore
	public static Result addnewPosition() {
		JsonNode json = request().body().asJson();
		
		String  position = json.get("position").asText();
		List<PositionRateVM> positionRateVM;
      	
		JsonNode posJson = json.get("positionList");
		ObjectMapper mapper = new ObjectMapper();
		positionRateVM = mapper.convertValue(posJson, mapper.getTypeFactory()
				.constructCollectionType(List.class, PositionRateVM.class));
	
		
		for (int i = 0; i < positionRateVM.size(); i++) {
			UserPosition up = UserPosition.getRecoredByPositionNameAndLevel(position,positionRateVM.get(i).level);
			if(up == null){
	        	UserPosition u = new UserPosition();
	        	u.setPosition(position);
	        	u.setLevel(positionRateVM.get(i).level);
	        	u.setRate(positionRateVM.get(i).rate);
	        	u.save();

	        }else{
	        	up.setPosition(position);
	        	up.setLevel(positionRateVM.get(i).level);
	        	up.setRate(positionRateVM.get(i).rate);
	        	up.update();
	        	
	        }		
  
		}
		
		
		/*UserPosition up = UserPosition.getPositionByPosName(position);
		if (up == null) {
			UserPosition u = new UserPosition();
			u.position = position;
			u.save();
			return ok(Json.toJson(u));
		} else {
			return ok("");
		}*/
		return ok("");
	}

	@JsonIgnore
	public static Result addNewClearance() {
		JsonNode json = request().body().asJson();
	    String clearance = json.get("clearancename").asText();
		
		UserClearance uc = UserClearance.getClearanceByName(clearance);
		if (uc == null) {
			UserClearance u = new UserClearance();
			u.clearance = clearance;
			u.save();
			return ok(Json.toJson(u));
		} else {
			return ok("");
		}

	}

	@JsonIgnore
	public static Result deleteClearanceByName() {
		JsonNode json = request().body().asJson();
	    String clearance = json.get("clearance").asText();
		UserClearance uc = UserClearance.getClearanceByName(clearance);
		if (uc != null) {
			uc.delete();
			return ok("deleted");
		} else {
			return ok("");
		}

	}

	@JsonIgnore
	public static Result deletePositionByName(String position) {
		List<UserPosition>up = UserPosition.getPositionListByPosName(position);
		if (up.size() != 0) {
			for(UserPosition u: up){
				u.delete();
			}
		
		
		} else {
			
		}
		return ok("deleted");
	}

	@JsonIgnore
	public static Result editPositionName(String editPosition,
			String editPositionOld) {

		UserPosition up = UserPosition.getPositionByPosName(editPositionOld);
		if (up != null) {
			up.position = editPosition;
			up.update();

		}
		return ok(Json.toJson(up));
	}

	@JsonIgnore
	public static Result editClearanceName() {

		JsonNode json = request().body().asJson();
	    String clearancenew = json.get("editClearanceNew").asText();
	    String clearanceold = json.get("editClearanceOld").asText();
		
		UserClearance uc = UserClearance.getClearanceByName(clearanceold);

		if (uc != null) {
			uc.clearance = clearancenew;
			uc.update();
			return ok(Json.toJson(uc));
		} else {

		}

		return ok();
	}

	@JsonIgnore
	public static Result getAllClearance() {
		List<UserClearance> uc = UserClearance.getAllClearance();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clearance", uc);
		return ok((Json.stringify(Json.toJson(map))));

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ManadatorySkillsVM {
		public String comment;
		public String dskill;

	}

	public static class DesiredSkillsVM {
		public String comment;
		public String dskill;
	}

	/*
	 * @JsonIgnoreProperties(ignoreUnknown=true) public static class ProfileVM {
	 * public String uname; public String pass; public List<AddEducationVM>
	 * addEducation; public List<AddEmpHistoryVM> addNewEmphistory; public
	 * List<AddCertificateVM> addCertificate; public UserInfoVM userDetails;
	 * 
	 * }
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class mskill {
		public String mskill;
		public String comment;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ManadatorySkills {
		List<mskill> mskill;
		public String comment;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class dskill {
		public String dskill;
		public String comment;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DesiredSkillsVM1 {
		
		List<dskill> dskill;
		public String comment;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SaveAppliedJobsVM {
		public String clearanceRequired;
		public String labourCategory;
		public String performanceLevel;
		public String positionType;
		public String requestNumber;
		public String requestType;
		public String workDescription;
		public String workLocation;
		public List<ManadatorySkills> manadatorySkills;
		public List<DesiredSkillsVM> desiredSkill;
		public String username;
		public String jobStatus;
		public String scheduledCloseDate;
		public String userskillsSpecification;

		public long id;

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserSaveAppliedJobsVM {
		public String clearanceRequired;
		public String labourCategory;
		public String performanceLevel;
		public String positionType;
		public String requestNumber;
		public String requestType;
		public String workDescription;
		public String workLocation;
		public String[] manadatorySkills;
		public String[] desiredSkill;
		public String [] skills;
		public String username;
		public String jobStatus;
		public String scheduledCloseDate;
		public long id;
		public String userskillsSpecification;
	}

	@JsonIgnoreProperties
	public static Result saveAppliedJob() {
		JsonNode json = request().body().asJson();
	//	System.out.println("json" + json);
		AppliedJobs apj = new AppliedJobs();
		ObjectMapper objectMapper = new ObjectMapper();
		SaveAppliedJobsVM saveAppliedJobsVM;

		try {
			saveAppliedJobsVM = objectMapper.readValue(json.get("jobData")
					.traverse(), SaveAppliedJobsVM.class);

			// apj.desiredSkil = json.get("desiredSkills").toString();
			String email = session().get("email");

			// Delete the job having status 'Draft' and Change to Applied. for
			// that(Current) user
			AppliedJobs appliedJobs = AppliedJobs.getUserAppliedJob(email,
					saveAppliedJobsVM.requestNumber);
			if (appliedJobs != null) {
				appliedJobs.delete();
			} else {

			}

			ArrayNode positions = (ArrayNode) json.get("manadatorySkills");
			ArrayNode dskills = (ArrayNode) json.get("desiredSkills");

			String manadatorySkills = "";
			int  pos =positions.size();
			//System.out.println("pos"+pos);
			for (int j = pos-1; j >=0 ; j--) {
				String position = positions.get(j).path("mskill").toString();
				manadatorySkills = position + "," + manadatorySkills  ;

			}

			String desiredSkills = "";
			int des = dskills.size();
			for (int j = des-1 ; j >= 0 ; j--) {
				String desired = dskills.get(j).path("dskill").toString();
				desiredSkills =  desired+","+desiredSkills ;

			}

			desiredSkills = desiredSkills.replaceAll(",$", "");
			manadatorySkills = manadatorySkills.replaceAll(",$", "");

			apj.manadatorySkill = json.get("manadatorySkills").toString();
			apj.desiredSkil = json.get("desiredSkills").toString();
			apj.jobno = saveAppliedJobsVM.requestNumber;
			String username = session().get("email");
			apj.username = username;
			apj.jobStatus = "Applied";

			
			apj.skills = json.get("skills").toString();
		
			List<Skills> userJobskills  = getSkills(apj.skills);
 			
 			for(Skills skills : userJobskills){
 				
 				UserSkill us = UserSkill.getSkillByName(skills.skillName);
 				if(us == null){
 					UserSkill u = new UserSkill();
 					u.skillName = skills.skillName;
 					u.save();
 					
 				}
 			}
			apj.positionname = saveAppliedJobsVM.labourCategory;
			apj.location = saveAppliedJobsVM.workLocation;
			apj.clearancereq = saveAppliedJobsVM.clearanceRequired;
			apj.reqType = saveAppliedJobsVM.requestType;
			apj.performancelevel = saveAppliedJobsVM.performanceLevel;
			apj.workDesc = saveAppliedJobsVM.workDescription;
			apj.positiontype = saveAppliedJobsVM.positionType;
			apj.scheduledCloseDate = saveAppliedJobsVM.scheduledCloseDate;
			apj.userskillsSpecification = json.get("userskillsSpecification").asText(); 
			apj.save();

			//String email = session().get("email");*/
			MailUtility mailUtility =  new MailUtility();
			mailUtility.sendMailToUser(email, saveAppliedJobsVM.labourCategory, saveAppliedJobsVM.performanceLevel, saveAppliedJobsVM.positionType, saveAppliedJobsVM.clearanceRequired, saveAppliedJobsVM.workLocation);
			mailUtility.sendMailToAdmin(email,saveAppliedJobsVM.labourCategory,saveAppliedJobsVM.positionType,saveAppliedJobsVM.workLocation);
	
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ok();
	}

	@JsonIgnoreProperties
	public static Result saveUserJobToDraft() {
		JsonNode json = request().body().asJson();
	   
		AppliedJobs apj = new AppliedJobs();
		ObjectMapper objectMapper = new ObjectMapper();

		UserSaveAppliedJobsVM saveAppliedJobsVM;
		try {

			saveAppliedJobsVM = objectMapper.readValue(json.get("jobData")
					.traverse(), UserSaveAppliedJobsVM.class);

			apj.manadatorySkill = json.get("manadatorySkills").toString();
			apj.desiredSkil = json.get("desiredSkills").toString();
			apj.skills = json.get("skills").toString();
			apj.jobno = saveAppliedJobsVM.requestNumber;
			String username = session().get("email");
			apj.username = username;
			apj.jobStatus = "Draft";
			apj.positionname = saveAppliedJobsVM.labourCategory;
			apj.location = saveAppliedJobsVM.workLocation;
			apj.clearancereq = saveAppliedJobsVM.clearanceRequired;
			apj.reqType = saveAppliedJobsVM.requestType;
			apj.performancelevel = saveAppliedJobsVM.performanceLevel;
			apj.workDesc = saveAppliedJobsVM.workDescription;
			apj.positiontype = saveAppliedJobsVM.positionType;
			apj.scheduledCloseDate = saveAppliedJobsVM.scheduledCloseDate;
			apj.userskillsSpecification = json.get("userskillsSpecification").asText();
			
			apj.save();

		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ok();
	}

	@JsonIgnoreProperties
	public static Result saveUserSavedJob() {
		JsonNode json = request().body().asJson();
		//System.out.println("json" + json);
		AppliedJobs apj = new AppliedJobs();
		ObjectMapper objectMapper = new ObjectMapper();
		UserSaveAppliedJobsVM saveAppliedJobsVM;
		try {

			saveAppliedJobsVM = objectMapper.readValue(json.get("jobData")
					.traverse(), UserSaveAppliedJobsVM.class);

			apj.manadatorySkill = json.get("manadatorySkills").toString();
			apj.desiredSkil = json.get("desiredSkills").toString();
		
			apj.jobno = saveAppliedJobsVM.requestNumber;
			String username = session().get("email");
			apj.username = username;
			if ("draft".equalsIgnoreCase(saveAppliedJobsVM.jobStatus)) {
				apj.jobStatus = "Draft";
			}
			if ("active".equalsIgnoreCase(saveAppliedJobsVM.jobStatus)) {
				apj.jobStatus = "Applied";
			}
			
 			apj.skills = json.get("skills").toString();
		    
 			List<Skills> userJobskills  = getSkills(apj.skills);
 			
 			for(Skills skills : userJobskills){
 				
 				UserSkill us = UserSkill.getSkillByName(skills.skillName);
 				if(us == null){
 					UserSkill u = new UserSkill();
 					u.skillName = skills.skillName;
 					u.save();
 					
 				}
 			}
 				
 			
 			apj.positionname = saveAppliedJobsVM.labourCategory;
			apj.location = saveAppliedJobsVM.workLocation;
			apj.clearancereq = saveAppliedJobsVM.clearanceRequired;
			apj.reqType = saveAppliedJobsVM.requestType;
			apj.performancelevel = saveAppliedJobsVM.performanceLevel;
			apj.workDesc = saveAppliedJobsVM.workDescription;
			apj.positiontype = saveAppliedJobsVM.positionType;
			apj.scheduledCloseDate = saveAppliedJobsVM.scheduledCloseDate;
			apj.userskillsSpecification = json.get("userskillsSpecification").asText();
			
			apj.save();
			
			String email = session().get("email");
			MailUtility mailUtility =  new MailUtility();
			mailUtility.sendMailToUser(email, saveAppliedJobsVM.labourCategory, saveAppliedJobsVM.performanceLevel, saveAppliedJobsVM.positionType, saveAppliedJobsVM.clearanceRequired, saveAppliedJobsVM.workLocation);
			mailUtility.sendMailToAdmin(email,saveAppliedJobsVM.labourCategory,saveAppliedJobsVM.positionType,saveAppliedJobsVM.workLocation);
			
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ok();
	}

	@JsonIgnoreProperties
	public static class DesiredSkills {
		public String dskill;
		public String comment;

	}

	@JsonIgnoreProperties
	public static class MandatorySkills {

		public String mskill;
		public String comment;
	}
	
	@JsonIgnoreProperties
	public static class Skills {

		public String skillName;
		public String isSelected;
	}
	
	@JsonIgnoreProperties
	public static  class UserSkillsVM{
		public String skillName;
		public String isSelected;
		
		
	}
	
	@JsonIgnoreProperties
	public static class AppliedJobVM {
		public String username;
		public int id;
		public String requestNumber;
		public String status;
		public String location;
		public String positionName;
		public String workDesc;
		public String jobStatus;
		public String jobno;
		public String positionname;
		public String positiontype;
		public String reqType;
		public String performancelevel;
		public String clearancereq;
		public String desiredSalary;
		public String maxOffer;
		public String userskillsSpecification;

		public List<DesiredSkills> dsSkills;
		public List<MandatorySkills> msSkils;

	}

	@JsonIgnoreProperties
	public static class SavedJobVM {
		public String username;
		public int id;
		
		public String requestNumber;
		public String requestType;
		public String labourCategory;
		public String performanceLevel;
		public String positionType;
		public String clearanceRequired;
		public String workLocation;
		public String workDescription;
		
		public List<UserSkillsVM> skills;
		
		public String jobStatus;

		public List<MandatorySkills> manadatorySkills;
		public List<DesiredSkills> desiredSkill;
		public String scheduledCloseDate;
		public String userskillsSpecification;

	}

	
	public static Result getAllArchivedJobs(int pageNumber) {
		String jobStatus = "Applied";
		int count = 0;
		count = AppliedJobs.getAllArchivedJobsCount(pageNumber, jobStatus);
		List<AppliedJobs> ap = AppliedJobs.getAllArchivedJobs(pageNumber, 10,
				jobStatus);
		ArrayList<AppliedJobVM> appliedJobVM = new ArrayList<AppliedJobVM>();
		for (AppliedJobs apj : ap) {
			AppliedJobVM apvm = new AppliedJobVM();
			apvm.id = apj.id;
			apvm.dsSkills = getDesiredSkills(apj.desiredSkil);
			apvm.msSkils = getMandtorySkills(apj.manadatorySkill);
			apvm.username = apj.username;
			apvm.status = apj.jobStatus;
			apvm.requestNumber = apj.jobno;
			apvm.location = apj.location;
			apvm.positionName = apj.positionname;
			UserDetails ud= UserDetails.getUserByEmail(apj.username);
			apvm.desiredSalary = ud.desiredsalary;
			StoreExcelFile sef = StoreExcelFile.getJobByRequestNumber(apj.jobno);
			apvm.maxOffer = sef.maxOffer;
			apvm.userskillsSpecification = apj.userskillsSpecification;
			
			appliedJobVM.add(apvm);

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appliedJobs", appliedJobVM);
		map.put("appliedJobCount", count);
		return ok(Json.toJson(map));
	}

	
	public static Result getAllAppliedJobs(int pageNumber) {
		String jobStatus = "Applied";
		int count = 0;
		count = AppliedJobs.getAllAppliedJobsCount(pageNumber, jobStatus);
		List<AppliedJobs> ap = AppliedJobs.getAllAppliedJobs(pageNumber, 10,
				jobStatus);
		ArrayList<AppliedJobVM> appliedJobVM = new ArrayList<AppliedJobVM>();

		for (AppliedJobs apj : ap) {
			AppliedJobVM apvm = new AppliedJobVM();
			apvm.id = apj.id;
			apvm.dsSkills = getDesiredSkills(apj.desiredSkil);
			apvm.msSkils = getMandtorySkills(apj.manadatorySkill);
			apvm.username = apj.username;
			apvm.status = apj.jobStatus;
			apvm.requestNumber = apj.jobno;
			apvm.location = apj.location;
			apvm.positionName = apj.positionname;
			
			UserDetails ud= UserDetails.getUserByEmail(apj.username);
			apvm.desiredSalary = ud.desiredsalary;
			StoreExcelFile sef = StoreExcelFile.getJobByRequestNumber(apj.jobno);
			apvm.maxOffer = sef.maxOffer; 
			apvm.userskillsSpecification = apj.userskillsSpecification;
			appliedJobVM.add(apvm);

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appliedJobs", appliedJobVM);
		map.put("appliedJobCount", count);
		return ok(Json.toJson(map));
	}

	public static Result generatePDF(String id) {
		final String rootDir = Play.application().configuration()
				.getString("resume.path");
		File f = new File(rootDir);
		if(!f.exists()){
			f.mkdir();
		}
		String candidiatename = "";
		String ResumeName = ""; 
		int ids = Integer.parseInt(id);
		AppliedJobs ap = AppliedJobs.getUserAppliedJobById(ids);

		//Job id to be shown on resume
	    List<Skills> userJobskills  = getSkills(ap.skills);; 
		String JobId = ap.jobno;
		
		String csrNumber = ap.performancelevel;
		Document document = new Document();
		try {
			String email = ap.username;
			UserDetails ud = UserDetails.getUserByEmail(email);
			
			if (!("NA".equalsIgnoreCase(ud.middlename))) {
				candidiatename = ud.firstname + " " + ud.middlename + " "
						+ ud.lastname;
				ResumeName =  
						   ud.lastname+"_"+ud.firstname;
			} else {
				candidiatename = ud.firstname + " NMI " + ud.lastname;
				ResumeName = "";
				ResumeName  = ud.lastname + "_" + ud.firstname;
			}
			
			ResumeName = ResumeName + "_"+JobId;
			String fileName = rootDir+candidiatename + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			
			List<UserSkill> skills = ud.userSkill;
			List<UserExperiance> userExperiance = ud.userExperiance;
			// used for the table name (heading)
			Font font = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD,
					BaseColor.BLACK);
			// used for the table column data
			Font font1 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL,
					BaseColor.BLACK);
			Font font4 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD,
					BaseColor.BLACK);
			// used for the column name
			Font font2 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD,
					BaseColor.BLACK);

			
			String userSkillLevel = "";
			for (UserExperiance ue : userExperiance) {
				
				userSkillLevel = ue.experianceLevel;
			}
			
			// skill table
						PdfPTable userDetailsTable = new PdfPTable(2);
						// table2.set
						userDetailsTable.setWidthPercentage(100);
						float[] userDetailswidth2 = { 2f, 2f};
						userDetailsTable.setWidths(userDetailswidth2);

						PdfPCell userDetailsCellcandidateName = new PdfPCell(new Paragraph(
								"Candidate Name: "+candidiatename,font1));
						userDetailsCellcandidateName.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsTable.addCell(userDetailsCellcandidateName);
						
						PdfPCell userDetailsCellcmpname = new PdfPCell(new Paragraph(
								"Company Name: " +"Booz Allen Hamilton" ,font1));
						userDetailsCellcmpname.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsTable.addCell(userDetailsCellcmpname);
						
			
						PdfPCell userDetailsCSRNumber =  new PdfPCell(new Paragraph(
								"CSR Number: " +ap.jobno,font1));
						userDetailsCSRNumber.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsTable.addCell(userDetailsCSRNumber);
						
						
						PdfPCell userDetailsClearanceLevel =  new PdfPCell(new Paragraph(
								"ClearanceLevel: " +"TS/SCI with Favorable Polygraph",font1));
						userDetailsClearanceLevel.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsTable.addCell(userDetailsClearanceLevel);
						
						
						PdfPCell userDetailsLaborCategory =  new PdfPCell(new Paragraph(
								"LaborCategory: " +ap.positionname,font1));
						userDetailsLaborCategory.setBackgroundColor(new BaseColor(230, 230, 250));
						
						userDetailsTable.addCell(userDetailsLaborCategory);
						
						PdfPCell userDetailsSkillLevel =  new PdfPCell(new Paragraph(
								"Skill Level: " +userSkillLevel,font1));
						userDetailsSkillLevel.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsTable.addCell(userDetailsSkillLevel);
						
						PdfPCell userDetailsSkillLevelJustification =  new PdfPCell(new Paragraph(
								"Skill Level Justification: " +ap.userskillsSpecification,font1));
						userDetailsSkillLevelJustification.setBackgroundColor(new BaseColor(230, 230, 250));
						userDetailsSkillLevelJustification.setColspan(2);
						userDetailsTable.addCell(userDetailsSkillLevelJustification);
						
			
			// skill table
			PdfPTable table2 = new PdfPTable(3);

			table2.setWidthPercentage(100);
			float[] width2 = { 2f, 2f, 2f };
			table2.setWidths(width2);

			PdfPCell cell2 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell2.setBackgroundColor(new BaseColor(140, 221, 8));
			for (Skills sk : userJobskills) {
				cell2 = new PdfPCell(new Phrase(sk.skillName));
				cell2.setBackgroundColor(new BaseColor(248, 248, 255));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell2);
			}
			
			if(userJobskills.size()%3!=0) {
				for(int i=userJobskills.size()%3; i<3;i++) {
					cell2 = new PdfPCell(new Phrase(""));
					cell2.setBackgroundColor(new BaseColor(248, 248, 255));
					cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell2);
				}
			}
			
			// for username and email
			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);
			
			PdfPCell cell4 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell4 = new PdfPCell(new Phrase("Candidate's Full Legal Name: "
					+ "" + candidiatename , font1));
			cell4.setBackgroundColor(new BaseColor(230, 230, 250));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell4);
			PdfPCell cell5 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell5 = new PdfPCell(new Phrase("Job ID: "+ JobId, font1));
			cell5.setBackgroundColor(new BaseColor(230, 230, 250));
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell5);


			List<UserClearance> clearance = ud.userClearance;
			PdfPTable table3 = new PdfPTable(1);
			table3.setWidthPercentage(100);
			

			PdfPCell cell3 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell3.setColspan(3);
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setPadding(10.0f);
			cell3.setBackgroundColor(new BaseColor(140, 221, 8));
			for (UserClearance uc : clearance) {
				cell3 = new PdfPCell(new Phrase(uc.clearance, font1));
				cell3.setBackgroundColor(new BaseColor(248, 248, 255));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(cell3);
			}

			List<DesiredSkills> desiredSkills = getDesiredSkills(ap.desiredSkil);
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			float[] width = { 5f, 5f };
			table.setWidths(width);

			PdfPCell cell = new PdfPCell(new Paragraph(
					"Requirement".toUpperCase()));
			cell.setColspan(3);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			
			cell = new PdfPCell(new Phrase(" Desired Skills", font4));
			cell.setBackgroundColor(new BaseColor(230, 230, 250));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			
			cell = new PdfPCell(new Phrase(" Candidate’s Skills/Experience", font4));
			cell.setBackgroundColor(new BaseColor(230, 230, 250));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			
			for (DesiredSkills ds : desiredSkills) {
				cell = new PdfPCell(new Phrase(ds.dskill, font1));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ds.comment, font1));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
			}

			List<MandatorySkills> mandatorySkils = getMandtorySkills(ap.manadatorySkill);
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			float[] width1 = { 5f, 5f };
			table1.setWidths(width1);

			PdfPCell cell1 = new PdfPCell(new Paragraph(
					"Requirement".toUpperCase()));
			cell1.setColspan(3);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setPadding(10.0f);
			
			cell1.setBackgroundColor(new BaseColor(140, 221, 8));
			
			cell1 = new PdfPCell(new Phrase(" Mandatory Skills", font4));
			cell1.setBackgroundColor(new BaseColor(230, 230, 250));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);

			cell1 = new PdfPCell(new Phrase(" Candidate’s Skills/Experience", font4));
			cell1.setBackgroundColor(new BaseColor(230, 230, 250));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
			
			for (MandatorySkills ms : mandatorySkils) {
				
				cell1 = new PdfPCell(new Phrase(ms.mskill, font1));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell1);

				cell1 = new PdfPCell(new Phrase(ms.comment, font1));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell1);
			}

			PdfPTable EmpHistoryNametable = new PdfPTable(1);
			EmpHistoryNametable.setWidthPercentage(100);
			float[] columnWidthsemapnameTop = { 2f };
			EmpHistoryNametable.setWidths(columnWidthsemapnameTop);
			
			PdfPCell cellemptablename = new PdfPCell(new Paragraph("EMPLOYMENT HISTORY",font4));
			
			cellemptablename.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cellemptablename.setPadding(10.0f);
			cellemptablename.setBackgroundColor(new BaseColor(230, 230, 250));
			EmpHistoryNametable.addCell(cellemptablename);
			
			PdfPTable EmpHistorytable = new PdfPTable(4);
			EmpHistorytable.setWidthPercentage(100);
			float[] columnWidthsTop = { 2f, 2f, 2f, 2f };
			EmpHistorytable.setWidths(columnWidthsTop);

			PdfPCell cellemp = new PdfPCell(new Paragraph("EMPLOYMENT HISTORY"));
			cellemp.setColspan(3);
			cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellemp.setPadding(10.0f);
			cellemp.setBackgroundColor(new BaseColor(140, 221, 8));

			List<EmploymentDetails> eds = EmploymentDetails
					.getEmploymentDetailsByUserEmail(email);

			cellemp = new PdfPCell(new Phrase("Employee Name", font2));
			cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellemp.setBackgroundColor(new BaseColor(230, 230, 250));
			EmpHistorytable.addCell(cellemp);

			cellemp = new PdfPCell(new Phrase("Start Date", font2));
			cellemp.setBackgroundColor(new BaseColor(230, 230, 250));
			cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			EmpHistorytable.addCell(cellemp);

			cellemp = new PdfPCell(new Phrase("End Date", font2));
			cellemp.setBackgroundColor(new BaseColor(230, 230, 250));
			cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			EmpHistorytable.addCell(cellemp);

			cellemp = new PdfPCell(new Phrase("Position Name/Title", font2));
			cellemp.setBackgroundColor(new BaseColor(230, 230, 250));
			cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			EmpHistorytable.addCell(cellemp);

			for (EmploymentDetails emp : eds) {
				// EmpHistorytable.addCell(cellemp);
				cellemp = new PdfPCell(new Phrase(emp.companyName, font1));
				//cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.startdate+"/"+emp.startYear , font1));
			//	cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.enddate+"/"+emp.endYear, font1));
			//	cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.position, font1));
				//cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);
			}

			
			PdfPTable EduHistoryNametable = new PdfPTable(1);
			EduHistoryNametable.setWidthPercentage(100);
			float[] columnWidthsedupnameTop = { 2f };
			EduHistoryNametable.setWidths(columnWidthsedupnameTop);
			
			PdfPCell celledutablename = new PdfPCell(new Paragraph("EDUCATION",font4));
			
			celledutablename.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cellemptablename.setPadding(10.0f);
			celledutablename.setBackgroundColor(new BaseColor(230, 230, 250));
			EduHistoryNametable.addCell(celledutablename);
			
			PdfPTable edutable = new PdfPTable(4);
			edutable.setWidthPercentage(100);
			float[] colwidth = { 2f, 2f ,2f,2f};
			edutable.setWidths(colwidth);

			PdfPCell celledu = new PdfPCell(new Paragraph("EDUCATION"));
			celledu.setColspan(3);
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			celledu.setPadding(10.0f);
			celledu.setBackgroundColor(new BaseColor(140, 221, 8));

			celledu = new PdfPCell(new Phrase("Degree", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);
			
			celledu = new PdfPCell(new Phrase("School Name", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);
			
			celledu = new PdfPCell(new Phrase("Degree/Major", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);

			
			celledu = new PdfPCell(new Phrase("Completion Date:", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);

			List<EducationDetails> ed = EducationDetails
					.getEducationDetailsByUserEmail(email);
			for (EducationDetails edu : ed) {
				celledu = new PdfPCell(new Phrase(edu.degree, font1));
			//	celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);

				
				celledu = new PdfPCell(new Phrase(edu.instituteName, font1));
				//  celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				  celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				  edutable.addCell(celledu);
				 
				  celledu = new PdfPCell(new Phrase(edu.degree, font1));
				//  celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				  celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				  edutable.addCell(celledu);
				 
				celledu = new PdfPCell(new Phrase(edu.toDate, font1));
				//celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);
			}

			
			PdfPTable EmpCertNametable = new PdfPTable(1);
			EmpCertNametable.setWidthPercentage(100);
			float[] columnWidthscertpnameTop = { 2f };
			EmpCertNametable.setWidths(columnWidthscertpnameTop);
			
			PdfPCell cellcerttablename = new PdfPCell(new Paragraph("Certifications",font4));
			
			cellcerttablename.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellcerttablename.setBackgroundColor(new BaseColor(230, 230, 250));
			EmpCertNametable.addCell(cellcerttablename);
		
			
			
			PdfPTable certtable = new PdfPTable(2);
			certtable.getDefaultCell().setBorder(0);
			certtable.setWidthPercentage(100);
			float[] cw = { 2f, 2f };
			certtable.setWidths(cw);

			PdfPCell cellcert = new PdfPCell(new Paragraph("CERTIFICATIONS:"));
			cellcert.setColspan(3);
			cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellcert.setPadding(10.0f);
			cellcert.setBackgroundColor(new BaseColor(140, 221, 8));
			List<CertificationDetails> cd = CertificationDetails
					.getCertificateDetailsByUserEmail(email);

			cellcert = new PdfPCell(new Phrase("List of Certification", font2));
			cellcert.setBackgroundColor(new BaseColor(230, 230, 250));
			cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
			certtable.addCell(cellcert);

			cellcert = new PdfPCell(new Phrase("Award Date:", font2));
			cellcert.setBackgroundColor(new BaseColor(230, 230, 250));
			cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
			certtable.addCell(cellcert);

			if( cd.size() == 0  ||  cd.isEmpty() || cd.get(0).certName == null ){
				cell.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase("None ", font1));
				
				//cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				
				cell.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase("None ", font1));
				
				//cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				
			}
			
			
			for (CertificationDetails c : cd) {
				// certtable.addCell(cellcert);
				cell.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase(c.certName, font1));
				//cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				cellcert.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase(c.certYear, font1));
				//cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				cellcert.setBorder(Rectangle.NO_BORDER);
				// certtable.addCell(c.certYear);
			}

			
			PdfPTable expDescNametable = new PdfPTable(1);
			expDescNametable.getDefaultCell().setBorder(0);
			expDescNametable.setWidthPercentage(100);
			float[] expnamecw = { 10f };
			expDescNametable.setWidths(expnamecw);
			
			PdfPTable expDesctable = new PdfPTable(4);
			expDesctable.getDefaultCell().setBorder(0);
			expDesctable.setWidthPercentage(100);
			float[] expcw = { 2f,2f,2f,2f};
			expDesctable.setWidths(expcw);
			PdfPCell cellnameExp = new PdfPCell(new Phrase("List of Certification",font4));

			cellnameExp = new PdfPCell(new Phrase("WORK EXPERIENCE", font4));
			// cellExp .setBackgroundColor(new BaseColor(230, 230, 250));
			cellnameExp.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellnameExp.setBackgroundColor(new BaseColor(230, 230, 250));
			//cellnameExp.setBorder(Rectangle.NO_BORDER);
			expDescNametable.addCell(cellnameExp);


			PdfPCell cellExp = new PdfPCell(new Phrase("List of Certification",font4));
			for (EmploymentDetails emp : eds) {
				
				cellExp = new PdfPCell(new Phrase("Employers Name: "+ emp.companyName,font));
				//cellExp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				expDesctable.addCell(cellExp);
				
				cellExp = new PdfPCell(new Phrase("Start Date: "+emp.startdate+"/"+emp.startYear,font));
				//cellExp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				expDesctable.addCell(cellExp);
				
				cellExp = new PdfPCell(new Phrase("End Date: " +emp.enddate+"/"+emp.endYear,font));
				//cellExp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				expDesctable.addCell(cellExp);
				
				cellExp = new PdfPCell(new Phrase("Position Held : " +emp.position,font));
				//cellExp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				expDesctable.addCell(cellExp);

				

				cellExp = new PdfPCell(new Phrase(emp.expdesc, font1));
				// cellExp .setBackgroundColor(new BaseColor(230, 230, 250));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				//cellExp.setBorder(Rectangle.NO_BORDER);
				cellExp.setColspan(4);
				expDesctable.addCell(cellExp);

			}

			 			
			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String date = sdf.format(new Date());
			//System.out.println(date); // 15/10/2013
			Paragraph date1 = new Paragraph(date);
			date1.setAlignment(Element.ALIGN_LEFT);
			document.add(date1);

			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			
			document.add(userDetailsTable);
			
			document.add(Chunk.NEWLINE);
			
			document.add(Chunk.NEWLINE);
			document.add(table1);// mandatory skills

			document.add(Chunk.NEWLINE);
			document.add(table);// desired skills

			document.add(Chunk.NEWLINE);

			document.add(Chunk.NEWLINE);
			document.add(EduHistoryNametable);
			document.add(edutable);

			document.add(Chunk.NEWLINE);
			
		
			//cert table added if it is present not selected
			
				document.add(EmpCertNametable);
				document.add(certtable);
				document.add(Chunk.NEWLINE);
			
			
			document.add(expDescNametable);
			document.add(expDesctable);
			response().setContentType("application/pdf");
			response().setHeader("Content-Disposition",
					"inline; filename=" + ResumeName);
			document.close();
			System.out.println("Pdf created successfully..");
			File file = new File(fileName);
			return ok(file);
		} catch (Exception e) {
			e.printStackTrace();
			return ok();
		}
		
	}


	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class EditJobVM {
		public Long id;
		public String requestNumber;
		public String requestType;
		public String labourCategory;
		public String performanceLevel;
		public String positionType;
		public String clearanceRequired;
		public String workLocation;
		public String workDescription;
		private List<String> manadatorySkills;
		private List<String> desiredSkill;
		public String certificationRequired;
		public String conusTravel;
		public String oconusTravel;
		public String reghrperYear;
		public String scheduleComments;
		public String nonpubComments;
		public String missionCritical;
		public String nightWork;
		public String localTravalusingpub;
		public String pagerDuty;
		public String pagerdutyComments;
		public String workonHoliday;
		public String workonWeekends;
		public String shiftWork;
		public String warzone;
		public String coop;
		public String scheduledCloseDate;
		public String updateDate;
		public String dateofStatus;
		public String maxOffer;
	}

	public static Result saveEditJob() {
		JsonNode json = request().body().asJson();

		JsonNode jsonnode  =request().body().asJson();
		
		JsonNode editJobJson = json.path("editJob");
		ObjectMapper editJob = new ObjectMapper();

		try {
			EditJobVM editJobVM = editJob.readValue(editJobJson.traverse(),
					EditJobVM.class);
			StoreExcelFile storeExcel = StoreExcelFile
					.getregNumber(editJobVM.requestNumber);

			storeExcel.requestNumber = editJobVM.requestNumber;
			storeExcel.requestType = editJobVM.requestType;
			storeExcel.labourCategory = editJobVM.labourCategory;
			storeExcel.performanceLevel = editJobVM.performanceLevel;
			storeExcel.positionType = editJobVM.positionType;
			storeExcel.clearanceRequired = editJobVM.clearanceRequired;
			storeExcel.workLocation = editJobVM.workLocation;
			storeExcel.workDescription = editJobVM.workDescription;
			if (editJobJson.path("manadatorySkills").size() != 0) {
				String manSkill = editJobJson.path("manadatorySkills")
						.toString();
				storeExcel.manadatorySkills = manSkill.replaceAll("\\[", "")
						.replaceAll("\\]", "").replace("\"", "");
			} else {
				storeExcel.manadatorySkills = editJobVM.manadatorySkills
						.toString();
			}
			
			if (editJobJson.path("desiredSkill").size() != 0) {
				String desSkill = editJobJson.path("desiredSkill").toString();
				storeExcel.desiredSkill = desSkill.replaceAll("\\[", "")
						.replaceAll("\\]", "").replace("\"", "");
			} else {
				storeExcel.desiredSkill = editJobVM.desiredSkill.toString();
			}
			storeExcel.certificationRequired = editJobVM.certificationRequired;
			if (editJobVM.conusTravel == "true" || editJobVM.conusTravel == "Y") {
				storeExcel.conusTravel = "Y";
			} else {
				storeExcel.conusTravel = "N";
			}

			if (editJobVM.oconusTravel == "true"
					|| editJobVM.oconusTravel == "Y") {
				storeExcel.oconusTravel = "Y";
			} else {
				storeExcel.oconusTravel = "N";
			}

			// storeExcel.oconusTravel =editJobVM.oconusTravel;

			storeExcel.reghrperYear = editJobVM.reghrperYear;

			// storeExcel.reghrperYear =editJobVM.reghrperYear;

			storeExcel.scheduleComments = editJobVM.scheduleComments;

			// storeExcel.nonpubComments =editJobVM.nonpubComments;

			if (editJobVM.nonpubComments == "true"
					|| editJobVM.nonpubComments == "Y") {
				storeExcel.nonpubComments = "Y";
			} else {
				storeExcel.nonpubComments = "N";
			}

			// storeExcel.missionCritical = editJobVM.missionCritical;
			if (editJobVM.missionCritical == "true"
					|| editJobVM.missionCritical == "Y") {
				storeExcel.missionCritical = "Y";
			} else {
				storeExcel.missionCritical = "N";
			}

			// storeExcel.nightWork =editJobVM.nightWork;
			if (editJobVM.nightWork == "true" || editJobVM.nightWork == "Y") {
				storeExcel.nightWork = "Y";
			} else {
				storeExcel.nightWork = "N";
			}

			// storeExcel.localTravalusingpub =editJobVM.localTravalusingpub;

			if (editJobVM.localTravalusingpub == "true"
					|| editJobVM.localTravalusingpub == "Y") {
				storeExcel.localTravalusingpub = "Y";
			} else {
				storeExcel.localTravalusingpub = "N";
			}

			// storeExcel.pagerDuty =editJobVM.pagerDuty;
			if (editJobVM.pagerDuty == "true" || editJobVM.pagerDuty == "Y") {
				storeExcel.pagerDuty = "Y";
			} else {
				storeExcel.pagerDuty = "N";
			}

			storeExcel.pagerdutyComments = editJobVM.pagerdutyComments;

			// storeExcel.workonHoliday =editJobVM.workonHoliday;
			if (editJobVM.workonHoliday == "true"
					|| editJobVM.workonHoliday == "Y") {
				storeExcel.workonHoliday = "Y";
			} else {
				storeExcel.workonHoliday = "N";
			}

			// storeExcel.workonWeekends =editJobVM.workonWeekends;

			if (editJobVM.workonWeekends == "true"
					|| editJobVM.workonWeekends == "Y") {
				storeExcel.workonWeekends = "Y";
			} else {
				storeExcel.workonWeekends = "N";
			}

			// storeExcel.shiftWork =editJobVM.shiftWork;
			if (editJobVM.shiftWork == "true" || editJobVM.shiftWork == "Y") {
				storeExcel.shiftWork = "Y";
			} else {
				storeExcel.shiftWork = "N";
			}

			// storeExcel.warzone =editJobVM.warzone;

			if (editJobVM.warzone == "true" || editJobVM.warzone == "Y") {
				storeExcel.warzone = "Y";
			} else {
				storeExcel.warzone = "N";
			}

			// storeExcel.coop =editJobVM.coop;
			if (editJobVM.coop == "true" || editJobVM.coop == "Y") {
				storeExcel.coop = "Y";
			} else {
				storeExcel.coop = "N";
			}
			storeExcel.scheduledCloseDate = editJobVM.scheduledCloseDate;
			
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String dateInString = editJobVM.scheduledCloseDate;
			Date date = null ;
			try {
				 date = formatter.parse(dateInString);
			 	} catch (ParseException e) {
				e.printStackTrace();
			}
			
			storeExcel.scheduledCloseDateforsort = date;
			storeExcel.updateDate = editJobVM.updateDate;
			storeExcel.dateofStatus = editJobVM.dateofStatus;
			storeExcel.update(storeExcel);

		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// .update();

		return ok("success");
	}

	public static Result inactiveJob(String requestNumber) {

		StoreExcelFile storeExcel = StoreExcelFile.getregNumber(requestNumber);
		storeExcel.jobStatus = "inactive";
		storeExcel.update(storeExcel);
		return ok("");
	}

	public static Result onactiveJob(String requestNumber) {

		StoreExcelFile storeExcel = StoreExcelFile.getregNumber(requestNumber);
		storeExcel.jobStatus = "active";
		storeExcel.update(storeExcel);
		return ok("");
	}

	
	public static class UserDetailsVM {
		
		public String email;
		public String password;
		public String firstname;
		public String middlename;
		public String lastname;
		public String gender;
		public String dob;
		public String userstatus;
	    public Date  lastlogin;
	    public String userLoggedInstatus;
	    public String altemail;
	    public String residentcity;
	    public String altphnumber;
	    public String residentState;
	    public String zipcode;
	    public String desiredsalary;
	    public String willingtorelocate ;
	    public String jobsearchstatus;
	    public String currentjobtitle;
	    public String phnumber;
	    public String emailalert;
	    public String resumefilepath;
	    
	}
	
	public static Result getAllUsers(int pageNumber) {
		int count = 0;
		int activeusercount = 0;
		List<UserDetails> userDetails = UserDetails.getAllUsers(pageNumber, 10);
		count = UserDetails.getAllUsersCount(pageNumber);
		activeusercount =  UserDetails.getActiveUserCount(pageNumber);
		
		ArrayList<UserDetailsVM> userDetailsVM = new ArrayList<UserDetailsVM>();
		for(UserDetails ud:userDetails){
			UserDetailsVM userDVM = new UserDetailsVM();
			userDVM.email = ud.email;
			userDVM.password = ud.password;
			userDVM.firstname = ud.firstname;
			userDVM.middlename = ud.middlename;
			userDVM.lastname  = ud.lastname;
			userDVM.gender = ud.gender;
			userDVM.dob =  ud.dob;
			userDVM.userstatus = ud.userstatus;
			userDVM.lastlogin = ud.lastlogin;
			userDVM.altemail =  ud.altemail;
			userDVM.residentcity = ud.residentcity;
			userDVM.altphnumber = ud.altphnumber;
			userDVM.residentState = ud.residentState;
			userDVM.zipcode = ud.zipcode;
			userDVM.desiredsalary = ud.desiredsalary;
			userDVM.willingtorelocate = ud.willingtorelocate ;
			userDVM.jobsearchstatus = ud.jobsearchstatus;
			userDVM.currentjobtitle = ud.currentjobtitle;
			userDVM.phnumber = ud.phnumber;
			userDVM.emailalert = ud.emailalert;
			//Date d = new Date(ud.lastlogin);
			userDVM.userLoggedInstatus = ud.userLoggedInstatus;
			userDVM.resumefilepath = ud.resumefilepath;
			userDetailsVM.add(userDVM);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", userDetailsVM);
		map.put("userCount", count);
		map.put("activeusercount", activeusercount);
		return ok((Json.stringify(Json.toJson(map))));

	}

	public static Result getUserDetailsForAdmin(String email) {

		List<EducationDetails> ed = EducationDetails
				.getEducationDetailsByUserEmail(email);
		List<CertificationDetails> cd = CertificationDetails
				.getCertificateDetailsByUserEmail(email);
		List<EmploymentDetails> eds = EmploymentDetails
				.getEmploymentDetailsByUserEmail(email);
		// List<UserSkill> skills = ud.userSkill;
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("userDetails", ud);
		map.put("educationDetails", ed);
		map.put("certificationDetails", cd);
		map.put("employmentDetails", eds);

		return ok(Json.toJson(map));

	}

	public static Result onActiveUser(String email) {
		UserDetails ud = UserDetails.getUserByEmail(email);
		ud.userstatus = "active";
		ud.update();
		return ok("");
	}

	public static Result onInActiveUser(String email) {
		UserDetails ud = UserDetails.getUserByEmail(email);
		ud.userstatus = "inactive";
		ud.update();
		return ok("");
	}

	public static class AdminVM {
		public long id;
		public String username;
		public String password;
		public String role;
	}

	public static Result getallAdmin(int pageNumber) {
		int count = 0;
		List<Admin> ad = Admin.getAllAdmin(pageNumber, 10);
		count = Admin.getAllAdminCount(pageNumber);

		ArrayList<AdminVM> al = new ArrayList<AdminVM>();
		for (Admin a : ad) {
			AdminVM avm = new AdminVM();
			avm.id = a.id;
			avm.username = a.username;
			avm.password = a.password;
			avm.role = a.role;
			al.add(avm);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminList", al);
		map.put("adminCount", count);
		return ok(Json.toJson(map));
	}

	public static Result addnewAdmin(String email, String password) {

		Admin ad = Admin.getAdminByEmail(email);
		if (ad == null) {
			Admin a = new Admin();

			a.password = password;
			a.username = email;
			a.role = "admin";
			a.save();

			return ok(Json.toJson(a));
		} else {
			return ok();
		}

	}

	public static Result deleteAminByEmail(String email) {
		Admin ad = Admin.getAdminByEmail(email);
		if (ad != null) {
			ad.delete();
			return ok("");
		} else {

			return ok("");

		}

	}

	public static Result editAdminDetails(String emailnew, String emailold,
			String passnew, String passold) {

		Admin ad = Admin.isAdmin(emailold, passold);
		if (ad != null) {
			ad.username = emailnew;
			ad.password = passnew;
			ad.update();
			return ok("");
		} else {

			return ok("");

		}

	}

	
	
	public static Result getAllUserAppliedJobs(int currentpage,String jobType,String sortName,Boolean sortType) {

		List<AppliedJobs> jobs = new ArrayList<>();
		List<StoreExcelFile> userJobs = null;
		// both are selected for search(DSC)
		String emailId = session().get("email");
	//	userJobs = StoreExcelFile.getAllJobs(pageNumber, 10);
		int count = 0;
		//int count = 0;
	    if("Position".equalsIgnoreCase(sortName)){
	    	
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);					
					jobs = AppliedJobs.getAllJobsForUserByPositionAsc(currentpage, 10,emailId);
					
				}else{
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);		
					
					jobs = AppliedJobs.getAllJobsForUserByPositionDesc(currentpage, 10,emailId);
				}
				
			}else{
				if(sortType == true){
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
						emailId,jobType);
					jobs = AppliedJobs.getAllJobsForUserByPositionJobTypeAsc(currentpage, 10,jobType,emailId);
					
				}else{
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
							emailId,jobType);						
					jobs = AppliedJobs.getAllJobsForUserByPositionJobTypeDesc(currentpage, 10,jobType,emailId);
				}
				
			}
	    	
		} 
		
	 

	    if("Location".equalsIgnoreCase(sortName) ){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);
					jobs = AppliedJobs.getAllJobsForUserByLocationAsc(currentpage, 10,emailId);
					
				}else{
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);		
					jobs = AppliedJobs.getAllJobsForUserByLocationDesc(currentpage, 10,emailId);
				}
				
			}else{
				if(sortType == true){
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
							emailId,jobType);
					jobs = AppliedJobs.getAllJobsForUserByLocationJobTypeAsc(currentpage, 10,jobType,emailId);
					
				}else{
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
							emailId,jobType);		
					jobs = AppliedJobs.getAllJobsForUserByLocationJobTypeDesc(currentpage, 10,jobType,emailId);
				}
				
			}
	    	
		} 
	    
	    
	    if("Clearance".equalsIgnoreCase(sortName)){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);
					jobs = AppliedJobs.getAllJobsForUserByClearanceAsc(currentpage, 10,emailId);
					
				}else{
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);		
					jobs = AppliedJobs.getAllJobsForUserByClearanceDesc(currentpage, 10,emailId);
				}
				
			}else{
				if(sortType == true){
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
							emailId,jobType);
					jobs = AppliedJobs.getAllJobsForUserByClearanceJobTypeAsc(currentpage, 10,jobType,emailId);
					
				}else{count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
						emailId,jobType);		
					jobs = AppliedJobs.getAllJobsForUserByClearanceJobTypeDesc(currentpage, 10,jobType,emailId);
				}
				
			}
	    	
		} 
	    
	    if("Experiance".equalsIgnoreCase(sortName) ){
			if("All".equalsIgnoreCase(jobType) ){
				if(sortType == true){
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,emailId);
					jobs = AppliedJobs.getAllJobsForUserByExperianceAsc(currentpage, 10,emailId);
					
				}else{
					count = AppliedJobs.getAllAppliedJobsCountByEmail(currentpage,
							emailId);
					jobs = AppliedJobs.getAllJobsForUserByExperianceDesc(currentpage, 10,emailId);
				}
				
			}else{
				if(sortType == true){
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,jobType,jobType);
					jobs = AppliedJobs.getAllJobsForUserByExperienceJobTypeAsc(currentpage, 10,jobType,emailId);
					
				}else{
					count = AppliedJobs.getAllJobsCountByEmailAndJobStatus(currentpage,
							emailId,jobType);
					jobs = AppliedJobs.getAllJobsForUserByExperienceJobTypeDesc(currentpage, 10,jobType,emailId);
				}
				
			}
	    	
		} 

			List<SavedJobVM> jobVMs = new ArrayList<SavedJobVM>();

		for (AppliedJobs s : jobs) {

			SavedJobVM jobVM = new SavedJobVM();

			jobVM.requestNumber = s.jobno;
			jobVM.requestType = s.reqType;
			jobVM.labourCategory = s.positionname;
			jobVM.performanceLevel = s.performancelevel;
			jobVM.positionType = s.positiontype;
			jobVM.clearanceRequired = s.clearancereq;
			jobVM.workLocation = s.location;
			jobVM.workDescription = s.workDesc;
				
			jobVM.jobStatus = s.jobStatus;
			jobVM.skills = getAllUserSkill(s.skills);
			
			jobVM.manadatorySkills = getMandtorySkills(s.manadatorySkill);
			jobVM.desiredSkill = getDesiredSkills(s.desiredSkil);
		    jobVM.scheduledCloseDate = s.scheduledCloseDate;
		    jobVM.userskillsSpecification = s.userskillsSpecification;
			String DATE_FORMAT_NOW = "MM/dd/yyyy";
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		    String stringDate = sdf.format(date);
			
			//check for the due to pmo date if  before the todays date then does not add to list else add  
			try{
	    		Date todaysDate = sdf.parse(stringDate);
	 	        Date jobPMODate = sdf.parse(s.scheduledCloseDate);
	 	       if(jobPMODate.before(todaysDate)){
	 	    	  count = count - 1;
	            }else{
	            	jobVMs.add(jobVM);
	            }
	 	        
	 	    }catch(Exception e){
	 	     //handle exception
	 	    } 
			
			
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("jobs", jobVMs);
		map.put("jobsCount", count);
		return ok(Json.toJson(map));

	}

	public static class SendMailVM {
		public String subject;
		public String name;
		public String email;
		public String content;

	}

	public static Result sendFeedbackMail() {
		JsonNode json = request().body().asJson();
		//System.out.println("josn" + json);

		ObjectMapper objectMapper = new ObjectMapper();
		SendMailVM sendMailVM;
		try {

			sendMailVM = objectMapper.readValue(json.get("contactus")
					.traverse(), SendMailVM.class);

			final String username = Play.application().configuration()
					.getString("mail.id");
			
			final String password = Play.application().configuration()
					.getString("mail.password");
			

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.office365.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			try {

				Message feedback = new MimeMessage(session);
				feedback.setFrom(new InternetAddress(username));
				feedback.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(username));
				feedback.setSubject(sendMailVM.subject);
				// message.setText();
				BodyPart messageBodyPart = new MimeBodyPart();
				// Now set the actual message
				messageBodyPart.setText("\nName of person : "
						+ sendMailVM.name + "\nPerson Email:  " + "\n "
						+ sendMailVM.email + "\nContent: " + "\n"
						+ sendMailVM.content);
				// Create a multipar message
				Multipart multipart = new MimeMultipart();
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				// Send the complete message parts
				feedback.setContent(multipart);
				Transport.send(feedback);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ok();
	}

	public static Result saveUserTemplate() {
		String email = session().get("email");
		
		JsonNode json = request().body().asJson();
	    String template = json.get("userTemplate").asText();
	    String templatename = json.get("templateName").asText();
		
		UserTemplate ut = UserTemplate.getUserTemplateByName(templatename,
				email);
		if (ut != null) {
			ut.template = template;
			ut.update();
			return ok("");
		} else {
			UserTemplate userTemplate = new UserTemplate();
			userTemplate.email = email;
			userTemplate.templateName = templatename;
			userTemplate.template = template;
			userTemplate.save();
			return ok("");
		}
		// return ok("");
	}

	public static class UserTemnplateVM {

		public int id;
		public String email;
		public String template;
		public String templateName;
	}

	public static Result getSavedUserTemplate() {
		String email = session().get("email");

		List<UserTemplate> usertemplate = UserTemplate
				.getUserTemplateByName(email);

		ArrayList<UserTemnplateVM> userTemnplateVM = new ArrayList<UserTemnplateVM>();
		for (UserTemplate ut : usertemplate) {

			UserTemnplateVM utvm = new UserTemnplateVM();
			utvm.id = ut.id;
			utvm.email = ut.email;
			utvm.template = ut.template;
			utvm.templateName = ut.templateName;
			userTemnplateVM.add(utvm);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", userTemnplateVM);
		return ok(Json.toJson(map));
	}

	public static Result getUserSavedJobs(String currentPage) {
		String email = session().get("email");

		List<AppliedJobs> jobs = AppliedJobs.getUserAppliedJobByStatus(email);

		List<SavedJobVM> jobVMs = new ArrayList<SavedJobVM>();

		for (AppliedJobs s : jobs) {

			SavedJobVM jobVM = new SavedJobVM();

			jobVM.requestNumber = s.jobno;
			jobVM.requestType = s.reqType;
			jobVM.labourCategory = s.positionname;
			jobVM.performanceLevel = s.performancelevel;
			jobVM.positionType = s.positiontype;
			jobVM.clearanceRequired = s.clearancereq;
			jobVM.workLocation = s.location;
			jobVM.workDescription = s.workDesc;
			
			jobVM.jobStatus = s.jobStatus;
			jobVM.skills = getAllUserSkill(s.skills);
			jobVM.manadatorySkills = getMandtorySkills(s.manadatorySkill);
			jobVM.desiredSkill = getDesiredSkills(s.desiredSkil);
			jobVM.scheduledCloseDate = s.scheduledCloseDate;
			jobVM.userskillsSpecification = s.userskillsSpecification; 


			String DATE_FORMAT_NOW = "MM/dd/yyyy";
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		    String stringDate = sdf.format(date);
			
			//check for the due to pmo date if  before the todays date then does not add to list else add  
			try{
	    		Date todaysDate = sdf.parse(stringDate);
	 	        Date jobPMODate = sdf.parse(s.scheduledCloseDate);
	 	       if(jobPMODate.before(todaysDate)){
	 	    	  
	            }else{
	            	jobVMs.add(jobVM);
	            }
	 	        
	 	    }catch(Exception e){
	 	     //handle exception
	 	    } 
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("totalPages", 10);
		// map.put("currentPage", currentPage);
		map.put("jobs", jobVMs);
		return ok(Json.toJson(map));

		// return ok();
	}

	public static Result getallSkillsForAdmin(int currentPage) {

		int count = 0;
		List<UserSkill> us = UserSkill.getAllSkillsForAdmin(currentPage, 10);
		count = UserSkill.getAllSkillsCountForAdmin(currentPage, 10);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("skillsCount", count);
		map.put("allSkills", us);
		return ok(Json.toJson(map));

	}
	
	public static  Result deleteSelectedJob(String reqNumber){
		StoreExcelFile storeExcelFile  = StoreExcelFile.getregNumber(reqNumber);
		
		if(storeExcelFile != null){
			storeExcelFile.delete(storeExcelFile);
		}
		
		return ok("");
	}
	
	
	@JsonIgnore
	public static Result updateUserPassword(String email,String pass ){
		UserDetails ud =UserDetails.getUserByEmail(email);
		
		if(ud!= null){
				ud.password = pass;	
				ud.update();	
		}
		return ok("success");
	}
	
	@JsonIgnore
	public static Result deleteUser(String uname){
		UserDetails ud =UserDetails.getUserByEmail(uname);
		if(ud != null){
			ud.delete();
		}
		return ok("success");
	}
	
	
	@JsonIgnore
	public static Result  editSkillDetails(String newSkill,String oldSkill){
		UserSkill uk = UserSkill.getSkillByName(oldSkill);
		if(uk != null){
			uk.skillName = newSkill;
			uk.update();
		}
		return ok("success");
	}	
	
	@JsonIgnore
	public static Result  deleteSkill(String skillname){
		UserSkill uk = UserSkill.getSkillByName(skillname);
		if(uk != null){
			uk.delete();
		}
		return ok("success");
	}
	
	
	public static Result getUserSkills() {

		String email = session().get("email");
		UserDetails ud = UserDetails.getUserByEmail(email);
		List<UserSkill> us = ud.userSkill;
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("skillsCount", count);
		map.put("userSkill", us);
		return ok(Json.toJson(map));

	}

	public static Result deleteJobById(int id) {

		AppliedJobs apj = AppliedJobs.getUserAppliedJobById(id);
		if(apj != null){
			apj.delete();	
			return ok("success");
		}else{
			return ok("success");	
		}
		

	}
	
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ArchivedJobsVM {
		public String username;
		public long id;
		public String requestNumber;
		public String status;
		public String location;
		public String positionName;
		public String workDesc;
		public String jobStatus;
		public String jobno;
		public String positionname;
		public String positiontype;
		public String performancelevel;
		public String clearancereq;
		
		List<mskill> msSkils;
		List<dskill> dsSkills;

	}
	
	
	public static Result movetoArchive() {
		JsonNode json = request().body().asJson();
	
		
	    List<ArchivedJobsVM> addEducation;
		JsonNode eduJson = json.get("archivedJobsId");
		ObjectMapper addEducationmapper = new ObjectMapper();
		ObjectMapper mapper = new ObjectMapper();
		addEducation = mapper.convertValue(
				eduJson,
				addEducationmapper.getTypeFactory().constructCollectionType(
						List.class, ArchivedJobsVM.class));
		for (int i = 0; i < addEducation.size(); i++) {
			
			AppliedJobs apj = AppliedJobs.getUserAppliedJobByReqNumberAdminforArchive(addEducation.get(i).requestNumber,addEducation.get(i).username);
			if(apj != null){
				apj.archived = "Y";
				apj.update();
			}

		}
		
		return ok("success");	
		
	}
	
	public static Result deleteAllJob() {

		List<StoreExcelFile> items = Ebean.find(StoreExcelFile.class).findList();
		Ebean.delete(items);
		
		return ok("success");
	}
	
	public  static Result getAllStates(){
		List<States> st = States.getAllSates();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("states", st);
		return ok(Json.toJson(map));
	}
	
	
	public  static Result getAllJobSearchStatus(){
		List<JobSearchStatus> st = JobSearchStatus.getAllJobSearchStatus();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobsearchstatus", st);
		return ok(Json.toJson(map));
	}
	
	public static Result sendMailtoAllUser(String mailSubject,String mailContent){
		List <UserDetails> ud =  UserDetails.getallUserEmail();
		
		ArrayList<String> email = new ArrayList<String>();
		for(UserDetails u:ud){
			String emailid = u.email;
			email.add(emailid);
		}
		
		MailUtility mailu = new MailUtility();
		mailu.sendMailToAlluser(mailSubject,mailContent,email);
		
		return ok("success"); 
	}
	
	
	public static Result deactivateTheJobByPMODate(){
		 
		String DATE_FORMAT_NOW = "MM/dd/yyyy";
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    String stringDate = sdf.format(date);
	    
	    List <StoreExcelFile> stexcel = StoreExcelFile.getAllJobstoSchedular();
	    for(StoreExcelFile s: stexcel){
	    	 try{
	    		Date todaysDate = sdf.parse(stringDate);
	 	        Date jobPMODate = sdf.parse(s.scheduledCloseDate);
	 	       if(jobPMODate.before(todaysDate)){
	                s.jobStatus = "inactive";
	                s.update(s);
	            }else{
	            	s.jobStatus = "active";
	                s.update(s);
	            }
	 	        
	 	    }catch(Exception e){
	 	     //handle exception
	 	    } 
	    }
		return ok();
	}
	
	public static Result sendmailAlertToUserAboutJobMatched(){
	   List <UserDetails> ud = UserDetails.getallUserEmail();
		for(UserDetails u:ud){
		  
			List <SendEmailAlert> sendEmail = SendEmailAlert.getAllPositionMatchedJobCount(u.email);
			if(sendEmail.size() != 0){
				MailUtility mailUtility = new  MailUtility();
				mailUtility.sendmailAlertToUserAboutJobMatched(u.email,sendEmail.size());
				
			}
			
		}
		
		return ok("");
	} 
 	
	
	public static Result  deleteSendEmailAlertData(){
		List<SendEmailAlert> items = Ebean.find(SendEmailAlert.class).findList();
		if(items.size()!= 0){
			Ebean.delete(items);
			return ok("success");
		}else{
			return ok("table empty");
		}
	}
	
	public static Result unSubribeFormEmailAlert(boolean alert){
		
		String email =  session().get("email");
		
		if(alert == false){
			UserDetails ud = UserDetails.getUserByEmail(email);
			if(ud != null){
				ud.emailalert = "No";
				ud.update();
				
			}
			return ok("success");
		}else{
			UserDetails ud = UserDetails.getUserByEmail(email);
			if(ud != null){
				ud.emailalert = "Yes";
				ud.update();
				
			}
			return ok("success");
		}
		
		
	}
	
 public static Result 	unSubribeFormEmailAlertByAdmin(boolean alert,String email){
		
		if(alert == false){
			UserDetails ud = UserDetails.getUserByEmail(email);
			if(ud != null){
				ud.emailalert = "No";
				ud.update();
				
			}
			return ok("success");
		}else{
			UserDetails ud = UserDetails.getUserByEmail(email);
			if(ud != null){
				ud.emailalert = "Yes";
				ud.update();
				
			}
			return ok("success");
		}
 }

 public static  Result  deleteUserAppliedJob(String jobNum){
	 AppliedJobs appliedJobs = AppliedJobs.getUserAppliedJobByReqNumberAdmin(jobNum);
	 if(appliedJobs != null ){
		 appliedJobs.delete();
	 }
	 return ok("success");
 }
 
 public static Result deleteUserTemplateById(int id){
	 session().get("email");
	 UserTemplate  ut = UserTemplate.deleteUserTemplateByid(session().get("email"),id);
	if(ut != null){
		ut.delete();
	}
	 return ok("success");
 }
 
 public static Result updateTemplateById(){
	 
	 	JsonNode json = request().body().asJson();
	    int id  = json.get("id").asInt();
	    String name = json.get("name").asText();
	    String content =  json.get("content").asText();
	 
	    UserTemplate  ut = UserTemplate.deleteUserTemplateByid(session().get("email"),id);
	    	if(ut != null){
	    		ut.templateName = name;
	    		ut.template = content;
	    		ut.update();
	    	}
	    	return ok("success");
 	}
 
 
//upload the excel
	public static Result uploadPositions() {
		play.mvc.Http.MultipartFormData body = request().body()
				.asMultipartFormData();
		MultipartFormData.FilePart excelpart = body.getFile("file");
		File excelfile = excelpart.getFile();
		String filename = excelpart.getFilename();
		//System.out.println("filename"+filename);
		// ArrayList<String> al = new ArrayList<>();
		Workbook wb_xssf; //Declare XSSF WorkBook
	    Workbook wb_hssf;//Declare HSSf WorkBook
		
		int newRows = 0;
		int updatedRows = 0;
		Sheet sheet = null;
		String  jobNum = ""; 
		String positionName = "";
		try {
			
			 FileInputStream file = new FileInputStream(excelfile);
			 String fileExtn = FilenameUtils.getExtension(filename);
			
			 if (fileExtn.equalsIgnoreCase("xlsx")){
			       wb_xssf = new XSSFWorkbook(file);
			       
			       sheet = wb_xssf.getSheetAt(0);
		      }

			 if (fileExtn.equalsIgnoreCase("xls")){
			      POIFSFileSystem fs = new POIFSFileSystem(file);
		    	  wb_hssf = new HSSFWorkbook(fs);
		    	  sheet = wb_hssf.getSheetAt(0);
		      }
			 
			 UserPosition userPosition = null;
			Row row;
			String reqNo = null;

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				reqNo = null;
				row = rowIterator.next();
				if (!row.getZeroHeight()) {

					UserPosition sd = new UserPosition();
					Cell c = row.getCell(0);

					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.position = c.getNumericCellValue() + "";
 						break;
					case Cell.CELL_TYPE_STRING:
						//System.out.println("nukmberString");
						/*userPosition = UserPosition.getregNumber(c
								.getStringCellValue());*/
						positionName =  c
								.getStringCellValue();
						break;
					}

					c = row.getCell(1);

					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						userPosition = UserPosition.getRecoredByPositionNameAndLevel(positionName,c
								.getStringCellValue());
						
						
						if (userPosition != null) {
							userPosition.level =  Double.toString(c
									.getNumericCellValue());;
							userPosition.position = positionName;
						} else {
							sd.level = c.getStringCellValue();
							sd.position = positionName;
						}
						break;
					case Cell.CELL_TYPE_STRING:
						// sd.requestType=c.getStringCellValue();
						
						userPosition = UserPosition.getRecoredByPositionNameAndLevel(positionName,c
								.getStringCellValue());
						
						if (userPosition != null) {
							userPosition.level = c.getStringCellValue();
							userPosition.position = positionName;
						} else {
							sd.level = c.getStringCellValue();
							sd.position = positionName;
						}

						break;
					}

					c = row.getCell(2);
					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						if (userPosition != null) {
							userPosition.rate =  Double.toString(c
									.getNumericCellValue());
						} else {
							sd.rate =  Double.toString(c.getNumericCellValue());
							//userPosition = sd.l;
						
						}
						break;
					case Cell.CELL_TYPE_STRING:
						
						
						if (userPosition != null) {
							userPosition.rate = c
									.getStringCellValue();
						} else {
							sd.rate = c.getStringCellValue();
							//userPosition = sd.l;
						
						}

						break;
					}
						
					if (userPosition != null) {
						
						//userPosition.id = userPosition.id;
						userPosition.update(userPosition);
							updatedRows = updatedRows + 1;
							System.out.println("updatedRows" + updatedRows
									+ "reqNo" + reqNo);
						
						// System.out.println("in update");
					} else {
							newRows = newRows + 1;
							sd.save();
					}

				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			flash().put("error", "Upload Failed");
			String newrowscount = Integer.toString(newRows);
			String updatedRowsCount = Integer.toString(updatedRows);
			//System.out.println("updatedRowsCount" + updatedRowsCount);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("newrowscount", newrowscount);
			map.put("updatedRowsCount", updatedRowsCount);

			return ok(Json.stringify(Json.toJson(map)));
		}

		String newrowscount = Integer.toString(newRows);
		String updatedRowsCount = Integer.toString(updatedRows);
		//System.out.println("updatedRowsCount" + updatedRowsCount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newrowscount", newrowscount);
		map.put("updatedRowsCount", updatedRowsCount);
		Application.deactivateTheJobByPMODate();
		return ok(Json.stringify(Json.toJson(map)));
	}
 
	public static class EditPositionRateVM{
		
		public int id;
		public String level;
		public String rate;
		public String position;
	}
	
	
	public static Result  editPositionRateDetails(){
        JsonNode json = request().body().asJson();
		//System.out.println("json"+json);
		List<EditPositionRateVM> positionRateVM;
		JsonNode posJson = json.get("positionList");
		ObjectMapper mapper = new ObjectMapper();
		positionRateVM = mapper.convertValue(posJson, mapper.getTypeFactory()
				.constructCollectionType(List.class, EditPositionRateVM.class));
	
		for(int i=0;i< positionRateVM.size();i++){
			UserPosition u = UserPosition.getRecoredByPositionNameAndLevelAsc(positionRateVM.get(i).position, positionRateVM.get(i).level)	;
			if(u != null){
				u.setRate(positionRateVM.get(i).rate);
				u.setPosition(positionRateVM.get(i).position);
				u.setLevel(positionRateVM.get(i).level);
				u.update();
			}
		}
		return ok("success");
	}
	
	public static class DefaultValuesVM{
		 public int  hours ;
	     public  double  fringe ;
	     public  double   overhead ;
	     public  double   	ganda ;
	     public  double   	gandaWrap ;
	     public  double   overheadWrap ;
	     public  double   	fringeWrap  ;
	     public  double   profit ;
		
	}
	
	
	
	public static Result  updateDefualtValues() throws JsonParseException, JsonMappingException, IOException{
		
		  JsonNode json = request().body().asJson();
		//  System.out.println("json"+json);
		  List<DefaultValues> items = Ebean.find(DefaultValues.class).findList();
			if(items.size()!= 0){
				Ebean.delete(items);
				//return ok("success");
			}
		  
		  DefaultValuesVM defaultValuesVM;
		  ObjectMapper objectMapper = new ObjectMapper();
		  defaultValuesVM = objectMapper.readValue(json.get("deFaultValues")
					.traverse(), DefaultValuesVM.class);
		  DefaultValues  DValues = new DefaultValues();
		  
		  DValues.fringe = defaultValuesVM.fringe;
		  DValues.hours = defaultValuesVM.hours;
		  
		  DValues.overhead = defaultValuesVM.overhead;
		  DValues.ganda = defaultValuesVM.ganda;
		  
		  DValues.gandaWrap = defaultValuesVM.gandaWrap;
		  DValues.overheadWrap =defaultValuesVM.overheadWrap;
		  DValues.fringeWrap = defaultValuesVM.fringeWrap;
		  DValues.profit = defaultValuesVM.profit;
		  
		  DValues.save();
		  
		  return ok("");
	}
	
	public static Result  getDefaultValues(){
	
		DefaultValues df = DefaultValues.getDefaultValues();
		HashMap<String , Object> map = new HashMap<>();
		map.put("values", df);
		
		return ok(Json.stringify(Json.toJson(map)));
	}  
	
	public static Result  gotoUserProfile(){
		//System.out.println();
		return redirect("/dashboard#/extra-profile");
		
	}
	
	public static Result getSampleResumeTemplate(){
			String filepath = "app/resumetemplate/Ardent-Resume-Template.doc";
			File f = new File(filepath);
		    System.out.println("f"+f);
		    
		    return ok(f);		
		
	}

	public static Result generateDocument(String id) throws IOException {
		final String rootDir = Play.application().configuration()
				.getString("resume.path");
		//System.out.println("rootdir" + rootDir);
		File f = new File(rootDir);
		if(!f.exists()){
			f.mkdir();
		}
		String candidiatename = "";
		String ResumeName = ""; 
		
		
		XWPFDocument document= new XWPFDocument(); 
		   //Write the Document in file system
		 
		   int ids = Integer.parseInt(id);

		   AppliedJobs ap = AppliedJobs.getUserAppliedJobById(ids);
					String email = ap.username;
					UserDetails ud = UserDetails.getUserByEmail(email);
		   //List<UserExperiance> userExperiance = ud.userExperiance;
					
					
					String JobId = ap.jobno;
					String csrNumber = ap.performancelevel;
					
					if (!("NA".equalsIgnoreCase(ud.middlename))) {
						candidiatename = ud.firstname + " " + ud.middlename + " "
								+ ud.lastname;
						ResumeName =  
								   ud.lastname+"_"+ud.firstname;
					} else {
						candidiatename = ud.firstname + " NMI " + ud.lastname;
						ResumeName = "";
						ResumeName  = ud.lastname + "_" + ud.firstname;
					}
					
					ResumeName = ResumeName + "_"+JobId;
					String fileName = rootDir+candidiatename + ".docx";
				
					  FileOutputStream out = new FileOutputStream(
							   new File(fileName));
					  String clor = "E6E6FA";
					  
					 XWPFTable  userDetailsTable = document.createTable();	
					 userDetailsTable.setWidth(100);
					   
					   XWPFTableRow userDetailsTableRowOne = userDetailsTable.getRow(0);
					   userDetailsTableRowOne.getCell(0).setText("Candidate Name: "+candidiatename);
					   userDetailsTableRowOne.getCell(0).setColor(clor);
					  
					   
					   CTTblWidth widthmanSkill1 =  userDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
					   widthmanSkill1.setType(STTblWidth.DXA);
					   widthmanSkill1.setW(BigInteger.valueOf(6000));
					  
					   
					   userDetailsTableRowOne.addNewTableCell();
					   userDetailsTableRowOne.getCell(1).setText("Company Name: "+"Booz Allen Hamilton");	
					   userDetailsTableRowOne.getCell(1).setColor(clor);
					  
					   CTTblWidth userDetailsWidth2 =  userDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
					   userDetailsWidth2.setType(STTblWidth.DXA);
					   userDetailsWidth2.setW(BigInteger.valueOf(5000));
					  
					    
					   XWPFTableRow userDetailsTableRowTwo = userDetailsTable.createRow();
					
					   userDetailsTableRowTwo.getCell(0).setText("CSR Number: "+ap.jobno);
					   userDetailsTableRowTwo.getCell(0).setColor(clor);
					  
					   CTTblWidth userDetailsWidth3 =  userDetailsTableRowTwo.getCell(0).getCTTc().addNewTcPr().addNewTcW();
					   userDetailsWidth3.setType(STTblWidth.DXA);
					   userDetailsWidth3.setW(BigInteger.valueOf(5000));
					  
					   userDetailsTableRowTwo.addNewTableCell();
					   userDetailsTableRowTwo.getCell(1).setText("Clearance Level: "+"TS/SCI with Favorable Polygraph");	
					   userDetailsTableRowTwo.getCell(1).setColor(clor);
					  
					   
					   CTTblWidth userDetailsWidth4 =  userDetailsTableRowTwo.getCell(1).getCTTc().addNewTcPr().addNewTcW();
					   userDetailsWidth4.setType(STTblWidth.DXA);
					   userDetailsWidth4.setW(BigInteger.valueOf(5000));
					  
					   
					   
					   XWPFTableRow  userDetailsTableRowThree = userDetailsTable.createRow();
						
					   
					   userDetailsTableRowThree.getCell(0).setText("Labor Category: "+ap.positionname);
					   userDetailsTableRowThree.getCell(0).setColor(clor);
					   
					   CTTblWidth userDetailsWidth5 =  userDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewTcW();
					   userDetailsWidth5.setType(STTblWidth.DXA);
					   userDetailsWidth5.setW(BigInteger.valueOf(5000));
					  
					   
					   userDetailsTableRowThree.addNewTableCell();
					   userDetailsTableRowThree.getCell(1).setText("Skill Level: "+ap.performancelevel);	
					   userDetailsTableRowThree.getCell(1).setColor(clor);
					  
					   CTTblWidth userDetailsWidth6 =  userDetailsTableRowThree.getCell(1).getCTTc().addNewTcPr().addNewTcW();
					   userDetailsWidth6.setType(STTblWidth.DXA);
					   userDetailsWidth6.setW(BigInteger.valueOf(5000));
					  
					   XWPFTableRow userDetailsTableRowFour = userDetailsTable.createRow();
					   userDetailsTableRowFour.getCell(0).setText("Skill Level Justification: "+ap.userskillsSpecification);
					   userDetailsTableRowFour.getCell(0).setColor(clor);
					   userDetailsTableRowFour.addNewTableCell();
					   userDetailsTableRowFour.getCell(1).setColor(clor);
					   
					   userDetailsTableRowFour.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
					   userDetailsTableRowFour.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
					   //userDetailsTableRowFour.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
					  
					   
					   XWPFParagraph paragraph4 = document.createParagraph();
					   XWPFRun run4=paragraph4.createRun();
					   run4.setText("");
					   
					   List<MandatorySkills> mandatorySkils = getMandtorySkills(ap.manadatorySkill);
					    
					    XWPFTable manSkill = document.createTable();
					    
						   
						   XWPFTableRow manSkillRowone = manSkill.getRow(0);
						   manSkillRowone.addNewTableCell();
						   manSkillRowone.getCell(0).setText("Mandatory Skills");
						
						   CTTblWidth widthmanSkill0 =  manSkillRowone.getCell(0).getCTTc().addNewTcPr().addNewTcW();
						   widthmanSkill0.setType(STTblWidth.DXA);
						   widthmanSkill0.setW(BigInteger.valueOf(5000));
						  // CTTableStyle style = ((Object) manSkillRowone.getCell(0).getCTTc()).addNewCnfStyle(Font.BOLD);
						  // style.set
						   manSkillRowone.getCell(0).setColor(clor);
						   // manSkillRowone.getCell(0).getCTTc().g;
						   
						   manSkillRowone.getCell(1).setText("Candidate’s Skills/Experience");	
						   manSkillRowone.getCell(1).setColor(clor);
						  
						   CTTblWidth widthmanSkill2 =  manSkillRowone.getCell(1).getCTTc().addNewTcPr().addNewTcW();
						   widthmanSkill2.setType(STTblWidth.DXA);
						   widthmanSkill2.setW(BigInteger.valueOf(5000));
						  
						   for (MandatorySkills ms : mandatorySkils) {
							   XWPFTableRow manSkillRowTwo = manSkill.createRow();
							   
							   manSkillRowTwo.getCell(0).setText(ms.mskill);
							   
							   manSkillRowTwo.addNewTableCell();
							   manSkillRowTwo.getCell(1).setText(ms.comment);	
								
							}
					   
					   
						   XWPFParagraph paragraph3 = document.createParagraph();
						   XWPFRun run3=paragraph3.createRun();
						   run3.setText("");
						   
		   List<DesiredSkills> desiredSkills = getDesiredSkills(ap.desiredSkil);
		   XWPFTable desiredSkill = document.createTable();
		   desiredSkill.setWidth(100);
		   
		   
		   XWPFTableRow tableRowTwo = desiredSkill.getRow(0);
		  
		   tableRowTwo.getCell(0).setText("Desired Skills");
		   tableRowTwo.getCell(0).setColor(clor);

		   CTTblWidth widthdesSkill0 =  tableRowTwo.getCell(0).getCTTc().addNewTcPr().addNewTcW();
		   widthdesSkill0.setType(STTblWidth.DXA);
		   widthdesSkill0.setW(BigInteger.valueOf(5000));
		   
		   tableRowTwo.addNewTableCell();
		   tableRowTwo.getCell(1).setText("Candidate’s Skills/Experience");	
		   tableRowTwo.getCell(1).setColor(clor);
		  
		   CTTblWidth widthdesSkill1 =  tableRowTwo.getCell(1).getCTTc().addNewTcPr().addNewTcW();
		   widthdesSkill1.setType(STTblWidth.DXA);
		   widthdesSkill1.setW(BigInteger.valueOf(5000));
		   
		   
		  
		   for (DesiredSkills ds : desiredSkills) {
			   XWPFTableRow tableRowThree = desiredSkill.createRow();
			   tableRowThree.getCell(0).setText(ds.dskill);
			   
			   tableRowThree.addNewTableCell();
			   tableRowThree.getCell(1).setText(ds.comment);	
				
			}
		  
		  
			   XWPFParagraph paragraph2 = document.createParagraph();
			   XWPFRun run2=paragraph2.createRun();
			   run2.setText("");
			   run2.setBold(true);
			   run2.setColor(clor);
			   XWPFTable  certificationDetailsTable = document.createTable();;
			   //certificationDetailsTable.setWidth(100);
			   XWPFTableRow certificationDetailsTableRowTHREE = certificationDetailsTable.getRow(0);
				  
			   certificationDetailsTableRowTHREE.getCell(0).setText("Certifications");
			   certificationDetailsTableRowTHREE.getCell(0).setColor(clor);
			  
			   certificationDetailsTableRowTHREE.addNewTableCell();
			   certificationDetailsTableRowTHREE.getCell(1).setColor(clor);
			   
			   certificationDetailsTableRowTHREE.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			   certificationDetailsTableRowTHREE.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			  // certificationDetailsTableRowTHREE.getCell(1).getCTTc().getTcPr().setGridSpan(Alignment.CENTER);
			   
			   //certificationDetailsTableRowTHREE.getCell(1).getCTTc().addNewColor().setVal("FF0000")
			   XWPFTableRow certificationDetailsTableRowOne = certificationDetailsTable.createRow();
			    
			   //  employmentDetailsTableRowOne.addNewTableCell();
			   certificationDetailsTableRowOne.getCell(0).setText("List of Certifications");
			   certificationDetailsTableRowOne.getCell(0).setColor(clor);
			  
			   CTTblWidth widthcert =  certificationDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
			   widthcert.setType(STTblWidth.DXA);
			   widthcert.setW(BigInteger.valueOf(5000));
			  
			   
			   
			   certificationDetailsTableRowOne.addNewTableCell();
			   certificationDetailsTableRowOne.getCell(1).setText("Certification Date:");	
			   certificationDetailsTableRowOne.getCell(1).setColor(clor);
			   
			   CTTblWidth width =  certificationDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
			   width.setType(STTblWidth.DXA);
			   width.setW(BigInteger.valueOf(5000));
			  
			   
			   List<CertificationDetails> cd = CertificationDetails
						.getCertificateDetailsByUserEmail(email);
				for (CertificationDetails c : cd) {
				
					
					XWPFTableRow certificationDetailsTableRowTwo = certificationDetailsTable.createRow();
					certificationDetailsTableRowTwo.getCell(0).setText(c.certName);
					//certificationDetailsTableRowTwo.getCell(0).setColor(clor);
					
					certificationDetailsTableRowTwo.addNewTableCell();
					certificationDetailsTableRowTwo.getCell(1).setText(c.certYear);	
					//certificationDetailsTableRowTwo.getCell(1).setColor(clor);
					   
				}
			   
			   
			   
				XWPFParagraph paragraph1 = document.createParagraph();
				   XWPFRun run1=paragraph1.createRun();
				   run1.setText("");
				   run1.setBold(true);
				   run1.setColor(clor);
				 
				   
			   XWPFTable  educationDetailsTable = document.createTable();;
			   //educationDetailsTable.setWidth(100);
			   
			   XWPFTableRow educationDetailsTableRowthree = educationDetailsTable.getRow(0);
				  
			   educationDetailsTableRowthree.getCell(0).setText("Education");
			   educationDetailsTableRowthree.getCell(0).setColor(clor);
			  
			   educationDetailsTableRowthree.addNewTableCell();
			   educationDetailsTableRowthree.getCell(1).setColor(clor);
			 
			   educationDetailsTableRowthree.addNewTableCell();
			   educationDetailsTableRowthree.getCell(2).setColor(clor);
			 
			   
			   educationDetailsTableRowthree.addNewTableCell();
			   educationDetailsTableRowthree.getCell(2).setColor(clor);
			 
			   educationDetailsTableRowthree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			   educationDetailsTableRowthree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			   educationDetailsTableRowthree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			   educationDetailsTableRowthree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			   
			   
			   XWPFTableRow educationDetailsTableRowOne = educationDetailsTable.createRow();
			 //  employmentDetailsTableRowOne.addNewTableCell();
			   educationDetailsTableRowOne.getCell(0).setText("Degree");
			   educationDetailsTableRowOne.getCell(0).setColor(clor);
			   
			   CTTblWidth widthedu1 =  educationDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
			   widthedu1.setType(STTblWidth.DXA);
			   widthedu1.setW(BigInteger.valueOf(3500));
			   
			   educationDetailsTableRowOne.addNewTableCell();
			   educationDetailsTableRowOne.getCell(1).setText("School Name ");	
			   educationDetailsTableRowOne.getCell(1).setColor(clor);
				
			   
			   CTTblWidth widthedu2 =  educationDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
			   widthedu2.setType(STTblWidth.DXA);
			   widthedu2.setW(BigInteger.valueOf(3500));
			   educationDetailsTableRowOne.addNewTableCell();
			   educationDetailsTableRowOne.getCell(2).setText("Degree/Major");	
			   educationDetailsTableRowOne.getCell(2).setColor(clor);
				  
			   CTTblWidth widthedu3 =  educationDetailsTableRowOne.getCell(2).getCTTc().addNewTcPr().addNewTcW();
			   widthedu3.setType(STTblWidth.DXA);
			   widthedu3.setW(BigInteger.valueOf(3500));
			   
			   educationDetailsTableRowOne.addNewTableCell();
			   educationDetailsTableRowOne.getCell(3).setText("Completion Date: ");	
			   educationDetailsTableRowOne.getCell(3).setColor(clor);
			   CTTblWidth widthedu4 =  educationDetailsTableRowOne.getCell(3).getCTTc().addNewTcPr().addNewTcW();
			   widthedu4.setType(STTblWidth.DXA);
			   widthedu4.setW(BigInteger.valueOf(3500));
			   
			   
			   List<EducationDetails> ed = EducationDetails
						.getEducationDetailsByUserEmail(email);
				for (EducationDetails edu : ed) {
			   
					XWPFTableRow educationDetailsTableRowTWO = educationDetailsTable.createRow();
					 //  employmentDetailsTableRowOne.addNewTableCell();
					educationDetailsTableRowTWO.getCell(0).setText(edu.degree);
					//educationDetailsTableRowTWO.getCell(0).setColor(clor);
					  
					   
					educationDetailsTableRowTWO.addNewTableCell();
					educationDetailsTableRowTWO.getCell(1).setText(edu.instituteName);	
					//educationDetailsTableRowTWO.getCell(1).setColor(clor);
						
					educationDetailsTableRowTWO.addNewTableCell();
					educationDetailsTableRowTWO.getCell(2).setText(edu.degree);	
					//educationDetailsTableRowTWO.getCell(2).setColor(clor);
						  
					educationDetailsTableRowTWO.addNewTableCell();
					educationDetailsTableRowTWO.getCell(3).setText(edu.toDate);	
					//educationDetailsTableRowTWO.getCell(3).setColor(clor);
					   
				}
		   
				XWPFParagraph paragraph = document.createParagraph();
				   XWPFRun run=paragraph.createRun();
				   run.setText("");
				   run.setBold(true);
				   run.setColor(clor);
			   XWPFTable  employmentDetailsTable = document.createTable();;
			  
			   XWPFTableRow EmploymentDetailsTableRowthree = employmentDetailsTable.getRow(0);
				  
			   EmploymentDetailsTableRowthree.getCell(0).setText("WORK EXPERIENCE");
			   EmploymentDetailsTableRowthree.getCell(0).setColor(clor);
			  
			   EmploymentDetailsTableRowthree.addNewTableCell();
			   EmploymentDetailsTableRowthree.getCell(1).setColor(clor);
			 
			   EmploymentDetailsTableRowthree.addNewTableCell();
			   EmploymentDetailsTableRowthree.getCell(2).setColor(clor);
			 
			   
			   EmploymentDetailsTableRowthree.addNewTableCell();
			   EmploymentDetailsTableRowthree.getCell(2).setColor(clor);
			 
			   EmploymentDetailsTableRowthree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			   EmploymentDetailsTableRowthree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			   EmploymentDetailsTableRowthree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			   EmploymentDetailsTableRowthree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			 
			   
			   
			   List<EmploymentDetails> eds = EmploymentDetails
						.getEmploymentDetailsByUserEmail(email);
			   
			   
			   int[] cols = {10000,0,0,0}; 
				
			   
			   for (EmploymentDetails ue : eds) {
					XWPFTableRow employmentDetailsTableRowTwo = employmentDetailsTable.createRow();
					
					employmentDetailsTableRowTwo.getCell(0).setText("Employers Name: "+ue.companyName);
					//employmentDetailsTableRowTwo.getCell(0).setColor(clor);
					
					employmentDetailsTableRowTwo.addNewTableCell();
					employmentDetailsTableRowTwo.getCell(1).setText("Start Date: "+ue.startdate +"/"+ue.startYear);	
					//employmentDetailsTableRowTwo.getCell(1).setColor(clor);
					
					employmentDetailsTableRowTwo.addNewTableCell();
					employmentDetailsTableRowTwo.getCell(2).setText("End Date: "+ue.enddate +"/"+ue.endYear);	
					//employmentDetailsTableRowTwo.getCell(2).setColor(clor);
					  
					employmentDetailsTableRowTwo.addNewTableCell();
					employmentDetailsTableRowTwo.getCell(3).setText("Position Held: "+ue.position);	
					//employmentDetailsTableRowTwo.getCell(3).setColor(clor);
					XWPFTableRow employmentDetailsTableRowThree = employmentDetailsTable.createRow();
					employmentDetailsTableRowThree.getCell(0).setText(ue.expdesc);
					//employmentDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(100));
					employmentDetailsTableRowThree.addNewTableCell();
					employmentDetailsTableRowThree.addNewTableCell();
					employmentDetailsTableRowThree.addNewTableCell();
					employmentDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
					employmentDetailsTableRowThree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
					employmentDetailsTableRowThree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
					employmentDetailsTableRowThree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
						   
					
					
					
				}
			   
			   
			   
		    document.write(out);
		    out.close();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String date = sdf.format(new Date());
			response().setContentType("application/docx");
			response().setHeader("Content-Disposition",
					"inline; filename=" + fileName);
			System.out.println("document created successfully..");
			File file = new File(fileName);
			return ok(file);
			
	}
	
	
	public static Result 	inviteUsers(){
	
    JsonNode json	 = request().body().asJson();
    System.out.println(json);
    ObjectMapper objectMapper =new ObjectMapper();
    SendMailVM sendMailVM;
    try{
    	sendMailVM = objectMapper.readValue(json.get("inviteUsers").traverse(), SendMailVM.class);
             	
    	MailUtility mu  = new MailUtility();
    	mu.inviteUsers(sendMailVM.email,sendMailVM.subject,sendMailVM.content);
     	
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    return ok("");
	}

	public static Result getAllallDegree(){
		
		List<Degree> d = Degree.getAllDegress();
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("allDegress", d);
	   
 		return ok(Json.stringify(Json.toJson(map)));
	}

	public static Result getAllYear(){
		
		List<Year>  y = Year.getAllYear();
		HashMap<String, Object> map = new HashMap<>();
		map.put("years", y);
		return ok(Json.stringify(Json.toJson(map)));
		
	}
	
	
	public static Result getAllMonth(){
		List<Month>  m = Month.getAllMonth();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("months", m);

		return ok(Json.stringify(Json.toJson(map)));
		
	}
	
	
	 
    public static void createDir(String rootDir) {
		File file3 = new File(rootDir);
		if (!file3.exists()) {
			file3.mkdirs();
		}
	}
    
    
	final static String rootDir = Play.application().configuration()
			.getString("resume.storage.path");
	static {
		createRootDir();
	}
	
	public static void createRootDir() {
		File file = new File(rootDir);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	
	
	public static Result uploadResume(){
		 createDir(rootDir);
		
		play.mvc.Http.MultipartFormData.FilePart docFile;
		String fileName = null;
		String filenamedbpath = null;
		
		//System.out.println("getAbsolutePath:"+Play.application().path().getAbsolutePath());
		//filenamedbpath  = Play.application().path().getAbsolutePath();
		
		filenamedbpath =   "/home/jobby/resumes";
		play.mvc.Http.MultipartFormData body = request().body()
				.asMultipartFormData();
		docFile = body.getFile("file");
		UserDetails u = UserDetails.getUserByEmail(session().get("email"));
		if (docFile != null) {
			fileName = docFile.getFilename();
			File file = docFile.getFile();

			File f = new File( rootDir + "/" + fileName);
			filenamedbpath =   rootDir + "/" + fileName;
	        try {
				Files.copy(file.toPath(), f.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		
			u.setResumefilepath(filenamedbpath);
			u.update();
	        
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			flash("Success", "File Uploaded successfully");
		}

		return ok(u.resumefilepath);
		
	}
	
	@SuppressWarnings("unused")
	public static Result getresume(String email){
		UserDetails u =UserDetails.getUserByEmail(email);
		
		if(u.resumefilepath != null){
			File f = new File(u.resumefilepath);
			if(f != null){
				return ok(f);	
			}
		}else{
			return ok();	
		}
		
		return ok();
		
	}
	
	@SuppressWarnings("unused")
	public static Result  getuserresume(){
		UserDetails u =UserDetails.getUserByEmail(session().get("email"));
		if(u.resumefilepath != null){
			File f = new File(u.resumefilepath);
			if(f != null){
				return ok(f);	
			}
		}else{
			return ok();	
		}
		
		return ok();
		
	}
	
	 public static  Result getapplyWithoutRegResume( String id){
		 final String rootDir = Play.application().configuration()
					.getString("resume.path");
			//System.out.println("rootdir" + rootDir);
			File f = new File(rootDir);
			if(!f.exists()){
				f.mkdir();
			}
			String candidiatename = "";
			String ResumeName = ""; 
			
			
			XWPFDocument document= new XWPFDocument(); 
			   //Write the Document in file system

			StoreExcelFile s = StoreExcelFile.getJobByRequestNumber(id);
						
						ResumeName = ResumeName + "_"+s.requestNumber;
						String fileName = rootDir+"your_Name" + ".docx";
					
						  FileOutputStream out = null;
						try {
							out = new FileOutputStream(
									   new File(fileName));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						  String clor = "E6E6FA";
						  
						 XWPFTable  userDetailsTable = document.createTable();	
						 userDetailsTable.setWidth(100);
						   
						   XWPFTableRow userDetailsTableRowOne = userDetailsTable.getRow(0);
						   userDetailsTableRowOne.getCell(0).setText("Candidate Name: " +"Put Your Name here" );
						   userDetailsTableRowOne.getCell(0).setColor(clor);
						  
						   
						   CTTblWidth widthmanSkill1 =  userDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
						   widthmanSkill1.setType(STTblWidth.DXA);
						   widthmanSkill1.setW(BigInteger.valueOf(6000));
						  
						   
						   userDetailsTableRowOne.addNewTableCell();
						   userDetailsTableRowOne.getCell(1).setText("Company Name: "+"Booz Allen Hamilton");	
						   userDetailsTableRowOne.getCell(1).setColor(clor);
						  
						   CTTblWidth userDetailsWidth2 =  userDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
						   userDetailsWidth2.setType(STTblWidth.DXA);
						   userDetailsWidth2.setW(BigInteger.valueOf(5000));
						  
						    
						   XWPFTableRow userDetailsTableRowTwo = userDetailsTable.createRow();
						
						   userDetailsTableRowTwo.getCell(0).setText("CSR Number: "+s.requestNumber);
						   userDetailsTableRowTwo.getCell(0).setColor(clor);
						  
						   CTTblWidth userDetailsWidth3 =  userDetailsTableRowTwo.getCell(0).getCTTc().addNewTcPr().addNewTcW();
						   userDetailsWidth3.setType(STTblWidth.DXA);
						   userDetailsWidth3.setW(BigInteger.valueOf(5000));
						  
						   userDetailsTableRowTwo.addNewTableCell();
						   userDetailsTableRowTwo.getCell(1).setText("Clearance Level: "+"TS/SCI with Favorable Polygraph");	
						   userDetailsTableRowTwo.getCell(1).setColor(clor);
						  
						   
						   CTTblWidth userDetailsWidth4 =  userDetailsTableRowTwo.getCell(1).getCTTc().addNewTcPr().addNewTcW();
						   userDetailsWidth4.setType(STTblWidth.DXA);
						   userDetailsWidth4.setW(BigInteger.valueOf(5000));
						  
						   
						   
						   XWPFTableRow  userDetailsTableRowThree = userDetailsTable.createRow();
							
						   
						   userDetailsTableRowThree.getCell(0).setText("Labor Category: "+s.labourCategory);
						   userDetailsTableRowThree.getCell(0).setColor(clor);
						   
						   CTTblWidth userDetailsWidth5 =  userDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewTcW();
						   userDetailsWidth5.setType(STTblWidth.DXA);
						   userDetailsWidth5.setW(BigInteger.valueOf(5000));
						  
						   
						   userDetailsTableRowThree.addNewTableCell();
						   userDetailsTableRowThree.getCell(1).setText("Skill Level: "+ s.performanceLevel);	
						   userDetailsTableRowThree.getCell(1).setColor(clor);
						  
						   CTTblWidth userDetailsWidth6 =  userDetailsTableRowThree.getCell(1).getCTTc().addNewTcPr().addNewTcW();
						   userDetailsWidth6.setType(STTblWidth.DXA);
						   userDetailsWidth6.setW(BigInteger.valueOf(5000));
						  
						   XWPFTableRow userDetailsTableRowFour = userDetailsTable.createRow();
						   userDetailsTableRowFour.getCell(0).setText("Skill Level Justification: "+ " ");
						   userDetailsTableRowFour.getCell(0).setColor(clor);
						   userDetailsTableRowFour.addNewTableCell();
						   userDetailsTableRowFour.getCell(1).setColor(clor);
						   
						   userDetailsTableRowFour.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
						   userDetailsTableRowFour.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
						   //userDetailsTableRowFour.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
						  
						   
						   XWPFParagraph paragraph4 = document.createParagraph();
						   XWPFRun run4=paragraph4.createRun();
						   run4.setText("");
						   
						   
						   
						    String mskills = new String();
						    
						// check if the mandatory skill does not contain null value;
							if (s.manadatorySkills != null) {
								mskills = s.manadatorySkills;
							} else {
								mskills = " ";
							}

							// split the string with numbers
							String[] tokensVal = mskills.split("(?=(\\d)(\\.)(\\s+))");
							// prints the count of tokens  
							ArrayList<String> mandatorySkils = new ArrayList<String>();
							int tokenManCount = 0;
							for (String token : tokensVal) {
								// check for the number
								tokenManCount++;
								if (token.trim().length() != 0) {
									if(tokenManCount > 10){
										mandatorySkils.add("1" + token.substring(0,token.length()-1));
									}else{
										mandatorySkils.add(token);
									}
								}

							}
						   
						   XWPFTable manSkill = document.createTable();
						    
							   
							   XWPFTableRow manSkillRowone = manSkill.getRow(0);
							   manSkillRowone.addNewTableCell();
							   manSkillRowone.getCell(0).setText("Mandatory Skills");
							
							   CTTblWidth widthmanSkill0 =  manSkillRowone.getCell(0).getCTTc().addNewTcPr().addNewTcW();
							   widthmanSkill0.setType(STTblWidth.DXA);
							   widthmanSkill0.setW(BigInteger.valueOf(5000));
							  // CTTableStyle style = ((Object) manSkillRowone.getCell(0).getCTTc()).addNewCnfStyle(Font.BOLD);
							  // style.set
							   manSkillRowone.getCell(0).setColor(clor);
							   // manSkillRowone.getCell(0).getCTTc().g;
							   
							   manSkillRowone.getCell(1).setText("Candidate’s Skills/Experience");	
							   manSkillRowone.getCell(1).setColor(clor);
							  
							   CTTblWidth widthmanSkill2 =  manSkillRowone.getCell(1).getCTTc().addNewTcPr().addNewTcW();
							   widthmanSkill2.setType(STTblWidth.DXA);
							   widthmanSkill2.setW(BigInteger.valueOf(5000));
							  
							   for (String  ms : mandatorySkils) {
								   XWPFTableRow manSkillRowTwo = manSkill.createRow();
								   
								   manSkillRowTwo.getCell(0).setText(ms);
								   CTTblWidth widthdesSkill2 =  manSkillRowTwo.getCell(1).getCTTc().addNewTcPr().addNewTcW();
								   widthdesSkill2.setType(STTblWidth.DXA);
								   widthdesSkill2.setW(BigInteger.valueOf(5000));
								   
								   
								   manSkillRowTwo.addNewTableCell();
								   manSkillRowTwo.getCell(1).setText("");	
									
								}
						   
						   
							   XWPFParagraph paragraph3 = document.createParagraph();
							   XWPFRun run3=paragraph3.createRun();
							   run3.setText("");
							   
			  
							   String desSkill = new String();
							   if (s.desiredSkill != null) {
								   desSkill = s.desiredSkill;
								} else {
									desSkill = " ";
								}

								// split the string with numbers
								String[] tokendesiredsVal = desSkill.split("(?=(\\d)(\\.)(\\s+))");

								// prints the count of tokens
								ArrayList<String> desiredSkills = new ArrayList<String>();
								int tokenCount = 0;
								for (String token : tokendesiredsVal) {
									tokenCount++;
									// add the string with number
									if (token.trim().length() != 0) {
										
										if(tokenCount> 10 ){
											desiredSkills.add("1"+token.substring(0,token.length()-1));
										}else{
											desiredSkills.add(token);
										}
									}

								}
			   
			   
			   
			   XWPFTable desiredSkill = document.createTable();
			   desiredSkill.setWidth(100);
			   
			   
			   XWPFTableRow tableRowTwo = desiredSkill.getRow(0);
			  
			   tableRowTwo.getCell(0).setText("Desired Skills");
			   tableRowTwo.getCell(0).setColor(clor);

			   CTTblWidth widthdesSkill0 =  tableRowTwo.getCell(0).getCTTc().addNewTcPr().addNewTcW();
			   widthdesSkill0.setType(STTblWidth.DXA);
			   widthdesSkill0.setW(BigInteger.valueOf(5000));
			   
			   tableRowTwo.addNewTableCell();
			   tableRowTwo.getCell(1).setText("Candidate’s Skills/Experience");	
			   tableRowTwo.getCell(1).setColor(clor);
			  
			   CTTblWidth widthdesSkill1 =  tableRowTwo.getCell(1).getCTTc().addNewTcPr().addNewTcW();
			   widthdesSkill1.setType(STTblWidth.DXA);
			   widthdesSkill1.setW(BigInteger.valueOf(5000));
			   
			   
			  
			   for (String  ds : desiredSkills) {
				   XWPFTableRow tableRowThree = desiredSkill.createRow();
				   tableRowThree.getCell(0).setText(ds);
				  
				   CTTblWidth widthdesSkill2 =  tableRowThree.getCell(1).getCTTc().addNewTcPr().addNewTcW();
				   widthdesSkill2.setType(STTblWidth.DXA);
				   widthdesSkill2.setW(BigInteger.valueOf(5000));
				   
				   tableRowThree.addNewTableCell();
				   tableRowThree.getCell(1).setText(" ");	
					
				}
			  
			  
				   XWPFParagraph paragraph2 = document.createParagraph();
				   XWPFRun run2=paragraph2.createRun();
				   run2.setText("");
				   run2.setBold(true);
				   run2.setColor(clor);
				   XWPFTable  certificationDetailsTable = document.createTable();;
				   //certificationDetailsTable.setWidth(100);
				   XWPFTableRow certificationDetailsTableRowTHREE = certificationDetailsTable.getRow(0);
					  
				   certificationDetailsTableRowTHREE.getCell(0).setText("Certifications");
				   certificationDetailsTableRowTHREE.getCell(0).setColor(clor);
				  
				   certificationDetailsTableRowTHREE.addNewTableCell();
				   certificationDetailsTableRowTHREE.getCell(1).setColor(clor);
				   
				   certificationDetailsTableRowTHREE.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
				   certificationDetailsTableRowTHREE.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				  // certificationDetailsTableRowTHREE.getCell(1).getCTTc().getTcPr().setGridSpan(Alignment.CENTER);
				   
				   //certificationDetailsTableRowTHREE.getCell(1).getCTTc().addNewColor().setVal("FF0000")
				   XWPFTableRow certificationDetailsTableRowOne = certificationDetailsTable.createRow();
				    
				   //  employmentDetailsTableRowOne.addNewTableCell();
				   certificationDetailsTableRowOne.getCell(0).setText("List of Certifications");
				   certificationDetailsTableRowOne.getCell(0).setColor(clor);
				  
				   CTTblWidth widthcert =  certificationDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
				   widthcert.setType(STTblWidth.DXA);
				   widthcert.setW(BigInteger.valueOf(5000));
				  
				   
				   
				   certificationDetailsTableRowOne.addNewTableCell();
				   certificationDetailsTableRowOne.getCell(1).setText("Certification Date:");	
				   certificationDetailsTableRowOne.getCell(1).setColor(clor);
				   
				   CTTblWidth width =  certificationDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
				   width.setType(STTblWidth.DXA);
				   width.setW(BigInteger.valueOf(5000));
				  
				   
				   
					
						
						XWPFTableRow certificationDetailsTableRowTwo = certificationDetailsTable.createRow();
						certificationDetailsTableRowTwo.getCell(0).setText("");
						//certificationDetailsTableRowTwo.getCell(0).setColor(clor);
						
						certificationDetailsTableRowTwo.addNewTableCell();
						certificationDetailsTableRowTwo.getCell(1).setText("");	
						//certificationDetailsTableRowTwo.getCell(1).setColor(clor);
						   
					
				   
				   
				   
					XWPFParagraph paragraph1 = document.createParagraph();
					   XWPFRun run1=paragraph1.createRun();
					   run1.setText("");
					   run1.setBold(true);
					   run1.setColor(clor);
					 
					   
				   XWPFTable  educationDetailsTable = document.createTable();;
				   //educationDetailsTable.setWidth(100);
				   
				   XWPFTableRow educationDetailsTableRowthree = educationDetailsTable.getRow(0);
					  
				   educationDetailsTableRowthree.getCell(0).setText("Education");
				   educationDetailsTableRowthree.getCell(0).setColor(clor);
				  
				   educationDetailsTableRowthree.addNewTableCell();
				   educationDetailsTableRowthree.getCell(1).setColor(clor);
				 
				   educationDetailsTableRowthree.addNewTableCell();
				   educationDetailsTableRowthree.getCell(2).setColor(clor);
				 
				   
				   educationDetailsTableRowthree.addNewTableCell();
				   educationDetailsTableRowthree.getCell(2).setColor(clor);
				 
				   educationDetailsTableRowthree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
				   educationDetailsTableRowthree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				   educationDetailsTableRowthree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				   educationDetailsTableRowthree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				   
				   
				   XWPFTableRow educationDetailsTableRowOne = educationDetailsTable.createRow();
				 //  employmentDetailsTableRowOne.addNewTableCell();
				   educationDetailsTableRowOne.getCell(0).setText("Degree");
				   educationDetailsTableRowOne.getCell(0).setColor(clor);
				   
				   CTTblWidth widthedu1 =  educationDetailsTableRowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW();
				   widthedu1.setType(STTblWidth.DXA);
				   widthedu1.setW(BigInteger.valueOf(3500));
				   
				   educationDetailsTableRowOne.addNewTableCell();
				   educationDetailsTableRowOne.getCell(1).setText("School Name ");	
				   educationDetailsTableRowOne.getCell(1).setColor(clor);
					
				   
				   CTTblWidth widthedu2 =  educationDetailsTableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW();
				   widthedu2.setType(STTblWidth.DXA);
				   widthedu2.setW(BigInteger.valueOf(3500));
				   educationDetailsTableRowOne.addNewTableCell();
				   educationDetailsTableRowOne.getCell(2).setText("Degree/Major");	
				   educationDetailsTableRowOne.getCell(2).setColor(clor);
					  
				   CTTblWidth widthedu3 =  educationDetailsTableRowOne.getCell(2).getCTTc().addNewTcPr().addNewTcW();
				   widthedu3.setType(STTblWidth.DXA);
				   widthedu3.setW(BigInteger.valueOf(3500));
				   
				   educationDetailsTableRowOne.addNewTableCell();
				   educationDetailsTableRowOne.getCell(3).setText("Completion Date: ");	
				   educationDetailsTableRowOne.getCell(3).setColor(clor);
				   CTTblWidth widthedu4 =  educationDetailsTableRowOne.getCell(3).getCTTc().addNewTcPr().addNewTcW();
				   widthedu4.setType(STTblWidth.DXA);
				   widthedu4.setW(BigInteger.valueOf(3500));
				   
				 
				   
						XWPFTableRow educationDetailsTableRowTWO = educationDetailsTable.createRow();
						 //  employmentDetailsTableRowOne.addNewTableCell();
						educationDetailsTableRowTWO.getCell(0).setText(" ");
						//educationDetailsTableRowTWO.getCell(0).setColor(clor);
						  
						   
						educationDetailsTableRowTWO.addNewTableCell();
						educationDetailsTableRowTWO.getCell(1).setText(" ");	
						//educationDetailsTableRowTWO.getCell(1).setColor(clor);
							
						educationDetailsTableRowTWO.addNewTableCell();
						educationDetailsTableRowTWO.getCell(2).setText(" ");	
						//educationDetailsTableRowTWO.getCell(2).setColor(clor);
							  
						educationDetailsTableRowTWO.addNewTableCell();
						educationDetailsTableRowTWO.getCell(3).setText(" ");	
						//educationDetailsTableRowTWO.getCell(3).setColor(clor);
						   
					
			   
					XWPFParagraph paragraph = document.createParagraph();
					   XWPFRun run=paragraph.createRun();
					   run.setText("");
					   run.setBold(true);
					   run.setColor(clor);
				   XWPFTable  employmentDetailsTable = document.createTable();;
				  
				   XWPFTableRow EmploymentDetailsTableRowthree = employmentDetailsTable.getRow(0);
					  
				   EmploymentDetailsTableRowthree.getCell(0).setText("WORK EXPERIENCE");
				   EmploymentDetailsTableRowthree.getCell(0).setColor(clor);
				  
				   EmploymentDetailsTableRowthree.addNewTableCell();
				   EmploymentDetailsTableRowthree.getCell(1).setColor(clor);
				 
				   EmploymentDetailsTableRowthree.addNewTableCell();
				   EmploymentDetailsTableRowthree.getCell(2).setColor(clor);
				 
				   
				   EmploymentDetailsTableRowthree.addNewTableCell();
				   EmploymentDetailsTableRowthree.getCell(2).setColor(clor);
				 
				   EmploymentDetailsTableRowthree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
				   EmploymentDetailsTableRowthree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				   EmploymentDetailsTableRowthree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				   EmploymentDetailsTableRowthree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
				 
				   
				  				   
				   int[] cols = {10000,0,0,0}; 
					
				   
				  
						XWPFTableRow employmentDetailsTableRowTwo = employmentDetailsTable.createRow();
						
						employmentDetailsTableRowTwo.getCell(0).setText("Employers Name: " + "Employers Name" );
						
						//employmentDetailsTableRowTwo.getCell(0).setColor(clor);
						
						employmentDetailsTableRowTwo.addNewTableCell();
						employmentDetailsTableRowTwo.getCell(1).setText("Start Date: "+ "MM" +"/"+  "YYYY");	
						//employmentDetailsTableRowTwo.getCell(1).setColor(clor);
						
						employmentDetailsTableRowTwo.addNewTableCell();
						employmentDetailsTableRowTwo.getCell(2).setText("End Date: "+ "MM" +"/"+  "YYYY");	
						//employmentDetailsTableRowTwo.getCell(2).setColor(clor);
						  
						employmentDetailsTableRowTwo.addNewTableCell();
						employmentDetailsTableRowTwo.getCell(3).setText("Position Held: "+ "Position Name ");	
						//employmentDetailsTableRowTwo.getCell(3).setColor(clor);
						XWPFTableRow employmentDetailsTableRowThree = employmentDetailsTable.createRow();
						employmentDetailsTableRowThree.getCell(0).setText("Exp Desc");
						//employmentDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(100));
						employmentDetailsTableRowThree.addNewTableCell();
						employmentDetailsTableRowThree.addNewTableCell();
						employmentDetailsTableRowThree.addNewTableCell();
						employmentDetailsTableRowThree.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
						employmentDetailsTableRowThree.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
						employmentDetailsTableRowThree.getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
						employmentDetailsTableRowThree.getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
							   
						
						
						
					
				   
				   
				   
			    try {
					document.write(out);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date = sdf.format(new Date());
				response().setContentType("application/docx");
				response().setHeader("Content-Disposition",
						"inline; filename=" + fileName);
				System.out.println("document created successfully..");
				File file = new File(fileName);
				return ok(file);
		 
	 }
	
}
