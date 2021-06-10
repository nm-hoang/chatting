package server;

import java.io.*;
import java.net.*;
import java.util.*;


public class ServerFrame extends javax.swing.JFrame 
{
   ArrayList clientStreams; 
   ArrayList<String> listClient;
   int port = 2222;
   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket socket;
       PrintWriter clientPw;

       public ClientHandler(Socket clientSocket, PrintWriter clientPrint) 
       {
            clientPw = clientPrint;
            try 
            {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                txtAreaChat.append("error \n");
            }

       }

       @Override
       public void run() 
       {
            String message, disconnect = "disconnect", chat = "chat" ;
            String connect = "connect";
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    txtAreaChat.append("Receive: " + message + "\n");
                    data = message.split(":");

                    if (data[2].equalsIgnoreCase(connect))
                    {
                        SendAllClient((data[0] + ":" + data[1] + ":" + chat));
                        AddClient(data[0]);
                    } 
                    else if (data[2].equalsIgnoreCase(disconnect)) 
                    {
                        SendAllClient((data[0] + ":is disconnected." + ":" + chat));
                        RemoveClient(data[0]);
                    } 
                    else if (data[2].equalsIgnoreCase(chat)) 
                    {
                        SendAllClient(message);
                    } 
                    else 
                    {
                        txtAreaChat.append("Invalid. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                txtAreaChat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientStreams.remove(clientPw);
             } 
	} 
    }

    public ServerFrame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaChat = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnShowListClient = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        txtAreaChat.setColumns(20);
        txtAreaChat.setRows(5);
        jScrollPane1.setViewportView(txtAreaChat);

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnStart.setText("Start connection");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnShowListClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnShowListClient.setText("Show list client");
        btnShowListClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowListClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnShowListClient, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowListClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        btnStart.getAccessibleContext().setAccessibleName("Start connecion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Thread server = new Thread(new ServerConnection());
        server.start();
        txtAreaChat.append("Server is started \n");
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnShowListClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowListClientActionPerformed
        txtAreaChat.append("\n Online users : \n");
        for (String current_user : listClient)
        {
            txtAreaChat.append(current_user);
            txtAreaChat.append("\n");
        }    
        
    }//GEN-LAST:event_btnShowListClientActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }
    
    public class ServerConnection implements Runnable 
    {
        @Override
        public void run() 
        {
            clientStreams = new ArrayList();
            listClient = new ArrayList();  

            try 
            {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) 
                {
				Socket clientSocketConnect = serverSocket.accept();
				PrintWriter writer = new PrintWriter(clientSocketConnect.getOutputStream());
				clientStreams.add(writer);

                                
				Thread thread = new Thread(new ClientHandler(clientSocketConnect, writer));
				thread.start();
				txtAreaChat.append("A client is connected \n");
                }
            }
            catch (Exception ex)
            {
                txtAreaChat.append("Something went wrong while connecting. \n");
            }
        }
    }
    
    public void AddClient (String data) 
    {
        String message, add = ": :connect",
            username = data;
        listClient.add(username);
        String[] tempList = new String[(listClient.size())];
        listClient.toArray(tempList);

        for (String id:tempList) 
        {
            message = (id + add);
            SendAllClient(message);
        }
      
    }
    
    public void RemoveClient (String data) 
    {
        a
        String message, add = ": :connect", name = data;
        listClient.remove(name);
        String[] tempList = new String[(listClient.size())];
        listClient.toArray(tempList);
        for (String user:tempList) 
        {
            message = (user + add);
            SendAllClient(message);
        }
    }
    
    public void SendAllClient(String message) 
    {
	Iterator it = clientStreams.iterator();
        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		txtAreaChat.append("Send: " + message + "\n");
                writer.flush();
            } 
            catch (Exception ex) 
            {
		txtAreaChat.append("Send error. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowListClient;
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaChat;
    // End of variables declaration//GEN-END:variables
}
