package lyrellion.ars_elemancy.mixin;

import alexthw.ars_elemental.util.CompatUtils;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import lyrellion.ars_elemancy.registry.ModItems;
import lyrellion.ars_elemancy.util.FocusHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CompatUtils.class)
public class SchoolFocusMixin {

    @ModifyReturnValue(method = "fireCheck", at = @At("RETURN"))
    private static boolean fireCheck(boolean original, SpellResolver resolver) {
        return original || FocusHelper.focusMatches(resolver,
                ModItems.CINDER_FOCUS.get(),
                ModItems.VAPOR_FOCUS.get(),
                ModItems.LAVA_FOCUS.get(),
                ModItems.ELEMANCER_FOCUS.get()
        );
    }

    @ModifyReturnValue(method = "waterCheck", at = @At("RETURN"))
    private static boolean waterCheck(boolean original, SpellResolver resolver) {
        return original || FocusHelper.focusMatches(resolver,
                ModItems.TEMPEST_FOCUS.get(),
                ModItems.MIRE_FOCUS.get(),
                ModItems.VAPOR_FOCUS.get(),
                ModItems.ELEMANCER_FOCUS.get()
        );
    }

    @ModifyReturnValue(method = "earthCheck", at = @At("RETURN"))
    private static boolean earthCheck(boolean original, SpellResolver resolver) {
        return original || FocusHelper.focusMatches(resolver,
                ModItems.SILT_FOCUS.get(),
                ModItems.MIRE_FOCUS.get(),
                ModItems.LAVA_FOCUS.get(),
                ModItems.ELEMANCER_FOCUS.get()
        );
    }

    @ModifyReturnValue(method = "airCheck", at = @At("RETURN"))
    private static boolean airCheck(boolean original, SpellResolver resolver) {
        return original || FocusHelper.focusMatches(resolver,
                ModItems.TEMPEST_FOCUS.get(),
                ModItems.CINDER_FOCUS.get(),
                ModItems.SILT_FOCUS.get(),
                ModItems.ELEMANCER_FOCUS.get()
        );
    }
}
