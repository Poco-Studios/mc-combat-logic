package me.av306.mcvalorant.commands;

import me.av306.mcvalorant.commands.match.CommandPanic;
import me.av306.mcvalorant.commands.match.CommandEndMatch;
import me.av306.mcvalorant.commands.match.CommandStartMatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandMatch implements CommandExecutor
{
    /**
     * This class bunches the two match-related commands together into one.
     * The three subcommands remain separate for ease of refactoring.
     */

    @Override
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args )
    {
        if ( !(sender instanceof Player) || args.length != 1 ) return false;

        switch ( args[0].toLowerCase() )
        {
            case "start":
                new CommandStartMatch().onCommand( sender, command, label, new String[]{""} );
                break;

            case "end":
                new CommandEndMatch().onCommand( sender, command, label, new String[]{""} );
                break;

            case "panic":
                new CommandPanic().onCommand( sender, command, label, new String[]{""} );

            default:
                sender.sendMessage( "Invalid argument!" );
                return false;
        }

        return true;
    }
}
