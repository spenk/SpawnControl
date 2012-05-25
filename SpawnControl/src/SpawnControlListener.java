import java.io.File;

public class SpawnControlListener extends PluginListener{
    boolean sah;
    boolean psogs;
    String sl;
    boolean ral;
    
	String file0 = "plugins/config/SpawnControl/SpawnControlprops.properties";
	String file1 = "plugins/config/SpawnControl/SpawnControlgroups.properties";
	String file2 = "plugins/config/SpawnControl/SpawnControlplayers.properties";
    public boolean onCommand(Player player, String[] split){    	
	if (split[0].equalsIgnoreCase("/setgroupspawn")||split[0].equalsIgnoreCase("/sgs")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <2 || split.length >2){
			player.notify("The corrent usage is /setgroupspawn <Groupname>!");
			return true;
		}
		Group group = etc.getDataSource().getGroup(split[1]);
		if (group == null){
			player.notify("This group doesn not exist!");
			return true;
		}
		String loc = (int)Math.floor(player.getX())+","+(int)Math.floor(player.getY())+","+(int)Math.floor(player.getZ())+","+(int)Math.floor(player.getLocation().dimension);
		addprop(file1,group.Name,loc);
		reloadfiles();
		player.sendMessage("§3"+group.Name+"§2 Spawn sucessfully Set!");
		return true;
	}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/removegroupspawn")||split[0].equalsIgnoreCase("/rgs")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <2 || split.length >2){
			player.notify("The corrent usage is /removegroupspawn <Groupname>!");
			return true;
		}
		Group group = etc.getDataSource().getGroup(split[1]);
		if (group == null){
			player.notify("This group doesn not exist!");
			return true;
		}
		PropertiesFile groups = new PropertiesFile(file1);
		if (!groups.containsKey(group.Name)){
			player.notify("This Group doesnt have a spawn!");
			return true;
		}
		delprop(file1,group.Name);
		reloadfiles();
		player.sendMessage("§2Spawn sucessfully Removed!");
		return true;
	}
	player.notify("You cant use this command!");
	return true;
    }
	
	if (split[0].equalsIgnoreCase("/setplayerspawn")||split[0].equalsIgnoreCase("/sps")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <2 || split.length >2){
			player.notify("The corrent usage is /setplayerspawn <Playername>!");
			return true;
		}
		Player player2 = etc.getServer().matchPlayer(split[1]);
		if (player2 == null){
			player.notify("This player is not online or does not exist!");
			return true;
		}
		String loc = (int)Math.floor(player.getX())+","+(int)Math.floor(player.getY())+","+(int)Math.floor(player.getZ())+","+(int)Math.floor(player.getLocation().dimension);
		addprop(file2,player2.getName(),loc);
		player.sendMessage("§2Spawn sucessfully Set!");
		reloadfiles();
		return true;
		}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/removeplayerspawn")||split[0].equalsIgnoreCase("/rps")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <2 || split.length >2){
			player.notify("The corrent usage is /removeplayerspawn <Playername>!");
			return true;
		}
		Player player2 = etc.getServer().matchPlayer(split[1]);
		if (player2 == null){
			player.notify("This player is not online or does not exist!");
			return true;
		}
		PropertiesFile players = new PropertiesFile(file2);
		if (!players.containsKey(player2.getName())){
			player.notify("This Player doesnt have a spawn!");
			return true;
		}
		delprop(file2,player.getName());
		reloadfiles();
		player.sendMessage("§2Spawn sucessfully Removed!");
		return true;
	}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/setglobalspawn")||split[0].equalsIgnoreCase("/sgls")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <1 || split.length >1){
			player.notify("The corrent usage is /setglobalspawn!");
			return true;
		}
		String loc = (int)Math.floor(player.getX())+","+(int)Math.floor(player.getY())+","+(int)Math.floor(player.getZ())+","+(int)Math.floor(player.getLocation().dimension);
		addprop(file0,"SpawnLocation",loc);
		sl = loc;
		player.sendMessage("§2Spawn sucessfully Set!");
		reloadfiles();
		return true;
	}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/forcespawn")||split[0].equalsIgnoreCase("/forcerespawn")){
		if (player.canUseCommand("/spawncontrol")){
		if (split.length <2 || split.length >2){
			player.notify("The corrent usage is /forcespawn <player>!");
			return true;
		}
		Player player2 = etc.getServer().matchPlayer(split[1]);
		if (player2 == null){
			player.notify("This player is not online or does not exist!");
			return true;
		}
    	Integer[] loc = decode(getspawn(player2));
    	if (player2.getLocation().dimension != loc[3]){
    	player2.switchWorlds(loc[3]);
    	}
    	player2.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	player2.sendMessage("§2Respawned!");
    	player.sendMessage("§2Respawned!");
		return true;
	}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/spawn")||split[0].equalsIgnoreCase("/respawn")){
		if(player.canUseCommand("/spawn")){
		if (split.length <1 || split.length >1){
			player.notify("The corrent usage is /spawn!");
			return true;
		}
    	Integer[] loc = decode(getspawn(player));
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	player.sendMessage("§2Respawned!");
	}
		return true;
	}
	if (split[0].equalsIgnoreCase("/GlobalSpawn")||split[0].equalsIgnoreCase("/gls")){
		if(player.canUseCommand("/spawn")){
		if (split.length <1 || split.length >1){
			player.notify("The corrent usage is /globalspawn!");
			return true;
		}
    	Integer[] loc = decode(sl);
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	player.sendMessage("§2Respawned!");
    	return true;
	}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/GroupSpawn")||split[0].equalsIgnoreCase("/gs")){
		if(player.canUseCommand("/spawn")){
		PropertiesFile groups = new PropertiesFile(file1);
		if (split.length <1 || split.length >1){
			player.notify("The corrent usage is /groupspawn!");
			return true;
		}
		String pgroup = (player.getGroups().length > 0 ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
		if (groups.containsKey(pgroup)){
		String s = groups.getProperty(pgroup);
    	Integer[] loc = decode(s);
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	player.sendMessage("§2Respawned!");
    	return true;
	}else{
		player.notify("The group you are in doesnt have an spawn!");
		return true;
	}
		}
		player.notify("You cant use this command!");
		return true;
	}
	if (split[0].equalsIgnoreCase("/playerSpawn")||split[0].equalsIgnoreCase("/ps")){
		if (player.canUseCommand("/spawn")){
		PropertiesFile players = new PropertiesFile(file2);
		if (split.length <1 || split.length >1){
			player.notify("The corrent usage is /playerspawn!");
			return true;
		}
		if (players.containsKey(player.getName())){
		String s = players.getProperty(player.getName());
    	Integer[] loc = decode(s);
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	player.sendMessage("§2Respawned!");
    	return true;
	}else{
		player.notify("You dont have an Individual spawn!");
		return true;
	}
		}
		player.notify("You cant use this command!");
		return true;
	}
	return false;
    }
    public void addprop(String file, String key, String value){
    	PropertiesFile f = new PropertiesFile(file);
    	f.setString(key, value);
    }
    public void delprop(String file, String key){
    	PropertiesFile f = new PropertiesFile(file);
    	f.removeKey(key);
    }
    public void createprops(){
    	PropertiesFile f = new PropertiesFile("plugins/config/SpawnControl/SpawnControlprops.properties");
    	sah = f.getBoolean("SpawnAtHome",false);
    	psogs = f.getBoolean("PlayerSpawnOverrideGroupSpawn",true);
    	Location player = etc.getServer().getDefaultWorld().getSpawnLocation();
    	String loc = (int)Math.floor(player.x)+","+(int)Math.floor(player.y)+","+(int)Math.floor(player.z)+","+(int)Math.floor(player.dimension);
    	sl = f.getString("SpawnLocation",loc);
    	ral = f.getBoolean("RespawnOnLogin",false);
    }
    public void createdir(){
    	File f = new File("plugins/config");
    	File f2 = new File("plugins/config/SpawnControl");
    	f.mkdir();
    	f2.mkdir();
    	return;
    }
    public void onPlayerRespawn(Player player,Location spawnLocation){
    	Integer[] loc = decode(getspawn(player));
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    }
    public void onPlayerRespawn(Player player){
    	Integer[] loc = decode(getspawn(player));
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    }
    public boolean onHealthChange(Player player,int oldValue,int newValue){
    	if (oldValue < 1 && newValue == 20){
    	Integer[] loc = decode(getspawn(player));
    	if (player.getLocation().dimension != loc[3]){
    	player.switchWorlds(loc[3]);
    	}
    	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
    	return false;
    	}
		return false;
    }
    public Integer[] decode(String s){
    	String[] sb = null;
    	sb = s.split(",");
    	Integer[] arr = {Integer.parseInt(sb[0]),Integer.parseInt(sb[1]),Integer.parseInt(sb[2]),Integer.parseInt(sb[3])};
		return arr;
    }
    public String getspawn(Player player){
    	PropertiesFile groups = new PropertiesFile(file1);
    	PropertiesFile players = new PropertiesFile(file2);
    	if (sah){
    		Warp h = etc.getDataSource().getHome(player.getName());
    		Location loc = h.Location;
    		String locs = (int)Math.floor(loc.x)+","+(int)Math.floor(loc.y)+","+(int)Math.floor(loc.z)+","+(int)Math.floor(loc.dimension);
    		return locs;
    	}
    	String pgroup = (player.getGroups().length > 0 ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
    	
    	if (groups.containsKey(pgroup) && players.containsKey(player.getName())){
    		if (psogs){
    			return players.getProperty(player.getName());
    		}
    		return groups.getProperty(pgroup);
    	}
    	
    	if(groups.containsKey(pgroup) && !players.containsKey(player.getName())){
    			return groups.getProperty(pgroup);
    		}
    	
    	if(!groups.containsKey(pgroup) && players.containsKey(player.getName())){
		return players.getProperty(player.getName());
    	}
		if(!groups.containsKey(pgroup) && players.containsKey(player.getName())){
		return sl;
		}
		return sl;
    }
    public void onLogin(Player player){
    	if (ral){
        	Integer[] loc = decode(getspawn(player));
        	if (player.getLocation().dimension != loc[3]){
        	player.switchWorlds(loc[3]);
        	}
        	player.teleportTo(loc[0], loc[1], loc[2], 0, 0);
        	return;
    	}
    }
    public void reloadfiles(){
    	PropertiesFile f = new PropertiesFile(file1);
    	PropertiesFile f2 = new PropertiesFile(file2);
    	f.save();
    	f2.save();
    }
    public void addcmdth(){
    	etc.getInstance().addCommand("/SetGroupSpawn", "<group> set a group spawn (/sgs)");
    	etc.getInstance().addCommand("/RemoveGroupSpawn", "<group> remove a group spawn (/rgs)");
    	etc.getInstance().addCommand("/SetPlayerSpawn", "<Player> set a Player spawn (/sps)");
    	etc.getInstance().addCommand("/RemovePlayerSpawn", "<Player> remove a Player spawn (/rps)");
    	etc.getInstance().addCommand("/SetGlobalSpawn", "sets the global spawn (/sgls)");
    	etc.getInstance().addCommand("/GlobalSpawn", "teleports you to global spawn (/gls)");
    	etc.getInstance().addCommand("/PlayerSpawn", "teleports you to Player spawn (/ps)");
    	etc.getInstance().addCommand("/GroupSpawn", "teleports you to Group spawn (/gs)");
    	etc.getInstance().addCommand("/spawn", "teleports you to first able spawn (/respawn)");
    	etc.getInstance().addCommand("/forcespawn", "<player> teleports the player to first able spawn (/forcerespawn)");
    }
    public void remcmdth(){
    	
    	etc.getInstance().removeCommand("/SetGroupSpawn");
    	etc.getInstance().removeCommand("/RemoveGroupSpawn");
    	etc.getInstance().removeCommand("/SetPlayerSpawn");
    	etc.getInstance().removeCommand("/RemovePlayerSpawn");
    	etc.getInstance().removeCommand("/SetGlobalSpawn");
    	etc.getInstance().removeCommand("/GlobalSpawn");
    	etc.getInstance().removeCommand("/PlayerSpawn");
    	etc.getInstance().removeCommand("/GroupSpawn");
    	etc.getInstance().removeCommand("/spawn");
    	etc.getInstance().removeCommand("/forcespawn");
    }
}