package me.av306.mcvalorant.commands;

import me.av306.mcvalorant.Main;
import me.av306.mcvalorant.util.MCValorantItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandGiveWeapons implements CommandExecutor
{
    private ArrayList<ItemStack> items = new ArrayList<>();

    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args )
    {
        // cancel if sent from console
        if ( !(sender instanceof Player) ) return false;

        // inform relevant players
        sender.sendMessage( ChatColor.GREEN + "Giving MCValorant weapons to all players!" );
        Main.logConsole( sender.getName() + " gave MCValorant weapons to all players!" );
        Main.informOperator( sender.getName() + " gave MCValorant weapons to all players!" );


        // AK-47 item
        MCValorantItemStack ak47 = new MCValorantItemStack( Material.CROSSBOW );
        ak47.addUnsafeEnchantment( Enchantment.QUICK_CHARGE, 5 ); // Quick Charge 5
        // ak47.addUnsafeEnchantment( Enchantment.ARROW_DAMAGE, 32767 ); // too op
        ak47.addUnsafeEnchantment( Enchantment.MENDING, 32767 ); // proven to work
        ak47.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        ak47.addUnsafeEnchantment( Enchantment.ARROW_KNOCKBACK, 32767 );
        ak47.addUnsafeEnchantment( Enchantment.KNOCKBACK, 32767 );
        items.add( ak47 );


        // yeet fork item
        MCValorantItemStack yeetfork = new MCValorantItemStack( Material.TRIDENT );
        yeetfork.addUnsafeEnchantment( Enchantment.RIPTIDE, 3 ); // best
        yeetfork.addUnsafeEnchantment( Enchantment.MENDING, 32767 );
        yeetfork.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        items.add( yeetfork );


        // AXE OF PEACE
        MCValorantItemStack axeofpeace = new MCValorantItemStack( Material.DIAMOND_AXE );
        axeofpeace.addUnsafeEnchantment( Enchantment.KNOCKBACK, 32767 );
        axeofpeace.addUnsafeEnchantment( Enchantment.FIRE_ASPECT, 32767 );
        axeofpeace.addUnsafeEnchantment( Enchantment.MENDING, 32767 );
        axeofpeace.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        items.add( axeofpeace );


        // elytra
        MCValorantItemStack elytra = new MCValorantItemStack( Material.ELYTRA );
        elytra.addUnsafeEnchantment( Enchantment.MENDING, 32767 );
        elytra.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        items.add( elytra );


        // rpg
        MCValorantItemStack rpg = new MCValorantItemStack( Material.BOW );
        rpg.addUnsafeEnchantment( Enchantment.KNOCKBACK, 32767 );
        rpg.addUnsafeEnchantment( Enchantment.ARROW_FIRE, 32767 );
        rpg.addUnsafeEnchantment( Enchantment.ARROW_INFINITE, 32767 );
        rpg.addUnsafeEnchantment( Enchantment.ARROW_DAMAGE, 32767 );
        rpg.addUnsafeEnchantment( Enchantment.MENDING, 32767 );
        rpg.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        items.add( rpg );


        // arrows
        MCValorantItemStack arrows = new MCValorantItemStack( Material.ARROW, 512 );
        items.add( arrows );


        // fireworks
        MCValorantItemStack fireworks = new MCValorantItemStack( Material.FIREWORK_ROCKET, 512 );
        items.add( fireworks );


        // shield
        MCValorantItemStack shield = new MCValorantItemStack( Material.SHIELD );
        shield.addUnsafeEnchantment( Enchantment.MENDING, 32767 );
        shield.addUnsafeEnchantment( Enchantment.DURABILITY, 32767 );
        items.add( shield );


        // WARNING: Clears player inventory.
        for ( Player player : Main.quickGetPlayers() ) {
            Inventory inventory = player.getInventory();

            // clear inventory
            inventory.clear();

            // Add items
            for ( ItemStack item : items )
            {
                inventory.addItem( item );
            }

            player.sendMessage( Main.SUCCESS + "Successfully given weapons! Have fun!" );
            Main.logConsole( "Successfully gave " + player.getName() + " weapons." );
            Main.informOperator( Main.SUCCESS + player.getName() + " weapons." );
        }


        return true;
    }
}
