package cn.wymo.etc.dealer;

import org.json.JSONObject;

public interface IDataProcessor {
	boolean init();
	String getName();
	void setInput(JSONObject input);
	boolean process();
	JSONObject getOutput();
}
