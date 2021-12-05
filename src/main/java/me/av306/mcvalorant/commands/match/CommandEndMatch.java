package me.av306.mcvalorant.commands.match;

import me.av306.mcvalorant.Main;
import me.av306.mcvalorant.listener.PlayerDeathEventListener;
import me.av306.mcvalorant.listener.PlayerJoinEventListener;
import me.av306.mcvalorant.listener.PlayerPostRespawnEventListener;
import me.av306.mcvalorant.util.MCValorantItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.data.type.Fire;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class CommandEndMatch implements CommandExecutor
{
    /**
     * Command to end the match. No longer called directly, now called through CommandMatch.
     */

    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args )
    {
        Main.logConsole( sender.getName() + " ended the match!" );

        // disable listeners
        PlayerDeathEventListener.isEnabled = false;
        PlayerPostRespawnEventListener.isEnabled = false;
        PlayerJoinEventListener.isEnabled = false;


        // announce && count scores
        int highestScore = -1;
        Player winner = null;
        for ( Player player : Main.quickGetPlayers() )
        {
            player.addPotionEffect( new PotionEffect( PotionEffectType.DAMAGE_RESISTANCE, 999999, 255, true, false, true ) );

            // Won't be seen due to the fix where highestScore was set to -1 to resolve the NullPointerException (unless something bad happens)
            player.sendTitle( Main.ERROR + "Oh no!", Main.ERROR + "If you're seeing this, something bad happened!", 10, 500, 10 );

            Main.logConsole( "Trying to get " + player.getName() + "'s score." );
            int currentScore = Main.scores.get( player ); // May produce error, hopefully fixed in PlayerPostRespawnEventListener

            if ( currentScore > highestScore )
            {
                highestScore = currentScore;
                winner = player;
            }
        }


        // announce winner
        for ( Player player : Main.quickGetPlayers() )
        {
            // fireworks!
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 100f, 1f );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 100f, 1f );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 100f, 1f );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR, 100f, 1f );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 100f, 1f );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 100f, 1f );
            player.playSound( player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 100f, 1f );

            player.sendMessage( Main.SUCCESS + winner.getName() + " is the winner of this round with a score of " + highestScore + "!" );
            player.sendTitle( Main.SUCCESS + winner.getName() + " wins this round!", ":D", 10, 40, 10 );

            try {
                for (int i = 0; i < 10; i++) {
                    // spawn 10 fireworks around winner
                    World world = winner.getWorld();
                    Firework firework = (Firework) world.spawnEntity( winner.getLocation(), EntityType.FIREWORK );
                    firework.setVisualFire( true );
                    firework.setGlowing( true );
                    firework.setVelocity( new Vector(0, 10, 0) );
                }

                MCValorantItemStack dia = new MCValorantItemStack( Material.DIAMOND, 10 );
                winner.getInventory().addItem( dia );
            }
            catch ( NullPointerException npe )
            {
                Main.informOperator( Main.ERROR + "Whoops! Unable to reward winner! Did they log off?" ); ;
            }
        }


        return true;
    }
}
