package run;

import java.io.File;

import org.apache.log4j.BasicConfigurator;

import tool.GetTorrent;
import tool.MakeTorrent;

public class TorrentOutang {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		if(args.length!=2){
			System.err.println("usage: create <dossier non vide> crée et seed un torrent à partir de ce dossier");
			System.err.println("usage: download <fichier .torrent> télécharge le torrent dans le dossier actuel");
			
		}
		if(args[0].equals("create")){
			try{
				File dossier=new File(args[1]);
				if (dossier.isDirectory() && dossier.list().length>0){
					new MakeTorrent(dossier);
				}
				else{
					if(dossier.isFile()){
						System.err.println("Pas de fichier, dossier uniquement");
						System.err.println("usage: create <dossier non vide> crée et seed un torrent à partir de ce dossier");
						System.err.println("usage: download <fichier .torrent> télécharge le torrent dans le dossier actuel");
						System.exit(0);
					}
					if(dossier.list().length==0){
						System.err.println("Votre dossier est vide!");
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
				System.err.println("problème avec le fichier torrent");
				System.err.println("usage: create <dossier non vide> crée et seed un torrent à partir de ce dossier");
				System.err.println("usage: download <fichier .torrent> télécharge le torrent dans le dossier actuel");
			}
			
		}
		
		
		
		
		

	}

}
