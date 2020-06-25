package ru.windcorp.piwcs.cns;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

//must be higher than one in openmodslib
@IFMLLoadingPlugin.SortingIndex(34)
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class CNSCorePlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {
				CNSTransformer.class.getName()
		};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// Do nothing
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
	
}
