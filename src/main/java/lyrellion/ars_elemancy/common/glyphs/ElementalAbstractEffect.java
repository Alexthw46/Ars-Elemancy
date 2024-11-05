package lyrellion.ars_elemancy.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

public abstract class ElementalAbstractEffect extends AbstractEffect {

    public ElementalAbstractEffect(String tag, String description) {
        super(prefix("glyph_" + tag), description);
    }

}
