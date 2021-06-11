package client;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientFrame extends javax.swing.JFrame 
{
    String username;
    String IPAddress = "127.0.0.1";
    ArrayList<String> listClient = new ArrayList();
    int port = 5009;
    Boolean connectionStatus = false;
    
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    
  
    public void AddClient(String data) 
    {
         listClient.add(data);
    }    
  
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            txtAreaChat.append("Could not send Disconnect message.\n");
        }
    }
      public void StartClientHandler() 
    {
         Thread thread = new Thread(new ClientHandler());
         thread.start();
    }
    
    public void Disconnect() 
    {
        try 
        {
            txtAreaChat.append("Disconnected.\n");
            socket.close();
        } catch(Exception ex) {
            txtAreaChat.append("Failed to disconnect. \n");
        }
        connectionStatus = false;
        txtUsername.setEditable(true);

    }
    
    public ClientFrame() 
    {
        initComponents();
    }
    
    public class ClientHandler implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String receive, connect = "connect", disconnect = "disconnect", chat = "chat";

            try 
            {
                while ((receive = reader.readLine()) != null) 
                {
                     data = receive.split(":");

                     if (data[2].equalsIgnoreCase(chat)) 
                     {
                        txtAreaChat.append(data[0] + ": " + data[1] + "\n");
                        txtAreaChat.setCaretPosition(txtAreaChat.getDocument().getLength());
                     } 
                     else if (data[2].equalsIgnoreCase(connect))
                     {
                        AddClient(data[0]);
                     } 
                     else if (data[2].equalsIgnoreCase(disconnect)) 
                     {
                         DiconnectClient(data[0]);
//                         userRemove(data[0]);
                     }
                }
           }catch(Exception ex) { }
        }

        private void DiconnectClient(String id) {
            txtAreaChat.append("User is disconnected");
            
            
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConnect = new javax.swing.JButton();
        lb_address = new javax.swing.JLabel();
        txtIPAddress = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaChat = new javax.swing.JTextArea();
        txtBoxChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnConnection = new javax.swing.JButton();

        btnConnect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_address.setText("Address : ");

        txtIPAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIPAddress.setText("127.0.0.1");
        txtIPAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIPAddressActionPerformed(evt);
            }
        });

        lb_port.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_port.setText("Port :");

        txtPort.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPort.setText("5009");
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });

        lb_username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_username.setText("Username :");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtAreaChat.setColumns(20);
        txtAreaChat.setRows(5);
        jScrollPane1.setViewportView(txtAreaChat);

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnConnection.setText("Connect");
        btnConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(lb_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIPAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                    .addComponent(txtUsername))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBoxChat, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_address)
                    .addComponent(txtIPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_port)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_username))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBoxChat)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIPAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIPAddressActionPerformed
       
    }//GEN-LAST:event_txtIPAddressActionPerformed

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
   
    }//GEN-LAST:event_txtPortActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
    
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        if (connectionStatus == false) 
        {
            username = txtUsername.getText();
            txtUsername.setEditable(false);
            
            try 
            {
                socket = new Socket(IPAddress, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + ":connected:connect");
                writer.flush(); 
                connectionStatus = true; 
            } 
            catch (Exception ex) 
            {
                txtAreaChat.append("Something went wrong, please try again \n");
                txtUsername.setEditable(true);
            }
            
            StartClientHandler();
            
        } else if (connectionStatus == true) 
        {
            txtAreaChat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if(txtBoxChat.getText().length() > 0){
            try {
               writer.println(username + ":" + txtBoxChat.getText() + ":chat");
               writer.flush(); 
            } catch (Exception ex) {
                txtAreaChat.append("Message can not send \n");
            }
        }
        txtBoxChat.setText("");
        txtBoxChat.requestFocus();
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectionActionPerformed
        // TODO add your handling code here:
         if (connectionStatus == false) 
        {
            username = txtUsername.getText();
            txtUsername.setEditable(false);
            try 
            {
                socket = new Socket(IPAddress, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + ":connected:connect");
                writer.flush(); 
                connectionStatus = true; 
            } 
            catch (Exception ex) 
            {
                txtAreaChat.append("Something went wrong, please try again \n");
                txtUsername.setEditable(true);
            }
            
            StartClientHandler();
            
        } else if (connectionStatus == true) 
        {
            txtAreaChat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_btnConnectionActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new ClientFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnConnection;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea txtAreaChat;
    private javax.swing.JTextField txtBoxChat;
    private javax.swing.JTextField txtIPAddress;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
