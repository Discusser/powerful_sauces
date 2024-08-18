package io.github.discusser.fabric.data;

import io.github.discusser.data.PowerfulSaucesData;
import io.github.discusser.fabric.data.providers.PowerfulSaucesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PowerfulSaucesDataFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PowerfulSaucesRecipeProvider::new);
    }
}
