package owl.discovery.findwally.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NamingService {

	@Value("${naming.service.prefixDevelop}")
	private String prefixDevelop; 
	
	public String getSimpleName(String name) {
		log.info("::getSimpleName:: [name={}] [prefixDevelop={}]", name, prefixDevelop);
		
		String simpleName = FilenameUtils.getName(name);
		String fullPath = FilenameUtils.getFullPath(name);

		if ( simpleName.contains(prefixDevelop) ) {
			int indexOfCiDevelop = simpleName.indexOf(prefixDevelop);
			int lastIndexOfDot = simpleName.lastIndexOf(".");
			simpleName = simpleName.substring(0, indexOfCiDevelop)+simpleName.substring(lastIndexOfDot);
		}
		
		long dotCount = simpleName.chars().filter(ch -> ch == '.').count();
		long minusCount = simpleName.chars().filter(ch -> ch == '-').count();
		
		if ( dotCount==1 ) {
			if ( minusCount>0 ) {
				int indexOfDot = simpleName.indexOf('.');
				int lastIndexOfMinus = simpleName.lastIndexOf('-');
				
				String version = simpleName.substring(lastIndexOfMinus+1, indexOfDot);
				
				try {
					Integer.parseInt(version);
					simpleName = simpleName.substring(0, lastIndexOfMinus)+simpleName.substring(indexOfDot);
				}catch(NumberFormatException ex) {
					// nothing to do 
				}
			}
		}else {
			if ( minusCount>0 ) {
				int indexOfDot = simpleName.indexOf('.');
				int lastIndexOfDot = simpleName.lastIndexOf('.');
				int lastIndexOfMinus = simpleName.lastIndexOf('-');
				
				if ( simpleName.substring(0, indexOfDot).lastIndexOf('-')>0 ) {
					indexOfDot = simpleName.substring(0, indexOfDot).lastIndexOf('-');
				}else {
					indexOfDot = lastIndexOfMinus;
				}
				
				simpleName = simpleName.substring(0, indexOfDot) + simpleName.substring(lastIndexOfDot);
			}
		}
		
		simpleName = fullPath+simpleName;
		
		log.info("::getSimpleName:: [simpleName={}]", simpleName);

		return simpleName;
	}

}
