package test.attprog.carlo.androidgcm.messages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Carlo on 07/02/2016.
 */
public class ConfigurationList {

    private List<Configuration> configurationList;

    public ConfigurationList() {
    }

    public ConfigurationList(LinkedHashMap<String,String> values){
        this.configurationList = new ArrayList<>();

        for(String key : values.keySet()){
            Configuration toBeAdded = new Configuration(key,values.get(key));
            this.configurationList.add(toBeAdded);
        }
    }

    public List<Configuration> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<Configuration> configurationList) {
        this.configurationList = configurationList;
    }
}
