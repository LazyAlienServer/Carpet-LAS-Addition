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

    public static final ChunkTicketType<ChunkPos> ENDER_PEARL_TICKET=ChunkTicketType.create("endeer_pearl", Comparator.comparingLong(ChunkPos::toLong),1);


    //计算EnderPearlChunkPOS添加ticket
    public static void addEnderPearlTicket(Vec3d pos, ServerWorld world){
        //添加ticket
        ChunkPos cp = new ChunkPos(MathHelper.floor(pos.x) >> 4, MathHelper.floor(pos.z) >> 4);
        world.getChunkManager().addTicket(ENDER_PEARL_TICKET, cp, 2, cp);
    }

    public static void addNCNoteBlockChunkTicket(World world, BlockPos pos){
        if(world instanceof ServerWorld){
            ChunkPos cp= new ChunkPos(pos.getX()>>4,pos.getZ()>>4);
            ((ServerWorld)world).getChunkManager().addTicket(NoteBlockChunkTicket,cp,3,cp);
        }
    }
}
