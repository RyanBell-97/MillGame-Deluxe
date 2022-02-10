
package mill;

import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class ClientThread implements Runnable{

	private int playerID;
	private boolean isBlack;
	public Socket socket;
	public DataInputStream dataIn;
	public DataOutputStream dataOut;
	public String latestServerReply;
	public String separator= "#";
	public Board board;
	private final String serverAddress= "localhost";
	private final int serverPort= 8585;

	public ClientThread(){
		
	}
	
	@Override
	public void run() {
		boolean myTurn;
		int latestIndexFromServer;
		boolean latestPlayerIsBlack;
		//int boardContentArray[] = new int[24];
		//String temp;
		StringTokenizer st;
		System.out.println("---Client---");
		
		while (true) {
			if (!MainWindowNew.connected) {
				board= new Board(true);
				//initSocketAndStreams();
				connectToServer();
				//while (!MainWindowNew.connected) {
				//}
			}
			latestServerReply= "";
			try {
				latestServerReply= dataIn.readUTF();
			} catch (Exception e) {
				System.out.println("run() while(true) latestServerReply= dataIn.readUTF() exception!");
			}
			st= new StringTokenizer(latestServerReply, separator);
			
			//detect if game stopped
			if (stopGame(latestServerReply)) {
				break;
			}
			/*if (latestServerReply.compareTo("stop-game")== 0) {
				stopGame();
				break;
			}*/
			
			//detect if the this player will play the next turn
			if (st.nextToken().compareTo("true")== 0) {
				myTurn= true;
			} else {
				myTurn= false;
			}
			
			//detect the index of the latest move from the server
			latestIndexFromServer= Integer.parseInt(st.nextToken());
			
			//detect who sent the index received from the server
			if (st.nextToken().compareTo("black")== 0) {
				latestPlayerIsBlack= true;
			} else {
				latestPlayerIsBlack= false;
			}
			
			//execute the latest index received from the server using the color of the player that made the move
			System.out.println(board.execute(latestIndexFromServer, latestPlayerIsBlack));
			if(board.gameOver()){
				try {
					MainWindowNew.loginJDialog.setLoggedIn(false);
					MainWindowNew.matchJDialog.selectedOpponent= false;
					this.dataOut.writeInt(98);
					this.dataOut.flush();
					this.dataIn.close();
					this.dataOut.close();
					this.socket.close();
				} catch (Exception e) {
					System.out.println("mainLoop() this.dataOut.writeInt(98)...");
				}
				if(board.isBlackWon()== isBlack){
					MainWindowNew.p.disconnect(0);
					System.out.println("Game over, you won!");
				}else{
					MainWindowNew.p.disconnect(1);
					System.out.println("Game over, you lost!");
				}
				break;
			}
			
			//parse the getBoardContentAsString string into an int array boardContentArray
			/*for (int i = 0; i < 24; i++) {
				boardContentArray[i]= Integer.parseInt(st.nextToken());
			}*/
			
			//call drawBoard with boardContentArray
			//MainWindowNew.p.drawBoard(boardContentArray);
			//MainWindowNew.p.drawBoard(board.getBoardContentAsArray());
			MainWindowNew.p.updateGUI(board);
			System.out.println("boardContentArray: "+ Arrays.toString(board.getBoardContentAsArray()));
		}
	}
	
	private void connectToServer(){
		try {
			//initSocketAndStreams();
			playerID= dataIn.readInt();
			isBlack= dataIn.readBoolean();
			//dataIn.readBoolean();
			System.out.println("Connected to server as player #"+ playerID+ " Color black?: "+ isBlack);
		} catch (Exception e) {
			System.out.println("IO exception from csc constructor");
		}
		MainWindowNew.p.connectionEstablishedGui(playerID, isBlack);
		MainWindowNew.connected= true;
		MainWindowNew.readyPlayers.countDown();
	}
	
	public void initSocketAndStreams(){
		try {
			socket= new Socket(serverAddress, serverPort);
			dataIn= new DataInputStream(socket.getInputStream());
			dataOut= new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("initSocketAndStreams");
		}
	}
	
	public void closeSocketAndStreams(){
		try {
			dataOut.close();
			dataIn.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("closeSocketAndStreams exception!");
		}
	}
	
	public String login(String username, String password){
		String loginResult= "";
		try {
			dataOut.writeUTF(username);
			dataOut.writeUTF(password);
			dataOut.flush();
		} catch (Exception e) {
			System.out.println("writeUTF username and password exception!");
		}
		try {
			loginResult= dataIn.readUTF();
		} catch (Exception e) {
			System.out.println("readUTF loginResult exception!");
		}
		if("loggedIn".equals(loginResult)){
			return loginResult;
		}else if ("signedIn".equals(loginResult)) {
			return loginResult;
		}else{
			closeSocketAndStreams();
			return loginResult;
		}
		
		//return "";
	}
	
	public String selectOpponent(String opponentUsername){
		String opponentSelectionResult;
		try {
			dataOut.writeUTF(opponentUsername);
			dataOut.flush();
		} catch (Exception e) {
			System.out.println("writeUTF opponentUsername exception");
		}
		
		return "";
	}
	
	public String getResponse(){
		try {
			return dataIn.readUTF();
		} catch (Exception e) {
			System.out.println("getResponse readUTF exception");
		}
		return "";
	}
	
	public void sendCancel(){
		try {
			dataOut.writeUTF("cancel");
			dataOut.flush();
		} catch (Exception e) {
		}
	}
	
	public String sendRefresh(){
		String temp= "";
		try {
			dataOut.writeUTF("refresh");
			dataOut.flush();
			System.out.println("loggedIn sent");
			temp= dataIn.readUTF();
			System.out.println("refresh reply");
		} catch (Exception e) {
			System.out.println("sendRefresh() exception!");
		}
		return temp;
	}
	
	public String sendSelectedPlayer(String opponent){
		String temp= "selected player response unchanged";
		try {
			dataOut.writeUTF(opponent);
			dataOut.flush();
			temp= dataIn.readUTF();
		} catch (Exception e) {
			System.out.println("sendSelectedPlayer exception!");
		}
		return temp;
	}

	public boolean stopGame(String latestServerReply){
		//ServerNew.board= new Board(isBlack);
		//ServerNew.askedToJoin= 0;
		
		if(board.gameOver()){
			MainWindowNew.loginJDialog.setLoggedIn(false);
			MainWindowNew.matchJDialog.selectedOpponent= false;
			try {
				this.dataOut.writeInt(98);
				this.dataOut.flush();
				this.dataIn.close();
				this.dataOut.close();
				this.socket.close();
			} catch (Exception e) {
				System.out.println("stopGame() board.gameOver() this.dataOut.writeInt(98)...");
			}
			if(board.isBlackWon()== isBlack){
				MainWindowNew.p.disconnect(0);
				System.out.println("Game over, you won!");
			}else{
				MainWindowNew.p.disconnect(1);
				System.out.println("Game over, you lost!");
			}
			return true;
		}else{
			if (latestServerReply.compareTo("you-stopped-the-game")== 0) {
				MainWindowNew.loginJDialog.setLoggedIn(false);
				MainWindowNew.matchJDialog.selectedOpponent= false;
				try {
					this.dataIn.close();
					this.dataOut.close();
					this.socket.close();
				} catch (Exception e) {
					System.out.println("stopGame() !board.gameOver() latestServerReply.compareTo(\"you-stopped-the-game\")== 0...");
				}
				MainWindowNew.p.disconnect(2);
				System.out.println("You stopped the game");
				return true;
			}else if (latestServerReply.compareTo("other-player-stopped-the-game")== 0) {
				MainWindowNew.loginJDialog.setLoggedIn(false);
				MainWindowNew.matchJDialog.selectedOpponent= false;
				try {
					this.dataOut.writeInt(99);
					this.dataOut.flush();
					this.dataOut.close();
					this.dataIn.close();
					this.socket.close();
				} catch (Exception e) {
					System.out.println("stopGame() !board.gameOver() latestServerReply.compareTo(\"other-player-stopped-the-game\")== 0...");
				}
				MainWindowNew.p.disconnect(3);
				System.out.println("The other player stopped the game");
				return true;
			}
		}
		return false;
	}
	
	public String getServerAddress(){
		return serverAddress;
	}
	public String getServerPort(){
		return String.valueOf(serverPort) ;
	}
}