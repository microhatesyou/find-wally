package owl.discovery.findwally.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZipElementInfo {
	private String name; 
	private String simpleName; 
	private long size; 
}
