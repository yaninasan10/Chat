import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;

public class ChatWindow extends JFrame {
    public interface ChatListener {
        void sendAnswer(String str, int ind);

        ArrayList<String> sortByAlphabet();

        ArrayList<String> sortByLastDate();
    }

    public void setListener(ChatListener listener_) {
        listener = listener_;
    }


    private JPanel panel;
    private JButton sendButton;
    private JButton changeButton;
    private JTextField field;
    private JFrame mainFrame;
    private DefaultListModel model;
    private JList bagList;
    private JLabel label;
    private JLabel label1;
    private JButton smileButton;
    private GridBagConstraints constraints;
    private Vector<String> vectorToListDog = new Vector<String>();
    private Vector<String> vectorToListCat = new Vector<String>();
    private Vector<String> vectorToListParrot = new Vector<String>();
    private Vector<String> vectorMessage = new Vector<String>();
    private JPanel panelWithSmiles;
    private JPanel lpane;
    private ArrayList<JButton> smileList = new ArrayList<>();
    int IND = 1;
    private ChatListener listener;


    public Vector<String> getVectorDog() {
        return vectorToListDog;
    }

    public Vector<String> getVectorCat() {
        return vectorToListCat;
    }

    public Vector<String> getVectorParrot() {
        return vectorToListParrot;
    }

    public Vector<String> getVectorMessage() {
        return vectorMessage;
    }

    public JTextField getField() {
        return field;
    }

    public void updateList(int ind) {
        model.removeAllElements();
        bagList.setModel(model);
        if (ind == 0) {
            bagList.setListData(this.getVectorDog());
            saveDogMessages();
        }
        if (ind == 1) {
            bagList.setListData(this.getVectorCat());
            saveCatMessages();
        }
        if (ind == 2) {
            bagList.setListData(this.getVectorParrot());
            saveParrotMessages();
        }
    }


    private void saveCatMessages() {
        String messages = "";
        for (String mes : vectorToListCat) {
            messages = messages.concat(mes + "\n");
        }
        try (FileWriter writer = new FileWriter("Cat.txt", false)) {
            writer.write(messages);
        } catch (IOException exc) {
            return;
        }
    }

    private void saveDogMessages() {
        String messages = "";
        for (String mes : vectorToListDog) {
            messages = messages.concat(mes + "\n");
        }
        try (FileWriter writer = new FileWriter("Dog.txt", false)) {
            writer.write(messages);
        } catch (IOException exc) {
            return;
        }
    }

    private void saveParrotMessages() {
        String messages = "";
        for (String mes : vectorToListParrot) {
            messages = messages.concat(mes + "\n");
        }
        try (FileWriter writer = new FileWriter("Parrot.txt", false)) {
            writer.write(messages);
        } catch (IOException exc) {
            return;
        }
    }

    ChatWindow() {
        setPreferredSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        lpane = new JPanel();
        lpane.setLayout(null);
        lpane.setPreferredSize(new Dimension(600, 400));
        panel.setBounds(0, 0, 400, 400);
        panel.setOpaque(true);

        smileList.add(new JButton("(o˘◡˘o)"));
        smileList.add(new JButton("(◕‿◕✿)"));
        smileList.add(new JButton("ʕ •̀ o •́ ʔ"));
        smileList.add(new JButton("(^˵◕ω◕˵^)"));
        panelWithSmiles = new JPanel(new FlowLayout());
        panelWithSmiles.setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < 4; ++i) {
            panelWithSmiles.add(smileList.get(i));
        }
        for (int i = 0; i < 4; ++i) {
            int I = i;
            smileList.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    field.setText(smileList.get(I).getText());
                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {
                }

                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    panelWithSmiles.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                }
            });
        }

        panelWithSmiles.setBackground(Color.PINK);
        panelWithSmiles.setBounds(190, 180, 100, 130);
        panelWithSmiles.setVisible(false);

        panel.setLayout(new GridBagLayout());
        sendButton = new JButton("Send");
        field = new JTextField();
        model = new DefaultListModel();
        bagList = new JList(model);


        try {
            vectorToListCat.addAll(Files.readAllLines(Paths.get("Cat.txt")));
        } catch (IOException ex) {

        }
        try {
            vectorToListDog.addAll(Files.readAllLines(Paths.get("Dog.txt")));
        } catch (IOException ex) {

        }

        try {
            vectorToListParrot.addAll(Files.readAllLines(Paths.get("Parrot.txt")));
        } catch (IOException ex) {

        }

        bagList.setListData(this.getVectorDog());
        bagList.setListData(this.getVectorCat());
        bagList.setListData(this.getVectorParrot());

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                listener.sendAnswer(field.getText(), IND);
                updateList(IND);
            }
        });

        changeButton = new JButton("Choose dialog");
        mainFrame = new JFrame();
        label = new JLabel("Cat is online");
        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Object[] possibilities_ = {"By alphabet", "By last date"};
                String s = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "What do you prefer?",
                        "Choose way of sorting your dialogs",
                        JOptionPane.PLAIN_MESSAGE, null,
                        possibilities_,
                        "By alphabet");
                Object[] poss = {};
                ArrayList<String> people = new ArrayList<String>();
                if (s.equals("By alphabet")) {
                    people = listener.sortByAlphabet();
                    poss = people.toArray();
                }
                if (s.equals("By last date")) {
                    people = listener.sortByLastDate();
                    poss = people.toArray();
                }
                String s_ = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Who would you like to talk to?",
                        "Choose dialog",
                        JOptionPane.PLAIN_MESSAGE, null,
                        poss,
                        "Cat");
                if (s_.equals("Dog")) {
                    IND = 0;
                    JOptionPane.showMessageDialog(mainFrame, "You are talking to Dog right now!",
                            "Conversation", JOptionPane.PLAIN_MESSAGE);
                    label.setText("Dog is online");
                }
                if (s_.equals("Cat")) {
                    IND = 1;
                    JOptionPane.showMessageDialog(mainFrame, "You are talking to Cat right now!",
                            "Conversation", JOptionPane.PLAIN_MESSAGE);
                    label.setText("Cat is online");
                }
                if (s_.equals("Parrot")) {
                    IND = 2;
                    JOptionPane.showMessageDialog(mainFrame, "You are talking to Parrot right now!",
                            "Conversation", JOptionPane.PLAIN_MESSAGE);
                    label.setText("Par is online");
                }
                updateList(IND);
            }
        });

        smileButton = new JButton("Smiles");
        smileButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                panelWithSmiles.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }

        });

        panelWithSmiles.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                panelWithSmiles.setVisible(false);
            }
        });

        changeButton.setBackground(Color.PINK);
        sendButton.setBackground(Color.PINK);
        label.setForeground(Color.red);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(10, 10, 0, 0);
        constraints.weighty = 3;
        constraints.weightx = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(changeButton, constraints);

        constraints.anchor = GridBagConstraints.NORTHEAST;
        constraints.weighty = 5;
        constraints.weightx = 5;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(label, constraints);


        constraints.anchor = GridBagConstraints.SOUTHEAST;
        constraints.insets = new Insets(0, 2, 18, 10);
        constraints.weighty = 5;
        constraints.weightx = 5;
        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(sendButton, constraints);

        constraints.anchor = GridBagConstraints.SOUTHEAST;
        constraints.insets = new Insets(0, 2, 18, 10);
        constraints.weighty = 5;
        constraints.weightx = 5;
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(smileButton, constraints);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 3;
        constraints.weightx = 5;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.insets = new Insets(0, 10, 20, 2);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(field, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 9;
        constraints.weightx = 5;
        constraints.gridx = 0;
        constraints.insets = new Insets(0, 10, 0, 0);
        constraints.gridy = 1;
        panel.add(new JScrollPane(bagList), constraints);


        setTitle("Chat");
        lpane.add(panelWithSmiles, JLayeredPane.PALETTE_LAYER);
        lpane.add(panel, JLayeredPane.DEFAULT_LAYER);
        pack();
        getContentPane().add(lpane);
    }
}
