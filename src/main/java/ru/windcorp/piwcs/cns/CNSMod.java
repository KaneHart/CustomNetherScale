package ru.windcorp.piwcs.cns;

import java.io.File;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(
		modid = "custom_nether_scale",
		name = "Custom Nether Scale",
		version = "$VERSION$",
		
		// Server-only mod
		acceptableRemoteVersions = "*",
		dependencies = "required-after:OpenMods@[$LIB-VERSION$,$NEXT-LIB-VERSION$)"
)
public class CNSMod {
	
	private Logger logger;
	
	private double netherScale;
	
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		loadConfig(e.getSuggestedConfigurationFile());
	}

	private void loadConfig(File suggestedConfigurationFile) {
		Configuration config = new Configuration(suggestedConfigurationFile);
		config.load();
		
		final double maxRatio = 2 << 16;
		
		netherScale = config.get(Configuration.CATEGORY_GENERAL, "nether_scale", 8.0,
				"Relative scale of the Nether. 8.0 is the vanilla value,"
				+ " 1 Nether block for every 8 blocks in the Overworld,"
				+ " 1.0 is block-for-block,"
				+ " 0.25 is 1 Overworld block for every 4 Nether blocks."
		).setMaxValue(maxRatio).setMinValue(1 / maxRatio).getDouble();
		
		getLogger().debug("Nether scale is {}", Double.toString(getNetherScale()));
		
		if (config.hasChanged()) config.save();
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public double getNetherScale() {
		return netherScale;
	}
	
}
