package com.yourname.womfix;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("womfix")
public class WOMCompatFix {
    public static final String MODID = "womfix";
    public static final Logger LOGGER = LogManager.getLogger();

    public WOMCompatFix() {
        LOGGER.info("WOM Compatibility Fix mod loaded");
    }
}