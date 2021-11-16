package me.av306.mcvalorant;


import me.av306.mcvalorant.commands.CommandGiveWeapons;
import me.av306.mcvalorant.commands.CommandMatch;
import me.av306.mcvalorant.listener.PlayerDeathEventListener;
import me.av306.mcvalorant.listener.PlayerJoinEventListener;
import me.av306.mcvalorant.listener.PlayerPostRespawnEventListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin
{
    public static Server psmp;
    public static PluginManager pluginManager;
    public static final Logger LOGGER = Bukkit.getLogger();

    public static HashMap<Player, Integer> scores = new HashMap<>();


    @Override
    public void onEnable()
    {
        psmp = this.getServer();
        pluginManager = psmp.getPluginManager();


        LOGGER.info( "MCValorant plugin created by AV3_08!" );
        logConsole( "Initialising! Here we go!" );
        logConsole( "Server: " + psmp.getName() );
        logConsole( "Plugins: " + pluginManager.getPlugins() );


        // events
        pluginManager.registerEvents( new PlayerDeathEventListener(), this );
        logConsole( "Registered PlayerDeathEvent listener!" );

        pluginManager.registerEvents( new PlayerPostRespawnEventListener(), this );
        logConsole( "Registered PlayerPostRespawnEvent listener!" );

        pluginManager.registerEvents( new PlayerJoinEventListener(), this );
        logConsole( "Registered PlayerJoinEvent listener!" );


        // Commands
        // this.getCommand( "startmatch" ).setExecutor( new CommandStartMatch() ); // now encapsulated
        // this.getCommand( "endmatch" ).setExecutor( new CommandEndMatch() );
        this.getCommand( "match" ).setExecutor( new CommandMatch() );
        this.getCommand( "giveweapons" ).setExecutor( new CommandGiveWeapons() );
        logConsole( "Registered commands!" );




        PlayerDeathEventListener.isEnabled = false;
        PlayerPostRespawnEventListener.isEnabled = false;
        PlayerJoinEventListener.isEnabled = false;
    }






    public static void logConsole( String msg )
    {
        LOGGER.info( "[MCValorant] " + msg );
    }

    public static Collection<? extends Player> quickGetPlayers()
    {
        return psmp.getOnlinePlayers();
    }
}