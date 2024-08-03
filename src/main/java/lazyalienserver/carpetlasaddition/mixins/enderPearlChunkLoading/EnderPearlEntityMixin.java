package lazyalienserver.carpetlasaddition.mixins.enderPearlChunkLoading;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.interfaces.mixins.enderPearlChunkLoading.lastTickInCannonAccess;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderPearlEntity.class)
public class EnderPearlEntityMixin implements lastTickInCannonAccess {

    EnderPearlEntity entity = (EnderPearlEntity) (Object) this;

    @Unique
    boolean isPearlInCannon = false;

    @Unique
    int leaveCannonTicks = 0;

    @Inject(at=@At("HEAD"),method = "tick")
    private void tick(CallbackInfo ci){

        //判断是否启用enderPearlChunkLoading选项
        if (!CarpetLASSetting.enderPearlChunkLoading) return;

        Vec3d velocity = entity.getVelocity();
        World world = entity.getEntityWorld();

        //判断世界是否为服务端世界
        if (!(world instanceof ServerWorld)) return;

        //判断是否进入珍珠炮
        if (isPearlInCannon){
            leaveCannonTicks += 1;
        }

        //判断世界为服务器世界
        //判断速度小于 5b/gt
        //作用：判断是否使用珍珠炮，并且被炮打出去
        if (isPearlInCannon || Math.abs(velocity.x) < 5 && Math.abs(velocity.z) < 5 ) return;
        isPearlInCannon = true;

    }

    @Override
    public int carpet_LAS_Addition$getLeaveCannonTicks() {
        return leaveCannonTicks;
    }

    @Override
    public boolean carpet_LAS_Addition$isPearlInCannon() {
        return isPearlInCannon;
    }
}
