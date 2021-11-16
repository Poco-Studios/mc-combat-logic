package me.av306.mcvalorant.listener;


import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import me.av306.mcvalorant.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class PlayerPostRespawnEventListener implements Listener
{
    /**
     * Gives player spawn protection (Resistance) for 5 seconds.
     */

    public static boolean isEnabled = false;

    @EventHandler
    public void onPlayerPostRespawnEvent( PlayerPostRespawnEvent event )
    {
        if ( !isEnabled ) return;

        Player playerWhoDied = event.getPlayer();

        Main.logConsole( playerWhoDied.getName() + " has respawned!" );

        playerWhoDied.addPotionEffect( new PotionEffect( PotionEffectType.DAMAGE_RESISTANCE, 100, 255, true, false, true ) );
        playerWhoDied.addPotionEffect( new PotionEffect( PotionEffectType.GLOWING, 999999, 1, true, false, true ) );
    }
}
