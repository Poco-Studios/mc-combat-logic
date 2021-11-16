package me.av306.mcvalorant.commands.match;

import me.av306.mcvalorant.Main;
import me.av306.mcvalorant.listener.PlayerDeathEventListener;
import me.av306.mcvalorant.listener.PlayerJoinEventListener;
import me.av306.mcvalorant.listener.PlayerPostRespawnEventListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPanic implements CommandExecutor
{
    /**
     * Command class for "panic button"
     */

    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args )
    {
        // panic
        Main.logConsole( "PANIC" );

        // disable listeners
        PlayerDeathEventListener.isEnabled = false;
        PlayerPostRespawnEventListener.isEnabled = false;
        PlayerJoinEventListener.isEnabled = false;

        // reset hashmap & send message
        for ( Player player : Main.quickGetPlayers() )
        {
            player.sendMessage( ChatColor.RED + "Plugin is panicking and this is very bad!!!" );

            Main.scores.put( player, 0 );
        }


        return true;
    }
}
