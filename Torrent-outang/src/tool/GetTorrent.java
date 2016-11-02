package tool;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class GetTorrent {

	public GetTorrent(File torrentfile) {

		File output = new File(".");
		SharedTorrent torrent = null;
		try {
			torrent = SharedTorrent.fromFile(torrentfile, output);
			Client client = new Client(InetAddress.getLocalHost(), torrent);
			client.share(1800);
			Thread.sleep((long) (1800 + 10) * 1000);
			client.stop();

			System.exit(0);
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
