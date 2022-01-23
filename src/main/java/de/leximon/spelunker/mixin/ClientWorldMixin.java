package de.leximon.spelunker.mixin;

import de.leximon.spelunker.core.IWorldRenderer;
import de.leximon.spelunker.core.SpelunkyEffectRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {

    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    @Override
    public void onBlockChanged(BlockPos pos, BlockState oldBlock, BlockState newBlock) {
        if(!isClient)
            return;
        WorldRenderer worldRenderer = MinecraftClient.getInstance().worldRenderer;
        SpelunkyEffectRenderer spelunkyEffectRenderer = ((IWorldRenderer) worldRenderer).getSpelunkyEffectRenderer();
        spelunkyEffectRenderer.updateBlock(pos);
    }

}
