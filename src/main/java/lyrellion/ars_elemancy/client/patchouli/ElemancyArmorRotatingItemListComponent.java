package lyrellion.ars_elemancy.client.patchouli;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.client.patchouli.component.RotatingItemListComponent;
import com.hollingsworth.arsnouveau.common.armor.AnimatedMagicArmor;
import com.hollingsworth.arsnouveau.common.crafting.recipes.IEnchantingRecipe;
import com.hollingsworth.arsnouveau.common.items.data.ArmorPerkHolder;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import lyrellion.ars_elemancy.recipe.ElemancyArmorRecipe;
import lyrellion.ars_elemancy.registry.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;

import java.util.ArrayList;
import java.util.List;

public class ElemancyArmorRotatingItemListComponent extends RotatingItemListComponent {
    @Override
    protected List<Ingredient> makeIngredients() {
        ClientLevel world = Minecraft.getInstance().level;
        if (world == null) return new ArrayList<>();

        RecipeHolder<ElemancyArmorRecipe> holder = world.getRecipeManager().getAllRecipesFor(ModRegistry.ELEMANCY_ARMOR_UP.get()).stream().filter(f -> f.id().toString().equals(recipeName)).findFirst().orElse(null);
        var recipe = holder != null ? holder.value() : null;
        for (RecipeType<? extends IEnchantingRecipe> type : ArsNouveauAPI.getInstance().getEnchantingRecipeTypes()) {
            RecipeHolder<? extends IEnchantingRecipe> recipe1 = world.getRecipeManager().getAllRecipesFor(type).stream().filter(f -> f.id().toString().equals(recipeName)).findFirst().orElse(null);
            if (recipe1 != null && recipe1.value() instanceof ElemancyArmorRecipe enchantingApparatusRecipe) {
                recipe = enchantingApparatusRecipe;
                break;
            }
        }

        return recipe == null ? List.of() : recipe.pedestalItems().stream().reduce(new ArrayList<>(),(list, ing) -> {
            for (var stack : ing.getItems()) {
                if (stack.getItem() instanceof AnimatedMagicArmor magicArmor) {
                    list.add(DataComponentIngredient.of(false, DataComponentRegistry.ARMOR_PERKS, new ArmorPerkHolder().setTier(3), magicArmor));
                } else list.add(Ingredient.of(stack));
            }
            return list;
        }, (list1, list2) -> {
            var combined = new ArrayList<>(list1);
            combined.addAll(list2);
            return combined;
        });
    }
}
