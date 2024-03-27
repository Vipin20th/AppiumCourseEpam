package appiumBase;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

	static AppiumDriverLocalService server;

	public static void Start() {
		if (isPortAvailable(4723)) {
			getInstance().start();
			System.out.println("Server started from here!");
		} else
			System.out.println("Server already running!");
	}

	static AppiumDriverLocalService getInstance() {
		if (server == null) {
			setInstance();
			server.clearOutPutStreams();
		}
		return server;
	}

	static void setInstance() {
		HashMap<String, String> environment = new HashMap();
		environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node"))
				.usingPort(4723).withEnvironment(environment).withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withLogFile(new File("AppiumLog.txt"));

		server = AppiumDriverLocalService.buildService(builder);
	}


	 static boolean isPortAvailable(int port) {
		try (ServerSocket serverSocket = new ServerSocket()) {
			serverSocket.setReuseAddress(false);
			serverSocket.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static void Stop() {
		if (server != null) {
			getInstance().stop();
			System.out.println("Appium server stopped!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AppiumServer.Start();
		Thread.sleep(2000);
		AppiumServer.Stop();
		
	}

}
