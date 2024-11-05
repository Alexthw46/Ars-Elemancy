package lyrellion.ars_elemancy.common;

import lyrellion.ars_elemancy.registry.ModRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;

public class CasterHolderContainer extends CurioHolderContainer {

    public CasterHolderContainer(int windowId, Inventory playerInv, ItemStack backpack) {
        super(ModRegistry.CASTER_HOLDER.get(), windowId, playerInv, backpack.getCapability(Capabilities.ItemHandler.ITEM), backpack);
    }

    public int offset() {
        return 54;
    }


}
