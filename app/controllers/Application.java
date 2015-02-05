package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import models.EducationDetails;
import models.EmploymentDetails;
import models.StoreExcelFile;
import models.UserClearance;
import models.UserDetails;
import models.UserPosition;
import models.UserSkill;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import views.html.index;
import views.html.register;
import views.html.signin;

import com.avaje.ebean.Ebean;
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

	public static Result createNewUser() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		System.out.println("dynamicForm" + dynamicForm);
		String email = dynamicForm.get("email");
		String pass = dynamicForm.get("password");
		String firstname = dynamicForm.get("firstname");
		String middlename = dynamicForm.get("middlename");
		String lastname = dynamicForm.get("lastname");
		String gender = dynamicForm.get("gender");

		UserDetails existingUser = UserDetails.getUserByEmail(email);
		if (existingUser == null) {
			UserDetails u = new UserDetails();
			u.email = email;
			u.password = pass;
			//u.fullname = firstname;
			u.firstname = firstname;
			u.middlename = middlename;
			u.lastname = lastname;
			u.gender = gender;
			Ebean.save(u);

		}else{
			flash("error", "Email ID Already Exist");
			return redirect("/signup");
		}

		return redirect("/login");

	}

	// called when user login to get there details
	public static Result getUserName() {
		String uname = session().get("email");
		Admin ad = Admin.checkAdmin(uname);
		if (ad == null) {
			UserDetails ud = UserDetails.getUserByEmail(uname);

			if (ud != null) {
				return ok(ud.firstname,ud.lastname);
			}
		}

		return ok(ad.username);
	}

	// called to check the usertype and also when page is refreshed
	public static Result checkForadmin() {
		String email = session().get("email");
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
			session().put("email", as.username);
			session().put("role", as.role);
			return redirect("/dashboard#/");

		} else {

			UserDetails ud = UserDetails.isUser(uname, pass);
			if (ud != null) {
				session().clear();
				session().put("email", ud.email);
				return redirect("/dashboard#/viewJobs");
			} else {
				flash().put("error", "Login Failed");
				return ok(signin.render());
			}

		}

	}

	// used to send the password to sign in user
	public static Result forgetPassword() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		String uname = dynamicForm.get("email");
		UserDetails ud = UserDetails.getPassword(uname);

		final String username = "support@arihantbooking.com";
		final String password = "Adschela@123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message feedback = new MimeMessage(session);
			feedback.setFrom(new InternetAddress(username));
			feedback.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(ud.email));
			feedback.setSubject("Your Password Details ");
			// message.setText();
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setText("\n Mail : " + ud.email + "\n Password: "
					+ ud.password);
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
		flash().put("email_success", "Password is send on your EmailId.");
		return ok(signin.render());

	}

	// upload the excel
	public static Result uploadandStoreExcel() {
		play.mvc.Http.MultipartFormData body = request().body()
				.asMultipartFormData();
		MultipartFormData.FilePart excelpart = body.getFile("file");
		File excelfile = excelpart.getFile();
		ArrayList<String> al = new ArrayList<>();
		int newRows = 0;
		int updatedRows = 0;
		try {
			FileInputStream file = new FileInputStream(excelfile);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			StoreExcelFile storeExcelFile = null;
			Row row;
			String reqNo = null;
			
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
						System.out.println("nukmberString");
						storeExcelFile = StoreExcelFile.getregNumber(c
								.getStringCellValue());

						if (storeExcelFile != null) {
							storeExcelFile.requestNumber = c
									.getStringCellValue();
							reqNo = c
									.getStringCellValue();
							
						} else {
							sd.requestNumber = c.getStringCellValue();
							System.out.println("sd.requestNumber"
									+ c.getStringCellValue());
						}

						break;
					}

					c = row.getCell(1);

					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.requestType = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:
						// sd.requestType=c.getStringCellValue();
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
						// sd.labourCategory = c.getStringCellValue();
						// used to add the Positon in UserPosition model when
						// not present
						UserPosition up = UserPosition.getPositionByPosName(c
								.getStringCellValue());
						if (up == null) {
							UserPosition u = new UserPosition();
							u.position = c.getStringCellValue();
							u.save();
						}

						if (storeExcelFile != null) {
							storeExcelFile.labourCategory = c
									.getStringCellValue();
						} else {
							sd.labourCategory = c.getStringCellValue();
						}

						break;
					}

					c = row.getCell(3);
					switch (c.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						sd.performanceLevel = c.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_STRING:
						// sd.performanceLevel = c.getStringCellValue();
						if (storeExcelFile != null) {
							storeExcelFile.performanceLevel = c
									.getStringCellValue();
						} else {
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
							// sd.positionType=c.getStringCellValue();
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
							// sd.workLocation = c.getStringCellValue();
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
						c = row.getCell(15);
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

					c = row.getCell(16);
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

					c = row.getCell(17);
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

					c = row.getCell(18);
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

					c = row.getCell(19);
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

					c = row.getCell(20);
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

					c = row.getCell(21);
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

					c = row.getCell(22);
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

					c = row.getCell(23);
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

					c = row.getCell(24);
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

					c = row.getCell(25);
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

					c = row.getCell(26);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.duetoPmo = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.duetoPmo = c
										.getStringCellValue();
							} else {
								sd.duetoPmo = c.getStringCellValue();
							}

							break;
						}
					}

					c = row.getCell(27);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.updateDate = c.getNumericCellValue() + "";
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

					c = row.getCell(28);
					if (c != null) {
						switch (c.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							sd.duetoGovt = c.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							if (storeExcelFile != null) {
								storeExcelFile.duetoGovt = c
										.getStringCellValue();
							} else {
								sd.duetoGovt = c.getStringCellValue();
							}

							break;
						}
					}
					if (storeExcelFile != null) {
                         
						storeExcelFile.update(storeExcelFile);
						if(reqNo != null){
							updatedRows = updatedRows + 1;
							System.out.println("updatedRows"+updatedRows+"reqNo"+reqNo);
						}
						
						// System.out.println("in update");
					} else {
						if (sd.requestNumber != null) {
							System.out.println("storeExcelFile"+storeExcelFile);
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
			String  updatedRowsCount = Integer.toString(updatedRows);
			System.out.println("updatedRowsCount"+updatedRowsCount);
			al.add(newrowscount);
			al.add(updatedRowsCount);
			return ok(Json.stringify(Json.toJson(al)));
		}
		
		String newrowscount = Integer.toString(newRows);
		String  updatedRowsCount = Integer.toString(updatedRows);
		System.out.println("updatedRowsCount"+updatedRowsCount);
		al.add(newrowscount);
		al.add(updatedRowsCount);
		
		return ok(Json.stringify(Json.toJson(al)));
	}

	public static class AdminVM {
		public int id;
		public String uname;
		public String password;
		public String date;
		public String valid_ips;
		public String status;
		public String site;
		public boolean isParent;
		public int cg;
		public List<AdminExtra> aextra;
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
		// saveActivityLog("LogOut");
		session().clear();
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
		public String duetoPmo;
		public String updateDate;
		public String duetoGovt;
		public String jobStatus;
		

	}

	
	
	// called when user first  view job page loaded
	public static Result getAllJobs(int currentPage, String jobType,
			Boolean location, Boolean usermatch, String position) {

		List<StoreExcelFile> jobs = new ArrayList<>();
		List<StoreExcelFile> userJobs = null;

		// jobtype are selected for search
		if (!("All".equalsIgnoreCase(jobType.trim()))) {
			System.out.println("all");
			userJobs = StoreExcelFile.getAllJobsByJobType(currentPage, 10,
					jobType);

		}
		
		

		// location are selected for search
		/*
		 * if (!(false == location)) { System.out.println("in  loca"); jobs =
		 * StoreExcelFile.getAllJobsByLocationDsc(currentPage, 10); }
		 */

		// location are selected for search
		/*
		 * if (!(true == location)) { System.out.println("in  loca"); jobs =
		 * StoreExcelFile.getAllJobsByLocationAsc(currentPage, 10); }
		 */
		// both are selected for search(DSC)
		if (!(true == location)
				&& !("jobType".equalsIgnoreCase(jobType.trim()))) {
			String emailId = session().get("email");
			userJobs = StoreExcelFile.getAllJobsByLocationAndJobTypeAsc(
					currentPage, 10, jobType);

			for (StoreExcelFile str : userJobs) {
				AppliedJobs as = AppliedJobs.getUserAppliedJob(emailId,
						str.requestNumber);
				if (as == null) {
					jobs.add(str);
				}
			}

		}

		// both are selected for search(DSC)
		if (!(false == location)
				&& !("jobType".equalsIgnoreCase(jobType.trim()))) {
			String emailId = session().get("email");
			userJobs = StoreExcelFile.getAllJobsByLocationAndJobTypeDsc(
					currentPage, 10, jobType);

			for (StoreExcelFile str : userJobs) {
				AppliedJobs as = AppliedJobs.getUserAppliedJob(emailId,
						str.requestNumber);
				if (as == null) {
					jobs.add(str);
				}
			}

		}

		
		//for admin to view all jobs
		/*if(jobType.equalsIgnoreCase("All") && location == false && usermatch == false && "notSelected".equals(position)){
			jobs = StoreExcelFile.getAllJobs(currentPage, 10);
		}*/
		
		// all jobs //selected Default(user profile job mtached job)
		if (("All".equalsIgnoreCase(jobType.trim())) && (false == location)) {
			ArrayList<String> al = new ArrayList<>();

			String email = session().get("email");
			UserDetails u = UserDetails.getUserByEmail(email);
			List<UserPosition> up = u.userPosition;
			for (UserPosition upd : up) {
				String pos = upd.position;
				al.add(pos);

				System.out.println("pos" + pos);
			}

			userJobs = StoreExcelFile.getALlUserMatchedJobDsc(currentPage, 10,
					al);

			for (StoreExcelFile str : userJobs) {
				AppliedJobs as = AppliedJobs.getUserAppliedJob(email,
						str.requestNumber);
				if (as == null) {
					jobs.add(str);
				}
			}

		}

		if (("All".equalsIgnoreCase(jobType.trim())) && (true == location)) {
			/* jobs = StoreExcelFile.getAllJobs(currentPage, 10); */
			ArrayList<String> al = new ArrayList<>();

			String email = session().get("email");
			UserDetails u = UserDetails.getUserByEmail(email);
			List<UserPosition> up = u.userPosition;
			for (UserPosition upd : up) {
				String pos = upd.position;
				al.add(pos);

				System.out.println("pos" + pos);
			}

			userJobs = StoreExcelFile.getALlUserMatchedJobAsc(currentPage, 10,
					al);

			for (StoreExcelFile str : userJobs) {
				AppliedJobs as = AppliedJobs.getUserAppliedJob(email,
						str.requestNumber);
				if (as == null) {
					jobs.add(str);
				}
			}

		}

		// called when the usermatches job type selected
		/*
		 * if (usermatch == true) { ArrayList<String> al = new ArrayList<>();
		 * 
		 * String email = session().get("email"); UserDetails u =
		 * UserDetails.getUserByEmail(email); List<UserPosition> up =
		 * u.userPosition; for (UserPosition upd : up) { String pos =
		 * upd.position; al.add(pos);
		 * 
		 * System.out.println("pos" + pos); }
		 * 
		 * jobs = StoreExcelFile.getALlUserMatchedJob(currentPage, 10, al); }
		 */
		if (!("notSelected".equalsIgnoreCase(position.trim()))) {
			jobs = StoreExcelFile.getALlUserJobByPosition(currentPage, 10,
					position);

		}

		List<JobVM> jobVMs = new ArrayList<JobVM>();

		if (currentPage > 10 && 10 != 0) {
			currentPage--;
		}

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
			jobVM.pagerdutyComments  =s.pagerdutyComments;
			jobVM.workonHoliday = s.workonHoliday;
		jobVM.workonWeekends = s.workonWeekends;
		jobVM.shiftWork  =s.shiftWork;
			jobVM.warzone = s.warzone;
			jobVM.coop = s.coop;
			jobVM.duetoPmo = s.duetoPmo;
			jobVM.updateDate = s.updateDate;
			jobVM.duetoGovt = s.duetoGovt;
			
			jobVM.jobStatus = s.jobStatus;
			// check if the desired skill does not contain null value;
			if (s.desiredSkill != null) {
				dSkills = s.desiredSkill;
			} else {
				dSkills = " ";
			}

			// split the string with numbers
			String[] tokendesiredsVal = dSkills
					.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

			// prints the count of tokens
			String numd = null;
			ArrayList<String> desSkill = new ArrayList<String>();
			for (String token : tokendesiredsVal) {
				// check for the number
				boolean b = token.matches("[-+]?\\d*\\.?\\d+");
				if (b == true) {
					numd = token;
				} else {
					// if number skip do not add to the list
					// add the string with number
					if (numd != null) {
						desSkill.add(numd + "" + token);
					}
				}
				System.out.print(token);
			}

			// check if the mandatory skill does not contain null value;
			if (s.manadatorySkills != null) {
				mskills = s.manadatorySkills;
			} else {
				mskills = " ";
			}

			// split the string with numbers
			String[] tokensVal = mskills
					.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			// prints the count of tokens
			String num = null;
			ArrayList<String> manSkill = new ArrayList<String>();
			for (String token : tokensVal) {
				// check for the number
				boolean b = token.matches("[-+]?\\d*\\.?\\d+");
				if (b == true) {
					num = token;
				} else {
					// if number skip dont add to the list
					// add the string with number
					if (num != null) {
						manSkill.add(num + "" + token);
					}
				}
			}

			jobVM.manadatorySkills = manSkill;
			jobVM.desiredSkill = desSkill;
			System.out.println("jobVM.desiredSkill" + jobVM.desiredSkill);

			jobVMs.add(jobVM);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPages", 10);
		map.put("currentPage", currentPage);
		map.put("jobs", jobVMs);

		return ok(Json.toJson(map));
	}

	public static  Result getAllJobsForAdmin(int currentPage, String jobType,
		Boolean location, Boolean usermatch, String position){

	List<StoreExcelFile> jobs = new ArrayList<>();
	//List<StoreExcelFile> userJobs = null;

	System.out.println("jobType:"+jobType);
	/*// jobtype are selected for search
	if (!("All".equalsIgnoreCase(jobType.trim()))) {
		System.out.println("all");
		userJobs = StoreExcelFile.getAllJobsByJobType(currentPage, 10,
				jobType);

	}
	*/
	

	// location are selected for search
	/*
	 * if (!(false == location)) { System.out.println("in  loca"); jobs =
	 * StoreExcelFile.getAllJobsByLocationDsc(currentPage, 10); }
	 */

	// location are selected for search
	/*
	 * if (!(true == location)) { System.out.println("in  loca"); jobs =
	 * StoreExcelFile.getAllJobsByLocationAsc(currentPage, 10); }
	 */
	// both are selected for search(DSC)
	if (!(true == location)
			&& !("jobType".equalsIgnoreCase(jobType.trim()))) {
		//String emailId = session().get("email");
		jobs = StoreExcelFile.getAllJobsByLocationAndJobTypeAsc(
				currentPage, 10, jobType);

		/*for (StoreExcelFile str : userJobs) {
			AppliedJobs as = AppliedJobs.getUserAppliedJob(emailId,
					str.requestNumber);
			if (as == null) {
				jobs.add(str);
			}
		}*/

	}

	// both are selected for search(DSC)
	if (!(false == location)
			&& !("jobType".equalsIgnoreCase(jobType.trim()))) {
		//String emailId = session().get("email");
		jobs = StoreExcelFile.getAllJobsByLocationAndJobTypeDsc(
				currentPage, 10, jobType);

		/*for (StoreExcelFile str : userJobs) {
			AppliedJobs as = AppliedJobs.getUserAppliedJob(emailId,
					str.requestNumber);
			if (as == null) {
				jobs.add(str);
			}
		}*/

	}

	
	//for admin to view all jobs
	/*if(jobType.equalsIgnoreCase("All") && location == false && usermatch == false && "notSelected".equals(position)){
		jobs = StoreExcelFile.getAllJobs(currentPage, 10);
	}*/
	
	// all jobs //selected Default(user profile job mtached job)
	if (("All".equalsIgnoreCase(jobType.trim())) && (false == location)) {
		/*ArrayList<String> al = new ArrayList<>();
		String email = session().get("email");
		UserDetails u = UserDetails.getUserByEmail(email);
		List<UserPosition> up = u.userPosition;
		for (UserPosition upd : up) {
			String pos = upd.position;
			al.add(pos);

			System.out.println("pos" + pos);
		}
		 */
		
		jobs = StoreExcelFile.getAllJobsForAdminDsc(currentPage, 10);

		/*for (StoreExcelFile str : userJobs) {
			AppliedJobs as = AppliedJobs.getUserAppliedJob(email,
					str.requestNumber);
			if (as == null) {
				jobs.add(str);
			}
		}*/

	}

	if (("All".equalsIgnoreCase(jobType.trim())) && (true == location)) {
		/* jobs = StoreExcelFile.getAllJobs(currentPage, 10); */
	/*	ArrayList<String> al = new ArrayList<>();

		String email = session().get("email");
		UserDetails u = UserDetails.getUserByEmail(email);
		List<UserPosition> up = u.userPosition;
		for (UserPosition upd : up) {
			String pos = upd.position;
			al.add(pos);

			System.out.println("pos" + pos);
		}*/

		jobs = StoreExcelFile.getAllJobsForAdminAsc(currentPage, 10);

	/*	for (StoreExcelFile str : userJobs) {
			AppliedJobs as = AppliedJobs.getUserAppliedJob(email,
					str.requestNumber);
			if (as == null) {
				jobs.add(str);
			}
		}
*/
	}

	// called when the usermatches job type selected
	/*
	 * if (usermatch == true) { ArrayList<String> al = new ArrayList<>();
	 * 
	 * String email = session().get("email"); UserDetails u =
	 * UserDetails.getUserByEmail(email); List<UserPosition> up =
	 * u.userPosition; for (UserPosition upd : up) { String pos =
	 * upd.position; al.add(pos);
	 * 
	 * System.out.println("pos" + pos); }
	 * 
	 * jobs = StoreExcelFile.getALlUserMatchedJob(currentPage, 10, al); }
	 */
	/*if (!("notSelected".equalsIgnoreCase(position.trim()))) {
		jobs = StoreExcelFile.getALlUserJobByPosition(currentPage, 10,
				position);

	}*/

	List<JobVM> jobVMs = new ArrayList<JobVM>();

	if (currentPage > 10 && 10 != 0) {
		currentPage--;
	}

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
		jobVM.pagerdutyComments  =s.pagerdutyComments;
		jobVM.workonHoliday = s.workonHoliday;
		jobVM.workonWeekends = s.workonWeekends;
		jobVM.shiftWork  =s.shiftWork;
		jobVM.warzone = s.warzone;
		jobVM.coop = s.coop;
		jobVM.duetoPmo = s.duetoPmo;
		jobVM.updateDate = s.updateDate;
		jobVM.duetoGovt = s.duetoGovt;
		jobVM.jobStatus = s.jobStatus;
		
		// check if the desired skill does not contain null value;
		if (s.desiredSkill != null) {
			dSkills = s.desiredSkill;
		} else {
			dSkills = " ";
		}

		// split the string with numbers
		String[] tokendesiredsVal = dSkills
				.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

		// prints the count of tokens
		String numd = null;
		ArrayList<String> desSkill = new ArrayList<String>();
		for (String token : tokendesiredsVal) {
			// check for the number
			boolean b = token.matches("[-+]?\\d*\\.?\\d+");
			if (b == true) {
				numd = token;
			} else {
				// if number skip do not add to the list
				// add the string with number
				if (numd != null) {
					desSkill.add(numd + "" + token);
				}
			}
			System.out.print(token);
		}

		// check if the mandatory skill does not contain null value;
		if (s.manadatorySkills != null) {
			mskills = s.manadatorySkills;
		} else {
			mskills = " ";
		}

		// split the string with numbers
		String[] tokensVal = mskills
				.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		// prints the count of tokens
		String num = null;
		ArrayList<String> manSkill = new ArrayList<String>();
		for (String token : tokensVal) {
			// check for the number
			boolean b = token.matches("[-+]?\\d*\\.?\\d+");
			if (b == true) {
				num = token;
			} else {
				// if number skip dont add to the list
				// add the string with number
				if (num != null) {
					manSkill.add(num + "" + token);
				}
			}
		}

		jobVM.manadatorySkills = manSkill;
		jobVM.desiredSkill = desSkill;
		System.out.println("jobVM.desiredSkill" + jobVM.desiredSkill);

		jobVMs.add(jobVM);
	}

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("totalPages", 10);
	map.put("currentPage", currentPage);
	map.put("jobs", jobVMs);

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
		public String fromDate;
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
		public String dob;
		public String gender;
		// public String userposition;
		public String email;
		public String password;
		public String midname;
		public String lastname;
		
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
		// List<UserSkill> skills = ud.userSkill;
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
	public static Result updateUserProfile() {
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);

		List<AddEducationVM> addEducation;
		List<AddEmpHistoryVM> addNewEmphistory;
		List<AddCertificateVM> addCertificate;

		String email = session().get("email");
		UserDetails u = UserDetails.getUserByEmail(email);

		JsonNode eduJson = json.get("addEducation");
		JsonNode empJson = json.get("addNewEmphistory");
		JsonNode certJson = json.get("addCertificate");
		JsonNode userClearance = json.path("clearance");
		JsonNode userPosition = json.path("position");
		JsonNode userSkills = json.path("skills");

		u.deleteManyToManyAssociations("userSkill");

		ArrayNode skills = (ArrayNode) userSkills;
		for (int k = 0; k < skills.size(); k++) {
			String s = skills.get(k).asText();
			System.out.println(s);
			UserSkill us = UserSkill.getSkillByName(s);
			u.userSkill.add(us);
		}

		u.saveManyToManyAssociations("userSkill");

		u.deleteManyToManyAssociations("userPosition");
		ArrayNode positions = (ArrayNode) userPosition;
		for (int j = 0; j < positions.size(); j++) {
			String position = positions.get(j).asText();
			UserPosition up = UserPosition.getPositionByPosName(position);
			u.userPosition.add(up);
		}

		u.saveManyToManyAssociations("userPosition");

		u.deleteManyToManyAssociations("userClearance");
		ArrayNode clearance = (ArrayNode) userClearance;
		for (int i = 0; i < clearance.size(); i++) {
			String clea = clearance.get(i).asText();
			UserClearance uc = UserClearance.getClearanceByName(clea);
			u.userClearance.add(uc);
		}

		u.saveManyToManyAssociations("userClearance");

		ObjectMapper mapper = new ObjectMapper();
		addNewEmphistory = mapper.convertValue(empJson, mapper.getTypeFactory()
				.constructCollectionType(List.class, AddEmpHistoryVM.class));
		for (int i = 0; i < addNewEmphistory.size(); i++) {
			EmploymentDetails ed = EmploymentDetails
					.getEmploymentDetailsByName(addNewEmphistory.get(i).companyName);
			if (ed != null) {
				ed.delete();

			}

			EmploymentDetails eds = new EmploymentDetails();
			eds.companyName = addNewEmphistory.get(i).companyName;
			eds.position = addNewEmphistory.get(i).position;
			eds.startdate = addNewEmphistory.get(i).startdate;
			if (addNewEmphistory.get(i).enddate == "") {
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
		for (int i = 0; i < addEducation.size(); i++) {
			EducationDetails eds = EducationDetails
					.getEducationDetailsByName(addEducation.get(i).degree);
			if (eds != null) {
				eds.delete();
			}

			EducationDetails ed = new EducationDetails();
			ed.instituteName = (addEducation.get(i).instituteName);
			ed.degree = (addEducation.get(i).degree);
			ed.fromDate = (addEducation.get(i).fromDate);
			ed.toDate = (addEducation.get(i).toDate);
			ed.user_details = UserDetails.getUserByEmail(email);
			ed.save();

			u.educationDetails.add(ed);

		}

		ObjectMapper certMapper = new ObjectMapper();
		addCertificate = mapper.convertValue(
				certJson,
				certMapper.getTypeFactory().constructCollectionType(List.class,
						AddCertificateVM.class));

		for (int i = 0; i < addCertificate.size(); i++) {
			CertificationDetails c = CertificationDetails
					.getCetificateByName(addCertificate.get(i).certName);
			if (c != null) {
				c.delete();
			}

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
			System.out.println("ui.firstname --"+ui.firstname);
			u.email = ui.email;
			u.firstname = ui.firstname;
			u.middlename = ui.midname;
			u.lastname = ui.lastname;
			u.dob = ui.dob;
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
		List<UserPosition> up = UserPosition.getAllPosition();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position", up);
		return ok((Json.stringify(Json.toJson(map))));

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
	public static class SaveAppliedJobsVM {
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
		public long id;

	}

	@JsonIgnoreProperties
	public static Result saveAppliedJob() {
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		AppliedJobs apj = new AppliedJobs();
		ObjectMapper objectMapper = new ObjectMapper();
		SaveAppliedJobsVM saveAppliedJobsVM;
		try {

			saveAppliedJobsVM = objectMapper.readValue(json.get("jobData")
					.traverse(), SaveAppliedJobsVM.class);
			
			apj.desiredSkil = json.get("desiredSkills").toString();
			apj.manadatorySkill = json.get("manadatorySkills").toString();
			apj.jobno = saveAppliedJobsVM.requestNumber;
			String username = session().get("email");
			apj.username = username;
			apj.jobStatus = "Applied";
			apj.positionname = saveAppliedJobsVM.labourCategory;
			apj.location = saveAppliedJobsVM.workLocation;
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
	public static class DesiredSkills {
		public String dskill;
		public String comment;

	}

	@JsonIgnoreProperties
	public static class MandatorySkills {

		public String mskill;
		public String comment;
	}

	public static class AppliedJobVM {
		public String username;
		public int id;
		public String requestNumber;
		public String status;
		public String location;
		public String positionName;
		public List<DesiredSkills> dsSkills;
		public List<MandatorySkills> msSkils;

	}

	public static Result getAllAppliedJobs() {
		List<AppliedJobs> ap = AppliedJobs.getAllJobs();
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
			appliedJobVM.add(apvm);

		}
		return ok((Json.stringify(Json.toJson(appliedJobVM))));
	}

	public static Result generatePDF(String id) {
		final String rootDir = Play.application().configuration()
				.getString("resume.path");
		System.out.println("rootdir" + rootDir);

		int ids = Integer.parseInt(id);
		AppliedJobs ap = AppliedJobs.getUserAppliedJobById(ids);
		//job id to be shown on resume
		String JobId =ap.jobno;
		
		OutputStream file = null;
		Document document = null;
		try {
			file = new FileOutputStream(new File(rootDir));
			document = new Document();
			PdfWriter.getInstance(document, file);
			String email = ap.username;
			UserDetails ud = UserDetails.getUserByEmail(email);
			//String candidiatename = ud.fullname;
			String candidiatename = ud.firstname;
			List<UserSkill> skills = ud.userSkill;
			// used for the table name (heading)
			Font font = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD,
					BaseColor.BLACK);
			// used for the table column data
			Font font1 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL,
					BaseColor.BLACK);
			// used for the column name
			Font font2 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD,
					BaseColor.BLACK);

			// Mandatory skills
			Chunk chunkSkills = new Chunk("Mandatory Skill".toUpperCase());
			chunkSkills.setBackground(new BaseColor(230, 230, 250));
			chunkSkills.setFont(font);

			Chunk chunkExpDetails = new Chunk("experience".toUpperCase());
			chunkExpDetails.setBackground(new BaseColor(230, 230, 250));
			chunkExpDetails.setFont(font);

			Chunk userName = new Chunk(candidiatename);
			userName.setBackground(new BaseColor(230, 230, 250));
			userName.setFont(font);

			Chunk userNameDetails = new Chunk(email);
			userNameDetails.setBackground(new BaseColor(230, 230, 250));
			userNameDetails.setFont(font);

			Chunk chunk = new Chunk("Key Skill Area".toUpperCase());
			chunk.setBackground(new BaseColor(230, 230, 250));
			chunk.setFont(font);

			Chunk eduChunk = new Chunk("EDUCATION");
			eduChunk.setBackground(new BaseColor(230, 230, 250));
			eduChunk.setFont(font);

			Chunk chunkDesired = new Chunk("DESIRED SKILLS");
			chunkDesired.setBackground(new BaseColor(230, 230, 250));
			chunkDesired.setFont(font);

			Chunk chunkClearance = new Chunk("Security Clearance".toUpperCase());
			chunkClearance.setBackground(new BaseColor(230, 230, 250));
			chunkClearance.setFont(font);

			Chunk empChunk = new Chunk("EMPLOYMENT HISTORY");
			empChunk.setBackground(new BaseColor(230, 230, 250));
			empChunk.setFont(font);

			Chunk certChunk = new Chunk("CERTIFICATION HISTORY");
			certChunk.setBackground(new BaseColor(230, 230, 250));
			certChunk.setFont(font);

			// skill table
			PdfPTable table2 = new PdfPTable(4);
			// table2.set
			table2.setWidthPercentage(100);
			float[] width2 = { 2f, 2f, 2f, 2f };
			table2.setWidths(width2);

			PdfPCell cell2 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell2.setColspan(3);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setPadding(10.0f);
			cell2.setBackgroundColor(new BaseColor(140, 221, 8));
			for (UserSkill sk : skills) {
				// EmpHistorytable.addCell(cellemp);
				cell2 = new PdfPCell(new Phrase(sk.skillName));
				cell2.setBackgroundColor(new BaseColor(248, 248, 255));
				// cell2.setFont(font1);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell2);
			}

			// for username and email
			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);
			/*
			 * float[] width3 = { 10f }; table2.setWidths(width3);
			 */

			PdfPCell cell4 = new PdfPCell(new Paragraph(
					"Key Skill Area".toUpperCase()));
			cell4 = new PdfPCell(new Phrase("Candidate’s Full Legal Name: "
					+ "" + candidiatename, font1));
			cell4.setBackgroundColor(new BaseColor(230, 230, 250));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell4);

			// add email of canidiate at bottom of student full name
			cell4 = new PdfPCell(new Phrase("Email: " + email, font1));
			cell4.setBackgroundColor(new BaseColor(230, 230, 250));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell4);
			
			cell4 = new PdfPCell(new Phrase("JOB ID: " + JobId, font1));
			cell4.setBackgroundColor(new BaseColor(230, 230, 250));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell4);

			List<UserClearance> clearance = ud.userClearance;
			PdfPTable table3 = new PdfPTable(1);
			table3.setWidthPercentage(100);
			/*
			 * float[] width3 = { 10f }; table2.setWidths(width3);
			 */

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

			for (DesiredSkills ds : desiredSkills) {
				// EmpHistorytable.addCell(cellemp);
				cell = new PdfPCell(new Phrase(ds.dskill, font1));
				cell.setBackgroundColor(new BaseColor(248, 248, 255));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ds.comment, font1));
				cell.setBackgroundColor(new BaseColor(248, 248, 255));
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
			for (MandatorySkills ms : mandatorySkils) {
				// EmpHistorytable.addCell(cellemp);
				cell1 = new PdfPCell(new Phrase(ms.mskill, font1));
				cell1.setBackgroundColor(new BaseColor(248, 248, 255));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell1);

				cell1 = new PdfPCell(new Phrase(ms.comment, font1));
				cell1.setBackgroundColor(new BaseColor(248, 248, 255));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell1);
			}

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
				cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.startdate, font1));
				cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.enddate, font1));
				cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);

				cellemp = new PdfPCell(new Phrase(emp.position, font1));
				cellemp.setBackgroundColor(new BaseColor(248, 248, 255));
				cellemp.setHorizontalAlignment(Element.ALIGN_LEFT);
				EmpHistorytable.addCell(cellemp);
			}

			PdfPTable edutable = new PdfPTable(4);
			edutable.setWidthPercentage(100);
			float[] colwidth = { 2f, 2f, 2f, 2f };
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

			celledu = new PdfPCell(new Phrase("Degree Major", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);

			celledu = new PdfPCell(new Phrase("Completion Date", font2));
			celledu.setBackgroundColor(new BaseColor(230, 230, 250));
			celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
			edutable.addCell(celledu);

			List<EducationDetails> ed = EducationDetails
					.getEducationDetailsByUserEmail(email);
			for (EducationDetails edu : ed) {
				// edutable.addCell(celledu);
				celledu = new PdfPCell(new Phrase(edu.degree, font1));
				celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);

				celledu = new PdfPCell(new Phrase(edu.instituteName, font1));
				celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);

				celledu = new PdfPCell(new Phrase(edu.degree, font1));
				celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);

				celledu = new PdfPCell(new Phrase(edu.toDate, font1));
				celledu.setBackgroundColor(new BaseColor(248, 248, 255));
				celledu.setHorizontalAlignment(Element.ALIGN_LEFT);
				edutable.addCell(celledu);
			}

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

			cellcert = new PdfPCell(new Phrase("Award Date", font2));
			cellcert.setBackgroundColor(new BaseColor(230, 230, 250));
			cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
			certtable.addCell(cellcert);

			for (CertificationDetails c : cd) {
				// certtable.addCell(cellcert);
				cell.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase(c.certName, font1));
				cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				cellcert.setBorder(Rectangle.NO_BORDER);
				cellcert = new PdfPCell(new Phrase(c.certYear, font1));
				cellcert.setBackgroundColor(new BaseColor(248, 248, 255));
				cellcert.setHorizontalAlignment(Element.ALIGN_LEFT);
				certtable.addCell(cellcert);
				cellcert.setBorder(Rectangle.NO_BORDER);
				// certtable.addCell(c.certName);
				// certtable.addCell(c.certYear);
			}

			PdfPTable expDesctable = new PdfPTable(1);
			expDesctable.getDefaultCell().setBorder(0);
			expDesctable.setWidthPercentage(100);
			float[] expcw = { 10f };
			expDesctable.setWidths(expcw);

			// PdfPCell cellExp = new PdfPCell(new
			// Paragraph("CERTIFICATIONS:"));
			PdfPCell cellExp = new PdfPCell(new Phrase("List of Certification",
					font2));
			Paragraph paragraph = new Paragraph();
			paragraph.add(new Phrase("This is a chapter."));
			// Chapter chapter = new Chapter(1);
			for (EmploymentDetails emp : eds) {
				/*
				 * chapter.addSection(emp.companyName);
				 * chapter.addSection(emp.position);
				 * chapter.addSection(emp.expdesc);
				 */
				cellExp = new PdfPCell(new Phrase(emp.companyName + ", " + " "
						+ emp.position + " " + "(" + emp.startdate + " - "
						+ emp.enddate + ")", font));
				cellExp.setBackgroundColor(new BaseColor(230, 230, 250));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellExp.setBorder(Rectangle.NO_BORDER);
				expDesctable.addCell(cellExp);

				/*
				 * cellExp = new PdfPCell(new Phrase(emp.position, font2));
				 * cellExp .setBackgroundColor(new BaseColor(230, 230, 250));
				 * cellExp .setHorizontalAlignment(Element.ALIGN_LEFT);
				 * cellExp.setBorder(Rectangle.NO_BORDER);
				 * expDesctable.addCell(cellExp);
				 */

				cellExp = new PdfPCell(new Phrase(emp.expdesc, font1));
				// cellExp .setBackgroundColor(new BaseColor(230, 230, 250));
				cellExp.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellExp.setBorder(Rectangle.NO_BORDER);
				expDesctable.addCell(cellExp);

			}

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String date = sdf.format(new Date());
			System.out.println(date); // 15/10/2013
			Paragraph date1 = new Paragraph(date);
			date1.setAlignment(Element.ALIGN_LEFT);
			document.add(date1);

			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			// document.add(new Paragraph("Candidate’s Name:",font1));
			document.add(table4);
			// preface.setAlignment(Element.ALIGN_CENTER);
			document.add(chunkClearance);
			document.add(table3);// user clearance
			document.add(chunkSkills);
			document.add(table1);// mandatory skills

			document.add(Chunk.NEWLINE);
			document.add(chunk);
			document.add(table2);// skill table
			
			document.add(Chunk.NEWLINE);
			document.add(chunkDesired);
			document.add(table);// desired skills
			
			document.add(Chunk.NEWLINE);
			document.add(empChunk);
			document.add(EmpHistorytable);

			document.add(Chunk.NEWLINE);
			document.add(eduChunk);
			document.add(edutable);

			document.add(Chunk.NEWLINE);
			document.add(certChunk);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(certtable);
			// document.add(Chunk.NEWLINE);
			document.add(chunkExpDetails);
			document.add(expDesctable);
			// document.newPage(); // Opened new page.
			response().setContentType("application/pdf");
			response().setHeader("Content-Disposition",
					"inline; filename=" + "Resume.pdf");
			document.close();
			file.close();
			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok(new File(rootDir));

	}

	/*
	 * public static Result getJobsByLocation(String currentpage,String
	 * location){
	 * 
	 * return ok()
	 * 
	 * }
	 */
	
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
	public String duetoPmo;
	public String updateDate;
	public String duetoGovt;
	}
	public static  Result saveEditJob(){
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		
		JsonNode editJobJson = json.path("editJob");
		ObjectMapper editJob = new ObjectMapper();

		try {
			EditJobVM editJobVM = editJob.readValue(editJobJson.traverse(),
					EditJobVM.class);
			StoreExcelFile storeExcel = StoreExcelFile.getregNumber(editJobVM.requestNumber);
			
			storeExcel.requestNumber  = editJobVM.requestNumber;
			storeExcel.requestType  =editJobVM.requestType;
			storeExcel.labourCategory  =editJobVM.labourCategory;
			storeExcel.performanceLevel  =editJobVM.performanceLevel;
			storeExcel.positionType  =editJobVM.positionType;
			storeExcel.clearanceRequired  = editJobVM.clearanceRequired;
			storeExcel.workLocation  =editJobVM.workLocation;
			storeExcel.workDescription  =editJobVM.workDescription;
			storeExcel.manadatorySkills  =json.path("manadatorySkills").toString();
			storeExcel.desiredSkill  =json.path("desiredSkill").toString();
			storeExcel.certificationRequired  =editJobVM.certificationRequired;
			storeExcel.conusTravel  =editJobVM.conusTravel;
			storeExcel.oconusTravel  =editJobVM.oconusTravel;
			storeExcel.reghrperYear  =editJobVM.reghrperYear;
			storeExcel.scheduleComments  =editJobVM.scheduleComments;
			storeExcel.nonpubComments  =editJobVM.nonpubComments;
			storeExcel.missionCritical  = editJobVM.missionCritical;
			storeExcel. nightWork =editJobVM.nightWork;
			storeExcel.localTravalusingpub  =editJobVM.localTravalusingpub;
			storeExcel.pagerDuty  =editJobVM.pagerDuty;
			storeExcel.pagerdutyComments  =editJobVM.pagerdutyComments;
			storeExcel.workonHoliday  =editJobVM.workonHoliday;
			storeExcel.workonWeekends  =editJobVM.workonWeekends;
			storeExcel.shiftWork  =editJobVM.shiftWork;
			storeExcel.warzone  =editJobVM.warzone;
			storeExcel.coop  =editJobVM.coop;
			storeExcel.duetoPmo  =editJobVM.duetoPmo;
			storeExcel.updateDate  =editJobVM.updateDate;
			storeExcel.duetoGovt  =editJobVM.duetoGovt;
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

		//.update();
		
		return ok("success");
	} 
	
	public static Result inactiveJob(String requestNumber){
		
		StoreExcelFile storeExcel = StoreExcelFile.getregNumber(requestNumber);
	    storeExcel.jobStatus = "inactive";
	    storeExcel.update(storeExcel);
		return ok("");
	}
	
	public static Result onactiveJob(String requestNumber){
		
		StoreExcelFile storeExcel = StoreExcelFile.getregNumber(requestNumber);
	    storeExcel.jobStatus = "active";
	    storeExcel.update(storeExcel);
		return ok("");
	}
	
	public static Result  getAllUsers(){
		
		List<UserDetails> userDetails  = UserDetails.getAllUsers();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", userDetails);
		return ok((Json.stringify(Json.toJson(map))));
		
	}
	
	
	public static Result getUserDetailsForAdmin(String email){
		
		List<EducationDetails> ed = EducationDetails
				.getEducationDetailsByUserEmail(email);
		List<CertificationDetails> cd = CertificationDetails
				.getCertificateDetailsByUserEmail(email);
		List<EmploymentDetails> eds = EmploymentDetails
				.getEmploymentDetailsByUserEmail(email);
		// List<UserSkill> skills = ud.userSkill;
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userDetails", ud);
		map.put("educationDetails", ed);
		map.put("certificationDetails", cd);
		map.put("employmentDetails", eds);

		return ok(Json.toJson(map));
		
	}
	
}
