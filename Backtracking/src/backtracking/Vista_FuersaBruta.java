/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jocxa
 */
public class Vista_FuersaBruta extends javax.swing.JFrame {
    
    private FondoPanel fondo = new FondoPanel();
    private DefaultTableModel variableTabla;
    private Object fila[] = new Object[5];  
    private DefaultListModel modeloListaIncorrecta;
    private DefaultListModel modeloListaRestricciones;
    
    private ArrayList<Nodo[]> posiblesSoluciones;
    private ArrayList<Nodo> incorrectas;
    private Nodo[] solucion;
    private int[] horaInicial;
    private int[] horaFinal;
    
    private int indiceTabla;
    
    /**
     * Creates new form Vista_Backtraking
     * @param posibles
     * @param _incorrectas
     * @param _solucion
     * @param _horaInicial
     * @param _horaFinal
     */
    public Vista_FuersaBruta(ArrayList<Nodo[]> posibles, ArrayList<Nodo> _incorrectas, Nodo[] _solucion, int _horaInicial[], int _horaFinal[]) {
        this.setContentPane(fondo); //Le pone fondo al frame
        
        initComponents();
        
        //Cargo valores a las variables globales
        this.posiblesSoluciones=posibles;
        this.incorrectas = _incorrectas;
        this.solucion = _solucion;
        this.horaInicial = _horaInicial;
        this.horaFinal = _horaFinal;   
        
        //Inicializo y configuro los elementos que se ocupan para la tabla y las listas
        this.variableTabla = (DefaultTableModel)this.tabla.getModel();
        this.modeloListaIncorrecta = new DefaultListModel();
        this.ListaIncorrectas.setModel(this.modeloListaIncorrecta);
        this.modeloListaRestricciones = new DefaultListModel();
        this.indiceTabla = 0;
        
        //Muestra la ventana
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);   //La inicia maximizada
        
        //Pinta dos botones
        PintarBoton("atras.png", this.BotonAtras);
        PintarBoton("siguiente.png", this.BotonSiguiente);
    
        //Carga las listas, las labels y todo
        MostrarHoras();
        MostrarSolucion();
        RellenarListaIncorrectas();
        RellenarTabla();
        PintarSolucionesPosibles();
    }
    
    //Metodo que poner las imagenes en los labels de las soluciones, utiliza la variable global, indiceTabla para saber cuales imagenes debe mostrar
    public void PintarSolucionesPosibles(){
        
        Nodo[] tmp = this.posiblesSoluciones.get(this.indiceTabla);
        
        //Primero nombres
        this.labelSospechoso.setText(tmp[0].nombre);
        this.labelArma.setText(tmp[1].nombre);
        this.labelMotivo.setText(tmp[2].nombre);
        this.labelParte.setText(tmp[3].nombre);
        this.labelLugar.setText(tmp[4].nombre);
        
        
        //Despues imagenes
        PintarLabel(tmp[0].nombre, this.labelImgSospechoso);
        PintarLabel(tmp[1].nombre, this.labelImgArma);
        PintarLabel(tmp[2].nombre, this.labelImgMotivo);
        PintarLabel(tmp[3].nombre, this.labelImgParte);
        PintarLabel(tmp[4].nombre, this.labelImgLugar);

    }
    
    //Metodo que rellena la tabla con las posibles combinaciones
    public void RellenarTabla(){
        
        for(int i=0; i<this.posiblesSoluciones.size(); i++){
            
            for(int j=0; j<5; j++){
                this.fila[j]= this.posiblesSoluciones.get(i)[j].nombre;
            }
            this.variableTabla.addRow(this.fila);
        } 
    }
    
    public void MostrarSolucion(){
        
        //Primero configuro los labels del nombre
        this.labelSolSospechoso.setText(this.solucion[0].nombre);
        this.labelSolArma.setText(this.solucion[1].nombre);
        this.labelSolMotivo.setText(this.solucion[2].nombre);
        this.labelSolParte.setText(this.solucion[3].nombre);
        this.labelSolLugar.setText(this.solucion[4].nombre);
        
        //Despues los labels con sus imagenes
        PintarLabel(this.solucion[0].nombre, this.labelSolImgSospechoso);
        PintarLabel(this.solucion[1].nombre, this.labelSolImgArma);
        PintarLabel(this.solucion[2].nombre, this.labelSolImgMotivo);
        PintarLabel(this.solucion[3].nombre, this.labelSolImgParte);
        PintarLabel(this.solucion[4].nombre, this.labelSolImgLugar);
    }
     
    public void PintarLabel(String nombre, JLabel label) {

        ImageIcon imageIcon = new ImageIcon("Imagenes\\" + nombre + ".jpg");
        Image imgEscalada = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        Icon iconEscalado = new ImageIcon(imgEscalada);
        label.setIcon(iconEscalado);
    }
    
    public void PintarBoton(String nombre, JButton b) {
        
        b.setOpaque(false);
        b.setBackground(new Color(0,0,0,0));
        ImageIcon imgConector = new ImageIcon("Imagenes\\" + nombre);
        b.setIcon(new ImageIcon(imgConector.getImage().getScaledInstance(b.getWidth(), b.getHeight(), 0)));
    }
    
    //Metodo que rellena la lista de incorrectas
    public void RellenarListaIncorrectas(){
        
        //Recorre el arreglo de incorrectas y lo poniendo en la lista
        for(int i=0; i<this.incorrectas.size(); i++){
            this.modeloListaIncorrecta.addElement(this.incorrectas.get(i).nombre);
        }
        
        //Pregunta si la el arreglo no esta vacio, para poner las imagen de la primera carta incorrecta, si extiste
        if(!this.incorrectas.isEmpty()){
            PintarLabel(this.incorrectas.get(0).nombre, this.labelIncorrecta);
        }
        
    }
    //Funcion que edita las labels de hora con las horas de duracion del algoritmo
    public void MostrarHoras(){
        
        //Hora inicial
        this.labelHora.setText(Integer.toString(this.horaInicial[0]));
        this.labelMin.setText(Integer.toString(this.horaInicial[1]));
        this.labelSeg.setText(Integer.toString(this.horaInicial[2]));
        this.labelMili.setText(Integer.toString(this.horaInicial[3]));
        
        
        //Hora final
        this.labelHoraFinal.setText(Integer.toString(this.horaFinal[0]));
        this.labelMinFinal.setText(Integer.toString(this.horaFinal[1]));
        this.labelSegFinal.setText(Integer.toString(this.horaFinal[2]));
        this.labelMiliFinal.setText(Integer.toString(this.horaFinal[3]));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaIncorrectas = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelSolImgSospechoso = new javax.swing.JLabel();
        labelSolSospechoso = new javax.swing.JLabel();
        labelSolImgArma = new javax.swing.JLabel();
        labelSolImgMotivo = new javax.swing.JLabel();
        labelSolImgParte = new javax.swing.JLabel();
        labelSolImgLugar = new javax.swing.JLabel();
        labelSolArma = new javax.swing.JLabel();
        labelSolMotivo = new javax.swing.JLabel();
        labelSolParte = new javax.swing.JLabel();
        labelSolLugar = new javax.swing.JLabel();
        BotonAtras = new javax.swing.JButton();
        BotonSiguiente = new javax.swing.JButton();
        labelIncorrecta = new javax.swing.JLabel();
        labelImgSospechoso = new javax.swing.JLabel();
        labelImgArma = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        labelImgMotivo = new javax.swing.JLabel();
        labelImgParte = new javax.swing.JLabel();
        labelImgLugar = new javax.swing.JLabel();
        labelArma = new javax.swing.JLabel();
        labelSospechoso = new javax.swing.JLabel();
        labelMotivo = new javax.swing.JLabel();
        labelParte = new javax.swing.JLabel();
        labelLugar = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labelMin = new javax.swing.JLabel();
        labelSeg = new javax.swing.JLabel();
        labelMili = new javax.swing.JLabel();
        labelHoraFinal = new javax.swing.JLabel();
        labelMinFinal = new javax.swing.JLabel();
        labelSegFinal = new javax.swing.JLabel();
        labelMiliFinal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sospechoso", "Arma", "Motivo", "Parte", "Lugar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        ListaIncorrectas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ListaIncorrectas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaIncorrectasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListaIncorrectas);

        jLabel1.setFont(new java.awt.Font("Elephant", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cartas marcadas incorrectas ");

        jLabel2.setFont(new java.awt.Font("Elephant", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 153));
        jLabel2.setText("Fuerza Bruta");

        jLabel3.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Hora Inicial ");

        jLabel4.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Hora Final ");

        jLabel5.setFont(new java.awt.Font("Elephant", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 0));
        jLabel5.setText("Solución ");

        labelSolImgSospechoso.setText("jLabel6");

        labelSolSospechoso.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolSospechoso.setForeground(new java.awt.Color(255, 153, 51));
        labelSolSospechoso.setText("Sospechoso");

        labelSolImgArma.setText("jLabel8");

        labelSolImgMotivo.setText("jLabel8");

        labelSolImgParte.setText("jLabel8");

        labelSolImgLugar.setText("jLabel8");

        labelSolArma.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolArma.setForeground(new java.awt.Color(255, 153, 51));
        labelSolArma.setText("Arma");

        labelSolMotivo.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolMotivo.setForeground(new java.awt.Color(255, 153, 51));
        labelSolMotivo.setText("Motivo");

        labelSolParte.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolParte.setForeground(new java.awt.Color(255, 153, 51));
        labelSolParte.setText("Parte");

        labelSolLugar.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolLugar.setForeground(new java.awt.Color(255, 153, 51));
        labelSolLugar.setText("Lugar");

        BotonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAtrasActionPerformed(evt);
            }
        });

        BotonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSiguienteActionPerformed(evt);
            }
        });

        labelIncorrecta.setText("jLabel6");

        labelImgSospechoso.setText("jLabel8");

        labelImgArma.setText("jLabel8");

        labelImgMotivo.setText("jLabel8");

        labelImgParte.setText("jLabel8");

        labelImgLugar.setText("jLabel8");

        labelArma.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelArma.setForeground(new java.awt.Color(255, 255, 0));
        labelArma.setText("Arma");

        labelSospechoso.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelSospechoso.setForeground(new java.awt.Color(255, 255, 0));
        labelSospechoso.setText("Sospechoso");

        labelMotivo.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelMotivo.setForeground(new java.awt.Color(255, 255, 0));
        labelMotivo.setText("Motivo");

        labelParte.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelParte.setForeground(new java.awt.Color(255, 255, 0));
        labelParte.setText("Parte");

        labelLugar.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelLugar.setForeground(new java.awt.Color(255, 255, 0));
        labelLugar.setText("Lugar");

        labelHora.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelHora.setForeground(new java.awt.Color(153, 153, 153));
        labelHora.setText("H");

        labelMin.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMin.setForeground(new java.awt.Color(153, 153, 153));
        labelMin.setText("m");

        labelSeg.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelSeg.setForeground(new java.awt.Color(153, 153, 153));
        labelSeg.setText("s");

        labelMili.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMili.setForeground(new java.awt.Color(153, 153, 153));
        labelMili.setText("mi");

        labelHoraFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelHoraFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelHoraFinal.setText("H");

        labelMinFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMinFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelMinFinal.setText("m");

        labelSegFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelSegFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelSegFinal.setText("s");

        labelMiliFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMiliFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelMiliFinal.setText("mi");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText(":");

        jLabel15.setFont(new java.awt.Font("Goudy Old Style", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText(":");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText(":");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText(":");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText(":");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(185, 185, 185))
                                        .addComponent(labelIncorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(labelImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(labelSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelArma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelParte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(BotonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)
                                        .addComponent(BotonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(225, 225, 225))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSolSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolArma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(284, 284, 284)
                                        .addComponent(labelSolMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolParte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSolImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelSolImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelSolImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelSolImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(12, 12, 12)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(66, 66, 66)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelMinFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(labelSegFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelMiliFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelMili, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMili, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMinFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSegFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(labelMiliFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSolImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSolSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolArma, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolParte, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIncorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelArma, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelParte, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSiguienteActionPerformed
        //Valida que si pueda ir hacia adelante
        if(this.indiceTabla < this.posiblesSoluciones.size()-1){
            this.indiceTabla++;
            PintarSolucionesPosibles();
        }
    }//GEN-LAST:event_BotonSiguienteActionPerformed

    private void BotonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAtrasActionPerformed
        //Valida que la tabla no sea la primera solucion, porque no hay atras
        if(this.indiceTabla != 0){
            this.indiceTabla--;
            PintarSolucionesPosibles();
        }
    }//GEN-LAST:event_BotonAtrasActionPerformed

    
    //Cada vez que clickean la lista de incorrectas, entonces cambia la imagen de incorrectas
    private void ListaIncorrectasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaIncorrectasMouseClicked
        int i = this.ListaIncorrectas.getSelectedIndex();
        if(i>=0)
            PintarLabel(this.incorrectas.get(i).nombre, this.labelIncorrecta);
    }//GEN-LAST:event_ListaIncorrectasMouseClicked

    //Cada vez que se la un click a la tabla se modifica el indice global de la misma, y llama a pintar soluciones
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        this.indiceTabla = this.tabla.getSelectedRow();
        PintarSolucionesPosibles(); 
    }//GEN-LAST:event_tablaMouseClicked

                                  
    
   
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAtras;
    private javax.swing.JButton BotonSiguiente;
    private javax.swing.JList ListaIncorrectas;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelArma;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelHoraFinal;
    private javax.swing.JLabel labelImgArma;
    private javax.swing.JLabel labelImgLugar;
    private javax.swing.JLabel labelImgMotivo;
    private javax.swing.JLabel labelImgParte;
    private javax.swing.JLabel labelImgSospechoso;
    private javax.swing.JLabel labelIncorrecta;
    private javax.swing.JLabel labelLugar;
    private javax.swing.JLabel labelMili;
    private javax.swing.JLabel labelMiliFinal;
    private javax.swing.JLabel labelMin;
    private javax.swing.JLabel labelMinFinal;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel labelParte;
    private javax.swing.JLabel labelSeg;
    private javax.swing.JLabel labelSegFinal;
    private javax.swing.JLabel labelSolArma;
    private javax.swing.JLabel labelSolImgArma;
    private javax.swing.JLabel labelSolImgLugar;
    private javax.swing.JLabel labelSolImgMotivo;
    private javax.swing.JLabel labelSolImgParte;
    private javax.swing.JLabel labelSolImgSospechoso;
    private javax.swing.JLabel labelSolLugar;
    private javax.swing.JLabel labelSolMotivo;
    private javax.swing.JLabel labelSolParte;
    private javax.swing.JLabel labelSolSospechoso;
    private javax.swing.JLabel labelSospechoso;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    //Esta clase es para poder ponerle fondo al Jframe
    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/backtracking/FondoBack.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

}
