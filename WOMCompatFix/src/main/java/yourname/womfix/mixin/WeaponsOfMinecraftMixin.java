package com.yourname.womfix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(targets = "reascer.wom.main.WeaponsOfMinecraft")
public class WeaponsOfMinecraftMixin {

    private static final Logger LOGGER = LogManager.getLogger("WOMFix");

    @Inject(method = "<init>", at = @At("HEAD"), cancellable = true, remap = false)
    private void onConstruction(CallbackInfo ci) {
        try {
            // 检查必要的类是否存在
            checkEpicFightClasses();
            LOGGER.info("Epic Fight compatibility check passed");
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Epic Fight compatibility issue detected: " + e.getMessage());
            LOGGER.warn("Weapons of Miracle will run in limited compatibility mode");
            // 不取消构造，让模组继续加载但记录警告
        }
    }

    private void checkEpicFightClasses() throws ClassNotFoundException {
        String[] requiredClasses = {
                "yesman.epicfight.api.forgeevent.AnimationRegistryEvent",
                "yesman.epicfight.api.client.forgeevent.RenderEngineEvent",
                "yesman.epicfight.api.data.reloader.SkillManager"
        };

        for (String className : requiredClasses) {
            try {
                Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("Missing Epic Fight class: " + className);
            }
        }
    }
}