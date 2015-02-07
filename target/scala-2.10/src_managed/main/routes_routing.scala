// @SOURCE:/home/jobby/conf/routes
// @HASH:003391f000df755f4332b4839cdb0370b97fd844
// @DATE:Fri Feb 06 11:05:03 UTC 2015


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_login1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:8
private[this] lazy val controllers_Application_signup2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
        

// @LINE:9
private[this] lazy val controllers_Application_SignIn3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("SignIn"))))
        

// @LINE:10
private[this] lazy val controllers_Application_dashBoard4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard"))))
        

// @LINE:15
private[this] lazy val controllers_Assets_at5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:16
private[this] lazy val controllers_Application_logOut6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("Logout"))))
        

// @LINE:20
private[this] lazy val controllers_Application_updateUserProfile7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("updateUserProfile"))))
        

// @LINE:21
private[this] lazy val controllers_Application_createNewUser8 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createNewUser"))))
        

// @LINE:22
private[this] lazy val controllers_Application_getAllPosition9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllPosition"))))
        

// @LINE:23
private[this] lazy val controllers_Application_getAllClearance10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllClearance"))))
        

// @LINE:24
private[this] lazy val controllers_Application_getUserProfile11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getUserProfile"))))
        

// @LINE:25
private[this] lazy val controllers_Application_uploadandStoreExcel12 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("uploadExcel"))))
        

// @LINE:26
private[this] lazy val controllers_Application_getAllSkills13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllSkills"))))
        

// @LINE:27
private[this] lazy val controllers_Application_forgetPassword14 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("forgetPassword"))))
        

// @LINE:28
private[this] lazy val controllers_Application_getAllJobs15 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllJobs/"),DynamicPart("currentPage", """[^/]+""",true),StaticPart("/"),DynamicPart("jobType", """[^/]+""",true),StaticPart("/"),DynamicPart("location", """[^/]+""",true),StaticPart("/"),DynamicPart("matchedpos", """[^/]+""",true),StaticPart("/"),DynamicPart("position", """[^/]+""",true))))
        

// @LINE:29
private[this] lazy val controllers_Application_saveOtherSkill16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveOtherSkill/"),DynamicPart("otherSkill", """[^/]+""",true))))
        

// @LINE:30
private[this] lazy val controllers_Application_saveAppliedJob17 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveAppliedJob"))))
        

// @LINE:36
private[this] lazy val controllers_Application_getAllAppliedJobs18 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllAppliedJobs"))))
        

// @LINE:37
private[this] lazy val controllers_Application_generatePDF19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("generatePDF/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:38
private[this] lazy val controllers_Application_checkForadmin20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("checkForadmin"))))
        

// @LINE:39
private[this] lazy val controllers_Application_getUserName21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getUserName"))))
        

// @LINE:40
private[this] lazy val controllers_Application_getAllJobsForAdmin22 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllJobsForAdmin/"),DynamicPart("currentPage", """[^/]+""",true),StaticPart("/"),DynamicPart("jobType", """[^/]+""",true),StaticPart("/"),DynamicPart("location", """[^/]+""",true),StaticPart("/"),DynamicPart("matchedpos", """[^/]+""",true),StaticPart("/"),DynamicPart("position", """[^/]+""",true))))
        

// @LINE:41
private[this] lazy val controllers_Application_saveEditJob23 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveEditJob"))))
        

// @LINE:42
private[this] lazy val controllers_Application_inactiveJob24 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("inactiveJob/"),DynamicPart("requestNumber", """[^/]+""",true))))
        

// @LINE:44
private[this] lazy val controllers_Application_onactiveJob25 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("onactiveJob/"),DynamicPart("requestNumber", """[^/]+""",true))))
        

// @LINE:45
private[this] lazy val controllers_Application_getAllUsers26 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllUsers"))))
        

// @LINE:46
private[this] lazy val controllers_Application_getUserDetailsForAdmin27 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getUserDetailsForAdmin/"),DynamicPart("email", """[^/]+""",true))))
        

// @LINE:47
private[this] lazy val controllers_Application_updateUserProfileByAdmin28 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("updateUserProfileByAdmin"))))
        

// @LINE:48
private[this] lazy val controllers_Application_onActiveUser29 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("onActiveUser/"),DynamicPart("email", """[^/]+""",true))))
        

// @LINE:49
private[this] lazy val controllers_Application_onInActiveUser30 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("onInActiveUser/"),DynamicPart("email", """[^/]+""",true))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.signup()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """SignIn""","""controllers.Application.SignIn()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard""","""controllers.Application.dashBoard()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """Logout""","""controllers.Application.logOut()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """updateUserProfile""","""controllers.Application.updateUserProfile()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createNewUser""","""controllers.Application.createNewUser()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllPosition""","""controllers.Application.getAllPosition()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllClearance""","""controllers.Application.getAllClearance()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getUserProfile""","""controllers.Application.getUserProfile()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """uploadExcel""","""controllers.Application.uploadandStoreExcel()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllSkills""","""controllers.Application.getAllSkills()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """forgetPassword""","""controllers.Application.forgetPassword()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllJobs/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>""","""controllers.Application.getAllJobs(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveOtherSkill/$otherSkill<[^/]+>""","""controllers.Application.saveOtherSkill(otherSkill:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveAppliedJob""","""controllers.Application.saveAppliedJob()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllAppliedJobs""","""controllers.Application.getAllAppliedJobs()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """generatePDF/$id<[^/]+>""","""controllers.Application.generatePDF(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """checkForadmin""","""controllers.Application.checkForadmin()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getUserName""","""controllers.Application.getUserName()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllJobsForAdmin/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>""","""controllers.Application.getAllJobsForAdmin(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveEditJob""","""controllers.Application.saveEditJob()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """inactiveJob/$requestNumber<[^/]+>""","""controllers.Application.inactiveJob(requestNumber:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """onactiveJob/$requestNumber<[^/]+>""","""controllers.Application.onactiveJob(requestNumber:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllUsers""","""controllers.Application.getAllUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getUserDetailsForAdmin/$email<[^/]+>""","""controllers.Application.getUserDetailsForAdmin(email:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """updateUserProfileByAdmin""","""controllers.Application.updateUserProfileByAdmin()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """onActiveUser/$email<[^/]+>""","""controllers.Application.onActiveUser(email:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """onInActiveUser/$email<[^/]+>""","""controllers.Application.onInActiveUser(email:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"GET", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:8
case controllers_Application_signup2(params) => {
   call { 
        invokeHandler(controllers.Application.signup(), HandlerDef(this, "controllers.Application", "signup", Nil,"GET", """""", Routes.prefix + """signup"""))
   }
}
        

// @LINE:9
case controllers_Application_SignIn3(params) => {
   call { 
        invokeHandler(controllers.Application.SignIn(), HandlerDef(this, "controllers.Application", "SignIn", Nil,"POST", """""", Routes.prefix + """SignIn"""))
   }
}
        

// @LINE:10
case controllers_Application_dashBoard4(params) => {
   call { 
        invokeHandler(controllers.Application.dashBoard(), HandlerDef(this, "controllers.Application", "dashBoard", Nil,"GET", """""", Routes.prefix + """dashboard"""))
   }
}
        

// @LINE:15
case controllers_Assets_at5(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:16
case controllers_Application_logOut6(params) => {
   call { 
        invokeHandler(controllers.Application.logOut(), HandlerDef(this, "controllers.Application", "logOut", Nil,"GET", """""", Routes.prefix + """Logout"""))
   }
}
        

// @LINE:20
case controllers_Application_updateUserProfile7(params) => {
   call { 
        invokeHandler(controllers.Application.updateUserProfile(), HandlerDef(this, "controllers.Application", "updateUserProfile", Nil,"POST", """User""", Routes.prefix + """updateUserProfile"""))
   }
}
        

// @LINE:21
case controllers_Application_createNewUser8(params) => {
   call { 
        invokeHandler(controllers.Application.createNewUser(), HandlerDef(this, "controllers.Application", "createNewUser", Nil,"POST", """""", Routes.prefix + """createNewUser"""))
   }
}
        

// @LINE:22
case controllers_Application_getAllPosition9(params) => {
   call { 
        invokeHandler(controllers.Application.getAllPosition(), HandlerDef(this, "controllers.Application", "getAllPosition", Nil,"GET", """""", Routes.prefix + """getAllPosition"""))
   }
}
        

// @LINE:23
case controllers_Application_getAllClearance10(params) => {
   call { 
        invokeHandler(controllers.Application.getAllClearance(), HandlerDef(this, "controllers.Application", "getAllClearance", Nil,"GET", """""", Routes.prefix + """getAllClearance"""))
   }
}
        

// @LINE:24
case controllers_Application_getUserProfile11(params) => {
   call { 
        invokeHandler(controllers.Application.getUserProfile(), HandlerDef(this, "controllers.Application", "getUserProfile", Nil,"GET", """""", Routes.prefix + """getUserProfile"""))
   }
}
        

// @LINE:25
case controllers_Application_uploadandStoreExcel12(params) => {
   call { 
        invokeHandler(controllers.Application.uploadandStoreExcel(), HandlerDef(this, "controllers.Application", "uploadandStoreExcel", Nil,"POST", """""", Routes.prefix + """uploadExcel"""))
   }
}
        

// @LINE:26
case controllers_Application_getAllSkills13(params) => {
   call { 
        invokeHandler(controllers.Application.getAllSkills(), HandlerDef(this, "controllers.Application", "getAllSkills", Nil,"GET", """""", Routes.prefix + """getAllSkills"""))
   }
}
        

// @LINE:27
case controllers_Application_forgetPassword14(params) => {
   call { 
        invokeHandler(controllers.Application.forgetPassword(), HandlerDef(this, "controllers.Application", "forgetPassword", Nil,"POST", """""", Routes.prefix + """forgetPassword"""))
   }
}
        

// @LINE:28
case controllers_Application_getAllJobs15(params) => {
   call(params.fromPath[Integer]("currentPage", None), params.fromPath[String]("jobType", None), params.fromPath[Boolean]("location", None), params.fromPath[Boolean]("matchedpos", None), params.fromPath[String]("position", None)) { (currentPage, jobType, location, matchedpos, position) =>
        invokeHandler(controllers.Application.getAllJobs(currentPage, jobType, location, matchedpos, position), HandlerDef(this, "controllers.Application", "getAllJobs", Seq(classOf[Integer], classOf[String], classOf[Boolean], classOf[Boolean], classOf[String]),"POST", """""", Routes.prefix + """getAllJobs/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>"""))
   }
}
        

// @LINE:29
case controllers_Application_saveOtherSkill16(params) => {
   call(params.fromPath[String]("otherSkill", None)) { (otherSkill) =>
        invokeHandler(controllers.Application.saveOtherSkill(otherSkill), HandlerDef(this, "controllers.Application", "saveOtherSkill", Seq(classOf[String]),"GET", """""", Routes.prefix + """saveOtherSkill/$otherSkill<[^/]+>"""))
   }
}
        

// @LINE:30
case controllers_Application_saveAppliedJob17(params) => {
   call { 
        invokeHandler(controllers.Application.saveAppliedJob(), HandlerDef(this, "controllers.Application", "saveAppliedJob", Nil,"POST", """""", Routes.prefix + """saveAppliedJob"""))
   }
}
        

// @LINE:36
case controllers_Application_getAllAppliedJobs18(params) => {
   call { 
        invokeHandler(controllers.Application.getAllAppliedJobs(), HandlerDef(this, "controllers.Application", "getAllAppliedJobs", Nil,"GET", """Admin""", Routes.prefix + """getAllAppliedJobs"""))
   }
}
        

// @LINE:37
case controllers_Application_generatePDF19(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Application.generatePDF(id), HandlerDef(this, "controllers.Application", "generatePDF", Seq(classOf[String]),"GET", """""", Routes.prefix + """generatePDF/$id<[^/]+>"""))
   }
}
        

// @LINE:38
case controllers_Application_checkForadmin20(params) => {
   call { 
        invokeHandler(controllers.Application.checkForadmin(), HandlerDef(this, "controllers.Application", "checkForadmin", Nil,"GET", """""", Routes.prefix + """checkForadmin"""))
   }
}
        

// @LINE:39
case controllers_Application_getUserName21(params) => {
   call { 
        invokeHandler(controllers.Application.getUserName(), HandlerDef(this, "controllers.Application", "getUserName", Nil,"GET", """""", Routes.prefix + """getUserName"""))
   }
}
        

// @LINE:40
case controllers_Application_getAllJobsForAdmin22(params) => {
   call(params.fromPath[Integer]("currentPage", None), params.fromPath[String]("jobType", None), params.fromPath[Boolean]("location", None), params.fromPath[Boolean]("matchedpos", None), params.fromPath[String]("position", None)) { (currentPage, jobType, location, matchedpos, position) =>
        invokeHandler(controllers.Application.getAllJobsForAdmin(currentPage, jobType, location, matchedpos, position), HandlerDef(this, "controllers.Application", "getAllJobsForAdmin", Seq(classOf[Integer], classOf[String], classOf[Boolean], classOf[Boolean], classOf[String]),"POST", """""", Routes.prefix + """getAllJobsForAdmin/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>"""))
   }
}
        

// @LINE:41
case controllers_Application_saveEditJob23(params) => {
   call { 
        invokeHandler(controllers.Application.saveEditJob(), HandlerDef(this, "controllers.Application", "saveEditJob", Nil,"POST", """""", Routes.prefix + """saveEditJob"""))
   }
}
        

// @LINE:42
case controllers_Application_inactiveJob24(params) => {
   call(params.fromPath[String]("requestNumber", None)) { (requestNumber) =>
        invokeHandler(controllers.Application.inactiveJob(requestNumber), HandlerDef(this, "controllers.Application", "inactiveJob", Seq(classOf[String]),"GET", """""", Routes.prefix + """inactiveJob/$requestNumber<[^/]+>"""))
   }
}
        

// @LINE:44
case controllers_Application_onactiveJob25(params) => {
   call(params.fromPath[String]("requestNumber", None)) { (requestNumber) =>
        invokeHandler(controllers.Application.onactiveJob(requestNumber), HandlerDef(this, "controllers.Application", "onactiveJob", Seq(classOf[String]),"GET", """""", Routes.prefix + """onactiveJob/$requestNumber<[^/]+>"""))
   }
}
        

// @LINE:45
case controllers_Application_getAllUsers26(params) => {
   call { 
        invokeHandler(controllers.Application.getAllUsers(), HandlerDef(this, "controllers.Application", "getAllUsers", Nil,"GET", """""", Routes.prefix + """getAllUsers"""))
   }
}
        

// @LINE:46
case controllers_Application_getUserDetailsForAdmin27(params) => {
   call(params.fromPath[String]("email", None)) { (email) =>
        invokeHandler(controllers.Application.getUserDetailsForAdmin(email), HandlerDef(this, "controllers.Application", "getUserDetailsForAdmin", Seq(classOf[String]),"GET", """""", Routes.prefix + """getUserDetailsForAdmin/$email<[^/]+>"""))
   }
}
        

// @LINE:47
case controllers_Application_updateUserProfileByAdmin28(params) => {
   call { 
        invokeHandler(controllers.Application.updateUserProfileByAdmin(), HandlerDef(this, "controllers.Application", "updateUserProfileByAdmin", Nil,"POST", """""", Routes.prefix + """updateUserProfileByAdmin"""))
   }
}
        

// @LINE:48
case controllers_Application_onActiveUser29(params) => {
   call(params.fromPath[String]("email", None)) { (email) =>
        invokeHandler(controllers.Application.onActiveUser(email), HandlerDef(this, "controllers.Application", "onActiveUser", Seq(classOf[String]),"GET", """""", Routes.prefix + """onActiveUser/$email<[^/]+>"""))
   }
}
        

// @LINE:49
case controllers_Application_onInActiveUser30(params) => {
   call(params.fromPath[String]("email", None)) { (email) =>
        invokeHandler(controllers.Application.onInActiveUser(email), HandlerDef(this, "controllers.Application", "onInActiveUser", Seq(classOf[String]),"GET", """""", Routes.prefix + """onInActiveUser/$email<[^/]+>"""))
   }
}
        
}

}
     