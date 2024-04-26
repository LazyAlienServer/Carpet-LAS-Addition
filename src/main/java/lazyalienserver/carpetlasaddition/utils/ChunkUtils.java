package lazyalienserver.carpetlasaddition.utils;

import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;

public class ChunkUtils {

    public static final ChunkTicketType<ChunkPos> NoteBlockChunkTicket=ChunkTicketType.create("NoteBlock", Comparator.comparingLong(ChunkPos::toLong),300);

    public static final ChunkTicketType<ChunkPos> ENDER_PEARL_TICKET=ChunkTicketType.create("endeer_pearl", Comparator.comparingLong(ChunkPos::toLong),2);


    //计算EnderPearlChunkPOS添加ticket
    public static void addEnderPearlTicket(Vec3d pos, Vec3d velocity, ServerWorld world){
        //计算下一次区块位置
        double nx = pos.x + velocity.x;
        double nz = pos.z + velocity.z;
        ChunkPos cp = new ChunkPos(MathHelper.floor(nx) >> 4, MathHelper.floor(nz) >> 4);
        ChunkPos cp2 = new ChunkPos(MathHelper.floor(pos.x) >> 4, MathHelper.floor(pos.z) >> 4);

        //添加ticket
        world.getChunkManager().addTicket(ENDER_PEARL_TICKET, cp2, 2, cp2);
        world.getChunkManager().addTicket(ENDER_PEARL_TICKET, cp, 2, cp);
    }

    public static void addNCNoteBlockChunkTicket(World world, BlockPos pos){
        if(world instanceof ServerWorld){
            ChunkPos cp= BlockPOStoChunkPOS(pos);
            ((ServerWorld)world).getChunkManager().addTicket(NoteBlockChunkTicket,cp,3,cp);
        }
    }
    private static ChunkPos BlockPOStoChunkPOS(BlockPos blockPos){
        return new ChunkPos(blockPos.getX()>>4,blockPos.getZ()>>4);
    }
}
