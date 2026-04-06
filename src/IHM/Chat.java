package IHM;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Chat extends JFrame {

    // ── Palette ──────────────────────────────────────────────────────────────
    static final Color BG_SIDEBAR   = new Color(24,  28,  40);
    static final Color BG_CHAT      = new Color(17,  20,  30);
    static final Color BG_INPUT     = new Color(30,  34,  50);
    static final Color ACCENT       = new Color(99, 179, 237);
    static final Color ACCENT_DARK  = new Color(49, 130, 206);
    static final Color BUBBLE_ME    = new Color(49, 130, 206);
    static final Color BUBBLE_OTHER = new Color(38,  42,  60);
    static final Color TEXT_PRIMARY = new Color(237, 242, 247);
    static final Color TEXT_MUTED   = new Color(113, 128, 150);
    static final Color ONLINE_DOT   = new Color(72,  187, 120);
    static final Color DIVIDER      = new Color(45,  50,  70);
    static final Color HOVER_ITEM   = new Color(35,  40,  58);
    static final Color SELECTED_ITEM= new Color(44,  51,  74);

    // ── Data ─────────────────────────────────────────────────────────────────
    static final String ME = "Moi";
    static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    record Client(String name, String avatar, boolean online, String lastMsg) {}
    record Message(String sender, String text, LocalTime time) {}

    List<Client> clients = List.of(
            new Client("Alice Martin",   "AM", true,  "D'accord, je regarde ça !"),
            new Client("Bob Dupont",     "BD", true,  "Merci pour l'info 👍"),
            new Client("Clara Moreau",   "CM", false, "On se parle demain ?"),
            new Client("David Leroy",    "DL", true,  "Fichier reçu, merci."),
            new Client("Emma Bernard",   "EB", false, "Super, bonne continuation !"),
            new Client("François Petit", "FP", true,  "Je suis disponible à 14h")
    );

    Map<String, List<Message>> conversations = new HashMap<>();
    String selectedClient = null;

    // ── UI Components ─────────────────────────────────────────────────────────
    JPanel chatMessagesPanel;
    JScrollPane chatScroll;
    JTextField inputField;
    JButton sendButton;
    JLabel chatHeaderName, chatHeaderStatus;
    JLabel chatHeaderAvatar;
    JPanel clientListPanel;

    public Chat() {
        initConversations();
        buildUI();
        selectClient(clients.get(0).name());
    }

    // ─────────────────────────────────────────────────────────────────────────
    void initConversations() {
        conversations.put("Alice Martin", new ArrayList<>(List.of(
                new Message("Alice Martin", "Bonjour ! Tu as reçu le rapport ?", LocalTime.of(9, 10)),
                new Message(ME,             "Oui, je l'ai eu ce matin.",          LocalTime.of(9, 12)),
                new Message("Alice Martin", "D'accord, je regarde ça !",          LocalTime.of(9, 13))
        )));
        conversations.put("Bob Dupont", new ArrayList<>(List.of(
                new Message(ME,          "Réunion confirmée pour demain.",  LocalTime.of(10, 0)),
                new Message("Bob Dupont","Merci pour l'info 👍",            LocalTime.of(10, 5))
        )));
        for (Client c : clients) {
            conversations.putIfAbsent(c.name(), new ArrayList<>());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    void buildUI() {
        setTitle("Chat — Espace Client");
        setSize(1000, 680);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_CHAT);
        setLayout(new BorderLayout());

        add(buildSidebar(), BorderLayout.WEST);
        add(buildChatArea(), BorderLayout.CENTER);
    }

    // ── Sidebar ───────────────────────────────────────────────────────────────
    JPanel buildSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setBorder(new MatteBorder(0, 0, 0, 1, DIVIDER));

        // Header sidebar
        JPanel sideHeader = new JPanel(new BorderLayout());
        sideHeader.setBackground(BG_SIDEBAR);
        sideHeader.setBorder(new EmptyBorder(20, 18, 12, 18));

        JLabel title = new JLabel("Messages");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(TEXT_PRIMARY);

        JLabel badge = new JLabel("" + clients.stream().filter(Client::online).count() + " en ligne");
        badge.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        badge.setForeground(ONLINE_DOT);

        sideHeader.add(title, BorderLayout.CENTER);
        sideHeader.add(badge, BorderLayout.SOUTH);

        // Search bar
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(BG_SIDEBAR);
        searchPanel.setBorder(new EmptyBorder(0, 14, 14, 14));

        JTextField search = new JTextField();
        search.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        search.setBackground(HOVER_ITEM);
        search.setForeground(TEXT_PRIMARY);
        search.setCaretColor(ACCENT);
        search.setBorder(new CompoundBorder(
                new LineBorder(DIVIDER, 1, true),
                new EmptyBorder(7, 12, 7, 12)
        ));
        search.putClientProperty("JTextField.placeholderText", "Rechercher…");

        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setBorder(new EmptyBorder(0, 8, 0, 4));

        searchPanel.add(searchIcon, BorderLayout.WEST);
        searchPanel.add(search, BorderLayout.CENTER);

        // Client list
        clientListPanel = new JPanel();
        clientListPanel.setLayout(new BoxLayout(clientListPanel, BoxLayout.Y_AXIS));
        clientListPanel.setBackground(BG_SIDEBAR);

        for (Client c : clients) {
            clientListPanel.add(buildClientRow(c));
        }

        JScrollPane listScroll = new JScrollPane(clientListPanel);
        listScroll.setBorder(null);
        listScroll.getViewport().setBackground(BG_SIDEBAR);
        listScroll.getVerticalScrollBar().setUnitIncrement(12);
        styleScrollBar(listScroll);

        JPanel topPart = new JPanel(new BorderLayout());
        topPart.setBackground(BG_SIDEBAR);
        topPart.add(sideHeader, BorderLayout.NORTH);
        topPart.add(searchPanel, BorderLayout.SOUTH);

        sidebar.add(topPart,    BorderLayout.NORTH);
        sidebar.add(listScroll, BorderLayout.CENTER);

        return sidebar;
    }

    JPanel buildClientRow(Client c) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setBackground(BG_SIDEBAR);
        row.setBorder(new EmptyBorder(10, 14, 10, 14));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 72));
        row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Avatar
        JLabel avatar = new JLabel(c.avatar(), SwingConstants.CENTER) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(avatarColor(c.name()));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        avatar.setForeground(Color.WHITE);
        avatar.setPreferredSize(new Dimension(44, 44));
        avatar.setOpaque(false);

        // Online dot overlay
        JPanel avatarWrapper = new JPanel(null);
        avatarWrapper.setOpaque(false);
        avatarWrapper.setPreferredSize(new Dimension(50, 50));
        avatar.setBounds(0, 3, 44, 44);
        avatarWrapper.add(avatar);

        if (c.online()) {
            JLabel dot = new JLabel() {
                @Override protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(BG_SIDEBAR);
                    g2.fillOval(0, 0, 14, 14);
                    g2.setColor(ONLINE_DOT);
                    g2.fillOval(2, 2, 10, 10);
                    g2.dispose();
                }
            };
            dot.setBounds(32, 34, 14, 14);
            avatarWrapper.add(dot);
        }

        // Text info
        JLabel nameLabel = new JLabel(c.name());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nameLabel.setForeground(TEXT_PRIMARY);

        JLabel lastLabel = new JLabel(c.lastMsg());
        lastLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lastLabel.setForeground(TEXT_MUTED);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        textPanel.setOpaque(false);
        textPanel.add(nameLabel);
        textPanel.add(lastLabel);

        row.add(avatarWrapper, BorderLayout.WEST);
        row.add(textPanel,     BorderLayout.CENTER);

        // Hover + click
        row.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (!c.name().equals(selectedClient))
                    row.setBackground(HOVER_ITEM);
            }
            @Override public void mouseExited(MouseEvent e) {
                if (!c.name().equals(selectedClient))
                    row.setBackground(BG_SIDEBAR);
            }
            @Override public void mouseClicked(MouseEvent e) {
                selectClient(c.name());
            }
        });

        row.putClientProperty("clientName", c.name());
        return row;
    }

    // ── Chat Area ─────────────────────────────────────────────────────────────
    JPanel buildChatArea() {
        JPanel area = new JPanel(new BorderLayout());
        area.setBackground(BG_CHAT);

        // Chat header
        JPanel header = new JPanel(new BorderLayout(12, 0));
        header.setBackground(BG_SIDEBAR);
        header.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, DIVIDER),
                new EmptyBorder(14, 20, 14, 20)
        ));

        chatHeaderAvatar = new JLabel("", SwingConstants.CENTER) {
            @Override protected void paintComponent(Graphics g) {
                if (selectedClient == null) return;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(avatarColor(selectedClient));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        chatHeaderAvatar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        chatHeaderAvatar.setForeground(Color.WHITE);
        chatHeaderAvatar.setPreferredSize(new Dimension(44, 44));
        chatHeaderAvatar.setOpaque(false);

        chatHeaderName   = new JLabel();
        chatHeaderName.setFont(new Font("Segoe UI", Font.BOLD, 15));
        chatHeaderName.setForeground(TEXT_PRIMARY);

        chatHeaderStatus = new JLabel();
        chatHeaderStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chatHeaderStatus.setForeground(ONLINE_DOT);

        JPanel headerText = new JPanel(new GridLayout(2, 1, 0, 2));
        headerText.setOpaque(false);
        headerText.add(chatHeaderName);
        headerText.add(chatHeaderStatus);

        header.add(chatHeaderAvatar, BorderLayout.WEST);
        header.add(headerText,       BorderLayout.CENTER);

        // Messages panel
        chatMessagesPanel = new JPanel();
        chatMessagesPanel.setLayout(new BoxLayout(chatMessagesPanel, BoxLayout.Y_AXIS));
        chatMessagesPanel.setBackground(BG_CHAT);
        chatMessagesPanel.setBorder(new EmptyBorder(16, 16, 8, 16));

        chatScroll = new JScrollPane(chatMessagesPanel);
        chatScroll.setBorder(null);
        chatScroll.getViewport().setBackground(BG_CHAT);
        chatScroll.getVerticalScrollBar().setUnitIncrement(16);
        styleScrollBar(chatScroll);

        // Input bar
        JPanel inputBar = new JPanel(new BorderLayout(10, 0));
        inputBar.setBackground(BG_INPUT);
        inputBar.setBorder(new CompoundBorder(
                new MatteBorder(1, 0, 0, 0, DIVIDER),
                new EmptyBorder(12, 18, 12, 18)
        ));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBackground(BG_CHAT);
        inputField.setForeground(TEXT_PRIMARY);
        inputField.setCaretColor(ACCENT);
        inputField.setBorder(new CompoundBorder(
                new LineBorder(DIVIDER, 1, true),
                new EmptyBorder(10, 14, 10, 14)
        ));

        sendButton = new JButton("Envoyer") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? ACCENT_DARK : ACCENT);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        sendButton.setForeground(Color.WHITE);
        sendButton.setOpaque(false);
        sendButton.setContentAreaFilled(false);
        sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(100, 42));
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        inputBar.add(inputField, BorderLayout.CENTER);
        inputBar.add(sendButton, BorderLayout.EAST);

        // Actions
        ActionListener sendAction = e -> sendMessage();
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        area.add(header,    BorderLayout.NORTH);
        area.add(chatScroll,BorderLayout.CENTER);
        area.add(inputBar,  BorderLayout.SOUTH);

        return area;
    }

    // ── Logic ─────────────────────────────────────────────────────────────────
    void selectClient(String name) {
        selectedClient = name;

        // Update sidebar highlight
        for (Component comp : clientListPanel.getComponents()) {
            if (comp instanceof JPanel row) {
                Object prop = row.getClientProperty("clientName");
                boolean sel = name.equals(prop);
                row.setBackground(sel ? SELECTED_ITEM : BG_SIDEBAR);
            }
        }

        // Update header
        Client c = clients.stream().filter(cl -> cl.name().equals(name)).findFirst().orElse(null);
        if (c == null) return;

        String initials = Arrays.stream(c.name().split(" "))
                .map(w -> String.valueOf(w.charAt(0))).reduce("", String::concat);
        chatHeaderAvatar.setText(initials);
        chatHeaderAvatar.repaint();
        chatHeaderName.setText(c.name());
        chatHeaderStatus.setText(c.online() ? "● En ligne" : "○ Hors ligne");
        chatHeaderStatus.setForeground(c.online() ? ONLINE_DOT : TEXT_MUTED);

        // Reload messages
        refreshMessages();
    }

    void refreshMessages() {
        chatMessagesPanel.removeAll();
        List<Message> msgs = conversations.getOrDefault(selectedClient, List.of());

        String lastDate = "";
        for (Message m : msgs) {
            // Day separator (simplified)
            String today = "Aujourd'hui";
            if (!today.equals(lastDate)) {
                chatMessagesPanel.add(buildDateSeparator(today));
                lastDate = today;
            }
            boolean isMe = m.sender().equals(ME);
            chatMessagesPanel.add(buildBubble(m, isMe));
            chatMessagesPanel.add(Box.createVerticalStrut(6));
        }

        chatMessagesPanel.add(Box.createVerticalGlue());
        chatMessagesPanel.revalidate();
        chatMessagesPanel.repaint();
        scrollToBottom();
    }

    void sendMessage() {
        String text = inputField.getText().trim();
        if (text.isEmpty() || selectedClient == null) return;

        Message msg = new Message(ME, text, LocalTime.now());
        conversations.computeIfAbsent(selectedClient, k -> new ArrayList<>()).add(msg);

        inputField.setText("");
        refreshMessages();
    }

    void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar sb = chatScroll.getVerticalScrollBar();
            sb.setValue(sb.getMaximum());
        });
    }

    // ── Bubble Builder ────────────────────────────────────────────────────────
    JPanel buildBubble(Message m, boolean isMe) {
        JPanel wrapper = new JPanel(new FlowLayout(isMe ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 0));
        wrapper.setOpaque(false);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel bubble = new JPanel(new BorderLayout(0, 4)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isMe ? BUBBLE_ME : BUBBLE_OTHER);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
            }
        };
        bubble.setOpaque(false);
        bubble.setBorder(new EmptyBorder(10, 14, 10, 14));

        JTextArea textArea = new JTextArea(m.text());
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textArea.setForeground(TEXT_PRIMARY);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Limit bubble width
        int maxWidth = 380;
        textArea.setSize(maxWidth, Short.MAX_VALUE);
        int prefH = textArea.getPreferredSize().height;
        textArea.setPreferredSize(new Dimension(maxWidth, prefH));

        JLabel timeLabel = new JLabel(m.time().format(TIME_FMT));
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        timeLabel.setForeground(new Color(255, 255, 255, 120));
        timeLabel.setHorizontalAlignment(isMe ? SwingConstants.RIGHT : SwingConstants.LEFT);

        if (!isMe) {
            JLabel senderLabel = new JLabel(m.sender());
            senderLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
            senderLabel.setForeground(ACCENT);
            bubble.add(senderLabel, BorderLayout.NORTH);
        }

        bubble.add(textArea,   BorderLayout.CENTER);
        bubble.add(timeLabel,  BorderLayout.SOUTH);

        wrapper.add(bubble);
        return wrapper;
    }

    JPanel buildDateSeparator(String label) {
        JPanel sep = new JPanel(new BorderLayout(8, 0));
        sep.setOpaque(false);
        sep.setBorder(new EmptyBorder(8, 0, 8, 0));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JSeparator left  = new JSeparator(); left.setForeground(DIVIDER);
        JSeparator right = new JSeparator(); right.setForeground(DIVIDER);
        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(TEXT_MUTED);

        sep.add(left,  BorderLayout.WEST);
        sep.add(lbl,   BorderLayout.CENTER);
        sep.add(right, BorderLayout.EAST);
        return sep;
    }

    // ── Utilities ─────────────────────────────────────────────────────────────
    Color avatarColor(String name) {
        Color[] palette = {
                new Color(99, 179, 237), new Color(154, 117, 234),
                new Color(72, 187, 120), new Color(246, 173, 85),
                new Color(252, 129, 129), new Color(76, 201, 240)
        };
        return palette[Math.abs(name.hashCode()) % palette.length];
    }

    void styleScrollBar(JScrollPane sp) {
        sp.getVerticalScrollBar().setBackground(BG_SIDEBAR);
        sp.getVerticalScrollBar().setOpaque(true);
    }

    // ── Entry Point ───────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            Chat app = new Chat();
            app.setVisible(true);
        });
    }
}