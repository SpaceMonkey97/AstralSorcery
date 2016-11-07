package hellfirepvp.astralsorcery.common.crafting.altar.recipes.upgrade;

import hellfirepvp.astralsorcery.common.block.BlockMarble;
import hellfirepvp.astralsorcery.common.block.network.BlockAltar;
import hellfirepvp.astralsorcery.common.crafting.IAltarUpgradeRecipe;
import hellfirepvp.astralsorcery.common.crafting.INighttimeRecipe;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.DiscoveryRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapeMap;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipeSlot;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import hellfirepvp.astralsorcery.common.tile.TileAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: AttenuationUpgradeRecipe
 * Created by HellFirePvP
 * Date: 09.10.2016 / 11:40
 */
public class AttenuationUpgradeRecipe extends DiscoveryRecipe implements IAltarUpgradeRecipe, INighttimeRecipe {

    public AttenuationUpgradeRecipe() {
        super(new ShapedRecipe(new ItemStack(BlocksAS.blockAltar, 1, BlockAltar.AltarType.ALTAR_2.ordinal()))
                .addPart(BlockMarble.MarbleBlockType.PILLAR.asStack(),
                        ShapedRecipeSlot.LOWER_LEFT,
                        ShapedRecipeSlot.UPPER_LEFT,
                        ShapedRecipeSlot.UPPER_RIGHT,
                        ShapedRecipeSlot.LOWER_RIGHT)
                .addPart(BlockMarble.MarbleBlockType.CHISELED.asStack(),
                        ShapedRecipeSlot.RIGHT,
                        ShapedRecipeSlot.LEFT)
                .addPart(ItemsAS.rockCrystal,
                        ShapedRecipeSlot.UPPER_CENTER));
    }

    @Override
    public TileAltar.AltarLevel getLevelUpgradingTo() {
        return TileAltar.AltarLevel.ATTENUATION;
    }

    @Nonnull
    @Override
    public ItemStack getOutputForRender() {
        return new ItemStack(BlocksAS.blockAltar, 1, BlockAltar.AltarType.ALTAR_2.ordinal());
    }

    @Override
    public boolean matches(TileAltar altar) {
        return altar.getAltarLevel().ordinal() < getLevelUpgradingTo().ordinal() && super.matches(altar);
    }

    @Nullable
    @Override
    public ItemStack getOutput(ShapeMap centralGridMap, TileAltar tileAltar) {
        return null;
    }

    @Override
    public int craftingTickTime() {
        return super.craftingTickTime() * 4;
    }

    @Override
    public void onCraftServerFinish(TileAltar altar, Random rand) {
        super.onCraftServerFinish(altar, rand);

        altar.tryForceLevelUp(getLevelUpgradingTo(), true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onCraftClientTick(TileAltar altar, int tick, Random rand) {
        super.onCraftClientTick(altar, tick, rand);

        ParticleManager pm = Minecraft.getMinecraft().effectRenderer;
        if(rand.nextInt(6) == 0) {
            pm.addBlockDestroyEffects(altar.getPos(), BlocksAS.blockMarble.getDefaultState());
        }
    }

}