package IHM;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Chat extends JFrame {


    // ── Palette ──────────────────────────────────────────────────────────────
    private static final Color BG_DARK      = new Color(13, 17, 23);
    private static final Color BG_PANEL     = new Color(22, 27, 34);
    private static final Color BG_INPUT     = new Color(33, 38, 45);
    private static final Color ACCENT       = new Color(88, 166, 255);
    private static final Color ACCENT_DARK  = new Color(56, 139, 253);
    private static final Color MSG_SELF_BG  = new Color(31, 111, 235);
    private static final Color MSG_OTHER_BG = new Color(33, 38, 45);
    private static final Color TEXT_PRIMARY = new Color(230, 237, 243);
    private static final Color TEXT_MUTED   = new Color(125, 133, 144);
    private static final Color BORDER_COLOR = new Color(48, 54, 61);
    private static final Color ONLINE_GREEN = new Color(63, 185, 80);

    // ── Fonts ─────────────────────────────────────────────────────────────────
    private static final Font FONT_TITLE   = new Font("SansSerif", Font.BOLD, 15);
    private static final Font FONT_MSG     = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FONT_TIME    = new Font("SansSerif", Font.PLAIN, 11);
    private static final Font FONT_INPUT   = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FONT_STATUS  = new Font("SansSerif", Font.PLAIN, 12);

    // ── Composants ────────────────────────────────────────────────────────────
    private JPanel  messagesPanel;
    private JScrollPane scrollPane;
    private JTextArea inputField;
    private JButton sendButton;
    private JLabel  statusLabel;
    private JLabel  typingLabel;

    private final String myUsername;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    // ─────────────────────────────────────────────────────────────────────────
    public Chat(String username, String peerName) {
        this.myUsername = username;

        setTitle("Chat — " + peerName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 720);
        setMinimumSize(new Dimension(380, 500));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout());

        add(buildHeader(peerName), BorderLayout.NORTH);
        add(buildMessagesArea(),   BorderLayout.CENTER);
        add(buildInputBar(),       BorderLayout.SOUTH);

        setVisible(true);

        // Message de bienvenue (supprime si tu n'en veux pas)
        appendMessage("Système", "Connexion établie avec " + peerName + " ✓", false, true);
    }

    // ── Header ────────────────────────────────────────────────────────────────
    private JPanel buildHeader(String peerName) {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(BG_PANEL);
                g2.fillRect(0, 0, getWidth(), getHeight());
                // bottom border
                g2.setColor(BORDER_COLOR);
                g2.fillRect(0, getHeight() - 1, getWidth(), 1);
            }
        };
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(14, 18, 14, 18));

        // Avatar + info
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        left.setOpaque(false);

        JPanel avatar = new AvatarPanel(peerName, 40);
        left.add(avatar);

        JPanel info = new JPanel(new GridLayout(2, 1, 0, 2));
        info.setOpaque(false);
        JLabel nameLabel = new JLabel(peerName);
        nameLabel.setFont(FONT_TITLE);
        nameLabel.setForeground(TEXT_PRIMARY);

        statusLabel = new JLabel("● En ligne");
        statusLabel.setFont(FONT_STATUS);
        statusLabel.setForeground(ONLINE_GREEN);

        info.add(nameLabel);
        info.add(statusLabel);
        left.add(info);
        header.add(left, BorderLayout.WEST);

        // Bouton options (3 points)
        JButton menuBtn = new JButton("•••");
        menuBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        menuBtn.setForeground(TEXT_MUTED);
        menuBtn.setBackground(null);
        menuBtn.setBorderPainted(false);
        menuBtn.setContentAreaFilled(false);
        menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(menuBtn, BorderLayout.EAST);

        return header;
    }

    // ── Zone messages ─────────────────────────────────────────────────────────
    private JScrollPane buildMessagesArea() {
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(BG_DARK);
        messagesPanel.setBorder(new EmptyBorder(16, 12, 8, 12));

        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(BG_DARK);
        scrollPane.getViewport().setBackground(BG_DARK);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBackground(BG_DARK);

        return scrollPane;
    }

    // ── Barre de saisie ───────────────────────────────────────────────────────
    private JPanel buildInputBar() {
        JPanel wrapper = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(BG_PANEL);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(BORDER_COLOR);
                g.fillRect(0, 0, getWidth(), 1);
            }
        };
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(10, 14, 14, 14));

        // Label "typing..."
        typingLabel = new JLabel(" ");
        typingLabel.setFont(FONT_TIME);
        typingLabel.setForeground(TEXT_MUTED);
        typingLabel.setBorder(new EmptyBorder(0, 4, 4, 0));
        wrapper.add(typingLabel, BorderLayout.NORTH);

        // Conteneur input + bouton
        JPanel inputRow = new JPanel(new BorderLayout(10, 0));
        inputRow.setOpaque(false);

        inputField = new JTextArea(2, 1);
        inputField.setFont(FONT_INPUT);
        inputField.setForeground(TEXT_PRIMARY);
        inputField.setBackground(BG_INPUT);
        inputField.setCaretColor(ACCENT);
        inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);
        inputField.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(10, 14, 10, 14)
        ));

        JScrollPane inputScroll = new JScrollPane(inputField);
        inputScroll.setBorder(new LineBorder(BORDER_COLOR, 1, true));
        inputScroll.setBackground(BG_INPUT);
        inputScroll.getViewport().setBackground(BG_INPUT);
        inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sendButton = new RoundButton("➤");
        sendButton.setPreferredSize(new Dimension(48, 48));
        sendButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        sendButton.setForeground(Color.WHITE);
        sendButton.setBackground(MSG_SELF_BG);
        sendButton.setBorderPainted(false);
        sendButton.setContentAreaFilled(false);
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        inputRow.add(inputScroll, BorderLayout.CENTER);
        inputRow.add(sendButton,  BorderLayout.EAST);
        wrapper.add(inputRow, BorderLayout.CENTER);

        // ── Événements ────────────────────────────────────────────────────────
        sendButton.addActionListener(e -> sendMessage());

        inputField.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    sendMessage();
                }
            }
        });

        // Placeholder
        inputField.setText("Écrivez un message…");
        inputField.setForeground(TEXT_MUTED);
        inputField.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Écrivez un message…")) {
                    inputField.setText("");
                    inputField.setForeground(TEXT_PRIMARY);
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (inputField.getText().isBlank()) {
                    inputField.setText("Écrivez un message…");
                    inputField.setForeground(TEXT_MUTED);
                }
            }
        });

        return wrapper;
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (text.isEmpty() || text.equals("Écrivez un message…")) return;

        appendMessage(myUsername, text, true, false);
        inputField.setText("");
        inputField.setForeground(TEXT_PRIMARY);

        // TODO: envoyer via socket → out.println(text);
    }

    public void appendMessage(String sender, String text, boolean isSelf, boolean isSystem) {
        SwingUtilities.invokeLater(() -> {
            if (isSystem) {
                messagesPanel.add(buildSystemMsg(text));
            } else {
                messagesPanel.add(buildBubble(sender, text, isSelf));
            }
            messagesPanel.add(Box.createVerticalStrut(6));
            messagesPanel.revalidate();
            messagesPanel.repaint();
            scrollToBottom();
        });
    }

    public void setStatus(String status, boolean online) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("● " + status);
            statusLabel.setForeground(online ? ONLINE_GREEN : TEXT_MUTED);
        });
    }

    /** Affiche / masque "X est en train d'écrire…" */
    public void setTyping(String peerName, boolean typing) {
        SwingUtilities.invokeLater(() ->
                typingLabel.setText(typing ? peerName + " est en train d'écrire…" : " ")
        );
    }

    // ── Constructeurs de bulles ───────────────────────────────────────────────
    private JPanel buildBubble(String sender, String text, boolean isSelf) {
        JPanel row = new JPanel(new FlowLayout(
                isSelf ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel bubble = new JPanel(new BorderLayout(0, 4)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isSelf ? MSG_SELF_BG : MSG_OTHER_BG);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
            }
        };
        bubble.setOpaque(false);
        bubble.setBorder(new EmptyBorder(10, 14, 10, 14));

        if (!isSelf) {
            JLabel nameLabel = new JLabel(sender);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            nameLabel.setForeground(ACCENT);
            bubble.add(nameLabel, BorderLayout.NORTH);
        }

        JTextArea msgText = new JTextArea(text);
        msgText.setFont(FONT_MSG);
        msgText.setForeground(TEXT_PRIMARY);
        msgText.setBackground(new Color(0, 0, 0, 0));
        msgText.setOpaque(false);
        msgText.setEditable(false);
        msgText.setLineWrap(true);
        msgText.setWrapStyleWord(true);
        msgText.setFocusable(false);
        // Largeur max bulle ≈ 65% de la fenêtre
        msgText.setPreferredSize(null);
        bubble.add(msgText, BorderLayout.CENTER);

        JLabel timeLabel = new JLabel(sdf.format(new Date()));
        timeLabel.setFont(FONT_TIME);
        timeLabel.setForeground(isSelf ? new Color(200, 220, 255, 160) : TEXT_MUTED);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        bubble.add(timeLabel, BorderLayout.SOUTH);

        // Limite la largeur de la bulle
        bubble.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        bubble.setPreferredSize(bubble.getPreferredSize());

        row.add(bubble);
        return row;
    }

    private JPanel buildSystemMsg(String text) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row.setOpaque(false);
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_TIME);
        lbl.setForeground(TEXT_MUTED);
        lbl.setBorder(new EmptyBorder(4, 12, 4, 12));
        row.add(lbl);
        return row;
    }

    private void scrollToBottom() {
        JScrollBar bar = scrollPane.getVerticalScrollBar();
        SwingUtilities.invokeLater(() -> bar.setValue(bar.getMaximum()));
    }

    static class AvatarPanel extends JPanel {
        private final String initials;
        private static final Color[] COLORS = {
                new Color(88, 166, 255), new Color(63, 185, 80),
                new Color(248, 81, 73),  new Color(210, 153, 34)
        };
        AvatarPanel(String name, int size) {
            this.initials = name.isEmpty() ? "?" : String.valueOf(name.charAt(0)).toUpperCase();
            setPreferredSize(new Dimension(size, size));
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int size = Math.min(getWidth(), getHeight());
            Color c = COLORS[Math.abs(initials.hashCode()) % COLORS.length];
            g2.setColor(c);
            g2.fillOval(0, 0, size, size);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, size / 2));
            FontMetrics fm = g2.getFontMetrics();
            int x = (size - fm.stringWidth(initials)) / 2;
            int y = (size - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(initials, x, y);
        }
    }

    static class RoundButton extends JButton {
        RoundButton(String label) { super(label); setFocusPainted(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? ACCENT_DARK : MSG_SELF_BG);
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth()  - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(getText(), x, y);
        }
        @Override public boolean isOpaque() { return false; }
    }

    // ── Point d'entrée (démo) ─────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
            catch (Exception ignored) {}

            Chat ui = new Chat("Moi", "Alice");

            // Simulation de messages pour la démo — retire cette section en prod
            Timer demo = new Timer(1200, null);
            String[][] msgs = {
                    {"Alice", "Salut ! Tu m'entends ?", "false"},
                    {"Moi",   "Oui, parfaitement !",    "true"},
                    {"Alice", "Super, le socket fonctionne 🎉", "false"},
                    {"Moi",   "Interface sympa non ?",  "true"},
            };
            final int[] i = {0};
            demo.addActionListener(e -> {
                if (i[0] < msgs.length) {
                    String[] m = msgs[i[0]++];
                    boolean self = m[1].equals("Moi") || Boolean.parseBoolean(m[2]);
                    ui.appendMessage(m[0], m[1], self, false);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            });
            demo.start();
        });
    }
}
