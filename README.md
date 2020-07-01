# Custom Nether Scale
1.7.10 Minecraft Forge mod that makes changing scale of the Nether possible.

This mod *requires* <a href="https://www.curseforge.com/minecraft/mc-mods/openmodslib/">OpenModsLib</a>.

## What it does
Usually, when travelling from the Overworld to the Nether, player's coordinates are divided by 8, and the reverse happens when travelling back. This has the effect of making travelling in the Nether much faster than travelling in the Overworld, 8 times faster, in fact. This mod makes the scaling factor configurable.

This is a server-only mod, it need not be installed on clients to be effective.

### Configuration
The mod's configuration file, `custom_nether_scale.cfg`, contains one setting only: `nether_scale`. *Change this setting to see any effect.*

The default value is `8.0`, which is the vanilla equivalent. Setting it to `1.0` disables the scaling effect entirely, while setting it to `0.125` (1/8) reverses it, making each block in the Overworld worth 8 in the Nether. Scale must be positive.

### Method of operation
Custom Nether Scale is a coremod; it achieves its effect by patching the relevant vanilla code during startup. This might cause incompatibility with other mods.

## Why?
See <a href="https://www.planetminecraft.com/forums/minecraft/modding/how-to-make-travelling-in-the-nether-impractical-611546/">this post</a> on the Planet Minecraft Forums for the motivation behind this mod. In short, Nether travel is boring.

## Compiling
A `gradlew build` would be enough, if only OpenModsLib wasn't required. It is, therefore:

1. Install Git for your operating system.
2. Run
   ```
   git submodule init
   git submodule update
   ```
   in this project's directory.
3. *Replace `mavenCentral()` in OpenModsLib's `build.gradle` with*
   ```
   maven {
       url = "https://repo1.maven.org/maven2/"
   }
   ```
   *to use HTTPS to connect to the central Maven repository.* Maven central will refuse to cooperate otherwise. See this mod's `build.gradle` for reference.
4. Follow instructions in the <a href="https://github.com/OpenMods/OpenModsLib/tree/753cde45e749c600bf82bc6f5384e878cef40f3a">OpenModsLib repo</a>.
5. `gradlew build`.
