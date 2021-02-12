package owl.discovery.findwally.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import owl.discovery.findwally.model.ZipElementInfo;

@Service
@Slf4j
public class ZipExploreService {
	
	@Autowired
	public NamingService namingService;

	public List<ZipElementInfo> explore(File zipFile) {
		List<ZipElementInfo> zipElementInfos = new ArrayList<>();
		
		try {
			ZipFile zf = new ZipFile(zipFile);
			Enumeration<?> e=zf.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze=(ZipEntry)e.nextElement();
				long size = ze.getSize();
				String simpleName = namingService.getSimpleName(ze.getName());
				zipElementInfos.add(ZipElementInfo.builder().name(ze.getName()).simpleName(simpleName).size(size).build());
			}
		} catch (Exception e) {
			log.error("::explore:: error : ", e);
			throw new ZipExploreServiceException(e);
		}
		
		return zipElementInfos;
	}
}
