# Java EE Example Application on APPUiO

This is a quickstart Java EE Example application for APPUiO - Swiss Container Platform

## Deployment

Create new Project if needed:

```
oc new-project example-jee-s2i
```

Create app and expose the service, to be able to reach the app from the internet:

```
oc new-app https://github.com/appuio/example-jee-s2i.git --name=appuio-jee-s2i
oc expose service appuio-jee-s2i
```

## Configuration

The current example also deploys a wildfly configuration file, the specific config file (cfg/standalone.xml) is optional.
In our example we add a custom config to show how configuration can be customized.


