package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class BillingClass extends JFrame {
	private JLabel companyName;
	private JLabel customerName;
	private JLabel product;
	private JLabel quantity;
	private JLabel price;
	private JLabel total;
	private JLabel gst;
	private JLabel grandTotal;

	private JLabel totalPrice;
	private JLabel gstRate;
	private JLabel grandTotalPrice;

	private JTextField customerNameTextField;
	private JTextField productTextField;
	private JTextField quantityTextField;
	private JTextField priceTextField;

	private JButton addProducts;

	private JButton calculate;

	private JButton print;
	private JButton clear;

	private JScrollPane jScrollPane;
	private JTable jTable;
	private DefaultTableModel defaultTableModel;

	private ArrayList<String> row;

	public BillingClass() {
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Billing Software");

		companyName = new JLabel("AAI HOME AUTOMATION SERVICES");

		customerName = new JLabel("Customer Name");
		product = new JLabel("Product");
		quantity = new JLabel("Quantity");
		price = new JLabel("Price");
		total = new JLabel("Total");
		gst = new JLabel("GST");
		grandTotal = new JLabel("Grand Total");

		totalPrice = new JLabel();
		gstRate = new JLabel("5%");
		grandTotalPrice = new JLabel();

		customerNameTextField = new JTextField();
		productTextField = new JTextField();
		quantityTextField = new JTextField();
		priceTextField = new JTextField();

		addProducts = new JButton("+");

		calculate = new JButton("Calculate");

		print = new JButton("Print");
		clear = new JButton("Clear");

		row = new ArrayList<String>();
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable = new JTable(defaultTableModel);

		jScrollPane = new JScrollPane(jTable);

		setPositions();
		addComponents();

		setTable();

		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				calculateButtonWorking();
			}
		});

		addProducts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addProductsButtonWorking();
			}
		});

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearButtonWorking();
			}
		});

		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				printButtonWorking();
			}
		});

		quantityTextField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent k) {
				String value = quantityTextField.getText();
				int length = value.length();
				if (k.getKeyChar() >= '0' && k.getKeyChar() <= '9'
						|| k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					quantityTextField.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Enter only Numbers");
					quantityTextField.setText(null);
				}
			}
		});

		priceTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				String value = priceTextField.getText();
				int length = value.length();
				if (k.getKeyChar() >= '0' && k.getKeyChar() <= '9'
						|| k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					priceTextField.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Enter only Numbers");
					priceTextField.setText(null);
				}
			}
		});

		setVisible(true);
	}

	private void setTable() {
		String[] columns = { "Products", "Quantity", "Price", "Total" };

		defaultTableModel.setColumnCount(4);
		defaultTableModel.setColumnIdentifiers(columns);
	}

	private void addProductsButtonWorking() {

		productTextField.requestFocusInWindow();

		double quantity = Double.parseDouble(quantityTextField.getText());

		double price = Double.parseDouble(priceTextField.getText());

		double total = quantity * price;

		row.add(0, productTextField.getText());
		row.add(1, quantityTextField.getText());
		row.add(2, priceTextField.getText());
		row.add(3, String.valueOf(total));

		defaultTableModel.insertRow(0, row.toArray());
		row = new ArrayList<String>();

		productTextField.setText(null);
		quantityTextField.setText(null);
		priceTextField.setText(null);
	}

	private void setPositions() {
		setBounds(20, 20, 850, 700);
		companyName.setBounds(150, 20, 350, 30);
		customerName.setBounds(50, 20, 150, 30);
		product.setBounds(50, 70, 50, 30);
		quantity.setBounds(270, 70, 50, 30);
		price.setBounds(490, 70, 50, 30);

		addProducts.setBounds(20, 160, 50, 20);

		calculate.setBounds(30, 320, 100, 30);

		total.setBounds(30, 370, 50, 30);
		gst.setBounds(30, 420, 50, 30);
		grandTotal.setBounds(30, 470, 100, 30);

		totalPrice.setBounds(200, 370, 150, 30);
		gstRate.setBounds(200, 420, 50, 30);
		grandTotalPrice.setBounds(200, 470, 150, 30);

		customerNameTextField.setBounds(200, 20, 250, 25);
		productTextField.setBounds(50, 110, 150, 25);
		quantityTextField.setBounds(270, 110, 150, 25);
		priceTextField.setBounds(490, 110, 150, 25);

		print.setBounds(50, 550, 150, 30);
		clear.setBounds(250, 550, 150, 30);

		jScrollPane.setBounds(30, 200, 700, 100);
		jScrollPane.setFocusable(false);

		jTable.setFocusable(false);
	}

	private void addComponents() {
		// add(companyName);
		add(customerName);
		add(product);
		add(quantity);
		add(price);
		add(addProducts);
		add(calculate);
		add(total);
		add(gst);
		add(grandTotal);
		add(totalPrice);
		add(gstRate);
		add(grandTotalPrice);
		add(customerNameTextField);
		add(productTextField);
		add(quantityTextField);
		add(priceTextField);
		add(print);
		add(clear);
		add(jScrollPane);
	}

	private void calculateButtonWorking() {

		Double total = 0.0;

		int rowCount = defaultTableModel.getRowCount();

		for (int i = 0; i < rowCount; i++) {
			total = Double.parseDouble((String) defaultTableModel.getValueAt(i,
					3)) + total;
		}

		totalPrice.setText(String.valueOf(total));

		double gst = 0.05;
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		double gstAmount = Double.parseDouble(numberFormat.format(gst));

		String grandTotalToDisplay = String.valueOf(numberFormat
				.format(((gstAmount) * total) + total));

		grandTotalPrice.setText(grandTotalToDisplay);
	}

	private void clearButtonWorking() {
		defaultTableModel.setRowCount(0);

		totalPrice.setText(null);
		grandTotalPrice.setText(null);
	}

	public void printButtonWorking() {

		try {

			// PDDocument document = new PDDocument();
			// document.addPage(new PDPage());
			// document.save("C:/pdfBox/AddText_IP.pdf");

			// Loading an existing document
			PDDocument doc = PDDocument.load(new File(
					"C:/pdfBox/OriginalBill.pdf"));

			// Creating a PDF Document
			PDPage page = doc.getPage(0);

			// Date
			PDPageContentStream contentStream = new PDPageContentStream(doc,
					page, true, true, true);

			// Begin the Content stream
			contentStream.beginText();

			// Setting the font to the Content stream
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream.newLineAtOffset(475, 587);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String date2 = dateFormat.format(date);

			// Adding text in the form of string
			// contentStream.showText(text);
			contentStream.showText(date2);
			contentStream.appendRawCommands(date2);

			// Ending the content stream
			contentStream.endText();

			// Closing the content stream
			contentStream.close();

			// Customer Name
			PDPageContentStream contentStream2 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream2.beginText();
			contentStream2.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream2.newLineAtOffset(40, 627);

			String customerName = customerNameTextField.getText();
			contentStream2.showText(customerName);
			contentStream2.appendRawCommands(customerName);

			contentStream2.endText();
			contentStream2.close();

			// Serial No.
			PDPageContentStream contentStream3 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream3.beginText();
			contentStream3.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream3.newLineAtOffset(35, 523);

			int rowCount = defaultTableModel.getRowCount();
			// contentStream.newLineAtOffset(0, -100);
			for (int i = 1; i <= rowCount; i++) {

				String srno = String.valueOf(i);
				contentStream3.showText(srno);
				contentStream3.appendRawCommands(srno);

				contentStream3.newLine();
				contentStream3.newLineAtOffset(0, -40);
			}

			contentStream3.endText();
			contentStream3.close();

			// Particulars
			PDPageContentStream contentStream4 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream4.beginText();
			contentStream4.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream4.newLineAtOffset(72, 523);

			// contentStream.newLineAtOffset(0, -100);
			for (int i = 0; i < rowCount; i++) {

				String products = (String) defaultTableModel.getValueAt(i, 0);
				contentStream4.showText(products);
				contentStream4.appendRawCommands(products);

				contentStream4.newLine();
				contentStream4.newLineAtOffset(0, -40);
			}

			contentStream4.endText();
			contentStream4.close();

			// Quantity
			PDPageContentStream contentStream5 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream5.beginText();
			contentStream5.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream5.newLineAtOffset(355, 523);

			// contentStream.newLineAtOffset(0, -100);
			for (int i = 0; i < rowCount; i++) {

				String quantity = (String) defaultTableModel.getValueAt(i, 1);
				contentStream5.showText(quantity);
				contentStream5.appendRawCommands(quantity);

				contentStream5.newLine();
				contentStream5.newLineAtOffset(0, -40);
			}

			contentStream5.endText();
			contentStream5.close();

			// Price
			PDPageContentStream contentStream6 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream6.beginText();
			contentStream6.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream6.newLineAtOffset(420, 523);

			for (int i = 0; i < rowCount; i++) {

				String price = (String) defaultTableModel.getValueAt(i, 2);
				contentStream6.showText(price);
				contentStream6.appendRawCommands(price);

				contentStream6.newLine();
				contentStream6.newLineAtOffset(0, -40);
			}

			contentStream6.endText();
			contentStream6.close();

			// Amount
			PDPageContentStream contentStream7 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream7.beginText();
			contentStream7.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream7.newLineAtOffset(500, 523);

			for (int i = 0; i < rowCount; i++) {

				double quantity = Double.parseDouble((String) defaultTableModel
						.getValueAt(i, 1));

				double rate = Double.parseDouble((String) defaultTableModel
						.getValueAt(i, 2));

				String amount = String.valueOf(quantity * rate);
				contentStream7.showText(amount);
				contentStream7.appendRawCommands(amount);

				contentStream7.newLine();
				contentStream7.newLineAtOffset(0, -40);
			}

			contentStream7.endText();
			contentStream7.close();

			// GST
			PDPageContentStream contentStream8 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream8.beginText();
			contentStream8.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream8.newLineAtOffset(72, 230);

			String gst = "GST";
			contentStream8.showText(gst);
			contentStream8.appendRawCommands(gst);

			contentStream8.endText();
			contentStream8.close();

			// GST Rate
			PDPageContentStream contentStream9 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream9.beginText();
			contentStream9.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream9.newLineAtOffset(420, 230);

			String gstRate = "5%";
			contentStream9.showText(gstRate);
			contentStream9.appendRawCommands(gstRate);

			contentStream9.endText();
			contentStream9.close();

			// GST Amount
			PDPageContentStream contentStream10 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream10.beginText();
			contentStream10.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream10.newLineAtOffset(500, 230);

			Double total = 0.0;

			for (int i = 0; i < rowCount; i++) {
				total = Double.parseDouble((String) defaultTableModel
						.getValueAt(i, 3)) + total;
			}

			double gstR = 0.05;
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			double gstAmount = Double.parseDouble(numberFormat.format(gstR));

			String gstRTotal = String.valueOf(numberFormat.format((gstAmount)
					* total));

			contentStream10.showText(gstRTotal);
			contentStream10.appendRawCommands(gstRTotal);

			contentStream10.endText();
			contentStream10.close();

			// Grand Total
			PDPageContentStream contentStream11 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream11.beginText();
			contentStream11.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream11.newLineAtOffset(72, 190);

			String grandTotal = "Total";
			contentStream11.showText(grandTotal);
			contentStream11.appendRawCommands(grandTotal);

			contentStream11.endText();
			contentStream11.close();

			// Grand Total Amount
			PDPageContentStream contentStream12 = new PDPageContentStream(doc,
					page, true, true, true);
			contentStream12.beginText();
			contentStream12.setFont(PDType1Font.TIMES_ROMAN, 16);
			contentStream12.newLineAtOffset(500, 190);

			double gstRTotalDouble = Double.parseDouble(gstRTotal);

			double gTotal = gstRTotalDouble + total;

			String grandTotalAmount = String.valueOf(gTotal);

			contentStream12.showText(grandTotalAmount);
			contentStream12.appendRawCommands(grandTotalAmount);

			contentStream12.endText();
			contentStream12.close();

			// Saving the document

			doc.save(new File("C:/pdfBox/" + customerNameTextField.getText()
					+ ".pdf"));

			ProcessBuilder processBuilder = new ProcessBuilder(
					"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe",
					"C:\\pdfBox\\" + customerNameTextField.getText() + ".pdf");

			processBuilder.start();

			// Closing the document
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BillingClass billingClass = new BillingClass();
	}
}
