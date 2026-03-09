package lyrellion.ars_elemancy.common.items.armor;

import alexthw.ars_elemental.ArsElemental;
import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import com.hollingsworth.arsnouveau.setup.registry.MaterialRegistry;
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
import net.minecraft.core.Holder;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static lyrellion.ars_elemancy.ConfigHandler.Common.ARMOR_MANA_REGEN;
import static lyrellion.ars_elemancy.ConfigHandler.Common.ARMOR_MAX_MANA;


public class ElemancyArmor extends AnimatedMagicArmor implements IElemancyArmor, IManaDiscountEquipment {

    final SpellSchool element;

    static Map<String, Holder<ArmorMaterial>> SCHOOL_TO_MATERIAL = new ConcurrentHashMap<>() {{
        put("tempest_heavy", AAMaterials.h_tempest);
        put("silt_heavy", AAMaterials.h_silt);
        put("vapor_heavy", AAMaterials.h_vapor);
        put("mire_heavy", AAMaterials.h_mire);
        put("cinder_heavy", AAMaterials.h_cinder);
        put("lava_heavy", AAMaterials.h_lava);
        put("elemental_heavy", AAMaterials.h_elemental);
        put("tempest", AAMaterials.tempest);
        put("silt", AAMaterials.silt);
        put("lava", AAMaterials.lava);
        put("vapor", AAMaterials.vapor);
        put("mire", AAMaterials.mire);
        put("cinder", AAMaterials.cinder);
        put("elemental", AAMaterials.elemental);
        put("tempest_light", AAMaterials.l_tempest);
        put("silt_light", AAMaterials.l_silt);
        put("lava_light", AAMaterials.l_lava);
        put("vapor_light", AAMaterials.l_vapor);
        put("mire_light", AAMaterials.l_mire);
        put("cinder_light", AAMaterials.l_cinder);
        put("elemental_light", AAMaterials.l_elemental);
    }};

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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ElemancyArmor(ArmorItem.Type slot, SpellSchool element, Holder<ArmorMaterial> material, Properties builder, String modelName) {
        super(material, slot, builder, new ElemancyArmorModel(modelName).withEmptyAnim());
        this.element = element;
    }

    private boolean hasArmorSetItem(ItemStack armor, Item armorFromSlot) {
        return armor.getItem() == armorFromSlot;
    }

    public static ArmorSet getArmorSetFromElement(SpellSchool school, String tier) {
        return switch (tier) {
            case "light" -> switch (school.getId()) {
                case "l_tempest" -> ModItems.TEMPEST_ARMOR_L;
                case "l_silt" -> ModItems.SILT_ARMOR_L;
                case "l_mire" -> ModItems.MIRE_ARMOR_L;
                case "l_vapor" -> ModItems.VAPOR_ARMOR_L;
                case "l_lava" -> ModItems.LAVA_ARMOR_L;
                case "l_cinder" -> ModItems.CINDER_ARMOR_L;
                case "l_elemental" -> ModItems.ELEMANCER_ARMOR_L;
                default -> new ArmorSet.Light("l_elemental", SpellSchools.ELEMENTAL);
            };
            case "heavy" -> switch (school.getId()) {
                case "h_tempest" -> ModItems.TEMPEST_ARMOR_H;
                case "h_silt" -> ModItems.SILT_ARMOR_H;
                case "h_mire" -> ModItems.MIRE_ARMOR_H;
                case "h_vapor" -> ModItems.VAPOR_ARMOR_H;
                case "h_lava" -> ModItems.LAVA_ARMOR_H;
                case "h_cinder" -> ModItems.CINDER_ARMOR_H;
                case "h_elemental" -> ModItems.ELEMANCER_ARMOR_H;
                default -> new ArmorSet.Heavy("h_elemental", SpellSchools.ELEMENTAL);
            };
            default -> switch (school.getId()) {
                case "tempest" -> ModItems.TEMPEST_ARMOR;
                case "silt" -> ModItems.SILT_ARMOR;
                case "mire" -> ModItems.MIRE_ARMOR;
                case "vapor" -> ModItems.VAPOR_ARMOR;
                case "lava" -> ModItems.LAVA_ARMOR;
                case "cinder" -> ModItems.CINDER_ARMOR;
                case "elemental" -> ModItems.ELEMANCER_ARMOR;
                default -> new ArmorSet.Medium("elemental", SpellSchools.ELEMENTAL);
            };
        };
    }

    static Holder<ArmorMaterial> schoolToMaterial(String key) {
        return SCHOOL_TO_MATERIAL.getOrDefault(key, MaterialRegistry.MEDIUM);
    }

    private Component getArmorSetTitle(ArmorSet set, int equipped) {
        return Component.translatable(set.getTranslationKey()).append(" (" + equipped + " / 4)").withStyle(ChatFormatting.DARK_AQUA);
    }

    public void addArmorSetDescription(ArmorSet set, List<Component> list) {
        list.add(Component.translatable("ars_elemental.armor_set." + set.getName() + ".desc").withStyle(ChatFormatting.GRAY));
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

    @OnlyIn(Dist.CLIENT)
    public void addInformationAfterShift(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        Player player = ArsNouveau.proxy.getPlayer();
        if (player != null) {
            ArmorSet set = getArmorSetFromElement(this.element, getTier());
            List<Component> equippedList = new ArrayList<>();
            //check if the player have all the armor pieces of the set. Color the text green if they do, gray if they don't
            int equippedCounter = 0;
            for (EquipmentSlot slot : OrderedSlots) {
                Item armor = set.getArmorFromSlot(slot);
                MutableComponent cmp = Component.literal(" - ").append(armor.getDefaultInstance().getHoverName());
                if (player.getItemBySlot(slot).getItem() == armor) {
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

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(@NotNull ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack)
                .withModifierAdded(PerkAttributes.MAX_MANA, new AttributeModifier(ArsNouveau.prefix("max_mana_armor_" + this.type.getName()), ARMOR_MAX_MANA.get(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                .withModifierAdded(PerkAttributes.MANA_REGEN_BONUS, new AttributeModifier(ArsNouveau.prefix("mana_regen_armor_" + this.type.getName()), ARMOR_MANA_REGEN.get(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));
    }

    /*
     * Needed to avoid file not found errors since Geckolib doesn't redirect to the correct texture
     */
    @Override
    public @Nullable ResourceLocation getArmorTexture(@NotNull ItemStack stack, @NotNull Entity entity, @NotNull EquipmentSlot slot, ArmorMaterial.@NotNull Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(ArsElemancy.MODID, "textures/armor/" + getTier() + "_armor_" + this.getSchool().getId() + ".png");
    }
}