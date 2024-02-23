package kiemtra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JFrame {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 44444;

    private Socket socket;
    private PrintWriter writer;
    private String name;

    private JTextArea chatArea;
    private JTextField messageField;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private JButton sendFileButton;

    public Client() {
        // Hộp thoại để người dùng nhập tên
        name = JOptionPane.showInputDialog("Enter your name:");

        setTitle("Chat Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Thiết lập các thuộc tính tự động của GroupLayout
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Khởi tạo các thành phần giao diện
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();
        messageField.addActionListener(new SendMessageListener());

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendMessageListener());

        sendFileButton = new JButton("File & IMG");
        sendFileButton.addActionListener(new SendFileListener());

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        JScrollPane fileListScrollPane = new JScrollPane(fileList);

        // Thiết lập GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane)
                .addComponent(messageField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(sendButton)
                .addComponent(sendFileButton)
                .addComponent(fileListScrollPane)));

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(scrollPane)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(sendButton)
                    .addComponent(sendFileButton)
                    .addComponent(fileListScrollPane)))
            .addComponent(messageField));

        setVisible(true);

        connectToServer();
        startReading();
    }
    private class SendFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(Client.this);
    
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
    
                // Hiển thị trước ảnh
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                JOptionPane.showMessageDialog(Client.this, imageIcon, "Preview", JOptionPane.PLAIN_MESSAGE);
    
                writer.println("[FILE]" + fileName); // Gửi tên file đến server
                writer.println("[ENDFILE]"); // Đánh dấu kết thúc file
                // Thêm thông tin client và tên file vào JList của client trước khi gửi file
                String clientFileInfo = name + ": " + fileName;
                listModel.addElement(clientFileInfo);
            }
        }
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(name); // Gửi tên của người dùng tới server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReading() {
        Thread readThread = new Thread(() -> {
            try {
                Scanner serverScanner = new Scanner(socket.getInputStream());
                while (serverScanner.hasNextLine()) {
                    String message = serverScanner.nextLine();
                    if (message.startsWith("[FILE]")) {
                        // Nếu tin nhắn bắt đầu với [FILE], đó là tên file
                        String fileName = message.substring(6); // Loại bỏ '[FILE]'
                        SwingUtilities.invokeLater(() -> listModel.addElement(fileName)); // Thêm tên file vào danh sách
                    } else {
                        chatArea.append(message + "\n"); // Hiển thị tin nhắn từ server (bao gồm tên của người gửi)
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        readThread.start();
    }

    private class SendMessageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            writer.println(name + ": " + message); // Gửi tin nhắn với tên của người gửi
            messageField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }
}
