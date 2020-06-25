package ru.windcorp.piwcs.cns;

import cpw.mods.fml.common.Mod;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;

public class CNSHooks {
	
	@Mod.Instance("custom_nether_scale")
	private static CNSMod modInstance;
	
	public static double getScale(WorldProvider provider) {
		if (provider instanceof WorldProviderHell) {
			return modInstance.getNetherScale(); 
		}
		
		return 1.0;
	}

}
