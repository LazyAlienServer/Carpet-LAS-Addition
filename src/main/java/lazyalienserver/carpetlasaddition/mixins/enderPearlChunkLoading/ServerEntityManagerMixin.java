package lazyalienserver.carpetlasaddition.mixins.enderPearlChunkLoading;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntityManager.class)
public abstract class ServerEntityManagerMixin<T extends EntityLike>{

    /**
     * 注入stopTicking方法
     * @see ServerEntityManager stopTicking
     * */

    @Inject(at=@At("HEAD"),method = "stopTicking", cancellable = true)
    public void stopTicking(T entity, CallbackInfo ci){
        if (!CarpetLASSetting.enderPearlChunkLoading) return;
        if (!(entity instanceof EnderPearlEntity entity1))  return;

        Vec3d velocity = entity1.getVelocity();
        World world = entity1.getEntityWorld();

        //判断世界为服务器世界并且xz轴有速度
        if (!(world instanceof ServerWorld&&(Math.abs(velocity.x) > 0.001 || Math.abs(velocity.z) > 0.001))) return;

        Vec3d pos = entity1.getPos();

        ChunkUtils.addEnderPearlTicket(pos,(ServerWorld) world);
        ci.cancel();
    }
}
