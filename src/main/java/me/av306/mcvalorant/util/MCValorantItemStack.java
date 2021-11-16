package me.av306.mcvalorant.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MCValorantItemStack extends ItemStack
{
    // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

    public MCValorantItemStack()
    {
        super();
    }


    public MCValorantItemStack( @NotNull final Material type )
    {
        super( type );
    }


    public MCValorantItemStack( @NotNull final Material type, final int amount )
    {
        super( type, amount, (short) 0 );
    }


    @Deprecated
    public MCValorantItemStack( @NotNull final Material type, final int amount, final short damage )
    {
        super( type, amount, damage );
    }


    @Deprecated
    public MCValorantItemStack( @NotNull final Material type, final int amount, final short damage, @Nullable final Byte data )
    {
        super( type, amount, damage, data );
    }


    public MCValorantItemStack( ItemStack stack ) throws IllegalArgumentException
    {
        super( stack );
    }
}
