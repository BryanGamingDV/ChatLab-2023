package me.bryang.chatlab.module;

import me.bryang.chatlab.ChatLab;
import me.bryang.chatlab.module.submodule.*;
import org.slf4j.Logger;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;
import java.nio.file.Path;

public class MainModule extends AbstractModule {

	private final ChatLab plugin;

	public MainModule(ChatLab plugin) {
		this.plugin = plugin;

	}

	@Provides
	@Singleton
	private Logger provideLogger(ChatLab plugin) {
		return plugin.getSLF4JLogger();
	}

	@Override
	public void configure() {
		bind(ChatLab.class)
			.toInstance(plugin);

		bind(Path.class)
			.named("plugin-folder")
			.toInstance(plugin.getDataFolder().toPath());
		bind(String.class)
			.named("plugin-version")
			.toInstance(plugin.getPluginMeta().getVersion());

		install(new DataModule());
		install(new ListenerModule());
		install(new CommandModule());
		install(new ServiceModule());
		install(new ConfigurationModule());
	}
}
