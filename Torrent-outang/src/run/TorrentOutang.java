package run;

import java.io.File;

import org.apache.log4j.BasicConfigurator;

import tool.GetTorrent;
import tool.MakeTorrent;

public class TorrentOutang {
	
	public static void usage(){
		System.err.println("usage: create <folder> creates and seeds torrent from that folder");
		System.err.println("usage: download <.torrent file> downloads torrent in actual directory");
		System.exit(0);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		if(args.length!=2){
			usage();
		}
		if(args.length==0){
			
		}
		if(args[0].equals("create")){
			try{
				File dossier=new File(args[1]);
				if (dossier.isDirectory() && dossier.list().length>0){
					new MakeTorrent(dossier);
				}
				else{
					if(dossier.isFile()){
						System.err.println("No file, only folders");
						usage();
					}
					if(dossier.list().length==0){
						System.err.println("Directory is empty");
					}
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		} else if (args[0].equals("download")){
			File fichier_tor=new File(args[1]);
			if(fichier_tor.isFile()&&fichier_tor.getName().endsWith(".torrent")){
				// fichier torrent, on dl
				new GetTorrent(fichier_tor);
			}else{
				System.err.println("problem with .torrent file!");
				usage();
			}
			
		}
		
		
		
		
		

	}

}
