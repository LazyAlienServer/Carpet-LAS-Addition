package lazyalienserver.carpetlasaddition.interfaces.mixins.enderPearlChunkLoading;

public interface lastTickInCannonAccess {
    default int carpet_LAS_Addition$getLeaveCannonTicks(){
        return 0;
    }

    default boolean carpet_LAS_Addition$isPearlInCannon(){
        return false;
    }
}
