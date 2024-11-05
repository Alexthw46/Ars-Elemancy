package lyrellion.ars_elemancy.registry;

import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import lyrellion.ars_elemancy.ArsNouveauRegistry;
import lyrellion.ars_elemancy.common.items.*;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.common.items.bangles.*;
import lyrellion.ars_elemancy.common.items.caster_tools.ElementalCasterTome;
import lyrellion.ars_elemancy.common.items.caster_tools.SpellHorn;
import lyrellion.ars_elemancy.common.items.foci.ElementalFocus;
import lyrellion.ars_elemancy.common.items.foci.GreaterElementalFocus;
import lyrellion.ars_elemancy.common.items.foci.NecroticFocus;
import lyrellion.ars_elemancy.datagen.AETagsProvider;
import lyrellion.ars_elemancy.world.ModWorldgen;
import com.hollingsworth.arsnouveau.common.block.ArchfruitPod;
import com.hollingsworth.arsnouveau.common.block.MagicLeaves;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import com.hollingsworth.arsnouveau.common.world.tree.MagicTree;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;
import static lyrellion.ars_elemancy.registry.ModPotions.LIGHTNING_LURE;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.LOG_PROP;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.SAP_PROP;

@SuppressWarnings("SameParameterValue")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredHolder<Block, ? extends Block> FLASHING_ARCHWOOD_LOG;
    public static final DeferredHolder<Block, ? extends Block> FLASHING_ARCHWOOD_LOG_STRIPPED;
    public static final DeferredHolder<Block, ? extends Block> FLASHING_ARCHWOOD_STRIPPED;
    public static final DeferredHolder<Block, ? extends Block> FLASHING_ARCHWOOD;
    public static final DeferredHolder<Block, ? extends Block> FLASHING_SAPLING;
    public static final DeferredHolder<Block, ? extends Block> FLASHING_LEAVES;
    public static final DeferredHolder<Block, ArchfruitPod> FLASHING_POD;
    public static final DeferredHolder<Block, FlowerPotBlock> POT_FLASHING_SAPLING;

    public static final DeferredHolder<Item, GreaterElementalFocus> FIRE_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> AIR_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> WATER_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> EARTH_FOCUS;
    public static final DeferredHolder<Item, NecroticFocus> NECRO_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> TEMPEST_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> SILT_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> MIRE_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> LAVA_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> CINDER_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> VAPOR_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> ELEMANCER_FOCUS;

    public static final DeferredHolder<Item, ElementalFocus> LESSER_FIRE_FOCUS;
    public static final DeferredHolder<Item, ElementalFocus> LESSER_AIR_FOCUS;
    public static final DeferredHolder<Item, ElementalFocus> LESSER_WATER_FOCUS;
    public static final DeferredHolder<Item, ElementalFocus> LESSER_EARTH_FOCUS;

    public static final ArmorSet FIRE_ARMOR = new ArmorSet("fire", SpellSchools.ELEMENTAL_FIRE);
    public static final ArmorSet AIR_ARMOR = new ArmorSet("air", SpellSchools.ELEMENTAL_AIR);
    public static final ArmorSet EARTH_ARMOR = new ArmorSet("earth", SpellSchools.ELEMENTAL_EARTH);
    public static final ArmorSet WATER_ARMOR = new ArmorSet("aqua", SpellSchools.ELEMENTAL_WATER);
    public static final ArmorSet TEMPEST_ARMOR = new ArmorSet("tempest", ArsNouveauRegistry.TEMPEST);
    public static final ArmorSet MIRE_ARMOR = new ArmorSet("mire", ArsNouveauRegistry.MIRE);
    public static final ArmorSet SILT_ARMOR = new ArmorSet("silt", ArsNouveauRegistry.SILT);
    public static final ArmorSet LAVA_ARMOR = new ArmorSet("lava", ArsNouveauRegistry.LAVA);
    public static final ArmorSet VAPOR_ARMOR = new ArmorSet("vapor", ArsNouveauRegistry.VAPOR);
    public static final ArmorSet CINDER_ARMOR = new ArmorSet("cinder", ArsNouveauRegistry.CINDER);
    public static final ArmorSet ELEMANCER_ARMOR = new ArmorSet("elemancer", SpellSchools.ELEMENTAL);

    public static final DeferredHolder<Item, ElementalCasterTome> FIRE_CTOME;
    public static final DeferredHolder<Item, ElementalCasterTome> AIR_CTOME;
    public static final DeferredHolder<Item, ElementalCasterTome> WATER_CTOME;
    public static final DeferredHolder<Item, ElementalCasterTome> EARTH_CTOME;
    public static final DeferredHolder<Item, ElementalCasterTome> NECRO_CTOME;
    public static final DeferredHolder<Item, ElementalCasterTome> SHAPERS_CTOME;


    public static final DeferredHolder<Item, BaseBangle> ENCHANTER_BANGLE;
    public static final DeferredHolder<Item, FireBangles> FIRE_BANGLE;
    public static final DeferredHolder<Item, WaterBangles> WATER_BANGLE;
    public static final DeferredHolder<Item, AirBangles> AIR_BANGLE;
    public static final DeferredHolder<Item, EarthBangles> EARTH_BANGLE;
    public static final DeferredHolder<Item, SummonBangles> SUMMON_BANGLE;
    public static final DeferredHolder<Item, AnimaBangles> ANIMA_BANGLE;

    public static final DeferredHolder<Item, CurioHolder> CURIO_BAG;
    public static final DeferredHolder<Item, CasterHolder> CASTER_BAG;

    public static final DeferredHolder<Item, Debugger> DEBUG_ICON;
    public static final DeferredItem<Item> MARK_OF_MASTERY;

    public static final DeferredHolder<Item, NecroEssence> ANIMA_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> TEMPEST_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> SILT_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> MIRE_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> LAVA_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> CINDER_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> VAPOR_ESSENCE;

    public static final DeferredHolder<Item, SpellHorn> SPELL_HORN;
    public static FoodProperties FLASHPINE_FOOD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 30 * 20), .4f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 30 * 20), .4f)
            .effect(() -> new MobEffectInstance(ModPotions.SHOCKED_EFFECT, 30 * 20, 0), .8f)
            .effect(() -> new MobEffectInstance(LIGHTNING_LURE, 30 * 20, 0), .2f)
            .alwaysEdible().build();

    static {

        DEBUG_ICON = ITEMS.register("debug", () -> new Debugger(new Item.Properties()));
        MARK_OF_MASTERY = (DeferredItem<Item>) ITEMS.register("mark_of_mastery", () -> new Item(itemProps()));
        ANIMA_ESSENCE = ITEMS.register("anima_essence", () -> new NecroEssence(itemProps()));
        TEMPEST_ESSENCE = ITEMS.register("tempest_essence", () -> new FusedEssence(itemProps()));
        SILT_ESSENCE = ITEMS.register("silt_essence", () -> new FusedEssence(itemProps()));
        MIRE_ESSENCE = ITEMS.register("mire_essence", () -> new FusedEssence(itemProps()));
        VAPOR_ESSENCE = ITEMS.register("vapor_essence", () -> new FusedEssence(itemProps()));
        CINDER_ESSENCE = ITEMS.register("cinder_essence", () -> new FusedEssence(itemProps()));
        LAVA_ESSENCE = ITEMS.register("lava_essence", () -> new FusedEssence(itemProps()));

        SPELL_HORN = ITEMS.register("spell_horn", () -> new SpellHorn(itemProps()));

        //curio
        CURIO_BAG = ITEMS.register("curio_bag", () -> new CurioHolder(itemProps().fireResistant().stacksTo(1)));
        CASTER_BAG = ITEMS.register("caster_bag", () -> new CasterHolder(itemProps().fireResistant().stacksTo(1).component(DataComponents.BASE_COLOR, DyeColor.RED)));

        FIRE_FOCUS = ITEMS.register("fire_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL_FIRE));
        WATER_FOCUS = ITEMS.register("water_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL_WATER));
        AIR_FOCUS = ITEMS.register("air_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL_AIR));
        EARTH_FOCUS = ITEMS.register("earth_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL_EARTH));
        TEMPEST_FOCUS = ITEMS.register("tempest_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.TEMPEST));
        MIRE_FOCUS = ITEMS.register("mire_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.MIRE));
        VAPOR_FOCUS = ITEMS.register("vapor_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.VAPOR));
        SILT_FOCUS = ITEMS.register("silt_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.SILT));
        LAVA_FOCUS = ITEMS.register("lava_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.LAVA));
        CINDER_FOCUS = ITEMS.register("cinder_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.CINDER));
        ELEMANCER_FOCUS = ITEMS.register("elemancer_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL));
        NECRO_FOCUS = ITEMS.register("necrotic_focus", () -> new NecroticFocus(FocusProp()));

        LESSER_FIRE_FOCUS = ITEMS.register("lesser_fire_focus", () -> new ElementalFocus(UncommonProp(), SpellSchools.ELEMENTAL_FIRE));
        LESSER_WATER_FOCUS = ITEMS.register("lesser_water_focus", () -> new ElementalFocus(UncommonProp(), SpellSchools.ELEMENTAL_WATER));
        LESSER_AIR_FOCUS = ITEMS.register("lesser_air_focus", () -> new ElementalFocus(UncommonProp(), SpellSchools.ELEMENTAL_AIR));
        LESSER_EARTH_FOCUS = ITEMS.register("lesser_earth_focus", () -> new ElementalFocus(UncommonProp(), SpellSchools.ELEMENTAL_EARTH));

        //bangles
        ENCHANTER_BANGLE = ITEMS.register("base_bangle", () -> new BaseBangle(itemProps().stacksTo(1)));
        FIRE_BANGLE = ITEMS.register("fire_bangle", () -> new FireBangles(UncommonProp()));
        WATER_BANGLE = ITEMS.register("water_bangle", () -> new WaterBangles(UncommonProp()));
        AIR_BANGLE = ITEMS.register("air_bangle", () -> new AirBangles(UncommonProp()));
        EARTH_BANGLE = ITEMS.register("earth_bangle", () -> new EarthBangles(UncommonProp()));
        SUMMON_BANGLE = ITEMS.register("summon_bangle", () -> new SummonBangles(UncommonProp()));
        ANIMA_BANGLE = ITEMS.register("anima_bangle", () -> new AnimaBangles(UncommonProp()));

        //caster tomes
        FIRE_CTOME = ITEMS.register("fire_caster_tome", () -> new ElementalCasterTome(itemProps(), SpellSchools.ELEMENTAL_FIRE));
        WATER_CTOME = ITEMS.register("water_caster_tome", () -> new ElementalCasterTome(itemProps(), SpellSchools.ELEMENTAL_WATER));
        AIR_CTOME = ITEMS.register("air_caster_tome", () -> new ElementalCasterTome(itemProps(), SpellSchools.ELEMENTAL_AIR));
        EARTH_CTOME = ITEMS.register("earth_caster_tome", () -> new ElementalCasterTome(itemProps(), SpellSchools.ELEMENTAL_EARTH));
        NECRO_CTOME = ITEMS.register("anima_caster_tome", () -> new ElementalCasterTome(itemProps(), ArsNouveauRegistry.NECROMANCY));
        SHAPERS_CTOME = ITEMS.register("manipulation_caster_tome", () -> new ElementalCasterTome(itemProps(), SpellSchools.MANIPULATION));


        //Trees
        FLASHING_SAPLING = addBlock("yellow_archwood_sapling", () -> new SaplingBlock(MagicTree.getGrower("flashing_tree", ModWorldgen.FLASHING_TREE_SAPLING), SAP_PROP));
        FLASHING_LEAVES = addBlock("yellow_archwood_leaves", () -> new MagicLeaves(blockProps(Blocks.OAK_LEAVES, MapColor.COLOR_YELLOW).lightLevel(b -> 8).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(
                ModItems::allowsSpawnOnLeaves).isSuffocating(ModItems::isntSolid).isViewBlocking(ModItems::isntSolid)));
        FLASHING_ARCHWOOD_LOG_STRIPPED = addBlock("stripped_yellow_archwood_log", () -> new RotatedPillarBlock(LOG_PROP.mapColor(MapColor.COLOR_YELLOW).lightLevel(b -> 6)));
        FLASHING_ARCHWOOD_STRIPPED = addBlock("stripped_yellow_archwood", () -> new RotatedPillarBlock(LOG_PROP.mapColor(MapColor.COLOR_YELLOW).lightLevel(b -> 6)));
        FLASHING_ARCHWOOD_LOG = addBlock("yellow_archwood_log", () -> new StrippableLog(LOG_PROP.mapColor(MapColor.COLOR_YELLOW).lightLevel(b -> 8), FLASHING_ARCHWOOD_LOG_STRIPPED::get));
        FLASHING_ARCHWOOD = addBlock("yellow_archwood", () -> new StrippableLog(LOG_PROP.mapColor(MapColor.COLOR_YELLOW).lightLevel(b -> 8), FLASHING_ARCHWOOD_STRIPPED::get));
        FLASHING_POD = BLOCKS.register("flashpine_pod", () -> new ArchfruitPod(AETagsProvider.AEBlockTagsProvider.FLASHING_LOGS));
        ITEMS.register("flashpine_pod", () -> new ItemNameBlockItem(FLASHING_POD.get(), itemProps().food(FLASHPINE_FOOD)));
        POT_FLASHING_SAPLING = BLOCKS.register("potted_yellow_archwood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FLASHING_SAPLING, blockProps(Blocks.FLOWER_POT, MapColor.COLOR_YELLOW).instabreak().noOcclusion()));
    }

    static Item.Properties itemProps() {
        return new Item.Properties();
    }

    static Item.Properties FocusProp() {
        return itemProps().stacksTo(1).fireResistant().rarity(Rarity.EPIC);
    }

    static Item.Properties UncommonProp() {
        return itemProps().stacksTo(1).rarity(Rarity.UNCOMMON);
    }

    public static Item.Properties ArmorProp() {
        return itemProps().rarity(Rarity.EPIC);
    }

    static DeferredHolder<Block, ? extends Block> addBlock(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = BLOCKS.register(name, blockSupp);
        ITEMS.register(name, () -> new BlockItem(block.get(), itemProps()));
        return block;
    }


    static BlockBehaviour.Properties blockProps(Block copyFrom, MapColor color) {
        return BlockBehaviour.Properties.ofFullCopy(copyFrom).mapColor(color);
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

}
