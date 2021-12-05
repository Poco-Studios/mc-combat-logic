package me.av306.mcvalorant.listener;

import me.av306.mcvalorant.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;


public class PlayerDeathEventListener implements Listener
{
    /**
     * Gives player a point after killing someone.
     */

    public static boolean isEnabled = false;


    @EventHandler
    public void onPlayerDeathEvent( PlayerDeathEvent event )
    {
        Player playerWhoDied = event.getEntity();

        event.setKeepInventory( true ); // keep inventory

        Player killer = playerWhoDied.getKiller();

        // cancel if no "killer" or disabled
        if ( killer == null || !isEnabled ) return;

       Main.logConsole( playerWhoDied.getName() + " was killed by " + killer.getName() + "!" );

        // increment "killer's" score by 1
        try
        {
            int killerScore = Main.scores.get(killer);
            killerScore += 1;
            Main.scores.put(killer, killerScore);
        }
        catch ( NullPointerException npe )
        {
            // NPE can be triggered if "killer" is offline
            for ( Player player : Main.quickGetPlayers() )
            {
                player.sendMessage( Main.WARN + "Whoops! failed to increment " + killer.getName() + "'s points! Are they offline?" );
            }

            npe.printStackTrace();
        }


        for ( Player player : Main.quickGetPlayers() )
        {
            Main.logConsole( killer.getName() + " has " + Main.scores.get( killer ) + " points!" );
            player.sendMessage( Main.INFO + killer.getName() + " has " + Main.scores.get( killer ) + " points!" );
        }
    }
}
