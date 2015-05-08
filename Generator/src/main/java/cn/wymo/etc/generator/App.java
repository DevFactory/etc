package cn.wymo.etc.generator;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

/**
 * Hello world!
 *
 */
public class App {	
	static final Logger logger = Logger.getLogger(App.class);
	
	public static void usage() {
		System.out.println("Usage: etc-gen [-h] [-H <host>] [-p <port>] <DATA>...");
		System.out.println(" Data message generator in ETC (Elastic Tracing Cloud)");
		System.out.println(" -H, --host=<hostname>     Specify host name/IP to request data messages (default:localhost).");
		System.out.println(" -p, --port=<port>         Specify port to request data messages (default:5679).");
		System.out.println(" -g, --gateway=<serial>    Specify gateway serial id (default:00000000).");
		System.out.println(" -s, --sensor=<serial>     Specify sensor serial id (default:00000000).");
		System.out.println(" -h, --help                Print this help and exit.");
		System.exit(0);
	}
	
	public static void main( String[] args ) {
		String host = "localhost";
		int port = 5678;
		String gateway = "00000000";
		String sensor = "00000000";
		
		int c;
		LongOpt[] options = new LongOpt[5];		
		options[0] = new LongOpt("help",    LongOpt.NO_ARGUMENT, null, 'h');
		options[1] = new LongOpt("host",    LongOpt.OPTIONAL_ARGUMENT, null, 'H');
		options[2] = new LongOpt("port",    LongOpt.OPTIONAL_ARGUMENT, null, 'p');
		options[3] = new LongOpt("gateway", LongOpt.OPTIONAL_ARGUMENT, null, 'g');
		options[4] = new LongOpt("sensor",  LongOpt.OPTIONAL_ARGUMENT, null, 's');
		
		Getopt g = new Getopt("etc-gen", args, "H:p:g:s:h;", options);
		g.setOpterr(false); // We'll do our own error handling
		
		while ((c = g.getopt()) != -1) {
			switch (c) {
				case 'h':
					usage();
					break;
				case 'H':
					host = g.getOptarg();
					break;
				case 'p':
					port = Integer.parseInt(g.getOptarg());
					break;
				case 'g':
					gateway = g.getOptarg();
					break;
				case 's':
					sensor = g.getOptarg();
					break;
				default:
					break;
			}
		}
		
		JSONObject message = new JSONObject();
		JSONObject senors  = new JSONObject();
		JSONArray values  = new JSONArray();
		message.put(gateway, senors);
		senors.put(sensor, values);		
		for(int i = g.getOptind(); i < args.length ; i++) {
			JSONObject value  = new JSONObject();
			value.put("timestamp", System.currentTimeMillis());
			value.put("value", Double.parseDouble(args[i]));
			values.put(value);
		}
		
		Context context = ZMQ.context(1);		
		String socketAddr = "tcp://"+host+":"+port;
		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		logger.info("Connecting ETC broker "+socketAddr+"...");
		socket.connect(socketAddr);
		logger.info("Sending "+message.toString());
		System.out.println("Sending "+message.toString());
		socket.send(message.toString().getBytes(), 0);
        socket.recv(0);
        System.exit(0);
	}
}
