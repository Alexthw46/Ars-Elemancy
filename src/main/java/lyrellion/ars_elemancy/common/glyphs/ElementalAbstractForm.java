package lyrellion.ars_elemancy.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.AbstractCastMethod;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

public abstract class ElementalAbstractForm extends AbstractCastMethod {
    public ElementalAbstractForm(String tag, String description) {
        super(prefix("glyph_" + tag), description);
    }

}
