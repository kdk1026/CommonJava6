package common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClient {
	
	private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
	
	private Socket socket;
	
    private String sRecvMsg;
    
	public String getsRecvMsg() {
		return sRecvMsg;
	}
	
	public void start(String sServerIp, int nPort, byte[] bSendMsg) throws IOException {
		InetSocketAddress isa = new InetSocketAddress(sServerIp, nPort);
		OutputStream os = null;
		InputStream is = null;
		
		try {
			this.socket = new Socket();
			this.socket.connect(isa);
		
			os = this.socket.getOutputStream();
			this.sendToServer(os, bSendMsg);
			
			is = this.socket.getInputStream();
			this.receivedFromServer(is);
			
		} finally {
			// 유지해야 하는 경우, 주석처리
			this.stop();
		}
	}
	
	public void stop() {
		try {
			this.socket.close();
		} catch (IOException e) {
			logger.error("stop IOException", e);
		}
	}
	
	private void sendToServer(OutputStream os, byte[] bSendMsg) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		/*
		 * 케릭터셋 인코딩 맞추어야 할 경우, 참고
		 * 	- common.util.bytes.ByteStringUtils
		 * 		> toByteEncoding
		 */
		
		bos.write(bSendMsg);
		bos.flush();
	}
	
	private void receivedFromServer(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buffer = new byte[4096];
		
		int nRead = bis.read(buffer, 0, buffer.length);
		if (nRead > 0) {
			sb.append(new String(buffer, 0, nRead));
		}
		
		this.sRecvMsg = sb.toString();
	}
	
}
