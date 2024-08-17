package io.github.discusser;

import com.mojang.logging.LogUtils;
import io.github.discusser.events.PowerfulSaucesEventHandler;
import io.github.discusser.objects.PowerfulSaucesItems;
import io.github.discusser.objects.PowerfulSaucesSerializers;
import io.github.discusser.objects.PowerfulSaucesTabs;
import org.slf4j.Logger;

public final class PowerfulSauces {
    public static final String MOD_ID = "powerful_sauces";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        PowerfulSaucesTabs.TABS.register();
        PowerfulSaucesItems.ITEMS.register();
        PowerfulSaucesSerializers.SERIALIZERS.register();
        PowerfulSaucesEventHandler.register();
    }
}
