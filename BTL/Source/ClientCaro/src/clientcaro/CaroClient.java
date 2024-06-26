package clientcaro;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TrayIcon;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;


public class CaroClient extends javax.swing.JFrame {

    /** Creates new form Caro */
    public CaroClient()
    {
        setVisible(true);
    }
    public CaroClient(String ip,final Integer port1,final Integer port2, String name) {
        initComponents();
        setTitle(name+ " (Client)");
        setVisible(true);
        jLabel_ban.setText("Bạn đánh.");
        jLabel_doiphuong.setText("Đối phương đánh.");
        host = ip;
        mySelf = new PlayNow(myselfPanel);
        you = new PlayNow(youPanel);
        mySelf.start();
        you.start();
        mySelf.suspend();
        class Listen extends Thread {

            public Listen() {
                start();
            }

            @Override
            public void run() {
                listenSocket(port1);
            }
        }
        new Listen();
        class ListenGame extends Thread {

            public ListenGame() {
                start();
            }

            @Override
            public void run() {
                listenSocketGame(port2);
            }
        }
        new ListenGame();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        boardPanel = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                this.setOpaque(false);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                //Vẽ bàn cờ
                g.setColor(Color.GRAY);
                for(int r = 0;r<=Size;r++){
                    g.drawLine(X0, Y0+r*Width, X0+Size*Width, Y0+r*Width);
                }
                for(int c = 0;c<=Size;c++){
                    g.drawLine(X0+c*Width, Y0, X0+c*Width, Y0+Size*Width);
                }

                //Vẽ ô có chuột qua
                if(!isPause)
                if(currentColumn<Size&&currentColumn>=0&&currentRow<Size&&currentRow>=0){
                    g.setColor(new Color(255, 204, 204, 150));
                    g2.fillOval(X0+currentColumn*Width+Width/6+1,Y0+ currentRow*Width+Width/6+1, 2*Width/3, 2*Width/3);
                }
                //Vẽ các vị trí đã đánh
                if(checked.size()==0) return;
                for(int p = 0;p<checked.size();p++){
                    if(startUser){
                        if(p%2==0) g2.setColor(Color.BLACK);
                        else g2.setColor(Color.GREEN);
                    }else{
                        if(p%2!=0) g2.setColor(Color.BLACK);
                        else g2.setColor(Color.GREEN);
                    }
                    g2.fillOval(X0+checked.get(p).x*Width+Width/6+1,Y0+ checked.get(p).y*Width+Width/6+1, 2*Width/3, 2*Width/3);
                    //g2.drawString(String.valueOf(p),X0+checked.get(p).x*Width+12,Y0+ checked.get(p).y*Width+20);
                }
                //Đánh dấu ô mới đánh
                g.setColor(Color.RED);
                g.drawRect(checked.get(checked.size()-1).x*Width+X0, checked.get(checked.size()-1).y*Width+Y0, Width, Width);
                super.paintComponent(g);
            }
        }
        ;
        backButton = new javax.swing.JButton();
        myselfPanel = new javax.swing.JPanel();
        jLabel_ban = new javax.swing.JLabel();
        youPanel = new javax.swing.JPanel();
        jLabel_doiphuong = new javax.swing.JLabel();
        typingTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatEditorPane = new javax.swing.JEditorPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caro");

        boardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardPanelMouseClicked(evt);
            }
        });
        boardPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                boardPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        backButton.setText("Xin đi lại");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        myselfPanel.setBackground(new java.awt.Color(153, 255, 153));
        myselfPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        myselfPanel.setPreferredSize(new java.awt.Dimension(150, 150));

        jLabel_ban.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        javax.swing.GroupLayout myselfPanelLayout = new javax.swing.GroupLayout(myselfPanel);
        myselfPanel.setLayout(myselfPanelLayout);
        myselfPanelLayout.setHorizontalGroup(
            myselfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myselfPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myselfPanelLayout.setVerticalGroup(
            myselfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myselfPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        youPanel.setBackground(new java.awt.Color(255, 255, 153));
        youPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        youPanel.setPreferredSize(new java.awt.Dimension(150, 150));

        jLabel_doiphuong.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        javax.swing.GroupLayout youPanelLayout = new javax.swing.GroupLayout(youPanel);
        youPanel.setLayout(youPanelLayout);
        youPanelLayout.setHorizontalGroup(
            youPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(youPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_doiphuong, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        youPanelLayout.setVerticalGroup(
            youPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(youPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel_doiphuong, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        typingTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typingTextFieldActionPerformed(evt);
            }
        });

        sendButton.setText("Gửi");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Tạm dừng");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        chatEditorPane.setBackground(new java.awt.Color(204, 255, 255));
        chatEditorPane.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jScrollPane1.setViewportView(chatEditorPane);

        fileMenu.setText("File");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu2.setText("Options");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(myselfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(youPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(typingTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(youPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myselfPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backButton)
                            .addComponent(pauseButton))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boardPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseClicked
        if (isPause) {
            return;
        }
        Point p = new Point();
        //Kiểm tra vị trí có thuộc bàn cờ không?
        if ((currentColumn < Size && currentColumn >= 0 && currentRow < Size && currentRow >= 0)) {
            p = new Point(currentColumn, currentRow);
        } else {
            return;
        }
        if (!checked.contains(p)) {
            checked.add(p);
            currentPoint = new Point(p);
            currentColumn = -1;
            currentRow = -1;
            boardPanel.repaint();
            try {
                outGame.writeObject(currentPoint);//truyền thông tin
            } catch (IOException ex) {
                //Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isWin(false)) {
                JOptionPane.showMessageDialog(this, "Bạn đã thắng");
                startUser = true;
                checked.removeAllElements();
            }
            user = true;//tới quân đen
            you.resume();
            mySelf.suspend();
            myselfPanel.setBorder(new LineBorder(Color.BLACK));
        }
        isPause = true;
    }//GEN-LAST:event_boardPanelMouseClicked

    private void boardPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseMoved
        int CX = evt.getX();
        int CY = evt.getY();
        if (CY - Y0 < 0) {
            currentColumn = (CX - X0) / Width;
            currentRow = -1 + (CY - Y0) / Width;
            return;
        }
        if (CX - X0 < 0) {
            currentColumn = -1 + (CX - X0) / Width;
            currentRow = (CY - Y0) / Width;
            return;
        }
        currentColumn = (CX - X0) / Width;
        currentRow = (CY - Y0) / Width;
        Point p = new Point(currentColumn, currentRow);
        if (checked.contains(p)) {
            currentColumn = -1;
            currentRow = -1;

        }
        boardPanel.repaint();
        boardPanel.validate();
    }//GEN-LAST:event_boardPanelMouseMoved

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (checked.size() == 0) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn muốn xin đi lại", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            user = !user;
            checked.remove(checked.size() - 1);
            boardPanel.repaint();
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String s = typingTextField.getText();
        if (s.length() == 0) {
            return;
        }
        try {
            Vector d = new Vector();
            d.add(s);
            out.writeObject(d);
        } catch (IOException ex) {
            Logger.getLogger(CaroClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        chatEditorPane.setText(chatEditorPane.getText() + "me : " + s + "\n");//2 cho 
        typingTextField.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        isPause = !isPause;
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void typingTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typingTextFieldActionPerformed
        sendButtonActionPerformed(null);
    }//GEN-LAST:event_typingTextFieldActionPerformed
    /**
     * Tìm xung quanh quân vừa đánh theo hàng ngang, doc, chéo ngang
     * chéo chính. Nếu đủ 5 quân và không bị chặn 2 đầu thì thắng
     * @param user
     * @return
     */
    public boolean isWin(boolean user) {
        int n = 6;
        /**
         * Kiểm tra số quân xung quanh quân mới đánh nếu = 4
         * và không bị chặn 2 đầu thì thắng
         */
        int ok = 0;
        /**
         * kiểm tra có bị chặn 2 đầu không
         */
        int soDauBiChan = 0;
        int u;// u=0 nếu là user 1; u=1 nếu là user 2
        if (startUser) {
            if (user) {
                u = 0;
            } else {
                u = 1;
            }
        } else {
            if (user) {
                u = 1;
            } else {
                u = 0;
            }
        }
        //Kiểm tra hàng ngang
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y);
            if (!(p.x < Size)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y);
            if (!(p.x >= 0)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra hàng dọc
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x, currentPoint.y + i);
            if (!(p.y < Size)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x, currentPoint.y - i);
            if (!(p.y >= 0)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra đường chéo chính
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y + i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y - i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra đường chéo phụ
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y - i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y + i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        return false;
    }

    public void listenSocket(Integer port1) {
        //Create socket connection
        try {
            socket = new Socket(host, port1);
            OutputStream o = socket.getOutputStream();
            out = new ObjectOutputStream(o);
            InputStream i = socket.getInputStream();
            in = new ObjectInputStream(i);
        } catch (UnknownHostException e) {
            System.err.println("Server không tồn tại");
        } catch (IOException e) {
            System.err.println("Server không mở");
        }
        while (true) {
            try {
                Vector s = null;
                try {
                    s = (Vector) in.readObject();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.toFront();
                chatEditorPane.setText(chatEditorPane.getText() + "you : " + s.get(0).toString() + "\n");//1 cho
            } catch (IOException ex) {
            }
        }
    }

    public void listenSocketGame(Integer port2) {
        //Create socket connection
        try {
            socketGame = new Socket(host, port2);
            OutputStream o = socketGame.getOutputStream();
            outGame = new ObjectOutputStream(o);
            InputStream i = socketGame.getInputStream();
            inGame = new ObjectInputStream(i);
        } catch (UnknownHostException e) {
            System.err.println("Server không tồn tại");
        } catch (IOException e) {
            System.err.println("Server không mở");
        }
        while (true) {
            try {
                Point s = null;
                try {
                    s = (Point) inGame.readObject();
                    checked.add(s);
                    currentPoint = s;
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.toFront();
                boardPanel.repaint();
                if (isWin(true)) {//quân đen thắng
                    JOptionPane.showMessageDialog(this, "Bạn đã thua");
                    startUser = false;
                    checked.removeAllElements();
                    boardPanel.repaint();
                }
                user = false;//quân đỏ
                isPause = false;
                mySelf.resume();
                you.suspend();
                youPanel.setBorder(new LineBorder(Color.BLACK));
            } catch (IOException ex) {
            }
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new CaroClient(ip, port1, port2).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JEditorPane chatEditorPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel_ban;
    private javax.swing.JLabel jLabel_doiphuong;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel myselfPanel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField typingTextField;
    private javax.swing.JPanel youPanel;
    // End of variables declaration//GEN-END:variables
    private int X0 = 20;
    private int Y0 = 20;
    private int Width = 32;
    private int Size = 15;
    private int currentRow = -1;
    private int currentColumn = -1;
    private Point currentPoint = new Point();
    /**
     * true  là user 1(màu đen)
     * false là user 2(màu đỏ)
     */
    private boolean user = false;
    /**
     * Vị trí chẵn lưu các điểm đã đánh của user 1
     * Vị trí lẻ lưu các điểm đã đánh của user 2
     */
    private Vector<Point> checked = new Vector<Point>();
    PlayNow mySelf;
    PlayNow you;
    Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    Socket socketGame = null;
    ObjectInputStream inGame = null;
    ObjectOutputStream outGame = null;
    boolean isPause = true;
    /**
     * true : quân đen đi trước
     * false  quân đỏ đi trước
     */
    boolean startUser = true;//quân đen đi trước
    String host;
}
