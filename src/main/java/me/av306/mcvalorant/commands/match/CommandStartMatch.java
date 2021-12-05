package me.av306.mcvalorant.commands.match;

import me.av306.mcvalorant.Main;
import me.av306.mcvalorant.listener.PlayerDeathEventListener;
import me.av306.mcvalorant.listener.PlayerJoinEventListener;
import me.av306.mcvalorant.listener.PlayerPostRespawnEventListener;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class CommandStartMatch implements CommandExecutor
{
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args )
    {
        // Log
        Main.logConsole( sender.getName() + " started the match!" );

        // enable listeners
        PlayerDeathEventListener.isEnabled = true;
        PlayerPostRespawnEventListener.isEnabled = true;
        PlayerJoinEventListener.isEnabled = true;

        // reset HashMap && setup
        for ( Player player : Main.quickGetPlayers() )
        {
            Main.scores.put( player, 0 );

            player.removePotionEffect( PotionEffectType.DAMAGE_RESISTANCE ); // remove invincibility if any
            player.addPotionEffect( new PotionEffect( PotionEffectType.GLOWING, 999999, 1, true, false, true ) );
            player.playSound( player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 10f, 1f );
            player.sendTitle( Main.SUCCESS + "Match starting!", "Have fun!", 10, 20, 10 );
        }


        return true;
    }
}
