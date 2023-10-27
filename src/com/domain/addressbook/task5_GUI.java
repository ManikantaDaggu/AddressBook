package com.domain.addressbook;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;
    private String houseno;
    private String street;

    public Contact(String name, String phoneNumber, String email,String houseno,String street) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.houseno=houseno;
        this.street=street;
    }

  

	public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    
    public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", houseno=" + houseno
				+ ", street=" + street + "]";
	}

	
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public Contact searchContact(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }
}

public class task5_GUI {
    private AddressBook addressBook = new AddressBook();
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField housenoField;
    private JTextField streetField;

    public task5_GUI() {
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        emailField = new JTextField(20);
        housenoField= new JTextField(20);
        streetField= new JTextField(20);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton displayButton = new JButton("Display");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneField.getText();
                String email = emailField.getText();
                String houseno=housenoField.getText();
                String street=streetField.getText();

                if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
                    Contact newContact = new Contact(name, phoneNumber, email,houseno,street);
                    addressBook.addContact(newContact);
                    nameField.setText("");
                    phoneField.setText("");
                    emailField.setText("");
                    housenoField.setText("");
                    streetField.setText("");
                    displayArea.append("Contact added successfully.\n");
                } else {
                    displayArea.append("Please fill in all the required fields.\n");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                addressBook.removeContact(name);
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                housenoField.setText("");
                streetField.setText("");
                displayArea.append("Contact removed.\n");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Contact foundContact = addressBook.searchContact(name);
                if (foundContact != null) {
                    displayArea.setText(foundContact.toString() + "\n");
                } else {
                    displayArea.setText("Contact not found.\n");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(""); // Clear the display area
                for (Contact contact : addressBook.getAllContacts()) {
                    displayArea.append(contact.toString() + "\n-------------------\n");
                }
            }
        });
        

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("House-No:"));
        inputPanel.add(housenoField);
        inputPanel.add(new JLabel("Street:"));
        inputPanel.add(streetField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new task5_GUI());
    }
}
