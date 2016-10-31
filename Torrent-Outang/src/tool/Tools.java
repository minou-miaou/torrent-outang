package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Tools {

	public List <File> toList(File dossier){
		List<File> liste = new ArrayList<File>(FileUtils.listFiles(dossier, TrueFileFilter.TRUE, TrueFileFilter.TRUE));
		
		
		String[]tableau=dossier.list();
		
		for (int i=0;i<tableau.length;i++){
			liste.add(new File(dossier.getAbsolutePath()+ File.separator+tableau[i]));
			System.out.println("tab(i):" + tableau[i]);
		}
		return liste;
	}
}
