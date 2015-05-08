package cn.wymo.etc.dealer;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = Logger.getLogger(App.class);
	
	public static void usage() {
		System.out.println("Usage: etc-dealer [-h] [-H <host>] [-p <port>]");
		System.out.println(" Data message dealer in ETC (Elastic Tracing Cloud)");
		System.out.println(" -H, --host=<hostname>     Specify host name/IP to request data messages (default:localhost).");
		System.out.println(" -p, --port=<port>         Specify port to request data messages (default:5679).");
		System.out.println(" -h, --help                Print this help and exit.");
		System.exit(0);
	}
	
	public static void main( String[] args ) {
		String host = "localhost";
		int port = 5679;
		
		int c;
		LongOpt[] options = new LongOpt[3];		
		options[0] = new LongOpt("help",   LongOpt.NO_ARGUMENT, null, 'h');
		options[1] = new LongOpt("host",   LongOpt.OPTIONAL_ARGUMENT, null, 'H');
		options[2] = new LongOpt("port",   LongOpt.OPTIONAL_ARGUMENT, null, 'p');
		
		Getopt g = new Getopt("etc-dealer", args, "H:p:h;", options);
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
				default:
					break;
			}
		}
		
		Context context = ZMQ.context(1);		
		String socketAddr = "tcp://"+host+":"+port;
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		logger.info("Connecting ETC broker "+socketAddr+"...");
		socket.connect(socketAddr);
        System.out.println("Waiting for message...");
        while (true) {
            //  Wait for next request from client (C string)
            String request = socket.recvStr(0);
            System.out.println(request);
            //  Send reply back to client (C string)
            socket.send(ByteBuffer.allocate(4).putInt(0).array());
        }
	}
}
