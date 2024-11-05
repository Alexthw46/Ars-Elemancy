package lyrellion.ars_elemancy.api.item;

import lyrellion.ars_elemancy.util.CompatUtils;
import com.hollingsworth.arsnouveau.api.item.ISpellModifierItem;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nullable;

public interface ISchoolFocus extends ISpellModifierItem, ISchoolProvider {
    static @Nullable SpellSchool hasFocus(Entity entity) {
        if (entity instanceof ISchoolProvider mage) return mage.getSchool();
        else if (entity instanceof Player player) {
            var focus = getFocus(player);
            if (focus != null) return focus.getSchool();
        }
        return null;
    }

    static ISchoolFocus getFocus(@NotNull Player player) {
        //check the player's hands and curios for a focus and return the school if found
        for (InteractionHand curHand : InteractionHand.values()) {
            Item hand = player.getItemInHand(curHand).getItem();
            if (hand instanceof ISchoolFocus focus) {
                return focus;
            }
        }
        SlotResult curio = CompatUtils.getCurio(player, c -> (c.getItem() instanceof ISchoolFocus));
        if (!curio.stack().isEmpty() && curio.stack().getItem() instanceof ISchoolFocus focus) {
            return focus;
        }
        return null;
    }

    double getDiscount();


}
