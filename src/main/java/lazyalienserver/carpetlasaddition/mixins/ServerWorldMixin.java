package lazyalienserver.carpetlasaddition.mixins;

import lazyalienserver.carpetlasaddition.CarpetLASServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    /**
     *  允许一个被carpet的tick插件进行冻结
     * */
    @Inject(at=@At("HEAD"),method = "tickTime")
    public void tickInTickFreeze(CallbackInfo ci){
        CarpetLASServer.tick();
    }
}
