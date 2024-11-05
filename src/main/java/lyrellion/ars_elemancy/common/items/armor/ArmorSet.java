package lyrellion.ars_elemancy.common.items.armor;

import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import static lyrellion.ars_elemancy.registry.ModItems.ArmorProp;
import static lyrellion.ars_elemancy.registry.ModItems.ITEMS;


public class ArmorSet {
    public String getName() {
        return name;
    }

    private final String name;

    public ArmorSet(String name, SpellSchool element) {
        this.name = name;
        this.head = ITEMS.register(name + "_hat", () -> new ElemancyArmor(ArmorItem.Type.HELMET, element, ArmorProp()));
        this.chest = ITEMS.register(name + "_robes", () -> new ElemancyArmor(ArmorItem.Type.CHESTPLATE, element, ArmorProp()));
        this.legs = ITEMS.register(name + "_leggings", () -> new ElemancyArmor(ArmorItem.Type.LEGGINGS, element, ArmorProp()));
        this.feet = ITEMS.register(name + "_boots", () -> new ElemancyArmor(ArmorItem.Type.BOOTS, element, ArmorProp()));
    }

    DeferredHolder<Item,ElemancyArmor> head;
    DeferredHolder<Item,ElemancyArmor> chest;
    DeferredHolder<Item,ElemancyArmor> legs;
    DeferredHolder<Item,ElemancyArmor> feet;

    public Item getHat() {
        return head.get();
    }

    public Item getChest() {
        return chest.get();
    }

    public Item getLegs() {
        return legs.get();
    }

    public Item getBoots() {
        return feet.get();
    }

    public Item getArmorFromSlot(EquipmentSlot slot) {
        return switch (slot) {
            case CHEST -> getChest();
            case LEGS -> getLegs();
            case FEET -> getBoots();
            default -> getHat();
        };
    }

    public String getTranslationKey() {
        return "ars_elemental.armor_set." + this.name;
    }
}

