/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2020
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.client.util.draw;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import org.lwjgl.opengl.GL11;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: TextureHelper
 * Created by HellFirePvP
 * Date: 18.07.2019 / 13:41
 */
public class TextureHelper {

    public static ITextureObject getBlockAtlasTexture() {
        return Minecraft.getInstance().getTextureMap();
    }

    public static ITextureObject getMissingTexture() {
        return MissingTextureSprite.getDynamicTexture();
    }

    public static void bindBlockAtlas() {
        ITextureObject obj = getBlockAtlasTexture();
        GlStateManager.bindTexture(obj.getGlTextureId());
    }

    public static void bindMissingTexture() {
        ITextureObject obj = getMissingTexture();
        GlStateManager.bindTexture(obj.getGlTextureId());
    }

    public static void refreshTextureBind() {
        getMissingTexture().bindTexture();
        getBlockAtlasTexture().bindTexture();
    }

}
