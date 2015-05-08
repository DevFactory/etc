package cn.wymo.etc.common.model;

public enum Status {
	STATUS_OFF("off"),
	STATUS_ON("on"),
	STATUS_UNKNOWN("-");
	
	private final String name;
	
    private Status(final String name) {
    	this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public String getValue() {
        return getName();
    }
    
    public String toString() {
    	return getName();
    }
    
    public static Status getEnum(String value) {
        for(Status v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        return STATUS_UNKNOWN;
    }
}
