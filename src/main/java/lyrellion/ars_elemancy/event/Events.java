package lyrellion.ars_elemancy.event;

import alexthw.ars_elemental.ArsElemental;
import alexthw.ars_elemental.api.item.ISchoolFocus;
import com.hollingsworth.arsnouveau.api.event.SpellCostCalcEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = ArsElemental.MODID)
public class Events {

    public static final String TAG_SOULBOUND_DROP_COUNT = "tagSoulboundDC";
    public static final String TAG_SOULBOUND_PREFIX = "tagSoulboundPrefix";
    public static final String TAG_SOULBOUND = "tagSoulbound";


    @SubscribeEvent
    public static void focusDiscount(SpellCostCalcEvent event) {
        var caster = event.context.getUnwrappedCaster();
        if (!caster.level().isClientSide() && caster instanceof Player player) {
            //if the player is holding a focus, and the spell match the focus's school, apply the focus discount.
            var focus = ISchoolFocus.getFocus(player);
            if (focus != null && event.context.getSpell().unsafeList().stream().anyMatch(focus.getSchool()::isPartOfSchool))
                event.currentCost = (int) (event.currentCost - focus.getDiscount() * event.context.getSpell().getCost());
        }
    }


}