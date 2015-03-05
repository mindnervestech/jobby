

import java.util.concurrent.TimeUnit;

import akka.actor.ActorSystem;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

public class Global extends GlobalSettings {
	public static final int CHAR_LEN=200;
	public static final String  APP_ENV_LOCAL = "local";
	public static final String  APP_ENV_VAR = "CURRENT_APPNAME";
	
	@Override
	public void onStart(Application app) {
		ActorSystem dactivateJobs = Akka.system();
		dactivateJobs.scheduler().schedule(
				Duration.create(10000, TimeUnit.MILLISECONDS),
				Duration.create(1, TimeUnit.DAYS), new Runnable() {
					public void run() {
						try {
							controllers.Application.deactivateTheJobByPMODate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, dactivateJobs.dispatcher());
		
	}
	
	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}
	
}
