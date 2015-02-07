// @SOURCE:/home/jobby/conf/routes
// @HASH:003391f000df755f4332b4839cdb0370b97fd844
// @DATE:Fri Feb 06 11:05:03 UTC 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:48
def onActiveUser(email:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "onActiveUser/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
}
                                                

// @LINE:28
def getAllJobs(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getAllJobs/" + implicitly[PathBindable[Integer]].unbind("currentPage", currentPage) + "/" + implicitly[PathBindable[String]].unbind("jobType", dynamicString(jobType)) + "/" + implicitly[PathBindable[Boolean]].unbind("location", location) + "/" + implicitly[PathBindable[Boolean]].unbind("matchedpos", matchedpos) + "/" + implicitly[PathBindable[String]].unbind("position", dynamicString(position)))
}
                                                

// @LINE:26
def getAllSkills(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllSkills")
}
                                                

// @LINE:9
def SignIn(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "SignIn")
}
                                                

// @LINE:37
def generatePDF(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "generatePDF/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                                                

// @LINE:39
def getUserName(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getUserName")
}
                                                

// @LINE:21
def createNewUser(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "createNewUser")
}
                                                

// @LINE:46
def getUserDetailsForAdmin(email:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getUserDetailsForAdmin/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
}
                                                

// @LINE:36
def getAllAppliedJobs(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllAppliedJobs")
}
                                                

// @LINE:20
def updateUserProfile(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "updateUserProfile")
}
                                                

// @LINE:44
def onactiveJob(requestNumber:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "onactiveJob/" + implicitly[PathBindable[String]].unbind("requestNumber", dynamicString(requestNumber)))
}
                                                

// @LINE:38
def checkForadmin(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "checkForadmin")
}
                                                

// @LINE:22
def getAllPosition(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllPosition")
}
                                                

// @LINE:10
def dashBoard(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "dashboard")
}
                                                

// @LINE:27
def forgetPassword(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "forgetPassword")
}
                                                

// @LINE:24
def getUserProfile(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getUserProfile")
}
                                                

// @LINE:41
def saveEditJob(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveEditJob")
}
                                                

// @LINE:8
def signup(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "signup")
}
                                                

// @LINE:23
def getAllClearance(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllClearance")
}
                                                

// @LINE:49
def onInActiveUser(email:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "onInActiveUser/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
}
                                                

// @LINE:40
def getAllJobsForAdmin(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getAllJobsForAdmin/" + implicitly[PathBindable[Integer]].unbind("currentPage", currentPage) + "/" + implicitly[PathBindable[String]].unbind("jobType", dynamicString(jobType)) + "/" + implicitly[PathBindable[Boolean]].unbind("location", location) + "/" + implicitly[PathBindable[Boolean]].unbind("matchedpos", matchedpos) + "/" + implicitly[PathBindable[String]].unbind("position", dynamicString(position)))
}
                                                

// @LINE:47
def updateUserProfileByAdmin(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "updateUserProfileByAdmin")
}
                                                

// @LINE:29
def saveOtherSkill(otherSkill:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "saveOtherSkill/" + implicitly[PathBindable[String]].unbind("otherSkill", dynamicString(otherSkill)))
}
                                                

// @LINE:30
def saveAppliedJob(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveAppliedJob")
}
                                                

// @LINE:16
def logOut(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "Logout")
}
                                                

// @LINE:45
def getAllUsers(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllUsers")
}
                                                

// @LINE:25
def uploadandStoreExcel(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "uploadExcel")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:42
def inactiveJob(requestNumber:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "inactiveJob/" + implicitly[PathBindable[String]].unbind("requestNumber", dynamicString(requestNumber)))
}
                                                

// @LINE:7
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          
}
                  


// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:48
def onActiveUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.onActiveUser",
   """
      function(email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "onActiveUser/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
      }
   """
)
                        

// @LINE:28
def getAllJobs : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllJobs",
   """
      function(currentPage,jobType,location,matchedpos,position) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllJobs/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("currentPage", currentPage) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobType", encodeURIComponent(jobType)) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("location", location) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("matchedpos", matchedpos) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("position", encodeURIComponent(position))})
      }
   """
)
                        

// @LINE:26
def getAllSkills : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllSkills",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllSkills"})
      }
   """
)
                        

// @LINE:9
def SignIn : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.SignIn",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "SignIn"})
      }
   """
)
                        

// @LINE:37
def generatePDF : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.generatePDF",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "generatePDF/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:39
def getUserName : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getUserName",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getUserName"})
      }
   """
)
                        

// @LINE:21
def createNewUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.createNewUser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "createNewUser"})
      }
   """
)
                        

// @LINE:46
def getUserDetailsForAdmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getUserDetailsForAdmin",
   """
      function(email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getUserDetailsForAdmin/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
      }
   """
)
                        

// @LINE:36
def getAllAppliedJobs : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllAppliedJobs",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllAppliedJobs"})
      }
   """
)
                        

// @LINE:20
def updateUserProfile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.updateUserProfile",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateUserProfile"})
      }
   """
)
                        

// @LINE:44
def onactiveJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.onactiveJob",
   """
      function(requestNumber) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "onactiveJob/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("requestNumber", encodeURIComponent(requestNumber))})
      }
   """
)
                        

// @LINE:38
def checkForadmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.checkForadmin",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "checkForadmin"})
      }
   """
)
                        

// @LINE:22
def getAllPosition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllPosition",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllPosition"})
      }
   """
)
                        

// @LINE:10
def dashBoard : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.dashBoard",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard"})
      }
   """
)
                        

// @LINE:27
def forgetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.forgetPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "forgetPassword"})
      }
   """
)
                        

// @LINE:24
def getUserProfile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getUserProfile",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getUserProfile"})
      }
   """
)
                        

// @LINE:41
def saveEditJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.saveEditJob",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveEditJob"})
      }
   """
)
                        

// @LINE:8
def signup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.signup",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:23
def getAllClearance : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllClearance",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllClearance"})
      }
   """
)
                        

// @LINE:49
def onInActiveUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.onInActiveUser",
   """
      function(email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "onInActiveUser/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
      }
   """
)
                        

// @LINE:40
def getAllJobsForAdmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllJobsForAdmin",
   """
      function(currentPage,jobType,location,matchedpos,position) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllJobsForAdmin/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("currentPage", currentPage) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobType", encodeURIComponent(jobType)) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("location", location) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("matchedpos", matchedpos) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("position", encodeURIComponent(position))})
      }
   """
)
                        

// @LINE:47
def updateUserProfileByAdmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.updateUserProfileByAdmin",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateUserProfileByAdmin"})
      }
   """
)
                        

// @LINE:29
def saveOtherSkill : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.saveOtherSkill",
   """
      function(otherSkill) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "saveOtherSkill/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("otherSkill", encodeURIComponent(otherSkill))})
      }
   """
)
                        

// @LINE:30
def saveAppliedJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.saveAppliedJob",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveAppliedJob"})
      }
   """
)
                        

// @LINE:16
def logOut : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logOut",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Logout"})
      }
   """
)
                        

// @LINE:45
def getAllUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllUsers",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllUsers"})
      }
   """
)
                        

// @LINE:25
def uploadandStoreExcel : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.uploadandStoreExcel",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "uploadExcel"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:42
def inactiveJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.inactiveJob",
   """
      function(requestNumber) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "inactiveJob/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("requestNumber", encodeURIComponent(requestNumber))})
      }
   """
)
                        

// @LINE:7
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:48
def onActiveUser(email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.onActiveUser(email), HandlerDef(this, "controllers.Application", "onActiveUser", Seq(classOf[String]), "GET", """""", _prefix + """onActiveUser/$email<[^/]+>""")
)
                      

// @LINE:28
def getAllJobs(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllJobs(currentPage, jobType, location, matchedpos, position), HandlerDef(this, "controllers.Application", "getAllJobs", Seq(classOf[Integer], classOf[String], classOf[Boolean], classOf[Boolean], classOf[String]), "POST", """""", _prefix + """getAllJobs/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>""")
)
                      

// @LINE:26
def getAllSkills(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllSkills(), HandlerDef(this, "controllers.Application", "getAllSkills", Seq(), "GET", """""", _prefix + """getAllSkills""")
)
                      

// @LINE:9
def SignIn(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.SignIn(), HandlerDef(this, "controllers.Application", "SignIn", Seq(), "POST", """""", _prefix + """SignIn""")
)
                      

// @LINE:37
def generatePDF(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.generatePDF(id), HandlerDef(this, "controllers.Application", "generatePDF", Seq(classOf[String]), "GET", """""", _prefix + """generatePDF/$id<[^/]+>""")
)
                      

// @LINE:39
def getUserName(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getUserName(), HandlerDef(this, "controllers.Application", "getUserName", Seq(), "GET", """""", _prefix + """getUserName""")
)
                      

// @LINE:21
def createNewUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.createNewUser(), HandlerDef(this, "controllers.Application", "createNewUser", Seq(), "POST", """""", _prefix + """createNewUser""")
)
                      

// @LINE:46
def getUserDetailsForAdmin(email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getUserDetailsForAdmin(email), HandlerDef(this, "controllers.Application", "getUserDetailsForAdmin", Seq(classOf[String]), "GET", """""", _prefix + """getUserDetailsForAdmin/$email<[^/]+>""")
)
                      

// @LINE:36
def getAllAppliedJobs(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllAppliedJobs(), HandlerDef(this, "controllers.Application", "getAllAppliedJobs", Seq(), "GET", """Admin""", _prefix + """getAllAppliedJobs""")
)
                      

// @LINE:20
def updateUserProfile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.updateUserProfile(), HandlerDef(this, "controllers.Application", "updateUserProfile", Seq(), "POST", """User""", _prefix + """updateUserProfile""")
)
                      

// @LINE:44
def onactiveJob(requestNumber:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.onactiveJob(requestNumber), HandlerDef(this, "controllers.Application", "onactiveJob", Seq(classOf[String]), "GET", """""", _prefix + """onactiveJob/$requestNumber<[^/]+>""")
)
                      

// @LINE:38
def checkForadmin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.checkForadmin(), HandlerDef(this, "controllers.Application", "checkForadmin", Seq(), "GET", """""", _prefix + """checkForadmin""")
)
                      

// @LINE:22
def getAllPosition(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllPosition(), HandlerDef(this, "controllers.Application", "getAllPosition", Seq(), "GET", """""", _prefix + """getAllPosition""")
)
                      

// @LINE:10
def dashBoard(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.dashBoard(), HandlerDef(this, "controllers.Application", "dashBoard", Seq(), "GET", """""", _prefix + """dashboard""")
)
                      

// @LINE:27
def forgetPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.forgetPassword(), HandlerDef(this, "controllers.Application", "forgetPassword", Seq(), "POST", """""", _prefix + """forgetPassword""")
)
                      

// @LINE:24
def getUserProfile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getUserProfile(), HandlerDef(this, "controllers.Application", "getUserProfile", Seq(), "GET", """""", _prefix + """getUserProfile""")
)
                      

// @LINE:41
def saveEditJob(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.saveEditJob(), HandlerDef(this, "controllers.Application", "saveEditJob", Seq(), "POST", """""", _prefix + """saveEditJob""")
)
                      

// @LINE:8
def signup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.signup(), HandlerDef(this, "controllers.Application", "signup", Seq(), "GET", """""", _prefix + """signup""")
)
                      

// @LINE:23
def getAllClearance(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllClearance(), HandlerDef(this, "controllers.Application", "getAllClearance", Seq(), "GET", """""", _prefix + """getAllClearance""")
)
                      

// @LINE:49
def onInActiveUser(email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.onInActiveUser(email), HandlerDef(this, "controllers.Application", "onInActiveUser", Seq(classOf[String]), "GET", """""", _prefix + """onInActiveUser/$email<[^/]+>""")
)
                      

// @LINE:40
def getAllJobsForAdmin(currentPage:Integer, jobType:String, location:Boolean, matchedpos:Boolean, position:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllJobsForAdmin(currentPage, jobType, location, matchedpos, position), HandlerDef(this, "controllers.Application", "getAllJobsForAdmin", Seq(classOf[Integer], classOf[String], classOf[Boolean], classOf[Boolean], classOf[String]), "POST", """""", _prefix + """getAllJobsForAdmin/$currentPage<[^/]+>/$jobType<[^/]+>/$location<[^/]+>/$matchedpos<[^/]+>/$position<[^/]+>""")
)
                      

// @LINE:47
def updateUserProfileByAdmin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.updateUserProfileByAdmin(), HandlerDef(this, "controllers.Application", "updateUserProfileByAdmin", Seq(), "POST", """""", _prefix + """updateUserProfileByAdmin""")
)
                      

// @LINE:29
def saveOtherSkill(otherSkill:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.saveOtherSkill(otherSkill), HandlerDef(this, "controllers.Application", "saveOtherSkill", Seq(classOf[String]), "GET", """""", _prefix + """saveOtherSkill/$otherSkill<[^/]+>""")
)
                      

// @LINE:30
def saveAppliedJob(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.saveAppliedJob(), HandlerDef(this, "controllers.Application", "saveAppliedJob", Seq(), "POST", """""", _prefix + """saveAppliedJob""")
)
                      

// @LINE:16
def logOut(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logOut(), HandlerDef(this, "controllers.Application", "logOut", Seq(), "GET", """""", _prefix + """Logout""")
)
                      

// @LINE:45
def getAllUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllUsers(), HandlerDef(this, "controllers.Application", "getAllUsers", Seq(), "GET", """""", _prefix + """getAllUsers""")
)
                      

// @LINE:25
def uploadandStoreExcel(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.uploadandStoreExcel(), HandlerDef(this, "controllers.Application", "uploadandStoreExcel", Seq(), "POST", """""", _prefix + """uploadExcel""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:42
def inactiveJob(requestNumber:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.inactiveJob(requestNumber), HandlerDef(this, "controllers.Application", "inactiveJob", Seq(classOf[String]), "GET", """""", _prefix + """inactiveJob/$requestNumber<[^/]+>""")
)
                      

// @LINE:7
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """""", _prefix + """login""")
)
                      
    
}
                          
}
        
    