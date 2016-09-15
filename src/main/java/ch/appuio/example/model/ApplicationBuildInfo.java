package ch.appuio.example.model;

import java.util.ArrayList;
import java.util.List;

public class ApplicationBuildInfo {

	ApplicationBuildInfoKeyValue version;
	ApplicationBuildInfoKeyValue buildUser;
	ApplicationBuildInfoKeyValue builddate;
	ApplicationBuildInfoKeyValue buildjdk;
	ApplicationBuildInfoKeyValue javaVendor;
	
	public ApplicationBuildInfo(ApplicationBuildInfoKeyValue version, ApplicationBuildInfoKeyValue buildUser, ApplicationBuildInfoKeyValue builddate, ApplicationBuildInfoKeyValue buildjdk, ApplicationBuildInfoKeyValue javaVendor) {
		this.version = version;
		this.buildUser = buildUser;
		this.builddate = builddate;
		this.buildjdk= buildjdk;
		this.javaVendor = javaVendor;
	}
	
	public List<ApplicationBuildInfoKeyValue> getAsList(){
		List<ApplicationBuildInfoKeyValue> list = new ArrayList<ApplicationBuildInfoKeyValue>();
		
		list.add(version);
		list.add(buildUser);
		list.add(builddate);
		list.add(buildjdk);
		list.add(javaVendor);
		
		return list;
		
	}

	public ApplicationBuildInfoKeyValue getVersion() {
		return version;
	}

}
