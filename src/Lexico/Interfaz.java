/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Interfaz.java
 *
 * Created on 07-nov-2011, 9:25:36
 */
package Lexico;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos J Medina
 */
public class Interfaz extends javax.swing.JFrame {
    
    String filename = "";
    
    void Ir(int line, JTextArea area)
    {
        String []t = area.getText().split("\n");
	int position = 0;
	for(int index = 0; index < t.length; index++)
        {
		if(index == line - 1)
                    break;
		if(t[index].length() != 0)
		        position += t[index].length();
	}
        area.setCaretPosition(position + line - 1);
    }
    
    void Ir_JTextPane(int line, JTextPane area)
    {
        String []t = area.getText().split("\n");
	int position = 0;
	for(int index = 0; index < t.length; index++)
        {
		if(index == line - 1)
                    break;
		if(t[index].length() != 0)
		        position += t[index].length();
	}
        area.setCaretPosition(position + line - 1);
    }
    
   int JTextLength(String fileName) throws FileNotFoundException
   {
       int longitud = 0;
       File archivo = new File(fileName);
       Scanner sc = new Scanner(archivo);
       sc.useDelimiter("\n");
       String linea;
       
       while (sc.hasNext())
       {
           linea = sc.next();
           longitud++;
       }
                        
       return longitud;
   }
   
    public Interfaz() 
    {
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //JScrollPane scroll = new JScrollPane(texto);
        JScrollPane scroll = new JScrollPane(txtPane);
        
        //TextLineNumber tln = new TextLineNumber(txtPane);
        //scroll.setColumnHeaderView( tln );
        
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);  
        
        JMenu file = new JMenu("Archivo");
        JMenu help = new JMenu("Ayuda");
        
        help.setMnemonic('H');
        file.setMnemonic('F');
        
        // Elemento del menú "Ayuda".
        JMenuItem aboutItem = new JMenuItem("Integrantes");
        aboutItem.setMnemonic('T');
        
        help.add(aboutItem);
        JMenuItem openItem = new JMenuItem("Abrir");
        openItem.setMnemonic('A');
        file.add(openItem);
        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.setMnemonic('S');
        file.add(exitItem);
	
        //finalString filename;
        
        openItem.addActionListener(
	new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                texto.setEditable(false);
                texto.setText("");
                int lineNumber = 0;
		String wd = System.getProperty("user.home");
		JFileChooser fc = new JFileChooser(wd);
                Filtro filtro = new Filtro();
                filtro.addExtension("txt");
                filtro.setDescription("Archivos de texto plano : ");
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filtro);
                
                fc.setDialogTitle("Seleccione el archivo de texto a abrir");
		filename = "";
		int rc = fc.showDialog(null, "Seleccionar archivo");
		if (rc == JFileChooser.APPROVE_OPTION)
		{
                    try {
                        File file = fc.getSelectedFile();
                        filename = file.getAbsolutePath();
                        lblNameFile.setText(filename);
                        // Leer archivo:
                        File archivo = new File(filename);
                        Scanner sc = new Scanner(archivo);
                        sc.useDelimiter("\n");
                        String linea;
                        String linePane = "";
                        while (sc.hasNext()) 
                        {
                            linea = sc.next();
                            linePane += linea + "\n";
                            texto.append(++lineNumber + "\t" + linea + "\n");
                        }
                        
                        txtPane.setText(linePane);
                        
                        
                        lineNumber = 0;
                        texto.setCaretPosition(0);
                        txtPane.setCaretPosition(0);
                        sc.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error abriendo el archivo  : " + filename);
                    }
		}
            }
	}
        );
	
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
	}
        );						
	
                JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		
                bar.add(file);
                bar.add(help);
		
		getContentPane();
		setSize(200, 200);
		setVisible(true);
                initComponents();
    }  

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        txtPane = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaResultados = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        lblNameFile = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jScrollPane4.setEnabled(false);

        txtPane.setColumns(20);
        txtPane.setRows(5);
        txtPane.setEnabled(false);
        jScrollPane4.setViewportView(txtPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ANALIZADOR LEXICO -----== PASCAL1 ==----");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Archivo para analizar:");

        TablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Linea", "Lexema", "Nº Token", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TablaResultados);

        texto.setColumns(30);
        texto.setFont(new java.awt.Font("Arial", 0, 10));
        texto.setLineWrap(true);
        texto.setRows(5);
        texto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(texto);

        lblNameFile.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNameFile.setForeground(new java.awt.Color(51, 51, 51));

        jButton1.setText("ANALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNameFile, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNameFile)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Lexer scan = new Lexer();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int simb=0;
        int filtro=0;
        DefaultTableModel datos = new DefaultTableModel();
        JTable tabla = new JTable(datos);
        datos.addColumn("Linea");
        datos.addColumn("Nº Token");
        datos.addColumn("Lexema");
        datos.addColumn("Estado");
        String fila[];
        try {
            scan.inicializar(filename);
            
              while(simb != -10){
                    if((simb = scan.getNextToken()) == -1){
                        fila =new String [4];
                        fila[0]=String.valueOf(scan.getNumeroDeLinea());
                        fila[1]=String.valueOf(simb);
                        fila[2]=String.valueOf(scan.getUltimoLexema());
                        fila[3]=scan.DevuelveEstado();
                        datos.addRow(fila);
                        tabla.updateUI();
                        simb=-10;
                    }else{
                        fila =new String [4];
                        fila[0]=String.valueOf(scan.getNumeroDeLinea());
                        fila[1]=String.valueOf(simb);
                        fila[2]=String.valueOf(scan.getUltimoLexema());
                        fila[3]=scan.DevuelveEstado();
                        datos.addRow(fila);
                        tabla.updateUI();
                        filtro=0;
                    }
              }              
                   
                  
            TablaResultados.setModel(datos);
                
        }
        catch (Exception e) {
            e.printStackTrace();
        }      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaResultados;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblNameFile;
    private javax.swing.JTextArea texto;
    private javax.swing.JTextArea txtPane;
    // End of variables declaration//GEN-END:variables
}
