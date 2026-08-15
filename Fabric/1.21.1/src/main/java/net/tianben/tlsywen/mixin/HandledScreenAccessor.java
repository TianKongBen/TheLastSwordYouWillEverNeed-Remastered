package net.tianben.tlsywen.mixin;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {

    @Accessor("x")
    int tlsywen$getX();

    @Accessor("y")
    int tlsywen$getY();

    @Accessor("backgroundHeight")
    int tlsywen$getBackgroundHeight();
}