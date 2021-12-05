package me.av306.mcvalorant;


import me.av306.mcvalorant.commands.CommandGiveWeapons;
import me.av306.mcvalorant.commands.CommandMatch;
import me.av306.mcvalorant.listener.PlayerDeathEventListener;
import me.av306.mcvalorant.listener.PlayerJoinEventListener;
import me.av306.mcvalorant.listener.PlayerPostRespawnEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin
{
    public static Server server;
    public static PluginManager pluginManager;
    public static final Logger LOGGER = Bukkit.getLogger();

    // score map
    public static HashMap<Player, Integer> scores = new HashMap<>();

    // stuff
    public static final ChatColor INFO = ChatColor.AQUA;
    public static final ChatColor SUCCESS = ChatColor.GREEN;
    public static final ChatColor WARN = ChatColor.YELLOW;
    public static final ChatColor ERROR = ChatColor.RED;


    @Override
    public void onEnable()
    {
        // Initialise server and plugin manager
        server = this.getServer();
        pluginManager = server.getPluginManager();


        LOGGER.info( "MCValorant plugin created by AV3_08!" );
        logConsole( "Initialising! Here we go!" );
        logConsole( "Server: " + server.getName() );
        logConsole( "Plugins: " + Arrays.toString(pluginManager.getPlugins()));


        // Register events
        pluginManager.registerEvents( new PlayerDeathEventListener(), this );
        logConsole( "Registered PlayerDeathEvent listener!" );

        pluginManager.registerEvents( new PlayerPostRespawnEventListener(), this );
        logConsole( "Registered PlayerPostRespawnEvent listener!" );

        pluginManager.registerEvents( new PlayerJoinEventListener(), this );
        logConsole( "Registered PlayerJoinEvent listener!" );


        // Register commands
        // this.getCommand( "startmatch" ).setExecutor( new CommandStartMatch() ); // now encapsulated
        // this.getCommand( "endmatch" ).setExecutor( new CommandEndMatch() );
        this.getCommand( "match" ).setExecutor( new CommandMatch() );
        this.getCommand( "giveweapons" ).setExecutor( new CommandGiveWeapons() );
        logConsole( "Registered commands!" );



        // Disable listeners at start
        PlayerDeathEventListener.isEnabled = false;
        PlayerPostRespawnEventListener.isEnabled = false;
        PlayerJoinEventListener.isEnabled = false;
    }






    public static void logConsole( String msg )
    {
        LOGGER.info( "[MCValorant] " + msg );
    }


    public static void informOperator( String msg )
    {
        for ( Player player : server.getOnlinePlayers() )
        {
            // cancel if not op
            if ( !player.isOp() ) return;

            // send message
            player.sendMessage( msg );
        }
    }


    public static Collection<? extends Player> quickGetPlayers()
    {
        return server.getOnlinePlayers();
    }
}