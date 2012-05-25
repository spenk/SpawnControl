import java.util.logging.Logger;

public class SpawnControl extends Plugin
{
	  String name = "SpawnControl";
	  String version = "1.0";
	  String author = " spenk";
	  static Logger log = Logger.getLogger("Minecraft");
		SpawnControlListener listener = new SpawnControlListener();
	  
public void initialize(){
log.info(this.name +" version "+ this.version + " by " +this.author+(" is initialized!"));
etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.PLAYER_RESPAWN, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.HEALTH_CHANGE, listener, this, PluginListener.Priority.MEDIUM);
listener.createdir();
listener.createprops();
}
public void enable(){
	log.info(this.name + " version " + this.version + " by " + this.author + " is enabled!");
	listener.addcmdth();
}

public void disable(){
	log.info(this.name + " version " + this.version + " is disabled!");
	listener.remcmdth();
}
}