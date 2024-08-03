package lazyalienserver.carpetlasaddition.mixins.enderPearlChunkLoading;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Deprecated
@Mixin(ThrownEntity.class)
public class ThrownEntityMixin {

    ThrownEntity entity=((ThrownEntity)(Object)this);

    @Inject(at = @At("HEAD"),method ="tick()V")
    private void chunkLoadNextChunk(CallbackInfo ci)
    {
        if (CarpetLASSetting.enderPearlChunkLoading && entity instanceof EnderPearlEntity entity1)
        {
            //System.out.println("["+CarpetServer.minecraft_server.getTicks() +"] tick1 => "+entity1.getPos());
            Vec3d velocity = entity1.getVelocity();
            World world = entity1.getEntityWorld();


            //判断世界为服务器世界并且xz轴有速度
            if (!(world instanceof ServerWorld &&(Math.abs(velocity.x) > 0.001 || Math.abs(velocity.z) > 0.001))) return;

            Vec3d pos = entity1.getPos().add(velocity);

            //System.out.println("["+CarpetServer.minecraft_server.getTicks() +"] tick2 => "+pos);

            ChunkUtils.addEnderPearlTicket(pos,(ServerWorld) world);
        }
    }
}
