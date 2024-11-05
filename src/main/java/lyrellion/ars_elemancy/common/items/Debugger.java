package lyrellion.ars_elemancy.common.items;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.ArsNouveauRegistry;
import lyrellion.ars_elemancy.common.items.foci.ElementalFocus;
import lyrellion.ars_elemancy.registry.ModPotions;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.hollingsworth.arsnouveau.api.spell.SpellSchools.*;

public class Debugger extends ElementalFocus {

    private final List<SpellSchool> elements = List.of(ELEMENTAL_AIR, ELEMENTAL_FIRE, ELEMENTAL_EARTH, ELEMENTAL_WATER);
    private int index;

    public Debugger(Properties properties) {
        super(properties, ArsNouveauRegistry.NECROMANCY);
        index = 0;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
//        var tag = pPlayer.getItemInHand(pUsedHand).getOrCreateTag();
//        if (pPlayer.isCrouching() && !pLevel.isClientSide()) {
//            index = ++index % 4;
//            tag.putInt("element", index);
//        }
//        this.element = elements.get(tag.getInt("element"));
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, Player player, @NotNull Entity entity) {
        if (player.getUUID().equals(ArsElemancy.Dev) && entity instanceof Player target)
            target.addEffect(new MobEffectInstance(ModPotions.HYMN_OF_ORDER, 6400));
        return super.onLeftClickEntity(stack, player, entity);
    }


    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext context, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, context, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(this.element.getTextComponent());
    }
}
