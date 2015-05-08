package cn.wymo.etc.broker;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = Logger.getLogger(App.class);
	
	public static void usage() {
		System.out.println("Usage: etc-broker [-h] [-i <port>] [-o <port>]");
		System.out.println(" Data message broker in ETC (Elastic Tracing Cloud)");
		System.out.println(" -i, --input=<port>       Specify port to listen data messages (default:5678).");
		System.out.println(" -o, --output=<port>      Specify port for data message dealer to connect (default:5679).");
		System.out.println(" -h, --help               Print this help and exit.");
		System.exit(0);
	}
	
	public static void main( String[] args ) {
		int input  = 5678;
		int output = 5679;
		
		int c;
		LongOpt[] options = new LongOpt[3];		
		options[0] = new LongOpt("help",    LongOpt.NO_ARGUMENT, null, 'h');
		options[1] = new LongOpt("input",   LongOpt.OPTIONAL_ARGUMENT, null, 'i');
		options[2] = new LongOpt("output",  LongOpt.OPTIONAL_ARGUMENT, null, 'o');
		
		Getopt g = new Getopt("etc-broker", args, "i:o:h;", options);
		g.setOpterr(false); // We'll do our own error handling
		
		while ((c = g.getopt()) != -1) {
			switch (c) {
				case 'h':
					usage();
					break;
				case 'i':
					input = Integer.parseInt(g.getOptarg());
					break;
				case 'o':
					output = Integer.parseInt(g.getOptarg());
					break;
				default:
					break;
			}
		}
		
		logger.info("Starting ETC broker on "+input+"...");
		Context context = ZMQ.context(1);

		logger.info("Binding socket for clients.\n" );
        Socket clients = context.socket(ZMQ.ROUTER);
        clients.bind ("tcp://*:"+input);

        logger.info("Binding socket for dealers.\n" );
        Socket dealers = context.socket(ZMQ.DEALER);
        dealers.bind ("tcp://*:"+output);
        
        //  Connect work threads to client threads via a queue
        ZMQ.proxy (clients, dealers, null);

        //  We never get here but clean up anyhow
        clients.close();
        dealers.close();
        context.term();
	}
}
