package lyrellion.ars_elemancy.common.items.armor;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.api.item.IElemancyArmor;
import lyrellion.ars_elemancy.client.TooltipUtils;
import lyrellion.ars_elemancy.client.armor.ElemancyArmorModel;
import lyrellion.ars_elemancy.client.armor.ElemancyArmorRenderer;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.mana.IManaDiscountEquipment;
import com.hollingsworth.arsnouveau.api.perk.IPerk;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.perk.PerkInstance;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import com.hollingsworth.arsnouveau.common.armor.AnimatedMagicArmor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static lyrellion.ars_elemancy.ConfigHandler.Common.ARMOR_MANA_REGEN;
import static lyrellion.ars_elemancy.ConfigHandler.Common.ARMOR_MAX_MANA;


public class ElemancyArmor extends AnimatedMagicArmor implements IElemancyArmor, IManaDiscountEquipment {

    final SpellSchool element;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ElemancyArmor(ArmorItem.Type slot, SpellSchool element, Properties builder) {
        super(IElemancyArmor.schoolToMaterial(element), slot, new ElemancyArmorModel("medium_armor_e").withEmptyAnim());
        this.element = element;
    }

    @Override
    public int getMinTier() {
        return 2;
    }

    public SpellSchool getSchool() {
        return element;
    }

    public String getTier() {
        return "medium";
    }

    @Override
    public int getManaDiscount(ItemStack i, Spell spell) {
        return Mth.ceil(getDiscount(spell.unsafeList()));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flags) {
        var perkProvider = PerkUtil.getPerkHolder(stack);
        if (perkProvider != null) {
            tooltip.add(Component.translatable("ars_nouveau.tier", 5).withStyle(ChatFormatting.GOLD));
            perkProvider.appendPerkTooltip(tooltip, stack);
        }
        TooltipUtils.addOnShift(tooltip, () -> addInformationAfterShift(stack, context, tooltip, flags), "armor_set");
    }

    EquipmentSlot[] OrderedSlots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    @OnlyIn(Dist.CLIENT)
    public void addInformationAfterShift(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        Player player = ArsNouveau.proxy.getPlayer();
        if (player != null) {
            ArmorSet set = getArmorSetFromElement(this.element);
            List<Component> equippedList = new ArrayList<>();
            //check if the player have all the armor pieces of the set. Color the text green if they do, gray if they don't
            int equippedCounter = 0;
            for (EquipmentSlot slot : OrderedSlots) {
                Item armor = set.getArmorFromSlot(slot);
                MutableComponent cmp = Component.literal(" - ").append(armor.getDefaultInstance().getHoverName());
                if (hasArmorSetItem(player.getItemBySlot(slot), armor)) {
                    cmp.withStyle(ChatFormatting.GREEN);
                    equippedCounter++;
                } else cmp.withStyle(ChatFormatting.GRAY);

                equippedList.add(cmp);
            }
            //add the tooltip for the armor set and the list of equipped armor pieces, then add the description
            list.add(getArmorSetTitle(set, equippedCounter));
            list.addAll(equippedList);
            addArmorSetDescription(set, list);
        }
    }

    private boolean hasArmorSetItem(ItemStack armor, Item armorFromSlot) {
        return armor.getItem() == armorFromSlot;
    }

    ArmorSet getArmorSetFromElement(SpellSchool element) {
        return switch (element.getId()) {
            case "tempest" -> ModItems.TEMPEST_ARMOR;
            case "silt" -> ModItems.SILT_ARMOR;
            case "mire" -> ModItems.MIRE_ARMOR;
            case "vapor" -> ModItems.VAPOR_ARMOR;
            case "lava" -> ModItems.LAVA_ARMOR;
            case "cinder" -> ModItems.CINDER_ARMOR;
            case "elemancer" -> ModItems.ELEMANCER_ARMOR;
            default -> null;
        };
    }

    private Component getArmorSetTitle(ArmorSet set, int equipped) {
        return Component.translatable(set.getTranslationKey()).append(" (" + equipped + " / 4)").withStyle(ChatFormatting.DARK_AQUA);
    }

    public void addArmorSetDescription(ArmorSet set, List<Component> list) {
        list.add(Component.translatable("ars_elemancy.armor_set." + set.getName() + ".desc").withStyle(ChatFormatting.GRAY));
    }


    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(@NotNull ItemStack stack) {
        var modifiers = super.getDefaultAttributeModifiers()
                .withModifierAdded(PerkAttributes.MAX_MANA, new AttributeModifier(ArsNouveau.prefix("max_mana_armor_" + this.type.getName()), ARMOR_MAX_MANA.get(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                .withModifierAdded(PerkAttributes.MANA_REGEN_BONUS, new AttributeModifier(ArsNouveau.prefix("mana_regen_armor_" + this.type.getName()), ARMOR_MANA_REGEN.get(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));

        for (PerkInstance perkInstance : PerkUtil.getPerksFromItem(stack)) {
            IPerk perk = perkInstance.getPerk();
            modifiers = perk.applyAttributeModifiers(modifiers, stack, perkInstance.getSlot().value(), EquipmentSlotGroup.bySlot(this.type.getSlot()));
        }

        return modifiers;
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            public <T extends LivingEntity> @NotNull HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null) this.renderer = new ElemancyArmorRenderer(getArmorModel());
                return this.renderer;
            }
        });
    }

    /*
     * Needed to avoid file not found errors since Geckolib doesn't redirect to the correct texture
     */
    @Override
    public @Nullable ResourceLocation getArmorTexture(@NotNull ItemStack stack, @NotNull Entity entity, @NotNull EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(ArsElemancy.MODID, "textures/armor/" + getTier() + "_armor_" + this.getSchool().getId() + ".png");
    }
}
