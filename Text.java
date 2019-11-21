package onpu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Text extends JFrame {
    private String[] elements = new String[]{"поздно", "очень", "домой", "вчера", "быстро"};
    private JComboBox<String> list= new JComboBox<>(elements);
    private JTextField field = new JTextField();
    private JRadioButton butt1 = new JRadioButton("•\tЗаменить существительное местоимением");
    private JRadioButton butt2 = new JRadioButton("•\tВставить после глагола «бы»");
    private JRadioButton butt3 = new JRadioButton("•\tЗаменить наречие другим, выбранным из списка");
    private JTextArea text = new JTextArea();
    private JMenuBar myMenu = new JMenuBar();
    private JMenu first = new JMenu("Menu");
    private JMenuItem clear = new JMenuItem("Очистить поля");
    private JMenuItem exit = new JMenuItem("Закрыть окно");
    private JLabel label1 = new JLabel("Введите предложение типа <Существительное>+<глагол в прошедшем времени>+<наречие>: ");
    private JLabel label2 = new JLabel("Преобразованое предложение");
    private JPanel panel1 =new JPanel();

    public Text() {
        super("Application");
        this.setBounds(300, 100, 700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        list.setVisible(false);
        list.setEditable(true);
        list.setSize(30, 70);

        myMenu.add(first);
        first.add(clear);
        first.add(exit);
        this.setJMenuBar(myMenu);

        container.setLayout(new GridLayout(7, 1));
        container.add(label1);
        container.add(field);

        panel1.add(butt1);
        panel1.add(butt2);
        panel1.add(butt3);

        container.add(panel1);
        container.add(list);
        container.add(label2);
        container.add(text);

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                text.setText("");
                field.setText("");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // WindowEvent.WINDOW_CLOSING;
                dispose();
            }
        });

        butt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String str = "";
                    String substr;
                    int i = 0;
                    while (field.getText().charAt(i) != ' ') {
                        i++;
                    }
                    substr = field.getText().substring(i + 1, field.getText().length());
                    for (int j = 0; j < i; j++) {
                        str += field.getText().charAt(j);
                    }
                    text.setText("Я/Он/Она/Они " + substr);
                } catch (StringIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((Component) null, "Ошибка! Поле для ввода пустое или данные введены в неправильном формате!");
                }
            }
        });

        butt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String str = "";
                    String sub1, sub2;
                    int i = 0;
                    while (field.getText().charAt(i) != ' ') {
                        i++;
                    }
                    while (field.getText().charAt(i + 1) != ' ') {
                        i++;
                    }
                    sub1 = field.getText().substring(0, i + 1);
                    sub2 = field.getText().substring(i + 2, field.getText().length());
                    text.setText(sub1 + " бы " + sub2);
                } catch (StringIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((Component) null, "Ошибка! Поле для ввода пустое или данные введены в неправильном формате!");
                }
            }
        });

        butt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                list.setVisible(true);
            }
        });

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    Object srs = event.getSource();
                    JComboBox combo = (JComboBox) srs;
                    String name = (String) combo.getSelectedItem();

                    String str = "";
                    String sub1 = " ", sub2;
                    int i = field.getText().length() - 1;
                    while (field.getText().charAt(i) != ' ') {
                        i--;
                    }
                    sub1 = field.getText().substring(0, i);
                    sub2 = sub1 + " " + name;
                    text.setText(sub2);
                    list.setVisible(false);
                } catch (StringIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((Component) null, "Ошибка! Поле для ввода пустое или данные введены в неправильном формате!");
                }
            }
        });
    }
}