package common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServer1 {

	private static final Logger logger = LoggerFactory.getLogger(SocketServer1.class);
	
	private ServerSocket serverSocket;
	
	public void start(int nPort) {
		try {
			this.serverSocket = new ServerSocket(nPort);
			
			while (true) {
				Socket socket = this.serverSocket.accept();
				
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
				
				if ( !serverThread.isRunning ) {
					break;
				}
			}
			
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (this.serverSocket != null) {
					this.serverSocket.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
	
	class ServerThread extends Thread {
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		
		boolean isRunning = true;
		String sRecvMsg = "";
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				logger.info("SocketServer Connected to {}", this.socket.getRemoteSocketAddress());
				
				this.is = this.socket.getInputStream();
				this.receivedFromClient(this.is);
				
				this.os = this.socket.getOutputStream();
				this.sendToClient(this.os);
				
				if ( this.sRecvMsg != null ) {
					this.isRunning = false;
				}
				
			} catch (IOException e) {
				logger.error("", e);
			} finally {
				try {
					if (this.os != null) {
						this.os.close();
					}
					if (this.is != null) {
						this.is.close();
					}
					if (this.socket != null) {
						this.socket.close();
					}
				} catch (IOException e) {
					logger.error("", e);
				}	
			}
		}
		
		private void receivedFromClient(InputStream is) throws IOException {
			StringBuilder sb = new StringBuilder();
			
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] buffer = new byte[4096];
			
			int nRead = bis.read(buffer, 0, buffer.length);
			if (nRead > 0) {
				sb.append(new String(buffer, 0, nRead));
			}
			
			synchronized (this) {
				this.sRecvMsg = sb.toString();
				
				logger.info("SocketServer : [{}]", this.sRecvMsg);
			}
		}
		
		private void sendToClient(OutputStream os) throws IOException {
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			// 받은 데이터에 따른 분기 처리 및 그에 따른 응답 값 정의
			String sSendMsg = "Received Success";
			
			bos.write(sSendMsg.getBytes());
			bos.flush();
		}
	}
	
}
