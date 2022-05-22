package hellofx;

import java.util.HashMap;

public class Information {
    private String name; 
    private HashMap<String,Data> label;

    protected boolean addLabel(Data label)
    {
        try {
            this.label.put(label.getName() , label);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Information() {
    }

    



    public String getLabel(String keyword) {
        return label.get(keyword).getValue();
    }

    public Information(String name) {
        this.name = name;
        this.label = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    
}