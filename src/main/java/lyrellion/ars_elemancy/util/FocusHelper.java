package lyrellion.ars_elemancy.util;

import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import net.minecraft.world.item.Item;

import java.util.Arrays;

public class FocusHelper {

    public static boolean focusMatches(SpellResolver resolver, Item... items){
        return Arrays.stream(items).anyMatch(resolver::hasFocus);
    }
}
