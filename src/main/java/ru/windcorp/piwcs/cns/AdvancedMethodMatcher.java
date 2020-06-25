package ru.windcorp.piwcs.cns;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import openmods.asm.MappedType;
import openmods.asm.VisitorHelper;

/**
 * Based on OpenMods' openmods.asm.MethodMatcher.
 * @author Javapony
 */
public class AdvancedMethodMatcher {
	
	private final String clsName;
	private final String description;
	private final String unmappedDescription;
	private final String srgName;
	private final String mcpName;

	public AdvancedMethodMatcher(String clsName, String description, String mcpName, String srgName) {
		this.clsName = clsName;
		this.description = description;
		this.unmappedDescription = unmapDescription(description);
		this.srgName = srgName;
		this.mcpName = mcpName;
	}

	private static String unmapDescription(String description) {
		if (!VisitorHelper.useSrgNames()) return null;
		
		StringBuilder result = new StringBuilder();
		
		int classStart = -1;
		boolean readingClass = false;
		
		for (int i = 0; i < description.length(); ++i) {
			char c = description.charAt(i);
			
			if (readingClass) {
				if (c == ';') {
					String mapped = description.substring(classStart, i);
					String unmapped = FMLDeobfuscatingRemapper.INSTANCE.unmap(mapped);
					
					result.append('L');
					result.append(unmapped);
					result.append(';');
					
					readingClass = false;
				}
			} else {
				if (c == 'L') {
					readingClass = true;
					classStart = i + 1;
				} else {
					result.append(c);
				}
			}
		}
		
		return result.toString();
	}

	public AdvancedMethodMatcher(MappedType cls, String description, String mcpName, String srgName) {
		this(cls.name(), description, mcpName, srgName);
	}

	public boolean match(String methodName, String methodDesc) {
		return matchName(methodName, methodDesc) && matchDescription(methodName, methodDesc);
	}

	private boolean matchName(String methodName, String methodDesc) {
		if (methodName.equals(mcpName)) return true;
		if (!VisitorHelper.useSrgNames()) return false;
		
		String mapped = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(clsName, methodName, methodDesc);
		if (mapped.equals(srgName)) return true;
		
		return false;
	}

	private boolean matchDescription(String methodName, String methodDesc) {
		if (methodDesc.equals(description)) return true;
		
		if (!VisitorHelper.useSrgNames()) return false;
		if (methodDesc.equals(unmappedDescription)) return true;
		
		return false;
	}

	@Override
	public String toString() {
		return String.format("Matcher: %s.[%s,%s] %s", clsName, srgName, mcpName, description);
	}
	
}
