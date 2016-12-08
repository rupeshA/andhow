package yarnandtail.andhow;

/**
 * All tests using AppConfig must extend this class so they have access to the
 * one and only AppConfig.Reloader, which is a single backdoor to cause the
 * AppConfig to reload.
 * 
 * @author eeverman
 */
public class AppConfigTestBase {
	
	public static AppConfig.Reloader reloader = AppConfig.build(null, null, null, null, null);

}