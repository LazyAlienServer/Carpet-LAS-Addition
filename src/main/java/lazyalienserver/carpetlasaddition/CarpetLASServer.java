package lazyalienserver.carpetlasaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.logging.LoggerRegistry;
import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.commands.server.LazyAlienServerCommand;
import lazyalienserver.carpetlasaddition.utils.CarpetLASAdditionTranslations;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Map;

public class CarpetLASServer implements ModInitializer,CarpetExtension {

    public static final String MOD_NAME = "Carpet-LAS-Addition";
    public static final String MOD_VERSION = "V1.0.2-240503-alpha";
    @Override
    public String version(){
        return MOD_VERSION;
    }
    public String modID(){
        return "carpetlasaddition";
    }
    public static void loadExtension(){
        CarpetServer.manageExtension(new CarpetLASServer());
    }

    @Override
    public void onInitialize() {
        CarpetLASServer.loadExtension();
        CommandRegistrationCallback.EVENT.register(CarpetLASServer::registerLASCommands);
        CarpetLASServer.ServerNetWork();
    }

    public static void ServerNetWork(){
    }

    @Override
    public void registerLoggers(){
        LoggerRegistry.registerLoggers();
    }

    public static void registerLASCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean b) {
        LazyAlienServerCommand.register(dispatcher);
    }

    @Override
    public void onGameStarted(){
        LASLogUtils.log("Carpet-LAS-Addition loaded.");
        CarpetServer.settingsManager.parseSettingsClass(CarpetLASSetting.class);

    }
    @Override
    public void onServerLoaded(MinecraftServer server){
        DateTimeUtils.initDayScheduleEvent();
    }

    @Override
    public void onServerClosed(MinecraftServer server){

    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        //Commands
    }
    @Override
    public Map<String, String> canHasTranslations(String lang)
    {
        return CarpetLASAdditionTranslations.getCarpetResource(lang);
    }
    @Override
    public void onTick(MinecraftServer server){

    }

    public static void tick(){

    }
}
