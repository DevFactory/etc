package cn.wymo.etc.common.model;

public enum Role {
	USER("USER", "集团用户"),
	ADMINISTRATOR("ADMINISTRATOR", "管理员"),
	VENDOR("VENDOR", "供应商");
	
	private final String name;
	private final String caption;
	
    private Role(final String name, final String caption) {
    	this.name = name;
    	this.caption = caption;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCaption() {
        return caption;
    }
    
	public static Role getEnum(String value) {
        for(Role v : values())
            if(v.getName().equalsIgnoreCase(value)) return v;
        return USER;
    }
	
	public String toString() {
        return getName();
    }
}
