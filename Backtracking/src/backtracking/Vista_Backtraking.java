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
 * @author jocxa & Jhonny
 */
public class Vista_Backtraking extends javax.swing.JFrame {

    private FondoPanel fondo = new FondoPanel();
    private DefaultTableModel variableTabla;
    private Object fila[] = new Object[5];  
    private DefaultListModel modeloListaIncorrecta;
    private DefaultListModel modeloListaRestricciones;
    
    private ArrayList<Nodo[]> posiblesSoluciones;
    private ArrayList<Nodo> incorrectas;
    private ArrayList<Nodo[]> restricciones;
    private Nodo[] solucion;
    private int[] horaInicial;
    private int[] horaFinal;
    
    private int indiceTabla;
    
    /**
     * Creates new form Vista_Backtraking
     * @param posibles
     * @param _incorrectas
     * @param _restricciones
     * @param _solucion
     * @param _horaInicial
     * @param _horaFinal
     */
    public Vista_Backtraking(ArrayList<Nodo[]> posibles, ArrayList<Nodo> _incorrectas, ArrayList<Nodo[]> _restricciones, Nodo[] _solucion, int _horaInicial[], int _horaFinal[]) {
        
        this.setContentPane(fondo); //Le pone fondo al frame
        
        initComponents();
        
        //Cargo valores a las variables globales
        this.posiblesSoluciones=posibles;
        this.incorrectas = _incorrectas;
        this.restricciones = _restricciones;
        this.solucion = _solucion;
        this.horaInicial = _horaInicial;
        this.horaFinal = _horaFinal;   
        
        //Inicializo y configuro los elementos que se ocupan para la tabla y las listas
        this.variableTabla = (DefaultTableModel)this.tabla.getModel();
        this.modeloListaIncorrecta = new DefaultListModel();
        this.ListaIncorrectas.setModel(this.modeloListaIncorrecta);
        this.modeloListaRestricciones = new DefaultListModel();
        this.ListaRestricciones.setModel(modeloListaRestricciones);
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
        RellenarListaRestricciones();
        RellenarTabla();
        PintarSolucionesPosibles();
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
            
    
    //Funcion que muestra en el frame la solucion del juego
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
    
    
    //Metodo que rellena la tabla con las posibles combinaciones
    public void RellenarTabla(){
        
        for(int i=0; i<this.posiblesSoluciones.size(); i++){
            
            for(int j=0; j<5; j++){
                this.fila[j]= this.posiblesSoluciones.get(i)[j].nombre;
            }
            this.variableTabla.addRow(this.fila);
        } 
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
    
    //Funcion que pinta un label, tiene que recibir el label y el nombre de la imagen
    public void PintarLabel(String nombre, JLabel label) {

        ImageIcon imageIcon = new ImageIcon("Imagenes\\" + nombre + ".jpg");
        Image imgEscalada = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        Icon iconEscalado = new ImageIcon(imgEscalada);
        label.setIcon(iconEscalado);
    }
    
    //Funcion que pinta un boton, tiene que recibir el boton y el nombre de la imagen
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
    
    //Funcion que rellena la lista de restricciones
    public void RellenarListaRestricciones(){
        
        //Recorre el arreglo de restricciones y lo poniendo en la lista
        for(int i=0; i<this.restricciones.size(); i++){
            this.modeloListaRestricciones.addElement(this.restricciones.get(i)[0].nombre +" con "+this.restricciones.get(i)[1].nombre);
        }
        //Valida si el arreglo no es vacio, para que muestre la primera junta de restricciones
        if(!this.restricciones.isEmpty()){
            PintarLabel(this.restricciones.get(0)[0].nombre, this.labelRestriccion1);
            PintarLabel(this.restricciones.get(0)[1].nombre, this.labelRestriccion2);
        }
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        dospuntos = new javax.swing.JLabel();
        labelMin = new javax.swing.JLabel();
        dospuntos1 = new javax.swing.JLabel();
        labelSeg = new javax.swing.JLabel();
        dospuntos2 = new javax.swing.JLabel();
        labelMili = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelHoraFinal = new javax.swing.JLabel();
        dospuntos3 = new javax.swing.JLabel();
        labelMinFinal = new javax.swing.JLabel();
        dospuntos4 = new javax.swing.JLabel();
        labelSegFinal = new javax.swing.JLabel();
        dospuntos5 = new javax.swing.JLabel();
        labelMiliFinal = new javax.swing.JLabel();
        labelSospechoso = new javax.swing.JLabel();
        labelArma = new javax.swing.JLabel();
        labelMotivo = new javax.swing.JLabel();
        labelParte = new javax.swing.JLabel();
        labelLugar = new javax.swing.JLabel();
        labelImgSospechoso = new javax.swing.JLabel();
        labelImgArma = new javax.swing.JLabel();
        labelImgMotivo = new javax.swing.JLabel();
        labelImgParte = new javax.swing.JLabel();
        labelImgLugar = new javax.swing.JLabel();
        BotonAtras = new javax.swing.JButton();
        BotonSiguiente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelSolSospechoso = new javax.swing.JLabel();
        labelSolArma = new javax.swing.JLabel();
        labelSolParte = new javax.swing.JLabel();
        labelSolMotivo = new javax.swing.JLabel();
        labelSolLugar = new javax.swing.JLabel();
        labelSolImgSospechoso = new javax.swing.JLabel();
        labelSolImgArma = new javax.swing.JLabel();
        labelSolImgMotivo = new javax.swing.JLabel();
        labelSolImgParte = new javax.swing.JLabel();
        labelSolImgLugar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaIncorrectas = new javax.swing.JList<>();
        labelIncorrecta = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListaRestricciones = new javax.swing.JList<>();
        labelRestriccion2 = new javax.swing.JLabel();
        labelRestriccion1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setBackground(new java.awt.Color(51, 51, 55));
        tabla.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        tabla.setForeground(new java.awt.Color(153, 153, 255));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sospechoso", "Arma", "Motivo", "Parte del Cuerpo", "Lugar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        jLabel1.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tabla de Posibles Combinaciones");

        jLabel2.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Hora Inicial: ");

        labelHora.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelHora.setForeground(new java.awt.Color(153, 153, 153));
        labelHora.setText("H");

        dospuntos.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos.setText(":");

        labelMin.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMin.setForeground(new java.awt.Color(153, 153, 153));
        labelMin.setText("M");

        dospuntos1.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos1.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos1.setText(":");

        labelSeg.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelSeg.setForeground(new java.awt.Color(153, 153, 153));
        labelSeg.setText("S");

        dospuntos2.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos2.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos2.setText(":");

        labelMili.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMili.setForeground(new java.awt.Color(153, 153, 153));
        labelMili.setText("Mi");

        jLabel3.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Hora Final: ");

        labelHoraFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelHoraFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelHoraFinal.setText("H");

        dospuntos3.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos3.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos3.setText(":");

        labelMinFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMinFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelMinFinal.setText("M");

        dospuntos4.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos4.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos4.setText(":");

        labelSegFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelSegFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelSegFinal.setText("S");

        dospuntos5.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        dospuntos5.setForeground(new java.awt.Color(255, 0, 0));
        dospuntos5.setText(":");

        labelMiliFinal.setFont(new java.awt.Font("Goudy Old Style", 1, 24)); // NOI18N
        labelMiliFinal.setForeground(new java.awt.Color(153, 153, 153));
        labelMiliFinal.setText("Mi");

        labelSospechoso.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelSospechoso.setForeground(new java.awt.Color(255, 255, 0));
        labelSospechoso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSospechoso.setText("Sospechoso");

        labelArma.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelArma.setForeground(new java.awt.Color(255, 255, 0));
        labelArma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArma.setText("Arma");

        labelMotivo.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelMotivo.setForeground(new java.awt.Color(255, 255, 0));
        labelMotivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMotivo.setText("Motivo");

        labelParte.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelParte.setForeground(new java.awt.Color(255, 255, 0));
        labelParte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelParte.setText("Parte ");

        labelLugar.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        labelLugar.setForeground(new java.awt.Color(255, 255, 0));
        labelLugar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLugar.setText("Lugar");

        labelImgSospechoso.setText("jLabel4");

        labelImgArma.setText("jLabel4");

        labelImgMotivo.setText("jLabel4");

        labelImgParte.setText("jLabel4");

        labelImgLugar.setText("jLabel4");

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

        jLabel4.setFont(new java.awt.Font("Elephant", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 153));
        jLabel4.setText("BackTracking");

        jLabel5.setFont(new java.awt.Font("Elephant", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 0));
        jLabel5.setText("Solución:");

        labelSolSospechoso.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolSospechoso.setForeground(new java.awt.Color(255, 153, 51));
        labelSolSospechoso.setText("Sospechoso");

        labelSolArma.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolArma.setForeground(new java.awt.Color(255, 153, 51));
        labelSolArma.setText("Arma");

        labelSolParte.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolParte.setForeground(new java.awt.Color(255, 153, 51));
        labelSolParte.setText("Parte");

        labelSolMotivo.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolMotivo.setForeground(new java.awt.Color(255, 153, 51));
        labelSolMotivo.setText("Motivo");

        labelSolLugar.setFont(new java.awt.Font("Elephant", 2, 18)); // NOI18N
        labelSolLugar.setForeground(new java.awt.Color(255, 153, 51));
        labelSolLugar.setText("Lugar");

        labelSolImgSospechoso.setText("jLabel11");

        labelSolImgArma.setText("jLabel11");

        labelSolImgMotivo.setText("jLabel11");

        labelSolImgParte.setText("jLabel11");

        labelSolImgLugar.setText("jLabel11");

        jLabel7.setFont(new java.awt.Font("Elephant", 2, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cartas Marcadas Incorrectas:");

        ListaIncorrectas.setBackground(new java.awt.Color(0, 204, 255));
        ListaIncorrectas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ListaIncorrectas.setForeground(new java.awt.Color(51, 0, 51));
        ListaIncorrectas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaIncorrectasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ListaIncorrectas);

        jLabel6.setFont(new java.awt.Font("Elephant", 2, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 153));
        jLabel6.setText("Restricciones:");

        ListaRestricciones.setBackground(new java.awt.Color(0, 204, 255));
        ListaRestricciones.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ListaRestricciones.setForeground(new java.awt.Color(51, 0, 51));
        ListaRestricciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaRestriccionesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ListaRestricciones);

        jLabel10.setFont(new java.awt.Font("Forte", 1, 28)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("&");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(412, 412, 412)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelArma, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImgParte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelParte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(BotonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelMinFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelSegFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelMiliFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dospuntos2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelMili, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelSolSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(labelSolImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSolArma, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelSolMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelSolParte, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSolImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSolImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSolImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelSolLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(215, 215, 215)
                                        .addComponent(labelRestriccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelRestriccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelIncorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelHora)
                            .addComponent(dospuntos)
                            .addComponent(labelMin)
                            .addComponent(dospuntos1)
                            .addComponent(labelSeg)
                            .addComponent(dospuntos2)
                            .addComponent(labelMili))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelHoraFinal)
                            .addComponent(dospuntos3)
                            .addComponent(labelMinFinal)
                            .addComponent(dospuntos4)
                            .addComponent(labelSegFinal)
                            .addComponent(dospuntos5)
                            .addComponent(labelMiliFinal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSolImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSolSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolArma, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolParte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSolLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(labelIncorrecta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelRestriccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelRestriccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel10))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelArma)
                            .addComponent(labelMotivo)
                            .addComponent(labelParte)
                            .addComponent(labelLugar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelImgSospechoso, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgArma, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgParte, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelImgLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Programacion del boton atras
    private void BotonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAtrasActionPerformed
        
        //Valida que la tabla no sea la primera solucion, porque no hay atras
        if(this.indiceTabla != 0){
            this.indiceTabla--;
            PintarSolucionesPosibles();
        }
        
    }//GEN-LAST:event_BotonAtrasActionPerformed

    //Programacion del boton siguiente
    private void BotonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSiguienteActionPerformed
        
        //Valida que si pueda ir hacia adelante
        if(this.indiceTabla < this.posiblesSoluciones.size()-1){
            this.indiceTabla++;
            PintarSolucionesPosibles();
        }
        
    }//GEN-LAST:event_BotonSiguienteActionPerformed

    //Cada vez que se la un click a la tabla se modifica el indice global de la misma, y llama a pintar soluciones
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        this.indiceTabla = this.tabla.getSelectedRow();
        PintarSolucionesPosibles();    
    }//GEN-LAST:event_tablaMouseClicked
    
    //Cada vez que clickean la lista de incorrectas, entonces cambia la imagen de incorrectas
    private void ListaIncorrectasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaIncorrectasMouseClicked
        int i = this.ListaIncorrectas.getSelectedIndex();
        if(i>=0)
            PintarLabel(this.incorrectas.get(i).nombre, this.labelIncorrecta);
    }//GEN-LAST:event_ListaIncorrectasMouseClicked

    //Cada vez que clickean la lista de restricciones, cambia las imagenes de las restricciones
    private void ListaRestriccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaRestriccionesMouseClicked
        int i = this.ListaRestricciones.getSelectedIndex();
        if(i>=0){
            PintarLabel(this.restricciones.get(i)[0].nombre, this.labelRestriccion1);
            PintarLabel(this.restricciones.get(i)[1].nombre, this.labelRestriccion2);
        }
    }//GEN-LAST:event_ListaRestriccionesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAtras;
    private javax.swing.JButton BotonSiguiente;
    private javax.swing.JList<String> ListaIncorrectas;
    private javax.swing.JList<String> ListaRestricciones;
    public javax.swing.JLabel dospuntos;
    public javax.swing.JLabel dospuntos1;
    public javax.swing.JLabel dospuntos2;
    public javax.swing.JLabel dospuntos3;
    public javax.swing.JLabel dospuntos4;
    public javax.swing.JLabel dospuntos5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelArma;
    public javax.swing.JLabel labelHora;
    public javax.swing.JLabel labelHoraFinal;
    private javax.swing.JLabel labelImgArma;
    private javax.swing.JLabel labelImgLugar;
    private javax.swing.JLabel labelImgMotivo;
    private javax.swing.JLabel labelImgParte;
    private javax.swing.JLabel labelImgSospechoso;
    private javax.swing.JLabel labelIncorrecta;
    private javax.swing.JLabel labelLugar;
    public javax.swing.JLabel labelMili;
    public javax.swing.JLabel labelMiliFinal;
    public javax.swing.JLabel labelMin;
    public javax.swing.JLabel labelMinFinal;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel labelParte;
    private javax.swing.JLabel labelRestriccion1;
    private javax.swing.JLabel labelRestriccion2;
    public javax.swing.JLabel labelSeg;
    public javax.swing.JLabel labelSegFinal;
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
    public javax.swing.JTable tabla;
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
