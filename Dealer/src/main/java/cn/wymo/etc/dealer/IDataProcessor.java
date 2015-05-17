package cn.wymo.etc.dealer;

public interface IDataProcessor {
	String getName();
	void setInput(Object input);
	boolean process();
	Object getOutput();
}
