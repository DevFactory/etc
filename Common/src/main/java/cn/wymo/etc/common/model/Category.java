package cn.wymo.etc.common.model;

public enum Category {
	CATEGORY_TEMPERATURE_AIR("temperature_air", "空气温度", "%.1f", "°C", TemperatureAir.class, 10, 10, -20, 50, 0.1, 1, 0),
	CATEGORY_TEMPERATURE_SOIL("temperature_soil", "土壤温度", "%.1f", "°C", TemperatureSoil.class, 10, 10, -20, 50, 0.1, 1, 0),
	CATEGORY_HUMIDITY("humidity", "空气湿度", "%.1f", "%RH", Humidity.class, 10, 10, 0, 100, 0.1, 1, 0),
	CATEGORY_LUMINATION("lumination", "环境照度", "%.1f", "Lux", Lumination.class, -1, -1, 0, 200000, 1, 0, 0),
	CATEGORY_MOISTURE("moisture", "土壤水分", "%.1f", "%", Moisture.class, 10, 10, 0, 100, 0.1, 1, 0),
	CATEGORY_DUST("dust", "粉尘浓度", "%.0f", "K�?283ml", Humidity.class, 1, 1, 0, 10, 1, 0, -1),
	CATEGORY_PH("ph", "土壤pH值", "%.1f", "", PH.class, 7, 7, 0, 14.9, 0.1, 1, 0),
	CATEGORY_O2("o2", "氧气含量", "%.1f", "%", O2.class, 10, 10, 0, 100, 0.1, 1, 1),
	CATEGORY_CO2("co2", "二氧化碳含量", "%.0f", "ppm", CO2.class, 500, 500, 0, 2000, 1, 0, -1),
	CATEGORY_NH3("nh3", "氨气含量", "%.0f", "ppm", NH3.class, 10, 10, 0, 100, 1, 0, -1);

	private final String name;
	private final String title;
	private final String formatter;
	private final String unit;
	private double min = Double.MIN_VALUE;
	private double max = Double.MAX_VALUE;
	private double delta;
	private int scale;
	private double majorTickInterval;
	private double minorTickInterval;
    private final Class<? extends Data> klass;
    private int polar;
	
    private Category(final String name, 
    		final String title,
    		final String formatter,
    		final String unit,
    		final Class<? extends Data> klass,
    		double majorTickInterval,
    		double minorTickInterval,
    		double min,
    		double max,
    		double delta,
    		int scale,
    		int polar) {
    	this.name = name;
    	this.klass = klass;
    	this.unit = unit;
    	this.title = title;
    	this.formatter = formatter;
    	this.min = min;
    	this.max = max;
    	this.delta = delta;
    	this.scale = scale;
    	this.majorTickInterval = majorTickInterval;
    	this.minorTickInterval = minorTickInterval;
    	this.polar = polar;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getFormatter() {
        return formatter;
    }
    
    public String getUnit() {
        return unit;
    }

    public Class<? extends Data> getModelClass() {
        return klass;
    }
    
    public String getValue() {
        return getName();
    }
    
    public double getMin() {
        return min;
    }
    
    public double getMax() {
        return max;
    }
    
    public double getDelta() {
        return delta;
    }
    
    public int getScale() {
        return scale;
    }
    
    public int getPolar() {
        return polar;
    }
    
    public double getMajorTickInterval() {
        return majorTickInterval;
    }
    
    public double getMinorTickInterval() {
        return minorTickInterval;
    }
    
    public String toString() {
    	return getName();
    }
    
    public static Category getEnum(String value) {
        for(Category v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
