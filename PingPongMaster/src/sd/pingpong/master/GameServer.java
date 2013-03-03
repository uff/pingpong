package sd.pingpong.master;
/* GameServer.java */

import java.net.*;
class GameServer {
   public static void main(String argv[]) throws Exception
   {
      ServerSocket welcomeSocket = new ServerSocket(6900);
      while(true) {
         Socket connectionSocket = welcomeSocket.accept();
         ConnectionThread s = new ConnectionThread(connectionSocket);
         new Thread(s).start();
      }
   }
}