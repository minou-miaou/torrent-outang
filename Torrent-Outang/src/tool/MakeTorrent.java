package tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.common.Torrent;
import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class MakeTorrent {

	public static int seedtime = 1800;

	Tools tools = new Tools();

	/**
	 * @param dossier
	 * Folder to convert in .torrent
	 */
	public MakeTorrent(File dossier) {

		File parent = dossier.getParentFile();

		Tracker t = null;
		try {
			t = new Tracker(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.start();

		// Torrent tor = null;

		try {
			List<File> liste = tools.toList(dossier);
			URL url = t.getAnnounceUrl();
			Torrent tor = Torrent.create(dossier, liste, url.toURI(), "Aymeric");
			tor.save(new FileOutputStream(parent + dossier.getName() + ".torrent"));
			TrackedTorrent tt = new TrackedTorrent(tor);
			t.announce(tt);
			Client seeder = new Client(InetAddress.getLocalHost(), new SharedTorrent(tor, parent, true));

			Runtime.getRuntime().addShutdownHook(new Thread(new Client.ClientShutdown(seeder, null)));
			seeder.share(seedtime);
			Thread.sleep((long) (seedtime + 5) * 1000);
			t.stop();
			System.exit(0);
		} catch (NoSuchAlgorithmException | InterruptedException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
