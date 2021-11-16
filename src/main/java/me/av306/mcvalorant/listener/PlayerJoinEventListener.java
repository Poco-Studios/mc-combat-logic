package me.av306.mcvalorant.listener;


import me.av306.mcvalorant.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinEventListener implements Listener
{
    /**
     * Fingers crossed, this should fix a lot of bugs.
     */

    public static boolean isEnabled = false;

    @EventHandler
    public void onPlayerJoinEvent( PlayerJoinEvent event )
    {
        Player player = event.getPlayer();

        if ( player == null || !isEnabled ) return;

        // when you join, it is guaranteed that your score is 0.
        Main.scores.put( player, 0 );

        player.sendMessage( ChatColor.RED + "To avoid errors, your score was reset to 0." );
    }
}
