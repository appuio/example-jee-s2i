package ch.appuio.example;

import ch.appuio.example.model.ApplicationBuildInfo;
import ch.appuio.example.model.ApplicationBuildInfoKeyValue;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 */
@Startup
@Singleton
public class ApplicationVersionService {


    @Inject
    private Logger log;

    public ApplicationBuildInfo getApplicationBuildInfo() {
        return applicationBuildInfo;
    }

    private ApplicationBuildInfo applicationBuildInfo;

    @PostConstruct
    public void init() {
        applicationBuildInfo = readApplicationBuildInfo();
    }


    private ApplicationBuildInfo readApplicationBuildInfo(){

        Manifest businessManifest = getBusinessManifest("APPUiOJavaeeExample");

        ApplicationBuildInfoKeyValue version = getMavenVersionClass(getClass(), "Implementation-Version", businessManifest);
        ApplicationBuildInfoKeyValue buildUser = getMavenInfoForClass(businessManifest, "Built-By");
        ApplicationBuildInfoKeyValue builddate = getMavenInfoForClass(businessManifest, "Build-Timestamp");
        ApplicationBuildInfoKeyValue buildjdk = getMavenInfoForClass(businessManifest, "Build-Jdk");
        ApplicationBuildInfoKeyValue buildJavaVendor = getMavenInfoForClass(businessManifest, "Java-Vendor");

        return new ApplicationBuildInfo(version, buildUser, builddate, buildjdk, buildJavaVendor);
    }

    private ApplicationBuildInfoKeyValue getMavenInfoForClass(Manifest manifest, String field){

        String value = "";

        if(manifest != null){
            Attributes attributes = manifest.getMainAttributes();
            if (attributes != null) {
                value = attributes.getValue(field);
            }
        }
        return new ApplicationBuildInfoKeyValue(field, value);
    }

    private Manifest getBusinessManifest(String module){

        Manifest manifest = null;

        Enumeration<URL> resources = null;
        try {
            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
        }
        catch (IOException e) {
            log.log(Level.SEVERE, "Not able to load META-INF/MANIFEST.MF", e);
        }
        if(resources != null){
            while (resources.hasMoreElements()) {
                try {
                    manifest = new Manifest(resources.nextElement().openStream());
                    Attributes attributes = manifest.getMainAttributes();

                    if(attributes != null && attributes.containsValue(module)){
                        return manifest;
                    }
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Not able to load META-INF/MANIFEST.MF", e);
                }
            }
        }
        return manifest;
    }

    private ApplicationBuildInfoKeyValue getMavenVersionClass(Class<?> clazz, String backupField, Manifest manifest){
        String ret = "";
        if(clazz != null){
            Package pack = clazz.getPackage();
            if(pack != null){
                ret = pack.getImplementationVersion();
                if (ret==null) {
                    return getMavenInfoForClass(manifest, backupField);
                }
            }
        }

        return new ApplicationBuildInfoKeyValue(backupField, ret);
    }
}
