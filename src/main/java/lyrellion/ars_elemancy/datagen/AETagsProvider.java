package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.CompletableFuture;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

public class AETagsProvider {

    public static class AEItemTagsProvider extends ItemTagsProvider {

        String[] curioSlots = {"curio", "back", "belt", "body", "bracelet", "charm", "feet", "head", "hands", "necklace", "ring"};

        static TagKey<Item> curiosTag(String key) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CuriosApi.MODID, key));
        }

        public static final TagKey<Item> CURIO_SPELL_FOCUS = curiosTag("an_focus");
        public static final TagKey<Item> CURIO_BANGLE = curiosTag("bracelet");
        public static final TagKey<Item> SUMMON_SHARDS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "magic_shards"));
        public static final TagKey<Item> SPELLBOOK = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "spellbook"));
        public static final TagKey<Item> PRISM_LENS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "spell_prism_lens"));

        public static final TagKey<Item> MAGIC_HOOD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "hood"));
        public static final TagKey<Item> MAGIC_ROBE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "robe"));
        public static final TagKey<Item> MAGIC_LEG = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "legs"));
        public static final TagKey<Item> MAGIC_BOOT = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "boot"));

        // create log compat
        public static final TagKey<Item> STRIPPED_LOGS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "stripped_logs"));
        public static final TagKey<Item> STRIPPED_WOODS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "stripped_wood"));


        public AEItemTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(gen.getPackOutput(), provider, blockTagsProvider.contentsGetter(), ArsElemancy.MODID, existingFileHelper);
        }


        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(CURIO_SPELL_FOCUS).add(ModItems.TEMPEST_FOCUS.get()); //TODO add the other
            tag(CURIO_BANGLE);
            tag(MAGIC_HOOD).add(ItemsRegistry.BATTLEMAGE_HOOD.get(), ItemsRegistry.ARCANIST_HOOD.get(), ItemsRegistry.SORCERER_HOOD.get());
            tag(MAGIC_ROBE).add(ItemsRegistry.BATTLEMAGE_ROBES.get(), ItemsRegistry.ARCANIST_ROBES.get(), ItemsRegistry.SORCERER_ROBES.get());
            tag(MAGIC_LEG).add(ItemsRegistry.BATTLEMAGE_LEGGINGS.get(), ItemsRegistry.ARCANIST_LEGGINGS.get(), ItemsRegistry.SORCERER_LEGGINGS.get());
            tag(MAGIC_BOOT).add(ItemsRegistry.BATTLEMAGE_BOOTS.get(), ItemsRegistry.ARCANIST_BOOTS.get(), ItemsRegistry.SORCERER_BOOTS.get());
        }

        public void addArmorTags(ArmorSet set) {
            tag(ItemTags.ARMOR_ENCHANTABLE).add(set.getHat(), set.getChest(), set.getLegs(), set.getBoots());
            tag(ItemTags.EQUIPPABLE_ENCHANTABLE).add(set.getHat(), set.getChest(), set.getLegs(), set.getBoots());
            tag(ItemTags.DURABILITY_ENCHANTABLE).add(set.getHat(), set.getChest(), set.getLegs(), set.getBoots());

            tag(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(set.getHat());
            tag(ItemTags.HEAD_ARMOR).add(set.getHat());
            tag(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(set.getChest());
            tag(ItemTags.CHEST_ARMOR).add(set.getChest());
            tag(ItemTags.LEG_ARMOR_ENCHANTABLE).add(set.getLegs());
            tag(ItemTags.LEG_ARMOR).add(set.getLegs());
            tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(set.getBoots());
            tag(ItemTags.FOOT_ARMOR).add(set.getBoots());
        }

        @Override
        public @NotNull String getName() {
            return "Ars Elemental Item Tags";
        }
    }

    public static class AEBlockTagsProvider extends BlockTagsProvider {
        final TagKey<Block> ARCHWOOD_LEAVES = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "leaves/archwood_leaves"));
        final static public TagKey<Block> FLASHING_LOGS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(MODID, "logs/flashing_archwood"));

        public AEBlockTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(gen.getPackOutput(), provider, ArsElemancy.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {

        }

        void logsTag(Block... blocks) {
            tag(BlockTags.LOGS).add(blocks);
            tag(BlockTags.LOGS_THAT_BURN).add(blocks);
            tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
        }

        void addPickMineable(int level, Block... blocks) {
            for (Block block : blocks) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                switch (level) {
                    case 1 -> tag(BlockTags.NEEDS_STONE_TOOL).add(block);
                    case 2 -> tag(BlockTags.NEEDS_IRON_TOOL).add(block);
                    case 3 -> tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block);
                    case 4 -> tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(block);
                }
            }

        }

        @Override
        public @NotNull String getName() {
            return "Ars Elemental Block Tags";
        }
    }

    public static class AEEntityTagProvider extends EntityTypeTagsProvider {

        public AEEntityTagProvider(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator.getPackOutput(), provider, MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {

        }

        @Override
        public @NotNull String getName() {
            return "Ars Elemental Entity Tags";
        }
    }

    public static class AEDamageTypeProvider extends DamageTypeTagsProvider {

        public AEDamageTypeProvider(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator.getPackOutput(), provider, MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {


        }
    }
}
